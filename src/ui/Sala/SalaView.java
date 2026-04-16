package ui.Sala;

import dao.DaoSalas;
import exception.SalaException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.Sala;

/**
 * Clase que representa la vista de salas en la aplicación de gestión de cine.
 * Permite visualizar, buscar, borrar y gestionar salas.
 * @author asier
 */
public class SalaView extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	/**
	 * Constantes de colores para la paleta de la interfaz de usuario.
	 */
	// =============================================
	// PALETA
	// =============================================
	private static final Color BG = new Color(8, 9, 13);
	private static final Color S1 = new Color(13, 17, 23);
	private static final Color S2 = new Color(17, 24, 39);
	private static final Color S3 = new Color(26, 34, 54);
	private static final Color BLUE = new Color(37, 99, 235);
	private static final Color BLUE3 = new Color(96, 165, 250);
	private static final Color TEXT = new Color(241, 245, 249);
	private static final Color TEXT2 = new Color(148, 163, 184);
	private static final Color TEXT3 = new Color(71, 85, 105);
	private static final Color BORDER = new Color(59, 130, 246, 30);
	private static final Color BORDER2 = new Color(59, 130, 246, 64);
	private static final Color DANGER = new Color(244, 63, 94);

	/**
	 * Componentes de la interfaz de usuario.
	 */
	// =============================================
	// COMPONENTES
	// =============================================
	private JPanel contentPane;
	private JTable tabla;
	private DefaultTableModel modelo;
	private JButton btnColor;
	private JTextField textField;
	private JButton btnVer;
	private JButton btnBorrar;
	private JButton btnSalas;
	private JButton btnx;

	/**
	 * Método principal para ejecutar la aplicación.
	 * @param args argumentos de línea de comandos (no utilizados).
	 */
	// =============================================
	// MAIN
	// =============================================
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			SalaView dialog = new SalaView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructor de la clase SalaView.
	 * Inicializa la ventana en pantalla completa y construye la interfaz de usuario.
	 */
	// =============================================
	// CONSTRUCTOR — PANTALLA COMPLETA
	// =============================================
	public SalaView() {
		// Ocupa toda la pantalla
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screen.width, screen.height-40);
		setTitle("Vista de la tabla");

		// Layout dinámico (no null)
		contentPane = new JPanel(new BorderLayout(0, 0));
		contentPane.setBackground(BG);
		setContentPane(contentPane);

		buildUI();
	}

	/**
	 * Construye la interfaz de usuario de la ventana.
	 * Configura los paneles, tabla, botones y demás componentes.
	 */
	// =============================================
	// CONSTRUCCIÓN DE LA UI
	// =============================================
	private void buildUI() {

		// ── NORTE: barra superior ──────────────────
		JPanel norte = new JPanel(new BorderLayout());
		norte.setBackground(S2);
		norte.setBorder(new MatteBorder(0, 0, 1, 0, BORDER));
		norte.setPreferredSize(new Dimension(0, 52));

		// Lado izquierdo: botón volver
		JPanel izq = new JPanel(new FlowLayout(FlowLayout.LEFT, 14, 10));
		izq.setOpaque(false);

		btnx = new JButton();
		try {
			btnx.setIcon(new ImageIcon(new ImageIcon(SalaView.class.getResource("/res/img/5037135_100x100.png"))
					.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH)));
			btnx.setIcon(new ImageIcon(new ImageIcon(SalaView.class.getResource("../res/img/5037135_100x100.png"))
					.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH)));
		} catch (Exception ex) {
			btnx.setText("←");
			btnx.setForeground(TEXT2);
			btnx.setFont(new Font("SansSerif", Font.BOLD, 16));
		}
		btnx.setBorderPainted(false);
		btnx.setContentAreaFilled(false);
		btnx.setFocusPainted(false);
		btnx.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnx.addActionListener(this);
		izq.add(btnx);
		norte.add(izq, BorderLayout.WEST);

		// Centro: título
		JLabel titulo = new JLabel("SALA VIEW — Cinema Management", JLabel.CENTER);
		titulo.setForeground(TEXT3);
		titulo.setFont(new Font("Monospaced", Font.PLAIN, 11));
		norte.add(titulo, BorderLayout.CENTER);

		// Lado derecho: badge de ocupación
		btnColor = new JButton("% of ocupaction") {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(getBackground());
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
				g2.dispose();
				super.paintComponent(g);
			}

			@Override
			protected void paintBorder(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(BORDER2);
				g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);
				g2.dispose();
			}
		};
		btnColor.setBackground(S3);
		btnColor.setForeground(TEXT3);
		btnColor.setFont(new Font("Monospaced", Font.PLAIN, 11));
		btnColor.setContentAreaFilled(false);
		btnColor.setFocusPainted(false);
		btnColor.setBorderPainted(false);

		JPanel der = new JPanel(new FlowLayout(FlowLayout.RIGHT, 14, 10));
		der.setOpaque(false);
		der.add(btnColor);
		norte.add(der, BorderLayout.EAST);

		contentPane.add(norte, BorderLayout.NORTH);

		// ── CENTRO: tabla ─────────────────────────
		String[] columnas = { "Num_Sala", "Aforo", "Tipo_pantalla" };
		modelo = new DefaultTableModel(columnas, 0) {
			@Override
			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};

		tabla = new JTable(modelo);
		tabla.setBackground(S1);
		tabla.setForeground(TEXT);
		tabla.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tabla.setRowHeight(42);
		tabla.setShowGrid(false);
		tabla.setIntercellSpacing(new Dimension(0, 0));
		tabla.setSelectionBackground(new Color(37, 99, 235, 40));
		tabla.setSelectionForeground(TEXT);
		tabla.setFocusable(false);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // columnas equitativas

		JTableHeader header = tabla.getTableHeader();
		header.setBackground(S2);
		header.setForeground(TEXT3);
		header.setFont(new Font("Monospaced", Font.BOLD, 12));
		header.setPreferredSize(new Dimension(0, 40));
		header.setBorder(new MatteBorder(0, 0, 1, 0, BORDER));
		header.setReorderingAllowed(false);

		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable t, Object val, boolean sel, boolean foc, int row,
					int col) {
				super.getTableCellRendererComponent(t, val, sel, foc, row, col);
				setBackground(sel ? new Color(37, 99, 235, 40) : (row % 2 == 0 ? S1 : S2));
				setForeground(col == 0 ? BLUE3 : col == 1 ? TEXT2 : TEXT);
				setFont(col == 0 ? new Font("Monospaced", Font.PLAIN, 13) : new Font("SansSerif", Font.PLAIN, 14));
				setHorizontalAlignment(col == 1 ? CENTER : LEFT);
				setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 1, 0, BORDER),
						new EmptyBorder(0, 16, 0, 16)));
				return this;
			}
		};
		for (int i = 0; i < 3; i++)
			tabla.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);

		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBorder(new LineBorder(BORDER, 1, true));
		scrollPane.setBackground(S1);
		scrollPane.getViewport().setBackground(S1);

		JPanel centro = new JPanel(new BorderLayout());
		centro.setBackground(BG);
		centro.setBorder(new EmptyBorder(20, 30, 20, 30));
		centro.add(scrollPane, BorderLayout.CENTER);

		contentPane.add(centro, BorderLayout.CENTER);

		// ── SUR: botones equitativos ───────────────
		JPanel sur = new JPanel(new BorderLayout());
		sur.setBackground(S2);
		sur.setBorder(new MatteBorder(1, 0, 0, 0, BORDER));
		sur.setPreferredSize(new Dimension(0, 70));

		// GridLayout 1×3 → los 3 botones ocupan el mismo ancho siempre
		JPanel botonesPanel = new JPanel(new GridLayout(1, 3, 20, 0));
		botonesPanel.setBackground(S2);
		botonesPanel.setBorder(new EmptyBorder(12, 30, 12, 30));

		// Celda 1: campo de texto + See
		JPanel celdaSee = new JPanel(new BorderLayout(8, 0));
		celdaSee.setBackground(S2);

		textField = new JTextField();
		textField.setBackground(new Color(17, 24, 39));
		textField.setForeground(TEXT);
		textField.setCaretColor(BLUE3);
		textField.setFont(new Font("Monospaced", Font.PLAIN, 13));
		textField.setBorder(
				BorderFactory.createCompoundBorder(new LineBorder(BORDER2, 1), new EmptyBorder(0, 10, 0, 10)));
		celdaSee.add(textField, BorderLayout.CENTER);

		btnVer = makeButton("See", BLUE, Color.WHITE, BLUE);
		btnVer.addActionListener(this);
		celdaSee.add(btnVer, BorderLayout.EAST);
		botonesPanel.add(celdaSee);

		// Celda 2: % from salas
		btnSalas = makeButton("% from rooms", S2, BLUE3, new Color(59, 130, 246, 100));
		btnSalas.addActionListener(this);
		botonesPanel.add(btnSalas);

		// Celda 3: Delete
		btnBorrar = makeButton("Delete", S2, DANGER, new Color(244, 63, 94, 77));
		btnBorrar.addActionListener(this);
		botonesPanel.add(btnBorrar);

		sur.add(botonesPanel, BorderLayout.CENTER);
		contentPane.add(sur, BorderLayout.SOUTH);
	}

	/**
	 * Crea un botón personalizado con colores y estilos específicos.
	 * @param text el texto del botón.
	 * @param bg el color de fondo.
	 * @param fg el color del texto.
	 * @param borderColor el color del borde.
	 * @return el botón creado.
	 */
	// =============================================
	// FACTORY DE BOTONES
	// =============================================
	private JButton makeButton(String text, Color bg, Color fg, Color borderColor) {
		JButton btn = new JButton(text) {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(getModel().isPressed() ? bg.darker() : getModel().isRollover() ? bg.brighter() : bg);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 7, 7);
				g2.dispose();
				super.paintComponent(g);
			}

			@Override
			protected void paintBorder(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(borderColor);
				g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 7, 7);
				g2.dispose();
			}
		};
		btn.setForeground(fg);
		btn.setFont(new Font("SansSerif", Font.BOLD, 13));
		btn.setContentAreaFilled(false);
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		return btn;
	}

	/**
	 * Maneja los eventos de acción de los botones.
	 * @param e el evento de acción.
	 */
	// =============================================
	// ACCIONES
	// =============================================
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnVer) {
			String idText = textField.getText().trim();
			if (idText.isEmpty()) {
				accesoDB2();
			} else {
				accesoBD(Integer.parseInt(idText));
			}
		}
		if (e.getSource() == btnBorrar) {
			String idText = textField.getText().trim();
			accesoBD3(Integer.parseInt(idText));
		}
		if (e.getSource() == btnSalas) {
			String idText = textField.getText().trim();
			accesoBD4(Integer.parseInt(idText));
		}

		if (e.getSource() == btnx) {
			this.dispose();
		}
	}

	/**
	 * Actualiza el color del botón según el estado de ocupación de la sala.
	 * @param id el ID de la sala.
	 */
	// ── lógica de color del btnColor ─────────────
	private void accesoBD4(int id) {
		DaoSalas dao = new DaoSalas();
		int resultado;
		try {
			resultado = dao.funcion(id, new TreeMap<>());
			if (resultado == 0)
				throw new SalaException(SalaException.TipoError.SALA_CERRADA, id);
			if (resultado == 3)
				throw new SalaException(SalaException.TipoError.SALA_LLENA, id);
			switch (resultado) {
			case 1:
				btnColor.setBackground(new Color(16, 185, 129));
				btnColor.setForeground(new Color(6, 78, 59));
				btnColor.setText("Available");
				break;
			case 2:
				btnColor.setBackground(new Color(245, 158, 11));
				btnColor.setForeground(new Color(92, 55, 0));
				btnColor.setText("Moderate");
				break;

			}
		} catch (SalaException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Room Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			this.dispose();
		}
	}

	/**
	 * Borra una sala por su ID.
	 * @param id el ID de la sala a borrar.
	 */
	private void accesoBD3(int id) {
		if (id <= 0)
			throw new SalaException(SalaException.TipoError.ID_INVALIDO, id);

		DaoSalas dao = new DaoSalas();
		Map<Integer, Sala> salas = new TreeMap<>();
		try {
			dao.borrarSala(id, salas);
		} catch (SalaException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Room Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			this.dispose();
		}
	}

	/**
	 * Obtiene todas las salas y actualiza la tabla.
	 */
	private void accesoDB2() {
		DaoSalas dao = new DaoSalas();
		Map<Integer, Sala> salas = new TreeMap<>();
		try {
			dao.obtenerSalas(salas);
			if (salas.isEmpty())
				throw new SalaException(SalaException.TipoError.SALA_NO_ENCONTRADA, 0);
			refreshTabla(salas);
		} catch (SalaException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Room Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			this.dispose();
		}
	}

	/**
	 * Busca una sala por ID y actualiza la tabla.
	 * @param id el ID de la sala a buscar.
	 */
	private void accesoBD(int id) {
		if (id <= 0)
			throw new SalaException(SalaException.TipoError.ID_INVALIDO, id);

		DaoSalas dao = new DaoSalas();
		Map<Integer, Sala> salas = new TreeMap<>();
		try {
			dao.obtenerSalasBuscar(id, salas);
			if (salas.isEmpty())
				throw new SalaException(SalaException.TipoError.SALA_NO_ENCONTRADA, id);
			refreshTabla(salas);
		} catch (SalaException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Room Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			this.dispose();
		}
	}

	/**
	 * Actualiza la tabla con los datos de las salas.
	 * @param salas el mapa de salas a mostrar.
	 */
	private void refreshTabla(Map<Integer, Sala> salas) {
		modelo.setRowCount(0);
		for (Sala sala : salas.values()) {
			modelo.addRow(new Object[] { sala.getNumSala(), sala.getAforo(), sala.getTipoTransmision() });
		}
	}
}