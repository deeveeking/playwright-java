node {
    parameters {
        booleanParam 'isUserNeedToBeLogin'
    }
    stage('Execute shell script for running test') {
        echo 'Executing shell script'
        sh "chmod u+x /script.sh;/script.sh ${params.isUserNeedToBeLogin}"
    }
}