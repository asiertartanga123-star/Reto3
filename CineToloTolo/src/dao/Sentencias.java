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
}
