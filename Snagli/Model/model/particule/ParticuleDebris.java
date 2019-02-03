package model.particule;

import model.enumeration.Direction;
import model.enumeration.Opacity;
import model.genercis.Element;
import model.genercis.Position;
import model.other.Random;

public class ParticuleDebris{
	/*------------------------------------------------------------------------------*/
	/*                                 ATTRIBUTES                                   */
	/*------------------------------------------------------------------------------*/
	private Debris debris1;
	private Debris debris2;
	private Debris debris3;
	private int heightmini;
	/*------------------------------------------------------------------------------*/
	/*                            CONSTRUCTORS/DESTRUCTORS                          */
	/*------------------------------------------------------------------------------*/
	public ParticuleDebris(String name, Position position, int heightmini) {
		position = new Position(position.getX()*50, position.getY()*50);
		setDebris1(new Debris(name, new Position(position), Random.alea(60, 180), Random.alea(300, 480), Direction.RIGHT));
		setDebris2(new Debris(name, new Position(position), Random.alea(60, 180), Random.alea(300, 480), Direction.LEFT));
		setDebris3(new Debris(name, new Position(position), Random.alea(30, 60), Random.alea(500, 680), (Random.alea(1) == 1)? Direction.RIGHT : Direction.LEFT));
		//setDebris3(new Debris(name, new Position(position), Random.alea(60, 120), Random.alea(60, 240), (Random.alea(1) == 1)? Direction.RIGHT : Direction.LEFT));
		this.heightmini = heightmini;
	}
	/*------------------------------------------------------------------------------*/
	/*                                 METHODS                                     */
	/*------------------------------------------------------------------------------*/


	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/

	public Debris getDebris1() {
		return debris1;
	}
	public void setDebris1(Debris debris1) {
		this.debris1 = debris1;
	}
	public Debris getDebris2() {
		return debris2;
	}
	public void setDebris2(Debris debris2) {
		this.debris2 = debris2;
	}
	public Debris getDebris3() {
		return debris3;
	}
	public void setDebris3(Debris debris3) {
		this.debris3 = debris3;
	}

	public boolean isAlive() {
		if (getDebris1() != null && getDebris2() != null  && getDebris3() != null ) {
			if (getDebris1().isAlive(heightmini) && getDebris2().isAlive(heightmini) && getDebris3().isAlive(heightmini)) {
				return true;
			}
		}
		return false;
	}
}
