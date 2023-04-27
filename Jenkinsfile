pipeline {

    agent any
       tools{
        maven "Maven 3.6.3"
        jdk "JDK-11"
    }  
             stage('Compile'){
            steps{
                echo "COMPILE"
             bat 'mvn clean install'
            }
        }
    
              stages {
         stage('Test') {
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
