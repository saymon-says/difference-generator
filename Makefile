.PHONY: build

install:
	gradlew clean install

run-dist:
	build\install\app\bin\app -h
check-updates:
	gradlew dependencyUpdates

lint:
	gradlew checkstyleMain

build:
	gradlew clean build

dist:
	gradlew clean install
	build\install\app\bin\app -f plain nested_json_1.json nested_json_2.json