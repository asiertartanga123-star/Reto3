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
	public static String BOTON = "Solicitar reembolso";

	// Inglés
	public static String USER = "User:";
	public static String MOVIE = "Movie:";
	public static String ROOM = "Room:";
	public static String SEAT = "Seat:";
	public static String PRICE = "Price:";
	public static String PURCHASE_DATE = "Purchase date:";
	public static String SHOW_DATE = "Show date:";
	public static String BUTTON = "Request a refund";

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

	public String getBOTON() {
		return idioma_inicial == Idioma.EN ? BUTTON : BOTON;
	}

	// para las ventanas emergentes
	public String getErrorEliminar() {
		return idioma_inicial == Idioma.EN ? "ERROR: DELETING ENTRY\n ?CONTACT TECHNICAL SUPPORT"
				: "ERROR: AL ELIMINAR LA ENTRADA\n?\nCONTACTE CON EL SERVICIO TECNICO";
	}

	public String getReembolsoCancelado() {
		return idioma_inicial == Idioma.EN ? "Refund canceled" : "Reembolso cancelado";
	}

	public String getTituloCancelado() {
		return idioma_inicial == Idioma.EN ? "Canceled" : "Cancelado";
	}

	public String getPreguntaEliminar() {
		return idioma_inicial == Idioma.EN ? "Do you want to delete this entry?" : "¿Desea eliminar esta entrada?";
	}

	public String getTituloConfirmar() {
		return idioma_inicial == Idioma.EN ? "Confirm deletion" : "Confirmar eliminación";
	}

	public String getExitoEliminar() {
		return idioma_inicial == Idioma.EN ? "Entry successfully deleted" : "Entrada eliminada correctamente";
	}

	public String getTituloExito() {
		return idioma_inicial == Idioma.EN ? "Success" : "Éxito";
	}

	public String getNoEncontrado() {
		return idioma_inicial == Idioma.EN ? "Entry not found for deletion" : "No se encontró la entrada para eliminar";
	}

	public String getTituloAviso() {
		return idioma_inicial == Idioma.EN ? "Notice" : "Aviso";
	}

}