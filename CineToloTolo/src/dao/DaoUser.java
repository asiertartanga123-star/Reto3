package dao;

import model.Entrada;
import model.Usuario;
import model.user.Ranking;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
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

					model.user.TicketInfo ticket = new model.user.TicketInfo(userName, numSala, idPeli, tituloPeli,
							precio, fechaAdqui, fechaEmision, numButaca);
					tickets.add(ticket);
				}
			}
		}

		return tickets;
	}

	/**
	 * Recupera el ranking de usuarios a partir de un procedimiento almacenado en
	 * base de datos.
	 * <p>
	 * Este método establece una conexión con la base de datos utilizando
	 * {@code DriverManager}, ejecuta la sentencia almacenada definida en
	 * {@code Sentencias.USER_PRO_RANKING} mediante un {@code CallableStatement}, y
	 * construye una lista de objetos {@code Ranking} a partir del {@code ResultSet}
	 * obtenido.
	 * </p>
	 *
	 * <p>
	 * Por cada registro recuperado, se instancia un objeto {@code Ranking} y se
	 * asignan sus atributos a partir de las columnas del resultado:
	 * </p>
	 * <ul>
	 * <li>{@code USUARIO}: Identificador o nombre de usuario.</li>
	 * <li>{@code NOMBRE}: Nombre completo del usuario.</li>
	 * <li>{@code POSICION_TOP}: Posición del usuario en el ranking.</li>
	 * <li>{@code TOTAL_ENTRADAS}: Número total de entradas asociadas al
	 * usuario.</li>
	 * </ul>
	 *
	 * <p>
	 * La conexión, el {@code CallableStatement} y el {@code ResultSet} son
	 * gestionados mediante bloques {@code try-with-resources}, garantizando su
	 * cierre automático.
	 * </p>
	 *
	 * @param user_name Nombre de usuario utilizado como criterio de filtrado en el
	 *                  ranking. Se pasa como primer parámetro al procedimiento
	 *                  almacenado.
	 * @param fecha     Fecha de referencia para el cálculo del ranking. Se
	 *                  convierte a {@code java.sql.Date} antes de ser enviada como
	 *                  segundo parámetro al procedimiento almacenado.
	 *
	 * @return {@code ArrayList<Ranking>} Lista de objetos {@code Ranking} que
	 *         contiene la información del ranking de usuarios obtenida desde la
	 *         base de datos. Si no existen resultados, se devuelve una lista vacía.
	 *
	 * @throws Exception Si ocurre algún error durante la conexión a la base de
	 *                   datos, la ejecución del procedimiento almacenado o el
	 *                   procesamiento del {@code ResultSet}.
	 *
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.CallableStatement
	 * @see java.sql.ResultSet
	 * @see java.sql.Date
	 * @see java.time.LocalDate
	 * @see java.util.ArrayList
	 * @see Ranking
	 * @see Sentencias#USER_PRO_RANKING
	 */
	public ArrayList<Ranking> mostrarRanking(String user_name, LocalDate fecha) throws Exception {

		ArrayList<Ranking> listaUsuarios = new ArrayList<>();

		try (Connection con = DriverManager.getConnection(urlBD, userBD, passwordBD);
				CallableStatement stmt = con.prepareCall(Sentencias.USER_PRO_RANKING)) {

			stmt.setString(1, user_name);
			stmt.setDate(2, java.sql.Date.valueOf(fecha));

			try (ResultSet rs = stmt.executeQuery()) {

				while (rs.next()) {
					Ranking user = new Ranking();
					user.setUsuario(rs.getString("USUARIO"));
					user.setNombre(rs.getString("NOMBRE"));
					user.setPosicion_top(rs.getInt("POSICION_TOP"));
					user.setTotal_entradas(rs.getInt("TOTAL_ENTRADAS"));
					listaUsuarios.add(user);
				}
			}
		}
		return listaUsuarios;
	}

	/**
	 * Actualiza la información de un usuario en la base de datos.
	 * <p>
	 * Este método establece una conexión con la base de datos utilizando
	 * {@code DriverManager} y ejecuta una sentencia SQL de actualización definida
	 * en {@code Sentencias.SQL_UPDATE_USUARIO} mediante un
	 * {@code PreparedStatement}.
	 * </p>
	 *
	 * <p>
	 * Los valores del objeto {@code Usuario} se asignan a los parámetros de la
	 * sentencia SQL en el siguiente orden:
	 * </p>
	 * <ul>
	 * <li>1 - {@code nombre}: Nombre del usuario.</li>
	 * <li>2 - {@code correo}: Correo electrónico del usuario.</li>
	 * <li>3 - {@code contrasena}: Contraseña del usuario.</li>
	 * <li>4 - {@code usuario}: Identificador único del usuario (clave para la
	 * actualización).</li>
	 * </ul>
	 *
	 * <p>
	 * La ejecución de {@code executeUpdate()} devuelve el número de filas
	 * afectadas, lo que permite determinar si la operación se realizó
	 * correctamente.
	 * </p>
	 *
	 * <p>
	 * La conexión y el {@code PreparedStatement} se gestionan mediante un bloque
	 * {@code try-with-resources}, garantizando el cierre automático de los
	 * recursos.
	 * </p>
	 *
	 * @param user Objeto {@code Usuario} que contiene los datos actualizados que se
	 *             desean persistir en la base de datos. Debe incluir tanto los
	 *             nuevos valores como el identificador del usuario a actualizar.
	 *
	 * @return {@code true} si al menos una fila fue actualizada en la base de
	 *         datos; {@code false} en caso contrario (por ejemplo, si no se
	 *         encontró el usuario).
	 *
	 * @throws Exception Si ocurre algún error durante la conexión a la base de
	 *                   datos o la ejecución de la sentencia SQL.
	 *
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.PreparedStatement
	 * @see Usuario
	 * @see Sentencias#SQL_UPDATE_USUARIO
	 */
	public boolean actualizarUsuario(Usuario user) throws Exception {
		int filasActualizadas = 0;

		try (Connection con = DriverManager.getConnection(urlBD, userBD, passwordBD);
				PreparedStatement stmt = con.prepareStatement(Sentencias.SQL_UPDATE_USUARIO)) {

			// asignamos los valores del ?
			stmt.setString(1, user.getNombre());
			stmt.setString(2, user.getCorreo());
			stmt.setString(3, user.getContrasena());
			stmt.setString(4, user.getUsuario());

			// retorna el num de filas actualizadas
			filasActualizadas = stmt.executeUpdate();
		}

		// por ende :/
		return filasActualizadas > 0;
	}

	/**
	 * Elimina una entrada de la base de datos en función de sus atributos
	 * identificativos.
	 * <p>
	 * Este método establece una conexión con la base de datos utilizando
	 * {@code DriverManager} y ejecuta una sentencia SQL de eliminación definida en
	 * {@code Sentencias.DELETE_ENTRADA} mediante un {@code PreparedStatement}.
	 * </p>
	 *
	 * <p>
	 * Para identificar de forma precisa la entrada, se utilizan múltiples atributos
	 * del objeto {@code Entrada}, incluyendo un rango de tiempo basado en la fecha
	 * de transmisión. Se aplica un margen de ±1 segundo sobre
	 * {@code fechaTransmision} para evitar problemas de precisión en la comparación
	 * de timestamps en la base de datos.
	 * </p>
	 *
	 * <p>
	 * Los parámetros asignados a la sentencia SQL son los siguientes:
	 * </p>
	 * <ul>
	 * <li>1 - {@code usuario}: Identificador del usuario asociado a la
	 * entrada.</li>
	 * <li>2 - {@code numSala}: Número de la sala de proyección.</li>
	 * <li>3 - {@code idPelicula}: Identificador de la película.</li>
	 * <li>4 - {@code fechaTransmision - 1 segundo}: Límite inferior del rango
	 * temporal.</li>
	 * <li>5 - {@code fechaTransmision + 1 segundo}: Límite superior del rango
	 * temporal.</li>
	 * <li>6 - {@code numButaca}: Número de la butaca asignada.</li>
	 * </ul>
	 *
	 * <p>
	 * La ejecución de {@code executeUpdate()} devuelve el número de filas
	 * eliminadas, lo que permite determinar si la operación se realizó
	 * correctamente.
	 * </p>
	 *
	 * <p>
	 * La conexión y el {@code PreparedStatement} se gestionan mediante un bloque
	 * {@code try-with-resources}, garantizando el cierre automático de los
	 * recursos.
	 * </p>
	 *
	 * @param entrada Objeto {@code Entrada} que contiene los datos necesarios para
	 *                identificar la entrada a eliminar. Incluye usuario, sala,
	 *                película, fecha de transmisión y número de butaca.
	 *
	 * @return {@code true} si al menos una fila fue eliminada en la base de datos;
	 *         {@code false} en caso contrario.
	 *
	 * @throws Exception Si ocurre algún error durante la conexión a la base de
	 *                   datos o la ejecución de la sentencia SQL.
	 *
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.PreparedStatement
	 * @see java.sql.Timestamp
	 * @see java.time.LocalDateTime
	 * @see Entrada
	 * @see Sentencias#DELETE_ENTRADA
	 */
	public boolean eliminarEntrada(Entrada entrada) throws Exception {
		int filasEliminadas = 0;

		try (Connection con = DriverManager.getConnection(urlBD, userBD, passwordBD);
				PreparedStatement stmt = con.prepareStatement(Sentencias.DELETE_ENTRADA)) {

			LocalDateTime fecha = entrada.getFechaTransmision();
			stmt.setString(1, entrada.getUsuario());
			stmt.setInt(2, entrada.getNumSala());
			stmt.setInt(3, entrada.getIdPelicula());
			stmt.setTimestamp(4, java.sql.Timestamp.valueOf(fecha.minusSeconds(1)));
			stmt.setTimestamp(5, java.sql.Timestamp.valueOf(fecha.plusSeconds(1)));
			stmt.setInt(6, entrada.getNumButaca());

			filasEliminadas = stmt.executeUpdate();
		}

		return filasEliminadas > 0;
	}
}