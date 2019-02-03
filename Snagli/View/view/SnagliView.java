package view;

import java.util.Observable;

import javax.swing.JOptionPane;

import gameFrame.GameFrame;
import model.genercis.SnagliModel;


@SuppressWarnings("deprecation")
public class SnagliView{
	
	private GraphicsBuilder graphicsBuilder;
	private final Observable observable;
	private GameFrame gameFrame;
	private SnagliModel model;
	
	public SnagliView(SnagliModel model , Observable observable) {
		this.observable = observable;
		this.model = model;
		//this.graphicsBuilder = new GraphicsBuilder(model);
		//this.gameFrame = new GameFrame("Snagli", this.graphicsBuilder, this.observable);
		
	}

	public void displayMessage(final String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public void closeAll() {
		this.gameFrame.dispose();
	}

	public String[] getKeys() {
		return this.gameFrame.getOrders();
	}
	
	public void resetKeys() {
		this.gameFrame.resetOrders();
	}
	
	public GameFrame getGameFrame() {
        return gameFrame;
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }
    
    public void setModelHasLoad() {
		this.graphicsBuilder = new GraphicsBuilder(model);
		this.gameFrame = new GameFrame("Snagli", this.graphicsBuilder, this.observable, model.getMap().getWidth(), model.getMap().getHeight());
    }
    
    
}