call mvn clean package
docker build -t schedapp/suai:latest -t schedapp/suai:`git rev-list HEAD | wc -l | tr -d ' '` .
mkdir ./target/suai
docker save -o ./target/suai/image.tar schedapp/suai:latest
cp ./docker-compose.yml ./target/suai/docker-compose.yml
docker-compose up
docker image prune -a