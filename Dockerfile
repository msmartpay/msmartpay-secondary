FROM tomcat:8.5

COPY admin.war /usr/local/tomcat/webapps/admin.war
COPY agent.war /usr/local/tomcat/webapps/agent.war
COPY DS.war /usr/local/tomcat/webapps/DS.war
COPY DSMRA.war /usr/local/tomcat/webapps/DSMRA.war
COPY MDS.war /usr/local/tomcat/webapps/MDS.war
COPY MRA1.0.war /usr/local/tomcat/webapps/MRA1.0.war
COPY collection.war /usr/local/tomcat/webapps/collection.war
COPY schedular.war /usr/local/tomcat/webapps/schedular.war
COPY ROOT.war /usr/local/tomcat/webapps/ROOT.war
