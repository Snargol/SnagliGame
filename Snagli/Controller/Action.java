import java.util.ArrayList;

import model.enumeration.Direction;
import model.enumeration.Opacity;
import model.enumeration.Power;
import model.enumeration.Projectiles;
import model.enumeration.Solidity;
import model.factory.ProjectilesFactory;
import model.genercis.HitBoxe;
import model.genercis.Position;
import model.genercis.SnagliModel;
import model.mobile.MobileEntity;
import model.motionless.MotionLess;
import model.other.Random;
import model.particule.ParticuleDebris;
import model.projectile.Projectile;

public class Action {
	/*------------------------------------------------------------------------------*/
	/*                                 ATTRIBUTES                                   */
	/*------------------------------------------------------------------------------*/

	private static SnagliModel model;
	/*------------------------------------------------------------------------------*/
	/*                            CONSTRUCTORS/DESTRUCTORS                          */
	/*------------------------------------------------------------------------------*/

	/*------------------------------------------------------------------------------*/
	/*                                 METHODS                                     */
	/*------------------------------------------------------------------------------*/
	public static void fire(MobileEntity entity, Direction orderDirection,boolean fireOrder) {
		//rajouter if playercanshoot (compteur de balls)
		if (fireOrder && model.getInventoryBar().getActualPowerObject() != null && !entity.getGravity() && orderDirection != null )
			if (Movement.canMove(entity.getPosition(), orderDirection, entity.getHitboxe(), entity.getSpeed(), entity.getSpeed())){
				switch (model.getInventoryBar().getActualPowerObject().getGrabableEffect()) {
				case GIVE_MISSILE : 
					if (orderDirection != Direction.DOWN) {

						if (model.getProjectiles().isEmpty()) {
							model.addProjectile(launchMissile(entity.getPosition(), orderDirection));
						}
					}

					break;
				default:
					break;
					//			if (fireOrder) {
					//				if (model.getProjectiles().isEmpty()) {
					//					model.addProjectile(launchMissile(entity.getPosition(), entity.getDirection()));
					//				}
					//			}
				}
			}


	}

	public static Projectile launchMissile(Position position, Direction direction) {
		Projectile projectile = ProjectilesFactory.launchProjectile(Projectiles.MISSILE, new Position(position), direction);
		return projectile;
	}

	public static void explode(Position position, int rayon) {
		for (MotionLess boxe : model.getMap().getOnTheMap(position, 1)) {
			DestroyIfCan(Solidity.SOLID, boxe);
		}
		Animation.createExplosion(new Position(position.getX()*50, position.getY()*50));
	}

	private static boolean isDestructible(Solidity solidityEntity, Solidity solidityBlock) {
		switch (solidityBlock) {
		case WEAK :
			return true;
		case NORMAL :
			if (solidityEntity == Solidity.NORMAL || solidityEntity == Solidity.SOLID)
				return true;
			return false;
		case SOLID :
			if (solidityEntity == Solidity.SOLID) 
				return true;
			return false;
		case UNBREKABLE : 
			return false;
		default : 
			return false;
		}
	}

	public static void destroyIfNeedFloor(MotionLess boxe) {
		if (boxe.isNeedFloor()) {
			boxe.setAlive(false);
		}
	}



	public static boolean destroyIfCan(Direction direction, MobileEntity entity) {
		boolean destroyed = false;

		ArrayList<MotionLess> nextActivesBoxes = new ArrayList<>();
		//		nextActivesBoxes = Movement.getNextBoxes(direction, Movement.getBoxes(entity.getPosition(), entity.getHitboxe()));
		nextActivesBoxes = model.getMap().getUpBoxes(entity.getPosition(), entity.getHitboxe());

		for (MotionLess motionLess : nextActivesBoxes) {
			System.out.println(motionLess.getName());
		}

		for (MotionLess boxe : nextActivesBoxes) {
			if (boxe.getOpacity() == Opacity.BLOCKING && boxe.getIsAlive()) {
				if (isDestructible(entity.getSolidityDestructiblesElements(), boxe.getSolidity())) {
					boxe.setAlive(false);
					Animation.createDebrisAnimation(boxe);
					destroyIfNeedFloor(model.getMap().getTopBoxe(boxe.getPosition()));
					destroyed = true;
				}
				Checks.checkActiveBoxe(boxe, entity);
				//entity.applyBlockEffect(boxe.getEffect());
				Checks.checkAdjustBars();
			}
		}
		return destroyed;
	}

	public static boolean destroyIfCan(ArrayList<MotionLess> boxes, MobileEntity entity) {
		boolean destroyed = false;

		for (MotionLess boxe : boxes) {
			if (boxe.getOpacity() == Opacity.BLOCKING && boxe.getIsAlive()) {
				if (isDestructible(entity.getSolidityDestructiblesElements(), boxe.getSolidity())) {
					boxe.setAlive(false);
					Animation.createDebrisAnimation(boxe);
					destroyIfNeedFloor(model.getMap().getTopBoxe(boxe.getPosition()));
					destroyed = true;
				}
				Checks.checkActiveBoxe(boxe, entity);
				//entity.applyBlockEffect(boxe.getEffect());
				Checks.checkAdjustBars();
			}
		}

		return destroyed;

	}

	public static void DestroyIfCan(Solidity solidity, MotionLess boxe) {
		if (boxe.getIsAlive()) {
			if (isDestructible(solidity, boxe.getSolidity())){
				boxe.setAlive(false);
				Animation.createDebrisAnimation(boxe);
				destroyIfNeedFloor(model.getMap().getTopBoxe(boxe.getPosition()));
			}
		}
	}


	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/

	public static void setModel(SnagliModel model1) {
		model = model1;
	}
}
