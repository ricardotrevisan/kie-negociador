# Docker Environment

[Referência 1](https://groups.google.com/g/jbpm-usage/c/P1J9xVaZRBA?pli=1)

workbench is just for autoring and administration it does not execute processes. this is what Kie server does. So if you’re running them on docker you should have two containers - one for workbench and the other for kie server. Kie server needs to connect to controller (workbench) so it get information about server templates. To check if they are connected in the Execution Server perspecitve (in workbench) you should see one conected under Remote Servers section.


[Images](https://github.com/kiegroup/kie-docker-ci-images/blob/main/README.md)

Workbench:

    docker run -p 8080:8080 -p 8001:8001 -d -e JAVA_OPTS="-Djava.net.preferIPv4Stack=true" --name jbpm-workbench jboss/jbpm-workbench-showcase:latest



Engine: 

    docker run -p 8180:8080 -d --name kie-server -e JAVA_OPTS="-Djava.net.preferIPv4Stack=true" --link jbpm-workbench:kie_wb jboss/kie-server-showcase:latest



[GIT configuration](https://kbroman.org/github_tutorial/pages/first_time.html)


# Requirements [REST API](https://docs.drools.org/7.69.0.Final/drools-docs/html_single/index.html#kie-server-rest-api-con_kie-apis)


## Authentication 
- KIE Server REST API requires HTTP Basic authentication or token-based authentication for the usser role kie-server
- Admin > Security 
> kieserver user creation under kiemgmt & rest-all groups plus all functions


## HTTP Header
- aplication/json
- aplication/XML


## Base URL and URIs
Server Info: http://localhost:8180/kie-server/services/rest/server

Containers: Info http://localhost:8180/kie-server/services/rest/server/containers/

Release ID: http://localhost:8180/kie-server/services/rest/server/containers/credito/release-id

Rule Firing POST: http://localhost:8180/kie-server/services/rest/server/containers/instances/credito


---

## Red Hat API REST Reference
[Documentation](https://access.redhat.com/documentation/pt-br/red_hat_decision_manager/7.6/html/interacting_with_red_hat_decision_manager_using_kie_apis/controller-java-api-con_kie-apis)
[API Reference](https://docs.jboss.org/drools/release/7.59.0.Final/kie-api-javadoc/index.html)


### TODO List
[Listing Server Templates] (https://www.notion.so/Drools-2530968747d14f8aafaa60b3b0e31801)



---
## Troubleshooting
[405](https://groups.google.com/g/drools-usage/c/bHkNzEkoV5g?pli=1)
[Log](https://mkyong.com/logging/slf4j-logback-tutorial/)