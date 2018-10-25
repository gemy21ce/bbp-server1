FROM openjdk:8-jdk-alpine
ENV jmx_port=1098
ENV ENV_JDBCTEMPLATE_LOG_LEVEL=OFF
ENV ENV_ROOT_LOG_LEVEL=INFO
RUN mkdir -p /usr/local/server1
ADD @project.build.finalName@.jar /usr/local/server1/
CMD java -Dcom.sun.management.jmxremote=true \
            -Dcom.sun.management.jmxremote.port=$jmx_port \
            -Dcom.sun.management.jmxremote.authenticate=false \
            -Dcom.sun.management.jmxremote.ssl=false \
            -jar /usr/local/server1/@project.build.finalName@.jar
