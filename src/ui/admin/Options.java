package ui.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.DaoAdmin;
import dao.Sentencias;
import util.validation.ValidacionAdmin;

import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class Options extends JDialog implements ActionListener {

	private JPanel contentPane;
	private JTabbedPane contentJTabbedPane;
	private JLabel titulo;
	private JButton btnAñadir;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnVolver;
	private JTable tablaUsuarios;
	private JTable tablaSalas;
	private JTable tablaPeliculas;
	private JTable tablaEntradas;
	private JScrollPane scroll;
	private DaoAdmin dao = new DaoAdmin();
	
	private static final long serialVersionUID = 1L;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {	
		System.setProperty("sun.java2d.uiScale", "1.0"); // Nota pa mi: "1.5" o  "2.0"
	    SwingUtilities.invokeLater(() -> {
	        try {
	            Options dialog = new Options();
	            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	            dialog.setVisible(true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    });   
	    
	}

	/**
	 * Create the dialog.
	 */
	
	public Options() {
		// ===========================================
	    // 				Panel principal
	    // ===========================================
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	    setBounds(0, 0, pantalla.width, pantalla.height);
	    setTitle("TOLOTOLO - USERS ADMINISTRATION");

	    contentPane = new JPanel();
	    contentPane.setBackground(new Color(27, 38, 59));
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    contentPane.setLayout(null);
	    setContentPane(contentPane);

	    // Color reutilizable
        Color TEXT = Color.WHITE;

	    // Titulo
	    titulo = new JLabel("Options");
	    titulo.setForeground(TEXT);
	    titulo.setFont(new Font("Verdana", Font.BOLD, 50));
	    titulo.setHorizontalAlignment(SwingConstants.CENTER);
	    contentPane.add(titulo);

	    // ===========================================
	    // 					Pestañas
	    // ===========================================
	    contentJTabbedPane = new JTabbedPane();
	    contentJTabbedPane.setToolTipText("");
	    contentJTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	    contentJTabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    contentJTabbedPane.setBounds(43, 120, 1832, 700); 
	    contentPane.add(contentJTabbedPane);

	    // Paneles
	    JPanel panelUsuarios = new JPanel(null);
	    JPanel panelSalas = new JPanel(null);
	    JPanel panelPeliculas = new JPanel(null);
	    JPanel panelEntradas = new JPanel(null);

	    // Agregar pestañas NOTA: Los indices empiezan desde el 0
	    contentJTabbedPane.addTab("Users", panelUsuarios);
	    contentJTabbedPane.addTab("Halls", panelSalas);
	    contentJTabbedPane.addTab("Movies", panelPeliculas);
	    contentJTabbedPane.addTab("Tickets", panelEntradas);

	    // ===========================================
	    // 					Tablas
	    // ===========================================
	    tablaUsuarios = estiloTabla(new String[]{"USER NAME","NAME","SURNAME","MAIL","AGE","PASSWORD"}, panelUsuarios, 10, 11, 1575, 650);
	    tablaSalas = estiloTabla(new String[]{"HALL NUMBER","CAPACITY","TRANSMISION TYPE"}, panelSalas, 50, 50, 1400, 500);
	    tablaPeliculas = estiloTabla(new String[]{"MOVIE ID","TITTLE","DURATION (Minutes)","SYNOPSIS", "RATING", "GENDER","DIRECTOR"}, panelPeliculas, 50, 50, 1400, 500);
	    tablaEntradas = estiloTabla(new String[]{"USER NAME","HALL NUMBER","MOVIE ID","CHAIR NUMBER","PRICE","TRANSMISION DATE", "ADQUISITION DATE"}, panelEntradas, 50, 50, 1400, 500);
	    
	    // ===========================================
	    // 			Botones (Parte inferior)
	    // ===========================================
	    
	    btnAñadir = ui.element.ControlObjects.botonMenu("Add");
	    btnAñadir.setBounds(100, 872, 160, 39);
	    contentPane.add(btnAñadir);
	    
	    btnEliminar = ui.element.ControlObjects.botonMenu("Delete");
	    btnEliminar.setBounds(357, 872, 160, 39);
	    contentPane.add(btnEliminar);
	    
	    btnModificar = ui.element.ControlObjects.botonMenu("Modify");
	    btnModificar.setBounds(617, 872, 160, 39);
	    contentPane.add(btnModificar);
	    
	    btnVolver = ui.element.ControlObjects.botonMenu("Back");
	    btnVolver.setBounds(1540, 872, 160, 39);
	    contentPane.add(btnVolver);
	    
	    // Anadirle accion
	    btnAñadir.addActionListener(this);
	    btnEliminar.addActionListener(this);
	    btnModificar.addActionListener(this);
	    btnVolver.addActionListener(this);
	    
	    // Datos
	    
	    cargarTabla(tablaUsuarios,  Sentencias.VER_USUARIOS);
	    cargarTabla(tablaSalas,     Sentencias.VER_SALAS);
	    cargarTabla(tablaPeliculas, Sentencias.VER_PELICULASS);
	    cargarTabla(tablaEntradas,  Sentencias.VER_ENTRADAS);
	    
	    // Detectar cuando la ventana cambia de tamaño
	    addComponentListener(new ComponentAdapter() {
	        @Override
	        public void componentResized(ComponentEvent e) {
	            recolocarElementos();
	        }
	    });
	    
	    //
	    SwingUtilities.invokeLater(() -> recolocarElementos());
	}
	
	private JTable estiloTabla(String[] columnas, JPanel panel, int x, int y, int w, int h) {

		// Layout del scroll (Tabla)
		panel.setLayout(new BorderLayout());
		
		// Colores reutilizables
	    Color PRIMARY   = new Color(0x1B, 0x26, 0x3B);
	    Color SECONDARY = new Color(0x0D, 0x1B, 0x2A);
	    Color TEXT      = Color.WHITE;
	    Color ACCENT    = new Color(0x06, 0xB6, 0xD4);

	    DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
	        public boolean isCellEditable(int r, int c) {
	            return false;
	        }
	    };

	    // Estilo
	    JTable tabla = new JTable(modelo);
	    tabla.setBackground(SECONDARY);
	    tabla.setForeground(TEXT);
	    tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
	    tabla.setRowHeight(30);
	    tabla.setGridColor(PRIMARY);
	    tabla.setSelectionBackground(ACCENT);
	    tabla.setSelectionForeground(SECONDARY);
	    tabla.setShowHorizontalLines(true);
	    tabla.setShowVerticalLines(false);

	    // Header
	    JTableHeader header = tabla.getTableHeader();
	    header.setBackground(PRIMARY);
	    header.setForeground(ACCENT);
	    header.setFont(new Font("Segoe UI", Font.BOLD, 13));
	    header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, ACCENT));

	    // Filas alternas
	    tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
	        @Override
	        public Component getTableCellRendererComponent(
	                JTable t, Object val, boolean sel, boolean foc, int row, int col) {

	            JLabel l = (JLabel) super.getTableCellRendererComponent(t, val, sel, foc, row, col);

	            if (sel) {
	                l.setBackground(ACCENT);
	                l.setForeground(SECONDARY);
	            } else {
	                l.setBackground(row % 2 == 0 ? SECONDARY : PRIMARY);
	                l.setForeground(TEXT);
	            }

	            l.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
	            l.setOpaque(true);
	            return l;
	        }
	    });
	    
	    // Panel de rueda
	    scroll = new JScrollPane(tabla);
	    scroll.getViewport().setBackground(SECONDARY);
	    scroll.setBorder(BorderFactory.createLineBorder(ACCENT));

	    panel.add(scroll, BorderLayout.CENTER);

	    return tabla;
	}
	
	private void recolocarElementos() {
	    int w = contentPane.getWidth();
	    int h = contentPane.getHeight();
	    int margen = 40;

	    // Titulo
	    titulo.setBounds(10, 23, w - 20, 56);

	    // Tabs/pestañas
	    int tabY = 100;
	    int tabH = h - 220;

	    contentJTabbedPane.setBounds(margen, tabY, w - (margen * 2), tabH);

	    // Scroll (Tabla)
	    for (int i = 0; i < contentJTabbedPane.getTabCount(); i++) {
	        Component tab = contentJTabbedPane.getComponentAt(i);
	        if (tab instanceof JPanel panel) {
	            for (Component comp : panel.getComponents()) {
	                if (comp instanceof JScrollPane) {
	                    comp.setBounds(20, 20,
	                        contentJTabbedPane.getWidth() - 60,
	                        contentJTabbedPane.getHeight() - 80);
	                }
	            }
	        }
	    }

	    // Botones inferiores
	    int botonY = h - 80;
	    int espacio = 40;
	    int anchoBtn = 160;

	    btnAñadir.setBounds(margen, botonY, anchoBtn, 40);
	    btnEliminar.setBounds(margen + anchoBtn + espacio, botonY, anchoBtn, 40);
	    btnModificar.setBounds(margen + (anchoBtn + espacio) * 2, botonY, anchoBtn, 40);
	    btnVolver.setBounds(w - margen - anchoBtn, botonY, anchoBtn, 40);
	    
	    contentPane.revalidate();
	    contentPane.repaint();
	}
	
	public void actionPerformed(ActionEvent e) {

		// ===========================================
		//              Botones (Añadir)
		// ===========================================	
		
		if (e.getSource() == btnAñadir) {

		int tab = contentJTabbedPane.getSelectedIndex();
		
		// Usuarios
		if (tab == 0) {
			
		String user = ValidacionAdmin.pedirTexto("User:", "Empty user");
		if (user == null) return;

		String nombre = ValidacionAdmin.pedirTexto("Name:", "Empty name");
		if (nombre == null) return;

		String apellido = ValidacionAdmin.pedirTexto("Surname:", "Empty surname");
		if (apellido == null) return;

		String correo = ValidacionAdmin.pedirCorreo("Mail:");
		if (correo == null) return;

		int edad = Integer.parseInt(JOptionPane.showInputDialog("Age:")); 
		
		String pass = ValidacionAdmin.pedirTexto("Password:", "Empty password");
		if (pass == null) return;

		String error = ValidacionAdmin.errorUsuario(user, nombre, apellido, correo, edad, pass);
		
		if (error != null) {
		JOptionPane.showMessageDialog(null, error);
		return;
		}

		dao.insertarUsuario(user, nombre, apellido, correo, edad, pass);
		cargarTabla(tablaUsuarios,  Sentencias.VER_USUARIOS);
		
		}

		// Salas
		if (tab == 1) {

		Integer numSalaObj = ValidacionAdmin.pedirEntero("Hall number:");
		if (numSalaObj == null) return;
		int numSala = numSalaObj;
		
		Integer aforoObj = ValidacionAdmin.pedirEntero("Capacity:");
		if (aforoObj == null) return;
		int aforo = aforoObj;

		String tipo = ValidacionAdmin.pedirTexto("Type (2D/3D):", "Empty type");
		if (tipo == null) return;

		String error = ValidacionAdmin.errorSala(numSala, aforo, tipo);

		if (error != null) {
		JOptionPane.showMessageDialog(null, error);
		return;
		}
		
		dao.insertarSala(numSala, aforo, tipo);
		cargarTabla(tablaSalas,     Sentencias.VER_SALAS);
		}

		// Peliculas
		if (tab == 2) {

		String titulo = ValidacionAdmin.pedirTexto("Tittle:", "Empty tittle");
		if (titulo == null) return;

		Integer duracionObj = ValidacionAdmin.pedirEntero("Duration:");
		if (duracionObj == null) return;
		int duracion = duracionObj;

		String sinopsis = ValidacionAdmin.pedirTexto("Synopsis:", "Empty synopsis");
		if (sinopsis == null) return;

		Integer valoracionObj = ValidacionAdmin.pedirEntero("Rate:");
		if (valoracionObj == null) return;
		int valoracion = valoracionObj;

		String genero = ValidacionAdmin.pedirTexto("Gender:", "Empty gender");
		if (genero == null) return;

		String director = ValidacionAdmin.pedirTexto("Director:", "Empty director");
		if (director == null) return;

		String error = ValidacionAdmin.errorPelicula(titulo, duracion, sinopsis, valoracion, genero, director);

		if (error != null) {
		JOptionPane.showMessageDialog(null, error);
		return;
		}

		dao.insertarPelicula(titulo, duracion, sinopsis, valoracion, genero, director);
		cargarTabla(tablaPeliculas, Sentencias.VER_PELICULASS);
		}

		// Entradas
		if (tab == 3) {
		String nick = ValidacionAdmin.pedirTexto("User:", "Empty user");
		if (nick == null) return;

		Integer numSalaObj = ValidacionAdmin.pedirEntero("Hall number:");
		if (numSalaObj == null) return;
		int numSala = numSalaObj;

		Integer idPeliculaObj = ValidacionAdmin.pedirEntero("Movie ID:");
		if (idPeliculaObj == null) return;
		int idPelicula = idPeliculaObj;

		Integer butacaObj = ValidacionAdmin.pedirEntero("Chair number:");
		if (butacaObj == null) return;
		int butaca = butacaObj;

		Integer precioObj = ValidacionAdmin.pedirEntero("Price:");
		if (precioObj == null) return;
		int precio = precioObj;

		java.sql.Date fechaTra = ValidacionAdmin.pedirFecha("Transmision date (YYYY-MM-DD):");
		if (fechaTra == null) return;

		java.sql.Date fechaAdq = ValidacionAdmin.pedirFecha("Adquisition date (YYYY-MM-DD):");
		if (fechaAdq == null) return;

		String error = ValidacionAdmin.errorEntrada(nick, numSala, idPelicula, butaca, precio, fechaTra, fechaAdq);

		if (error != null) {
		JOptionPane.showMessageDialog(null, error);
		return;
		}

		dao.insertarEntrada(nick, numSala, idPelicula, butaca, precio, fechaTra, fechaAdq);
		cargarTabla(tablaEntradas,  Sentencias.VER_ENTRADAS);
		}

	}
		
		// ===========================================
		//             Botones (Eliminar)
		// ===========================================
		if (e.getSource() == btnEliminar) {

		int tab = contentJTabbedPane.getSelectedIndex();

		// Usuarios
	if (tab == 0) { 
		int fila = tablaUsuarios.getSelectedRow();
		
		if (fila != -1) {
		String user = tablaUsuarios.getValueAt(fila, 0).toString();
		dao.borrarUsuario(user);
		cargarTabla(tablaUsuarios,  Sentencias.VER_USUARIOS);
		}

	}
		
		// Salas
	if (tab == 1) {
		int fila = tablaSalas.getSelectedRow();

		if (fila != -1) {
		int numSala = Integer.parseInt(tablaSalas.getValueAt(fila, 0).toString());
		dao.borrarSala(numSala);
		cargarTabla(tablaSalas,     Sentencias.VER_SALAS);
		}

	}

		// Peliculas
	if (tab == 2) {
		int fila = tablaPeliculas.getSelectedRow();

		if (fila != -1) {
		int id = Integer.parseInt(tablaPeliculas.getValueAt(fila, 0).toString());
		dao.borrarPelicula(id);
		cargarTabla(tablaPeliculas, Sentencias.VER_PELICULASS);
		}

	}

		//Entradas
	if (tab == 3) {
		int fila = tablaEntradas.getSelectedRow();

		if (fila != -1) {
		String nick = tablaEntradas.getValueAt(fila, 0).toString();
		int numSala = Integer.parseInt(tablaEntradas.getValueAt(fila, 1).toString());
		int idPelicula = Integer.parseInt(tablaEntradas.getValueAt(fila, 2).toString());
		String fechaStr = tablaEntradas.getValueAt(fila, 5).toString(); 

		java.sql.Date fecha = java.sql.Date.valueOf(fechaStr);
		dao.borrarEntrada(nick, numSala, idPelicula, fecha);
		cargarTabla(tablaEntradas,  Sentencias.VER_ENTRADAS);
		}

	}

	}

		// ===========================================
		// 		       Botones (Modificar)
		// ===========================================

		if (e.getSource() == btnModificar) {
			
		int tab = contentJTabbedPane.getSelectedIndex();

		// Usuarios

	if (tab == 0) {

		int fila = tablaUsuarios.getSelectedRow();

	if (fila != -1) {

		String user = tablaUsuarios.getValueAt(fila, 0).toString();
		String nombre = JOptionPane.showInputDialog("New name:");
		String apellido = JOptionPane.showInputDialog("New surname:");
		String correo = JOptionPane.showInputDialog("New mail:");
		int edad = Integer.parseInt(JOptionPane.showInputDialog("New age:"));
		String pass = JOptionPane.showInputDialog("New password:");
		
		dao.actualizarUsuario(nombre, apellido, correo, edad, pass, user);
		cargarTabla(tablaUsuarios,  Sentencias.VER_USUARIOS);
		}

	}

		// Salas

	if (tab == 1) {

		int fila = tablaSalas.getSelectedRow();

		if (fila != -1) {

		int numSala = Integer.parseInt(tablaSalas.getValueAt(fila, 0).toString());
		int aforo = Integer.parseInt(JOptionPane.showInputDialog("New capacity:"));
		String tipo = JOptionPane.showInputDialog("New type (2D/3D):");

		dao.actualizarSala(numSala, aforo, tipo);
		cargarTabla(tablaSalas,     Sentencias.VER_SALAS);
		}

	}
		
		// Peliculas

	if (tab == 2) {
		int fila = tablaPeliculas.getSelectedRow();

		if (fila != -1) {
			
		int id = Integer.parseInt(tablaPeliculas.getValueAt(fila, 0).toString());
		String titulo = JOptionPane.showInputDialog("New tittle:");
		int duracion = Integer.parseInt(JOptionPane.showInputDialog("New duration:"));
		String sinopsis = JOptionPane.showInputDialog("New synopsis:");
		int valoracion = Integer.parseInt(JOptionPane.showInputDialog("New valoration:"));
		String genero = JOptionPane.showInputDialog("New gender:");
		String director = JOptionPane.showInputDialog("New director:");

		dao.actualizarPelicula(id, titulo, duracion, sinopsis, valoracion, genero, director);

		cargarTabla(tablaPeliculas, Sentencias.VER_PELICULASS);

		}
	}

	if (tab == 3) {
			
		int fila = tablaEntradas.getSelectedRow();
		
		if (fila != -1) {
			
		String nick = tablaEntradas.getValueAt(fila, 0).toString();
		int numSala = Integer.parseInt(tablaEntradas.getValueAt(fila, 1).toString());
		int idPelicula = Integer.parseInt(tablaEntradas.getValueAt(fila, 2).toString());
		String fechaStr = tablaEntradas.getValueAt(fila, 5).toString();

		java.sql.Date fecha = java.sql.Date.valueOf(fechaStr);
		int butaca = Integer.parseInt(JOptionPane.showInputDialog("New chair number:"));
		int precio = Integer.parseInt(JOptionPane.showInputDialog("New price:"));

		dao.actualizarEntrada(nick, numSala, idPelicula, fecha, butaca, precio);
		cargarTabla(tablaEntradas,  Sentencias.VER_ENTRADAS);
		}
	}
	
	}
		
		// ===========================================
		//              Botones (Atras)
		// ===========================================
		
		if (e.getSource() == btnVolver) {
		dispose();
		}
	}
	 	// ===========================================
	 	// 			 Cargado de los datos
	 	// ===========================================
	 	
		private void cargarTabla(JTable tabla, String sql) {
			DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
				modelo.setRowCount(0);

				try {
					for (Object[] fila : dao.ejecutarConsulta(sql)) {
						modelo.addRow(fila);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	
}
