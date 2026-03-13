package model;

public class Usuario {
	private String nameUser;
	private String nombre;
	private String apellido;
	private String email;
	private int edad;
	private String contraseña;

	public Usuario(String nameUser, String nombre, String apellido, String email, int edad, String contraseña) {
		this.nameUser = nameUser;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.edad = edad;
		this.contraseña = contraseña;
	}

	public String getNameUser() {
		return nameUser;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

}
