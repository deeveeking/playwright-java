isUserNeedToBeLogin = "${isUserNeedToBeLogin}"

node {
    try {
        stage("Run Test") {
            echo "Var value = ${isUserNeedToBeLogin}"
            if (isUserNeedToBeLogin) {
                labelledShell(label: "Run default test", script: "chmod +x gradlew \n./gradlew clean loginTest -DisUserNeedToBeLogin=${isUserNeedToBeLogin}")
            } else {
                labelledShell(label: "Run Login test", script: "chmod +x gradlew \n./gradlew clean test -DisUserNeedToBeLogin=${isUserNeedToBeLogin}")
            }
        }
    } finally {
        generateAllure()
        echo "Some fails..."
    }
}

def generateAllure() {
    allure([
            includeProperties: true,
            jdk: '',
            properties: [],
            reportBuildPolice: 'ALWAYS',
            results: [[path: 'build/allure-results']]
    ])
}