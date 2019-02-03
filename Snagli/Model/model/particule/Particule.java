package model.particule;

import model.enumeration.EffectWhenTutch;
import model.enumeration.Opacity;
import model.genercis.Element;
import model.genercis.Position;
import model.other.Random;

public class Particule extends Element{
	private int timeLife;
	private long startTime;
	private int positionX;
	private int positionY;
	/*------------------------------------------------------------------------------*/
	/*                                 ATTRIBUTES                                   */
	/*------------------------------------------------------------------------------*/

	/*------------------------------------------------------------------------------*/
	/*                            CONSTRUCTORS/DESTRUCTORS                          */
	/*------------------------------------------------------------------------------*/
	public Particule( String name, Position position,int positionX, int positionY, int timeLife, long startTime) {
		super(0, Random.alea(3), 4, null, null, name, 32, 32, 30, 30, position,EffectWhenTutch.NONE);
		setStartTime(startTime);
		setTimeLife(timeLife);
		setPositionX(positionX);
		setPositionY(positionY);
	}
	
	public Particule( String name, int lineSpriteSheet, int numberSprites, Position position, int timeLife, int sizeXFrame, int sizeYFrame, int sizeXDisplay, int sizeYDisplay, long startTime) {
		super(0, lineSpriteSheet, numberSprites, null, null, "Particules/", name, sizeXFrame, sizeYFrame, sizeXDisplay, sizeYDisplay, position, EffectWhenTutch.NONE);
		setStartTime(startTime);
		setTimeLife(timeLife);
		setPositionX(positionX);
		setPositionY(positionY);
	}
	/*------------------------------------------------------------------------------*/
	/*                                 METHODS                                     */
	/*------------------------------------------------------------------------------*/
	public boolean isAlive(long time) {
		if (time - startTime >= timeLife)
			return false;
		return true;
	}
	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/
	public int getTimeLife() {
		return timeLife;
	}
	public void setTimeLife(int timeLife) {
		this.timeLife = timeLife;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public int getPositionX() {
		return positionX;
	}
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	public int getPositionY() {
		return positionY;
	}
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}


	
	

}
