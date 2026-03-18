package pruebas;

import util.validacion.ValidacionUser;

public class PruebaValidaciones {
	public static void main(String[] args) {
		boolean valido = ValidacionUser.validarUser("juan01", "hash123");
		if(valido)
			System.out.println("UserValido");
	}
}
