package Clases;
public class Sala {

    private int numeroSala;
    private int capacidad;
    private boolean estado;   //Si esta lleno o vacio
    private String formato;   //2d, 3d, 4d

    public Sala(int numeroSala, int capacidad, boolean estado, String formato) {
        this.numeroSala = numeroSala;
        this.capacidad = capacidad;
        this.estado = estado;
        this.formato = formato;
    }
    public int getNumeroSala() {
        return numeroSala;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public boolean isEstado() {
        return estado;
    }
    public String getFormato() {
        return formato;
    }
    

}
