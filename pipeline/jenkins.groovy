pipeline {
    agent any
    environment {
        REPO = 'https://github.com/spolish/kbot'
        BRANCH = 'main'
        GITHUB_TOKEN=credentials('7e1207e4-3e32-4b41-a3e0-207dd12365c9')
    }
    parameters {
        choice(name: 'OS', choices: ['linux', 'darwin', 'windows', 'all'], description: 'Pick OS')
        choice(name: 'ARCH', choices: ['amd64', 'arm64', 'all'], description: 'Pick ARCH')
    }
    stages{
        
        stage ("clone") {
            steps {
            echo 'CLONE REPOSITORY'
              git branch: "${BRANCH}", url: "${REPO}" 
            }
        }
        
        stage ("test") {
            steps {
                echo 'TEST EXECUTION STARTED'
                sh 'make test'
            }
        }
    
        stage ("build") {
            steps {
                echo 'BUILD EXECUTION STARTED'
                sh 'make build'
            }
        }
        
        
        stage ("image") {
            steps {
                script {
                    echo 'IMAGE EXECUTION STARTED'
                    sh 'make image'
                }
            }
        }
        
        stage ("push") {
            steps {
                script {
                    sh 'echo $GITHUB_TOKEN_PSW | docker login ghcr.io -u $GITHUB_TOKEN_USR --password-stdin'
                    sh 'make push'
                }
            }
        }
    }
}