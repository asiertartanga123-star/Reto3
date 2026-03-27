package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ui.catalogo.Catalogo;
import ui.user.MainUserView;
import util.validation.ValidacionUser;

/**
 * Ventana principal de la aplicación CINE TOLOTOLO.
 * <p>
 * Actúa como menú de navegación central desde el que se accede
 * al catálogo de películas, al área de usuario y al panel de administración.
 * </p>
 *
 * @author TuNombre
 * @version 1.0
 */
public class MainMenu extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    // ── Colores del proyecto ──────────────────────────────
    /** Color primario: azul oscuro. */
    private static final Color PRIMARY   = new Color(0x1B, 0x26, 0x3B);
    /** Color secundario: azul más oscuro, usado de fondo. */
    private static final Color SECONDARY = new Color(0x0D, 0x1B, 0x2A);
    /** Color de acento: cian, usado en botones y bordes. */
    private static final Color ACCENT    = new Color(0x06, 0xB6, 0xD4);
    /** Color de texto principal: blanco. */
    private static final Color TEXT      = Color.WHITE;

    // ── Componentes ───────────────────────────────────────
    /** Panel raíz del frame. */
    private JPanel contentPane;

    /** Botón para abrir el catálogo de películas. */
    private JButton btnCatalogo;

    /** Botón para acceder al área de usuario. */
    private JButton btnUsuario;

    /** Botón para abrir el panel de administración. */
    private JButton btnAdmin;

    /** Botón para salir de la aplicación. */
    private JButton btnSalir;

    private JButton btnSala;

    private  JLabel footer;

    /**
     * Punto de entrada de la aplicación.
     * Lanza el {@code MainMenu} en el hilo de eventos de Swing.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
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

    /**
     * Construye y configura la ventana principal.
     * <p>
     * Inicializa los paneles, el logo, el título y los botones de navegación.
     * </p>
     */
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
        JPanel panelBotones = new JPanel(new GridLayout(5, 1, 0, 15));
        panelBotones.setOpaque(false);

        btnCatalogo = crearBoton("Film Catalogue");
        btnUsuario  = crearBoton("User Access");
        btnAdmin    = crearBoton("Admin Panel");
        btnSala    = crearBoton("Cinema Rooms");
        btnSalir    = crearBoton("Exit");

        // Color rojo solo para el botón de salir
        btnSalir.setForeground(new Color(0xFF, 0x6B, 0x6B));
        btnSalir.setBorder(BorderFactory.createLineBorder(new Color(0xFF, 0x6B, 0x6B), 1));

        panelBotones.add(btnCatalogo);
        panelBotones.add(btnUsuario);
        panelBotones.add(btnAdmin);
        panelBotones.add(btnSala);
        panelBotones.add(btnSalir);

        contentPane.add(panelBotones, BorderLayout.CENTER);

        // ── Pie ───────────────────────────────────────────
        footer = new JLabel("© 2025 CineToloTolo", SwingConstants.CENTER);
        footer.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        footer.setForeground(new Color(120, 140, 160));
        contentPane.add(footer, BorderLayout.SOUTH);
    }

    /**
     * Crea un botón con el estilo visual del proyecto.
     * <p>
     * Aplica colores, fuente, borde y efecto hover. Registra automáticamente
     * el {@code ActionListener} del frame.
     * </p>
     *
     * @param texto etiqueta que mostrará el botón
     * @return {@code JButton} con el estilo del proyecto
     */
    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBackground(PRIMARY);
        btn.setForeground(ACCENT);
        btn.setBorder(BorderFactory.createLineBorder(ACCENT, 1));
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Efecto hover: invierte los colores al pasar el ratón
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

    /**
     * Gestiona los eventos de los botones del menú principal.
     * <ul>
     *   <li>{@code btnCatalogo}: abre el catálogo de películas como diálogo modal.</li>
     *   <li>{@code btnUsuario}: oculta el menú principal y abre la vista de usuario.</li>
     *   <li>{@code btnAdmin}: muestra un mensaje provisional (pendiente de implementar).</li>
     *   <li>{@code btnSalir}: solicita confirmación y cierra la aplicación.</li>
     * </ul>
     *
     * @param e evento de acción generado por el botón pulsado
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == btnCatalogo) {
            // Abre el catálogo como modal; al cerrarse, el menú vuelve automáticamente
            Catalogo catalogo = new Catalogo();
            catalogo.setModal(true);
            catalogo.setVisible(true);
        }

        if (src == btnUsuario) {
            // Oculta el MainMenu mientras UserView está abierto
//              ValidacionUser.controlExcepcionIrremediable(() -> {
//                MainUserView userView = new MainUserView();
//                userView.setVisible(true);
//                setVisible(false);
//            }, "SQL ERROR", "Error", true);
        }

        if (src == btnAdmin) {
            // TODO: sustituir por la ventana de administración cuando esté implementada
            JOptionPane.showMessageDialog(this,
                "Admin panel coming soon.",
                "Info", JOptionPane.INFORMATION_MESSAGE);
        }

        if (src == btnSalir) {
            // Pide confirmación antes de cerrar la aplicación
            int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to exit?",
                "Exit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) System.exit(0);
        }
    }
}