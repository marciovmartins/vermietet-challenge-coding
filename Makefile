install:
	docker-compose build

up:
	docker-compose up -d
	JDBC_URL=jdbc:mysql://localhost:13000/vermietet JDBC_USERNAME=postgres JDBC_PASSWORD=mysecretpassword ./gradlew bootRun

test:
	./gradlew test

check: prepare-check
	./gradlew clean check

prepare-check:
	docker-compose up -d app_test
	./gradlew flywayMigrate -i