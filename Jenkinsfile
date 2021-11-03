pipeline {
    agent any

    tools {
        maven 'maven'
        jdk 'jdk'
    }
			try {
    throw new Exception('fail!')
} catch (all) {
    currentBuild.result = "FAILURE"
} finally {
     node('master') {
        step([$class: 'Mailer', notifyEveryUnstableBuild: true, recipients: 'allagui967@gmail.com', sendToIndividuals: true])
    }   
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

        stage('Nexus') {
            steps {

                echo 'INSIDE Nexus'
                bat "mvn deploy:deploy-file -DgroupId=tn.esprit.spring -DartifactId=spring-boot-data-jpa-entity -Dversion=$BUILD_NUMBER -DgeneratePom=true -Dpackaging=jar -DrepositoryId=deploymentRepo  -Durl=http://localhost:8081/repository/maven-releases/ -Dfile=target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-0.0.1-SNAPSHOT.jar"
            }
        }
	
		
    }

		
 }

