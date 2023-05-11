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
              
    
//         stage('Sonar Analysis') {
//             steps {
//                 // use the SonarQube Scanner to analyze the project
//                 withSonarQubeEnv('SonarQubeServer') {
//                     sh 'mvn sonar:sonar'
//                 }
//             }
	    //         }
            
            stage('Docker Build') {
            steps {
                script {
                    docker.build("docker-vaidehi/sportsclub-image:${TAG}")
                }
            }
        }
	stage('Pushing Docker Image to Jfrog Artifactory') {
            steps {
                script {
                    docker.withRegistry('http://172.27.59.80:8082/', 'artifactory-docker') {
                        docker.image("docker-vaidehi/sportsclub-image:${TAG}").push()
                        docker.image("docker-vaidehi/sportsclub-image:${TAG}").push("latest")
                    }
                }
            }
        }
        stage('Deploy'){
            steps {
                sh "docker stop sportsclub-image | true"
                sh "docker rm sportsclub-image | true"
                sh "docker run --network vaidehi-sports-network --name sportsclub-image -d -p 8082:8080 172.27.59.80:8082/artifactory/docker-vaidehi/:${TAG}"
            }
        }	    
    
        }
    }   
