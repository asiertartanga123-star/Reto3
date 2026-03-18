package ui.catalogo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import java.awt.Font;

public class Principal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton cliente, administrador, catalogo, salas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 874, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(100, 200, 100, 200));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		cliente = new JButton("CLIENTE");
		cliente.setFont(new Font("Verdana", Font.BOLD, 25));
		cliente.addActionListener(this);
		contentPane.add(cliente);
		
		administrador = new JButton("ADMINISTRADOR");
		administrador.setFont(new Font("Verdana", Font.BOLD, 25));
		administrador.addActionListener(this);
		contentPane.add(administrador);
		
		catalogo = new JButton("CATALOGO");
		catalogo.setFont(new Font("Verdana", Font.BOLD, 25));
		catalogo.addActionListener(this);
		contentPane.add(catalogo);
		
		salas = new JButton("SALAS");
		salas.setFont(new Font("Verdana", Font.BOLD, 25));
		salas.addActionListener(this);
		contentPane.add(salas);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==catalogo) {
			Catalogo peli = new Catalogo();
			peli.setVisible(true);
			this.dispose();
		}
			
	}
}
