package ui.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import util.validacion.ValidacionUser;

public class LoginJDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JButton btnLogin;
	private String usuario;
	private int intentos = 3;

	// paleta
	private static final Color AZUL = Color.BLUE;
	private static final Color NEGRO = Color.BLACK;

	public LoginJDialog(JFrame parent) {

		super(parent, "Login", true);

		setSize(360, 200);
		setLocationRelativeTo(parent);
		setLayout(new BorderLayout());
		getContentPane().setBackground(NEGRO);

		// cierro la app, si quiere saltar el login
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// panel
		JPanel panel = new JPanel(new GridLayout(2, 2, 12, 12));
		panel.setBorder(new EmptyBorder(20, 20, 20, 20));
		panel.setBackground(NEGRO);

		// etiquetas
		JLabel lblUser = new JLabel("Usuario");
		lblUser.setForeground(AZUL);
		lblUser.setFont(new Font("Consolas", Font.PLAIN, 13));

		JLabel lblPass = new JLabel("Contraseña");
		lblPass.setForeground(AZUL);
		lblPass.setFont(new Font("Consolas", Font.PLAIN, 13));

		// campo del texto
		txtUser = new JTextField();
		estilizarCampo(txtUser);

		// password
		txtPass = new JPasswordField();
		estilizarCampo(txtPass);

		panel.add(lblUser);
		panel.add(txtUser);
		panel.add(lblPass);
		panel.add(txtPass);

		add(panel, BorderLayout.CENTER);

		// botones login
		btnLogin = new JButton("ENTRAR");
		btnLogin.setFont(new Font("Consolas", Font.BOLD, 13));
		btnLogin.setForeground(NEGRO);
		btnLogin.setBackground(AZUL);
		btnLogin.setFocusPainted(false);
		btnLogin.setBorder(new EmptyBorder(8, 30, 8, 30));
		btnLogin.addActionListener(this);

		JPanel p = new JPanel();
		p.setBackground(NEGRO);
		p.setBorder(new EmptyBorder(0, 0, 15, 0));
		p.add(btnLogin);
		add(p, BorderLayout.SOUTH);
	}

	private void estilizarCampo(JTextField campo) {
		campo.setOpaque(false);
		campo.setForeground(AZUL);
		campo.setCaretColor(AZUL);
		campo.setFont(new Font("Consolas", Font.PLAIN, 14));
		campo.setBorder(new LineBorder(AZUL, 1));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String user = txtUser.getText();
		String pass = new String(txtPass.getPassword());
		boolean valido = ValidacionUser.validarUser(user, pass);

		if (valido) {
			usuario = user;
			JOptionPane.showMessageDialog(this, "Bienvenido " + getUsuario(), "Acceso permitido",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();

		} else {
			intentos--;
			JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos\nIntentos restantes: " + intentos,
					"Acceso denegado", JOptionPane.ERROR_MESSAGE);
			if (intentos <= 0) {
				System.exit(0);
			}
		}
	}

	public String getUsuario() {
		return usuario;
	}
}