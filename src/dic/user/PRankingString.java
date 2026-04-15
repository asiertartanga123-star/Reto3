package dic.user;

public class PRankingString {
	private Idioma idioma_actual;

	public PRankingString(Idioma idioma_actual) {
		this.idioma_actual = idioma_actual;
	}

	public Idioma getIdioma_actual() {
		return idioma_actual;
	}

	public void setIdioma_actual(Idioma idioma_actual) {
		this.idioma_actual = idioma_actual;
	}
	
	public String getNombre() {
		return idioma_actual == Idioma.EN ?"Name: ":"Nombre";
	}
	public String getEntradas() {
		return idioma_actual == Idioma.EN ?"Tickets: ":"Entradas";
	}
}
