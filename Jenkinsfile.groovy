pipeline {
    agent none
    stages {
        stage("Build") {
            agent {
                docker {
                    image "python:3.9.0-buster"
                }
            }
            stages {
                stage("Build A") {
                    steps {
                        sh "python --version --version"
                    }
                }
                stage("Build B") {
                    steps {
                        sh "python --version --version"
                    }
                }
            }
        }
        stage("Test") {
            matrix {
                agent {
                    label 'test'
                }
                axes {
                    axis {
                        name "FIRST_AXIS"
                        values "A", "B"
                    }
                    axis {
                        name "SECOND_AXIS"
                        values "1", "2"
                    }
                }
                stages {
                    stage("Test This") {
                        steps {
                            sh 'echo we are here in ${FIRST_AXIS}${SECOND_AXIS}'
                            sh 'sleep 10'
                        }
                    }
                    stage("Test That") {
                        steps {
                            sh 'echo we are here in ${FIRST_AXIS}${SECOND_AXIS}'
                            sh 'sleep 10'
                        }
                    }
                }
            }
        }
    }
}