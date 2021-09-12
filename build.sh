#!/bin/bash
mvn clean package

moduleName="suai"

exportDirectory="target/docker/$moduleName"
buildBranch=$(git rev-parse --abbrev-ref HEAD)
buildNumber=$(git rev-list HEAD | wc -l | tr -d ' ')
buildVersion="$buildBranch.$buildNumber"

echo Y | docker image prune -a
docker build -t schedapp/suai:latest -t schedapp/suai:$buildVersion .
mkdir -p ./$exportDirectory
echo Y | docker image prune
docker save -o ./$exportDirectory/image.tar schedapp/suai
cp ./docker-compose.yml ./$exportDirectory/docker-compose.yml