FROM centos:7

MAINTAINER VASUKIDILEEP

RUN yum install -y wget && \
yum install -y java-1.7.0-openjdk java-1.7.0-openjdk-devel

COPY /Users/user/Desktop/football/apache-tomcat-9.0.12.tgz /opt
RUN cd /opt
RUN tar -xvf apache-tomcat-9.0.12.tgz

COPY /Users/user/Desktop/football/target/football-project.war /opt/apache-tomcat-9.0.12/webapps

EXPOSE 8080

CMD ["bash"]
