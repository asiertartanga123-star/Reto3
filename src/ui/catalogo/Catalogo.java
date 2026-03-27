package ui.catalogo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.Image;

import dao.DaoCatalogo;
import exception.FieldVacio;
import model.Pelicula;
import ui.element.ControlObjects;


public class Catalogo extends JDialog implements ActionListener {

		private static final long serialVersionUID = 1L;
		private JPanel contentPane;
		private JTable tabla;
		private DefaultTableModel modelo;
		private JScrollPane scrollPane1;
		private JLabel titulo;
		private Map<Integer, Pelicula> peliculas = new TreeMap<>();
		private JTextField bucaTituloPeli;
	    private JLabel labelBuscar;
	    private JButton btnDetalle;
	    private JButton btnBuscarTitulo;
	    private JTableHeader header;
	    private JComboBox<String> comboGenero;
	    private JButton btnFiltrarGenero;
	    private JComboBox<Integer> comboValoracion;
	    private JButton btnFiltrarValoracion;
	    private JButton btnValorar;
	    private JButton btnResetear; 
	    private JLabel labelGenero;
	    private JLabel labelValMin;
		private JMenuBar menuBar;
		private JMenu menuOpciones;
		private	JMenuItem itemReset;
		private JMenuItem itemSalir;
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			try {
				Catalogo dialog = new Catalogo();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * Create the dialog.
		 */
		public Catalogo() {
//			setBounds(100, 100, 1044, 583);
			Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
			setBounds(0, 0, pantalla.width, pantalla.height);
	        setTitle("TOLOTOLO - FILMS CATALOGUE");

	        // ── Panel principal ────────────────────────────────────
	        contentPane = new JPanel();
	        contentPane.setBackground(new Color(27, 38, 59));
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        contentPane.setLayout(null);
	        setContentPane(contentPane);

	        // ── Colores reutilizables ──────────────────────────────
	        Color PRIMARY   = new Color(0x1B, 0x26, 0x3B);
	        Color SECONDARY = new Color(0x0D, 0x1B, 0x2A);
	        Color TEXT      = Color.WHITE;
	        Color ACCENT    = new Color(0x06, 0xB6, 0xD4);

	        // ══════════════════════════════════════════════════════
	        //  TÍTULO PRINCIPAL
	        // ══════════════════════════════════════════════════════
	        titulo = new JLabel("CATALOGUE");
	        titulo.setForeground(TEXT);
	        titulo.setFont(new Font("Verdana", Font.BOLD, 50));
	        titulo.setHorizontalAlignment(SwingConstants.CENTER);
	        titulo.setBounds(10, 23, 1010, 56);
	        contentPane.add(titulo);

	        // ══════════════════════════════════════════════════════
	        //  TABLA DE PELÍCULAS
	        // ══════════════════════════════════════════════════════
	        String[] columnas = {"FILM_ID","TITLE","GENRE","RATING","DURATION","DIRECTOR"};
	        modelo     = new DefaultTableModel(columnas, 0) {
				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int row, int colum) {
					return false;
				}
			};
	        tabla      = new JTable(modelo);
	        scrollPane1 = new JScrollPane(tabla);
	        scrollPane1.setBounds(42, 153, 932, 295);
	        contentPane.add(scrollPane1);

	        // Estilo de la tabla
	        tabla.setBackground(SECONDARY);
	        tabla.setForeground(TEXT);
	        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
	        tabla.setRowHeight(30);
	        tabla.setGridColor(PRIMARY);
	        tabla.setSelectionBackground(ACCENT);
	        tabla.setSelectionForeground(SECONDARY);
	        tabla.setShowHorizontalLines(true);
	        tabla.setShowVerticalLines(false);

	        // Estilo del header de la tabla
	        header = tabla.getTableHeader();
	        header.setBackground(PRIMARY);
	        header.setForeground(ACCENT);
	        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
	        header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, ACCENT));

	        // Filas alternando color
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

	        // Estilo del scrollPane
	        scrollPane1.getViewport().setBackground(SECONDARY);
	        scrollPane1.setBorder(BorderFactory.createLineBorder(ACCENT));

	        // Carga inicial de datos
	        accesoBD();

	        // ══════════════════════════════════════════════════════
	        //  ZONA SUPERIOR IZQUIERDA: Búsqueda por título
	        // ══════════════════════════════════════════════════════

	        // Label "Buscar:"
	        labelBuscar = new JLabel("Search:");
	        labelBuscar.setForeground(TEXT);
	        labelBuscar.setFont(new Font("Tahoma", Font.BOLD, 15));
	        labelBuscar.setBounds(42, 113, 71, 30);
	        contentPane.add(labelBuscar);

	        // Campo de texto para el título
	        bucaTituloPeli = new JTextField();
	        bucaTituloPeli.setBounds(109, 121, 200, 21);
	        bucaTituloPeli.setColumns(10);
	        contentPane.add(bucaTituloPeli);

	        // Botón buscar por título
			ImageIcon iconoLupa = new ImageIcon(new ImageIcon("C:\\Users\\1dam\\Desktop\\Reto3\\src\\ui\\catalogo\\img\\lupa.png")
        		.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
			btnBuscarTitulo = ui.element.ControlObjects.botonMenu("Search");
			btnBuscarTitulo.setIcon(iconoLupa);
	        btnBuscarTitulo.setFocusPainted(false);
	        btnBuscarTitulo.setFont(new Font("Consolas", Font.BOLD, 14));
	        btnBuscarTitulo.setBackground(PRIMARY);
	        btnBuscarTitulo.setForeground(ACCENT);
	        btnBuscarTitulo.setBorder(BorderFactory.createLineBorder(ACCENT, 1));
	        btnBuscarTitulo.setOpaque(true);
	        btnBuscarTitulo.setBounds(319, 120, 80, 21);
	        btnBuscarTitulo.addActionListener(this);
	        contentPane.add(btnBuscarTitulo);

	        // ══════════════════════════════════════════════════════
	        //  ZONA SUPERIOR CENTRO: Filtro por género
	        // ══════════════════════════════════════════════════════
	        // Label "Género:"
	        labelGenero = new JLabel("Genre:");
	        labelGenero.setForeground(TEXT);
	        labelGenero.setFont(new Font("Tahoma", Font.BOLD, 12));
	        labelGenero.setBounds(415, 121, 55, 21);
	        contentPane.add(labelGenero);

	        // ComboBox con los géneros disponibles
	        comboGenero = new JComboBox<>(new String[]{
	        		"TODOS","TERROR","COMEDIA","DRAMA","ACCION","CIENCIA_FICCION"
	        });
	        comboGenero.setBounds(470, 121, 130, 21);
	        contentPane.add(comboGenero);

	        // Botón filtrar por género
	        btnFiltrarGenero = ui.element.ControlObjects.botonMenu("Filter");
	        btnFiltrarGenero.setBounds(610, 121, 80, 21);
	        btnFiltrarGenero.addActionListener(this);
	        contentPane.add(btnFiltrarGenero);

	        // ══════════════════════════════════════════════════════
	        //  ZONA SUPERIOR DERECHA: Filtro por valoración mínima
	        // ══════════════════════════════════════════════════════

	        // Label "Val. mín:"
	        labelValMin = new JLabel("Min rating:");
	        labelValMin.setForeground(TEXT);
	        labelValMin.setFont(new Font("Tahoma", Font.BOLD, 12));
	        labelValMin.setBounds(680, 121, 65, 21);
	        contentPane.add(labelValMin);

	        // ComboBox con valores de valoración del 1 al 5
	        comboValoracion = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
	        comboValoracion.setBounds(765, 121, 60, 21);
	        contentPane.add(comboValoracion);

	        // Botón filtrar por valoración
	        btnFiltrarValoracion = ui.element.ControlObjects.botonMenu("Filter");
	        btnFiltrarValoracion.setBounds(835, 121, 80, 21);
	        btnFiltrarValoracion.addActionListener(this);
	        contentPane.add(btnFiltrarValoracion);

	        // ══════════════════════════════════════════════════════
	        //  ZONA INFERIOR IZQUIERDA: Ver detalles de película
	        // ══════════════════════════════════════════════════════
	        btnDetalle = ui.element.ControlObjects.botonMenu("View details");
	        btnDetalle.setBounds(42, 464, 130, 30);
	        btnDetalle.addActionListener(this);
	        contentPane.add(btnDetalle);

	        // ══════════════════════════════════════════════════════
	        //  ZONA INFERIOR CENTRO: Valorar película seleccionada
	        // ══════════════════════════════════════════════════════
			ImageIcon iconoEstrella = new ImageIcon(new ImageIcon(getClass().getResource("./img/estrella.png"))
       			 .getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
			btnValorar = ui.element.ControlObjects.botonMenu("Rate");
			btnValorar.setIcon(iconoEstrella);
	        btnValorar.setBounds(185, 464, 110, 30);
	        btnValorar.addActionListener(this);
	        contentPane.add(btnValorar);

	        // ══════════════════════════════════════════════════════
	        //  ZONA INFERIOR DERECHA: Resetear todos los filtros
	        // ══════════════════════════════════════════════════════
			ImageIcon iconoReset = new ImageIcon(new ImageIcon(getClass().getResource("./img/reset.png"))
        		.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
			btnResetear = ui.element.ControlObjects.botonMenu("Reset filters");
			btnResetear.setIcon(iconoReset);
	        btnResetear.setBounds(824, 464, 150, 30);
	        btnResetear.addActionListener(this);
	        contentPane.add(btnResetear);
	        
	        contentPane.addComponentListener(new java.awt.event.ComponentAdapter() {
	            @Override
	            public void componentResized(java.awt.event.ComponentEvent e) {
	                recolocarElementos();
	            }
	        });

	        //  NUEVO: llamada inicial para que al abrir ya esté centrado
	        SwingUtilities.invokeLater(this::recolocarElementos);


			// ══════════════════════════════════════════════════════
			//  MENÚ SUPERIOR
			// ══════════════════════════════════════════════════════
			menuBar = new JMenuBar();
			menuOpciones = new JMenu("Options");

			itemReset = new JMenuItem("Reset filters");
			itemReset.addActionListener(this);

			itemSalir = new JMenuItem("Exit");
			itemSalir.addActionListener(this);

			menuBar.setBackground(PRIMARY);
        	menuBar.setBorder(BorderFactory.createEmptyBorder());	

			menuOpciones.setBackground(PRIMARY);
        	menuOpciones.setForeground(Color.WHITE);
        	menuOpciones.getPopupMenu().setBackground(PRIMARY);
        	menuOpciones.getPopupMenu().setBorder(BorderFactory.createLineBorder(ACCENT, 1));
			menuOpciones.add(itemReset);
			menuOpciones.add(itemSalir);
			menuBar.add(menuOpciones);
			setJMenuBar(menuBar);
	    }


		private void resetFiltros() {
			bucaTituloPeli.setText("");
	            comboGenero.setSelectedIndex(0);
	            comboValoracion.setSelectedIndex(0);
	            accesoBD();
		}

		private void verDetalles() {
			 int fila = tabla.getSelectedRow();
	            if (fila == -1) {
	                JOptionPane.showMessageDialog(this, "First select a film.");
	                return;
	            }
	            int    id       = (int)    modelo.getValueAt(fila, 0);
	            String tit      = (String) modelo.getValueAt(fila, 1);
	            String genero   = (String) modelo.getValueAt(fila, 2);
	            int    val      = (int)    modelo.getValueAt(fila, 3);
	            int    duracion = (int)    modelo.getValueAt(fila, 4);
	            String director = (String) modelo.getValueAt(fila, 5);

	            // ── FIX: comprobar que la película existe en el mapa antes de acceder ──
	            Pelicula peli = peliculas.get(id);
	            if (peli == null) {
	                JOptionPane.showMessageDialog(this,
	                    "Film not found in cache. Please refresh the catalogue.",
	                    "Data error", JOptionPane.WARNING_MESSAGE);
	                return;
	            }

	            String sinopsis = peli.getSinopsis();
	            new DetallePelicula(this, id, tit, genero, val, duracion, director, sinopsis).setVisible(true);
		}
		
		// ══════════════════════════════════════════════════════════
	//  RESPONSIVE: recoloca todos los elementos según el tamaño
	// ══════════════════════════════════════════════════════════
		private void recolocarElementos() {
		    int w = contentPane.getWidth();   // ← contentPane, no getWidth()
		    int h = contentPane.getHeight();  // ← contentPane, no getHeight()

		    int margen     = 40;
		    int anchoTabla = w - (margen * 2);
		    int yFiltros   = 120;
		    int yTabla     = 160;
		    int altoTabla  = h - yTabla - 120;
		    int yBotones   = yTabla + altoTabla + 20;

		    // Título
		    titulo.setBounds(0, 20, w, 60);

		    // Tabla
		    scrollPane1.setBounds(margen, yTabla, anchoTabla, altoTabla);

		    // Búsqueda por título (izquierda)
		    labelBuscar.setBounds(margen, yFiltros - 8, 65, 25);
		    bucaTituloPeli.setBounds(margen + 65, yFiltros, 180, 22);
		    btnBuscarTitulo.setBounds(margen + 255, yFiltros, 80, 22);

		    // Filtro por género (centro)
		    int xGenero = (w / 2) - 110;
		    labelGenero.setBounds(xGenero, yFiltros, 60, 22);
		    comboGenero.setBounds(xGenero + 60, yFiltros, 130, 22);
		    btnFiltrarGenero.setBounds(xGenero + 200, yFiltros, 80, 22);

		    // Filtro por valoración (derecha)
		    int xVal = w - margen - 230;
		    labelValMin.setBounds(xVal, yFiltros, 65, 22);
		    comboValoracion.setBounds(xVal + 65, yFiltros, 60, 22);
		    btnFiltrarValoracion.setBounds(xVal + 135, yFiltros, 80, 22);

		    // Botones inferiores
		    btnDetalle.setBounds(margen, yBotones, 130, 30);
		    btnValorar.setBounds(margen + 145, yBotones, 110, 30);
		    btnResetear.setBounds(w - margen - 150, yBotones, 150, 30);

		    contentPane.revalidate();
		    contentPane.repaint();
		}
		
		private void abrirValorar() {
	        int fila = tabla.getSelectedRow();
	        if (fila == -1) {
	            JOptionPane.showMessageDialog(this, "First select a film.");
	            return;
	        }

	        int id  = (int) modelo.getValueAt(fila, 0);
	        String tit = (String) modelo.getValueAt(fila, 1);

	        // Spinner del 1 al 5
	        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(3, 1, 5, 1);
	        JSpinner spinner = new JSpinner(spinnerModel);
	        spinner.setFont(new Font("Segoe UI", Font.PLAIN, 14));

	        int opcion = JOptionPane.showConfirmDialog(
	                this,
	                new Object[]{"Your rating for \"" + tit + "\" (1-5):", spinner},
	                "Rate film",
	                JOptionPane.OK_CANCEL_OPTION
	        );

	        if (opcion == JOptionPane.OK_OPTION) {
	            int nuevaValoracion = (int) spinner.getValue();
	            try {
	                DaoCatalogo dao = new DaoCatalogo();
	                boolean ok = dao.valorarPelicula(id, nuevaValoracion);
	                if (ok) {
	                    JOptionPane.showMessageDialog(this, "¡Rating saved successfully!");
	                    accesoBD(); 
	                } else {
	                    JOptionPane.showMessageDialog(this, "The review could not be saved.",
	                            "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    }
		
		public void accesoBD()
		{
			DaoCatalogo accesoBD=new DaoCatalogo();
			peliculas.clear();
			try 
			{
					accesoBD.obtenerPelis(peliculas);
					modelo.setRowCount(0);
					for (Pelicula pelicula: peliculas.values()) {
						Object[] fila = {
								pelicula.getIdPelicula(),
								pelicula.getTitulo(),
								pelicula.getGenero(),
								pelicula.getValoracion(),
								pelicula.getDuracionMin(),
								pelicula.getDirector(),
						};
						modelo.addRow(fila);
					}
			}catch(SQLException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "SQL error ocurred", JOptionPane.ERROR_MESSAGE);
				this.dispose();
			}catch(Exception exception)
			{
				JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				this.dispose();
			}
		}
				
		
		public void accesoBDTitulo(String textoBusqueda) throws FieldVacio {
		    if (textoBusqueda.isEmpty()) {
		        throw new FieldVacio("The search field is empty.");
		    }
		    try {
		    accesoBD();
		    modelo.setRowCount(0);
		    for (Pelicula pelicula : peliculas.values()) {
		        if (pelicula.getTitulo().toLowerCase().contains(textoBusqueda.toLowerCase())) {
		            modelo.addRow(new Object[]{
		                pelicula.getIdPelicula(), pelicula.getTitulo(),
		                pelicula.getGenero(), pelicula.getValoracion(),
		                pelicula.getDuracionMin(), pelicula.getDirector()
		            });
		        }
		    }
		    }catch(Exception e) {
		    	JOptionPane.showMessageDialog(this,
		                "Could not refresh data. Showing last known results.",
		                "Warning", JOptionPane.WARNING_MESSAGE);
		    }
		}

	    public void accesoBDGenero(String genero) {
	        DaoCatalogo dao = new DaoCatalogo();
	        peliculas.clear();
	        try {
	            dao.filtrarPorGenero(genero, peliculas);
	            modelo.setRowCount(0);
	            for (Pelicula p : peliculas.values()) {
	                modelo.addRow(new Object[]{
	                        p.getIdPelicula(), p.getTitulo(),
	                        p.getGenero(), p.getValoracion(),
	                        p.getDuracionMin(), p.getDirector()
	                });
	            }
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(this, e.getMessage(), "Error en el SQL", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
		
	    public void accesoBDValoracion(int valoracionMinima) {
	        DaoCatalogo dao = new DaoCatalogo();
	        peliculas.clear();
	        try {
	            dao.filtrarPorValoracion(valoracionMinima, peliculas);
	            modelo.setRowCount(0);
	            for (Pelicula p : peliculas.values()) {
	                modelo.addRow(new Object[]{
	                        p.getIdPelicula(), p.getTitulo(),
	                        p.getGenero(),     p.getValoracion(),
	                        p.getDuracionMin(), p.getDirector()
	                });
	            }
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(this, e.getMessage(), "Error en el SQL", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
		

		@Override
	    public void actionPerformed(ActionEvent e) {

	        // -- Buscar por título --
			if (e.getSource() == btnBuscarTitulo) {
			    try {
			        accesoBDTitulo(bucaTituloPeli.getText().trim());
			    } catch (FieldVacio ex) {
			        JOptionPane.showMessageDialog(this, ex.getMessage(), "Empty field", JOptionPane.WARNING_MESSAGE);
			    }
			}

	        // -- NUEVO: Filtrar por género --
	        if (e.getSource() == btnFiltrarGenero) {
	            String generoSeleccionado = (String) comboGenero.getSelectedItem();
	            if ("TODOS".equals(generoSeleccionado)) {
	                accesoBD();
	            } else {
	                accesoBDGenero(generoSeleccionado);
	            }
	        }

			if(e.getSource() == btnResetear) {
				resetFiltros();
			}

			if(e.getSource() == btnValorar) {
				abrirValorar();
			}

			if(e.getSource() == btnDetalle) {
				verDetalles();
			}

			if(e.getSource() == itemReset) {
				resetFiltros();
			}

			if(e.getSource() == itemSalir) {
				dispose();
			}

	        // -- NUEVO: Filtrar por valoración mínima --
	        if (e.getSource() == btnFiltrarValoracion) {
	            int valMin = (int) comboValoracion.getSelectedItem();
	            accesoBDValoracion(valMin);
	        }
	    }
}

