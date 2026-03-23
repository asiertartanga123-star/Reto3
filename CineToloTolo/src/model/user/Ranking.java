package model.user;

public class Ranking {
	private int posicion_top;
	private String usuario;
	private String nombre;
	private int total_entradas;

	public Ranking() {
	}

	public int getPosicion_top() {
		return posicion_top;
	}

	public void setPosicion_top(int posicion_top) {
		this.posicion_top = posicion_top;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTotal_entradas() {
		return total_entradas;
	}

	public void setTotal_entradas(int total_entradas) {
		this.total_entradas = total_entradas;
	}

	@Override
	public String toString() {
		return "Ranking [posicion_top=" + posicion_top + ", " + (usuario != null ? "usuario=" + usuario + ", " : "")
				+ (nombre != null ? "nombre=" + nombre + ", " : "") + "total_entradas=" + total_entradas + "]";
	}

}
