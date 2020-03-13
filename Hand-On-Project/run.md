java -javaagent:elastic-apm-agent-1.2.0.jar -Delastic.apm.service_name=config-server -Delastic.apm.application_packages=se.magnus.springcloud.configserver -Delastic.apm.server_urls=http://localhost:8200 -jar ./spring-cloud/config-server/build/libs/config-server-1.0.0-SNAPSHOT.jar

java -javaagent:elastic-apm-agent-1.2.0.jar -Delastic.apm.service_name=eureka-server -Delastic.apm.application_packages=se.magnus.springcloud.eurekaserver -Delastic.apm.server_urls=http://localhost:8200 -jar ./spring-cloud/eureka-server/build/libs/eureka-server-1.0.0-SNAPSHOT.jar


java -javaagent:elastic-apm-agent-1.2.0.jar -Delastic.apm.service_name=authorization-server -Delastic.apm.application_packages=se.magnus.springcloud.authorizationserver -Delastic.apm.server_urls=http://localhost:8200 -jar ./spring-cloud/authorization-server/build/libs/authorization-server-1.0.0-SNAPSHOT.jar


java -javaagent:elastic-apm-agent-1.2.0.jar -Delastic.apm.service_name=recommendation-service -Delastic.apm.application_packages=se.magnus.microservices.core.recommendation -Delastic.apm.server_urls=http://localhost:8200 -jar ./microservices/recommendation-service/build/libs/recommendation-service-1.0.0-SNAPSHOT.jar


java -javaagent:elastic-apm-agent-1.2.0.jar -Delastic.apm.service_name=product-service -Delastic.apm.application_packages=se.magnus.microservices.core.product -Delastic.apm.server_urls=http://localhost:8200 -jar ./microservices/product-service/build/libs/product-service-1.0.0-SNAPSHOT.jar


java -javaagent:elastic-apm-agent-1.2.0.jar -Delastic.apm.service_name=product-composite-service -Delastic.apm.application_packages=se.magnus.microservices.core.product-composite -Delastic.apm.server_urls=http://localhost:8200 -jar ./microservices/product-composite-service/build/libs/product-composite-service-1.0.0-SNAPSHOT.jar
