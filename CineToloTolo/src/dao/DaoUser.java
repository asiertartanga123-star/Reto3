package dao;

import model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Clase de acceso a datos (DAO) encargada de gestionar las operaciones
 * relacionadas con los usuarios en la base de datos.
 *
 * Proporciona métodos para obtener la información de un usuario específico y
 * recuperar los tickets asociados a dicho usuario. La conexión a la base de
 * datos se configura a partir de un archivo de propiedades.
 */
public class DaoUser {

	// configuracin para recoger info del properties
	private ResourceBundle configFile;
	private String urlBD;
	private String userBD;
	private String passwordBD;

	// sentencia
	private final String SQLCONSULTAUSUARIO = Sentencias.USER_POR_PK;
	private final String SQLCONSULTATICKETS = Sentencias.USER_TICKET;

	/**
	 * Constructor de la clase DaoUser.
	 *
	 * Inicializa la configuración de conexión a la base de datos leyendo los
	 * parámetros desde el archivo de propiedades {@code config.configCliente}. A
	 * partir de este archivo se obtienen la URL de conexión, el usuario y la
	 * contraseña necesarios para establecer la conexión con la base de datos.
	 */
	public DaoUser() {
		this.configFile = ResourceBundle.getBundle("config.configCliente");
		this.urlBD = this.configFile.getString("Conn");
		this.userBD = this.configFile.getString("DBUser");
		this.passwordBD = this.configFile.getString("DBPass");
	}

	/**
	 * Obtiene un usuario de la base de datos a partir de su identificador (clave
	 * primaria).
	 *
	 * Ejecuta una consulta que recupera los datos del usuario y construye un objeto
	 * {@code Usuario} con la información obtenida.
	 *
	 * @param usuario identificador o nombre de usuario a buscar.
	 * @return objeto {@code Usuario} con los datos recuperados, o {@code null} si
	 *         el usuario no existe en la base de datos.
	 * @throws Exception si ocurre algún error durante el acceso a la base de datos.
	 */
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

	/**
	 * Recupera todos los tickets asociados a un usuario concreto.
	 *
	 * Ejecuta una consulta que obtiene los tickets comprados por el usuario y crea
	 * una lista de objetos {@code Ticket} con la información obtenida de la base de
	 * datos.
	 *
	 * @param usuario identificador o nombre del usuario cuyos tickets se desean
	 *                consultar.
	 * @return lista de tickets pertenecientes al usuario.
	 * @throws Exception si ocurre algún error durante la consulta a la base de
	 *                   datos.
	 */
	public ArrayList<model.user.TicketInfo> obtenerTicketsUsuario(String usuario) throws Exception {

		ArrayList<model.user.TicketInfo> tickets = new ArrayList<>();

		try (Connection con = DriverManager.getConnection(urlBD, userBD, passwordBD);
				PreparedStatement stmt = con.prepareStatement(SQLCONSULTATICKETS)) {

			stmt.setString(1, usuario);

			try (ResultSet rs = stmt.executeQuery()) {

				while (rs.next()) {

					String userName = rs.getString("USUARIO");
					int numSala = rs.getInt("NUM_SALA");
					int idPeli = rs.getInt("ID_PELICULA");
					String tituloPeli = rs.getString("TITULO");
					int precio = rs.getInt("PRECIO");

					LocalDateTime fechaAdqui = rs.getTimestamp("FECHA_ADQUIERE").toLocalDateTime();

					LocalDateTime fechaEmision = rs.getTimestamp("FECHA_TRANSMISION").toLocalDateTime();

					int numButaca = rs.getInt("NUM_BUTACA");

					model.user.TicketInfo ticket = new model.user.TicketInfo(userName, numSala, idPeli, tituloPeli, precio,
							fechaAdqui, fechaEmision, numButaca);

					tickets.add(ticket);
				}
			}
		}

		return tickets;
	}

}