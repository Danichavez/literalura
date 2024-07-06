Proyecto: Gestión de Libros y Autores
Descripción General:
El proyecto tiene como objetivo gestionar libros y autores utilizando Spring Boot y Spring Data JPA para interactuar con una base de datos relacional. Permite realizar operaciones como búsqueda de libros por título, listar libros registrados, listar autores, y obtener estadísticas sobre los libros en base a idiomas y autores vivos en un año determinado.

Componentes Principales:
Clases de Modelo:

Libros: Representa la entidad de libros, con atributos como título, autores, idiomas, número de descargas, etc.
Autor: Representa la entidad de autores, con atributos como nombre, año de nacimiento, año de muerte, etc.
Repositorios:

LibrosRepository: Interfaz que extiende JpaRepository para operaciones CRUD y definición de consultas personalizadas para la entidad Libros.
AutorRepository: Interfaz similar para la entidad Autor, con métodos para buscar autores vivos en un año específico.
Servicios:

ConsumoAPI y ConvierteDatos: Utilidades para consumir datos de una API externa y convertirlos en objetos de modelo.
Clase Principal (Principal):

Gestiona la interacción con el usuario a través de un menú interactivo (mostrarMenu()).
Permite realizar operaciones como búsqueda de libros por título, listar libros y autores registrados, obtener autores vivos en un año específico, y obtener estadísticas sobre la cantidad de libros en un idioma dado.
Consultas y Estadísticas:

Utiliza consultas derivadas de Spring Data JPA para contar la cantidad de libros en un idioma específico (countByIdiomasContains) y obtener autores vivos en un año determinado.
También se implementan consultas nativas (@Query con nativeQuery = true) para casos donde las consultas JPQL estándar no son adecuadas, como contar libros por idioma (countLibrosPorIdioma).
Funcionalidades Implementadas:
Búsqueda de Libros: Permite buscar libros por título utilizando una API externa.
Gestión de Libros y Autores: Operaciones CRUD básicas utilizando los repositorios LibrosRepository y AutorRepository.
Estadísticas y Consultas Avanzadas: Proporciona estadísticas como la cantidad de libros en un idioma específico y la lista de autores vivos en un año determinado.
Interfaz de Usuario Interactiva: Menú interactivo que facilita al usuario seleccionar diferentes operaciones.
