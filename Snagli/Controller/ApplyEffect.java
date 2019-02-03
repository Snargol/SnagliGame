import java.awt.Color;

import model.enumeration.GrabableEffect;
import model.enumeration.Resistance;
import model.genercis.EffectGet;
import model.genercis.Position;
import model.genercis.SnagliModel;
import model.mobile.MobileEntity;
import model.other.MovableText;

public class ApplyEffect {

	/*------------------------------------------------------------------------------*/
	/*                                 ATTRIBUTES                                   */
	/*------------------------------------------------------------------------------*/
	private static SnagliModel model;
	private static int lastPowerUse = 0;
	/*------------------------------------------------------------------------------*/
	/*                            CONSTRUCTORS/DESTRUCTORS                          */
	/*------------------------------------------------------------------------------*/
	public ApplyEffect(SnagliModel model) {
		ApplyEffect.model = model;
	}
	/*------------------------------------------------------------------------------*/
	/*                                 METHODS                                     */
	/*------------------------------------------------------------------------------*/

	public static void activeSelectedPower(String power) {

		if (power != null && Integer.parseInt(power) != getLastPowerUse()) {
			setLastPowerUse(Integer.parseInt(power));
			if (Integer.parseInt(power) != 0) {
				if (getModel().getPowerObject().size() >= Integer.parseInt(power)) {
					getModel().getInventoryBar().setActualPowerObject(getModel().getPowerObject().get(Integer.parseInt(power)-1).getGrabableEffect());
					selectAnimation(getModel().getPowerObject().get(Integer.parseInt(power)-1).getGrabableEffect());
					getModel().removePowerObject(getModel().getPowerObject().get(Integer.parseInt(power)-1).getGrabableEffect());

				}
			}
		}
	}
	
	public static boolean applyDamageToEntity(MobileEntity entity, int damage) {
		if (entity.getLife() > 0) {
			if (entity.alterLife(-damage) || (entity.getLifeMax() == entity.getLife())) {
				Checks.checkAdjustBars();
				if (entity.isTouchFloor())
					entity.jump(damage);
				model.addtext(new MovableText(-damage, new Position(entity.getPosition()), Color.RED));
				entity.addinvulnerability(Resistance.ALL, 1500, model.getTimer().getTimeMilliSeconds());
			}
			return true;
		}
		else
			return false;
	}
	
	public static boolean applyDamageToEntity(MobileEntity entity, int damage, int resistanceTime) {
		if (entity.getLife() > 0) {
			if (entity.alterLife(-damage) || (entity.getLifeMax() == entity.getLife())) {
				Checks.checkAdjustBars();
				if (entity.isTouchFloor())
					entity.jump(damage);
				model.addtext(new MovableText(-damage, new Position(entity.getPosition()), Color.RED));
				entity.addinvulnerability(Resistance.ALL, resistanceTime, model.getTimer().getTimeMilliSeconds());
			}
			return true;
		}
		else
			return false;
	}
	
	public static void applyGetPowerObject(GrabableEffect effect) {
		if (getModel().getInventoryBar().getActualPowerObject() == null) {
			getModel().getInventoryBar().setActualPowerObject(effect);
		}
		else {
			model.addPowerObject(effect);
		}
	}

	private static void selectAnimation(GrabableEffect effect) {
		switch (effect) {
		case GIVE_FIRE :
			Animation.createOrangeParticule(new Position((int) (getModel().getPlayer().getPosition().getX()- getModel().getPlayer().getHitboxe().getWidth()*1.5), getModel().getPlayer().getPosition().getY() - getModel().getPlayer().getHitboxe().getHeight()*0 - 75));
			break;
		case GIVE_ICE :
			Animation.createBlueParticule(new Position((int) (getModel().getPlayer().getPosition().getX()- getModel().getPlayer().getHitboxe().getWidth()*1.5), getModel().getPlayer().getPosition().getY() - getModel().getPlayer().getHitboxe().getHeight()*0 - 75));
			break;
		case GIVE_MISSILE :
			Animation.createBlackParticule(new Position((int) (getModel().getPlayer().getPosition().getX()- getModel().getPlayer().getHitboxe().getWidth()*1.5), getModel().getPlayer().getPosition().getY() - getModel().getPlayer().getHitboxe().getHeight()*0 - 75));
			break;
		case GIVE_POISON :
			Animation.createPurpleParticule(new Position((int) (getModel().getPlayer().getPosition().getX()- getModel().getPlayer().getHitboxe().getWidth()*1.5), getModel().getPlayer().getPosition().getY() - getModel().getPlayer().getHitboxe().getHeight()*0 - 75));
			break;
		default :
			Animation.createMultiColorParticule(new Position((int) (getModel().getPlayer().getPosition().getX()- getModel().getPlayer().getHitboxe().getWidth()*1.5), getModel().getPlayer().getPosition().getY() - getModel().getPlayer().getHitboxe().getHeight()*0 - 75));


		}

	}

	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/
	public static void setModel(SnagliModel model1) {
		model = model1;
	}

	public static SnagliModel getModel() {
		return model;
	}

	public static int getLastPowerUse() {
		return lastPowerUse;
	}

	public static void setLastPowerUse(int lastPowerUse) {
		ApplyEffect.lastPowerUse = lastPowerUse;
	}


}
