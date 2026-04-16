package util.validation;

import javax.swing.JOptionPane;

public class ValidacionAdmin {

	public static String pedirTexto(String mensaje, String errorMsg) {
	    while (true) {
	        String input = JOptionPane.showInputDialog(mensaje);

	        if (input == null) return null; 

	        if (input.trim().isEmpty()) {
	            JOptionPane.showMessageDialog(null, errorMsg);
	        } else {
	            return input;
	        }
	    }
	}
	
	public static Integer pedirEntero(String mensaje) {
	    while (true) {
	        try {
	            String input = JOptionPane.showInputDialog(mensaje);

	            if (input == null) return null;
	            
	            return Integer.parseInt(input);
	            
	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(null, "Introduce a valid number");
	        }
	    }
	}

	public static java.sql.Date pedirFecha(String mensaje) {
	    while (true) {
	        try {
	            String input = JOptionPane.showInputDialog(mensaje);
	            
	            if (input == null) return null;
	            
	            return java.sql.Date.valueOf(input);
	            
	        } catch (IllegalArgumentException e) {
	            JOptionPane.showMessageDialog(null, "Invalid format. Ex: (YYYY-MM-DD)");
	        }
	    }
	}
	

	public static String pedirCorreo(String mensaje) {
	    while (true) {
	        String input = JOptionPane.showInputDialog(mensaje);
	        
	        if (input == null) return null;
	        
	        if (input.trim().isEmpty() || !input.contains("@")) {
	            JOptionPane.showMessageDialog(null, "Invalid mail");
	        } else {
	            return input;
	        }
	    }
	}
	
	// ===========================================
	// 			 	    Usuario
	// ===========================================	
	public static String errorUsuario(String user, String nombre, String apellido, String correo, int edad, String pass) {

        if (user == null || user.trim().isEmpty()) return "Empty user";
        if (nombre == null || nombre.trim().isEmpty()) return "Empty name";
        if (apellido == null || apellido.trim().isEmpty()) return "Empty surname";
        if (correo == null || !correo.contains("@")) return "Empty mail";
        if (edad <= 0 || edad > 120) return "Invalid age";
        if (pass == null || pass.trim().isEmpty()) return "Empty password";
        if (pass.length() < 4) return "Short password";
        
        return null;
        
    }
    
    // ===========================================
 	// 			 	     Sala
 	// ===========================================	
    public static String errorSala(int numSala, int aforo, String tipo) {

    	if (numSala <=0) return "Hall number cannot be 0";
    	if (aforo <=0) return "Capacity cannot be less than 0";
    	if (!tipo.contains("2D") && !tipo.contains("3D")) return "Must be 2D or 3D";

    	return null;

    }

    // ===========================================
  	// 			 	   Pelicula
  	// ===========================================
    public static String errorPelicula(String titulo, int duracion, String sinopsis, int valoracion, String genero, String director) {

        if (titulo == null || titulo.trim().isEmpty()) return "Empty tittle";
        if (duracion <= 0) return "Invalid duration";
        if (sinopsis == null || sinopsis.trim().isEmpty()) return "Empty synopsis";
        if (valoracion < 0 || valoracion > 10) return "Rating should be from 0 to 10";
        if (genero == null || genero.trim().isEmpty()) return "Empty gender";
        if (director == null || director.trim().isEmpty()) return "Empty director";

        return null;

    }
    
    // ===========================================
 	// 			 	    Entrada
 	// ===========================================
    public static String errorEntrada(String nick, int numSala, int idPelicula, int butaca, int precio, java.sql.Date fechaTra, java.sql.Date fechaAdq) {

        if (nick == null || nick.trim().isEmpty()) return "Empty user";
        if (numSala <= 0) return "Invalid hall number";
        if (idPelicula <= 0) return "Invalid movie ID";
        if (butaca <= 0) return "Invalid chair number";
        if (precio < 0) return "Invalid price";
        if (fechaTra == null) return "Invalid transmission date";
        if (fechaAdq == null) return "Invalid acquisition date";

        java.sql.Date hoy = new java.sql.Date(System.currentTimeMillis());
        if (fechaAdq.after(hoy)) return "The acquisition date cannot be more today";
        if (fechaAdq.after(fechaTra)) return "The acquisition cannot be subsequent to the transfer.";

        return null;
    }

    public static boolean esCorreoValido(String input) {
	    return input != null && !input.trim().isEmpty() && input.contains("@");
	}
    
    public static int validarEntero(String input) {
		if (input == null) {
			throw new IllegalArgumentException("Input is null");
		}
		
		try {
	        return Integer.parseInt(input);
	    } catch (NumberFormatException e) {
	        throw new IllegalArgumentException("Invalid number format");
	    }
	}
    
    
}
