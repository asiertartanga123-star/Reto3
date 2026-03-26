package dic.user;

public class LoginString {

	private Idioma idiomaActual;

	// -------ESPAÑOL-------
	public final String ES_TITULO_OK = "Acceso permitido";
	public final String ES_MENSAJE_OK = "Bienvenido ";

	public final String ES_TITULO_ERROR = "Acceso denegado";
	public final String ES_MENSAJE_ERROR = "Usuario o contraseña incorrectos";
	public final String ES_INTENTOS = "Intentos restantes: ";

	public final String ES_LABEL_USER = "Usuario";
	public final String ES_LABEL_PASS = "Contraseña";

	// -------ENGLISH-------
	public final String EN_TITULO_OK = "Access granted";
	public final String EN_MENSAJE_OK = "Welcome ";

	public final String EN_TITULO_ERROR = "Access denied";
	public final String EN_MENSAJE_ERROR = "Invalid username or password";
	public final String EN_INTENTOS = "Remaining attempts: ";

	public final String EN_LABEL_USER = "Username";
	public final String EN_LABEL_PASS = "Password";

	public LoginString(Idioma idioma) {
		this.idiomaActual = idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idiomaActual = idioma;
	}

	public Idioma getIdioma() {
		return idiomaActual;
	}

	// --- getter para obtener el string
	public String tituloOk() {
		return idiomaActual == Idioma.ES ? ES_TITULO_OK : EN_TITULO_OK;
	}

	public String mensajeOk() {
		return idiomaActual == Idioma.ES ? ES_MENSAJE_OK : EN_MENSAJE_OK;
	}

	public String tituloError() {
		return idiomaActual == Idioma.ES ? ES_TITULO_ERROR : EN_TITULO_ERROR;
	}

	public String mensajeError() {
		return idiomaActual == Idioma.ES ? ES_MENSAJE_ERROR : EN_MENSAJE_ERROR;
	}

	public String intentos() {
		return idiomaActual == Idioma.ES ? ES_INTENTOS : EN_INTENTOS;
	}

	public String labelUser() {
		return idiomaActual == Idioma.ES ? ES_LABEL_USER : EN_LABEL_USER;
	}

	public String labelPass() {
		return idiomaActual == Idioma.ES ? ES_LABEL_PASS : EN_LABEL_PASS;
	}

	public String buttonLogin() {
		return idiomaActual == Idioma.ES ? "ENTRAR" : "LOGIN";
	}
}