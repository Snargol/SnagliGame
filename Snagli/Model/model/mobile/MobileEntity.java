package model.mobile;

import java.util.ArrayList;

import model.enumeration.ActionSprite;
import model.enumeration.BlockEffect;
import model.enumeration.Direction;
import model.enumeration.EffectWhenTutch;
import model.enumeration.EntityEffect;
import model.enumeration.Opacity;
import model.enumeration.Resistance;
import model.enumeration.Solidity;
import model.enumeration.State;
import model.genercis.Effect;
import model.genercis.Element;
import model.genercis.HitBoxe;
import model.genercis.Invulnerability;
import model.genercis.Position;

public class MobileEntity extends Element{
	//------------------------------ATTRIBUTS-------------------------------
	//private Position 				position;
	private Opacity 				opacity; // à enlever
	private boolean 				isFreeze 			= false;
	private boolean 				gravity 			= false;
	private boolean 				isJumping 			= false;

	private boolean 				touchFloor			= false;
	private boolean 				climbingLadder		= false;
	private int 					speed 				= 5;
	private int 					life;
	private int 					lifeMax;
	private int 					damage; // à enlever
	private int 					altitude 			= 0;
	private int 					altitudeMax 		= 200;
	//private int 					altitudeMin 		= 45;
	private int						altitudeToJump		= 0;
	private int						speedJump 			= 10;

	private long 					startTime   		= 0;
	private int 					duration    		= 0;
	private Direction 				directionX;
	private Direction 				imposedDirection;
	//private ArrayList<Resistance> 	resistance 	= new ArrayList<>();
	private ArrayList<Invulnerability>  invulnerability = new ArrayList<>();
	private ArrayList<Effect>		effect 		= new ArrayList<>();
	private ArrayList<EntityEffect> effects;
	private State					state;
	private int					 	oldBoxesSize;
	private int 					currentHeal 		= 0;
	private int 					currentRegen 		= 0;
	private long 					previousTimeRegen 	= 0;
	private Solidity 				solidityDestructiblesElements = Solidity.NORMAL;

	//------------------------------DAMAGES----------------------------------
	protected final int 			DAMAGE_FIRE 		= 34;
	protected final int 			DAMAGE_POISON 		= 7;
	protected final int 			DAMAGE_SOFT_PIC	 	= 5;
	protected final int 			DAMAGE_HARD_PIC 	= 20;
	protected final int 			DAMAGE_TOUCH 		= 34;
	protected final int 			DAMAGE_JUMP 		= 34;
	protected final int 			DAMAGE_BIG_JUMP 	= 50;
	int 							actualDamages       = 0;
	//------------------------------------------------------------------------
	protected final int 			HEAL_HEART          = 33;
	protected final int 			REGEN_APPLE         = 50;

	//------------------------------------------------------------------------

	public int getOldBoxesSize() {
		return oldBoxesSize;
	}


	public void setOldBoxesSize(int size) {
		this.oldBoxesSize = size;
	}


	public MobileEntity(int xSpritePos, int ySpritePos, int nbSprites, String name, Opacity opacity,
			HitBoxe hitboxe,  Position position,int widthFrame, int heightFrame, int widthDisplay, int heightDisplay,
			EffectWhenTutch effectWhenTutch) {
		super(xSpritePos, ySpritePos, nbSprites, opacity, hitboxe, name,  widthFrame, heightFrame, widthDisplay, heightDisplay, position, effectWhenTutch);
		setPosition(new Position(position));
	}



	public int getDamage() {
		return damage;
	}

	public int getLifeMax() {
		return lifeMax;
	}

	public void setLifeMax(int lifeMax) {
		this.lifeMax = lifeMax;
	}

