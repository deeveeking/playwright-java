isLoginTest = "${isUserNeedToBeLogin}"
projectUrl = "https://github.com/deeveeking/playwright-java.git"

node {
        withEnv(["isLoginTest=$isLoginTest"]) {
            jdk 'JAVA_HOME'
            try {
                stage("Run Test") {
                    downloadProject("$projectUrl", "master")
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

def downloadProject(String url, String branch) {
    cleanWs()
    checkout scm: [
            $class: 'GitSCM', branches: [[name: branch]],
            userRemoteConfigs: [[
                    url: url
            ]]
    ]
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