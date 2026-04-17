package exportadorXML;

import java.util.List;

import model.Entrada;
import model.Pelicula;
import model.Sala;
import model.Usuario;

public interface InterfazExportador {

    public void exportarCatalogo(List<Pelicula> listaPeliculas, List<Usuario>  listaUsuarios, 
                                List<Entrada>  listaEntradas,
                                 String ruta,
                                 List<Sala> listaSalas);
	
}
