pipeline {
  // The pipeline can run on any available agent.
    agent any

  // Specifies the tools required for the pipeline
    tools{
        jdk 'jdk17'
        maven 'maven3'
    }
  // Clone the repository from GitHub on the 'master' branch without fetching changelogs or polling for changes
    stages {

        stage('Code Checkout') {

            steps {
                git branch: 'master', changelog: false, poll: false, url: 'https://github.com/AbrorSoft/Coffee.git'
            }

        }
// Executes a shell script to check Java and Maven versions, clean the project, and package it
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
// Post-processing after the build
            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                 // Archive the built JAR files as artifacts
                    archiveArtifacts 'target/*.jar'
                }
            }

        }

        stage('Deploy') {

            steps {
            // Log message for deployment
//             echo 'Deploying jar file to server directory...'
            //

            // Get the first JAR file in the target directory
//              JAR_FILE=$(ls target/*.jar | head -n 1)
            //
            // Define the destination directory
//              DEST_DIR=/home/coffee/jars
            //

            // Switch to the Jenkins user
//           sudo -u jenkins -i
            //

            // Log the file being copied
//             echo "Copying $JAR_FILE to $DEST_DIR"
            //

            // Copy the JAR file to the destination directory
//             sudo cp $JAR_FILE $DEST_DIR
            //
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
             // Log message for restarting the project
                echo 'Restarting coffee project'

                // Switch to the Jenkins user
//                 sudo -u jenkins -i
                //
                // Restart the Docker container running the coffee project
//                   sudo docker restart docker_coffee_1
                //
                sh '''
                sudo -u jenkins -i
                sudo docker restart docker_coffee_1
                '''
            }

        }
    }
}
