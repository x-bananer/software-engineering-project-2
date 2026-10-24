pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    environment {
        DOCKERHUB_REPO = 'ksenishl/software-engineering-project-2'
        DOCKER_IMAGE_TAG = 'latest'
        DOCKERHUB_CREDENTIALS_ID = 'dockerhub-credentials'
    }

    stages {
        stage('checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/x-bananer/software-engineering-project-2.git'
            }
        }

        stage('build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('make report') {
            steps {
                sh 'mvn verify'
                jacoco(
                    exclusionPattern: '**/Main.class'
                )
            }
        }

        stage('publish test results') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }

        stage('build docker image') {
            steps {
                script {
                    docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}", '.')
                }
            }
        }

        stage('push docker image to docker hub') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: DOCKERHUB_CREDENTIALS_ID,
                        usernameVariable: 'DOCKERHUB_USERNAME',
                        passwordVariable: 'DOCKERHUB_PASSWORD'
                    )
                ]) {
                    sh 'echo "$DOCKERHUB_PASSWORD" | docker login -u "$DOCKERHUB_USERNAME" --password-stdin'
            		sh 'docker push "$DOCKERHUB_REPO:$DOCKER_IMAGE_TAG"'
                }
            }
        }
    }
}
