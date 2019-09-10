FROM openjdk:11
ADD target/aws-ecs-springboot.jar aws-ecs-springboot.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "aws-ecs-springboot.jar"]