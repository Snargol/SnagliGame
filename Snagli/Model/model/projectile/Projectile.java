package model.projectile;

import model.enumeration.BehaviourProjectile;
import model.enumeration.Direction;
import model.enumeration.EffectWhenTutch;
import model.enumeration.Opacity;
import model.enumeration.Projectiles;
import model.genercis.Element;
import model.genercis.HitBoxe;
import model.genercis.Position;

public class Projectile extends Element{
	/*------------------------------------------------------------------------------*/
	/*                                 ATTRIBUTES                                   */
	/*------------------------------------------------------------------------------*/
	private long timeLife;
	private int numberBondMax;
	private int actualNumberBond;
	private int speed;
	private Direction direction;
	private Projectiles projectile;
	private BehaviourProjectile behaviour;

	/*------------------------------------------------------------------------------*/
	/*                            CONSTRUCTORS/DESTRUCTORS                          */
	/*------------------------------------------------------------------------------*/
	public Projectile(int xImagePos, int yImagePos, int nbSprites, Opacity opacity,HitBoxe hitboxe,  String name, BehaviourProjectile behaviour, int widthFrame,
			int heightFrame, int widthDisplay, int heightDisplay, Position position, Direction direction, Projectiles typeProjectile) {
		super(xImagePos, yImagePos, nbSprites, opacity, hitboxe, name, widthFrame, heightFrame, widthDisplay, heightDisplay, position, EffectWhenTutch.NORMAL_DAMAGE);
		setProjectile(typeProjectile);
		setDirection(direction);
		setSpeed(10);
		setBehaviour(behaviour);

	}
	/*------------------------------------------------------------------------------*/
	/*                                 METHODS                                     */
	/*------------------------------------------------------------------------------*/
	public void move() {
		move(getDirection());
	}
	
	public void move(Direction direction) {
		switch (direction) {
		case RIGHT :
			getPosition().addX(getSpeed());
			break;
		case LEFT : 
			getPosition().addX(-getSpeed());
			break;
		case UP : 
			getPosition().addY(-getSpeed());
			break;
		case DOWN : 
			System.out.println("down");
		default :
			getPosition().addX(getSpeed());
			break;
		}

	}
	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/

	public long getTimeLife() {
		return timeLife;
	}
	public void setTimeLife(long timeLife) {
		this.timeLife = timeLife;
	}
	public int getNumberBondMax() {
		return numberBondMax;
	}
	private void setNumberBondMax(int numberBondMax) {
		this.numberBondMax = numberBondMax;
	}
	public int getActualNumberBond() {
		return actualNumberBond;
	}
	public void setActualNumberBond(int actualNumberBond) {
		this.actualNumberBond = actualNumberBond;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public Projectiles getProjectile() {
		return projectile;
	}
	public void setProjectile(Projectiles projectile) {
		this.projectile = projectile;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		switch (projectile) {
		case MISSILE :
			switch (direction) {
			case RIGHT:
				createSpriteArray(0, 0, 4, 50, 50);
				break;
			case LEFT :
				createSpriteArray(0, 1, 4, 50, 50);
				break;
			case UP :
				createSpriteArray(0, 2, 4, 50, 50);
				break;
			default :
				createSpriteArray(0, 0, 4, 50, 50);
				break;
			}
			break;
		default :

		}
		this.direction = direction;
	}
	public BehaviourProjectile getBehaviour() {
		return behaviour;
	}
	public void setBehaviour(BehaviourProjectile behaviour) {
		this.behaviour = behaviour;
	}

	

}
