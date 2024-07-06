package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Datos;
import com.aluracursos.literalura.model.DatosLibros;
import com.aluracursos.literalura.model.Libros;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibrosRepository;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

public class Principal {

    private Scanner teclado=new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE="https://gutendex.com/books/";
    private final String API_KEY="1513";
    private ConvierteDatos conversor= new ConvierteDatos();
    private List<DatosLibros> datosLibros= new ArrayList<>();
    @Autowired
    private LibrosRepository librosRepository;

    @Autowired
    private AutorRepository autoresRepository;

    public Principal(LibrosRepository librosRepository, AutorRepository autoresRepository) {
        this.librosRepository = librosRepository;
        this.autoresRepository = autoresRepository;
    }

    public void mostrarMenu(){
        int opcion=-1;
        while(opcion!=0){
            var menu = """
                     ¡Bienvenidos!
                     Selecciona la opción que desees:
                    1. Buscar libro por titulo
                    2. Listar libros de la base de datos
                    3. Listar autores registrados
                    4. Listar autores vivos por año
                    5. Listar libros por idioma
                    0. Salir
                    
                    """;
            System.out.println(menu);
            opcion=teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    mostrarLibrosBuscados();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    mostrarAutoresVivosanio();
                    break;
                case 5:
                    mostrarLibrosIdioma();
                    break;
                case 0:
                    System.out.println("Aplicación Desconectada");
                    break;
                default:
                    System.out.println("Opción invalida");
            }
        }

    }


    private void buscarLibroPorTitulo() {

        System.out.println("Ingrese el nombre del libro que desea buscar:");
        String tituloLibro = teclado.nextLine();

        String url = URL_BASE + "?api_key=" + API_KEY + "&search=" + tituloLibro.replace(" ", "+");
        String json = consumoAPI.obtenerDatos(url);
        Datos datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        if (datosBusqueda.resultados().isEmpty()) {
            System.out.println("Libro no encontrado.");
        } else {
            // Utilizar un conjunto para almacenar títulos únicos
            Set<String> titulosUnicos = new HashSet<>();
            for (DatosLibros resultados : datosBusqueda.resultados()) {
                Libros libroBusqueda = new Libros(resultados);
                titulosUnicos.add(libroBusqueda.getTitulo());
            }

            // Mostrar listado de títulos únicos encontrados
            System.out.println("Se encontraron los siguientes libros:");
            int contador = 1;
            for (String titulo : titulosUnicos) {
                System.out.println(contador + ". " + titulo);
                contador++;
            }

            // Solicitar al usuario que seleccione un libro
            System.out.println("Seleccione el número del libro que desea agregar a la base de datos:");
            int seleccion = teclado.nextInt();
            teclado.nextLine(); // Consumir el salto de línea

            if (seleccion >= 1 && seleccion <= datosBusqueda.resultados().size()) {
                DatosLibros resultadosSeleccionado = datosBusqueda.resultados().get(seleccion - 1);
                Libros libroSeleccionado = new Libros(resultadosSeleccionado);

                imprimirDetallesLibro(libroSeleccionado);

                System.out.println("¿Desea registrar el libro en la base de datos? (1: Sí, 0: No)");
                int opcion = teclado.nextInt();
                teclado.nextLine(); // Consumir el salto de línea

                if (opcion == 1) {
                    // Verificar nuevamente antes de intentar guardar
                    String tituloSeleccionado = libroSeleccionado.getTitulo();
                    if (librosRepository.existsByTitulo(tituloSeleccionado)) {
                        System.out.println("El libro '" + tituloSeleccionado + "' ya está registrado en la base de datos.");
                    } else {
                        try {
                            librosRepository.save(libroSeleccionado);
                            System.out.println("Libro registrado en la base de datos.");
                        } catch (Exception e) {
                            System.out.println("Error al intentar registrar el libro en la base de datos.");
                            e.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("Operación cancelada. El libro no se registró en la base de datos.");
                }
            } else {
                System.out.println("Selección inválida. Operación cancelada.");
            }
        }
      }


    private void imprimirDetallesLibro(Libros libro) {

        System.out.println("DETALLES LIBRO");
        System.out.println("Titulo: " + libro.getTitulo());
        libro.getAutores().forEach(autor -> System.out.println("Autor: " + autor.getNombre()));
        System.out.println("Idioma: " + String.join(", ", libro.getIdiomas()));
        System.out.println("Numero de descargas: " + libro.getNumeroDescargas());
        System.out.println("----------------------------------");
    }

    private void mostrarLibrosBuscados() {
        List<Libros> todosLosLibros = librosRepository.findAll();

        if (todosLosLibros.isEmpty()) {
            System.out.println("No hay libros registrados en la base de datos.");
        } else {
            System.out.println("Lista de todos los libros:");

            for (Libros libro : todosLosLibros) {
                System.out.println("- " + libro.getTitulo());
            }
        }
    }


    private void mostrarAutoresRegistrados() {
        System.out.println("Autores registrados en la base de datos:");

        List<Autor> autores = autoresRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            for (Autor autor : autores) {
                System.out.println("- " + autor.getNombre() + " (ID: " + autor.getAnioNacimiento() + ")");
            }
        }

    }

    private void mostrarAutoresVivosanio() {
        System.out.println("Ingrese el año para listar autores vivos:");
        int anio = teclado.nextInt();
        teclado.nextLine(); // Consumir el salto de línea

        List<Autor> autores = autoresRepository.findAutoresVivosEnAnio(anio);

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + anio);
        } else {
            System.out.println("Autores vivos en el año " + anio + ":");

            for (Autor autor : autores) {
                System.out.println("- " + autor.getNombre());
            }
        }

    }

    private void mostrarLibrosIdioma() {
        System.out.println("Ingrese el idioma para listar la cantidad de libros:");
        String idioma = teclado.nextLine();

        // Contar la cantidad de libros en el idioma especificado
        long cantidadLibros = librosRepository.countLibrosPorIdioma(idioma);

        System.out.println("Cantidad de libros en " + idioma + ": " + cantidadLibros);
    }

}
