package com.aluracursos.literalura.repository;


import com.aluracursos.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query(value = "SELECT * FROM autores " +
            "WHERE anio_Muerte IS NULL OR anio_Muerte > :anio", nativeQuery = true)
    List<Autor> findAutoresVivosEnAnio(@Param("anio") int anio);


}
