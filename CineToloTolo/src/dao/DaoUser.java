package dao;

import model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DaoUser {

	// configuracin para recoger info del properties
	private ResourceBundle configFile;
	private String urlBD;
	private String userBD;
	private String passwordBD;

	// sentencia
	private final String SQLCONSULTAUSUARIO = Sentencias.USER_POR_PK;
	private final String SQLCONSULTATICKETS = Sentencias.USER_TICKET;

	public DaoUser() {
		this.configFile = ResourceBundle.getBundle("config.configCliente");
		this.urlBD = this.configFile.getString("Conn");
		this.userBD = this.configFile.getString("DBUser");
		this.passwordBD = this.configFile.getString("DBPass");
	}

	// obtener user por el pk
	public Usuario obtenerUsuario(String usuario) throws Exception {

		Usuario user = null;

		try (Connection con = DriverManager.getConnection(urlBD, userBD, passwordBD);
				PreparedStatement stmt = con.prepareStatement(SQLCONSULTAUSUARIO)) {

			stmt.setString(1, usuario);

			try (ResultSet rs = stmt.executeQuery()) {

				if (rs.next()) {
					user = new Usuario();
					user.setUsuario(rs.getString("USUARIO"));
					user.setNombre(rs.getString("NOMBRE"));
					user.setApellido(rs.getString("APELLIDO"));
					user.setCorreo(rs.getString("CORREO"));
					user.setEdad(rs.getInt("EDAD"));
					user.setContrasena(rs.getString("CONTRASENA"));
				}
			}
		}
		return user;
	}

	public ArrayList<model.user.Ticket> obtenerTicketsUsuario(String usuario) throws Exception {

		ArrayList<model.user.Ticket> tickets = new ArrayList<>();

		try (Connection con = DriverManager.getConnection(urlBD, userBD, passwordBD);
				PreparedStatement stmt = con.prepareStatement(SQLCONSULTATICKETS)) {

			stmt.setString(1, usuario);

			try (ResultSet rs = stmt.executeQuery()) {

				while (rs.next()) {

					String userName = rs.getString("USUARIO");
					String numSala = rs.getString("NUM_SALA");
					int idPeli = rs.getInt("ID_PELICULA");
					String tituloPeli = rs.getString("TITULO");
					int precio = rs.getInt("PRECIO");

					LocalDateTime fechaAdqui = rs.getTimestamp("FECHA_ADQUIERE").toLocalDateTime();

					LocalDateTime fechaEmision = rs.getTimestamp("FECHA_TRANSMISION").toLocalDateTime();

					model.user.Ticket ticket = new model.user.Ticket(userName, numSala, idPeli, tituloPeli, precio,
							fechaAdqui, fechaEmision);

					tickets.add(ticket);
				}
			}
		}

		return tickets;
	}

}