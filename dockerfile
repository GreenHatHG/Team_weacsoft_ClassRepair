FROM java:openjdk-8u92-jre-alpine
ENV LANG en_GB.UTF-8
RUN apk add --update ttf-dejavu && rm -rf /var/cache/apk/*
VOLUME /tmp
ARG JAR_FILE=classrepair-system/target/*.jar
COPY ${JAR_FILE} app.jar
RUN echo "Asia/Shanghai" > /etc/timezone;
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar ${0} ${@}"]