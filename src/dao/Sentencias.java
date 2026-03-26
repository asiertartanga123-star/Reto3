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
	public static String USER_POR_PK = "SELECT USUARIO, NOMBRE, APELLIDO, CORREO, EDAD, CONTRASENA FROM USUARIO WHERE USUARIO = ?";
	public static String USER_TICKET = "SELECT E.USUARIO, E.NUM_SALA, E.ID_PELICULA, P.TITULO, E.PRECIO, E.FECHA_ADQUIERE, E.FECHA_TRANSMISION, E.NUM_BUTACA FROM ENTRADA E JOIN PELICULA P ON E.ID_PELICULA = P.ID_PELICULA WHERE E.USUARIO = ? ORDER BY E.FECHA_TRANSMISION DESC";
	public static String RANKING_SEMANAL;
	public static String VER_PELICULAS = "{CALL VerPeliculas()}";
	public static String VER_PELI_ID = "SELECT * FROM PELICULA P WHERE P.ID_PELICULA = ?";
	public static String FILTRAR_POR_GENERO   = "SELECT * FROM PELICULA WHERE GENERO = ?";
	public static String FILTRAR_POR_VALORACION = "SELECT * FROM PELICULA WHERE VALORACION >= ?";
	public static String OBTENER_VALORACION_ACTUAL = "SELECT VALORACION FROM PELICULA WHERE ID_PELICULA = ?";
	public static String ACTUALIZAR_VALORACION     = "UPDATE PELICULA SET VALORACION = ? WHERE ID_PELICULA = ?";
}
