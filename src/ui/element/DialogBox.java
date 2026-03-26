package ui.element;

import java.awt.Component;

import javax.swing.JOptionPane;

public class DialogBox {

	public static void manejarErrorCarga(Component padre, String path, String mensaje) {
		JOptionPane.showMessageDialog(padre, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
	}
}
