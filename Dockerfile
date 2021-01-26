FROM maven:3.6-jdk-11 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN rm -rf /home/app/src/client/node_modules
RUN mvn -Dmaven.test.skip -f /home/app/pom.xml clean package


FROM openjdk:11-jre
COPY --from=build /home/app/target/bid-0.0.1-SNAPSHOT.jar bid_app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","bid_app.jar","--spring.config.location=classpath:/application.test.yml"]
