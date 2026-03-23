package ui.element;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

/**
 * <h2>ControlObjects</h2>
 *
 * <p>
 * Clase utilitaria encargada de la creación y configuración de componentes
 * gráficos reutilizables dentro de la interfaz de usuario.
 * </p>
 *
 * <p>
 * Proporciona métodos estáticos que retornan objetos Swing preconfigurados,
 * garantizando consistencia visual (colores, tipografías y comportamiento) en
 * las ventanas del sistema.
 * </p>
 *
 * <p>
 * Esta clase no debe ser instanciada.
 * </p>
 * 
 * @version 1.0
 */
public class ControlObjects {

	/**
	 * Crea un botón de menú con estilo personalizado.
	 *
	 * <p>
	 * El botón se configura con colores definidos para estado normal y hover,
	 * tipografía monoespaciada en negrita y sin borde ni foco visible, ideal para
	 * menús laterales o barras de navegación.
	 * </p>
	 *
	 * <p>
	 * Incluye un {@link java.awt.event.MouseListener} para gestionar el cambio de
	 * colores cuando el cursor entra o sale del componente.
	 * </p>
	 *
	 * @param texto Texto que se mostrará en el botón.
	 * @return Un {@link JButton} configurado con el estilo del menú.
	 */
	public static JButton botonMenu(String texto) {
		JButton boton = new JButton(texto);

		Color bgNormal = new Color(60, 60, 60);
		Color bgHover = new Color(20, 20, 20);

		Color fgNormal = new Color(255, 255, 255);
		Color fgHover = new Color(235, 235, 235);
		boton.setFocusPainted(false);
		boton.setFont(new Font("Consolas", Font.BOLD, 14));
		boton.setBackground(bgNormal);
		boton.setForeground(fgNormal);
		boton.setBorder(null);
		boton.setOpaque(true);
		boton.setContentAreaFilled(true);
		boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// Hover
		boton.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				boton.setBackground(bgHover);
				boton.setForeground(fgHover);
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				boton.setBackground(bgNormal);
				boton.setForeground(fgNormal);
			}
		});

		return boton;
	}

	/**
	 * Crea y configura un {@code JToggleButton} para ser utilizado en un panel
	 * superior de interfaz.
	 * <p>
	 * Este método inicializa un botón tipo toggle, permitiendo su personalización
	 * mediante iconos o, en su defecto, mediante texto con efectos visuales
	 * (hover). El botón se añade al panel especificado en la posición indicada.
	 * </p>
	 *
	 * <p>
	 * Comportamiento:
	 * </p>
	 * <ul>
	 * <li>Si se proporcionan {@code iconMenuOff} y {@code iconMenuOn}, el botón
	 * utilizará dichos iconos para los estados normal y seleccionado
	 * respectivamente.</li>
	 * <li>Si alguno de los iconos es {@code null}, el botón mostrará un texto
	 * definido por {@code auxString} con una fuente y color configurados.</li>
	 * <li>En modo texto, se añade un efecto hover que cambia el color del texto al
	 * pasar el ratón por encima.</li>
	 * </ul>
	 *
	 * <p>
	 * Propiedades visuales aplicadas al botón:
	 * </p>
	 * <ul>
	 * <li>Sin borde visible ({@code setBorderPainted(false)}).</li>
	 * <li>Sin fondo ({@code setContentAreaFilled(false)}).</li>
	 * <li>Sin indicador de foco ({@code setFocusPainted(false)}).</li>
	 * </ul>
	 *
	 * <p>
	 * El botón es añadido al {@code JPanel} utilizando la restricción de layout
	 * indicada por {@code pocisionWestEast} (por ejemplo, {@code BorderLayout.WEST}
	 * o {@code BorderLayout.EAST}).
	 * </p>
	 *
	 * @param topPane          Panel {@code JPanel} al que se añadirá el botón. Se
	 *                         establece su fondo en negro.
	 * @param iconMenuOn       Icono que representa el estado seleccionado del
	 *                         botón.
	 * @param iconMenuOff      Icono que representa el estado no seleccionado del
	 *                         botón.
	 * @param pocisionWestEast Restricción de layout que indica la posición del
	 *                         botón dentro del panel (por ejemplo,
	 *                         {@code BorderLayout.WEST} o
	 *                         {@code BorderLayout.EAST}).
	 * @param auxString        Texto alternativo que se mostrará en el botón en caso
	 *                         de no disponer de iconos.
	 *
	 * @return {@code JToggleButton} Instancia del botón creado y configurado.
	 *
	 * @see javax.swing.JPanel
	 * @see javax.swing.JToggleButton
	 * @see javax.swing.ImageIcon
	 * @see java.awt.Color
	 * @see java.awt.Font
	 * @see java.awt.event.MouseAdapter
	 */
	public static JToggleButton crearToggleSuperior(JPanel topPane, ImageIcon iconMenuOn, ImageIcon iconMenuOff,
			String pocisionWestEast, String auxString) {
		topPane.setBackground(Color.BLACK);

		JToggleButton btnMenu = new JToggleButton();

		// en caso de que no tenga la imagen del toggle
		if (iconMenuOff == null || iconMenuOn == null) {
			btnMenu.setText(auxString);
			btnMenu.setFont(new Font("Consolas", Font.BOLD, 12));
			btnMenu.setForeground(Color.WHITE);

			// hover
			btnMenu.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					btnMenu.setForeground(Color.BLUE);
				}

				@Override
				public void mouseExited(java.awt.event.MouseEvent evt) {
					btnMenu.setForeground(Color.WHITE);
				}
			});
		} else {
			btnMenu.setIcon(iconMenuOff);
			btnMenu.setSelectedIcon(iconMenuOn);
		}
		btnMenu.setFocusPainted(false);
		btnMenu.setBorderPainted(false);
		btnMenu.setContentAreaFilled(false);

		topPane.add(btnMenu, pocisionWestEast);

		return btnMenu;
	}

	/**
	 * Aplica un estilo visual transparente y personalizado a un
	 * {@code JScrollPane}, modificando específicamente la apariencia de su barra de
	 * desplazamiento vertical.
	 * <p>
	 * Este método elimina los elementos visuales por defecto del scroll (fondo,
	 * bordes y botones de incremento/decremento) y aplica un diseño minimalista con
	 * un thumb (indicador de desplazamiento) redondeado y coloreado.
	 * </p>
	 *
	 * <p>
	 * Configuraciones principales aplicadas:
	 * </p>
	 * <ul>
	 * <li>El {@code JScrollPane} y su {@code viewport} se hacen transparentes
	 * ({@code setOpaque(false)}).</li>
	 * <li>Se elimina el borde del scroll ({@code setBorder(null)}).</li>
	 * <li>Se configura la barra vertical ({@code JScrollBar}) como no opaca.</li>
	 * <li>Se establece una velocidad de desplazamiento personalizada
	 * ({@code setUnitIncrement(16)}).</li>
	 * </ul>
	 *
	 * <p>
	 * Personalización de la UI de la barra mediante {@code BasicScrollBarUI}:
	 * </p>
	 * <ul>
	 * <li>{@code thumbColor}: Color del indicador de desplazamiento, definido por
	 * el parámetro.</li>
	 * <li>{@code trackColor}: Totalmente transparente.</li>
	 * <li>Botones de incremento/decremento: sustituidos por botones invisibles sin
	 * tamaño.</li>
	 * <li>{@code paintTrack}: sobrescrito para no renderizar el fondo de la
	 * barra.</li>
	 * <li>{@code paintThumb}: dibuja el thumb con bordes redondeados y
	 * antialiasing.</li>
	 * </ul>
	 *
	 * <p>
	 * El método {@code paintThumb} utiliza {@code Graphics2D} para mejorar la
	 * calidad visual del renderizado, aplicando suavizado (antialiasing) y
	 * dibujando un rectángulo redondeado ajustado a los límites del thumb.
	 * </p>
	 *
	 * @param scroll      Componente {@code JScrollPane} al que se le aplicará el
	 *                    estilo transparente y personalizado.
	 * @param thumb_Color Color del thumb (indicador de desplazamiento) de la barra
	 *                    vertical.
	 *
	 * @see javax.swing.JScrollPane
	 * @see javax.swing.JScrollBar
	 * @see javax.swing.plaf.basic.BasicScrollBarUI
	 * @see java.awt.Color
	 * @see java.awt.Graphics
	 * @see java.awt.Graphics2D
	 * @see java.awt.RenderingHints
	 * @see java.awt.Rectangle
	 * @see javax.swing.JButton
	 * @see java.awt.Dimension
	 */
	public static void aplicarScrollTransparente(JScrollPane scroll, Color thumb_Color) {

		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		scroll.setBorder(null);

		JScrollBar vertical = scroll.getVerticalScrollBar();
		vertical.setOpaque(false);
		vertical.setUnitIncrement(16);

		vertical.setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {

			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = thumb_Color;
				this.trackColor = new Color(0, 0, 0, 0);
			}

			@Override
			protected JButton createDecreaseButton(int orientation) {
				return crearBotonInvisible();
			}

			@Override
			protected JButton createIncreaseButton(int orientation) {
				return crearBotonInvisible();
			}

			private JButton crearBotonInvisible() {
				JButton btn = new JButton();
				btn.setOpaque(false);
				btn.setContentAreaFilled(false);
				btn.setBorderPainted(false);
				btn.setFocusable(false);
				btn.setPreferredSize(new Dimension(0, 0));
				return btn;
			}

			@Override
			protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
				// lo llamamos para no añadir ningun estilo
			}

			@Override
			protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {

				if (thumbBounds.isEmpty() || !scrollbar.isEnabled())
					return;

				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				g2.setColor(thumbColor);

				// rendondear los bordes de la tabla
				g2.fillRoundRect(thumbBounds.x + 2, thumbBounds.y, thumbBounds.width - 4, thumbBounds.height, 10, 10);

				g2.dispose();
			}
		});
	}

}