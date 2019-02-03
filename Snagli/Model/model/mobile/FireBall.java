package model.mobile;

import model.enumeration.Direction;
import model.enumeration.EffectWhenTutch;
import model.enumeration.Opacity;
import model.genercis.HitBoxe;
import model.genercis.Position;

public class FireBall extends MobileEntity {
	private int actualFunction = 1;
	private int upFunction = 1;
	private int roundedFunction = 2;
	private int downFunction = 3;
	private int sens;
	private int compteur;
	private Position savePosition;


	public FireBall(Direction direction, Position position) {
		super(0, 0, 4, "FireBall", Opacity.PENETRABLE, new HitBoxe(50,50), position, 50, 50, 40,
				40, EffectWhenTutch.BURN);
		setSpeed(4);
		setSens((direction == Direction.LEFT)? -1 : 1);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void move() {
		Position newPos = new Position(0,0);
		setPosition(new Position(getPosition().getX() + getSpeed(), getSavePosition().getY() + (getCurrentFunction(compteur) * sens)));;
		setCompteur(getCompteur() +1);
	}

	private int getUp(int x) {
		return (int) 0.8 * x;
	}
	
	private int getCurrentFunction(int x) {
		if (getActualFunction() == 1) {
			return getUp(x);
		}
		else if (getActualFunction() == 2) {
			return getrounded(x);
		}
		else {
			return getDown(x);
		}
	}
	
	private int getDown(int x) {
		return (int) -0.8 * x;
	}
	
	
	private int getrounded(int x) {
		return (int) -0.05 * (x * x) + 5;
	}

	public int getSens() {
		return sens;
	}

	public void setSens(int sens) {
		this.sens = sens;
	}

	public int getActualFunction() {
		return actualFunction;
	}

	public void setActualFunction(int actualFunction) {
		this.actualFunction = actualFunction;
	}

	public int getCompteur() {
		return compteur;
	}

	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}
	
	public void reenitCompteur() {
		this.compteur = 0;
	}

	public Position getSavePosition() {
		return savePosition;
	}

	public void setSavePosition(Position savePosition) {
		this.savePosition = savePosition;
	}
	
	
	
}
