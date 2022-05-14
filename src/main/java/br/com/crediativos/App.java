package br.com.crediativos;

import java.util.ArrayList;
import java.util.List;

import com.trevis.credito.Applicant;

import org.kie.api.KieServices;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.internal.command.CommandFactory;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.KieContainerResourceList;
import org.kie.server.api.model.KieServiceResponse.ResponseType;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.RuleServicesClient;

/**
 * Hello world!
 *
 */
public class App {

    private static final String URL = "http://localhost:8180/kie-server/services/rest/server";
    private static final String USER = "kieserver";
    private static final String PASSWORD = "kieserver1!";

    private static final MarshallingFormat FORMAT = MarshallingFormat.JSON;
    private static KieServicesConfiguration conf;
    private static KieServicesClient kieServicesClient;

    // private static final String CONTAINERID = "credito";
    private static final String CLASS_NAME = "Applicant";

    public static void initialize() {
        System.out.println("Init >>");
        conf = KieServicesFactory.newRestConfiguration(URL, USER, PASSWORD);
        conf.setMarshallingFormat(FORMAT);
        kieServicesClient = KieServicesFactory.newKieServicesClient(conf);
    }

    public static void listContainers() {
        KieContainerResourceList containersList = kieServicesClient.listContainers().getResult();
        List<KieContainerResource> kieContainers = containersList.getContainers();
        System.out.println("Available containers: ");
        for (KieContainerResource container : kieContainers) {
            System.out.println("\t" + container.getContainerId() + " (" + container.getReleaseId() + ")");
        }
    }

    public static void main(String[] args) {

        initialize();
        List<KieContainerResource> kieContainers = kieServicesClient.listContainers().getResult().getContainers();
        // // listContainers();

        // if (kieContainers.size() == 0) {
        // System.out.println("No containers available...");
        // // return;
        // } else {
        // System.out.println(
        // "Available: " + kieContainers.size() + "\nReleaseId: " +
        // kieContainers.get(0).getReleaseId());
        // }

        KieContainerResource container = kieContainers.get(0);
        String containerId = container.getContainerId();

        RuleServicesClient ruleClient = kieServicesClient.getServicesClient(RuleServicesClient.class);
        KieCommands commandsFactory = KieServices.Factory.get().getCommands();

        Applicant applicant = new Applicant();
        applicant.setAge(50);
        applicant.setMoney(10000);
        applicant.setApproved(false);

        List<Command<?>> cmds = new ArrayList<Command<?>>();
        KieCommands commands = KieServices.Factory.get().getCommands();
        cmds.add(commands.newInsert(applicant, CLASS_NAME));
        cmds.add(commands.newFireAllRules());

        BatchExecutionCommand batchCommands = CommandFactory.newBatchExecution(cmds);
        ServiceResponse<org.kie.api.runtime.ExecutionResults> response = ruleClient
                .executeCommandsWithResults(containerId, batchCommands);

        // Command<?> insert = commandsFactory.newInsert(applicant);
        // Command<?> fireAllRules = commandsFactory.newFireAllRules();
        // Command<?> batchCommand =
        // commandsFactory.newBatchExecution(Arrays.asList(insert, fireAllRules));

        // ServiceResponse<String> executeResponse =
        // ruleClient.executeCommands(containerId, batchCommand);

        System.out.println("\nI've seen things, you people wouldn't believe" +
                "\n... attack ships on fire off the shoulder of Orion." +
                "\nI've watched see Beams glitter in the dark near the Tannhauser Gate." +
                "\nAll those moments, will be lost in time like tears in rain...");

        if (response.getType() == ResponseType.SUCCESS) {
            System.out.println("[Off-world] Commands executed with success! :: ");
            // System.out.println(response.getResult());
            Applicant updatedApplicant = (Applicant) response.getResult().getValue(CLASS_NAME);
            System.out.print("Is Roy approved? [N6MAA10816]::" + updatedApplicant.getApproved());
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
        } else {
            System.out.println("Error executing rules. Message: ");
            System.out.println(response.getMsg());
        }
    }
}
