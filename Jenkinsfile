// Definir el mapa de colores para la notificación de Slack
    def COLOR_MAP = [
        'SUCCESS': 'good',
        'FAILURE': 'danger'
    ]

pipeline {
    agent any 

    tools { 
        maven 'mavenjenkins'
        jdk 'jenkisjava'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/CarlosKamisato/ProjectDevops.git'
            }
        }

        stage('Build') {
            steps {
                script {
                    //sh 'mvn clean package'
                    echo 'Construyendo la aplicacion'                   
                }
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Test') {
            steps {
                script {
                    sh 'mvn test'
                }
            }
        }


    stage('Sonar Scanner') {
        steps {
            withSonarQubeEnv('SonarQube') { 
                sh 'mvn sonar:sonar -Dsonar.projectKey=GS -Dsonar.sources=src/main/java/com/kibernumacademy/devops -Dsonar.tests=src/test/java/com/kibernumacademy/devops -Dsonar.java.binaries=.'
            }
        }
    }

        stage('Quality Gate'){
            steps{
                timeout(time:1, unit:'HOURS'){
                    waitForQualityGate abortPipeline:true
                }
            }
        }

    }
    post {
        always {
            echo 'Slack Notification'
            slackSend channel: '#ci-cd-proyecto_final',
                color: COLOR_MAP[currentBuild.currentResult],
                message: "*${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More Info at: ${env.BUILD_URL}"
        }
    }
}