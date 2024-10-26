FROM openjdk:21-jdk-slim

WORKDIR /com.hackathon.bankingapp.application

COPY target/*.jar com.hackathon.bankingapp.application.jar

EXPOSE 3000

CMD ["java", "-jar", "com.hackathon.bankingapp.application.jar"]
