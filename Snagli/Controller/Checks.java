import java.awt.Color;
import java.util.ArrayList;

import model.entities.TemporalEntity;
import model.enumeration.BlockEffect;
import model.enumeration.Direction;
import model.enumeration.GrabableEffect;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.enumeration.State;
import model.enumeration.StateType;
import model.factory.BlockFactory;
import model.genercis.Effect;
import model.genercis.EffectGet;
import model.genercis.Invulnerability;
import model.genercis.Position;
import model.genercis.SnagliModel;
import model.mobile.MobileEntity;
import model.mobile.Player;
import model.motionless.MotionLess;
import model.other.MovableText;
import model.other.Random;

public class Checks {



	/*------------------------------------------------------------------------------*/
	/*                                 ATTRIBUTES                                   */
	/*------------------------------------------------------------------------------*/
	private static SnagliModel model;
	/*------------------------------------------------------------------------------*/
	/*                            CONSTRUCTORS/DESTRUCTORS                          */
	/*------------------------------------------------------------------------------*/
	public Checks(SnagliModel model) {
		Checks.model = model;
	}
	/*------------------------------------------------------------------------------*/
	/*                                 METHODS                                     */
	/*------------------------------------------------------------------------------*/


	static void checkActivesBoxes(MobileEntity entity) {
		entity.setClimbingLadder(false);
		for (MotionLess block : Movement.getActivesBoxes(Movement.getBoxes(entity.getPosition(), entity.getHitboxe()))) {
			checkActiveBoxe(block, entity);
		}
	}


	static void checkActiveBoxe(MotionLess block, MobileEntity entity) {

		if (block.getPermeability() != Permeability.NONE &&block.getIsAlive()) {
			switch (block.getPermeability()) {
			case DAMAGE :
				if (block.getEffectWhenTutch() != null) {
					if (!checkIsResistantToEffect(entity.getInvulnerability(), block.getResistanceOfTheEffect())) {
						if (entity.isTouchFloor())
							ApplyEffect.applyDamageToEntity(entity, EffectGet.ValueOfEffectWhenTutch(block.getEffectWhenTutch()));
					}
				}
				break;
			case GRABABLE :
				if (block.getGrabableEffect() != null) {
					switch (block.getGrabableEffect()){
					case GIVE_MONEY :
						Animation.createStarParticule(entity.getPosition());	
						if (entity instanceof Player) {
							((Player) entity).addMonnaie(1);
							checkAdjustBars();
						}
						break;
					case GIVE_HEALTH :
						Animation.createHealParticule(entity.getPosition());
						if (entity.getLife() >= entity.getLifeMax()) {
							model.addPowerObject(GrabableEffect.GIVE_HEALTH);
						}
						if (entity.getLife() + entity.getHEAL_HEART() <= entity.getLifeMax() ) {
							entity.alterLife(entity.getHEAL_HEART());
							model.addtext(new MovableText(entity.getHEAL_HEART(), new Position(entity.getPosition()), new Color(75, 119, 69)));	
							checkAdjustBars();
						}
						else {
							model.addtext(new MovableText(entity.getLifeMax() - entity.getLife(), new Position(entity.getPosition()), new Color(75, 119, 69)));
							entity.alterLife(entity.getLifeMax() - entity.getLife());
							checkAdjustBars();
						}
		
						break;
					case GIVE_REGENERATION :
						Animation.createHealParticule(entity.getPosition());
						if (!checkHasEffect(entity.getEffect(), BlockEffect.REGENERATION))
							entity.getEffect().add(new Effect(3000, 50, 5, BlockEffect.REGENERATION));
						else {
							ApplyEffect.applyGetPowerObject(GrabableEffect.GIVE_REGENERATION);
						}
						break;
					case GIVE_FIRE :
						ApplyEffect.applyGetPowerObject(GrabableEffect.GIVE_FIRE);
						Animation.createOrangeParticule(new Position((int) (block.getPosition().getX()*50- 75), (int) (block.getPosition().getY()*50 - 75)));
						break;
					case GIVE_ICE :
						ApplyEffect.applyGetPowerObject(GrabableEffect.GIVE_ICE);
						Animation.createBlueParticule(new Position((int) (block.getPosition().getX()*50- 75), (int) (block.getPosition().getY()*50 - 75)));

						break;
					case GIVE_POISON :
						ApplyEffect.applyGetPowerObject(GrabableEffect.GIVE_POISON);
						Animation.createPurpleParticule(new Position((int) (block.getPosition().getX()*50- 75), (int) (block.getPosition().getY()*50 - 75)));

						break;
					case GIVE_MISSILE :
						ApplyEffect.applyGetPowerObject(GrabableEffect.GIVE_MISSILE);
						Animation.createBlackParticule(new Position((int) (block.getPosition().getX()*50- 75), (int) (block.getPosition().getY()*50- 75)));
						break;
						
					default : 
						Animation.createMultiColorParticule(new Position((int) (block.getPosition().getX()*50- 75), (int) (block.getPosition().getY()*50- 75)));

					}
					block.setAlive(false);
					break;
				}
				break;
			case ELEVATOR :
				if (entity.isTouchFloor())
					entity.jump(300);
				break;
			case SCALABLE :
				entity.setClimbingLadder(true);
				break;
			case SPAWNER:
				model.getMap().setOnTheMap(BlockFactory.getFromGrabableEffect(Random.getGrabableObject(), block.getPosition()),block.getPosition());
				break;
			default :
			}
		}

	}
	
	
	static public boolean checkHasEffect(ArrayList<Effect> effects, BlockEffect searchEffect) {
		for (Effect effect : effects) {
			if (effect.getBlockEffect() == searchEffect && effect.isAlive(model.getTimer().getTimeMilliSeconds())) {
				return true;
			}
		}
		return false;
	}

