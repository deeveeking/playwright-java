chmod +x gradlew
echo "Var value = ${isUserNeedToBeLogin}"
if [ $isUserNeedToBeLogin = true ]
then
	./gradlew clean test -DisUserNeedToBeLogin=${isUserNeedToBeLogin}
else
	echo "Else block"
	./gradlew clean loginTest -DisUserNeedToBeLogin=${isUserNeedToBeLogin}
fi
