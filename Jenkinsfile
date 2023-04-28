pipeline {
	
environment {
registry = "vaidehichaudhary77/sportsclub"
registryCredential = 'dockerhub_id'
dockerImage = ''
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
    
	    stage('Building our image') {
steps{
script {
dockerImage = docker.build registry + ":$BUILD_NUMBER"
}
}
}
stage('Deploy our image') {
steps{
script {
docker.withRegistry( '', registryCredential ) {
dockerImage.push()
}
}
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
             
     
        
        }
    }   
