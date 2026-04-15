package model;

/**
 * Clase modelo que representa a un usuario dentro del sistema.
 *
 * Contiene la información básica de un usuario, como su identificador, nombre,
 * apellido, correo electrónico, edad y contraseña.
 */
public class Usuario {

	private String usuario;
	private String nombre;
	private String apellido;
	private String correo;
	private int edad;
	private String contrasena;

	/**
	 * Constructor vacío de la clase {@code Usuario}.
	 */
	public Usuario() {
	}

	/**
	 * Constructor que inicializa todos los atributos del usuario.
	 *
	 * @param usuario    identificador o nombre de usuario.
	 * @param nombre     nombre del usuario.
	 * @param apellido   apellido del usuario.
	 * @param correo     correo electrónico del usuario.
	 * @param edad       edad del usuario.
	 * @param contrasena contraseña del usuario.
	 */
	public Usuario(String usuario, String nombre, String apellido, String correo, int edad, String contrasena) {
		this.usuario = usuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.edad = edad;
		this.contrasena = contrasena;
	}

	/**
	 * Devuelve el identificador del usuario.
	 *
	 * @return nombre de usuario.
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Establece el identificador del usuario.
	 *
	 * @param usuario nombre de usuario.
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Devuelve el nombre del usuario.
	 *
	 * @return nombre del usuario.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del usuario.
	 *
	 * @param nombre nombre del usuario.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve el apellido del usuario.
	 *
	 * @return apellido del usuario.
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Establece el apellido del usuario.
	 *
	 * @param apellido apellido del usuario.
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Devuelve el correo electrónico del usuario.
	 *
	 * @return correo electrónico.
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * Establece el correo electrónico del usuario.
	 *
	 * @param correo correo electrónico.
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * Devuelve la edad del usuario.
	 *
	 * @return edad del usuario.
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * Establece la edad del usuario.
	 *
	 * @param edad edad del usuario.
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}

	/**
	 * Devuelve la contraseña del usuario.
	 *
	 * @return contraseña del usuario.
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Establece la contraseña del usuario.
	 *
	 * @param contrasena contraseña del usuario.
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Override
	public String toString() {
		return "Usuario [" + (usuario != null ? "usuario=" + usuario + ", " : "")
				+ (nombre != null ? "nombre=" + nombre + ", " : "")
				+ (apellido != null ? "apellido=" + apellido + ", " : "")
				+ (correo != null ? "correo=" + correo + ", " : "") + "edad=" + edad + ", "
				+ (contrasena != null ? "contrasena=" + contrasena : "") + "]";
	}

}