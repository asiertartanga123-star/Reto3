package ui.user.panel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelInicio extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelInicio() {
		setLayout(new BorderLayout());
		setBackground(Color.blue);
		setBorder(new EmptyBorder(20, 20, 20, 20));
	}

}
