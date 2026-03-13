package model;

import java.time.LocalDate;

public class Adquiere {
	private String userName;
	private int id_pelicula;
	private int numero_sala;
	private LocalDate fecha_transmision;
	private LocalDate fecha_adquiere;
	private int precio;
	private int num_butaca;

	public Adquiere(String userName, int id_pelicula, int numero_sala, LocalDate fecha_transmision,
			LocalDate fecha_adquiere, int precio, int num_butaca) {
		this.userName = userName;
		this.id_pelicula = id_pelicula;
		this.numero_sala = numero_sala;
		this.fecha_transmision = fecha_transmision;
		this.fecha_adquiere = fecha_adquiere;
		this.precio = precio;
		this.num_butaca = num_butaca;
	}

	public String getUserName() {
		return userName;
	}

	public int getId_pelicula() {
		return id_pelicula;
	}

	public int getNumero_sala() {
		return numero_sala;
	}

	public LocalDate getFecha_transmision() {
		return fecha_transmision;
	}

	public LocalDate getFecha_adquiere() {
		return fecha_adquiere;
	}

	public void setFecha_adquiere(LocalDate fecha_adquiere) {
		this.fecha_adquiere = fecha_adquiere;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getNum_butaca() {
		return num_butaca;
	}

	public void setNum_butaca(int num_butaca) {
		this.num_butaca = num_butaca;
	}

}
