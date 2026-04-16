package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.DaoXML;
import exportadorXML.ExportadorXML;
import model.Entrada;
import model.Pelicula;
import model.Sala;
import model.Usuario;
import ui.Sala.SalaView;
import ui.admin.Admin;
import ui.catalogo.Catalogo;
import ui.user.MainUserView;

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
    /** Botón para generar en XML */
    private JButton btnExportarXML;

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
                JOptionPane.showMessageDialog(null,
                    "Error de inicialización delMainMenu:\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
                System.err.println("Error al iniciar MainMenu: " + e.getMessage());
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
        setSize(700, 750);
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
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelBotones.setOpaque(false);
        panelBotones.setBorder(new EmptyBorder(20, 0, 20, 0));
        
        // Panel para centrar los botones
        JPanel centradorBotones = new JPanel();
        centradorBotones.setLayout(new GridLayout(6, 1, 0, 12));
        centradorBotones.setOpaque(false);

        btnCatalogo = crearBoton("Film Catalogue");
        btnUsuario  = crearBoton("User Access");
        btnAdmin    = crearBoton("Admin Panel");
        btnSala    = crearBoton("Cinema Rooms");
        btnSalir    = crearBoton("Exit");

        // Color rojo solo para el botón de salir
        btnSalir.setForeground(new Color(0xFF, 0x6B, 0x6B));
        btnSalir.setBorder(BorderFactory.createLineBorder(new Color(0xFF, 0x6B, 0x6B), 2));

        btnExportarXML = crearBoton("Export XML");
        btnExportarXML.setForeground(new Color(0x22, 0xC5, 0x5E));
        btnExportarXML.setBorder(BorderFactory.createLineBorder(new Color(0x22, 0xC5, 0x5E), 2));

        centradorBotones.add(btnCatalogo);
        centradorBotones.add(btnUsuario);
        centradorBotones.add(btnAdmin);
        centradorBotones.add(btnSala);
        centradorBotones.add(btnExportarXML); 
        centradorBotones.add(btnSalir);
        
        panelBotones.add(Box.createHorizontalGlue());
        panelBotones.add(centradorBotones);
        panelBotones.add(Box.createHorizontalGlue());

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
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setBackground(PRIMARY);
        btn.setForeground(ACCENT);
        btn.setBorder(BorderFactory.createLineBorder(ACCENT, 2));
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        // Establecer tamaño preferido y padding
        btn.setPreferredSize(new Dimension(300, 50));
        btn.setMargin(new Insets(10, 15, 10, 15));

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

        if (e.getSource() == btnCatalogo) {
            // Abre el catálogo como modal; al cerrarse, el menú vuelve automáticamente
            Catalogo catalogo;
            try {
                catalogo = new Catalogo();
                catalogo.setModal(true);
                catalogo.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == btnSala) {
            SalaView sala;
            try {
                sala = new SalaView();
                sala.setModal(true);
                sala.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == btnUsuario) {
            // Oculta el MainMenu mientras UserView está abierto
            MainUserView user;
            
            try {
                user = new MainUserView();
                user.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        // user:Administrador pass:tolotolocine2026
        if (e.getSource() == btnAdmin) {
            Admin admin;
            try {
                admin = new Admin();
                admin.setModal(true);
                admin.setVisible(true); 
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == btnExportarXML){
             try {
                DaoXML daoXML = new DaoXML();

                List<Pelicula> peliculas = daoXML.obtenerTodasPeliculas();
                List<Usuario>  usuarios  = daoXML.obtenerTodosUsuarios();
                List<Entrada>  entradas  = daoXML.obtenerTodasEntradas();
                List<Sala>  salas  = daoXML.obtenerTodasSalas();
                

                new File("xml").mkdirs();	
                String ruta = "xml/catalogo.xml";
                File ficheroXML = new File(ruta);
                if (ficheroXML.exists()) {
                    ficheroXML.delete();
                }
                new ExportadorXML().exportarCatalogo(peliculas, usuarios, entradas, ruta,salas );

                JOptionPane.showMessageDialog(this,
                    "XML generado en:\n" + new File(ruta).getAbsolutePath(),
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }

        }

        if (e.getSource() == btnSalir) {
            // Pide confirmación antes de cerrar la aplicación
            int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to exit?",
                "Exit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) System.exit(0);
        }
    }
}