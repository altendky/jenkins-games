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
                    parallel {
                        stage("B - 1") {
                            steps {
                                sh "python --version --version"
                                sh "sleep 10"
                            }
                        }
                        stage("B - 2") {
                            steps {
                                sh "python --version --version"
                                sh "sleep 10"
                            }
                        }
                    }
                }
            }
        }
        stage("Test") {
            matrix {
                agent {
                    docker {
                        image "python:3.9.0-buster"
                    }
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
                            sh 'echo $(date) -- we are here in ${FIRST_AXIS}${SECOND_AXIS} - $(pwd) | tee --append the_file'
                            sh 'sleep 10'
                        }
                    }
                    stage("Test That") {
                        steps {
                            sh 'cat the_file'
                            sh 'sleep 10'
                        }
                    }
                }
            }
        }
    }
}
