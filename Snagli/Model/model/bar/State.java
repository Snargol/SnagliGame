package model.bar;

import model.enumeration.EffectWhenTutch;
import model.genercis.Element;
import model.genercis.Position;

public class State extends Element{
	private int width = 50;
	private int height = 50;
	public State(String name, int nbSprites, Position position) {
		super(0, 0, nbSprites, null, null, "Bar/States/", name, 32, 32, 50, 50, new Position(position.getX() + 5, position.getY() + 5), EffectWhenTutch.NORMAL_DAMAGE);
		// TODO Auto-generated constructor stub
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
