package dic.user;

public class PConfigString {
	private Idioma idioma_actual;

	public PConfigString(Idioma idioma_actual) {
		this.idioma_actual = idioma_actual;
	}

	public Idioma getIdioma_actual() {
		return idioma_actual;
	}

	public void setIdioma_actual(Idioma idioma_actual) {
		this.idioma_actual = idioma_actual;
	}

	public String getConfigCuenta() {
		return idioma_actual == Idioma.ES ? "Configuración de cuenta" : "Account settings";
	}

	public String getCorreo() {
		return idioma_actual == Idioma.ES ? "Correo" : "Email";
	}

	public String getNombre() {
		return idioma_actual == Idioma.ES ? "Nombre" : "Name";
	}

	public String getNuevaContrasena() {
		return idioma_actual == Idioma.ES ? "Nueva contraseña" : "New password";
	}

	public String getRepetirContrasena() {
		return idioma_actual == Idioma.ES ? "Repetir contraseña" : "Repeat password";
	}

	public String getGuardarCambios() {
		return idioma_actual == Idioma.ES ? "Guardar cambios" : "Save changes";
	}

	public String getErrorActualizacion() {
		return idioma_actual == Idioma.ES
				? "ERROR: NO SE PUDO REALIZAR LA ACTUALIZACION\n?\nCONTACTE CON EL SERVICIO TECNICO"
				: "ERROR: THE UPDATE COULD NOT BE COMPLETED\n?\nCONTACT TECHNICAL SUPPORT";
	}

	public String getPasswordUpdated() {
		return idioma_actual == Idioma.EN ? "Password updated" : "Contraseña actualizada";
	}

	public String getPasswordsDontMatch() {
		return idioma_actual == Idioma.EN ? "Passwords do not match" : "Las contraseñas no coinciden";
	}

	public String getNoChanges() {
		return idioma_actual == Idioma.EN ? "No changes were made" : "No se realizaron cambios";
	}

	public String getChangesSaved() {
		return idioma_actual == Idioma.EN ? "Changes saved" : "Cambios guardados";
	}

	public String getUserUpdateError() {
		return idioma_actual == Idioma.EN ? "USER UPDATE ERROR" : "ERROR USER UPDATE";
	}
}