LogiExpress API - Sistema de Gestión de Envíos

Autor: Konny Agüero Díaz
Curso: IF0009 - Desarrollo de Software IV
Evaluación: Quiz 4 - Mini-Proyecto Integrador Back-End

Descripción del Proyecto
Este es el código de LogiExpress, una API RESTful que desarrollé en Spring Boot para automatizar la gestión de paquetes y rutas de entrega de una empresa de logística. El sistema permite registrar paquetes, validar reglas como el límite de 30 kg, asignarlos a clientes y consultar sus estados. Todo el sistema está protegido mediante roles y autenticación con tokens JWT.

Justificación de Arquitectura y Desacoplamiento
Para este proyecto decidí utilizar una arquitectura multicapa estricta, dividiendo el código en domain, data, business, controller, config y exception.

Hice este desacoplamiento porque al usar DTOs en la capa de controladores, me aseguro de que la estructura interna de la base de datos nunca quede expuesta directamente. Además, al diseñar esta API REST como una columna vertebral independiente, en el futuro se le pueden conectar múltiples interfaces, como una página web SPA para los administradores o una aplicación móvil para los repartidores, sin necesidad de tocar ni una sola línea de la lógica de negocio o la base de datos en el back-end.

Tecnologías Utilizadas
Lenguaje: Java 21
Framework: Spring Boot 3.3.4
Persistencia: Spring Data JPA y Hibernate
Base de Datos: H2 en memoria
Seguridad: Spring Security y JSON Web Tokens
Documentación: OpenAPI 3.0 con Swagger UI
Pruebas: JUnit 5 Jupiter y Mockito

Instrucciones de Ejecución
Para correr el proyecto ocupas tener instalado Java 21 en tu computadora. Los pasos son los siguientes:

1. Clona este repositorio en tu máquina local y abre la carpeta del proyecto.
2. Para compilar y ejecutar, puedes abrir una terminal y usar el comando ./mvnw.cmd spring-boot:run si estás en Windows, o ./mvnw spring-boot:run si usas Mac o Linux. También puedes simplemente correr la clase principal LogiexpressApplication.java directamente desde tu editor de código.
3. La aplicación va a iniciar en el puerto 8080 y la base de datos H2 se creará automáticamente en la memoria.

Documentación de la API
Una vez que el servidor esté corriendo, puedes ver y probar los endpoints entrando a tu navegador en la dirección: http://localhost:8080/swagger-ui/index.html

Ten en cuenta que las rutas están protegidas. Para probar la creación o eliminación de paquetes, primero tienes que generar un token JWT en el endpoint de login y luego pegarlo en el botón de autorización de Swagger.

Pruebas Unitarias
El proyecto incluye pruebas automatizadas para la capa de negocio. Si quieres ejecutarlas, solo tienes que correr el comando ./mvnw.cmd test en la terminal.
