#!/usr/bin/env sh

$(which java) -jar jacoco/jacococli.jar dump --port 13003 --destfile build/jacoco/output/jacoco.exec
$(which java) -jar jacoco/jacococli.jar report build/jacoco/output/jacoco.exec --classfiles build/jacoco/output/classdump --html build/jacoco/output/html --sourcefiles src/main/java