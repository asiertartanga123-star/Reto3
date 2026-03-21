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