package Clases;
public class Cliente {
    private int id;
    private String nombre;
    private String apellido;
    private String gmail;
    private String dni;

    public Cliente(int id, String nombre, String apellido, String gmail, String dni) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.gmail = gmail;
        this.dni = dni;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getGmail() {
        return gmail;
    }

    public String getDni() {
        return dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
