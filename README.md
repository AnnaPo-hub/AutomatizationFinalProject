### Это еще  не законченный файл

Программа предназначена для автоматизированного тестирования сервиса по покупке тура. 

### Необходимое окружение: 
установленный Node.js, Docker; 
убедитесь, что перечисленные ниже в инструкции порты свободны; 

### Инструкции по установке 
1. Скачать архив;
1. Запустить контейнер, в котором разворачивается база данных `docker-compose up -d --force-recreate`, база работает на порту 3306;
1. Находясь в каталоге gate-simulator, запустить симулятор банковского сервиса командой `npm start`, симулятор запускается на порту 9999;  
1. Запустить приложение командой :
    * для использования Postgres: `java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/postgres -jar artifacts/aqa-shop.jar` 
    * для использования MySQL.`java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar`  
    
   Приложение запускается на порту 8080; 
  
1. Запускаем тесты командой: 
    при работе с postgres: `./gradlew test -Dproperty_name=jdbc:postgresql://localhost:5432/postgres -Dlogin=user -Dpassword=password -Dapp_url=y` 
    при работе с mySql: `./gradlew test -Dproperty_name=jdbc:mysql://localhost:3306/app -Dlogin=x -Dpassword=y -Dapp_url=y` 
    (по умолчанию app_url = http://localhost:8080, login = app, password= pass ); 
    

     

