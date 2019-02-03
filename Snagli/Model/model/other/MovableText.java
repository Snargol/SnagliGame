package model.other;

import java.awt.Color;

import model.genercis.Position;

public class MovableText extends Text{
	
	/*------------------------------------------------------------------------------*/
	/*                                 ATTRIBUTES                                   */
	/*------------------------------------------------------------------------------*/
	
	/*------------------------------------------------------------------------------*/
	/*                            CONSTRUCTORS/DESTRUCTORS                          */
	/*------------------------------------------------------------------------------*/

	public MovableText(int text, Position position, Color color) {
		super(text, position, color);
	}
	
	public MovableText(String text, Position position) {
		super(text, position);
	}
	
	/*------------------------------------------------------------------------------*/
	/*                                 METHODS                                     */
	/*------------------------------------------------------------------------------*/

	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/

	@Override
	public Position getPosition() {
		if ((initialHeight - position.getY()) <= height ) {
			position.addY(-1);
		}
		else {
			isAlive = false;
		}
		return position;
	}

}
