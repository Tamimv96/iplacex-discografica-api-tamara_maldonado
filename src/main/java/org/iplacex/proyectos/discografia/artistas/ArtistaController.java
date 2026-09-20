// recibe peticiones http
package org.iplacex.proyectos.discografia.artistas;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
// indica que esta clase recibira solicitudes http y las respondera
@RestController
//app de otros origenes pueden hacer peticiones
@CrossOrigin
//ruta base para los endpoints
@RequestMapping("/api")
// se atiendan las solicitudes
public class ArtistaController {
    // propiedd privada del repositorio de artistas
private final IArtistaRepository artistaRepository;

public ArtistaController(IArtistaRepository artistaRepository) {
    this.artistaRepository = artistaRepository;
}
// POST
@PostMapping(
    value = "/artista",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<Object> HandleInsertArtistaRequest(@RequestBody Artista artista) {

    Artista artistaGuardado = artistaRepository.save(artista);
    // 201 se creo un nuevo recurso

    return ResponseEntity.status(201).body(artistaGuardado);
}
//metodo GET
@GetMapping(
    value = "/artistas",
    produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<List<Artista>> HandleGetAristasRequest() {

    List<Artista> artistas = artistaRepository.findAll();

    return ResponseEntity.ok(artistas);
}
@GetMapping(
    //el valor del id puede cambiar
    value = "/artista/{id}",
    produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<Object> HandleGetArtistaRequest(@PathVariable String id) {

    return artistaRepository.findById(id)
        .map(artista -> ResponseEntity.ok().body((Object) artista))
        .orElse(ResponseEntity.notFound().build());
}
//PUT
@PutMapping(
    value = "/artista/{id}",
    //recibe y devuelve en formato json
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<Object> HandleUpdateArtistaRequest(
        @PathVariable String id,
        @RequestBody Artista artista) {

    if (artistaRepository.existsById(id)) {

        artista._id = id;

        Artista artistaActualizado = artistaRepository.save(artista);
        
        // la solicitud salio ok 200

        return ResponseEntity.ok(artistaActualizado);
    }
    //404

    return ResponseEntity.notFound().build();
}
// DELETE
@DeleteMapping(
    value = "/artista/{id}",
    produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<Object> HandleDeleteArtistaRequest(@PathVariable String id) {

    if (artistaRepository.existsById(id)) {

        artistaRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

    return ResponseEntity.notFound().build();
}
// prueba 500 @GetMapping("/error-prueba")
//public ResponseEntity<Object> HandleErrorPrueba() {

   // throw new RuntimeException("Error de prueba");
//}
}
