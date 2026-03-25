package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ui.catalogo.Catalogo;
import ui.user.MainUserView;
import util.validation.ValidacionUser;

public class MainMenu extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    // ── Colores del proyecto ──────────────────────────────
    private static final Color PRIMARY   = new Color(0x1B, 0x26, 0x3B);
    private static final Color SECONDARY = new Color(0x0D, 0x1B, 0x2A);
    private static final Color ACCENT    = new Color(0x06, 0xB6, 0xD4);
    private static final Color TEXT      = Color.WHITE;

    // ── Componentes ───────────────────────────────────────
    private JPanel contentPane;
    private JButton btnCatalogo;
    private JButton btnUsuario;
    private JButton btnAdmin;
    private JButton btnSalir;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainMenu frame = new MainMenu();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MainMenu() {
        setTitle("CINE TOLOTOLO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // ── Panel principal ───────────────────────────────
        contentPane = new JPanel();
        contentPane.setBackground(SECONDARY);
        contentPane.setBorder(new EmptyBorder(30, 60, 30, 60));
        contentPane.setLayout(new BorderLayout(0, 30));
        setContentPane(contentPane);

        // ── Logo / Título ─────────────────────────────────
        JPanel panelTitulo = new JPanel(new GridLayout(2, 1, 0, 5));
        panelTitulo.setOpaque(false);

        JLabel logo = new JLabel("🎬", SwingConstants.CENTER);
        logo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 60));
        logo.setForeground(ACCENT);

        JLabel titulo = new JLabel("CINE TOLOTOLO", SwingConstants.CENTER);
        titulo.setFont(new Font("Verdana", Font.BOLD, 32));
        titulo.setForeground(TEXT);

        panelTitulo.add(logo);
        panelTitulo.add(titulo);
        contentPane.add(panelTitulo, BorderLayout.NORTH);

        // ── Panel de botones ──────────────────────────────
        JPanel panelBotones = new JPanel(new GridLayout(4, 1, 0, 15));
        panelBotones.setOpaque(false);

        btnCatalogo = crearBoton("🎥  Film Catalogue");
        btnUsuario  = crearBoton("👤  User Access");
        btnAdmin    = crearBoton("🛠  Admin Panel");
        btnSalir    = crearBoton("🚪  Exit");

        // Color rojo solo para el botón de salir
        btnSalir.setForeground(new Color(0xFF, 0x6B, 0x6B));
        btnSalir.setBorder(BorderFactory.createLineBorder(new Color(0xFF, 0x6B, 0x6B), 1));

        panelBotones.add(btnCatalogo);
        panelBotones.add(btnUsuario);
        panelBotones.add(btnAdmin);
        panelBotones.add(btnSalir);

        contentPane.add(panelBotones, BorderLayout.CENTER);

        // ── Pie ───────────────────────────────────────────
        JLabel footer = new JLabel("© 2025 CineToloTolo", SwingConstants.CENTER);
        footer.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        footer.setForeground(new Color(120, 140, 160));
        contentPane.add(footer, BorderLayout.SOUTH);
    }

    // ── Fabrica botones con el estilo del proyecto ────────
    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBackground(PRIMARY);
        btn.setForeground(ACCENT);
        btn.setBorder(BorderFactory.createLineBorder(ACCENT, 1));
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Hover effect
        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                btn.setBackground(ACCENT);
                btn.setForeground(SECONDARY);
            }
            @Override public void mouseExited(MouseEvent e) {
                btn.setBackground(PRIMARY);
                btn.setForeground(ACCENT);
            }
        });

        btn.addActionListener(this);
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == btnCatalogo) {
            Catalogo catalogo = new Catalogo();
            catalogo.setModal(true);
            catalogo.setVisible(true);
        }

        if (src == btnUsuario) {
            ValidacionUser.controlExcepcionIrremediable(() -> {
                MainUserView userView = new MainUserView();
                userView.setVisible(true);
            }, "SQL ERROR", "Error", true);
        }

        if (src == btnAdmin) {
            // Sustituye por tu ventana de admin cuando la tengas
            JOptionPane.showMessageDialog(this,
                "Admin panel coming soon.",
                "Info", JOptionPane.INFORMATION_MESSAGE);
        }

        if (src == btnSalir) {
            int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to exit?",
                "Exit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) System.exit(0);
        }
    }
}
