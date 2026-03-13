package model;

public class Sala {
	private int numero;
	private int aforo;
	private TipoTransmision transmision;

	public Sala(int numero, int aforo, TipoTransmision transmision) {
		this.numero = numero;
		this.aforo = aforo;
		this.transmision = transmision;
	}

	public int getNumero() {
		return numero;
	}

	public int getAforo() {
		return aforo;
	}

	public void setAforo(int aforo) {
		this.aforo = aforo;
	}

	public TipoTransmision getTransmision() {
		return transmision;
	}

	public void setTransmision(TipoTransmision transmision) {
		this.transmision = transmision;
	}
}
