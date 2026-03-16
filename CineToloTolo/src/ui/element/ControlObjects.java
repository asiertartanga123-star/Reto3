package ui.element;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

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

		Color bgNormal = new Color(10, 10, 10);
		Color bgHover = new Color(30, 30, 30);
		Color fgNormal = new Color(0, 0, 250);
		Color fgHover = new Color(120, 120, 255);

		boton.setFocusPainted(false);
		boton.setFont(new Font("Consolas", Font.BOLD, 14));
		boton.setBackground(bgNormal);
		boton.setForeground(fgNormal);
		boton.setBorder(null);
		boton.setOpaque(true);

		// hover
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
}