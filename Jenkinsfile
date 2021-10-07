pipeline {
    agent none
    options { skipDefaultCheckout(false) }
stages {

    stage('Docker build') {
        agent any
        steps {
            sh 'docker build -t frontend:latest "/var/jenkins_home/workspace/Deploy/frontend"'
        }
    }
    stage('Docker run') {
        agent any
        steps{
						sh 'docker ps -f name=frontend -q \
							| xargs --no-run-if-empty docker container stop'

						sh 'docker container ls -a -f name=frontend -q \
							| xargs -r docker container rm'

						sh 'docker images -f "dangling=true" -q \
							| xargs -r docker rmi'

						sh 'docker run -d --name frontend -p 80:80 -p 443:443 -v /home/ubuntu/httpsKey/:/var/empty/ --network usedtrade frontend:latest'

        }
    }
}
}
