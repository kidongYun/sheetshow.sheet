cd ./app

git pull origin test

sudo ./gradlew build

sudo docker build -t sheetshow-sheet:1.0.2-SNAPSHOT --build-arg SPRING_PROFILES_ACTIVE=local .