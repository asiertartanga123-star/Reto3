package ui.admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.element.ControlObjects;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;

public class Admin extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel titulo;
    private JLabel usuario;
    private JLabel contrasenia;
    private JTextField loginUsuario;
    private JPasswordField loginContrasenia;
    private JButton btnLogin;
    private JButton btnVolver;

    public static void main(String[] args) {

        try {
            Admin dialog = new Admin();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Admin() {

    	// ──────────────── Ventana ───────────────────
        setTitle("TOLOTOLO - ADMINISTRATION OPTIONS");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // ──────────── Panel principal ───────────────
        contentPane = new JPanel();
        contentPane.setBackground(new Color(27, 38, 59));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // ── Colores reutilizables ───────────────────
        Color PRIMARY   = new Color(0x1B, 0x26, 0x3B);
        Color SECONDARY = new Color(0x0D, 0x1B, 0x2A);
        Color TEXT      = Color.WHITE;
        Color ACCENT    = new Color(0x06, 0xB6, 0xD4);

        // ──────────────── Titulo ───────────────────
        titulo = new JLabel("Administration access");
        titulo.setForeground(TEXT);
        titulo.setFont(new Font("Verdana", Font.BOLD, 30));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBounds(10, 23, 564, 56);
        contentPane.add(titulo);

        // Usuario
        
        usuario = new JLabel("User");
        usuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
        usuario.setForeground(TEXT);
        usuario.setBounds(68, 104, 114, 19);
        contentPane.add(usuario);

        loginUsuario = new JTextField();
        loginUsuario.setBounds(68, 137, 465, 32);
        contentPane.add(loginUsuario);

        // Contraseña
        
        contrasenia = new JLabel("Password");
        contrasenia.setFont(new Font("Tahoma", Font.PLAIN, 16));
        contrasenia.setForeground(TEXT);
        contrasenia.setBounds(68, 196, 114, 32);
        contentPane.add(contrasenia);

        loginContrasenia = new JPasswordField();
        loginContrasenia.setBounds(68, 239, 465, 32);
        contentPane.add(loginContrasenia);

        // Botón login
        btnLogin = ui.element.ControlObjects.botonMenu("Login");
        btnLogin.setBounds(68, 350, 120, 30);
        btnLogin.addActionListener(this);
        contentPane.add(btnLogin);

        // Botón volver
        btnVolver = ui.element.ControlObjects.botonMenu("Back");
        btnVolver.setBounds(412, 350, 120, 30);
        btnVolver.addActionListener(this);
        contentPane.add(btnVolver);
    }

    public void actionPerformed(ActionEvent e) {

        // Boton login
        if (e.getSource() == btnLogin) {

            String usuario = loginUsuario.getText();
            String password = new String(loginContrasenia.getPassword());

            if (usuario.equals("Administrador") && password.equals("tolotolocine2026")) {
                Options option = new Options();
                option.setModal(true);
                option.setVisible(true);
                dispose();

            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
            }
        }

        // Boton volver
        if (e.getSource() == btnVolver) {
            dispose(); 
        }
    }
}