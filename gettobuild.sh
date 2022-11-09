#!/usr/bin/env bash

pushd froncik
npm install
npm run export
popd

rm -rf backend/src/main/resources/static/*
cp -R ./froncik/out/* backend/src/main/resources/static/

pushd backend
./mvnw package
popd 

cp backend/target/*.jar app.jar
