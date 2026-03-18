package ui.Sala;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class SalaView extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SalaView dialog = new SalaView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SalaView() {
		setBounds(100, 100, 521, 527);
		setTitle("Vista de la tabla");
		contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    
	    
	    String[] columnas = {"Num_Sala","Aforo", "Tipo_pantalla"};

	    modelo = new DefaultTableModel(columnas, 0);
	    contentPane.setLayout(null);
	    tabla = new JTable(modelo);

	    JScrollPane scrollPane = new JScrollPane(tabla);
	    scrollPane.setBounds(49, 55, 386, 207);

	    contentPane.add(scrollPane);
	    
	    btnColor = new JButton("");
	    btnColor.setBounds(198, 24, 85, 21);
	    contentPane.add(btnColor);
	    
	    btnVer= new JButton("Ver");
	    btnVer.setBounds(432, 0, 65, 31);
	    contentPane.add(btnVer);
	    
	    btnBorrar = new JButton("Borrar");
	    btnBorrar.setBounds(49, 335, 98, 31);
	    contentPane.add(btnBorrar);
	    
	    btnSalas = new JButton("% de las salas");
	    btnSalas.setBounds(337, 340, 98, 31);
	    contentPane.add(btnSalas);
	    
	    btnx = new JButton("");
	    btnx.setBounds(198, 402, 98, 31);
	    contentPane.add(btnx);
	    
	    textField = new JTextField();
	    textField.setBounds(198, 346, 96, 19);
	    contentPane.add(textField);
	    textField.setColumns(10);
	    
	    
	 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
