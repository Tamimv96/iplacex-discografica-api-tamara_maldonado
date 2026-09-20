package org.iplacex.proyectos.discografia.discos;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

// Repositorio para administrar documentos Disco,
// de identificador (_id) tipo String.
public interface IDiscoRepository extends MongoRepository<Disco, String> {
// especifica el campo a buscar
    @Query("{ 'idArtista': ?0 }")
    //devuelve todos los discos de un artista
    List<Disco> findDiscosByIdArtista(String idArtista);
}