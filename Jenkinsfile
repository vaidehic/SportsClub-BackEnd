pipeline {

    agent any
       tools{
        maven "Maven 3.6.3"
        jdk "JDK-11"
    }  
        stages {
         stage('Build') {
             steps {
                 echo 'Building...'
             }
         }
             stage('Compile'){
            steps{
                echo "COMPILE"
             bat 'mvn clean install'
            }
        }
        stage('Sonar Analysis') {
            steps {
                // use the SonarQube Scanner to analyze the project
                withSonarQubeEnv('SonarQubeServer') {
                    bat 'mvn sonar:sonar'
                }
            }
        }
         
        }
    }   
