# Sistema de Gestión - Club de Gimnasia Rítmica "María"

##  Descripción del Proyecto

Este proyecto es una aplicación web desarrollada con **Spring Boot** para la gestión integral de un club deportivo de gimnasia rítmica. El sistema permite administrar la información de gimnastas, clubes asociados, entrenadores, competiciones, puntuaciones... ofreciendo herramientas para el control de datos y la toma de decisiones.

La aplicación implementa el patrón de arquitectura **MVC (Modelo-Vista-Controlador)** y utiliza **Thymeleaf** para el renderizado de vistas, asegurando una separación clara de responsabilidades y una interfaz de usuario dinámica con **Bootstrap 5**.

###  Tecnologías Utilizadas
* **Backend:** Java 17, Spring Boot .
* **Base de Datos:** MySQL .
* **Frontend:** HTML5, Thymeleaf, Bootstrap .


### Instrucciones de instalación y ejecución Para poner en marcha el proyecto=
  clona el repositorio en tu equipo; a continuación, ejecuta la aplicación desde la terminal utilizando el comando ./mvnw spring-boot:run Una vez arranque, accede desde tu navegador a la dirección http://localhost:8080/club/list e inicia sesión en el formulario de seguridad introduciendo el usuario admin y la contraseña 123 para desbloquear el acceso al sistema. y ya estaria dentro de mi aplicacion

### Listado de funcionalidades implementadas 
La aplicación ofrece una gestión integral del club deportivo mediante un sistema CRUD completo para Clubes (con paginación, exportación a PDF y auditoría de cambios) y Gimnastas, que incluye herramientas avanzadas como filtros de búsqueda combinados, ordenación dinámica y borrado masivo transaccional. Además, integra un dashboard de estadísticas en tiempo real para visualizar métricas clave (como el gimnasta más joven o conteos por ciudad), gestión de catálogos auxiliares (Entrenadores, Competiciones, Aparatos, Puntuaciones) y una capa de seguridad robusta con Spring Security que restringe el acceso a usuarios autenticados.
---

##  Diagrama ER (Entidad-Relación)
fotos\image.png

## capturas de pantalla 


