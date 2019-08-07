pipeline {
    agent {label 'slave-maven'}

    stages {
        // stage('Config') {
        //     steps {
        //     }
        // }

        stage('Revision') {
            steps {
                sh "echo \"Build # ${env.BUILD_NUMBER}\" > revision.txt"
            }
        }

        stage('Build'){
            steps {
               sh 'mvn -B -DskipTests clean package'
            }
        }

        stage('Deploy') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'master') {
                        withCredentials([
                            string(credentialsId: 'aws-access-key', variable: 'awsAccessKey'),
                            string(credentialsId: 'aws-secret-key', variable: 'awsSecretKey')
                        ]) {
                                step([
                                    $class: 'AWSCodeDeployPublisher',
                                    applicationName: 'Testing-Code-Deploy',
                                    awsAccessKey: "$awsAccessKey",
                                    awsSecretKey: "$awsSecretKey",
                                    credentials: 'awsAccessKey',
                                    deploymentConfig: 'CodeDeployDefault.AllAtOnce',
                                    deploymentGroupAppspec: false,
                                    deploymentGroupName: 'Deployment-Group-Testing',
                                    deploymentMethod: 'deploy',
                                    excludes: '',
                                    iamRoleArn: '',
                                    includes: "democodedeploy.jar,revision.txt, appspec.yml, codedeploy/**",
                                    proxyHost: '',
                                    proxyPort: 0,
                                    region: 'ap-southeast-1',
                                    s3bucket: 'travala-code-test',
                                    s3prefix: '',
                                    subdirectory: '',
                                    versionFileName: '',
                                    waitForCompletion: true
                                ])
                            }
                    } else {
                        sshPublisher(
                            failOnError: true,
                            publishers: [
                                sshPublisherDesc(
                                    configName: 'general_service_test',
                                    transfers: [
                                        sshTransfer(
                                            execCommand:"/home/ubuntu/travala/travala-main-service/deployment/deploy_${env.BRANCH_NAME}.sh"
                                        )
                                    ]
                                )
                            ]
                        )
                    }
                }

            }
        }
    }

    post {
        always {
            emailext subject: "[TRAVALA] Main Service - Build # ${env.BUILD_NUMBER} - ${currentBuild.currentResult}", body: "Build # ${env.BUILD_NUMBER} - ${currentBuild.currentResult}: Check console output at ${env.BUILD_URL} to view the results.", to: 'hoapv@gemvietnam.com'
        }
    }
}
