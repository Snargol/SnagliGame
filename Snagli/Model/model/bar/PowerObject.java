package model.bar;

import model.enumeration.GrabableEffect;
import model.enumeration.Power;
import model.genercis.Frame;
import model.genercis.Position;

public class PowerObject {
	private GrabableEffect grabableEffect;
	private Frame frame;
	private int number;
	private Position position;
	//private Position textPosition;
	private Size size;
	
	public PowerObject(GrabableEffect grabableEffect, Frame frame, int number, Position position, Size size) {
		setGrabableEffect(grabableEffect);
		setFrame(frame);
		setNumber(number);
		setPosition(position);
		setSize(size);
//		setTextPosition(new Position(position.getX() + getSize().getWidth() - (getSize().getWidth()/4),
//				position.getY() + getSize().getHeight() + 5));
	}
	
	public PowerObject(GrabableEffect grabableEffect, Frame frame, Position position) {
		setGrabableEffect(grabableEffect);
		setFrame(frame);
		setNumber(getNumber()+1);
		setPosition(new Position(0,0));
//		setTextPosition(new Position(position.getX() + getSize().getWidth() - (getSize().getWidth()/4),
//				position.getY() + getSize().getHeight() + 5));
	}

	public GrabableEffect getGrabableEffect() {
		return grabableEffect;
	}

	public void setGrabableEffect(GrabableEffect grabableEffect) {
		this.grabableEffect = grabableEffect;
	}

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public Position getTextPosition() {
		return new Position(position.getX() + getSize().getWidth() - (getSize().getWidth()/4),
				position.getY() + getSize().getHeight() + 5);
	}

//	public void setTextPosition(Position textPosition) {
//		this.textPosition = textPosition;
//	}
	
	
}
