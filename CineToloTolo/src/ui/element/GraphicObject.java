package ui.element;

import java.awt.Component;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class GraphicObject {
	public ImageIcon cargarIconoEscalado(String path, int ancho, int alto, Component parent) {
		URL url = getClass().getResource(path);

		if (url == null) {
			DialogBox.manejarErrorCarga(parent, path,
					"No se pudo cargar la imagen:\n" + path + "\nNo se añadira imagen.");
			return null;
		}
		ImageIcon icono = new ImageIcon(url);
		Image imagenEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		return new ImageIcon(imagenEscalada);
	}
}
