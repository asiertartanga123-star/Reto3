package ui.catalogo;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DetallePelicula extends JDialog {
	
	private JLabel labelTitulo;
	private JTextArea txtSinopsis;
	private JButton btnCerrar;

    public DetallePelicula(JDialog parent, int id, String titulo, String genero,
                           int valoracion, int duracion, String director, String sinopsis) {

        super(parent, "Detalle — " + titulo, true);
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
        panel.add(fila("Género:",    genero,             PRIMARY, TEXT, ACCENT, y)); y += 45;
        panel.add(fila("Duración:",  duracion + " min",  PRIMARY, TEXT, ACCENT, y)); y += 45;
        panel.add(fila("Valoración:", valoracion + " / 5", PRIMARY, TEXT, ACCENT, y)); y += 45;

        // ── Sinopsis ──
        JLabel lblSinLbl = new JLabel("Sinopsis:");
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
        btnCerrar = ui.element.ControlObjects.botonMenu("Cerrar");
        btnCerrar.setBounds(376, 342, 90, 28);
        btnCerrar.setBackground(ACCENT);
        btnCerrar.setForeground(SECONDARY);
        btnCerrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnCerrar.setFocusPainted(false);
        btnCerrar.setBorderPainted(false);
        btnCerrar.addActionListener(e -> dispose());
        panel.add(btnCerrar);
    }

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