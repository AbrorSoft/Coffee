pipeline {

    agent any

    tools{
        jdk 'JAVA_HOME'
        maven 'maven3'
    }

    stages {

        stage('Code Checkout') {

            steps {
                git branch: 'master', changelog: false, poll: false, url: 'https://github.com/AbrorSoft/Coffee.git'
            }

        }

        stage('Build & Test') {

            steps {
                // Run Maven on a Unix agent.
                // bat 'npx rimraf node_modules'
                // bat 'npm cache clean --force'
                bat 'npm install'
                bat 'echo %JAVA_HOME%'
                bat 'java --version'

                bat 'mvn --version'
                bat 'mvn clean'
                bat "mvn package"

            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    archiveArtifacts 'target/*.jar'
                }
            }
        }

        stage('Deploy Image') {

            steps {
                bat 'docker-compose -f coffee.yml up'
            }

        }
    }
}
