package util.validation;

import dao.DaoUser;
import model.Usuario;
import util.exceptions.*;

public class ValidacionUser {
	public static boolean validarUser(String user_name, String pass) {
		DaoUser daoUser = new DaoUser();
		Usuario user = null;
		try {
			user = daoUser.obtenerUsuario(user_name);
		} catch (Exception e) {
			e.printStackTrace();
		}

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

}
