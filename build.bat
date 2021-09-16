call mvn clean package
docker build --tag guap .
docker-compose up
docker image prune -a