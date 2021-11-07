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
	/*
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
			  
			       bat "docker login -u monemalla -p 98678795mm"
			       bat "docker tag ${ARTIFACTID}:latest monemalla/${ARTIFACTID}:${DOCKER_IMAGE_VERSION}"
			       bat "docker push monemalla/${ARTIFACTID}:${DOCKER_IMAGE_VERSION}"
				
				  echo "monem monem"
				  
				   
				 }
			   }
	    stage ('deliver to docker compose'){
		  steps{
		    echo 'Inside deliver to docker compse stage'			
			
		  }
		}
		
		*/
		 stage('Test') {
            steps {
                sh 'echo "Fail!"; exit 1'
            }
        }
		
    }

	post {
        always {
            echo 'This will always run'
        }
        success {
            echo 'This will run only if successful'
        }
        failure {
            mail bcc: 'allagui967@gmail.com', body: "<b>Example</b>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br> URL de build: ${env.BUILD_URL}<br> error: error=run.getPreviousBuild().result.toString().equals(run.result.toString())", cc: '', charset: 'UTF-8', from: '', mimeType: 'text/html', replyTo: '', subject: "ERROR CI: Project name -> ${env.JOB_NAME}", to: "allagui967@gmail.com";
        }
        unstable {
            echo 'This will run only if the run was marked as unstable'
        }
        changed {
            echo 'This will run only if the state of the Pipeline has changed'
            echo 'For example, if the Pipeline was previously failing but is now successful'
        }
    }	
    }
 }

