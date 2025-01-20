# Foro Hub

Foro Hub es una API RESTful desarrollada con Spring Boot para gestionar un foro de discusión. Permite a los usuarios autenticarse, crear, consultar, actualizar y eliminar tópicos. La seguridad se implementa usando JWT (JSON Web Token) para proteger las operaciones.

## Índice

- [Características](#características)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Uso](#uso)
- [Rutas](#rutas)
- [Autenticación JWT](#autenticación-jwt)
- [Enlace de demostración](#enlace-de-demostración)

## Características

- Autenticación y autorización usando JWT.
- CRUD de tópicos:
  - Crear nuevo tópico.
  - Mostrar todos los tópicos.
  - Mostrar un tópico específico.
  - Actualizar un tópico.
  - Eliminar un tópico.

## Requisitos

- Java 17 o superior.
- Spring Boot 3.0 o superior.
- Maven 3.6 o superior.
- Base de datos (H2, MySQL, o cualquier compatible con Spring Data JPA).

## Instalación

1. Clona este repositorio:
   ```bash
   git clone https://github.com/tu_usuario/foro-hub.git
   ```
2. Accede al directorio del proyecto:
   ```bash
   cd foro-hub
   ```
3. Configura las propiedades de la base de datos en `application.properties`.
4. Construye el proyecto:
   ```bash
   mvn clean install
   ```
5. Ejecuta la aplicación:
   ```bash
   mvn spring-boot:run
   ```

## Uso

Utiliza herramientas como [Insomnia](https://insomnia.rest/) o [Postman](https://www.postman.com/) para probar la API. La autenticación es requerida para la mayoría de las rutas.

### Crear un Tópico (requiere autenticación)

```
POST /topics
```

Cuerpo de la solicitud:

```json
{
  "titulo": "Pregunta sobre Spring Boot",
  "mensaje": "¿Cómo configurar un filtro de seguridad?",
  "autorId": 1
}
```

### Obtener Todos los Tópicos

```
GET /topics
```

### Obtener un Tópico por ID

```
GET /topics/{id}
```

### Actualizar un Tópico (requiere autenticación)

```
PUT /topics/{id}
```

Cuerpo de la solicitud:

```json
{
  "titulo": "Pregunta sobre Spring Boot (actualizada)",
  "mensaje": "¿Cómo configurar correctamente un filtro de seguridad?"
}
```

### Eliminar un Tópico (requiere autenticación)

```
DELETE /topics/{id}
```

## Autenticación JWT

Para obtener un token JWT:

1. Envía una solicitud a:
   ```
   POST /login
   ```
2. Cuerpo de la solicitud:
   ```json
   {
     "email": "usuario@example.com",
     "contrasena": "password123"
   }
   ```
3. Usa el token recibido en la cabecera `Authorization`:
   ```
   Authorization: Bearer <token>
   ```

## Enlace de demostración

[Ver la aplicación en funcionamiento](https://github.com/KarlaMel24/ForoHub/blob/main/Multimedia/Foro_Hub.mp4)
