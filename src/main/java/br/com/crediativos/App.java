package br.com.crediativos;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.controller.api.model.spec.ServerTemplateList;
import org.kie.server.controller.client.KieServerControllerClient;
import org.kie.server.controller.client.KieServerControllerClientFactory;

import br.com.crediativos.model.Applicant;

/**
 * Hello world!
 *
 */
public class App {

    private static final String URL = "http://localhost:8180/kie-server/services/rest/server";
    private static final String USER = "kieserver";
    private static final String PASSWORD = "kieserver1!";

    private static final MarshallingFormat FORMAT = MarshallingFormat.JSON;

    private static final String CONTAINERID = "credito";
    private static final String CLASS_NAME = "Applicant";

    /**
     * Reference:
     * https://access.redhat.com/documentation/pt-br/red_hat_decision_manager/7.6/html/interacting_with_red_hat_decision_manager_using_kie_apis/controller-java-api-con_kie-apis
     */

    public static void main(String[] args) {
        System.out.println("Hello World!");

        KieServerControllerClient client = KieServerControllerClientFactory.newRestClient(URL, USER, PASSWORD);

        final ServerTemplateList serverTemplateList = client.listServerTemplates();
        System.out.println(String.format("Found %s server template(s) at controller url: %s",
                serverTemplateList.getServerTemplates().length,
                URL));

        // TODO Conectando e autenticando
        // TODO Confirmar URL para Server Template Listing
        // TODO Preparar método de consumo Regra
        // TODO Confirmar pacote/assinatura do POJO

        // KieServices ks = KieServices.Factory.get();
        // KieContainer container = ks.getKieClasspathContainer();
        // KieSession session = container.newKieSession("crediativos-session");

        // Applicant applicant = new Applicant();
        // applicant.setAge(50);
        // applicant.setMoney(10000);
        // applicant.setApproved(false);

        // session.fireAllRules();
    }
}
