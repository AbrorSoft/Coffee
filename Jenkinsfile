pipeline {

    agent any

    tools{
        jdk 'jdk17'
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
                sh '''
                java --version
                mvn --version
                mvn clean
                mvn package
                '''
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    archiveArtifacts 'target/*.jar'
                }
            }

        }

        stage('Deploy') {

            steps {
                echo 'Deploying jar file to server directory...'
                sh '''
                JAR_FILE=$(ls target/*.jar | head -n 1)
                DEST_DIR=/home/coffee/jars

                sudo -u jenkins -i
                echo "Copying $JAR_FILE to $DEST_DIR"
                sudo cp $JAR_FILE $DEST_DIR
                '''
            }

        }

        stage('Restart') {

            steps {
                echo 'Restarting coffee project'
                sh '''
                sudo -u jenkins -i
                sudo docker restart docker_coffee_1
                '''
            }

        }
    }
}
