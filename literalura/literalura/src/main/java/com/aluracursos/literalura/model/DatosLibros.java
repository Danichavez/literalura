package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(@JsonAlias("title") String titulo,
                          @JsonAlias("authors") List<DatosAutor> autor,
                          @JsonAlias("subjects") List<String> capitulos,
                          @JsonAlias("languages") List<String> idiomas,
                          @JsonAlias("download_count") double numeroDescargas) {

}


