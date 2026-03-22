package ui.user.panel;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dao.DaoUser;
import dic.user.PTicketString;
import model.Entrada;
import model.user.TicketInfo;
import ui.element.ControlObjects;
import util.validation.ValidacionUser;
import ui.element.*;

public class PanelTicket extends JPanel {

	/*
	 * 
	 * */
	private static final long serialVersionUID = 1L;

	// paleta de colores
	private static final Color CARD = new Color(255, 255, 255, 40);
	private static final Color TEXTO = new Color(255, 255, 255, 220);
	private static final Color TEXTO_SEC = new Color(200, 200, 200, 180);

	private DaoUser daoUser = new DaoUser();
	private PTicketString pts;

	public PanelTicket(String userName, PTicketString pts) throws Exception {
		this.pts = pts;
		ArrayList<TicketInfo> tickets = daoUser.obtenerTicketsUsuario(userName);
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		setBorder(new EmptyBorder(20, 20, 20, 20));

		agregarContenido(tickets);
	}

	private void agregarContenido(ArrayList<TicketInfo> tickets) {

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		JPanel content = new JPanel(new GridBagLayout());
		content.setOpaque(false);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 0, 10, 0);
		gbc.anchor = GridBagConstraints.NORTH;

		for (int i = 0; i < tickets.size(); i++) {

			TicketInfo t = tickets.get(i);

			// panel ticket
			PanelStyle.RoundedPanel ticketPanel = new PanelStyle.RoundedPanel(CARD, 20);
			ticketPanel.setLayout(new GridBagLayout());

			// contendor para el borde transparente
			JPanel contenedor_trans = new JPanel(new BorderLayout());
			contenedor_trans.setOpaque(false);
			contenedor_trans.add(ticketPanel, BorderLayout.CENTER);

			GridBagConstraints tgbc = new GridBagConstraints();
			tgbc.gridx = 0;
			tgbc.gridy = 0;
			tgbc.anchor = GridBagConstraints.WEST;
			tgbc.insets = new Insets(5, 5, 5, 5);

			// titulo
			JLabel lblTitulo = new JLabel(t.getNombreCine());
			lblTitulo.setForeground(TEXTO);
			lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));

			tgbc.gridwidth = 2;
			ticketPanel.add(lblTitulo, tgbc);

			tgbc.gridy++;
			tgbc.gridwidth = 1;

			agregarFila(ticketPanel, tgbc, pts.getUSUARIO(), t.getUserName());
			agregarFila(ticketPanel, tgbc, pts.getPELICULA(), t.getTituloPeli());
			agregarFila(ticketPanel, tgbc, pts.getSALA(), String.valueOf(t.getNumSala()));
			agregarFila(ticketPanel, tgbc, pts.getBUTACA(), String.valueOf(t.getNumButaca()));
			agregarFila(ticketPanel, tgbc, pts.getPRECIO(), "€ " + t.getPrecio());
			agregarFila(ticketPanel, tgbc, pts.getFECHA_COMP(), t.getFecha_adqui().format(fmt));
			agregarFila(ticketPanel, tgbc, pts.getFECHA_FUN(), t.getFecha_emision().format(fmt));

			if (util.validation.ValidacionUser.seReembolsa(t)) {

				JButton btnReembolso = ControlObjects.botonMenu(pts.getBOTON());

				btnReembolso.addActionListener(e -> {
					eliminarEntrada(t.getUserName(), t.getNumSala(), t.getId_peli(), t.getFecha_emision(),
							t.getNumButaca());
				});

				tgbc.gridx = 1;
				tgbc.gridwidth = 2;
				tgbc.anchor = GridBagConstraints.CENTER;

				ticketPanel.add(btnReembolso, tgbc);

				tgbc.gridy++;
				tgbc.gridwidth = 1;
				tgbc.anchor = GridBagConstraints.WEST;
			}

			gbc.gridy = i;
			content.add(contenedor_trans, gbc);
		}

		// filler
		gbc.gridy = tickets.size();
		gbc.weighty = 1.0;
		content.add(new JPanel(), gbc);

		JScrollPane scroll = new JScrollPane(content);
		ControlObjects.aplicarScrollTransparente(scroll, CARD);
		scroll.setBorder(null);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		scroll.getVerticalScrollBar().setUnitIncrement(16);

		add(scroll, BorderLayout.CENTER);
	}

	private void agregarFila(JPanel panel, GridBagConstraints gbc, String label, String valor) {

		JLabel lbl = new JLabel(label);
		lbl.setForeground(TEXTO_SEC);
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

	private void eliminarEntrada(String user, int numSala, int idPeli, LocalDateTime fechaEmision, int numBut) {
		// Crear objeto Entrada
		Entrada e = new Entrada(user, numSala, idPeli, numBut, 0, fechaEmision, null);

		// Ventana de confirmación
		int opcion = JOptionPane.showConfirmDialog(this, pts.getPreguntaEliminar(), pts.getTituloConfirmar(),
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (opcion == JOptionPane.YES_OPTION) {
			ValidacionUser.controlExcepcionIrremediable(() -> {
				if (daoUser.eliminarEntrada(e)) {
					JOptionPane.showMessageDialog(this, pts.getExitoEliminar(), pts.getTituloExito(),
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, pts.getNoEncontrado(), pts.getTituloAviso(),
							JOptionPane.WARNING_MESSAGE);
				}

			}, pts.getErrorEliminar(), "ERROR DELETE USER",false);

		} else {
			// Usuario canceló la operación
			JOptionPane.showMessageDialog(this, pts.getReembolsoCancelado(), pts.getTituloCancelado(), JOptionPane.INFORMATION_MESSAGE);
		}
	}
}