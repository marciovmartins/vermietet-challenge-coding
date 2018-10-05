#!/usr/bin/env bash

timeout 30 bash -c "</dev/tcp/localhost/13000"; echo $?
timeout 30 bash -c "</dev/tcp/localhost/13001"; echo $?