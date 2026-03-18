package model.user;

import java.time.LocalDateTime;

public class Ticket {

	private String nombreCine;
	private String userName;
	private String numSala;
	private int id_peli;
	private String tituloPeli;
	private int precio;
	private LocalDateTime fecha_adqui;
	private LocalDateTime fecha_emision;

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

	public String getNombreCine() {
		return nombreCine;
	}

	public String getUserName() {
		return userName;
	}

	public String getNumSala() {
		return numSala;
	}

	public int getId_peli() {
		return id_peli;
	}

	public String getTituloPeli() {
		return tituloPeli;
	}

	public int getPrecio() {
		return precio;
	}

	public LocalDateTime getFecha_adqui() {
		return fecha_adqui;
	}

	public LocalDateTime getFecha_emision() {
		return fecha_emision;
	}
}