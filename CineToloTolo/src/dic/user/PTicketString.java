package dic.user;

public class PTicketString {
	public Idioma idioma_inicial;

	// Español
	public static String USUARIO = "Usuario:";
	public static String PELICULA = "Película:";
	public static String SALA = "Sala:";
	public static String BUTACA = "Butaca:";
	public static String PRECIO = "Precio:";
	public static String FECHA_COMP = "Fecha compra:";
	public static String FECHA_FUN = "Fecha función:";

	// Inglés
	public static String USER = "User:";
	public static String MOVIE = "Movie:";
	public static String ROOM = "Room:";
	public static String SEAT = "Seat:";
	public static String PRICE = "Price:";
	public static String PURCHASE_DATE = "Purchase date:";
	public static String SHOW_DATE = "Show date:";

	public PTicketString(Idioma idioma_inicial) {
		this.idioma_inicial = idioma_inicial;
	}

	public Idioma getIdioma_inicial() {
		return idioma_inicial;
	}

	public void setIdioma_inicial(Idioma idioma_inicial) {
		this.idioma_inicial = idioma_inicial;
	}

	// Getters con condición de idioma
	public String getUSUARIO() {
		return idioma_inicial == Idioma.EN ? USER : USUARIO;
	}

	public String getPELICULA() {
		return idioma_inicial == Idioma.EN ? MOVIE : PELICULA;
	}

	public String getSALA() {
		return idioma_inicial == Idioma.EN ? ROOM : SALA;
	}

	public String getBUTACA() {
		return idioma_inicial == Idioma.EN ? SEAT : BUTACA;
	}

	public String getPRECIO() {
		return idioma_inicial == Idioma.EN ? PRICE : PRECIO;
	}

	public String getFECHA_COMP() {
		return idioma_inicial == Idioma.EN ? PURCHASE_DATE : FECHA_COMP;
	}

	public String getFECHA_FUN() {
		return idioma_inicial == Idioma.EN ? SHOW_DATE : FECHA_FUN;
	}
}