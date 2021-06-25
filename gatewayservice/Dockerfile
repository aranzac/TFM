FROM java:8-jre

ADD /target/gateway.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/gateway.jar"]
COPY target/gateway.jar gateway.jar
EXPOSE 8080

# FROM java:8-jre

# COPY . /usr/local/src/gatewayservice
# WORKDIR /usr/local/src/gatewayservice

# # ADD target/configserver.jar /app/
# CMD ["java", "-Xmx200m", "-jar", "/usr/local/src/gatewayservice/target/gateway.jar"]

# EXPOSE 8080