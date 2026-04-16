package dao;

import interfaces.Interfazsalas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.ResourceBundle;
import model.Sala;

/**
 * Clase DAO para gestionar las operaciones de base de datos relacionadas con las salas.
 * Implementa la interfaz Interfazsalas.
 * @author asier
 */
public class DaoSalas implements Interfazsalas {
	/**
	 * Archivo de configuración para la base de datos.
	 */
	private ResourceBundle configFile;
	/**
	 * URL de la base de datos.
	 */
	private String urlBD;
	/**
	 * Usuario de la base de datos.
	 */
	private String userBD;
	/**
	 * Contraseña de la base de datos.
	 */
	private String passwordBD;

	/**
	 * Sentencias SQL utilizadas en las operaciones.
	 */
	// sentencia
	private final String SQLVERSALAS = Sentencias.VER_SALAS;
	private final String SQLVERSALASBUSCAR = Sentencias.VER_SALASBUSCAR;
	private final String SQLBORRARSALA = Sentencias.BORRAR_SALA;
	private final String SQLFUNCION = Sentencias.FUNCION;

	/**
	 * Constructor de la clase DaoSalas.
	 * Inicializa la configuración de la base de datos desde el archivo de propiedades.
	 */
	public DaoSalas() {
		this.configFile = ResourceBundle.getBundle("configGlobal");
		this.urlBD = this.configFile.getString("Conn");
		this.userBD = this.configFile.getString("DBUser");
		this.passwordBD = this.configFile.getString("DBPass");
	}

	/**
	 * Obtiene todas las salas de la base de datos.
	 * @param Salas mapa donde se almacenarán las salas obtenidas.
	 * @return la última sala obtenida, o null si no hay salas.
	 * @throws Exception si ocurre un error en la base de datos.
	 */
	// obtener user por el pk
	public Sala obtenerSalas(Map<Integer, Sala> Salas) throws Exception{

		Sala sala = null;

		try (Connection con = DriverManager.getConnection(urlBD, userBD, passwordBD);
				PreparedStatement stmt = con.prepareStatement(SQLVERSALAS)) {


			try (ResultSet rs = stmt.executeQuery()) {

				while (rs.next()) {
					sala = new Sala();
					sala.setNumSala(rs.getInt("NUM_SALA"));
					sala.setAforo(rs.getInt("AFORO"));
					sala.setTipoTransmision(rs.getString("TIPO_TRANSMISION"));
					Salas.put(sala.getNumSala(), sala);
				}
			}
		}
		return sala;
	}

	/**
	 * Obtiene una sala específica por su ID.
	 * @param id el ID de la sala a buscar.
	 * @param Salas mapa donde se almacenará la sala obtenida.
	 * @return la sala obtenida, o null si no se encuentra.
	 * @throws Exception si ocurre un error en la base de datos.
	 */
	public Sala obtenerSalasBuscar(int id,Map<Integer, Sala> Salas) throws Exception {

		Sala sala = null;


		try (Connection con = DriverManager.getConnection(urlBD, userBD, passwordBD);
				PreparedStatement stmt = con.prepareStatement(SQLVERSALASBUSCAR)) {

			stmt.setInt(1, id);

			try (ResultSet rs = stmt.executeQuery()) {

				while (rs.next()) {
					sala = new Sala();
					sala.setNumSala(rs.getInt("NUM_SALA"));
					sala.setAforo(rs.getInt("AFORO"));
					sala.setTipoTransmision(rs.getString("TIPO_TRANSMISION"));
					Salas.put(sala.getNumSala(), sala);
				}
			}
		}
		return sala;
	}
	
	/**
	 * Borra una sala de la base de datos por su ID.
	 * @param id el ID de la sala a borrar.
	 * @param Salas mapa de salas (no utilizado en esta implementación).
	 * @throws Exception si ocurre un error en la base de datos.
	 */
	public void borrarSala(int id,Map<Integer, Sala> Salas) throws Exception {

		try (Connection con = DriverManager.getConnection(urlBD, userBD, passwordBD);
				PreparedStatement stmt = con.prepareStatement(SQLBORRARSALA)) {

			stmt.setInt(1, id);
			stmt.executeUpdate();
			
		}
	}
	
	/**
	 * Obtiene la disponibilidad de una sala por su ID.
	 * @param id el ID de la sala.
	 * @param Salas mapa de salas (no utilizado en esta implementación).
	 * @return el código de disponibilidad (0: cerrada, 1: disponible, 2: moderada, 3: llena).
	 * @throws Exception si ocurre un error en la base de datos.
	 */
	public int funcion(int id, Map<Integer, Sala> Salas) throws Exception {
	    int disponibilidad = 0;

	    try (Connection con = DriverManager.getConnection(urlBD, userBD, passwordBD);
	         PreparedStatement stmt = con.prepareStatement(SQLFUNCION)) {

	        stmt.setInt(1, id);

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                disponibilidad = rs.getInt(1);
	            }
	        }
	    }
	    return disponibilidad;
	}
	
}
