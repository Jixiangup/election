FROM maven:3.6.3-openjdk-17 AS build
ENV TZ=Asia/Shanghai
#COPY settings.xml /usr/share/maven/conf/settings.xml

COPY . .
RUN mvn clean package

FROM openjdk:17
ENV TZ=Asia/Shanghai
ARG JAR_FILE=election-api/target/election-api-1.0.0.jar
COPY --from=build ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]