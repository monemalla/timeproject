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
			   
			   bat 'docker tag ${ARTIFACTID}:latest monemalla/${ARTIFACTID}:${DOCKER_IMAGE_VERSION}'
			   bat 'docker push monemalla/${ARTIFACTID}:${DOCKER_IMAGE_VERSION}'
		}
    }
 }
 }

