#pull jdk image
FROM openjdk:11
#add/copy jar file from target to docker file system
ADD ./target/idea-hamster-posting-idea-0.0.1-SNAPSHOT.jar /usr/src/idea-hamster-posting-idea-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
#run the jar file
ENTRYPOINT ["java","-jar","idea-hamster-posting-idea-0.0.1-SNAPSHOT.jar"]





