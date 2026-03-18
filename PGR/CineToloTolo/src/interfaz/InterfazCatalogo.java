package interfaz;

import java.util.Map;

import model.Pelicula;

public interface InterfazCatalogo {

	public void obtenerPelis(Map<Integer, Pelicula> peliculas) throws Exception;
}
