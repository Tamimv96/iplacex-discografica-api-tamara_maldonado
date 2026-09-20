
// administra artistas en MongoDB.
package org.iplacex.proyectos.discografia.artistas;

import org.springframework.data.mongodb.repository.MongoRepository;
// extender 
public interface IArtistaRepository extends MongoRepository<Artista, String> {

}