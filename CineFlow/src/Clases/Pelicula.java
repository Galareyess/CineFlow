package Clases;

import java.lang.annotation.Inherited;

import javax.annotation.processing.Generated;

@Entity
@Table(name = "peliculas")
public class Pelicula {
    @Inherited
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private String clasificacion;
    private int duracion;

    public Pelicula(int id, String titulo, String clasificacion, int duracion) {
        this.id = id;
        this.titulo = titulo;
        this.clasificacion = clasificacion;
        this.duracion = duracion;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }


}
