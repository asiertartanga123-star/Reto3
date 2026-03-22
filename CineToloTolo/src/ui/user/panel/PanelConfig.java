package ui.user.panel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dao.DaoUser;
import dic.user.PConfigString;
import model.Usuario;
import ui.element.*;
import util.validation.ValidacionUser;

public class PanelConfig extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static final Color CARD = new Color(255, 255, 255, 40);
	private static final Color TEXTO = new Color(255, 255, 255, 220);
	private static final Color TEXTO_SEC = new Color(200, 200, 200);

	private JTextField tfNombre;
	private JTextField tfCorreo;
	private JPasswordField tfPass1;
	private JPasswordField tfPass2;

	private JButton btnGuardar;

	private Usuario user;
	private DaoUser daoUser = new DaoUser();
	private PConfigString pcs;
	private boolean cambios = false;

	public PanelConfig(String userName, PConfigString pcs) throws Exception {
		this.pcs = pcs;
		user = daoUser.obtenerUsuario(userName);

		setLayout(new GridBagLayout());
		setBackground(Color.BLACK);
		setBorder(new EmptyBorder(20, 20, 20, 20));

		construirUI();
	}

	private void construirUI() {

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 1.0;

		gbc.gridx = 0;
		gbc.weightx = 0.3;
		add(parteIzq(), gbc);

		gbc.gridx = 1;
		gbc.weightx = 0.7;
		add(parteDer(), gbc);
	}

	private JPanel parteIzq() {

		PanelStyle.RoundedPanel panel = new PanelStyle.RoundedPanel(CARD, 20);
		panel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.insets = new Insets(10, 10, 10, 10);

		gbc.gridy = 0;
		JLabel avatar = new JLabel(user.getUsuario().substring(0, 2).toUpperCase(), SwingConstants.CENTER);
		avatar.setOpaque(true);
		avatar.setBackground(new Color(0, 120, 255));
		avatar.setForeground(Color.WHITE);
		avatar.setPreferredSize(new Dimension(60, 60));
		panel.add(avatar, gbc);

		gbc.gridy++;
		JLabel lblUser = new JLabel(user.getUsuario());
		lblUser.setForeground(TEXTO);
		panel.add(lblUser, gbc);

		gbc.gridy++;
		JLabel lblCorreo = new JLabel(user.getCorreo());
		lblCorreo.setForeground(TEXTO_SEC);
		panel.add(lblCorreo, gbc);

		return panel;
	}

	private JPanel parteDer() {

		PanelStyle.RoundedPanel panel = new PanelStyle.RoundedPanel(CARD, 20);
		panel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;

		gbc.gridy = 0;
		JLabel titulo = new JLabel(pcs.getConfigCuenta());
		titulo.setForeground(TEXTO);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		panel.add(titulo, gbc);

		gbc.gridy++;
		tfNombre = crearCampo(panel, gbc, pcs.getNombre(), user.getNombre());

		gbc.gridy++;
		tfCorreo = crearCampo(panel, gbc, pcs.getCorreo(), user.getCorreo());

		gbc.gridy++;
		tfPass1 = new JPasswordField();
		estilizarCampo(tfPass1);
		panel.add(crearFila(pcs.getNuevaContrasena(), tfPass1), gbc);

		gbc.gridy++;
		tfPass2 = new JPasswordField();
		estilizarCampo(tfPass2);
		panel.add(crearFila(pcs.getRepetirContrasena(), tfPass2), gbc);

		gbc.gridy++;
		btnGuardar = ControlObjects.botonMenu(pcs.getGuardarCambios());
		btnGuardar.addActionListener(this);
		btnGuardar.setPreferredSize(new Dimension(150, 50));
		panel.add(btnGuardar, gbc);

		return panel;
	}

	private JTextField crearCampo(JPanel panel, GridBagConstraints gbc, String label, String valor) {
		JTextField tf = new JTextField(valor);
		estilizarCampo(tf);
		panel.add(crearFila(label, tf), gbc);
		return tf;
	}

	private JPanel crearFila(String label, JComponent field) {

		JPanel fila = new JPanel(new BorderLayout());
		fila.setOpaque(false);

		JLabel lbl = new JLabel(label);
		lbl.setForeground(TEXTO_SEC);

		fila.add(lbl, BorderLayout.NORTH);
		fila.add(field, BorderLayout.CENTER);

		return fila;
	}

	private void estilizarCampo(JTextField tf) {
		tf.setBackground(new Color(50, 50, 50));
		tf.setForeground(TEXTO);
		tf.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnGuardar) {
			guardar();
		}
	}

	private void guardar() {

		String nuevoNombre = tfNombre.getText();
		String nuevoCorreo = tfCorreo.getText();
		String pass1 = new String(tfPass1.getPassword());
		String pass2 = new String(tfPass2.getPassword());

		// implementar la logica, para guardar
		if (!user.getNombre().equals(nuevoNombre)) {
			ValidacionUser.controlExcepcionIrremediable(() -> {
				if (daoUser.actualizarUsuario(user)) {
					JOptionPane.showMessageDialog(this, pcs.getPasswordUpdated());
					cambios = true;
				} else
					cambios = false;

			}, pcs.getErrorActualizacion(), "ERROR USER UPDATE", false);
		}

		if (!user.getCorreo().equals(nuevoCorreo)) {
			ValidacionUser.controlExcepcionIrremediable(() -> {
				if (daoUser.actualizarUsuario(user)) {
					JOptionPane.showMessageDialog(this, pcs.getPasswordUpdated());
					cambios = true;
				} else
					cambios = false;

			}, pcs.getErrorActualizacion(), "ERROR USER UPDATE", false);
		}

		if (!pass1.isEmpty() || !pass2.isEmpty()) {
			if (pass1.equals(pass2)) {
				user.setContrasena(pass2);
				// try catch, que controla excepciones
				ValidacionUser.controlExcepcionIrremediable(() -> {
					if (daoUser.actualizarUsuario(user)) {
						JOptionPane.showMessageDialog(this, pcs.getPasswordUpdated());
						cambios = true;
					} else
						cambios = false;

				}, pcs.getErrorActualizacion(), "ERROR UPDATE USER", false);

			} else {
				// TransparentOptionPane.showMessage(this, "Las contraseñas no coinciden");
				JOptionPane.showMessageDialog(this, pcs.getPasswordsDontMatch());
				return;
			}
		}

		if (!cambios) {
			JOptionPane.showMessageDialog(this, pcs.getNoChanges());
		} else {
			JOptionPane.showMessageDialog(this, pcs.getChangesSaved());
		}
	}
}