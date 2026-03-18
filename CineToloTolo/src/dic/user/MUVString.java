package dic.user;

public class MUVString {

	private Idioma idioma_actual;

	public MUVString(Idioma idioma_actual) {
		this.idioma_actual = idioma_actual;
	}

	public Idioma getIdioma_actual() {
		return idioma_actual;
	}

	public void setIdioma_actual(Idioma idioma_actual) {
		this.idioma_actual = idioma_actual;
	}

	public String getInicio() {
		return idioma_actual == Idioma.EN ? "Home" : "Inicio";
	}

	public String getRankingSemanal() {
		return idioma_actual == Idioma.EN ? "Weekly Ranking" : "Ranking semanal";
	}

	public String getVerTickets() {
		return idioma_actual == Idioma.EN ? "View tickets" : "Ver tickets";
	}

	public String getConfiguracion() {
		return idioma_actual == Idioma.EN ? "Settings" : "Configuración";
	}
}