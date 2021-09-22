pipeline {
    agent none
    options { skipDefaultCheckout(true) }
stages {

    stage('Docker build') {
        agent any
        steps {
            sh 'docker build -t peopoolbe:latest "/var/jenkins_home/workspace/j5d205/S05P21D205/Backend/Crawling_server"'
        }
    }
    stage('Docker run') {
        agent any
        steps{
						
						sh 'docker ps -f name=peopoolbe -q \
              | xargs --no-run-if-empty docker container stop'

						sh 'docker container ls -a -f name=peopoolbe -q \
              | xargs -r docker container rm'

						sh 'docker images -f "dangling=true" -q \
							| xargs -r docker rmi'
						
            sh 'docker run -d --name peopoolbe -p 8080:8080 peopoolbe:latest '
						
        }
    }
}
}
