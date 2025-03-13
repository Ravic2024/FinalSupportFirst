pipeline {
    agent any

    environment {
        MAVEN_HOME = '/opt/apache-maven-3.9.6'
        PATH = "${MAVEN_HOME}/bin:${env.PATH}"
    }

    triggers {
        pollSCM('H/5 * * * *')  // Poll every 5 minutes
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'SF_SanityTesting_Demo_07022025', credentialsId: 'gitlab', url: 'https://pexgit.growipx.com/cio-products/test-automation'
            }
        }
        stage('Test Cleanup 1') {  // Changed the stage name
            steps {
                dir('/var/lib/jenkins/jobs/supportfirst-test-pipeline/htmlreports/Spark/') {
                    sh 'ls'  // For debugging purposes
                    sh 'rm -rf *'
                }
            }
        }
        stage('Test Cleanup 2') {  // Changed the stage name
            steps {
                dir('/var/lib/jenkins/workspace/supportfirst-test-pipeline/https:/jenkins-test.growatiopex.com/job/supportfirst-test-pipeline/Spark') {
                    sh 'ls'  // For debugging purposes
                    sh 'rm -rf *'
                }
            }
        }
        stage('Test') {
            steps {
                dir('/var/lib/jenkins/workspace/supportfirst-test-pipeline') {
                    sh 'ls'  // For debugging purposes
                    sh 'mvn clean test'
                }
            }
        }
    }

    post {
        always {
            publishHTML([
                allowMissing: false, 
                alwaysLinkToLastBuild: false, 
                keepAll: false, 
                reportDir: 'https://jenkins-test.growatiopex.com/job/supportfirst-test-pipeline/Spark/',  
                reportFiles: 'Spark.html', 
                reportName: 'Spark', 
                reportTitles: '', 
                useWrapperFileDirectly: true
            ])
        }
    }
}