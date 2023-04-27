pipeline {

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
            
            stage('Upload_Artifact') {
steps {
    script{
def server = Artifactory.server 'artifactory'
               def uploadSpec = """{
"files": [
{
      "pattern": "target/*.jar",
"target": "sportsclub/"
}
]
}"""
server.upload(uploadSpec)
}
}

             }
             
            
         stage('Build Docker Image') {
         
         steps{
                  bat "docker build -t vaidehi/sportsclub ."  
         }
     }
	    
     stage('Publish Docker Image') {
         
        steps{

    	      withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'dockerPassword', usernameVariable: 'dockerUser')]) {
    		    bat "docker login -u vaidehichaudhary77 -p Vaidehic@123
	      }
        	bat "docker push vaidehi/sportsclub"
         }
    } 
        
        
        
        
        
        
        
        
        }
    }   
