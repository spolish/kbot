pipeline {
    agent any

    parameters {

        choice(name: 'OS', choices: ['linux', 'darwin', 'windows', 'all'], description: 'Pick OS')

    }

    stages {
        stage('CI') {
            steps {
                checkout scm

                sh 'make test'
                
                withDockerRegistry([credentialsId: 'docker-registry-credentials', url: 'https://ghcr.io']) {
                    sh 'make image push'
                }
            }
        }

        stage('CD') {
            dependencies {
                stage('CI')
            }
            steps {
                checkout scm

                sh 'echo "VERSION=$(git describe --tags --abbrev=0)-$(git rev-parse --short HEAD)" > version.env'
                sh 'echo "TARGETARCH=amd64" >> version.env'
                
                sh 'docker run --rm -v $PWD:/workdir mikefarah/yq yq -i \'.image.tag=env.VERSION | .image.arch=env.TARGETARCH\' helm/values.yaml'

                sh '''
                git config user.name 'jenkins'
                git config user.email 'jenkins@example.com'
                git add .
                git commit -m "Update version $VERSION"
                git push
                '''
            }
        }
    }
}


pipeline {
    agent any
    parameters {

        choice(name: 'OS', choices: ['linux', 'darwin', 'windows', 'all'], description: 'Pick OS')

    }
    stages {
        stage('Example') {
            steps {
                echo "Build for platform ${params.OS}"

                echo "Build for arch: ${params.ARCH}"

            }
        }
    }
}