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

## HTTP Header
- aplication/json
- aplication/XML

## Base URL
http://localhost:8080/kie-server/services/rest/ 
Example request: http://localhost:8080/kie-server/services/rest/server/containers/


## Troubleshooting
[405](https://groups.google.com/g/drools-usage/c/bHkNzEkoV5g?pli=1)