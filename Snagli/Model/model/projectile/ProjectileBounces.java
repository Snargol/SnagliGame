package model.projectile;

import model.enumeration.BehaviourProjectile;
import model.enumeration.Direction;
import model.enumeration.Opacity;
import model.enumeration.Projectiles;
import model.genercis.HitBoxe;
import model.genercis.Position;

public class ProjectileBounces extends Projectile{
	private int damage;
	private int numberOfBounds = 0;
	private int numberMaxOfBounds = 0;
	private int actualMove; // 0 : UP - 1 : Curve - 2 : DOWN
	private Position savePoint;
	private int compteur;

	public ProjectileBounces(int xImagePos, int yImagePos, int nbSprites, Opacity opacity, HitBoxe hitboxe, String name,
			BehaviourProjectile behaviour, int widthFrame, int heightFrame, int widthDisplay, int heightDisplay,
			Position position, Direction direction, Projectiles typeProjectile) {
		super(xImagePos, yImagePos, nbSprites, opacity, hitboxe, name, behaviour, widthFrame, heightFrame, widthDisplay,
				heightDisplay, position, direction, typeProjectile);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isAlive() {
		if (getNumberOfBounds() < getNumberBondMax()) {
			return false;
		}
		return false;
		
	}
	
	

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getNumberOfBounds() {
		return numberOfBounds;
	}

	public void setNumberOfBounds(int numberOfBounds) {
		this.numberOfBounds = numberOfBounds;
	}

	public int getActualMove() {
		return actualMove;
	}

	public void setActualMove(int actualMove) {
		this.actualMove = actualMove;
	}

	public Position getSavePoint() {
		return savePoint;
	}

	public void setSavePoint(Position savePoint) {
		this.savePoint = savePoint;
	}

	public int getCompteur() {
		return compteur;
	}

	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}
	
	

}
