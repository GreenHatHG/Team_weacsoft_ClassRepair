FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=classrepair-system/target/*.jar
COPY ${JAR_FILE} app.jar
RUN echo "Asia/Shanghai" > /etc/timezone;
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar ${0} ${@}"]