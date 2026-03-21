package ui.user.panel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelConfig extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelConfig() {
		setLayout(new BorderLayout());
		setBackground(Color.green);
		setBorder(new EmptyBorder(20, 20, 20, 20));
	}

}