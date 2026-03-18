package ui.user.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.user.Ranking;

public class PanelRanking extends JPanel {
	public static final Color FONDO = Color.BLACK;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PanelRanking(ArrayList<Ranking> ranking, String user_name) {
		setLayout(new BorderLayout());
		setBackground(FONDO);
		setBorder(new EmptyBorder(20, 20, 20, 20));
		add(generarLista(ranking, user_name));
	}

	private JScrollPane generarLista(ArrayList<Ranking> ranking, String user_name) {

		JPanel content = new JPanel(new GridBagLayout());
		content.setBackground(FONDO);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;

		for (int i = 0; i < ranking.size(); i++) {
			Ranking t = ranking.get(i);

			JPanel ticketPanel = new JPanel(new GridBagLayout());

			// color tier
			Color tierColor;
			if (t.getPosicion_top() <= 3) {
				tierColor = new Color(220, 53, 69);
			} else if (t.getPosicion_top() <= 7) {
				tierColor = new Color(255, 140, 0);
			} else {
				tierColor = new Color(40, 167, 69);
			}
			if (user_name.equals(t.getUsuario()))
				tierColor = new Color(0, 0, 255);

			ticketPanel.setBackground(FONDO);
			ticketPanel.setBorder(new CompoundBorder(new LineBorder(tierColor, 2), new EmptyBorder(15, 15, 15, 15)));

			GridBagConstraints tgbc = new GridBagConstraints();
			tgbc.gridx = 0;
			tgbc.gridy = 0;
			tgbc.anchor = GridBagConstraints.WEST;
			tgbc.insets = new Insets(5, 5, 5, 5);

			JLabel lblTitulo = new JLabel("#" + t.getPosicion_top() + " - " + t.getUsuario());
			lblTitulo.setForeground(tierColor);
			lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
			tgbc.gridwidth = 2;
			ticketPanel.add(lblTitulo, tgbc);

			tgbc.gridy++;
			tgbc.gridwidth = 1;
			JLabel lblNombre = new JLabel("Nombre: " + t.getNombre());
			lblNombre.setForeground(Color.WHITE);
			ticketPanel.add(lblNombre, tgbc);

			tgbc.gridy++;
			JLabel lblEntradas = new JLabel("Entradas: " + t.getTotal_entradas());
			lblEntradas.setForeground(Color.LIGHT_GRAY);
			ticketPanel.add(lblEntradas, tgbc);

			gbc.gridy = i;
			gbc.weighty = 0;
			content.add(ticketPanel, gbc);
		}

		// Espaciador final
		gbc.gridy = ranking.size();
		gbc.weighty = 1.0;
		content.add(new JPanel(), gbc);

		// scroll
		JScrollPane scroll = new JScrollPane(content);
		scroll.setBorder(null);
		scroll.getVerticalScrollBar().setUnitIncrement(12); // suaviza scroll

		scroll.getViewport().setBackground(FONDO);

		return scroll;
	}

}
