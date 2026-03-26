package model;

import java.time.LocalDateTime;

/**
 * Clase modelo que representa una entrada de cine dentro del sistema.
 *
 * Contiene la información asociada a la compra de una entrada, como el usuario
 * que la adquirió, la sala, la película, la butaca asignada, el precio y las
 * fechas de adquisición y transmisión de la función.
 */
public class Entrada {

	private String usuario;
	private int numSala;
	private int idPelicula;
	private int numButaca;
	private int precio;
	private LocalDateTime fechaTransmision;
	private LocalDateTime fechaAdquiere;

	/**
	 * Constructor vacío de la clase {@code Entrada}.
	 */
	public Entrada() {
	}

	/**
	 * Constructor que inicializa todos los atributos de una entrada.
	 *
	 * @param usuario          nombre del usuario que compra la entrada.
	 * @param numSala          número de la sala donde se proyecta la película.
	 * @param idPelicula       identificador de la película.
	 * @param numButaca        número de la butaca asignada.
	 * @param precio           precio de la entrada.
	 * @param fechaTransmision fecha y hora de la proyección.
	 * @param fechaAdquiere    fecha y hora en la que se adquiere la entrada.
	 */
	public Entrada(String usuario, int numSala, int idPelicula, int numButaca, int precio,
			LocalDateTime fechaTransmision, LocalDateTime fechaAdquiere) {
		this.usuario = usuario;
		this.numSala = numSala;
		this.idPelicula = idPelicula;
		this.numButaca = numButaca;
		this.precio = precio;
		this.fechaTransmision = fechaTransmision;
		this.fechaAdquiere = fechaAdquiere;
	}

	/**
	 * Devuelve el usuario asociado a la entrada.
	 *
	 * @return nombre del usuario.
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Establece el usuario asociado a la entrada.
	 *
	 * @param usuario nombre del usuario.
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Devuelve el número de sala de la proyección.
	 *
	 * @return número de sala.
	 */
	public int getNumSala() {
		return numSala;
	}

	/**
	 * Establece el número de sala de la proyección.
	 *
	 * @param numSala número de sala.
	 */
	public void setNumSala(int numSala) {
		this.numSala = numSala;
	}

	/**
	 * Devuelve el identificador de la película.
	 *
	 * @return id de la película.
	 */
	public int getIdPelicula() {
		return idPelicula;
	}

	/**
	 * Establece el identificador de la película.
	 *
	 * @param idPelicula id de la película.
	 */
	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	/**
	 * Devuelve el número de butaca asignada.
	 *
	 * @return número de butaca.
	 */
	public int getNumButaca() {
		return numButaca;
	}

	/**
	 * Establece el número de butaca asignada.
	 *
	 * @param numButaca número de butaca.
	 */
	public void setNumButaca(int numButaca) {
		this.numButaca = numButaca;
	}

	/**
	 * Devuelve el precio de la entrada.
	 *
	 * @return precio de la entrada.
	 */
	public int getPrecio() {
		return precio;
	}

	/**
	 * Establece el precio de la entrada.
	 *
	 * @param precio precio de la entrada.
	 */
	public void setPrecio(int precio) {
		this.precio = precio;
	}

	/**
	 * Devuelve la fecha y hora de transmisión de la película.
	 *
	 * @return fecha de transmisión.
	 */
	public LocalDateTime getFechaTransmision() {
		return fechaTransmision;
	}

	/**
	 * Establece la fecha y hora de transmisión de la película.
	 *
	 * @param fechaTransmision fecha de transmisión.
	 */
	public void setFechaTransmision(LocalDateTime fechaTransmision) {
		this.fechaTransmision = fechaTransmision;
	}

	/**
	 * Devuelve la fecha y hora en la que se adquirió la entrada.
	 *
	 * @return fecha de adquisición.
	 */
	public LocalDateTime getFechaAdquiere() {
		return fechaAdquiere;
	}

	/**
	 * Establece la fecha y hora en la que se adquirió la entrada.
	 *
	 * @param fechaAdquiere fecha de adquisición.
	 */
	public void setFechaAdquiere(LocalDateTime fechaAdquiere) {
		this.fechaAdquiere = fechaAdquiere;
	}
}