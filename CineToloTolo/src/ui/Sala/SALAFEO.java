package ui.Sala;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import model.Pelicula;


public class SALAFEO extends JDialog implements ActionListener {

		private static final long serialVersionUID = 1L;
		private JButton btnVerTabla;
		private JPanel contentPane;
		private JTable tabla;
		private DefaultTableModel modelo;
		private JScrollPane scrollPane1;
		private JLabel titulo;

		private Map<Integer, Pelicula> peliculas = new TreeMap<>();
		private JTextField bucaTituloPeli;

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			try {
				SALAFEO dialog = new SALAFEO();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * Create the dialog.
		 */
		public SALAFEO() {
			setBounds(100, 100, 1044, 583);
			setTitle("Vista de la tabla");
			contentPane = new JPanel();
			contentPane.setBackground(new Color(27, 38, 59));
		    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		    contentPane.setLayout(null);   
		    setContentPane(contentPane);
			{
				titulo = new JLabel("CATALOGO");
				titulo.setForeground(new Color(255, 255, 255));
				titulo.setFont(new Font("Verdana", Font.BOLD, 50));
				titulo.setHorizontalAlignment(SwingConstants.CENTER);
				titulo.setBounds(10, 23, 1010, 56);
				getContentPane().add(titulo);
			}
			{
//				btnVerTabla = new JButton("Ver tabla");
//			    btnVerTabla.setForeground(new Color(240, 255, 255));
//			    btnVerTabla.setBackground(new Color(128, 128, 128));
//			    btnVerTabla.addActionListener(this);
//			    btnVerTabla.setBounds(863, 421, 111, 30);
//			    contentPane.add(btnVerTabla);
				
				String[] columnas = {"IDPelicula","Titulo","Genero","Valoración","Duración","Director"};
			    modelo = new DefaultTableModel(columnas, 0);
			    tabla = new JTable(modelo);
			    tabla.setBorder(new LineBorder(new Color(0, 0, 0)));
			    tabla.setFont(new Font("Tahoma", Font.BOLD, 13));
			    scrollPane1 = new JScrollPane(tabla);
			    scrollPane1.setBounds(42,153,932,301);
			    contentPane.add(scrollPane1);
			    accesoBD();
			    
			 // Colores principales
			    Color PRIMARY   = new Color(0x1B, 0x26, 0x3B);  // #1B263B
			    Color SECONDARY = new Color(0x0D, 0x1B, 0x2A);  // #0D1B2A
			    Color TEXT      = Color.WHITE;                    // #ffffff
			    Color ACCENT    = new Color(0x06, 0xB6, 0xD4);  // #06b6d4

			    // Colores de la tabla
			    tabla.setBackground(SECONDARY);
			    tabla.setForeground(TEXT);
			    tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
			    tabla.setRowHeight(30);
			    tabla.setGridColor(PRIMARY);
			    tabla.setSelectionBackground(ACCENT);
			    tabla.setSelectionForeground(SECONDARY);
			    tabla.setShowHorizontalLines(true);
			    tabla.setShowVerticalLines(false);

			    // Establece el header 
			    JTableHeader header = tabla.getTableHeader();
			    header.setBackground(PRIMARY);
			    header.setForeground(ACCENT);
			    header.setFont(new Font("Segoe UI", Font.BOLD, 13));
			    header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, ACCENT));

			    // Hace una fila de un color, la siguiente de otro, alternando
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

			    // Edita el scrollpane
			    scrollPane1.getViewport().setBackground(SECONDARY);
			    scrollPane1.setBorder(BorderFactory.createLineBorder(ACCENT));
			    
			    JButton btnDetalle = ui.element.ControlObjects.botonMenu("ver detalles");
			    btnDetalle.setBounds(42, 464, 150, 30); // ajusta Y según tu layout
			    btnDetalle.addActionListener(e -> {
			        int fila = tabla.getSelectedRow();
			        if (fila == -1) {
			            JOptionPane.showMessageDialog(this, "Selecciona una película primero.");
			            return;
			        }
			        // Recogemos los datos de la fila seleccionada
			        int    id       = (int)    modelo.getValueAt(fila, 0);
			        String titulo   = (String) modelo.getValueAt(fila, 1);
			        String genero   = (String) modelo.getValueAt(fila, 2);
			        int    val      = (int)    modelo.getValueAt(fila, 3);
			        int    duracion = (int)    modelo.getValueAt(fila, 4);
			        String director = (String) modelo.getValueAt(fila, 5);
			        // La sinopsis la sacamos del mapa original
			        String sinopsis = peliculas.get(id).getSinopsis();

			        new DetallePelicula(this, id, titulo, genero, val, duracion, director, sinopsis)
			            .setVisible(true);
			    });
			    contentPane.add(btnDetalle);
			}
			
			JButton prueba = ControlObjects.botonMenu("prueba");
			contentPane.add(prueba);
			
			JLabel lblNewLabel = new JLabel("Buscar:");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNewLabel.setBounds(42, 113, 71, 30);
			contentPane.add(lblNewLabel);
			
			bucaTituloPeli = new JTextField();
			bucaTituloPeli.setBounds(109, 121, 292, 19);
			contentPane.add(bucaTituloPeli);
			bucaTituloPeli.setColumns(10);
			
			{
				if (bucaTituloPeli.getText() != null) {
					String a = bucaTituloPeli.getText();
					
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
//								pelicula.getSinopsis()
						};
						modelo.addRow(fila);
					}
			}catch(SQLException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error en el SQL", JOptionPane.ERROR_MESSAGE);
				this.dispose();
			}catch(Exception exception)
			{
				JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				this.dispose();
			}
		}
		
		public void accesoBDTitulo(String a)
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
//								pelicula.getSinopsis()
						};
						modelo.addRow(fila);
					}
			}catch(SQLException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error en el SQL", JOptionPane.ERROR_MESSAGE);
				this.dispose();
			}catch(Exception exception)
			{
				JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				this.dispose();
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
}

