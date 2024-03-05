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

<img src="https://github.com/faketri/OnlineMarket/blob/master/assets/DbDiagrams.svg">

# Структура проекта 

```
+---entity  // Описание сущностей, репозитории.
¦   +---exception
¦   +---image
¦   ¦   +---exception
¦   ¦   +---gateway
¦   ¦   L---model
¦   +---order
¦   ¦   +---exception
¦   ¦   +---gateway
¦   ¦   L---model
¦   +---product
¦   ¦   +---exception
¦   ¦   +---gateway
¦   ¦   ¦   L---repo
¦   ¦   ¦       L---child
¦   ¦   L---model
¦   ¦       L---child
¦   +---promotion
¦   ¦   +---exception
¦   ¦   +---gateway
¦   ¦   L---model
¦   +---rating
¦   ¦   +---exception
¦   ¦   +---gateway
¦   ¦   L---model
¦   L---user
¦       +---exception
¦       +---gateway
¦       L---model
+---infastructure   // Контроллеры, dto, сервисы
¦   +---brand
¦   ¦   +---controller
¦   ¦   +---dto
¦   ¦   L---gateway
¦   +---categories
¦   ¦   +---controller
¦   ¦   +---dto
¦   ¦   L---gateway
¦   +---characteristics
¦   ¦   +---controller
¦   ¦   +---dto
¦   ¦   L---gateway
¦   +---config
¦   ¦   +---exception
¦   ¦   L---web
¦   ¦       +---authentication
¦   ¦       ¦   +---controller
¦   ¦       ¦   +---dto
¦   ¦       ¦   L---gateway
¦   ¦       L---documentation
¦   +---image
¦   ¦   +---controller
¦   ¦   +---dto
¦   ¦   L---gateway
¦   +---order
¦   ¦   +---controller
¦   ¦   +---dto
¦   ¦   L---gateway
¦   +---product
¦   ¦   +---controller
¦   ¦   +---dto
¦   ¦   L---gateway
¦   ¦       L---filter
¦   +---promotion
¦   ¦   +---controller
¦   ¦   +---dto
¦   ¦   L---gateway
¦   +---rating
¦   ¦   +---controller
¦   ¦   +---dto
¦   ¦   L---gateway
¦   L---user
¦       +---controller
¦       +---dto
¦       L---gateway
L---usecase   // Реализация сервисов
    +---auth
    +---brand
    +---categories
    +---characteristics
    +---image
    +---product
    ¦   +---child
    ¦   L---filter
    +---promotion
    +---rating
    L---user
```