	static public boolean checkIsResistantToEffect2(ArrayList<Invulnerability> invulnerabilities, BlockEffect effect) {
		if (invulnerabilities != null) {
			for (Invulnerability invulnerability : invulnerabilities) {
				if (invulnerability.isAlive(model.getTimer().getTimeMilliSeconds())) {
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
		}
		return false;
	}

	static public boolean checkIsResistantToEffect(ArrayList<Invulnerability> invulnerabilities, Resistance resistanceOfEffect) {
		if (invulnerabilities != null) {
			for (Invulnerability invulnerability : invulnerabilities) {
				if (invulnerability.isAlive(model.getTimer().getTimeMilliSeconds())) {
					if (invulnerability.getResistance() == Resistance.ALL) {
						return true;
					}
					if (invulnerability.getResistance() == resistanceOfEffect) {
						return true;
					}
				}
			}
		}
		return false;
	}


	static boolean checkActiveFloor(MobileEntity entity, Direction direction) {

		boolean touchFloor = false;

		for (MotionLess boxe : Movement.getNextBoxes(Direction.DOWN, entity.getPosition(), entity.getHitboxe(), entity.getSpeed())) {
			if ((boxe.getOpacity() == Opacity.BLOCKING) && boxe.getIsAlive()) {
				touchFloor = true;
			}
		}
		if (!entity.isTouchFloor() && touchFloor && direction == Direction.DOWN) {
			Animation.createDownSmoke(entity.getPosition());
		}
		else if (!entity.isTouchFloor() &&  touchFloor) {
			Animation.createLittleSmoke(entity.getPosition());
		}

		return touchFloor;
	}

	private static void checkGravity(MobileEntity entity, boolean allowMoveX) {
		if (entity.getGravity()) {
			if (entity.getDirection() == Direction.RIGHT || entity.getDirection() == Direction.LEFT) {
				if (allowMoveX) {
					if (Movement.canMove(entity.getPosition(), entity.getDirection(), entity.getHitboxe(), entity.getSpeed(), entity.getSpeed())) {
						entity.move(entity.getDirection());
					}
				}
			}
			if (entity.getAltitude() < entity.getAltitudeMax()) {
				entity.setAltitude(entity.getAltitude() + entity.getSpeed());
				if (Movement.canMove(entity.getPosition(), entity.getDirection(), entity.getHitboxe(), entity.getSpeed(), entity.getSpeed())){
					entity.move(entity.getImposedDirection());
				}
				else {
					entity.setGravity(false);
				}
			}
			entity.setGravity(false);
		}
	}

	private static void checkActivesBalls(MobileEntity entity) {
		System.out.println("Movement - CheckActivesBalls - méthode non implémentée.");
	}

	//return a boolean if there is an entity on the next movement 
	private boolean checkNextEntity(MobileEntity entity) {
		Position position = entity.getPosition();
		entity.setPosition(Movement.getTheoricalPosition(entity.getDirection(), entity.getPosition(), entity.getSpeed()));
		boolean isEntityOnNextCase = false;
		if (Movement.getEntitiesInContact(entity).size() >= 1) {
			isEntityOnNextCase = true;
		}
		entity.setPosition(position);
		return isEntityOnNextCase;
	}

	public static void checksinvulnerabilityAndEffects(long time, MobileEntity entity) {
		checkEffects(time, entity);
		checkInvulnerability(time, entity);
	}


	public static void checkEffects(long time, MobileEntity entity) {
		for (Effect effect : entity.getEffect()) {
			switch (effect.getBlockEffect()) {
			case REGENERATION :
				if (effect.isAlive(model.getTimer().getTimeMilliSeconds())){
					if (effect.getActualAlteration() < effect.getLifeAlteration()) {
						if (entity.getLife() + 1 <= entity.getLifeMax()) {
							entity.alterLife(1);
							effect.setActualAlteration(effect.getActualAlteration() + 1);
						}

						if (effect.getActualAlteration() == effect.getLifeAlteration()) {
							effect.setTotalLifeAlteration(effect.getTotalLifeAlteration() + effect.getLifeAlteration());
							model.addtext(new MovableText(effect.getLifeAlteration(), new Position(entity.getPosition()), new Color(75, 119, 69)));
						}

						//						if (effect.getActualAlteration() == effect.getLifeAlteration() || entity.getLife() == entity.getLifeMax()) {
						//							effect.setTotalLifeAlteration(effect.getTotalLifeAlteration() + effect.getLifeAlteration());
						//							model.addtext(new MovableText((entity.getLife() + effect.getLifeAlteration() <= entity.getLifeMax())? effect.getLifeAlteration() : effect.getActualAlteration() , new Position(entity.getPosition()), new Color(75, 119, 69)));
						//						}

						checkAdjustBars();
						return;
					}
					else {
						if (effect.getStartTime() == 0) {
							effect.setStartTime(model.getTimer().getTimeMilliSeconds());
						}
						if (model.getTimer().getTimeMilliSeconds() - effect.getStartTime() >= effect.getTimeGap()) {
							effect.setActualAlteration(0);
							effect.setStartTime(0);
						}
					}
				}
				break;
			default :

			}
		}
	}

	public static void checkInvulnerability(long time, MobileEntity entity) {
		int i = 0;
		ArrayList<Integer> tab = new ArrayList<>();
		for (Invulnerability invulnerability : entity.getInvulnerability()) {
			if (!invulnerability.isAlive(time)) {
				tab.add(i);
			}
			i++;
		}
		ArrayList<Integer> reverseTab = new ArrayList<>();
		i = tab.size() - 1;
		for (i = tab.size() - 1;i>=0;i--) {
			reverseTab.add(tab.get(i));
		}
		for (Integer integer : reverseTab) {
			entity.removeInvulnerability(entity.getInvulnerability().get(integer.intValue()).getResistance());
		}
	}


	public static void checkEffectsPlayer(long time, Player entity) {
		checkDurationEffect(time, entity);
		checkRegen(time, entity);
	}

	private static void checkDurationEffect(long time, MobileEntity entity) {
		if ((entity.getState() == State.NONE || entity.getState() == null) || entity.getInvulnerability().size() <= 0) {
			return;
		}
		if (entity.getStartTime() == 0) {
			entity.setStartTime(time);
			return;
		}
		if ((float)(time - entity.getStartTime() ) >= (float) (entity.getDuration())) {
			entity.removeInvulnerability(entity.getResistanceByState(entity.getState()));
			entity.setState(State.NONE);
			entity.setStartTime(0);
			entity.setDuration(0);
		}
	}

	private static void checkRegen(long time, Player entity) {

		if (entity.isRegenApple()) {
			if (entity.getCurrentRegen() < entity.getREGEN_APPLE()) {
				if (time - entity.getPreviousTimeRegen() <= 300) {
					entity.alterLife(1);
					entity.setCurrentRegen(entity.getCurrentRegen() +1);
					checkAdjustBars();
				}
				else if (time - entity.getPreviousTimeRegen() >=  3000) {
					entity.setPreviousTimeRegen(time);
					model.addtext(new MovableText(entity.getCurrentRegen(), new Position(entity.getPosition()), new Color(75, 119, 69)));
				}
			}
			else {
				entity.setRegenApple(false);
				entity.setCurrentRegen(0);
			}

		}
		if (entity.isRegenHeart()) {
			if (entity.getCurrentHeal() < entity.getHEAL_HEART()) {

				if (time - entity.getPreviousTimeRegen() <= 1000) {
					entity.alterLife(+1);
					entity.setCurrentHeal(entity.getCurrentHeal() +1);
					checkAdjustBars();
				}
				else {
					model.addtext(new MovableText(entity.getCurrentHeal(), new Position(entity.getPosition()), new Color(75, 119, 69)));
					entity.setPreviousTimeRegen(time);
				}
			}
			else {
				entity.setRegenHeart(false);
				entity.setCurrentHeal(0);
			}
		}
	}




	public static void checkAdjustBars() {
		adjustGauges();
		ajustStateBar();
	}

	private static void adjustGauges() {
		model.getGaugeBars().get(0).adaptBar(model.getPlayer().getLife(), model.getPlayer().getLifeMax());
		model.getGaugeBars().get(1).adaptBar(model.getPlayer().getMonnaie(), 50);
	}

	private static void ajustStateBar() {
		if ((((double) model.getPlayer().getLife())/model.getPlayer().getLifeMax()) == 0) {
			model.getStateBar().selectState(StateType.DEAD);
			return;
		}
		else if ((((double) model.getPlayer().getLife())/model.getPlayer().getLifeMax()) < 0.33) {
			model.getStateBar().selectState(StateType.NO_LIFE);
			model.getStateBar().selectRedBackGround();
			return;
		}
		else if ((((double) model.getPlayer().getLife())/model.getPlayer().getLifeMax()) <= 0.66) {
			model.getStateBar().selectState(StateType.MID_LIFE);
			model.getStateBar().selectOrangeBackGround();
			return;
		}
		else if ((((double) model.getPlayer().getLife())/model.getPlayer().getLifeMax()) <= 1) {
			model.getStateBar().selectState(StateType.FULL_LIFE);
			model.getStateBar().selectGreenBackGround();
			return;
		}

	}

	public static void checkDamagesEntity(MobileEntity entity) {
		for (TemporalEntity tempEntity : Movement.getTemporalEntitiesInContact(entity)) {
			if (tempEntity.getEffectWhenTutch() != null) {
				if (!checkIsResistantToEffect(entity.getInvulnerability(), Resistance.NONE)) {
					ApplyEffect.applyDamageToEntity(entity, EffectGet.ValueOfEffectWhenTutch(tempEntity.getEffectWhenTutch()),800);
//					if (entity.getLife() > 0) {
//						    entity.alterLife(-EffectGet.ValueOfEffectWhenTutch(tempEntity.getEffectWhenTutch())) ;//|| (entity.getLifeMax() == entity.getLife())) {
//							checkAdjustBars();
//							model.addtext(new MovableText(-EffectGet.ValueOfEffectWhenTutch(tempEntity.getEffectWhenTutch()), new Position(entity.getPosition()), Color.RED));
//							entity.addinvulnerability(Resistance.ALL, 1000, model.getTimer().getTimeMilliSeconds());
//						//}
//					}
				}
			}

			//entity.alterLife(-EffectGet.ValueOfEffectWhenTutch(tempEntity.getEffectWhenTutch()));
		}
	}
	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/
	public static void setModel(SnagliModel model1) {
		model = model1;
	}
}
