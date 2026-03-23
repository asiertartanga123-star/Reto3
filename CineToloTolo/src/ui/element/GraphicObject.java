package ui.element;

import java.awt.Component;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class GraphicObject {
	/**
	 * Carga un recurso de imagen desde una ruta interna y devuelve un
	 * {@code ImageIcon} escalado a las dimensiones especificadas.
	 * <p>
	 * Este método utiliza {@code getClass().getResource(path)} para localizar la
	 * imagen dentro del classpath. Si el recurso no se encuentra, se muestra un
	 * mensaje de advertencia mediante {@code DialogBox.manejarErrorCarga} y se
	 * devuelve {@code null}.
	 * </p>
	 *
	 * <p>
	 * En caso de éxito, la imagen se escala utilizando {@code getScaledInstance}
	 * con el algoritmo {@code Image.SCALE_SMOOTH} para mejorar la calidad visual
	 * del resultado.
	 * </p>
	 *
	 * @param path   Ruta del recurso de imagen dentro del classpath.
	 * @param ancho  Anchura deseada para la imagen escalada.
	 * @param alto   Altura deseada para la imagen escalada.
	 * @param parent Componente {@code Component} utilizado como referencia para
	 *               mostrar el mensaje de error en caso de fallo en la carga.
	 *
	 * @return {@code ImageIcon} Imagen cargada y escalada si el recurso existe;
	 *         {@code null} en caso de no encontrarse la imagen.
	 *
	 * @see javax.swing.ImageIcon
	 * @see java.awt.Image
	 * @see java.net.URL
	 * @see java.awt.Component
	 * @see DialogBox#manejarErrorCarga
	 */
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
