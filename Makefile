.PHONY: build

install:
	docker-compose build

run:
	docker-compose up -d app

stop:
	docker-compose stop

test: run
	docker-compose exec app gradle test

check: run
	docker-compose exec app gradle check

clean: run
	docker-compose exec app gradle clean

build: run
	docker-compose exec app gradle bootJar
	docker build -t vermietet-challenge-coding:1.0.0 .