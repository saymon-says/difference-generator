.PHONY: build

install:
	gradlew clean install

run-dist:
	build\install\app\bin\app
check-updates:
	gradlew dependencyUpdates

lint:
	gradlew checkstyleMain

build:
	gradlew clean build

dist:
	gradlew clean install
	build\install\app\bin\app -f plain file1.json file2.json