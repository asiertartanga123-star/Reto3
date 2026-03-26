package interfaces;

import java.util.Map;

import model.Sala;

public interface Interfazsalas {

	public Sala obtenerSalas(Map<Integer, Sala> Salas) throws Exception;
	
	public Sala obtenerSalasBuscar(int id,Map<Integer, Sala> Salas) throws Exception;
	
	public void borrarSala(int id,Map<Integer, Sala> Salas) throws Exception;
	
	public int funcion(int id,Map<Integer, Sala> Salas) throws Exception;
	
}
