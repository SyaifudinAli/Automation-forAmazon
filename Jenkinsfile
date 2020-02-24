node {
   
    stage('initialize'){
        def dockerHome = tool 'myDocker'
        def mavenHome  = tool 'myMaven'
        env.PATH = "${dockerHome}/bin:${mavenHome}/bin:${env.PATH}"
    }
   
    stage('checkout') {
        checkout scm
    }
   
    stage('check java') {
        sh "java -version"
    }
    
    stage('check maven') {
        sh "mvn -version"
    }

    stage('build') {
        withEnv(['DISPLAY=:0']) {
            sh "rm -rf  /tmp/.X0-lock"
            sh "/usr/bin/Xvfb :0 -screen 0 1024x768x24 </dev/null &"
            sh 'export DISPLAY=":0"'
            sh "chmod +x /var/lib/jenkins/workspace/drivers/geckodriver"          
            sh "mvn clean test"           
        }        
    }
   
}