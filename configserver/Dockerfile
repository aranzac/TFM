FROM java:8-jre

ADD /target/config.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/config.jar"]
COPY target/config.jar config.jar
EXPOSE 8761



