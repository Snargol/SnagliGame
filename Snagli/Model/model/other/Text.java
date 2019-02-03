package model.other;

import java.awt.Color;

import model.genercis.Position;

public class Text {
	/*------------------------------------------------------------------------------*/
	/*                                 ATTRIBUTES                                   */
	/*------------------------------------------------------------------------------*/
	private String text = new String();
	protected Position position;
	protected boolean isAlive = true;
	protected int initialHeight;
	protected int height = 50;	
	private Color color;
	/*------------------------------------------------------------------------------*/
	/*                            CONSTRUCTORS/DESTRUCTORS                          */
	/*------------------------------------------------------------------------------*/
	public Text(int text, Position position, Color color) {
		this.text = (text >= 0)? "+ " + text : " " + text; 
		this.position = position;
		initialHeight = position.getY();
		this.color = color;
	}

	public Text(String text, Position position) {
		this.text = text;
		this.position = position;
		initialHeight = position.getY();
	}
	
	public Text(Position position) {
		this.text = "";
		this.position = position;
		initialHeight = position.getY();
	}
	/*------------------------------------------------------------------------------*/
	/*                                 METHODS                                     */
	/*------------------------------------------------------------------------------*/



	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public Position getPosition() {
		if (!((initialHeight - position.getY()) <= height )) {
			isAlive = false;
		}

		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public Color getColor() {
		return color;
	}



}
