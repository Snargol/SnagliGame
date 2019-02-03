package gameFrame;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import view.GraphicsBuilder;
	

public class GameFrame extends JFrame implements KeyListener, Runnable {
	
	private static final long serialVersionUID = 1L;
	int width;
	int height;
	//----------------------------Key Boolean------------------------------------------

	
	String orderDirection ;
	String orderFire ;
	String orderJump ;
	String orderSprint;
	String orderActivePower ;
	
	private boolean up;
	private boolean down;
	private boolean right;
	private boolean left;
	
	
	//-----------------------------eventPerformer---------------------------------------

	Observable observable;

	GraphicsBuilder graphicsBuilder;

	String title;
	
	//D---------------------------detect the key in use and start eventPerform--------------

	public GameFrame(final String title, final GraphicsBuilder graphicsBuilder, final Observable observable, int width, int height){
		this.observable = observable;
		this.graphicsBuilder = graphicsBuilder;
		this.title = title;
		this.width = width;
		this.height = height;
		
		this.addKeyListener(this);
		SwingUtilities.invokeLater(this);
	}
	
	public String generateKeyDirection() {

		if (right) {
			return "0010";
		}
		else if (left) {
			return "0100";
		}
		else if (up) {
			return "0001";
		}
		else if (down) {
			return "0011";
		}
		else {
			return "0000";
		}
	}
	
	
	@Override
	public void keyPressed(final KeyEvent keyEvent) {
		int key = keyEvent.getKeyCode();
		if (key == KeyEvent.VK_Z)
			up = true;
		if (key == KeyEvent.VK_D)
			right = true;
		if (key == KeyEvent.VK_S)
			down = true;
		if (key == KeyEvent.VK_Q)
			left = true;
		
		if (key == KeyEvent.VK_SPACE)
			orderJump = "0001";
		if (key == KeyEvent.VK_A) 
			orderSprint = "0001";
		if (key == KeyEvent.VK_B)
			orderFire = "0001";
		
		if (key == KeyEvent.VK_NUMPAD1) {
			orderActivePower ="1";
			return;
		}
		if (key == KeyEvent.VK_NUMPAD2) {
			orderActivePower ="2";
			return;
		}
		if (key == KeyEvent.VK_NUMPAD3) {
			orderActivePower ="3";
			return;
		}
		if (key == KeyEvent.VK_NUMPAD4) {
			orderActivePower ="4";
			return;
		}
		if (key == KeyEvent.VK_NUMPAD5) {
			orderActivePower ="5";
			return;
		}
		if (key == KeyEvent.VK_NUMPAD6) {
			orderActivePower ="6";
			return;
		}
		if (key == KeyEvent.VK_NUMPAD7) {
			orderActivePower ="7";
			return;
		}
		if (key == KeyEvent.VK_NUMPAD8) {
			orderActivePower ="8";
			return;
		}
		if (key == KeyEvent.VK_NUMPAD9) {
			orderActivePower ="9";
			return;
		}
		
	}
	
	@Override
	public void keyReleased(final KeyEvent keyEvent) {
		// TODO Auto-generated method stub
		int key = keyEvent.getKeyCode();
		if (key == KeyEvent.VK_Z)
			up = false;
		if (key == KeyEvent.VK_D) {
			right = false;
			orderSprint = "0000";//if D is released then the sprint is over
		}
		if (key == KeyEvent.VK_S)
			down = false;
		if (key == KeyEvent.VK_Q) {
			left = false;
			orderSprint = "0000"; //if Q is released then the sprint is over
		}
		if (key == KeyEvent.VK_SPACE)
			orderJump = "0000";
		if (key == KeyEvent.VK_B)
			orderFire = "0000";
		
		
		if (key == KeyEvent.VK_NUMPAD1 || key == KeyEvent.VK_NUMPAD2 || key == KeyEvent.VK_NUMPAD3 ||
				key == KeyEvent.VK_NUMPAD4 || key == KeyEvent.VK_NUMPAD5 || key == KeyEvent.VK_NUMPAD6 || key == KeyEvent.VK_NUMPAD1 
				|| key == KeyEvent.VK_NUMPAD7 || key == KeyEvent.VK_NUMPAD8 || key == KeyEvent.VK_NUMPAD9 ) {
			orderActivePower = "0";
			return;
		}
	}
	
	@Override
	public void keyTyped(final KeyEvent keyEvent) {
		
		
	}

	public String[] getOrders() {
		orderDirection = generateKeyDirection();
		String[] orders = {orderDirection,orderFire,orderJump,orderSprint,orderActivePower};
		return orders;
	}
	
	public void resetOrders() {
		orderDirection = "0000";
		orderFire = "0000";
		orderJump = "0000";
		orderSprint = "0000";
		orderActivePower = "0";
		
	}

	@Override
	public void run() {
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setResizable(false);
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//this.setUndecorated(true);
		this.setVisible(true);
		this.setBackground(Color.CYAN);
		
		final GamePanel gamePanel = new GamePanel(graphicsBuilder);
		this.setContentPane(gamePanel);
		//this.setSize(width*50,height*50+25);
		this.setSize(1400, 800);
		this.setLocationRelativeTo(null);
		observable.addObserver(gamePanel);
		this.setVisible(true);
	}
	
}