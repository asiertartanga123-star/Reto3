package ui.catalogo;

import java.awt.*;
import javax.swing.*;

/**
 * Diálogo que muestra los detalles de una película específica.
 * Incluye información como título, director, género, duración, valoración y sinopsis.
 *
 * @author Hodei
 */
public class DetallePelicula extends JDialog {
	
	private JLabel labelTitulo;
	private JTextArea txtSinopsis;
	private JButton btnCerrar;

    /**
     * Constructor de la clase DetallePelicula.
     * Crea un diálogo modal con los detalles de la película proporcionada.
     *
     * @param parent El diálogo padre para centrar el nuevo diálogo.
     * @param id El ID de la película.
     * @param titulo El título de la película.
     * @param genero El género de la película.
     * @param valoracion La valoración de la película (de 1 a 5).
     * @param duracion La duración de la película en minutos.
     * @param director El director de la película.
     * @param sinopsis La sinopsis de la película.
     */
    public DetallePelicula(JDialog parent, int id, String titulo, String genero,
                           int valoracion, int duracion, String director, String sinopsis) {

        super(parent, "Details — " + titulo, true);
        setSize(503, 428);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        Color PRIMARY   = new Color(0x1B, 0x26, 0x3B);
        Color SECONDARY = new Color(0x0D, 0x1B, 0x2A);
        Color TEXT      = Color.WHITE;
        Color ACCENT    = new Color(0x06, 0xB6, 0xD4);

        JPanel panel = new JPanel(null);
        panel.setBackground(SECONDARY);
        setContentPane(panel);

        // ── Título ──
        labelTitulo = new JLabel();
        labelTitulo.setForeground(ACCENT);
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        labelTitulo.setBounds(20, 15, 460, 30);
        panel.add(labelTitulo);

        // ── Campos ──
        int y = 60;
        panel.add(fila("Director:",  director,           PRIMARY, TEXT, ACCENT, y)); y += 45;
        panel.add(fila("Genre:",    genero,             PRIMARY, TEXT, ACCENT, y)); y += 45;
        panel.add(fila("Duration:",  duracion + " min",  PRIMARY, TEXT, ACCENT, y)); y += 45;
        panel.add(fila("Rate:", valoracion + " / 5", PRIMARY, TEXT, ACCENT, y)); y += 45;

        // ── Sinopsis ──
        JLabel lblSinLbl = new JLabel("Synopsis:");
        lblSinLbl.setForeground(ACCENT);
        lblSinLbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblSinLbl.setBounds(20, y, 100, 20);
        panel.add(lblSinLbl);

        txtSinopsis = new JTextArea(sinopsis);
        txtSinopsis.setBackground(PRIMARY);
        txtSinopsis.setForeground(TEXT);
        txtSinopsis.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtSinopsis.setEditable(false);
        txtSinopsis.setLineWrap(true);
        txtSinopsis.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(txtSinopsis);
        scroll.setBounds(20, y + 22, 455, 70);
        scroll.setBorder(BorderFactory.createLineBorder(ACCENT));
        panel.add(scroll);

        // ── Botón cerrar ──
        btnCerrar = ui.element.ControlObjects.botonMenu("Close");
        btnCerrar.setBounds(376, 342, 90, 28);
        btnCerrar.setBackground(ACCENT);
        btnCerrar.setForeground(SECONDARY);
        btnCerrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnCerrar.setFocusPainted(false);
        btnCerrar.setBorderPainted(false);
        btnCerrar.addActionListener(e -> dispose());
        panel.add(btnCerrar);
    }

    /**
     * Método auxiliar para crear un panel con una fila de etiqueta y valor.
     * Utilizado para mostrar información de la película en formato de fila.
     *
     * @param etiqueta La etiqueta a mostrar (ej. "Director:").
     * @param valor El valor correspondiente a la etiqueta.
     * @param bg El color de fondo del panel.
     * @param text El color del texto del valor.
     * @param accent El color del texto de la etiqueta.
     * @param y La posición vertical del panel.
     * @return Un JPanel configurado con la etiqueta y el valor.
     */
    // ── Helper: fila etiqueta + valor ──────────────────────────────────
    private JPanel fila(String etiqueta, String valor,
                        Color bg, Color text, Color accent, int y) {
        JPanel p = new JPanel(null);
        p.setBackground(bg);
        p.setBounds(20, y, 455, 35);

        JLabel lEtiq = new JLabel(etiqueta);
        lEtiq.setForeground(accent);
        lEtiq.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lEtiq.setBounds(8, 8, 100, 20);

        JLabel lVal = new JLabel(valor);
        lVal.setForeground(text);
        lVal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lVal.setBounds(115, 8, 330, 20);

        p.add(lEtiq);
        p.add(lVal);
        return p;
    }
}