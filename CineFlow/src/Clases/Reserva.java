package Clases;
import java.time.LocalDateTime;
import java.util.List;

public class Reserva{
    private int codigoReserva;
    private String cliente;
    private LocalDateTime fecha;
    private List<Ticket> tickets;

    
public Reserva(int codigoReserva, String cliente, LocalDateTime fecha, List<Ticket> tickets) {
        this.codigoReserva = codigoReserva;
        this.cliente = cliente;
        this.fecha = fecha;
        this.tickets = tickets;
    }

    public int getCodigoReserva() {
        return codigoReserva;
    }
    public String getCliente() {
        return cliente;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }


    public void agregarTicket(Ticket t){

    }

    public void monto(){

    }

    public void calcularTotal(){

    }

    public void generarCodigoDeAcceso(){


    }



}


