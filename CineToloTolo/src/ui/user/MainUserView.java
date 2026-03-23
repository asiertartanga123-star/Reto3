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
	private JPanel contentPane;
	private JPanel panelMenu;
	private CardLayout cardLayout;

	private LoginJDialog login;
	private JPanel panelContenido;

	PanelTicket pt;
	private JToggleButton btnMenu;

	// botones
	private JButton btnInicio;
	private JButton btnRankSem;
	private JButton btnConfig;
	private JButton btnTick;
	private JButton btnCerrarSesion;
	private JButton btnVolverMenu;

	// contenedores para cada idioma
	private PTicketString pts;
	private MUVString muvString;
	private PConfigString pcs;
	private PRankingString prs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ValidacionUser.controlExcepcionIrremediable(() -> {
					MainUserView frame = new MainUserView();
					frame.setVisible(true);
				}, "ERROR SQL: \n? \nCONTACT TECHNICAL SUPPORT", "SQL ERROR", true);

			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public MainUserView() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(500, 300);
		setLocationRelativeTo(null);
		login = new LoginJDialog(this);
		login.setVisible(true);

		pts = new PTicketString(login.getIdioma());
		muvString = new MUVString(login.getIdioma());
		pcs = new PConfigString(login.getIdioma());
		prs = new PRankingString(login.getIdioma());

		contentPane = new JPanel(new BorderLayout());
		setContentPane(contentPane);

		JPanel panelTop = new JPanel(new BorderLayout());
		cargarHeader(panelTop);
		add(panelTop, BorderLayout.NORTH);

		crearMenu();
		contenidoDer();
	}

	// header
	private void cargarHeader(JPanel panelTop) {
		panelTop.setBackground(Color.BLACK);
		GraphicObject go = new GraphicObject();

		// boton idioma
		btnMenu = ControlObjects.crearToggleSuperior(panelTop,
				go.cargarIconoEscalado("/res/img/off_toggle.png", 40, 40, this),
				go.cargarIconoEscalado("/res/img/on_toggle.png", 40, 40, this), "West", "MENU");
		btnMenu.addActionListener(this);
	}

	// cargar menu
	private void crearMenu() {
		// panel totalmente transparente
		panelMenu = new PanelStyle.RoundedPanel(new Color(0, 0, 0), 0);

		panelMenu.setLayout(new GridLayout(6, 1));
		panelMenu.setPreferredSize(new Dimension(0, 0)); // ancho fijo, alto flexible

		// Botones
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
		panelContenido.add(new PanelInicio(), "Inicio");
		panelContenido.add(new PanelTicket("luis03", pts), "Ticket");
		panelContenido.add(new PanelRanking("luis03", prs), "rank");
		panelContenido.add(new PanelConfig("luis03", pcs), "config");
		contentPane.add(panelContenido, BorderLayout.CENTER);
	}

	private void cerrarSesion() {
		dispose();
		main(new String[2]);
	}

	/**
	 * Maneja los eventos de acción generados por los botones de la interfaz de
	 * usuario.
	 * <p>
	 * Este método implementa la interfaz {@code ActionListener} y determina qué
	 * acción ejecutar en función del botón que generó el evento. Dependiendo del
	 * origen, puede alternar el menú, mostrar distintos paneles de contenido,
	 * cerrar la sesión del usuario o regresar al menú principal.
	 * </p>
	 *
	 * <p>
	 * Acciones manejadas por botón:
	 * </p>
	 * <ul>
	 * <li>{@code btnMenu}: Invoca {@code toggleMenu()} para alternar la visibilidad
	 * del menú lateral.</li>
	 * <li>{@code btnInicio}: Muestra el panel "Inicio" mediante
	 * {@code cardLayout.show(panelContenido, "Inicio")}.</li>
	 * <li>{@code btnTick}: Muestra el panel "Ticket".</li>
	 * <li>{@code btnRankSem}: Muestra el panel de ranking ("rank").</li>
	 * <li>{@code btnConfig}: Muestra el panel de configuración del usuario
	 * ("config").</li>
	 * <li>{@code btnCerrarSesion}: Llama a {@code cerrarSesion()} para finalizar la
	 * sesión activa.</li>
	 * <li>{@code btnVolverMenu}: Llama a {@code menuPrincipal()} para regresar al
	 * menú principal.</li>
	 * </ul>
	 *
	 * @param e Evento de acción que contiene información sobre el origen del
	 *          evento.
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
			return;
		}
		if (a == btnInicio) {
			cardLayout.show(panelContenido, "Inicio");
		}
		if (a == btnTick) {
			cardLayout.show(panelContenido, "Ticket");
		}
		if (a == btnRankSem) {
			cardLayout.show(panelContenido, "rank");
		}

		if (a == btnConfig) {
			cardLayout.show(panelContenido, "config");
		}

		if (a == btnCerrarSesion) {
			cerrarSesion();
		}
		if (a == btnVolverMenu) {
			// menuPrincipal();
		}
	}

}
