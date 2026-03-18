package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import interfaz.InterfazCatalogo;
import model.Pelicula;
import model.Usuario;

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

		
		public DaoCatalogo() {
			this.configFile = ResourceBundle.getBundle("config.configAdmin.properties");
			this.urlBD = this.configFile.getString("Conn");
			this.userBD = this.configFile.getString("DBUser");
			this.passwordBD = this.configFile.getString("DBPass");
		}

		public void obtenerPelis() throws Exception {

			try (Connection con = DriverManager.getConnection(urlBD, userBD, passwordBD);
					PreparedStatement stmt = con.prepareStatement(SQLVERPELIS)) {

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
}
