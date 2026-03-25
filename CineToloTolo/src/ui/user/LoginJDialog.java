package ui.user;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dic.user.LoginString;
import ui.element.*;
import util.validation.ValidacionUser;

public class LoginJDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	// elementos
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JButton btnLogin;
	private JToggleButton btnMenu;
	private JLabel lblUser;
	private JLabel lblPass;

	// contador de intentos
	private String usuario;
	private int intentos = 3;

	private LoginString logStr;

	// paleta de colores
	private static final Color HEADER_BG = new Color(0, 0, 0, 120);
	private static final Color LOGIN_BG = new Color(255, 255, 255, 15);
	private static final Color LABEL_COLOR = new Color(255, 255, 255);

	private static final Color TEXTFIELD_BG = new Color(255, 255, 255, 150);
	private static final Color TEXTFIELD_TEXT = new Color(0, 0, 0);

	public LoginJDialog(JFrame parent) {
		super(parent, "Login", true);

		logStr = new LoginString(dic.user.Idioma.EN);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screen);
		setLocationRelativeTo(null);

		setContentPane(new PanelStyle.BackgroundPanel("/res/img/background_login_user.jpg"));
		getContentPane().setLayout(new BorderLayout());

		crearHeader(parent);
		crearCentro();
	}

	private void crearHeader(JFrame parent) {
		JPanel header = crearPanelTransparente(HEADER_BG);
		header.setLayout(new BorderLayout());
		header.setPreferredSize(new Dimension(0, 60));
		header.setBorder(new EmptyBorder(10, 10, 10, 20));

		JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		right.setOpaque(false);

		GraphicObject go = new GraphicObject();

		String ruta1 = logStr.getIdioma() == dic.user.Idioma.EN ? "/res/img/es_toggle.png" : "/res/img/en_toggle.png";

		String ruta2 = logStr.getIdioma() == dic.user.Idioma.EN ? "/res/img/en_toggle.png" : "/res/img/es_toggle.png";

		btnMenu = ControlObjects.crearToggleSuperior(right, go.cargarIconoEscalado(ruta1, 68, 30, parent),
				go.cargarIconoEscalado(ruta2, 68, 30, parent), "East", "EN/ES");
		btnMenu.addActionListener(this);

		header.add(right, BorderLayout.EAST);
		add(header, BorderLayout.NORTH);
	}

	private void crearCentro() {
		JPanel center = new JPanel(new GridBagLayout());
		center.setOpaque(false);

		center.add(crearLoginPanel());
		add(center, BorderLayout.CENTER);
	}

	private JPanel crearLoginPanel() {
		JPanel login = crearPanelTransparente(LOGIN_BG);
		login.setLayout(new GridLayout(3, 2, 15, 15));
		login.setBorder(new EmptyBorder(30, 40, 30, 40));
		login.setPreferredSize(new Dimension(420, 220));

		lblUser = crearLabel(logStr.labelUser());
		txtUser = new JTextField();
		estiloTextField(txtUser);

		lblPass = crearLabel(logStr.labelPass());
		txtPass = new JPasswordField();
		estiloTextField(txtPass);

		btnLogin = ControlObjects.botonMenu(logStr.buttonLogin());
		btnLogin.addActionListener(this);

		login.add(lblUser);
		login.add(txtUser);
		login.add(lblPass);
		login.add(txtPass);
		login.add(new JLabel());
		login.add(btnLogin);

		return login;
	}

	private JLabel crearLabel(String texto) {
		JLabel lbl = new JLabel(texto);
		lbl.setForeground(LABEL_COLOR);
		lbl.setFont(new Font("Consolas", Font.PLAIN, 16));
		return lbl;
	}

	private void estiloTextField(JTextField campo) {
		campo.setOpaque(true);
		campo.setBackground(TEXTFIELD_BG);
		campo.setForeground(TEXTFIELD_TEXT);
		campo.setCaretColor(TEXTFIELD_TEXT);
		campo.setFont(new Font("Consolas", Font.PLAIN, 14));
		campo.setBorder(new EmptyBorder(10, 14, 10, 14));
	}

	private JPanel crearPanelTransparente(Color bg) {
		return new PanelStyle.RoundedPanel(bg, 25);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnLogin) {
			String user = txtUser.getText();
			
			String pass = new String(txtPass.getPassword());
			
			if (user == null || pass == null || user.isEmpty() || pass.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Agrega todos los campos","campo vacio",JOptionPane.WARNING_MESSAGE);
				return;
			}

			boolean valido = ValidacionUser.validarUser(user, pass);

			if (valido) {
				usuario = user;
				JOptionPane.showMessageDialog(this, logStr.mensajeOk() + usuario, logStr.tituloOk(),
						JOptionPane.INFORMATION_MESSAGE);
				dispose();
			} else {
				intentos--;
				JOptionPane.showMessageDialog(this, logStr.mensajeError() + "\n" + logStr.intentos() + intentos,
						logStr.tituloError(), JOptionPane.ERROR_MESSAGE);
				if (intentos <= 0)
					System.exit(0);
			}
		}

		if (e.getSource() == btnMenu) {
			if (logStr.getIdioma() == dic.user.Idioma.EN)
				logStr.setIdioma(dic.user.Idioma.ES);
			else
				logStr.setIdioma(dic.user.Idioma.EN);

			lblUser.setText(logStr.labelUser());
			lblPass.setText(logStr.labelPass());
			btnLogin.setText(logStr.getIdioma() == dic.user.Idioma.ES ? "ENTRAR" : "LOGIN");
		}
	}

	public String getUsuario() {
		return usuario;
	}

	public dic.user.Idioma getIdioma() {
		return logStr.getIdioma();
	}
}