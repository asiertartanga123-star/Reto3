package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.ResourceBundle;

import interfaz.InterfazCatalogo;
import model.Pelicula;
import exportadorXML.ExportadorXML;

public class DaoCatalogo implements InterfazCatalogo {

	/**
	 * Clase de acceso a datos (DAO) encargada de gestionar las operaciones
	 * para mostrar el catalogo de peliculas.
	 *
	 */

		// configuracin para recoger info del properties
		private ResourceBundle configFile;
		private String urlBD;
		private String userBD;
		private String passwordBD;

		// sentencia
		private final String SQLVERPELIS          = Sentencias.VER_PELICULAS;
	    private final String SQLFILTRARGENERO     = Sentencias.FILTRAR_POR_GENERO;
	    private final String SQLFILTRARVALORACION = Sentencias.FILTRAR_POR_VALORACION;

		
		public DaoCatalogo() {
			this.configFile = ResourceBundle.getBundle("configGlobal");
			this.urlBD = this.configFile.getString("Conn");
			this.userBD = this.configFile.getString("DBUser");
			this.passwordBD = this.configFile.getString("DBPass");
		}

		public void obtenerPelis(Map<Integer, Pelicula> peliculas) throws Exception {	
			try (Connection con = DriverManager.getConnection(urlBD, userBD, passwordBD);
			         CallableStatement cs = (CallableStatement) con.prepareCall(SQLVERPELIS)) {

			        boolean hasResults = cs.execute();

			        // Recorremos todos los ResultSet generados por el cursor
			        while (hasResults) {
			            try (ResultSet rs = cs.getResultSet()) {
			                if (rs != null) {
			                    while (rs.next()) {
			                        // Crear el objeto Pelicula fila por fila
			                        Pelicula catalogo = new Pelicula();
			                        catalogo.setIdPelicula(rs.getInt(1));  // ID_PELICULA
			                        catalogo.setTitulo(rs.getString(2));   // TITULO
			                        catalogo.setDuracionMin(rs.getInt(3)); // DURACION_MIN
			                        catalogo.setSinopsis(rs.getString(4)); // SINOPSIS
			                        catalogo.setGenero(rs.getString(5));   // GENERO
			                        catalogo.setDirector(rs.getString(6)); // DIRECTOR
			                        catalogo.setValoracion(rs.getInt(7));  // VALORACION

			                        peliculas.put(catalogo.getIdPelicula(), catalogo);
			                    }
			                }
			            }

			            // Pasar al siguiente ResultSet generado por el cursor
			            hasResults = cs.getMoreResults();
			        }
			    }
		}
		
		private Pelicula mapearPelicula(ResultSet rs) throws Exception {
	        Pelicula p = new Pelicula();
	        p.setIdPelicula(rs.getInt("ID_PELICULA"));
	        p.setTitulo(rs.getString("TITULO"));
	        p.setGenero(rs.getString("GENERO"));
	        p.setValoracion(rs.getInt("VALORACION"));
	        p.setDuracionMin(rs.getInt("DURACION_MIN"));
	        p.setDirector(rs.getString("DIRECTOR"));
	        p.setSinopsis(rs.getString("SINOPSIS"));
	        return p;
	    }
		
//		@Override
//	    public void verPeliID(int numId) throws Exception {
//	        try (Connection con = DriverManager.getConnection(urlBD, userBD, passwordBD);
//	             PreparedStatement stmt = con.prepareStatement(SQLPELISID)) {
//
//	            stmt.setInt(1, numId);
//	            try (ResultSet rs = stmt.executeQuery()) {
//	                if (rs.next()) {
//	                    mapearPelicula(rs); 
//	                }
//	            }
//	        }
//	    }
		
		public void filtrarPorGenero(String genero, Map<Integer, Pelicula> peliculas) throws Exception {
	        try (Connection con = DriverManager.getConnection(urlBD, userBD, passwordBD);
	             PreparedStatement stmt = con.prepareStatement(SQLFILTRARGENERO)) {

	            stmt.setString(1, genero);
	            try (ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    Pelicula p = mapearPelicula(rs);
	                    peliculas.put(p.getIdPelicula(), p);
	                }
	            }
	        }
	    }
		
		public void filtrarPorValoracion(int valoracionMinima, Map<Integer, Pelicula> peliculas) throws Exception {
	        try (Connection con = DriverManager.getConnection(urlBD, userBD, passwordBD);
	             PreparedStatement stmt = con.prepareStatement(SQLFILTRARVALORACION)) {

	            stmt.setInt(1, valoracionMinima);
	            try (ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    Pelicula p = mapearPelicula(rs);
	                    peliculas.put(p.getIdPelicula(), p);
	                }
	            }
	        }
	    }
		
		public boolean valorarPelicula(int idPelicula, int nuevaValoracion) throws Exception {

		    // Validar rango de valoración
			if (nuevaValoracion < 1 || nuevaValoracion > 5) {
		        throw new IllegalArgumentException("La valoración debe estar entre 1 y 5.");
		    }

		    try (Connection con = DriverManager.getConnection(urlBD, userBD, passwordBD)) {

		        // 1. Obtener la valoración actual
		        int valoracionActual = 0;
		        try (PreparedStatement stmtSelect = con.prepareStatement(Sentencias.OBTENER_VALORACION_ACTUAL)) {
		            stmtSelect.setInt(1, idPelicula);
		            try (ResultSet rs = stmtSelect.executeQuery()) {
		                if (rs.next()) {
		                    valoracionActual = rs.getInt("VALORACION");
		                } else {
		                    throw new Exception("No se encontró la película con ID: " + idPelicula);
		                }
		            }
		        }

		        // 2. Calcular la media entre la valoración actual y la nueva
		        int mediaValoracion = (valoracionActual + nuevaValoracion) / 2;

		        // 3. Actualizar con la media calculada
		        try (PreparedStatement stmtUpdate = con.prepareStatement(Sentencias.ACTUALIZAR_VALORACION)) {
		            stmtUpdate.setInt(1, mediaValoracion);
		            stmtUpdate.setInt(2, idPelicula);
		            int filasAfectadas = stmtUpdate.executeUpdate();
		            return filasAfectadas > 0;
		        }

		    }
		}
		
	
}

