package ui.catalogo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


public class Catalogo extends JDialog implements ActionListener {

		private static final long serialVersionUID = 1L;
		private JButton btnVerTabla;
		private JPanel contentPane;
		private JTable tabla;
		private DefaultTableModel modelo;
		private JScrollPane scrollPane1;
		private JLabel titulo;


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
			setBounds(100, 100, 1044, 583);
			setTitle("Vista de la tabla");
			contentPane = new JPanel();
			contentPane.setBackground(new Color(250, 250, 210));
		    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		    contentPane.setLayout(null);   
		    setContentPane(contentPane);
			{
				titulo = new JLabel("CATALOGO");
				titulo.setFont(new Font("Verdana", Font.BOLD, 50));
				titulo.setHorizontalAlignment(SwingConstants.CENTER);
				titulo.setBounds(10, 23, 1010, 56);
				getContentPane().add(titulo);
			}
			{
				btnVerTabla = new JButton("Ver tabla");
			    btnVerTabla.setForeground(new Color(240, 255, 255));
			    btnVerTabla.setBackground(new Color(128, 128, 128));
			    btnVerTabla.addActionListener(this);
			    btnVerTabla.setBounds(863, 421, 111, 30);
			    contentPane.add(btnVerTabla);
			    String[] columnas = {"IDPelicula","Titulo","Genero","Valoración","Duración","Director","Sinopsis"};
			    
			    modelo = new DefaultTableModel(columnas, 0);
			    tabla = new JTable(modelo);
			    tabla.setBorder(new LineBorder(new Color(0, 0, 0)));
			    tabla.setFont(new Font("Tahoma", Font.BOLD, 13));
			    scrollPane1 = new JScrollPane(tabla);
			    scrollPane1.setBounds(42,116,932,274);
			    
			    contentPane.add(scrollPane1);
			}
			
			JLabel lblNewLabel = new JLabel("INTRODUCE EL ID DE UNA PELÍCULA:");
			lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 15));
			lblNewLabel.setBounds(42, 400, 322, 43);
			contentPane.add(lblNewLabel);
			{
				
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
}

