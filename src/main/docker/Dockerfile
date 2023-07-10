FROM openjdk:11
WORKDIR application
ARG JAR_FILE=maven/${project.build.finalName}.jar
COPY ${JAR_FILE} ${project.build.finalName}.jar
ENTRYPOINT ["java", "-jar", "${project.build.finalName}.jar"]