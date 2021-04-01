reset_gradle:
	bash gradlew --stop
	lsof -nti:8005 | xargs kill -9 || true

clean: reset_gradle
	bash gradlew clean

build: clean
	bash gradlew build

run: reset_gradle
	bash gradlew bootRun
