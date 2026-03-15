package util.validacion;

import dao.DaoUser;
import model.Usuario;

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
}
