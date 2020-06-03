### AutomatizationFinalProject

Программа предназначена для автоматизированного тестирования сервиса по покупке тура с помощью дебетовой карты и с получением кредита
по данным дебетовой карты. 

### Необходимое окружение: 
установленный Node.js, Docker; 
убедитесь, что  порты  8080, 9999 и 5432 или 3306 (в зависимости от выбранной базы данных) свободны; 

### Инструкции по установке 
1. Скачать архив;
1. Запустить контейнер, в котором разворачивается база данных (далее БД) `docker-compose up -d --force-recreate`
1. Убедитесь в том, что БД готова к работе (логи смотреть через `docker-compose logs -f <applicationName>` (mysql или postgres)
1. Находясь в каталоге gate-simulator, запустите симулятор банковского сервиса командой `npm start`, симулятор запускается на порту 9999;  
1. Перед запуском приложения, раскомментируйте нужные логин и пароль в файле application.properties (в зависимости от нужной БД)
1. Запустите приложение командой :
    * для использования Postgres: `java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/postgres -jar artifacts/aqa-shop.jar` 
    * для использования MySQL: `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar` 
      
   Приложение запускается на порту 8080; 
  
1. Запустите тесты командой: 
    * при работе с postgres: `./gradlew test -Dproperty_name=jdbc:postgresql://localhost:5432/postgres -Dlogin=user -Dpassword=password -Dapp_url=y` 
    * при работе с mySql: `./gradlew test -Dproperty_name=jdbc:mysql://localhost:3306/app -Dlogin=app -Dpassword=pass -Dapp_url=y` 
    (по умолчанию app_url = http://localhost:8080); 
    

     

