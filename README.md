Resumen del Proyecto
Objetivo: Gestionar libros y autores utilizando Spring Boot y Spring Data JPA para interactuar con una base de datos relacional.

Funcionalidades Principales:

Búsqueda de libros por título mediante una API externa.
Operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para libros y autores.
Estadísticas sobre libros, como contar la cantidad en un idioma específico.
Consultas avanzadas, como listar autores vivos en un año determinado.
Componentes Clave:

Modelo:

Libros: Representa los libros con detalles como título, autores, idiomas.
Autor: Representa los autores con información como nombre, año de nacimiento y muerte.
Repositorios:

LibrosRepository: Gestiona operaciones de base de datos para la entidad Libros.
AutorRepository: Similar a LibrosRepository pero para la entidad Autor, incluyendo consultas específicas como buscar autores vivos en un año.
Servicios:

ConsumoAPI y ConvierteDatos: Utilidades para obtener datos de una API externa y convertirlos en objetos del modelo interno.
Clase Principal (Principal):

Administra la interacción con el usuario a través de un menú interactivo.
Permite al usuario realizar diversas operaciones como buscar libros, listar libros y autores, obtener estadísticas y más.
Tecnologías Utilizadas: Spring Boot y Spring Data JPA para la gestión eficiente de datos y la creación de una interfaz de usuario interactiva.
