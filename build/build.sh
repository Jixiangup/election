#!/usr/bin/env zsh
echo 'election api start build...'

mvn clean package -DskipTests -T 1C  -pl \
election-api