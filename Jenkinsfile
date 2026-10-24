pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    environment {
        DOCKERHUB_REPO = 'ksenishl/software-engineering-project-2'
        DOCKER_IMAGE_TAG = 'latest'
        DOCKERHUB_CREDENTIALS_ID = 'dockerhub-credentials'
        GITHUB_CREDENTIALS_ID = 'sep-2-jacoco-token'
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

        stage('publish jacoco report to github pages') {
            steps {
                withCredentials([
                    string(
                        credentialsId: GITHUB_CREDENTIALS_ID,
                        variable: 'GITHUB_TOKEN'
                    )
                ]) {
                    dir('jacoco-report') {
                        deleteDir()
                        sh '''
                            cp -R ../target/site/jacoco/. .
                            git init
                            git checkout -b gh-pages
                            git add .
                            git commit -m "docs: publish jacoco report"
                            git remote add origin "https://x-access-token:$GITHUB_TOKEN@github.com/x-bananer/software-engineering-project-2.git"
                            git push --force origin gh-pages
                        '''
                    }
                }
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
