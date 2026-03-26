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
import javax.swing.border.EmptyBorder;

import ui.user.panel.*;

import dao.DaoUser;

import ui.element.*;

public class MainUserView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelMenu;
	private CardLayout cardLayout;

	private LoginJDialog login;
	private JToggleButton btnIdioma;
	private JPanel panelContenido;

	private JToggleButton btnMenu;

	// botones
	private JButton btnInicio;
	private JButton btnRankSem;
	private JButton btnConfig;
	private JButton btnTick;
	private DaoUser daoUser = new DaoUser();

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
	 * @throws Exception 
	 */
	public MainUserView() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(500, 300);
		setLocationRelativeTo(null);
		//login = new LoginJDialog(this);
		//login.setVisible(true);
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
		// boton menu
		btnIdioma = ControlObjects.crearToggleSuperior(panelTop,
				go.cargarIconoEscalado("/res/img/es_toggle.png", 68, 30, this),
				go.cargarIconoEscalado("/res/img/en_toggle.png", 68, 30, this), "East", "EN/ES");

		btnIdioma.addActionListener(this);

		// boton idioma
		btnMenu = ControlObjects.crearToggleSuperior(panelTop,
				go.cargarIconoEscalado("/res/img/off_toggle.png", 40, 40, this),
				go.cargarIconoEscalado("/res/img/on_toggle.png", 40, 40, this), "West", "MENU");
		btnMenu.addActionListener(this);
	}

	// cargar menu
	private void crearMenu() {

		panelMenu = new JPanel(new GridLayout(4, 1));
		panelMenu.setPreferredSize(new Dimension(0, 0));
		panelMenu.setBackground(Color.BLACK);

		btnInicio = ControlObjects.botonMenu("inicio");
		btnRankSem = ControlObjects.botonMenu("Ranking semanal");
		btnTick = ControlObjects.botonMenu("Ver tickets");
		btnConfig = ControlObjects.botonMenu("Confisguracion");

		btnTick.addActionListener(this);
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
	}
	
	
	private void contenidoDer() throws Exception {
		cardLayout = new CardLayout();
		panelContenido = new JPanel(cardLayout);
		panelContenido.add(new PanelTicket(daoUser.obtenerTicketsUsuario("luis03")), "Ticket");
		
		contentPane.add(panelContenido, BorderLayout.CENTER);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnMenu) {
			toggleMenu();
			return;
		}
		if (e.getSource()==btnTick) {
			cardLayout.show(panelContenido, "Ticket");
		}

	}

}
