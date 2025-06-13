FROM quay.io/wildfly/wildfly:35.0.1.Final-jdk21

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin123! --silent

COPY ./target/worktime-server-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/worktime-server.war

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]