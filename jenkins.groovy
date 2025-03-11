node {
    parameters {
        booleanParam 'isUserNeedToBeLogin'
    }
    stage('Execute shell script for running test') {
        steps {
            echo 'Executing shell script'
            sh "chmod u+x $WORKSPACE/script.sh;$WORKSPACE/script.sh ${params.isUserNeedToBeLogin}"
        }
    }
}