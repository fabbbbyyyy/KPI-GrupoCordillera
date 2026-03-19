# KPI-GrupoCordillera

API REST para gestion de indicadores KPI construida con Spring Boot, JPA y MySQL, con documentacion OpenAPI/Swagger y soporte de ejecucion en Docker.

## Checklist de uso rapido

- [ ] Revisar requisitos
- [ ] Configurar variables de entorno (si aplica)
- [ ] Levantar la API en local o con Docker Compose
- [ ] Abrir Swagger UI para validar endpoints

## Stack tecnico

- Java 17
- Spring Boot 4.0.3
- Maven Wrapper (`mvnw`, `mvnw.cmd`)
- Spring Data JPA
- MySQL 8 (principal)
- H2 (dependencia runtime disponible)
- springdoc OpenAPI 2.6.0
- Docker + Docker Compose

## Estructura del proyecto

```text
KPI-GrupoCordillera/
  src/main/java/cl/cordillera/kpi/
    config/            # Configuracion OpenAPI
    controller/        # Endpoints REST
    model/             # Entidades
    repository/        # Repositorios JPA
    service/           # Logica de negocio
  src/main/resources/
    application.properties
    application-docker.properties
  docs/
    openapi.md
  Dockerfile
  docker-compose.yml
```

## Configuracion

La aplicacion usa estas variables de entorno (con defaults):

- `SPRING_DATASOURCE_URL` (default: `jdbc:mysql://localhost:3308/kpigrupocordillera?useSSL=false`)
- `SPRING_DATASOURCE_USERNAME` (default: `root`)
- `SPRING_DATASOURCE_PASSWORD` (default: vacio)
- `SERVER_PORT` (default: `5000`)

Cuando se usa perfil Docker (`SPRING_PROFILES_ACTIVE=docker`), la URL de BD se resuelve con `mysql:3306` desde `application-docker.properties`.

## Ejecucion local (sin Docker)

1. Asegura que MySQL este disponible en `localhost:3308`.
2. Ejecuta la app con Maven Wrapper.

```powershell
.\mvnw.cmd spring-boot:run
```

## OpenAPI / Swagger

Con la app corriendo en puerto `5000`:

- Swagger UI: `http://localhost:5000/swagger-ui.html`
- OpenAPI JSON: `http://localhost:5000/v3/api-docs`

Documentacion extendida: `docs/openapi.md`.

## Docker

### Opcion A: solo contenedor de la API

Requiere una base de datos MySQL externa o local accesible desde el contenedor.

```powershell
docker build -t kpi-app .
docker run --rm -p 5000:5000 -e SPRING_DATASOURCE_URL="jdbc:mysql://host.docker.internal:3308/kpigrupocordillera?useSSL=false" -e SPRING_DATASOURCE_USERNAME="root" -e SPRING_DATASOURCE_PASSWORD="" kpi-app
```

### Opcion B: API + MySQL con Compose (recomendada)

```powershell
docker compose up --build
```

Servicios definidos en `docker-compose.yml`:

- API: `http://localhost:5000`
- MySQL host local: `localhost:3308`
- MySQL dentro de red Docker: `mysql:3306`

## Troubleshooting rapido

- Puerto `5000` ocupado: cambia `SERVER_PORT` o el mapeo en `docker-compose.yml`.
- MySQL no inicia en Compose: revisa estado del contenedor `mysql` y su `healthcheck`.
- Error de conexion a BD: valida `SPRING_DATASOURCE_URL`, usuario y password.
- Swagger no carga: confirma que la app esta arriba y que el puerto publicado es correcto.

## Notas

- No se incluyen credenciales de produccion.
- Para despliegues, siempre inyectar variables de entorno desde el entorno destino.

