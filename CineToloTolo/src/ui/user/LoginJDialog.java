package ui.user;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import dic.user.LoginString;
import ui.element.ControlObjects;
import ui.element.GraphicObject;
import util.validation.ValidacionUser;

public class LoginJDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JTextField txtUser;
	private JPasswordField txtPass;
	private JButton btnLogin;
	private JToggleButton btnMenu;
	private JLabel lblUser;
	private JLabel lblPass;
	private String usuario;
	private int intentos = 3;

	private LoginString logStr;

	private static final Color AZUL = Color.BLUE;
	private static final Color NEGRO = Color.BLACK;

	public LoginJDialog(JFrame parent) {
		super(parent, "Login", true);

		logStr = new LoginString(dic.user.Idioma.ES);

		setSize(460, 250);
		setLocationRelativeTo(parent);
		setLayout(new BorderLayout());
		getContentPane().setBackground(NEGRO);

		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		crearPanelSuperior(parent);
		crearPanelCentral();
	}

	// ===== Toggle de idioma arriba =====
	private void crearPanelSuperior(JFrame parent) {
		JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelTop.setBackground(NEGRO);

		GraphicObject go = new GraphicObject();

		// condicional para mostrar el toggle
		String rutaToggle1 = logStr.getIdioma() == dic.user.Idioma.EN ? "/res/img/es_toggle.png"
				: "/res/img/en_toggle.png";

		String rutaToggle2 = logStr.getIdioma() == dic.user.Idioma.EN ? "/res/img/en_toggle.png"
				: "/res/img/es_toggle.png";

		btnMenu = ControlObjects.crearToggleSuperior(panelTop, go.cargarIconoEscalado(rutaToggle1, 68, 30, parent),
				go.cargarIconoEscalado(rutaToggle2, 68, 30, parent), "East", "EN/ES");
		btnMenu.addActionListener(this);

		add(panelTop, BorderLayout.NORTH);
	}

	// --Panel grid sin mas--
	private void crearPanelCentral() {
		JPanel panelCentral = new JPanel(new GridLayout(3, 2, 10, 10));
		panelCentral.setBackground(NEGRO);
		panelCentral.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

		lblUser = new JLabel(logStr.labelUser());
		lblUser.setForeground(AZUL);
		lblUser.setFont(new Font("Consolas", Font.PLAIN, 16));
		txtUser = new JTextField();
		estiloTextField(txtUser);

		lblPass = new JLabel(logStr.labelPass());
		lblPass.setForeground(AZUL);
		lblPass.setFont(new Font("Consolas", Font.PLAIN, 16));
		txtPass = new JPasswordField();
		estiloTextField(txtPass);

		btnLogin = ControlObjects.botonMenu(logStr.buttonLogin());
		btnLogin.addActionListener(this);

		panelCentral.add(lblUser);
		panelCentral.add(txtUser);
		panelCentral.add(lblPass);
		panelCentral.add(txtPass);
		panelCentral.add(new JLabel()); // vacio solo es visual :/
		panelCentral.add(btnLogin);

		add(panelCentral, BorderLayout.CENTER);
	}

	private void estiloTextField(JTextField campo) {
		campo.setOpaque(false);
		campo.setForeground(AZUL);
		campo.setCaretColor(AZUL);
		campo.setFont(new Font("Consolas", Font.PLAIN, 14));
		campo.setBorder(new LineBorder(AZUL, 1));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogin) {
			String user = txtUser.getText();
			String pass = new String(txtPass.getPassword());

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