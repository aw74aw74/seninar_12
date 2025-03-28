# Логирование запросов с помощью Spring Integration

Проект демонстрирует реализацию использования Spring Integration для логирования запросов в файл.
Проект создан на основе семинара 4, где была реализована базовая структура Spring MVC приложения.

## Используемые технологии

- Java 17
- Spring Boot 3.4.3
- Spring MVC
- Spring Integration
- Thymeleaf
- Lombok
- Maven

## Начало работы

1. Предварительные требования:
   - Java 17 или выше
   - Maven

2. Клонирование репозитория:
   ```bash
   git clone https://github.com/aw74aw74/seninar_12
   cd seminar_12
   ```

3. Сборка проекта:
   ```bash
   mvn clean install
   ```

4. Запуск приложения:
   ```bash
   mvn spring-boot:run
   ```

## Структура проекта

```
src/
├── main/
│   ├── java/com/example/seminar_12/
│   │   ├── config/
│   │   │   └── IntegrationConfig.java     # Конфигурация Spring Integration
│   │   ├── controller/
│   │   │   └── HomeController.java        # Контроллер с логированием
│   │   ├── model/
│   │   │   └── User.java                  # Модель пользователя
│   │   ├── service/
│   │   │   └── RequestLoggingService.java # Сервис логирования
│   │   └── Seminar12App.java              # Главный класс приложения
│   │
│   └── resources/
│       ├── static/
│       │   └── css/                       # Стили CSS
│       ├── templates/                     # Шаблоны Thymeleaf
│       │   ├── index.html                 # Главная страница
│       │   ├── greeting.html              # Страница приветствия
│       │   ├── register.html              # Форма регистрации
│       │   └── result.html                # Результат регистрации
│       └── application.yml                # Конфигурация приложения
```

## API Endpoints

1. `GET /` - Главная страница
   ```
   Запрос домашней страницы с IP: 192.168.1.100 : 2025-03-28T02:35:37
   ```

2. `GET /greeting` - Страница приветствия
   ```
   Запрос приветствия с IP: 192.168.1.100 : 2025-03-28T02:35:38
   ```

3. `GET /register` - Форма регистрации
   ```
   Запрос регистрации с IP: 192.168.1.100 : 2025-03-28T02:35:39
   ```

4. `POST /register` - Обработка регистрации
   ```
   Новый юзер добавлен с IP: 192.168.1.100 : User{name='John'} : 2025-03-28T02:35:40
   ```

## Используемые паттерны

1. **Gateway** - `RequestLoggingService` как единая точка входа для логирования

2. **Chain of Responsibility** - последовательная обработка сообщений через каналы:
   ```
   requestChannel → transformer → fileChannel → fileWriter
   ```

3. **Observer** - реализован через механизм каналов Spring Integration

4. **Dependency Injection** - внедрение зависимостей через конструктор с помощью Lombok `@RequiredArgsConstructor`

5. **Facade** - `HomeController` скрывает сложность системы логирования за простым интерфейсом

## Процесс логирования

1. **Получение запроса**
   - Контроллер получает HTTP запрос
   - Извлекается IP адрес клиента
   - Формируется сообщение с деталями запроса

2. **Обработка сообщения**
   - Сообщение отправляется в requestChannel
   - Преобразуется в строку через transformer
   - Передается в fileChannel

3. **Сохранение**
   - FileWritingMessageHandler записывает сообщение в history.log
   - Каждое сообщение добавляется в конец файла
   - Автоматически добавляется перенос строки


## Проверка работы

1. Запустите приложение:
   ```bash
   mvn spring-boot:run
   ```

2. Откройте в браузере:
   ```
   http://localhost:8080
   ```

3. Проверьте файл `history.log` в корневой директории проекта
