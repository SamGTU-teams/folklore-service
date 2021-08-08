# Folklore project

## Requirements

- JDK 1.8
- Maven 3
- PostgreSQL 10+

## Run application

`mvn spring-boot:run`

## Run tests

`mvn test`

## Swagger UI

- [Heroku Folklore service API][1]
- [Local Folklore service API][2]

## JVM options

### Profiles

'-Dspring.profiles.active' - set active profile

Example:
`-Dspring.profiles.active=dev`

- default
- 'dev'

[1]: https://rassafel-folklore-service.herokuapp.com/swagger-ui.html
[2]: http://localhost:8080/swagger-ui.html