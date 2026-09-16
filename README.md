LogiExpress API - Sistema de Gestión de Envíos

Konny Agüero Díaz
IF0009 - Desarrollo de Software IV
Quiz 5 - Mini-Proyecto Integrador Back-End

LogiExpress es una API RESTful desarrollada en Spring Boot para automatizar la gestión de paquetes y rutas de entrega de una empresa de logística urbana. El sistema permite el registro de paquetes, validación de reglas de negocio (como el límite de peso), asignación a clientes y consulta de estados, todo protegido mediante roles y autenticación JWT.

El proyecto está estructurado bajo una Arquitectura Multicapa Desacoplada estricta, dividida en los siguientes paquetes:
* `domain`: Entidades del modelo relacional.
* `data`: Repositorios para el acceso a datos (Spring Data JPA).
* `business`: Servicios que encapsulan las reglas de negocio y transacciones.
* `controller`: Controladores REST y objetos de transferencia (DTOs).
* `config`: Configuraciones de Seguridad (RBAC/JWT) y documentación (OpenAPI).
* `exception`: Manejo centralizado de errores bajo el estándar RFC 7807.

¿Por qué este desacoplamiento y cómo sirve como columna vertebral?
El uso estricto de DTOs en la capa `controller` garantiza que la estructura interna de la base de datos (`domain`) nunca se exponga directamente al exterior. Esta API REST actúa como una columna vertebral robusta, independiente y agnóstica a la plataforma que la consume. 
Gracias a este diseño cliente-servidor, en el futuro se pueden conectar múltiples interfaces gráficas simultáneamente —como una aplicación web SPA (React/Angular) para los administradores o una aplicación Móvil (Android/iOS) para los operadores de reparto— sin necesidad de modificar ni una sola línea de código en la lógica de negocio o en la base de datos de este Back-End.

Tecnologías Utilizadas
* **Lenguaje:** Java (JDK 21 Local)
* **Framework:** Spring Boot 3.x
* **Persistencia:** Spring Data JPA / Hibernate
* **Base de Datos:** H2 Database (En memoria)
* **Seguridad:** Spring Security + JSON Web Tokens (JWT)
* **Documentación:** OpenAPI 3.0 / Swagger UI
* **Pruebas:** JUnit 5 Jupiter y Mockito

---

Instrucciones de Ejecución

Requisitos Previos
* Tener instalado **Java Development Kit (JDK) 21**.
* Apache Maven (o utilizar el Wrapper incluido en el proyecto).

Pasos para levantar la aplicación

1. Clonar el repositorio:
   ```bash
   git clone <tu-enlace-de-github>
   cd logiexpress