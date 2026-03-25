package ui.user.game_object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class ActorPrincipal {

	private static final int W_H = 100; // ancho y alto

	int y = 0; // cordenada Y |
	int ya = 0;
	int x = 0; // cordenada x inicial -
	// xa -> cordenada del input del teclado
	int xa = 0;
	private JPanel game;

	public ActorPrincipal(JPanel game) {
		this.game = game;
	}

	public void move() {
		/*
		 * condicion input, controlar que no pace el width del jpanel restando el with
		 * del actor
		 * cambio la cordenada en la que se situa el objeto
		 */
		if (x + xa >= 0 && x + xa <= game.getWidth() - W_H)
			x = x + xa;
		if (y + ya >= 0 && y + ya <= game.getHeight() - W_H)
			y = y + ya;

	}

	public void paint(Graphics2D g) {
		// diguja un rectangulo con los atributos anteriormente dados dentro del jpane
		g.fillRect(x, y, W_H, W_H);
	}

	// 0 si esta quieto
	public void keyReleased(KeyEvent e) {
		// cuando se suelta la tecla, detenemos el movimiento
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT)
			xa = 0;
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN)
			ya = 0;
	}

	// detect acuando el teclado esta pulsado
	public void keyPressed(KeyEvent e) {
	    // izquierda
	    if (e.getKeyCode() == KeyEvent.VK_LEFT)
	        xa = -10;
	    // derecha
	    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
	        xa = 10;
	    // arriba
	    if (e.getKeyCode() == KeyEvent.VK_UP)
	        ya = -10;
	    // abajo
	    if (e.getKeyCode() == KeyEvent.VK_DOWN)
	        ya = 10;
	}

	// retorna el objeto, con las cordenadas donde esta
	public Rectangle getBounds() {
		
		return new Rectangle(x, y, W_H, W_H);
	}

	public int getTopY() {
		return y - W_H;
	}
}
