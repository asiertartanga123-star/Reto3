package ui.user.panel;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

import model.user.TicketInfo;
import ui.element.ControlObjects;

public class PanelTicket extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final Color FONDO = Color.BLACK;
	private static final Color PRIMARIO = new Color(0, 0, 255);
	private static final Color TEXTO = Color.WHITE;

	public PanelTicket(ArrayList<TicketInfo> tickets) {

		setLayout(new BorderLayout());
		setBackground(FONDO);
		setBorder(new EmptyBorder(20, 20, 20, 20));

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		JPanel content = new JPanel(new GridBagLayout());
		content.setBackground(FONDO);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 0, 10, 0);
		gbc.anchor = GridBagConstraints.NORTH;

		for (int i = 0; i < tickets.size(); i++) {

			TicketInfo t = tickets.get(i);

			JPanel ticketPanel = new JPanel(new GridBagLayout());
			ticketPanel.setBackground(FONDO);
			ticketPanel.setBorder(new CompoundBorder(new LineBorder(PRIMARIO, 1), new EmptyBorder(15, 15, 15, 15)));

			GridBagConstraints tgbc = new GridBagConstraints();
			tgbc.gridx = 0;
			tgbc.gridy = 0;
			tgbc.anchor = GridBagConstraints.WEST;
			tgbc.insets = new Insets(5, 5, 5, 5);

			// Título
			JLabel lblTitulo = new JLabel(t.getNombreCine());
			lblTitulo.setForeground(PRIMARIO);
			lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
			tgbc.gridwidth = 2;
			ticketPanel.add(lblTitulo, tgbc);

			tgbc.gridy++;
			tgbc.gridwidth = 1;

			agregarFila(ticketPanel, tgbc, "Usuario:", t.getUserName());
			agregarFila(ticketPanel, tgbc, "Película:", t.getTituloPeli());
			agregarFila(ticketPanel, tgbc, "Sala:", String.valueOf(t.getNumSala()));
			agregarFila(ticketPanel, tgbc, "Butaca:", String.valueOf(t.getNumButaca()));
			agregarFila(ticketPanel, tgbc, "Precio:", "€ " + t.getPrecio());
			agregarFila(ticketPanel, tgbc, "Fecha compra:", t.getFecha_adqui().format(fmt));
			agregarFila(ticketPanel, tgbc, "Fecha función:", t.getFecha_emision().format(fmt));

			// botno reembolso
			if (util.validation.ValidacionUser.seReembolsa(t)) {

				JButton btnReembolso = ControlObjects.botonMenu("Solicitar reembolso");
				btnReembolso.addActionListener(e -> {
					System.out.println("Reembolso solicitado:");
					System.out.println("Sala: " + t.getNumSala());
					System.out.println("Pelicula: " + t.getId_peli());
					System.out.println("Fecha transmision: " + t.getFecha_emision());
					System.out.println("Butaca: " + t.getNumButaca());
				});

				// posicion del boton
				tgbc.gridx = 1;
				tgbc.gridwidth = 2;
				tgbc.anchor = GridBagConstraints.CENTER;
				ticketPanel.add(btnReembolso, tgbc);

				tgbc.gridy++;
				tgbc.gridwidth = 1;
				tgbc.anchor = GridBagConstraints.WEST;
			}
			gbc.gridy = i;
			content.add(ticketPanel, gbc);
		}

		gbc.gridy = tickets.size();
		gbc.weighty = 1.0;
		content.add(new JPanel(), gbc);

		JScrollPane scroll = new JScrollPane(content);
		scroll.setBorder(null);
		scroll.getVerticalScrollBar().setUnitIncrement(16);
		scroll.getViewport().setBackground(FONDO);

		add(scroll, BorderLayout.CENTER);
	}

	private void agregarFila(JPanel panel, GridBagConstraints gbc, String label, String valor) {

		JLabel lbl = new JLabel(label);
		lbl.setForeground(PRIMARIO);
		lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		JLabel val = new JLabel(valor);
		val.setForeground(TEXTO);
		val.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		gbc.gridx = 0;
		gbc.weightx = 0.3;
		gbc.fill = GridBagConstraints.NONE;
		panel.add(lbl, gbc);

		gbc.gridx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(val, gbc);

		gbc.gridy++;
	}
}