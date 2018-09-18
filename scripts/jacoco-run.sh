#!/usr/bin/env sh

$(which java) -jar jacoco/jacococli.jar dump --destfile build/jacoco/output/jacoco.acceptanceTest.exec
$(which java) -jar jacoco/jacococli.jar merge build/jacoco/output/jacoco.test.exec build/jacoco/output/jacoco.integrationTest.exec build/jacoco/output/jacoco.acceptanceTest.exec --destfile build/jacoco/output/jacoco.exec
$(which java) -jar jacoco/jacococli.jar report build/jacoco/output/jacoco.exec --classfiles build/jacoco/output/classdump --html build/jacoco/output/html --sourcefiles src/main/java