package org.iplacex.proyectos.discografia.artistas;

//importa interfaz de lista
import java.util.List;

// sirve para indicar qué atributo sera el identificador único
import org.springframework.data.annotation.Id;
// relaciona clase java con coleccion mongo
import org.springframework.data.mongodb.core.mapping.Document;
// indica los objetos clase artista se almacenaran como documentos de la coleccion artistas de mongo
import org.springframework.data.mongodb.core.mapping.Field;

//define la info que debe tener cada ficha de artista
@Document("artistas")
public class Artista {

    @Id
    //identificador único
    public String _id;

    @Field("nombre")
    public String nombre;
// nombre del artista 
//estilos musicales del artista
    @Field("estilos")
    public List<String> estilos;
    @Field("anioFundacion")
    public int anioFundacion;
     @Field("estaActivo")
    public boolean estaActivo;
}