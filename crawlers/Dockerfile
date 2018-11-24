FROM maven:3.3-jdk-8-onbuild 

FROM openjdk:8-jre-alpine
COPY --from=0 /usr/src/app/target/crawlers /opt/crawlers
CMD ["java","-jar","/opt/crawlers/crawlers.jar"]
