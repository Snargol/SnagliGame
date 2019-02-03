package model.mobile;

import Interfaces.IApplyBlockEffects;
import Interfaces.IApplyEntityEffects;
import model.enumeration.ActionSprite;
import model.enumeration.BlockEffect;
import model.enumeration.EffectWhenTutch;
import model.enumeration.EntityEffect;
import model.enumeration.Opacity;
import model.enumeration.Resistance;
import model.enumeration.State;
import model.genercis.HitBoxe;
import model.genercis.Invulnerability;
import model.genercis.Position;

public class Player extends MobileEntity{
	private boolean 				isRegenHeart 		= false;
	private boolean 				isRegenApple 		= false;
	private boolean 				sneak				= false;
	private int 					monnaie     		= 0;
	private int COO_PLAYER_DISPLAY_X = 750;

	public Player(Position position) {
		super(0, 0, 1, "waiting", Opacity.BLOCKING, new HitBoxe(50,100), new Position(position.getX()*50,position.getY()*50-50), 50, 100, 50, 100, EffectWhenTutch.NONE);
		setLife(100);
		setDamage(50);
		setLifeMax(100);
		setSpeed(5);
		// TODO Auto-generated constructor stub


	}


	public int getDamagesEntity(EntityEffect effect) {
		switch (effect) {

		case FIRE :
			return DAMAGE_FIRE;
		case POISON : 
			return DAMAGE_POISON;
		case JUMP : 
			return DAMAGE_JUMP;
		case BIG_JUMP : 
			return DAMAGE_BIG_JUMP;
		case TOUCH :
			return DAMAGE_TOUCH;
		default :
			return 0;
		}
	}
	
//	public boolean applyBlockEffect(BlockEffect effect) {
//		int lifeBefore = getLife();
//
//		if (isResistantBlockEffect(effect)) {
//			return false;
//		}
//
//		switch (effect) {
//		case BURN :
//			alterLife(-DAMAGE_FIRE);
//			actualDamages = DAMAGE_FIRE;
//			break;
//		case SOFT_SPICY:
//			alterLife(-DAMAGE_SOFT_PIC);
//			actualDamages = DAMAGE_SOFT_PIC;
//			break;
//		case HARD_SPICY:
//			alterLife(-DAMAGE_HARD_PIC);
//			actualDamages = DAMAGE_HARD_PIC;
//			break;
//		case HEAL :
//			isRegenHeart = true;
//			break;
//		case REGENERATION :
//			isRegenApple = true;
//			break;     
//		case NONE :
//			break;
//		case GIVE_MONEY :
//			addMonnaie(1);
//			break;
//		default :
//			break;
//		}
//		if (getLife() < lifeBefore) {
//			setState(State.INVILNERABILITY, 1500);
//			return true;
//		}
//		return false;
//
//	}
	
	public int getMonnaie() {
		return monnaie;
	}


	public void setMonnaie(int monnaie) {
		this.monnaie = monnaie;
		if (this.monnaie > 50 ) {
			this.monnaie = 0;
		}
		if (this.monnaie < 0) {
			this.monnaie = 0;
		}
	}

	public void addMonnaie(int monnaie) {
		this.monnaie += monnaie;
		if (this.monnaie > 50 ) {
			this.monnaie = 0;
		}
		if (this.monnaie < 0) {
			this.monnaie = 0;
		}
	}


	public boolean isRegenHeart() {
		return isRegenHeart;
	}


	public void setRegenHeart(boolean isRegenHeart) {
		this.isRegenHeart = isRegenHeart;
	}


	public boolean isRegenApple() {
		return isRegenApple;
	}


	public void setRegenApple(boolean isRegenApple) {
		this.isRegenApple = isRegenApple;
	}

	public boolean isResistantEntityEffect(EntityEffect effect) {
		for (Invulnerability resistance : getInvulnerability()) {
			if (""+effect == ""+resistance) {
				return true;
			}
			else if (resistance.getResistance() == Resistance.ALL) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}


	public ActionSprite getActionSpriteByEntityEffect(EntityEffect effect) {
		switch (effect) {
		case FIRE : 
			return ActionSprite.BURN;
		case BIG_JUMP :
			return ActionSprite.SPLASH;
		case JUMP : 
			return ActionSprite.SPLASH;
		default : 
			return ActionSprite.DEAD;
		}
	}
	
	public Position getDisplayPosition() {
		return new Position(COO_PLAYER_DISPLAY_X - getPosition().getX(), getPosition().getY());
	}


}
