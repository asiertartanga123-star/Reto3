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
import javax.swing.border.EmptyBorder;

import model.user.Ranking;
import ui.element.*;

public class PanelRanking extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final Color TEXTO = new Color(255, 255, 255, 220);
	private static final Color TEXTO_SEC = new Color(220, 220, 220, 180);

	public PanelRanking(ArrayList<Ranking> ranking, String user_name) {
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		setBorder(new EmptyBorder(20, 20, 20, 20));

		add(generarLista(ranking, user_name));
	}

	private JScrollPane generarLista(ArrayList<Ranking> ranking, String user_name) {

		JPanel content = new JPanel(new GridBagLayout());
		content.setOpaque(false);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(10, 0, 10, 0);

		for (int i = 0; i < ranking.size(); i++) {

			Ranking t = ranking.get(i);

			Color tierBase;
			if (t.getPosicion_top() <= 3) {
				tierBase = new Color(220, 53, 69);
			} else if (t.getPosicion_top() <= 7) {
				tierBase = new Color(255, 140, 0);
			} else {
				tierBase = new Color(40, 167, 69);
			}

			if (user_name.equals(t.getUsuario())) {
				tierBase = new Color(0, 120, 255);
			}

			Color tierBackground = new Color(tierBase.getRed(), tierBase.getGreen(), tierBase.getBlue(), 60);

			PanelStyle.RoundedPanel ticketPanel = new PanelStyle.RoundedPanel(tierBackground, 20);
			ticketPanel.setLayout(new GridBagLayout());

			GridBagConstraints tgbc = new GridBagConstraints();
			tgbc.gridx = 0;
			tgbc.gridy = 0;
			tgbc.anchor = GridBagConstraints.WEST;
			tgbc.insets = new Insets(5, 5, 5, 5);

			JLabel lblTitulo = new JLabel("#" + t.getPosicion_top() + " - " + t.getUsuario());
			lblTitulo.setForeground(TEXTO);
			lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));

			tgbc.gridwidth = 2;
			ticketPanel.add(lblTitulo, tgbc);

			tgbc.gridy++;
			tgbc.gridwidth = 1;

			JLabel lblNombre = new JLabel("Nombre: " + t.getNombre());
			lblNombre.setForeground(TEXTO);
			lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 13));
			ticketPanel.add(lblNombre, tgbc);

			tgbc.gridy++;

			JLabel lblEntradas = new JLabel("Entradas: " + t.getTotal_entradas());
			lblEntradas.setForeground(TEXTO_SEC);
			lblEntradas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
			ticketPanel.add(lblEntradas, tgbc);

			gbc.gridy = i;
			content.add(ticketPanel, gbc);
		}

		gbc.gridy = ranking.size();
		gbc.weighty = 1.0;
		content.add(new JPanel(), gbc);

		JScrollPane scroll = new JScrollPane(content);
		ControlObjects.aplicarScrollTransparente(scroll, new Color(255, 255, 255, 40));
		scroll.setBorder(null);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		scroll.getVerticalScrollBar().setUnitIncrement(12);

		return scroll;
	}
}