.PHONY: build

install:
	gradlew clean install

run-dist:
	#/Users/serdi/Hexlet/java/java-project-lvl1/build/install/app/bin/app
	build\install\app\bin\app
check-updates:
	gradlew dependencyUpdates

lint:
	gradlew checkstyleMain

build:
	gradlew clean build

dist:
	gradlew clean install
	build\install\app\bin\app src/main/java/resource/filepath1.json src/main/java/resource/filepath2.json