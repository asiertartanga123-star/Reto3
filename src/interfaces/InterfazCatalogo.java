package interfaces;

import java.util.Map;

import model.Pelicula;

public interface InterfazCatalogo {

	public void obtenerPelis(Map<Integer, Pelicula> peliculas) throws Exception;
	
	void filtrarPorGenero(String genero, Map<Integer, Pelicula> peliculas) throws Exception;
	
    void filtrarPorValoracion(int valoracionMinima, Map<Integer, Pelicula> peliculas) throws Exception;
    
    boolean valorarPelicula(int idPelicula, int nuevaValoracion) throws Exception;
}
