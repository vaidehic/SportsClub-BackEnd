pipeline {
	

	
	environment {
        DATE = new Date().format('yy.M')
        TAG = "${DATE}.${BUILD_NUMBER}"
    }
	
	

    agent any
       tools{
        maven "Maven 3.6.3"
        jdk "JDK-11"
    }  
	
	
	
    stages{
             stage('Compile'){
            steps{
                echo "COMPILE"
             bat 'mvn clean install'
            }
        }
    
	    stage('Docker Build') {
            steps {
                script {
                    docker.build("sportsclub-docker-local/sportsclub:${TAG}")
                }
            }
        }
	stage('Pushing Docker Image to Jfrog Artifactory') {
            steps {
                script {
                    docker.withRegistry('http://localhost:8082/', 'Jfrog-jenkinsUserPassword') {
                        docker.image("sportsclub-docker-local/sportsclub:${TAG}").push()
                        docker.image("sportsclub-docker-local/sportsclub:${TAG}").push("latest")
                    }
                }
            }
        }
        stage('Deploy'){
            steps {
                bat "docker stop sportsclub | true"
                bat "docker rm sportsclub | true"
                bat "docker run --name sportsclub -d -p 8082:8080 http://localhost:8082/artifactory/sportsclub-docker-local/:${TAG}"
            }
        }	    
	    
	    
	    
         
         stage('Unit Test Case') {
             steps {
                 bat 'mvn test'
             }
             
             post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
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
