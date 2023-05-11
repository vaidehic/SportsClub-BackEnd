pipeline {
	

	
	environment {
        DATE = new Date().format('yy.M')
        TAG = "${DATE}.${BUILD_NUMBER}"
    }
	
	

    agent
	{
	node{
		label 'linux-slave'
	}
	}
	
       tools{
        maven "maven-linux-slave"
        jdk "linux-jdk"
    }  
	
	
	
    stages{
             stage('Compile'){
            steps{
                echo "COMPILE"
             sh 'mvn clean install'
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
                sh "docker stop sportsclub | true"
                sh "docker rm sportsclub | true"
                sh "docker run --name sportsclub -d -p 8082:8080 http://localhost:8082/artifactory/sportsclub-docker-local/:${TAG}"
            }
        }	    
	    
	    
	    
         
         stage('Unit Test Case') {
             steps {
                 sh 'mvn test'
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
                    sh 'mvn sonar:sonar'
                }
            }
        }
            
            
        
        }
    }   
