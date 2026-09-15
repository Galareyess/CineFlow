CineFlow

Sistema backend para la gestión de un cine, desarrollado con Java, Spring Boot y MySQL.

El sistema permite gestionar películas, salas, funciones, butacas, usuarios, productos y reservas, además de exponer endpoints REST para la comunicación con clientes externos.

Tecnologías utilizadas
Java 26
Spring Boot
Spring Data JPA
Hibernate
MySQL
Maven
Postman
Arquitectura

El proyecto utiliza una arquitectura organizada por responsabilidades:

Controller
    ↓
Service
    ↓
Repository
    ↓
MySQL
Controller: recibe las solicitudes HTTP y devuelve las respuestas.
Service: contiene la lógica de negocio.
Repository: gestiona el acceso a la base de datos mediante Spring Data JPA.
Entity: representa las entidades del dominio y su relación con las tablas de la base de datos.
DTO: permite transportar datos específicos entre el cliente y el servidor.
Base de datos

El sistema utiliza una base de datos MySQL llamada cineflow.

El archivo schema.sql contiene las instrucciones necesarias para crear la estructura de la base de datos.

Ejecución
Tener instalado Java 26 y MySQL.
Crear la base de datos cineflow.
Configurar las credenciales de MySQL en:
src/main/resources/application.properties
Ejecutar la aplicación Spring Boot.

El servidor queda disponible en:

http://localhost:8080
Endpoints
Usuarios
Método	Endpoint	Descripción
POST	/api/usuarios/clientes	Registra un cliente
POST	/api/usuarios/administradores	Registra un administrador
GET	/api/usuarios/clientes/{usuario}	Obtiene un cliente
GET	/api/usuarios/login?usuario=...&contrasena=...	Inicia sesión
Películas
Método	Endpoint	Descripción
GET	/api/peliculas	Obtiene todas las películas
GET	/api/peliculas/{id}	Obtiene una película por ID
POST	/api/peliculas	Crea una película
PUT	/api/peliculas/{id}	Modifica una película
DELETE	/api/peliculas/{id}	Elimina una película
Cartelera
Método	Endpoint	Descripción
GET	/api/cartelera	Obtiene todas las funciones
GET	/api/cartelera/{id}	Obtiene una función por ID
POST	/api/cartelera	Crea una función
DELETE	/api/cartelera/{id}	Elimina una función
Salas y butacas
Método	Endpoint	Descripción
GET	/api/salas	Obtiene todas las salas
GET	/api/salas/{id}	Obtiene una sala por ID
POST	/api/salas	Crea una sala
PUT	/api/salas/{id}	Modifica una sala
DELETE	/api/salas/{id}	Elimina una sala
GET	/api/salas/butacas	Obtiene las butacas
POST	/api/salas/butacas	Crea una butaca
GET	/api/salas/butacas/{fila}/{numero}/disponible	Consulta la disponibilidad de una butaca
DELETE	/api/salas/butacas/{fila}/{numero}	Elimina una butaca
Productos
Método	Endpoint	Descripción
GET	/api/productos	Obtiene todos los productos
GET	/api/productos/{id}	Obtiene un producto por ID
POST	/api/productos	Crea un producto
PUT	/api/productos/{id}	Modifica un producto
DELETE	/api/productos/{id}	Elimina un producto
Ventas y reservas
Método	Endpoint	Descripción
GET	/api/ventas	Obtiene todas las reservas
GET	/api/ventas/{id}	Obtiene una reserva por ID
GET	/api/ventas/tickets	Obtiene todos los tickets
POST	/api/ventas	Crea una venta y procesa el pago
DELETE	/api/ventas/{id}	Cancela una reserva
Validaciones

El sistema utiliza Jakarta Validation para validar los datos recibidos en las solicitudes. Entre las validaciones utilizadas se encuentran:

@NotNull
@NotBlank
@Positive
@Valid

También se realizan validaciones propias de la lógica de negocio, por ejemplo, para comprobar la disponibilidad de butacas y validar los datos de una tarjeta durante el proceso de pago.

Manejo de errores

El proyecto cuenta con un manejador global de excepciones mediante @RestControllerAdvice.

Las excepciones de tipo IllegalArgumentException son capturadas y devueltas como respuestas HTTP 404 NOT FOUND junto con un mensaje descriptivo.

Ejemplo:

{
    "error": "Producto no encontrado: 1"
}
Pruebas con Postman

Los endpoints fueron probados mediante Postman, permitiendo comprobar la comunicación entre un cliente externo, el servidor Spring Boot y la base de datos MySQL.

La colección utilizada se encuentra en:

CineFlow API.json

La colección incluye las operaciones CRUD principales y utiliza variables para parametrizar las solicitudes.

Front-end

El front-end definitivo no forma parte de esta entrega.

El backend puede recibir y procesar solicitudes externas mediante clientes como Postman.
