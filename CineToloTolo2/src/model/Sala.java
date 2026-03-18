package model;

public class Sala {

	private int numSala;
	private int aforo;
	private String tipoTransmision;

	public Sala() {
	}

	public Sala(int numSala, int aforo, String tipoTransmision) {
		this.numSala = numSala;
		this.aforo = aforo;
		this.tipoTransmision = tipoTransmision;
	}

	public int getNumSala() {
		return numSala;
	}

	public void setNumSala(int numSala) {
		this.numSala = numSala;
	}

	public int getAforo() {
		return aforo;
	}

	public void setAforo(int aforo) {
		this.aforo = aforo;
	}

	public String getTipoTransmision() {
		return tipoTransmision;
	}

	public void setTipoTransmision(String tipoTransmision) {
		this.tipoTransmision = tipoTransmision;
	}
}