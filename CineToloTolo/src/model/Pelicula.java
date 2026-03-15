package model;

public class Pelicula {

	private int idPelicula;
	private String titulo;
	private int duracionMin;
	private String sinopsis;
	private String genero;
	private String director;
	private int valoracion;

	public Pelicula() {
	}

	public Pelicula(int idPelicula, String titulo, int duracionMin, String sinopsis, String genero, String director,
			int valoracion) {
		this.idPelicula = idPelicula;
		this.titulo = titulo;
		this.duracionMin = duracionMin;
		this.sinopsis = sinopsis;
		this.genero = genero;
		this.director = director;
		this.valoracion = valoracion;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDuracionMin() {
		return duracionMin;
	}

	public void setDuracionMin(int duracionMin) {
		this.duracionMin = duracionMin;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
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