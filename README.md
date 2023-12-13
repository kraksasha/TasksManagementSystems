Для локального запуска необходимо:
1.Склонировать проект на свою IDEA
2.Создать новое подключение MySQL connection c портом 3306 и username, password указанные в файле apllication.properties в корне проекта с помощью MySQLWorkbench
3.Запустить скрипт Taskssystemdb.sql который находится в корне проекта
4.Запустить проект путем запуска главного класса либо с терминала в IDEA с использование docker.
5.Команды для запуска с использованием docker-compose
    - docker-compose build - сборка image
    - docker-compose up - создание и запуск контейнеров
    - docker-compose down - оставновка и удаление раннее созданный контейнеров