	protected void setDamage(int damage) {
		this.damage = damage;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public ArrayList<Invulnerability> getInvulnerability() {
		return invulnerability;
	}

	public void addinvulnerability(Resistance resistance) {
		if (resistance != null) {
			this.invulnerability.add(new Invulnerability(resistance));
		}
	}
	
	public void addinvulnerability(Resistance resistance, long timeLife, long time) {
		if (resistance != null) {
			this.invulnerability.add(new Invulnerability(resistance, timeLife, time));
		}
	}

	public void setState(State state, int duration) {
		this.duration = duration;
		this.state = state;
		addinvulnerability(getResistanceByState(state));
	}

	public void removeInvulnerability(Resistance resistance) {
		int i = 0;
		if (this.invulnerability.size() >= 1) {
			for (Invulnerability inv : this.invulnerability) {
				i++;
				if (inv.getResistance() == resistance) {
					this.invulnerability.remove(i-1);
					return;
				}

			}
		}
	}

	public Resistance getResistanceByState(State state) {
		switch (state) {
		case FIRE :
			return Resistance.FIRE;
		case ICE :
			return Resistance.ICE;
		case POISON :
			return Resistance.POISON;
		case TOUCH :
			return Resistance.TOUCH;
		case SPLASH :
			return Resistance.JUMP;
		case INVILNERABILITY :
			return Resistance.ALL;
		case NONE :
			return null;
		default :
			return null;
		}

	}

	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		
		int lifeBefore = getLife();
		
		if (life >= 100) {
			this.life = 100;
		}
		else if (life <= 0) {
			this.life = 0;
		}
		this.life = life;
		
		if (getLife() < lifeBefore) {
			setState(State.INVILNERABILITY, 1500);
		}
	}
	public boolean alterLife(int life) {
		
		int lifeBefore = getLife();
		
		if ((this.life + life) >= 100) {
			this.life = 100;
		}
		else if (this.life + life <= 0) {
			this.life = 0;
		}
		else {
			this.life = this.life +  life;
		}
		
		if (getLife() != lifeBefore) {
			return true;
		}
		
		return false;
		
//		if (getLife() < lifeBefore) {
//			setState(State.INVILNERABILITY, 1500);
//		}
	}
	public boolean getIsFreeze() {
		return isFreeze;
	}
	public void setFreeze(boolean isFreeze) {
		this.isFreeze = isFreeze;
	}

	//apply SpriteSheet corresponding to the action
	public void applySpriteSheet(ActionSprite action) {

	}

	// apply damage to the entity
	public void applyDamages(int damages) {

	}


	public boolean isResistantBlockEffect(BlockEffect effect) {
		if (getInvulnerability() != null) {
			for (Invulnerability invulnerability : getInvulnerability()) {
				switch (invulnerability.getResistance()) {
				case PICS :
					if (effect == BlockEffect.SOFT_SPICY || effect == BlockEffect.HARD_SPICY ) {
						return true;
					}
				case LAVA :
					if (effect == BlockEffect.BURN) {
						return true;
					}
				case ALL :
					return true;
				default :
					return false;
				}
			}
		}
		return false;
	}


	public ArrayList<EntityEffect> getEffects() {
		return effects;
	}


	public void addEffect(EntityEffect effect) {
		this.effects.add(effect);
	}

	public void removeEffect() {

	}

	public void move() {
		move(getDirection());
	}

	public void move(Direction direction) {
		switch (direction) {
		case RIGHT :
			getPosition().addX(speed);
			break;
		case LEFT : 
			getPosition().addX(-speed);
			break;
		case DOWN :
			getPosition().addY(speedJump);
			break;
		case UP : 
			getPosition().addY(-speedJump);
			break;
		default :
			break;
		}

	}
	
	public void move(Direction direction, int speed) {
		switch (direction) {
		case RIGHT :
			getPosition().addX(speed);
			break;
		case LEFT : 
			getPosition().addX(-speed);
			break;
		case DOWN :
			getPosition().addY(speed);
			break;
		case UP : 
			getPosition().addY(-speed);
			break;
		default :
			break;
		}

	}

	public boolean getGravity() {
		return gravity;
	}


	public void setGravity(boolean gravity) {
		this.gravity = gravity;
	}

	public Direction getImposedDirection() {
		return imposedDirection;
	}


