package dao;

/**
 * Clase que centraliza las sentencias SQL utilizadas en la aplicación.
 *
 * Contiene constantes con consultas relacionadas con los usuarios y sus
 * tickets, permitiendo reutilizar las sentencias desde las clases DAO y
 * facilitar su mantenimiento.
 */
public class Sentencias {
	// Sentensias para el apartado del usuario
	// Select
	public static String USER_POR_PK = "SELECT USUARIO, NOMBRE, APELLIDO, CORREO, EDAD, CONTRASENA FROM USUARIO WHERE USUARIO = ?";
	public static String USER_TICKET = "SELECT E.USUARIO, E.NUM_SALA, E.ID_PELICULA, P.TITULO, E.PRECIO, E.FECHA_ADQUIERE, E.FECHA_TRANSMISION, E.NUM_BUTACA FROM ENTRADA E JOIN PELICULA P ON E.ID_PELICULA = P.ID_PELICULA WHERE E.USUARIO = ? ORDER BY E.FECHA_TRANSMISION DESC";
	// Update/Delete
	public static String SQL_UPDATE_USUARIO = "UPDATE USUARIO SET NOMBRE = ?, CORREO = ?, CONTRASENA = ? WHERE USUARIO = ?";
	public static String DELETE_ENTRADA = "DELETE FROM ENTRADA WHERE USUARIO = ? AND NUM_SALA = ? AND ID_PELICULA = ? AND FECHA_TRANSMISION BETWEEN ? AND ? AND NUM_BUTACA = ?";
	// PROCEDURE
	public static String USER_PRO_RANKING = "{CALL RANKING_USUARIOS_ENTRADAS_HASTA(?,?)}";

	
	public static String VER_PELICULAS = "{CALL VerPeliculas()}";
	public static String VER_PELI_ID = "SELECT * FROM PELICULA P WHERE P.ID_PELICULA = ?";
	public static String FILTRAR_POR_GENERO   = "SELECT * FROM PELICULA WHERE GENERO = ?";
	public static String FILTRAR_POR_VALORACION = "SELECT * FROM PELICULA WHERE VALORACION >= ?";
	public static String OBTENER_VALORACION_ACTUAL = "SELECT VALORACION FROM PELICULA WHERE ID_PELICULA = ?";
	public static String ACTUALIZAR_VALORACION     = "UPDATE PELICULA SET VALORACION = ? WHERE ID_PELICULA = ?";

	public static String VER_SALAS = "SELECT * FROM SALA";
	public static String VER_SALASBUSCAR = "SELECT * FROM SALA WHERE NUM_SALA = ?";
	public static String BORRAR_SALA = "DELETE FROM SALA WHERE NUM_SALA = ?";
	public static String FUNCION = "SELECT DISPONIBILIDAD_AFORO(?)";

	/* Sentencias necesarias para el XML */
	public static final String GET_ALL_PELICULAS = "SELECT * FROM PELICULA";
	public static final String GET_ALL_USUARIOS  = "SELECT * FROM USUARIO";
	public static final String GET_ALL_ENTRADAS  = "SELECT * FROM ENTRADA";

	/* Sentencias necesarias para el administrador*/
	// Usuario
		public static String INSERT_USUARIO = "INSERT INTO USUARIO (USER_NAME, NOMBRE, APELLIDO, CORREO, EDAD, CONTRASEÑA) VALUES (?, ?, ?, ?, ?, ?)";
		
		public static String BORRAR_USUARIO = "DELETE FROM USUARIO WHERE USER_NAME = ?";
		
		public static String ACTUALIZAR_USUARIO = "UPDATE USUARIO SET NOMBRE = ?, APELLIDO = ?, CORREO = ?, EDAD = ?, CONTRASEÑA = ? WHERE USER_NAME = ?";
		
		public static String VER_USUARIOS = "SELECT * FROM USUARIO";
		
		// Pelicula
		public static String INSERT_PELICULA = "INSERT INTO PELICULA (TITULO, DURACION_MIN, SIPNOSIS, MEDIA_VALORACION, GENERO, DIRECTOR) VALUES (?, ?, ?, ?, ?, ?)";
		
		public static String BORRAR_PELICULA = "DELETE FROM PELICULA WHERE ID_PELICULA = ?";
		
		public static String ACTUALIZAR_PELICULA = "UPDATE PELICULA SET TITULO = ?, DURACION_MIN = ?, SIPNOSIS = ?, MEDIA_VALORACION = ?, GENERO = ?, DIRECTOR = ? WHERE ID_PELICULA = ?";
		
		public static String VER_PELICULASS = "SELECT * FROM PELICULA";
		
		// Sala
		public static String INSERT_SALA = "INSERT INTO SALA (NUM_SALA, AFORO, TIPO_TRASMISION) VALUES (?, ?, ?)";
		
	
		
		public static String ACTUALIZAR_SALA = "UPDATE SALA SET AFORO = ?, TIPO_TRASMISION = ? WHERE NUM_SALA = ?";
		

		
		// Entrada
		public static String INSERT_ENTRADA = "INSERT INTO ENTRADA (USER_NAME, NUM_SALA, ID_PELICULA, NUM_BUTACA, PRECIO, FECHA_TRASMISION, FECHA_ADQUIERE) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		public static String BORRAR_ENTRADA = "DELETE FROM ENTRADA WHERE USER_NAME = ? AND NUM_SALA = ? AND ID_PELICULA = ? AND FECHA_TRASMISION = ?";
		
		public static String ACTUALIZAR_ENTRADA = "UPDATE ENTRADA SET NUM_BUTACA = ?, PRECIO = ? WHERE USER_NAME = ? AND NUM_SALA = ? AND ID_PELICULA = ? AND FECHA_TRASMISION = ?";
		
		public static String VER_ENTRADAS = "SELECT * FROM ENTRADA";

		public static String COMPROBACION_ENTRADA = "{CALL comprobarEntrada(?, ?, ?, ?)}";
}
