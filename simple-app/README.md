# Build Docker image using JIB

Simple app has [Google's jib](https://cloud.google.com/blog/products/gcp/introducing-jib-build-java-docker-images-better) dependency which enables us to build Docker images by just simple command:

`mvn clean compile package jib:dockerBuild`

Or you can build using regular Docker build command

`docker build -t gtech/simple-app .`