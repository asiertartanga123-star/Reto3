package ui.user.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.element.PanelStyle;
import ui.user.game_object.ActorPrincipal;

public class PanelInicio extends JPanel {
	

	private static final long serialVersionUID = 1L;

	ActorPrincipal acPrin = new ActorPrincipal(this);
	private boolean running = false;

	public PanelInicio() {
		setLayout(new BorderLayout());
		setBackground(Color.blue);
		add(new PanelStyle.BackgroundPanel("/res/img/background_login_user.jpg"));

		
		

		// activo el input del teclado
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				acPrin.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				acPrin.keyPressed(e);
			}
		});

		setFocusable(true);
	}

	public void move() {
		acPrin.move();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		acPrin.paint(g2d);
	}

	public void runPInicio() throws InterruptedException {
		running = true;
		while (running) {
			this.move();
			this.repaint();
			Thread.sleep(10);
		}
	}

	public void startGame() {
		if (running)
			return;
		new Thread(() -> {
			try {
				runPInicio();
			} catch (InterruptedException e) {
				// cojo el hilo que solto esta excepcion y lo marco que fue interrumpida
				// interrupt otorga un flag de que se quiere interrumpir (no hace nada por si
				// sola pero, puedo ponerle eventos o acciones que quiero que realice si pasa
				// esto, como solo es un hilo no hago nada mas que eso)
				// Thread.currentThread().interrupt();
			}
		}).start();
	}

	public void stopGame() {
		running = false;
	}
	
	public void obtenerDimenTo() {
		System.out.println("Alto: "+this.getHeight() + "Ancho: "+this.getWidth());
		
	}
}