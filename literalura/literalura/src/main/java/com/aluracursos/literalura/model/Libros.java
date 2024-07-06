package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Libros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "autoreslibros",
            joinColumns = @JoinColumn(name = "idlibro"),
            inverseJoinColumns = @JoinColumn(name = "idautor")
    )
    private List<Autor> autores;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "idiomasLibros", joinColumns = @JoinColumn(name = "idlibro"))
    @Column(name = "idioma")
    private List<String> idiomas;

    @Column(name = "numdescargas")
    private Double numeroDescargas;

    public Libros() {}

    public Libros(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        this.autores = datosLibros.autor().stream().map(da -> new Autor(da.nombre(), da.anioNacimiento(), da.anioMuerte())).toList();
        this.idiomas = datosLibros.idiomas();
        this.numeroDescargas = datosLibros.numeroDescargas();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }
}