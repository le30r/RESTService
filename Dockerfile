FROM openjdk:17
EXPOSE 8080
ADD target/rest-beeline.jar rest-beeline.jar
ENTRYPOINT ["java","-jar","/rest-beeline.jar"]
