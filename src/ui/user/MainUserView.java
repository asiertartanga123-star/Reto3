package ui.user;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import ui.user.panel.*;
import util.validation.ValidacionUser;
import dic.user.*;
import ui.element.*;

public class MainUserView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	// contenido y paneles necesarios
	private JPanel contentPane;
	private JPanel panelMenu;
	private CardLayout cardLayout;
	private LoginJDialog login;
	private JPanel panelContenido;
	// Panel de inicio (estatico para que se use en todo el codigo)
	private static PanelInicio pi = new PanelInicio();

	// toggle button y botones
	private JToggleButton btnMenu;
	private JButton btnInicio;
	private JButton btnRankSem;
	private JButton btnConfig;
	private JButton btnTick;
	private JButton btnCerrarSesion;
	private JButton btnVolverMenu;

	// idiomas
	private PTicketString pts;
	private MUVString muvString;
	private PConfigString pcs;
	private PRankingString prs;

	// boolean para poder interactuar con el menu, si uso el toggle
	private boolean seInteractua = true;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ValidacionUser.controlExcepcionIrremediable(() -> {
					MainUserView frame = new MainUserView();
					frame.setVisible(true);
					EventQueue.invokeLater(() -> {
						pi.requestFocusInWindow();
						pi.startGame();
					});
				}, "ERROR SQL: \n? \nCONTACT TECHNICAL SUPPORT", "SQL ERROR", true);
			}
		});
	}

	public MainUserView() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(500, 300);
		setLocationRelativeTo(null);
		login = new LoginJDialog(this);
		login.setVisible(true);

		cargarIdioma();
		contentPane = new JPanel(new BorderLayout());
		setContentPane(contentPane);

		JPanel panelTop = new JPanel(new BorderLayout());
		cargarHeader(panelTop);
		add(panelTop, BorderLayout.NORTH);

		crearMenu();
		contenidoDer();
	}

	private void cargarIdioma() {
		pts = new PTicketString(login.getIdioma());
		muvString = new MUVString(login.getIdioma());
		pcs = new PConfigString(login.getIdioma());
		prs = new PRankingString(login.getIdioma());
	}

	private void cargarHeader(JPanel panelTop) {
		panelTop.setBackground(Color.BLACK);
		GraphicObject go = new GraphicObject();

		btnMenu = ControlObjects.crearToggleSuperior(panelTop,
				go.cargarIconoEscalado("C:/Users/Lidia/Desktop/repositorios/Reto3.1/Reto3/CineToloTolo/bin/res/img/off_toggle.png", 40, 40, this),
				go.cargarIconoEscalado("/res/img/on_toggle.png", 40, 40, this), "West", "MENU");
		btnMenu.addActionListener(this);
	}

	private void crearMenu() {
		panelMenu = new PanelStyle.RoundedPanel(new Color(0, 0, 0), 0);

		panelMenu.setLayout(new GridLayout(6, 1));
		panelMenu.setPreferredSize(new Dimension(0, 0));

		btnInicio = ControlObjects.botonMenu(muvString.getInicio());
		btnRankSem = ControlObjects.botonMenu(muvString.getRankingSemanal());
		btnTick = ControlObjects.botonMenu(muvString.getVerTickets());
		btnConfig = ControlObjects.botonMenu(muvString.getConfiguracion());
		btnCerrarSesion = ControlObjects.botonMenu(muvString.getCerrarSesion());
		btnVolverMenu = ControlObjects.botonMenu(muvString.getVolverMenu());

		btnInicio.addActionListener(this);
		btnTick.addActionListener(this);
		btnRankSem.addActionListener(this);
		btnConfig.addActionListener(this);
		btnCerrarSesion.addActionListener(this);
		btnVolverMenu.addActionListener(this);

		panelMenu.add(btnInicio);
		panelMenu.add(btnRankSem);
		panelMenu.add(btnTick);
		panelMenu.add(btnConfig);
		panelMenu.add(btnVolverMenu);
		panelMenu.add(btnCerrarSesion);

		contentPane.add(panelMenu, BorderLayout.WEST);
	}

	private void toggleMenu() {

		if (btnMenu.isSelected()) {
			panelMenu.setPreferredSize(new Dimension(150, 0));
		} else {
			panelMenu.setPreferredSize(new Dimension(0, 0));
		}

		panelMenu.revalidate();
		panelMenu.repaint();
	}

	private void contenidoDer() throws Exception {
		cardLayout = new CardLayout();
		panelContenido = new JPanel(cardLayout);
		panelContenido.add(pi, "Inicio");
		panelContenido.add(new PanelTicket(login.getUsuario(), pts), "Ticket");
		panelContenido.add(new PanelRanking(login.getUsuario(),prs), "rank");
		panelContenido.add(new PanelConfig(login.getUsuario(), pcs), "config");
		contentPane.add(panelContenido, BorderLayout.CENTER);
	}

	private void cerrarSesion() {
		dispose();
		main(new String[2]);
	}

	/**
	 * Maneja los eventos de acción generados por los elementos interactivos de la
	 * interfaz de usuario.
	 * <p>
	 * Este método determina el origen del evento y ejecuta la acción
	 * correspondiente, permitiendo la navegación entre paneles mediante
	 * {@link CardLayout}, el control del menú lateral y la gestión del ciclo de
	 * ejecución del juego en el panel de inicio.
	 * </p>
	 *
	 * <p>
	 * Comportamiento por componente:
	 * </p>
	 * <ul>
	 * <li>{@code btnMenu}: Alterna la visibilidad del menú lateral.</li>
	 * <li>{@code btnInicio}: Muestra el panel de inicio, asigna el foco al panel
	 * para recibir eventos de teclado e inicia la ejecución del juego.</li>
	 * <li>{@code btnTick}: Muestra el panel de tickets y detiene la ejecución del
	 * juego.</li>
	 * <li>{@code btnRankSem}: Muestra el panel de ranking semanal y detiene la
	 * ejecución del juego.</li>
	 * <li>{@code btnConfig}: Muestra el panel de configuración y detiene la
	 * ejecución del juego.</li>
	 * <li>{@code btnCerrarSesion}: Finaliza la sesión actual y reinicia la vista
	 * principal.</li>
	 * </ul>
	 *
	 * @param e Evento de acción que contiene la referencia al componente que generó
	 *          la interacción del usuario.
	 *
	 * @see java.awt.event.ActionListener
	 * @see java.awt.event.ActionEvent
	 * @see java.awt.CardLayout
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object a = e.getSource();

		if (a == btnMenu) {
			toggleMenu();
			pi.stopGame();
			if (seInteractua) {
				pi.requestFocusInWindow();
				pi.startGame();
			}
			pi.obtenerDimenTo();
			return;
		}
		seInteractua = false;
		if (a == btnInicio) {
			cardLayout.show(panelContenido, "Inicio");
			pi.requestFocusInWindow();
			pi.startGame();
			seInteractua = true;

		}
		if (a == btnTick) {
			cardLayout.show(panelContenido, "Ticket");
			pi.stopGame();
		}
		if (a == btnRankSem) {
			cardLayout.show(panelContenido, "rank");
			pi.stopGame();
		}

		if (a == btnConfig) {
			cardLayout.show(panelContenido, "config");
			pi.stopGame();
		}

		if (a == btnCerrarSesion) {
			cerrarSesion();
		}
		if (a == btnVolverMenu) {
			this.dispose();
		}
	}
}