package ui.user;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainUserView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LoginJDialog login;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUserView frame = new MainUserView();
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
	public MainUserView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(500, 300);
		setLocationRelativeTo(null);

		
		login = new LoginJDialog(this);
		login.setVisible(true);
	}

}
