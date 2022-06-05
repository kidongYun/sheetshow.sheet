cd ~/sheetshow.sheet/app

chmod +x ./deploy.sh

git pull origin test

sudo ./gradlew build

sudo docker build -t sheetshow-sheet --build-arg SPRING_PROFILES_ACTIVE=local .

sudo docker-compose -d up
