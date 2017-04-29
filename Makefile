start:
	@./gradlew clean build && java -jar build/libs/BINGO-1.0-SNAPSHOT.jar

tests:
	@./gradlew clean build test
