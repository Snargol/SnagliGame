package model.particule;

import model.enumeration.Direction;
import model.enumeration.EffectWhenTutch;
import model.enumeration.Opacity;
import model.genercis.Element;
import model.genercis.Frame;
import model.genercis.Position;
import model.other.Random;

public class Debris extends Element{
	/*------------------------------------------------------------------------------*/
	/*                                 ATTRIBUTES                                   */
	/*------------------------------------------------------------------------------*/
	private int heightGAP;
	private int widthGap;
	private int iteration = 12;
	private int x = -iteration;
	private int y = 0;
	private int direction = 1;

	/*------------------------------------------------------------------------------*/
	/*                            CONSTRUCTORS/DESTRUCTORS                          */
	/*------------------------------------------------------------------------------*/
	public Debris(String name, Position position, int widthGAP, int heightGAP, Direction direction) {
		super(0, Random.alea(3), 4, null, null, name, 32, 32, 40, 40, position,EffectWhenTutch.NONE);
		this.heightGAP = heightGAP;
		this.widthGap = widthGAP;
		//if the direction is left, then iteration is negative
		this.direction = (direction == Direction.LEFT)? -this.direction : this.direction;
	}
	/*------------------------------------------------------------------------------*/
	/*                                 METHODS                                     */
	/*------------------------------------------------------------------------------*/
	private void calculatePosition() {
		x += iteration;
		
		//System.out.println(x + "-  1 : " + ((double) widthGap)/6 + " 2 : " + ((double)widthGap) /2);
		if (x <= (int) Math.round(((double) widthGap)/3)) {
			y = (int) Math.round((0.67 * heightGAP) / (widthGap / 3));
			position.setPosition((int) position.getX()  + (iteration*direction), position.getY() - y);
		}
		else if (x <= (int) Math.round((((double)widthGap) /3)*2)) {
			y = (int) Math.round((heightGAP/widthGap));
			position.setPosition((int) position.getX()  + (iteration*direction), position.getY() - y);
		}
		else if (x <= (int) Math.round((((double)widthGap) /6)*5)) {
			//y = (int) Math.round(0);
			position.setPosition((int) position.getX() + (iteration*direction), position.getY());
		}
		else if (x < widthGap) {
			y = (int) Math.round((-heightGAP/widthGap));
			position.setPosition((int) position.getX() + (iteration*direction), position.getY() - y);
		}
		else {

			position.setPosition((int) position.getX() + (Random.alea(3) * direction), position.getY() + iteration);
		}
	

		
	}
	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/
	@Override
	public Position getPosition() {
		calculatePosition();
		return position;

	}

	public boolean isAlive(int heightmini) {
		if (position.getY() > heightmini*50 || position.getY() < 0) {
			return false;
		}
		return true;
	}


	
}
