package ui.element;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelStyle {
	public static class BackgroundPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final Image img;

		public BackgroundPanel(String path) {
			img = new ImageIcon(getClass().getResource(path)).getImage();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
	}

	public static class RoundedPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final Color bg;
		private final int radius;

		public RoundedPanel(Color bg, int radius) {
			this.bg = bg;
			this.radius = radius;
			setOpaque(false);
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(bg);
			g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
			super.paintComponent(g);
		}
	}

}
