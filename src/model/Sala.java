package model;

/**
 * Clase modelo que representa una sala de cine dentro del sistema.
 *
 * Contiene la información básica de una sala, como su número identificador, el
 * aforo máximo y el tipo de transmisión disponible (por ejemplo, 2D, 3D, IMAX).
 */
public class Sala {

	private int numSala;
	private int aforo;
	private String tipoTransmision;

	/**
	 * Constructor vacío de la clase {@code Sala}.
	 */
	public Sala() {
	}

	/**
	 * Constructor que inicializa todos los atributos de la sala.
	 *
	 * @param numSala         número identificador de la sala.
	 * @param aforo           capacidad máxima de espectadores.
	 * @param tipoTransmision tipo de proyección de la sala.
	 */
	public Sala(int numSala, int aforo, String tipoTransmision) {
		this.numSala = numSala;
		this.aforo = aforo;
		this.tipoTransmision = tipoTransmision;
	}

	/**
	 * Devuelve el número de la sala.
	 *
	 * @return número de sala.
	 */
	public int getNumSala() {
		return numSala;
	}

	/**
	 * Establece el número de la sala.
	 *
	 * @param numSala número de sala.
	 */
	public void setNumSala(int numSala) {
		this.numSala = numSala;
	}

	/**
	 * Devuelve el aforo máximo de la sala.
	 *
	 * @return aforo de la sala.
	 */
	public int getAforo() {
		return aforo;
	}

	/**
	 * Establece el aforo máximo de la sala.
	 *
	 * @param aforo capacidad de la sala.
	 */
	public void setAforo(int aforo) {
		this.aforo = aforo;
	}

	/**
	 * Devuelve el tipo de transmisión de la sala.
	 *
	 * @return tipo de transmisión.
	 */
	public String getTipoTransmision() {
		return tipoTransmision;
	}

	/**
	 * Establece el tipo de transmisión de la sala.
	 *
	 * @param tipoTransmision tipo de transmisión.
	 */
	public void setTipoTransmision(String tipoTransmision) {
		this.tipoTransmision = tipoTransmision;
	}

	@Override
	public String toString() {
		return "Sala [numSala=" + numSala + ", aforo=" + aforo + ", "
				+ (tipoTransmision != null ? "tipoTransmision=" + tipoTransmision : "") + "]";
	}

}