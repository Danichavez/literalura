package com.aluracursos.literalura.service;


public interface IConvierteDatos {
//Tipo de dato generico, que recibe un tipo de clase generica
    <T> T obtenerDatos(String json, Class<T> clase);
}

