package ui.user;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import ui.user.panel.*;

import dao.DaoUser;
import dic.user.MUVString;
import dic.user.PTicketString;
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
	private DaoUser daoUser = new DaoUser();
	// contenedores para cada idioma
	private PTicketString pts;
	private MUVString muvString;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUserView frame = new MainUserView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
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
		//login.setVisible(true);

		pts = new PTicketString(login.getIdioma());
		muvString = new MUVString(login.getIdioma());

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
		// Panel menú transparente tipo glass

		panelMenu = new PanelStyle.RoundedPanel(new Color(0, 0, 0), 0);

		panelMenu.setLayout(new GridLayout(4, 1));
		panelMenu.setPreferredSize(new Dimension(0, 0)); // ancho fijo, alto flexible

		// Botones
		btnInicio = ControlObjects.botonMenu(muvString.getInicio());
		btnRankSem = ControlObjects.botonMenu(muvString.getRankingSemanal());
		btnTick = ControlObjects.botonMenu(muvString.getVerTickets());
		btnConfig = ControlObjects.botonMenu(muvString.getConfiguracion());

		btnInicio.addActionListener(this);
		btnTick.addActionListener(this);
		btnRankSem.addActionListener(this);
		btnConfig.addActionListener(this);

		panelMenu.add(btnInicio);
		panelMenu.add(btnRankSem);
		panelMenu.add(btnTick);
		panelMenu.add(btnConfig);

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
		panelContenido.add(new PanelTicket(daoUser.obtenerTicketsUsuario("luis03"), pts), "Ticket");
		panelContenido.add(new PanelRanking(daoUser.mostrarRanking("luis03", LocalDate.now()), "luis03"), "rank");
		panelContenido.add(new PanelConfig("luis03"), "config");
		contentPane.add(panelContenido, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnMenu) {
			toggleMenu();
			return;
		}
		if (e.getSource() == btnInicio) {
			cardLayout.show(panelContenido, "Inicio");
		}
		if (e.getSource() == btnTick) {
			cardLayout.show(panelContenido, "Ticket");
		}
		if (e.getSource() == btnRankSem) {
			cardLayout.show(panelContenido, "rank");
		}

		if (e.getSource() == btnConfig) {
			cardLayout.show(panelContenido, "config");
		}
	}

}
