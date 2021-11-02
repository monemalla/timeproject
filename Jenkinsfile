pipeline {
    agent any

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

                //sh "mvn verify"

                withSonarQubeEnv('SonarQube') {
                    bat "mvn -Dsonar.sources=src/main sonar:sonar"
                }
            }
        }

        stage('Nexus') {
            steps {

                echo 'INSIDE Nexus'
                bat "mvn clean package deploy:deploy-file -DgroupId=tn.esprit.spring -DartifactId=spring-boot-data-jpa-entity -Dversion=2.4 -DgeneratePom=true -Dpackaging=jar -DrepositoryId=deploymentRepo  -Durl=http://localhost:8081/repository/maven-releases/ -Dfile=target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-0.0.1-SNAPSHOT.jar"
            }
        }
    }
}
