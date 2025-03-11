isLoginTest = "${isUserNeedToBeLogin}"

node {
    withEnv(["isLoginTest=$isLoginTest"]) {
        try {
            stage("Run Test") {
                echo "Var value = $isLoginTest"
                if ("$isLoginTest") {
                    labelledShell(label: "Run default test", script: "chmod +x gradlew \n./gradlew clean loginTest -DisUserNeedToBeLogin=${isLoginTest}")
                } else {
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
            reportBuildPolice: 'ALWAYS',
            results: [[path: 'build/allure-results']]
    ])
}