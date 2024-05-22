# OnlineMarket

Интернет магазин электроники.
Технлогии которые использовались при разработке -
<ul>
  <li>Java 17</li>
  <li>Spring boot (Web, JPA, Security)</li>
  <li>JWT</li>
  <li>PostgreSQL</li>
  <li>Docker</li>
  <li>Git</li>
  <li>HTML</li>
  <li>CSS</li>
  <li>Vue.js</li>
</ul>

<h2>
  Диаграмма базы данных
</h2>

<img src="./assets/DbDiagrams.jpg">

# Сборка проекта

### Docker

```
docker-compose --env-file .config/.env build
```

### Стандартная

```
mvn clean package
```

# Docker

### Dockerfile для автоматической сборки проекта

```
FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean package

FROM amazoncorretto:17
ARG JAR_FILE=*.jar
COPY /target/${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
```

### compose.yaml

```
version: '3.5'

services:
  postgres:
    container_name: postgres_container
    image: postgres:16-alpine
    environment:
      POSTGRES_USER: ${POSTGRES_USER}
      POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
      PGDATA: /data/postgres
    volumes:
      - postgres:/data/postgres
    ports:
      - "5432:5432"
    networks:
      - postgres
    restart: unless-stopped

  pgadmin:
    container_name: pgadmin_container
    image: dpage/pgadmin4
    environment:
      PGADMIN_DEFAULT_EMAIL: ${PGADMIN_DEFAULT_EMAIL}
      PGADMIN_DEFAULT_PASSWORD: ${PGADMIN_DEFAULT_PASSWORD}
      PGADMIN_CONFIG_SERVER_MODE: 'False'
    volumes:
      - pgadmin:/var/lib/pgadmin
    ports:
      - "${PGADMIN_PORT:-5050}:80"
    networks:
      - postgres
    restart: unless-stopped

  spring:
    container_name: glances_back
    environment:
      DB_CONNECT: ${DB_CONNECT}
      POSTGRES_USER: ${POSTGRES_USER}
      POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
      JWT_ACCESS_KEY: ${JWT_ACCESS_KEY}
      JWT_REFRESH_KEY: ${JWT_REFRESH_KEY}
      PASSWORD_SALT: ${PASSWORD_SALT}
    image: spring-boot
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - "9000:9000"
    networks:
      - postgres
    depends_on:
      - postgres

  vue:
    container_name: vue_front
    image: vue-js
    build:
      context: .
      dockerfile: FrontEnd/Docker/Dockerfile
    ports:
      - "8080:8080"
    networks:
      - spring
    depends_on:
      - spring

networks:
  spring:
    driver: bridge
  postgres:
    driver: bridge

volumes:
  postgres:
  pgadmin:
  spring:
  vue:
```

# Структура проекта

```
├───entity       // Модели сущностей для базы данных, репозитории
│   ├───exception // Глобальный исключения
│   ├───image
│   │   ├───exception   // Локальные исключения для сущности
│   │   ├───gateway     // Репозитории
│   │   └───model       // Модель сущности
│   ├───product         // Область продукта
│   │   └───payload
│   │       ├───brand
│   │       │   ├───exception
│   │       │   ├───gateway
│   │       │   └───model
│   │       ├───categories
│   │       │   ├───exception
│   │       │   ├───gateway
│   │       │   └───model
│   │       ├───characteristics
│   │       │   ├───exception
│   │       │   ├───gateway
│   │       │   └───model
│   │       ├───product
│   │       │   ├───exception
│   │       │   ├───gateway
│   │       │   │   └───repo
│   │       │   └───model
│   │       ├───promotion
│   │       │   ├───exception
│   │       │   ├───gateway
│   │       │   └───model
│   │       └───rating
│   │           ├───exception
│   │           ├───gateway
│   │           └───model
│   └───user            // Область пользователя
│       └───payload
│           ├───basket
│           │   ├───exception
│           │   ├───gateway
│           │   └───model
│           ├───jwt
│           │   ├───gateway
│           │   └───model
│           ├───order
│           │   ├───exception
│           │   ├───gateway
│           │   │   └───mapper
│           │   └───model
│           ├───payment
│           │   ├───exception
│           │   ├───gateway
│           │   └───model
│           └───user
│               ├───exception
│               ├───gateway
│               │   ├───mapper
│               │   └───repository
│               └───model
├───infastructure       // Сервисы, контролеры, DTO
│   ├───config
│   │   ├───exception
│   │   └───web
│   │       └───documentation
│   ├───image
│   │   ├───controller
│   │   ├───dto
│   │   └───gateway
│   ├───product
│   │   └───payload
│   │       ├───brand
│   │       │   ├───controller
│   │       │   ├───dto
│   │       │   └───gateway
│   │       ├───categories
│   │       │   ├───controller
│   │       │   ├───dto
│   │       │   └───gateway
│   │       ├───characteristics
│   │       │   ├───controller
│   │       │   ├───dto
│   │       │   └───gateway
│   │       ├───product
│   │       │   ├───controller
│   │       │   ├───dto
│   │       │   └───gateway
│   │       │       └───filter
│   │       ├───promotion
│   │       │   ├───controller
│   │       │   ├───dto
│   │       │   └───gateway
│   │       └───rating
│   │           ├───controller
│   │           ├───dto
│   │           └───gateway
│   └───user
│       └───payload
│           ├───auth
│           │   ├───controller
│           │   ├───dto
│           │   └───gateway
│           ├───basket
│           │   ├───controller
│           │   ├───dto
│           │   └───gateway
│           ├───jwt
│           │   ├───dto
│           │   └───gateway
│           ├───order
│           │   ├───controller
│           │   ├───dto
│           │   └───gateway
│           ├───payment
│           │   ├───controller
│           │   ├───dto
│           │   └───gateway
│           └───user
│               ├───controller
│               ├───dto
│               └───gateway
└───usecase             // Реализация сервисов, логика.
    ├───image
    ├───product
    │   └───payload
    │       ├───brand
    │       ├───categories
    │       ├───characteristics
    │       ├───product
    │       │   ├───child
    │       │   └───filter
    │       ├───promotion
    │       └───rating
    └───user
        └───payload
            ├───auth
            ├───basket
            ├───jwt
            ├───order
            ├───payment
            └───user
                └───mapper
```
