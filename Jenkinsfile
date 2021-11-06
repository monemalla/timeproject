pipeline {
    agent any
	
	environment { 
        POM = readMavenPom(file:'pom.xml')
        ARTIFACTID=POM.getArtifactId()
        ARTIFACT_VERSION = POM.getVersion()
        DOCKER_IMAGE_VERSION = "${env.BUILD_NUMBER}"
    }

    tools {
        maven 'maven'
        jdk 'jdk'
		dockerTool 'docker'
    }
	

    stages {
        stage('Build') {

            steps {

                echo 'INSIDE Build Stage'

                bat "mvn clean package"
            }
        }

		
        stage('Sonar') {
 
            steps {

                echo 'INSIDE Sonar Stage'

                withSonarQubeEnv('SonarQube') {
                    bat "mvn -Dsonar.sources=src/main sonar:sonar"
                }
            }
        }
		stage ('Build Docker Image'){
		    steps {
			   echo 'Inside build Docker Image Stage'
			   
			   bat "mvn dockerfile:build -Ddockerfile.repository=${ARTIFACTID}"
		}
    }
	    stage ('Push to registry'){
		    steps {
			   echo 'Inside Push to registry Stage'
			  
			     //withDockerRegistry([ credentialsId: "dockerHub", url: "https://hub.docker.com/" ]){
			       //bat "docker tag ${ARTIFACTID}:latest monemalla/${ARTIFACTID}:${DOCKER_IMAGE_VERSION}"
			       //bat "docker push monemalla/${ARTIFACTID}:${DOCKER_IMAGE_VERSION}"
				  docker login -u monemalla -p 98678795mm
				  echo "monem monem"
				  
				   
				 //}
			   }
		
		}
    }
 }

