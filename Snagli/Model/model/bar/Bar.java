package model.bar;

import model.genercis.FrameBar;
import model.genercis.Position;

public class Bar {

	/*------------------------------------------------------------------------------*/
	/*                                 PARAMETRES                                   */
	/*------------------------------------------------------------------------------*/
	private Position position;
	
	/*------------------------------------------------------------------------------*/
	/*                                 CONSTRUCTEURS                                */
	/*------------------------------------------------------------------------------*/
	public Bar(Position position) {
		this.position = position;
	}
	
	/*------------------------------------------------------------------------------*/
	/*                                 METHODES                                     */
	/*------------------------------------------------------------------------------*/
	
	
	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	
	
	
}
