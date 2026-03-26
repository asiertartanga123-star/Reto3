package model;

public class Sala {

	private int numSala;
	private int aforo;
	private String tipoTrasmision;

	public Sala() {
	}

	public Sala(int numSala, int aforo, String tipoTrasmision) {
		this.numSala = numSala;
		this.aforo = aforo;
		this.tipoTrasmision = tipoTrasmision;
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

	public String getTipoTrasmision() {
		return tipoTrasmision;
	}

	public void setTipoTrasmision(String tipoTrasmision) {
		this.tipoTrasmision = tipoTrasmision;
	}
}