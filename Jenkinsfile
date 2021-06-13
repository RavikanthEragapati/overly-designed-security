//Scripted pipeline
//Declarative pipeline

pipeline {
  agent any

  stages {
    stage("build") {
      steps {
        echo 'This is build stage - Executing'
        script{
                sh './gradlew clean build --no-daemon'
        }
      }
    }

    stage("test") {
      steps {
        echo 'This is test stage - Executing'
      }
    }

    stage("deploy") {
      steps {
        echo 'This is deploy stage - Executing'
        script{
            sh 'cd ~/.jenkins/workspace/my-pipeline_main/build/libs'
            sh 'pwd'
            sh 'ls -a'
           // sh 'java -jar overly-designed-security-0.0.1-SNAPSHOT.jar'
        }

      }
    }

  }
}