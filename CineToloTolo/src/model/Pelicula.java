package model;

public class Pelicula {
	private int id;
	private String titulo;
	private int duracion_min;
	private String sipnosis;
	private Genero genero;
	private String director;
	private int valoracion;

	public Pelicula(int id, String titulo, int duracion_min, String sipnosis, Genero genero, String director,
			int valoracion) {
		this.id = id;
		this.titulo = titulo;
		this.duracion_min = duracion_min;
		this.sipnosis = sipnosis;
		this.genero = genero;
		this.director = director;
		this.valoracion = valoracion;
	}

	public int getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDuracion_min() {
		return duracion_min;
	}

	public void setDuracion_min(int duracion_min) {
		this.duracion_min = duracion_min;
	}

	public String getSipnosis() {
		return sipnosis;
	}

	public void setSipnosis(String sipnosis) {
		this.sipnosis = sipnosis;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

}
