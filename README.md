# aws-ecs-springboot
Springboot Application packed into a docker container to be deployed on AWS ECS

The deployment is done using [build-pipeline-docker-ecs.yaml](build-pipeline-docker-ecs.yaml)

This step creates a CodePipeline which is triggered upon a commit to the master branch

## For local testing
Clone the repository.  Make sure you have Java11 and Docker installed.  If you do not want to install Java11, you may chose to mount the project onto a docker container based on openjdk:11 and docker installed.

Run the following commands
````
mvn clean install
docker build -t <Your-Image-Name>:<Your-Tag> .
docker run -p 80:8080 <Your-Image-Name>:<Your-Tag>
````

Once the docker container starts running, you can test it on ``localhost/employees``.  At start you would not have any data. Create data using ``POST`` requests.

## AWS Deployment
Upload [build-pipeline-docker-ecs.yaml](build-pipeline-docker-ecs.yaml) to CloudFormation console or using AWS CLI.  This is the only manual step to be done using AWS Console. It has all the configuration to automate the creation and deployment of the service on ECS.

### _Note_
1. The value for ``DockerImageRepository`` given after uploading the pipeline file should match ``DockerImageRepository`` in ``config.json``
2. Here the database is embedded within a running instance. So each running task (in this case 2) has its own database and consequent GET requests may look different because of the usage of LoadBalancer although the endpoint is the same.
3. The file [build-pipeline-docker-ecs.yaml](build-pipeline-docker-ecs.yaml) is generic and not directly related to this project. It is only packed into this repository to have all components at a single place.
4. Upon each commit to the master, a new TaskDefinition is created and the ECS service is updated.
5. This project is not production ready since it does not have a real persistent component.
6. Lifecycle policy is configured to have only 5 image versions in ECR. You may not want to do it in production.

After a successful deployment you now should have another CloudFormation stack created by the **Deploy** stage of the pipeline.
Among **Outputs** of this stack you will find the endpoint for your API