	public void setImposedDirection(Direction imposedDirection) {
		this.imposedDirection = imposedDirection;
	}

	public int getAltitude() {
		return altitude;
	}


	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}


	public int getAltitudeMax() {
		return altitudeMax;
	}


	public void setAltitudeMax(int altitudeMax) {
		this.altitudeMax = altitudeMax;
	}

	public Direction getDirection() {
		return directionX;
	}
	public void setDirection(Direction directionX) {
		this.directionX = directionX;
	}


	public void shoot(Direction fireDirection) {
		System.out.println("shooooot !!!!");

	}

	public void jump() {
		if (altitudeToJump != 0) {
			if (altitude < altitudeToJump) {
				isJumping = true;
				move(Direction.UP);
				altitude += speedJump;
			}
			else {
				isJumping = false;
				altitudeToJump = 0;
				altitude = 0;
			}
		}
		else if (altitude < altitudeMax) {
			isJumping = true;
			move(Direction.UP);
			altitude += speedJump;
		}
		else {
			isJumping = false;
			altitudeToJump = 0;
			altitude = 0;
		}
	}

	public void jump(int altitudeToJump) {
		isJumping = true;
		this.altitudeToJump = altitudeToJump;
	}
	
	public boolean isForceToJump() {
		if (altitudeToJump != 0) {
			return true;
		}
		return false;
	}
	


	public boolean getIsJumping() {
		return isJumping;
	}

	public void setIsJumping(boolean isJumping) {
		this.isJumping = isJumping;
		this.altitudeToJump = 0;
		this.altitude = 0;
	}


	public long getStartTime() {
		return startTime;
	}


	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}


	public State getState() {
		return state;
	}


	public void setState(State state) {
		this.state = state;
	}


	public int getDAMAGE_FIRE() {
		return DAMAGE_FIRE;
	}


	public int getDAMAGE_POISON() {
		return DAMAGE_POISON;
	}


	public int getDAMAGE_SOFT_PIC() {
		return DAMAGE_SOFT_PIC;
	}


	public int getDAMAGE_HARD_PIC() {
		return DAMAGE_HARD_PIC;
	}


	public int getDAMAGE_TOUCH() {
		return DAMAGE_TOUCH;
	}


	public int getDAMAGE_JUMP() {
		return DAMAGE_JUMP;
	}


	public int getDAMAGE_BIG_JUMP() {
		return DAMAGE_BIG_JUMP;
	}


	public int getHEAL_HEART() {
		return HEAL_HEART;
	}


	public int getREGEN_APPLE() {
		return REGEN_APPLE;
	}


	private void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public int getCurrentHeal() {
		return currentHeal;
	}


	public void setCurrentHeal(int currentHeal) {
		this.currentHeal = currentHeal;
	}


	public int getCurrentRegen() {
		return currentRegen;
	}


	public void setCurrentRegen(int currentRegen) {
		this.currentRegen = currentRegen;
	}


	public long getPreviousTimeRegen() {
		return previousTimeRegen;
	}


	public void setPreviousTimeRegen(long previousTimeRegen) {
		this.previousTimeRegen = previousTimeRegen;
	}


	public int getActualDamages() {
		return actualDamages;
	}


	public Solidity getSolidityDestructiblesElements() {
		return solidityDestructiblesElements;
	}


	public void setSolidityDestructiblesElements(Solidity solidityDestructiblesElements) {
		this.solidityDestructiblesElements = solidityDestructiblesElements;
	}


	public boolean isTouchFloor() {
		return touchFloor;
	}


	public void setTouchFloor(boolean touchFloor) {
		this.touchFloor = touchFloor;
	}


	public boolean isClimbingLadder() {
		return climbingLadder;
	}


	public void setClimbingLadder(boolean climbingLadder) {
		this.climbingLadder = climbingLadder;
	}


	public ArrayList<Effect> getEffect() {
		return effect;
	}


	public void setEffect(ArrayList<Effect> effect) {
		this.effect = effect;
	}
	


}

