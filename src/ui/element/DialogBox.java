package ui.element;

import java.awt.Component;

import javax.swing.JOptionPane;

public class DialogBox {

	/**
	 * Muestra un mensaje de advertencia al usuario cuando ocurre un error de carga.
	 * <p>
	 * Este método utiliza {@code JOptionPane} para desplegar un cuadro de diálogo
	 * con un mensaje informativo asociado a un fallo, normalmente relacionado con
	 * la carga de recursos (por ejemplo, imágenes, archivos, etc.).
	 * </p>
	 *
	 * <p>
	 * El parámetro {@code path} puede utilizarse para identificar la ruta del
	 * recurso que provocó el error, aunque en esta implementación no se muestra
	 * directamente en el mensaje.
	 * </p>
	 *
	 * @param padre   Componente {@code Component} que actúa como referencia para
	 *                posicionar el cuadro de diálogo en la interfaz.
	 * @param path    Ruta del recurso que no pudo cargarse (uso informativo o para
	 *                depuración).
	 * @param mensaje Texto que se mostrará al usuario describiendo el error.
	 *
	 * @see javax.swing.JOptionPane
	 * @see java.awt.Component
	 */
	public static void manejarErrorCarga(Component padre, String path, String mensaje) {
		JOptionPane.showMessageDialog(padre, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
	}
}
