# Liverpool Orders API

API REST desarrollada con Spring Boot para la gestión de clientes, entregas y pedidos.

## Tecnologías

- Java 17 - Amazon Corretto 17
- Spring Boot 3.2.4
- MongoDB
- Maven
- Docker
- Swagger / OpenAPI

## Ejecución local

### 1. Levantar MongoDB

```code
docker compose up -d
```
### 2. Ejecutar la aplicación
```code
mvn spring-boot:run
```
La aplicación estará disponible en:

```code
http://localhost:8080
```
Swagger
```code
http://localhost:8080/swagger-ui/index.html
```
#### Variables de entorno
Para utilizar MongoDB Atlas:
```code
MONGODB_URI
```
La aplicación utiliza MongoDB local por defecto si no se configura esta variable.
## Despliegue
La aplicación se encuentra desplegada en Google Cloud Run.
```code
https://liverpool-orders-api-1004521375766.us-central1.run.app
```