package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.ResourceBundle;

import com.mysql.cj.jdbc.CallableStatement;

import interfaz.InterfazCatalogo;
import model.Pelicula;

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
		private final String SQLVERPELIS = Sentencias.VER_PELICULAS;
		private final String SQLPELISID = Sentencias.VER_PELI_ID;

		
		public DaoCatalogo() {
			this.configFile = ResourceBundle.getBundle("configGlobal");
			this.urlBD = this.configFile.getString("Conn");
			this.userBD = this.configFile.getString("DBUser");
			this.passwordBD = this.configFile.getString("DBPass");
		}

		public void obtenerPelis(Map<Integer, Pelicula> peliculas) throws Exception {

			try (Connection con = DriverManager.getConnection(urlBD, userBD, passwordBD);
					CallableStatement cs = (CallableStatement) con.prepareCall(SQLVERPELIS)) {

				try (ResultSet rs = cs.executeQuery()) {

					while (rs.next()) {
						Pelicula catalogo= new Pelicula();
						catalogo.setIdPelicula(rs.getInt("ID_PELICULA"));
						catalogo.setTitulo(rs.getString("TITULO"));
						catalogo.setGenero(rs.getString("GENERO"));
						catalogo.setValoracion(rs.getInt("VALORACION"));
						catalogo.setDuracionMin(rs.getInt("DURACION_MIN"));
						catalogo.setDirector(rs.getString("DIRECTOR"));
						catalogo.setSinopsis(rs.getString("SINOPSIS"));	
						peliculas.put(catalogo.getIdPelicula(), catalogo);
					}
				}
			}
		}
		
		public void verPeliID(int numId) throws Exception {
			
			try (Connection con = DriverManager.getConnection(urlBD, userBD, passwordBD);
					PreparedStatement stmt = con.prepareStatement(SQLPELISID)) {

				stmt.setInt(1, numId);

				try (ResultSet rs = stmt.executeQuery()) {

					if (rs.next()) {
						Pelicula catalogo= new Pelicula();
						catalogo.setIdPelicula(rs.getInt("ID_PELICULA"));
						catalogo.setTitulo(rs.getString("TITULO"));
						catalogo.setGenero(rs.getString("GENERO"));
						catalogo.setValoracion(rs.getInt("VALORACION"));
						catalogo.setDuracionMin(rs.getInt("DURACION_MIN"));
						catalogo.setDirector(rs.getString("DIRECTOR"));
						catalogo.setSinopsis(rs.getString("SINOPSIS"));		
					}
				}
			
		}

	}
}

