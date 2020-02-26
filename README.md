# REST-сервис для управления опросами

Настройки доступа к базе данных:

/src/main/resources/application.properties 

spring.datasource.url=jdbc:postgresql://localhost:5432/poll
spring.datasource.username=polluser
spring.datasource.password=pollPassword

Формат даты - timestamp

## Список запросов:
### - Добавление нового опроса 
####  POST /polls
  {
    "name": "Новый опрос",
    "startDate": 533456,
    "endDate": 1033456,
    "status": 1
  }
### - Удаление опроса
####  DEL /polls/{pollId}
  
  - pollId - id удаляемого опроса
  
### - Редактирование опроса
  
####  PUT /polls/{pollId}
  
  - pollId - id опроса
  
  {
    "name": "Новое наименование запроса",
    "startDate": 424234,
    "endDate": 634234,
    "status": 2
  }
  
### - Получить список опросов
  
####  GET /polls?orderBy={orderBy}&direction={direction}&name={name}&status={status}&fromStartDate={fromStartDate}&toStartDate={toStartDate}&fromEndDate={fromEndDate}&toEndDate={toEndDate}
  
  Обязательные поля:
  - orderBy - сортировка по полю startDate/name
  - direction - ASC/DESC порядок сортировки
  
  Опциональные поля для фильтрации:
  - name - наименование опроса
  - status - статус опроса
  - fromStartDate - диапазон даты начала опроса начиная с даты ...
  - toStartDate - диапазон даты начала опроса заканчивая датой ...
  - fromEndDate - диапазон даты окончания опроса начиная с даты ...
  - toEndDate - диапазон даты окончания опроса заканчивая датой ...
  
###  - Добавить новый вопрос опроса:
####  POST /questions
  
  {
    "pollId": 1,
    "text": "текст вопроса"
    "order": 2
  }
  
  - pollId - id опроса
  - text - текст вопроса
  - order - порядок отображения
