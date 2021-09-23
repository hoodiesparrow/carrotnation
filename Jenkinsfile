pipeline {
    agent none
    options { skipDefaultCheckout(false)) }
stages {

    stage('Docker build') {
        agent any
        steps {
            sh 'docker build -t crawlingbe:latest "/var/jenkins_home/workspace/j5d205/S05P21D205/Backend/Crawling_server"'
        }
    }
    stage('Docker run') {
        agent any
        steps{
						
						sh 'docker ps -f name=crawlingbe -q \
              | xargs --no-run-if-empty docker container stop'

						sh 'docker container ls -a -f name=crawlingbe -q \
              | xargs -r docker container rm'

						sh 'docker images -f "dangling=true" -q \
							| xargs -r docker rmi'
						
            sh 'docker run -d --name crawlingbe -p 8080:8080 crawlingbe:latest '
						
        }
    }
}
}
