FROM ubuntu
RUN apt-get update
RUN apt-get upgrade -y
RUN apt-get install -y default-jdk
COPY  simple-app.jar /opt/simple-app.jar
ENTRYPOINT java -jar /opt/simple-app.jar --host=0.0.0.0