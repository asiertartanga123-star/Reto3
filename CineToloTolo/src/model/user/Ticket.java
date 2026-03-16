package model.user;

import java.time.LocalDateTime;

/**
 * Clase modelo que representa un ticket de cine adquirido por un usuario.
 *
 * Contiene información sobre el cine, usuario, sala, película, precio y las
 * fechas de adquisición y emisión de la función.
 */
public class Ticket {

	private String nombreCine;
	private String userName;
	private String numSala;
	private int id_peli;
	private String tituloPeli;
	private int precio;
	private LocalDateTime fecha_adqui;
	private LocalDateTime fecha_emision;

	/**
	 * Constructor que inicializa un ticket con los datos proporcionados. El nombre
	 * del cine se establece por defecto a "CineToloTolo".
	 *
	 * @param userName     nombre del usuario que adquirió el ticket.
	 * @param numSala      número de la sala donde se proyecta la película.
	 * @param id_peli      identificador de la película.
	 * @param tituloPeli   título de la película.
	 * @param precio       precio del ticket.
	 * @param fechaAdqui   fecha y hora de adquisición del ticket.
	 * @param fechaEmision fecha y hora de la transmisión de la función.
	 */
	public Ticket(String userName, String numSala, int id_peli, String tituloPeli, int precio, LocalDateTime fechaAdqui,
			LocalDateTime fechaEmision) {

		this.nombreCine = "CineToloTolo";
		this.userName = userName;
		this.numSala = numSala;
		this.id_peli = id_peli;
		this.tituloPeli = tituloPeli;
		this.precio = precio;
		this.fecha_adqui = fechaAdqui;
		this.fecha_emision = fechaEmision;
	}

	/**
	 * Devuelve el nombre del cine.
	 *
	 * @return nombre del cine.
	 */
	public String getNombreCine() {
		return nombreCine;
	}

	/**
	 * Devuelve el nombre del usuario que adquirió el ticket.
	 *
	 * @return nombre del usuario.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Devuelve el número de sala de la proyección.
	 *
	 * @return número de sala.
	 */
	public String getNumSala() {
		return numSala;
	}

	/**
	 * Devuelve el identificador de la película.
	 *
	 * @return id de la película.
	 */
	public int getId_peli() {
		return id_peli;
	}

	/**
	 * Devuelve el título de la película.
	 *
	 * @return título de la película.
	 */
	public String getTituloPeli() {
		return tituloPeli;
	}

	/**
	 * Devuelve el precio del ticket.
	 *
	 * @return precio del ticket.
	 */
	public int getPrecio() {
		return precio;
	}

	/**
	 * Devuelve la fecha y hora en que se adquirió el ticket.
	 *
	 * @return fecha de adquisición.
	 */
	public LocalDateTime getFecha_adqui() {
		return fecha_adqui;
	}

	/**
	 * Devuelve la fecha y hora de emisión de la función.
	 *
	 * @return fecha de emisión.
	 */
	public LocalDateTime getFecha_emision() {
		return fecha_emision;
	}
}