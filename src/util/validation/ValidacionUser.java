package util.validation;

import javax.swing.JOptionPane;

import dao.DaoUser;
import model.Usuario;
import util.exceptions.*;

public class ValidacionUser {
	private static Usuario user = null;
	public static boolean validarUser(String user_name, String pass) {
		DaoUser daoUser = new DaoUser();
		
		controlExcepcionIrremediable(() -> user = daoUser.obtenerUsuario(user_name),
				"ERROR SQL: \n? \nCONTACT TECHNICAL SUPPORT", "SQL ERROR",
				true);

		if (user == null)
			return false;
		if (user_name.equals(user.getUsuario()) && pass.equals(user.getContrasena()))
			return true;
		return false;
	}

	public static boolean seReembolsa(model.user.TicketInfo ticket) {
		try {
			ExceptionUser.validarReembolso(ticket);
			return true;
		} catch (ExceptionToloTolo e) {
			return false;
		}
	}

	// try, parametro una accion (para cerrar el programa, si es que se produce una
	// excepcion inrecuperable)
	public static void controlExcepcionIrremediable(AccionConExcepcion accion, String mensaje, String titulo,
			boolean terminar) {
		try {
			accion.ejecutar();
		} catch (Exception e) {

			String mensajeFinal = mensaje.replace("?",
					e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName());

			JOptionPane.showMessageDialog(null, mensajeFinal, titulo, JOptionPane.ERROR_MESSAGE);
			if (terminar)
				System.exit(0);
		}
	}
}
