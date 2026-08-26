package Clases;
public class Butaca {
    private int idButaca;
    private int idSala;
    private boolean disponible;
    private int fila;
    
    public Butaca(int idButaca, int idSala, boolean disponible, int fila) {
        this.idButaca = idButaca;
        this.idSala = idSala;
        this.disponible = disponible;
        this.fila = fila;
    }

    public int getIdButaca() {
        return idButaca;
    } 

    public int getIdSala() {
        return idSala;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public int getFila() {
        return fila;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }
}
