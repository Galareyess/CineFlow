package Clases;

import java.util.List;

public class Ticket {

    private int codigoTicket;
    private Pelicula pelicula;
    private Butaca butaca;
    private int precio;
    private Sala sala;


    public Ticket(int codigoTicket, Pelicula pelicula, Butaca butaca, int precio, Sala sala) {
        this.codigoTicket = codigoTicket;
        this.pelicula = pelicula;
        this.butaca = butaca;
        this.precio = precio;
        this.sala = sala;
    }
    public int getPrecio() {
        return precio;

    }public Butaca getButaca() {
        return butaca;

    }public int getCodigoTicket() {
        return codigoTicket;

    }public Pelicula getPelicula() {
        return pelicula;
        
    }public Sala getSala() {
        return sala;
    }



}
