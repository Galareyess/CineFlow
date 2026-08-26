package Clases;
public interface PeliculaDAO {
    void guardar(Pelicula pelicula);
    Pelicula buscarPorId(int id);
    List<Pelicula> listarTodas();
    void actualizar(Pelicula pelicula);
    void eliminar(int id);
}
