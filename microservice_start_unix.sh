#!/bin/bash
docker-compose -f Microservice/docker-compose-microservice.yml up
if [ $? -eq 0 ]
then
    echo 'stop all services...'
    docker-compose -f Microservice/docker-compose-microservice.yml stop
    docker-compose -f Microservice/docker-compose-microservice.yml rm
else
    echo 'delete all services...'
    docker-compose -f Microservice/docker-compose-microservice.yml rm --force
fi