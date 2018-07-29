install:
	docker-compose build

up:
	docker-compose up -d
	JDBC_URL=jdbc:mysql://localhost:13000/vermietet JDBC_USERNAME=root JDBC_PASSWORD= ./gradlew bootRun

test:
	./gradlew test