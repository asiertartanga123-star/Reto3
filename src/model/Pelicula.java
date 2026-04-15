package model;

/**
 * Clase modelo que representa una película dentro del sistema.
 *
 * Contiene la información principal de una película, como su identificador,
 * título, duración, sinopsis, género, director y valoración.
 */
public class Pelicula {

	private int idPelicula;
	private String titulo;
	private int duracionMin;
	private String sinopsis;
	private String genero;
	private String director;
	private int valoracion;
	private String rutaImg;

	/**
	 * Constructor vacío de la clase {@code Pelicula}.
	 */
	public Pelicula() {
	}

	/**
	 * Constructor que inicializa todos los atributos de la película.
	 *
	 * @param idPelicula  identificador de la película.
	 * @param titulo      título de la película.
	 * @param duracionMin duración de la película en minutos.
	 * @param sinopsis    descripción breve de la trama.
	 * @param genero      género cinematográfico de la película.
	 * @param director    nombre del director.
	 * @param valoracion  valoración de la película.
	 */
	public Pelicula(int idPelicula, String titulo, int duracionMin, String sinopsis, String genero, String director,
			int valoracion) {
		this.idPelicula = idPelicula;
		this.titulo = titulo;
		this.duracionMin = duracionMin;
		this.sinopsis = sinopsis;
		this.genero = genero;
		this.director = director;
		this.valoracion = valoracion;
		this.rutaImg = rutaImg;
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
	 * Devuelve el título de la película.
	 *
	 * @return título de la película.
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * Establece la ruta de la imagen de la película
	 * 
	 * @param rutaImg
	 */
	public void setRutaImg(String rutaIMG){
		this.rutaImg = rutaIMG;
	}

	/**
	 * 
	 * @param rutaImg
	 */
	public String getRutaImg() {
		return rutaImg;
	}

	/**
	 * Establece el título de la película.
	 *
	 * @param titulo título de la película.
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Devuelve la duración de la película en minutos.
	 *
	 * @return duración en minutos.
	 */
	public int getDuracionMin() {
		return duracionMin;
	}

	/**
	 * Establece la duración de la película en minutos.
	 *
	 * @param duracionMin duración en minutos.
	 */
	public void setDuracionMin(int duracionMin) {
		this.duracionMin = duracionMin;
	}

	/**
	 * Devuelve la sinopsis de la película.
	 *
	 * @return sinopsis de la película.
	 */
	public String getSinopsis() {
		return sinopsis;
	}

	/**
	 * Establece la sinopsis de la película.
	 *
	 * @param sinopsis sinopsis de la película.
	 */
	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	/**
	 * Devuelve el género de la película.
	 *
	 * @return género de la película.
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * Establece el género de la película.
	 *
	 * @param genero género de la película.
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}

	/**
	 * Devuelve el director de la película.
	 *
	 * @return nombre del director.
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * Establece el director de la película.
	 *
	 * @param director nombre del director.
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * Devuelve la valoración de la película.
	 *
	 * @return valoración de la película.
	 */
	public int getValoracion() {
		return valoracion;
	}

	/**
	 * Establece la valoración de la película.
	 *
	 * @param valoracion valoración de la película.
	 */
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	@Override
	public String toString() {
		return "Pelicula [idPelicula=" + idPelicula + ", " + (titulo != null ? "titulo=" + titulo + ", " : "")
				+ "duracionMin=" + duracionMin + ", " + (sinopsis != null ? "sinopsis=" + sinopsis + ", " : "")
				+ (genero != null ? "genero=" + genero + ", " : "")
				+ (director != null ? "director=" + director + ", " : "") + "valoracion=" + valoracion + "]";
	}

}