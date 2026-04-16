package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.ResourceBundle;

import interfaces.Interfazsalas;
import model.Sala;


public class DaoSalas implements Interfazsalas {
	private ResourceBundle configFile;
	private String urlBD;
	private String userBD;
	private String passwordBD;

	// sentencia
	private final String SQLVERSALAS = Sentencias.VER_SALAS;
	private final String SQLVERSALASBUSCAR = Sentencias.VER_SALASBUSCAR;
	private final String SQLBORRARSALA = Sentencias.BORRAR_SALA;
	private final String SQLFUNCION = Sentencias.FUNCION;

	public DaoSalas() {
		this.configFile = ResourceBundle.getBundle("configGlobal");
		this.urlBD = this.configFile.getString("Conn");
		this.userBD = this.configFile.getString("DBUser");
		this.passwordBD = this.configFile.getString("DBPass");
	}

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
	
	public void borrarSala(int id,Map<Integer, Sala> Salas) throws Exception {

		try (Connection con = DriverManager.getConnection(urlBD, userBD, passwordBD);
				PreparedStatement stmt = con.prepareStatement(SQLBORRARSALA)) {

			stmt.setInt(1, id);
			stmt.executeUpdate();
			
		}
	}
	
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
