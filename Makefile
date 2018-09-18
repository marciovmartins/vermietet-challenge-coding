install:
	docker-compose build

run:
	docker-compose up -d app

stop:
	docker-compose stop

test: run
	docker-compose exec app ./gradlew test

check: run
	docker-compose exec app ./gradlew check

clean: run
	docker-compose exec app ./gradlew clean