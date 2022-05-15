cd ~/app

git pull origin test

sudo ./gradlew build

sudo docker build -t sheetshow-sheet --build-arg SPRING_PROFILES_ACTIVE=local .

sudo docker-compose up
