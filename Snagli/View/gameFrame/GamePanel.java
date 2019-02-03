package gameFrame;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

import view.GraphicsBuilder;

public class GamePanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;

	private final GraphicsBuilder graphicsBuilder;
	
	public GamePanel(GraphicsBuilder graphicsBuilder) {
		this.graphicsBuilder = graphicsBuilder;
	}

	@Override
	public void update(Observable observable, Object arg) {
		this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.graphicsBuilder.applyModelToGraphic(g);
	}
}
