# Sistema de Gestión de Biblioteca con Spring Security JWT

[![Java](https://img.shields.io/badge/Java-17%2B-blue)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring_Security-6.x-brightgreen)](https://spring.io/projects/spring-security)
[![JWT](https://img.shields.io/badge/JWT-Authentication-orange)](https://jwt.io/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0%2B-orange)](https://www.mysql.com/)

Una API REST segura construida con Spring Boot 3 y Spring Security que implementa un sistema completo de gestión de biblioteca con autenticación JWT, préstamos de libros y gestión de usuarios.

## Características principales

- **Autenticación JWT**: Sistema seguro de autenticación con tokens JWT
- **Operaciones CRUD completas**: Para libros, usuarios y préstamos
- **Sistema de préstamos**: Gestión completa de préstamos de libros con fechas de devolución
- **Búsquedas avanzadas**: Filtros por título, género, ISBN, usuario y disponibilidad
- **Validación de datos**: Uso de anotaciones para validar entradas
- **Paginación y ordenamiento**: En todos los endpoints de listado
- **Manejo de excepciones**: Control centralizado de errores con mensajes descriptivos
- **Seguridad robusta**: Contraseñas encriptadas con BCrypt y protección de endpoints

## Tecnologías utilizadas

- **Spring Boot 3**: Framework para aplicaciones Java
- **Spring Security**: Autenticación y autorización
- **Spring Data JPA**: Persistencia de datos
- **JWT**: Tokens para autenticación stateless
- **MySQL Database**: Base de datos relacional MySQL
- **Lombok**: Reducción de código boilerplate
- **Maven**: Gestión de dependencias
- **Validation API**: Validación de datos de entrada

## Endpoints de la API

### Autenticación

#### POST `/register/user`
Registra un nuevo usuario en el sistema.
```
{
"username": "usuario",
"password": "contraseña",
"email": "usuario@email.com",
"firstName": "Nombre",
"lastName": "Apellido"
}
```
- Responses:
  - `201 Created`: Usuario creado exitosamente
  - `400 Bad Request`: Error de validación o usuario ya existe

#### POST `/authenticate`
Autentica a un usuario y genera un token JWT.
```
{
"username": "usuario123",
"password": "contraseña"
}
```
- Responses:
  - `200 OK`: Devuelve el token JWT
  - `401 Unauthorized`: Credenciales inválidas

### Libros (Books)

#### GET `/api/book`
Devuelve una lista paginada de todos los libros.
Parámetros: `pageNum`, `pageSize`, `sort`
- Responses: `200 OK`

#### GET `/api/book/available`
Devuelve libros disponibles con paginación.
Parámetros: `pageNum`, `pageSize`, `sort`
- Responses: `200 OK`

#### GET `/api/book/title/{title}`
Busca libros por título con paginación.
Parámetros: `pageNum`, `pageSize`, `sort`
- Responses: `200 OK`

#### GET `/api/book/genre/{genre}`
Busca libros por género con paginación.
Parámetros: `pageNum`, `pageSize`, `sort`
- Responses: `200 OK`

#### GET `/api/book/isbn/{isbn}`
Busca un libro por ISBN.
- Responses:
  - `200 OK`: Libro encontrado
  - `404 Not Found`: ISBN no encontrado

#### GET `/api/book/count-available`
Cuenta los libros disponibles.
- Responses: `200 OK`

#### POST `/api/book`
Crea un nuevo libro.
```
{
"isbn": "1234567890",
"title": "El Principito",
"author": "Antoine",
"genre": "Fábula",
"available": true
}
```
- Responses:
  - `201 Created`: Libro creado exitosamente
  - `400 Bad Request`: Error de validación o ISBN ya existe

#### POST `/api/book/bulk`
Crea múltiples libros en una sola solicitud.
```
[
{
"isbn": "1234567890",
"title": "Libro 1",
"author": "Autor 1",
"genre": "Género 1",
"available": true
},
{
"isbn": "0987654321",
"title": "Libro 2",
"author": "Autor 2",
"genre": "Género 2",
"available": true
}
]
```
- Responses:
  - `201 Created`: Libros creados exitosamente
  - `400 Bad Request`: Error de validación

#### PUT `/api/book/{isbn}`
Actualiza un libro existente.
- Responses:
  - `200 OK`: Libro actualizado
  - `404 Not Found`: ISBN no encontrado

#### PUT `/api/book/available/{isbn}/{available}`
Actualiza la disponibilidad de un libro.
- Responses:
  - `200 OK`: Disponibilidad actualizada
  - `404 Not Found`: ISBN no encontrado

#### DELETE `/api/book/{isbn}`
Elimina un libro.
- Responses:
  - `200 OK`: Libro eliminado
  - `404 Not Found`: ISBN no encontrado

### Usuarios (Users)

#### GET `/api/user`
Devuelve una lista paginada de todos los usuarios.
Parámetros: `pageNum`, `pageSize`, `sort`
- Responses: `200 OK`

#### GET `/api/user/user-name/{username}`
Busca un usuario por nombre de usuario.
- Responses:
  - `200 OK`: Usuario encontrado
  - `404 Not Found`: Usuario no encontrado

#### GET `/api/user/email/{email}`
Busca un usuario por email.
- Responses:
  - `200 OK`: Usuario encontrado
  - `404 Not Found`: Email no encontrado

#### POST `/api/user`
Crea un nuevo usuario.
```
{
"username": "nuevoUsuario",
"password": "contraseña",
"email": "usuario@email.com",
"firstName": "Nombre",
"lastName": "Apellido"
}
```
- Responses:
  - `201 Created`: Usuario creado exitosamente
  - `400 Bad Request`: Error de validación o usuario ya existe

#### POST `/api/user/bulk`
Crea múltiples usuarios en una sola solicitud.
- Responses:
  - `201 Created`: Usuarios creados exitosamente
  - `400 Bad Request`: Error de validación

#### PUT `/api/user/{username}`
Actualiza un usuario existente.
- Responses:
  - `200 OK`: Usuario actualizado
  - `404 Not Found`: Usuario no encontrado

#### PUT `/api/user/update-password/{username}`
Actualiza la contraseña de un usuario.
```
{
"currentPassword": "contraseñaActual",
"newPassword": "nuevaContraseña"
}
```
- Responses:
  - `200 OK`: Contraseña actualizada
  - `406 Not Acceptable`: Contraseña actual no coincide

#### DELETE `/api/user/{username}`
Elimina un usuario.
- Responses:
  - `200 OK`: Usuario eliminado
  - `404 Not Found`: Usuario no encontrado

### Préstamos (Loans)

#### GET `/api/loan`
Devuelve una lista paginada de todos los préstamos.
Parámetros: `pageNum`, `pageSize`, `sort`
- Responses: `200 OK`

#### GET `/api/loan/user/{username}`
Busca préstamos por usuario con paginación.
Parámetros: `pageNum`, `pageSize`, `sort`
- Responses: `200 OK`

#### GET `/api/loan/book/{isbn}`
Busca préstamos por libro con paginación.
Parámetros: `pageNum`, `pageSize`, `sort`
- Responses: `200 OK`

#### GET `/api/loan/{loanReference}`
Busca un préstamo por referencia.
- Responses:
  - `200 OK`: Préstamo encontrado
  - `404 Not Found`: Referencia no encontrada

#### POST `/api/loan`
Crea un nuevo préstamo.
```
{
"loanReference": "REF123",
"bookIsbn": "1234567890",
"userUsername": "usuario123",
"loanDate": "2023-10-15",
"dueDate": "2023-10-30"
}
```
- Responses:
  - `201 Created`: Préstamo creado exitosamente
  - `400 Bad Request`: Error de validación o libro no disponible

#### POST `/api/loan/bulk`
Crea múltiples préstamos en una sola solicitud.
- Responses:
  - `201 Created`: Préstamos creados exitosamente
  - `400 Bad Request`: Error de validación

#### PUT `/api/loan/{loanReference}`
Extiende la fecha de devolución de un préstamo.
```
{
"2023-11-15"
}
```
- Responses:
  - `200 OK`: Préstamo actualizado
  - `404 Not Found`: Referencia no encontrada

#### DELETE `/api/loan/{loanReference}`
Elimina un préstamo.
- Responses:
  - `200 OK`: Préstamo eliminado
  - `404 Not Found`: Referencia no encontrada

## Requisitos previos

- Java 17 o superior
- Maven 3.6+
- MySQL 8.0+
- Postman o cualquier cliente para probar la API

## Instalación y ejecución

1. Clonar el repositorio:
```
 git clone https://github.com/Alexander-Sot23/JavaSpringSecurity-JWT.git
 cd JavaSpringSecurity-JWT
 ```
2. Configurar la base de datos en `application.properties` (puedes eliminar los comentarios '#'):
```
spring.application.name=JavaSpringSecurity-JWT
#
spring.datasource.url=jdbc:mysql://localhost:3306/tu_base_de_datos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
#
jwt.secret=tu_clave_secreta
jwt.expiration=30				#30 minutos antes de expirar
```
3. Construir y ejecutar la aplicación:
```
mvn spring-boot:run
```
La API estará disponible en: `http://localhost:8080`

## Configuración de seguridad
El sistema utiliza JWT para autenticación. Para acceder a los endpoints protegidos:

Autenticarse en `/authenticate` para obtener un token

Incluir el token en las solicitudes como header: `Authorization: Bearer <token>`
