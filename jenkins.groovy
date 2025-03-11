isLoginTest = "${isUserNeedToBeLogin}"

node {
    withEnv(["isLoginTest=$isLoginTest"]) {
        try {
            stage("Run Test") {
                echo "Var value = $isLoginTest"
                if (Boolean.parseBoolean("$isLoginTest")) {
                    echo "Run default test"
                    labelledShell(label: "Run default test", script: "chmod +x gradlew \n./gradlew clean loginTest -DisUserNeedToBeLogin=${isLoginTest}")
                } else {
                    echo "Run Login test"
                    labelledShell(label: "Run Login test", script: "chmod +x gradlew \n./gradlew clean test -DisUserNeedToBeLogin=${isLoginTest}")
                }
            }
        } finally {
            generateAllure()
            echo "Some fails..."
        }
    }
}

def generateAllure() {
    allure([
            includeProperties: true,
            jdk: '',
            properties: [],
            reportBuildPolicy: 'ALWAYS',
            results: [[path: 'build/allure-results']]
    ])
}