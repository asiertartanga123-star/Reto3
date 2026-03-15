package model;

import java.time.LocalDateTime;

public class Entrada {

	private String usuario;
	private int numSala;
	private int idPelicula;
	private int numButaca;
	private int precio;
	private LocalDateTime fechaTransmision;
	private LocalDateTime fechaAdquiere;

	public Entrada() {
	}

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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getNumSala() {
		return numSala;
	}

	public void setNumSala(int numSala) {
		this.numSala = numSala;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	public int getNumButaca() {
		return numButaca;
	}

	public void setNumButaca(int numButaca) {
		this.numButaca = numButaca;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public LocalDateTime getFechaTransmision() {
		return fechaTransmision;
	}

	public void setFechaTransmision(LocalDateTime fechaTransmision) {
		this.fechaTransmision = fechaTransmision;
	}

	public LocalDateTime getFechaAdquiere() {
		return fechaAdquiere;
	}

	public void setFechaAdquiere(LocalDateTime fechaAdquiere) {
		this.fechaAdquiere = fechaAdquiere;
	}
}