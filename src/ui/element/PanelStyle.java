package ui.element;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Clase contenedora de estilos personalizados para la interfaz gráfica de
 * usuario.
 * <p>
 * {@code PanelStyle} agrupa paneles y utilidades visuales con características
 * especiales, tales como fondos con imágenes, paneles con esquinas redondeadas
 * y otros elementos estilizados que pueden ser reutilizados en distintas partes
 * de la aplicación.
 * </p>
 *
 * <p>
 * Clases anidadas incluidas:
 * </p>
 * <ul>
 * <li>{@link PanelStyle.BackgroundPanel}: Panel con imagen de fondo escalada
 * automáticamente.</li>
 * <li>{@link PanelStyle.RoundedPanel}: Panel con esquinas redondeadas y color
 * de fondo configurable.</li>
 * </ul>
 *
 * @see javax.swing.JPanel
 * @see java.awt.Color
 * @see java.awt.Image
 * @Notas
 *        <p>
 *        Se creo así, ya que quiero generar dependencia con la clase
 *        panelStyle, y que sirva como un contenedor de mis clases panel, que no
 *        son tan fuertes para ser clases propias.
 *        </p>
 */
public class PanelStyle {
	/**
	 * Panel personalizado que permite establecer una imagen de fondo escalada
	 * dinámicamente.
	 * <p>
	 * Esta clase extiende {@code JPanel} y sobreescribe el método
	 * {@code paintComponent} para dibujar una imagen que ocupa todo el tamaño del
	 * panel, adaptándose a sus dimensiones en tiempo de ejecución.
	 * </p>
	 *
	 * <p>
	 * La imagen se carga desde el classpath mediante
	 * {@code getClass().getResource(path)} y se almacena como un objeto
	 * {@code Image}.
	 * </p>
	 *
	 * <p>
	 * Durante el renderizado, la imagen se dibuja escalada al ancho y alto actuales
	 * del panel, permitiendo que el fondo se ajuste automáticamente al
	 * redimensionamiento del componente.
	 * </p>
	 *
	 * @see javax.swing.JPanel
	 * @see java.awt.Image
	 * @see java.awt.Graphics
	 * @see javax.swing.ImageIcon
	 */
	public static class BackgroundPanel extends JPanel {
		/**
		 * asdf
		 */
		private static final long serialVersionUID = 1L;
		private final Image img;

		/**
		 * Constructor que carga la imagen de fondo desde una ruta del classpath.
		 *
		 * @param path Ruta del recurso de imagen que se utilizará como fondo.
		 */
		public BackgroundPanel(String path) {
			img = new ImageIcon(getClass().getResource(path)).getImage();
		}

		/**
		 * Sobrescribe el método de renderizado del panel para dibujar la imagen de
		 * fondo.
		 * <p>
		 * Primero invoca {@code super.paintComponent(g)} para asegurar el
		 * comportamiento estándar de Swing, y posteriormente dibuja la imagen escalada
		 * ocupando todo el área del panel.
		 * </p>
		 *
		 * @param g Objeto {@code Graphics} utilizado para el renderizado del
		 *          componente.
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
	}

	/**
	 * Panel personalizado con esquinas redondeadas y color de fondo configurable.
	 * <p>
	 * Esta clase extiende {@code JPanel} y permite definir un radio de redondeo
	 * para las esquinas, así como un color de fondo específico. La pintura se
	 * realiza sobrescribiendo {@code paintComponent} y utilizando
	 * {@code Graphics2D} para habilitar antialiasing, logrando bordes suaves.
	 * </p>
	 *
	 * <p>
	 * El panel se establece como no opaco ({@code setOpaque(false)}) para que solo
	 * se pinte el área redondeada con el color definido y no el fondo
	 * predeterminado de Swing.
	 * </p>
	 *
	 * @see javax.swing.JPanel
	 * @see java.awt.Graphics
	 * @see java.awt.Graphics2D
	 * @see java.awt.Color
	 * @see java.awt.RenderingHints
	 */
	public static class RoundedPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final Color bg;
		private final int radius;

		/**
		 * Constructor que inicializa el color de fondo y el radio de las esquinas.
		 *
		 * @param bg     Color de fondo del panel.
		 * @param radius Radio en píxeles para redondear las esquinas.
		 */
		public RoundedPanel(Color bg, int radius) {
			this.bg = bg;
			this.radius = radius;
			setOpaque(false);
		}

		/**
		 * Sobrescribe el método de renderizado para dibujar el panel con esquinas
		 * redondeadas.
		 * <p>
		 * Se habilita antialiasing para suavizar los bordes y se rellena un rectángulo
		 * redondeado con el color de fondo especificado.
		 * </p>
		 *
		 * @param g Objeto {@code Graphics} utilizado para el renderizado del
		 *          componente.
		 */
		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(bg);
			g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
			super.paintComponent(g);
		}
	}

}
