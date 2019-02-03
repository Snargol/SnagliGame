import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.Spring;

import Interfaces.IApplyBlockEffects;
import Interfaces.IApplyEntityEffects;
import model.entities.TemporalEntity;
import model.enumeration.BlockEffect;
import model.enumeration.Direction;
import model.enumeration.Opacity;
import model.enumeration.OrderEnum;
import model.enumeration.Permeability;
import model.genercis.HitBoxe;
import model.genercis.Order;
import model.genercis.Position;
import model.genercis.SnagliModel;
import model.mobile.Mob;
import model.mobile.MobileEntity;
import model.mobile.Player;
import model.motionless.MotionLess;
import model.projectile.Projectile;

public class Movement {
	/*------------------------------------------------------------------------------*/
	/* ATTRIBUTES */
	/*------------------------------------------------------------------------------*/
	private static int TAILLE_CASE = 50;
	private static SnagliModel model;

	/*------------------------------------------------------------------------------*/
	/* CONSTRUCTORS/DESTRUCTORS */
	/*------------------------------------------------------------------------------*/
	public Movement(SnagliModel model, int TAILLE_CASE) {
		this.model = model;
		this.TAILLE_CASE = TAILLE_CASE;
	}
	/*------------------------------------------------------------------------------*/
	/* METHODS */
	/*------------------------------------------------------------------------------*/

	// catch all actives boxes among the actual boxe
	public static ArrayList<MotionLess> getActivesBoxes(ArrayList<MotionLess> boxes) {
		ArrayList<MotionLess> list = new ArrayList<>();

		for (MotionLess block : boxes) {
			if (block.getEffect() != BlockEffect.NONE || block.getPermeability() != Permeability.NONE) {
				list.add(block);
			}
		}

		return list;
	}

	public static Position getTheoricalPosition(Direction direction, Position position, int speed) {
		switch (direction) {
		case RIGHT:
			return new Position(position.getX() + speed, position.getY());
		case LEFT:
			return new Position(position.getX() - speed, position.getY() - speed);
		case UP:
			return new Position(position.getX(), position.getY() - speed);
		case DOWN:
			return new Position(position.getX(), position.getY() + speed);
		default:
			return new Position(position.getX(), position.getY());
		}
	}

	// catch all boxes
	public static ArrayList<MotionLess> getBoxes(Position position, HitBoxe hitboxe) {

		return model.getMap().getOnTheMap(position, hitboxe);

	}


	public static ArrayList<MotionLess> getNextBoxes(Direction direction, Position position, HitBoxe hitboxe, int speed) {

		return getBoxes(getTheoricalPosition(direction, position, speed), hitboxe);
	}


	// by taking the central coordinates of each entity, we see if they are present
	// in the circle of specified radius and center corresponding to the given
	// position
	public static ArrayList<MobileEntity> getCloseEntities(MobileEntity entity1, int rayon) {
		ArrayList<MobileEntity> closeEntities = new ArrayList<>();

		for (MobileEntity entity : model.getMonsters()) {

			if (entity.getPosition().getX()
					+ (entity.getWidthDisplay() / 2) <= (entity1.getPosition().getX() + (entity1.getWidthDisplay() / 2)
							+ rayon * TAILLE_CASE)
					&& entity.getPosition().getX() + (entity.getWidthDisplay() / 2) >= (entity1.getPosition().getX()
							+ (entity1.getWidthDisplay() / 2) - rayon * TAILLE_CASE)
					|| entity.getPosition().getY()
					+ (entity.getHeightDisplay() / 2) <= (entity1.getPosition().getY()
							+ (entity1.getHeightDisplay() / 2) + rayon * TAILLE_CASE)
					&& entity.getPosition().getY()
					+ (entity.getHeightDisplay() / 2) >= (entity1.getPosition().getY()
							+ (entity1.getHeightDisplay() / 2) - rayon * TAILLE_CASE)) {
				closeEntities.add(entity);
			}
		}

		for (MobileEntity entity : model.getBalls()) {

			if (entity.getPosition().getX()
					+ (entity.getWidthDisplay() / 2) <= (entity1.getPosition().getX() + (entity1.getWidthDisplay() / 2)
							+ rayon * TAILLE_CASE)
					&& entity.getPosition().getX() + (entity.getWidthDisplay() / 2) >= (entity1.getPosition().getX()
							+ (entity1.getWidthDisplay() / 2) - rayon * TAILLE_CASE)
					|| entity.getPosition().getY()
					+ (entity.getHeightDisplay() / 2) <= (entity1.getPosition().getY()
							+ (entity1.getHeightDisplay() / 2) + rayon * TAILLE_CASE)
					&& entity.getPosition().getY()
					+ (entity.getHeightDisplay() / 2) >= (entity1.getPosition().getY()
							+ (entity1.getHeightDisplay() / 2) - rayon * TAILLE_CASE)) {
				closeEntities.add(entity);
			}
		}

		return closeEntities;
	}

	public static ArrayList<TemporalEntity> getCloseTemporalEntities(MobileEntity entity1, int rayon) {
		ArrayList<TemporalEntity> closeEntities = new ArrayList<>();
		Position e1Pos = new Position((entity1.getPosition().getX() + entity1.getHitboxe().getWidth() / 2), (entity1.getPosition().getY() + entity1.getHitboxe().getHeight() / 2));
		Position e2Pos;

		for (TemporalEntity entity2 : model.getTemporalEntities()) {
			e2Pos = new Position((entity2.getPosition().getX() + entity2.getHitboxe().getWidth() / 2), (entity2.getPosition().getY() + entity2.getHitboxe().getHeight() / 2));

			//System.out.println((e2Pos.getX()  + "<=" +  e1Pos.getX() +"+"+ rayon*50 +"&&"+ e2Pos.getX() +">="+ e1Pos.getX() +"-"+ rayon*50));
			if ((e2Pos.getX() <= e1Pos.getX() + rayon*50 && e2Pos.getX() >= e1Pos.getX() - rayon*50)
					&& (e2Pos.getY() <= e1Pos.getY() + rayon*50 && e2Pos.getY() >= e1Pos.getY() - rayon*50)) {
				closeEntities.add(entity2);
			}
		}


		return closeEntities;
	}

	public static ArrayList<MobileEntity> getEntitiesInContact(MobileEntity entity) {
		ArrayList<MobileEntity> activeEntities = new ArrayList<>();
		boolean xLeft = false;
		boolean xRight = false;
		boolean yDown = false;
		boolean yUp = false;

		for (MobileEntity closeEntity : getCloseEntities(entity, 4)) {
			if ((entity.getPosition().getX() <= closeEntity.getPosition().getX())
					&& (entity.getPosition().getX() + entity.getWidthDisplay() >= closeEntity.getPosition().getX())) {
				xLeft = true;
			}
			if ((entity.getPosition().getX() >= closeEntity.getPosition().getX()) && (entity.getPosition()
					.getX() <= closeEntity.getPosition().getX() + closeEntity.getWidthDisplay())) {
				xRight = true;
			}
			if ((entity.getPosition().getY() <= closeEntity.getPosition().getY()) && (entity.getPosition().getY()
					+ entity.getHeightDisplay() <= closeEntity.getPosition().getY() + closeEntity.getHeightDisplay())) {
				yDown = true;
			}
			if ((entity.getPosition().getY() >= closeEntity.getPosition().getY()) && (entity.getPosition()
					.getY() <= closeEntity.getPosition().getY() + closeEntity.getHeightDisplay())) {
				yUp = true;
			}

			if ((xLeft && yDown) || (xLeft && yUp) || (xRight && yDown) || (xRight && yUp)) {
				activeEntities.add(closeEntity);
			}
		}

		return activeEntities;
	}

	public static ArrayList<TemporalEntity> getTemporalEntitiesInContact(MobileEntity entity) {
		ArrayList<TemporalEntity> activeEntities = new ArrayList<>();
		boolean xLeft = false;
		boolean xRight = false;
		boolean yDown = false;
		boolean yUp = false;

		for (TemporalEntity closeEntity : getCloseTemporalEntities(entity, 2)) {
			if (closeEntity.getIsAlive()) {
				if ((entity.getPosition().getX() < closeEntity.getPosition().getX())
						&& (entity.getPosition().getX() + entity.getWidthDisplay() > closeEntity.getPosition().getX())) {
					xLeft = true;
				}
				if ((entity.getPosition().getX() > closeEntity.getPosition().getX()) && (entity.getPosition()
						.getX() < closeEntity.getPosition().getX() + closeEntity.getWidthDisplay())) {
					xRight = true;
				}
				if ((entity.getPosition().getY() < closeEntity.getPosition().getY()) && (entity.getPosition().getY()
						+ entity.getHeightDisplay() < closeEntity.getPosition().getY() + closeEntity.getHeightDisplay())) {
					yDown = true;
				}
				if ((entity.getPosition().getY() > closeEntity.getPosition().getY()) && (entity.getPosition()
						.getY() < closeEntity.getPosition().getY() + closeEntity.getHeightDisplay())) {
					yUp = true;
				}

				if ((xLeft && yDown) || (xLeft && yUp) || (xRight && yDown) || (xRight && yUp)) {
					activeEntities.add(closeEntity);
				}
			}
		}

		return activeEntities;
	}

	public static boolean canMove(Position position, Direction direction, HitBoxe hitBoxe, int speed, int speedJump) {

		switch (direction) {
		case UP :
			for (MotionLess boxe : model.getMap().getUpBoxes(getTheoricalPosition(Direction.UP ,new Position(position), speed), hitBoxe)) {
				if (boxe.getOpacity() == Opacity.BLOCKING  && boxe.getIsAlive()) {
					return false;
				}
			}
			break;
		case DOWN : 
			for (MotionLess boxe : model.getMap().getDownBoxes(calculePositionByDirection(new Position(position), Direction.DOWN, speed, speedJump), hitBoxe)) {
				if (boxe.getOpacity() == Opacity.BLOCKING  && boxe.getIsAlive()) {
					return false;
				}
			}
			break;
		default : 
			for (MotionLess boxe : model.getMap().getOnTheMap(calculePositionByDirection(new Position(position), direction, speed, speedJump), hitBoxe)) {
				if (boxe.getOpacity() == Opacity.BLOCKING  && boxe.getIsAlive()) {
					return false;
				}
			}
			break;
		}
		return true;
	}

	static boolean hasChangedBoxe(MotionLess boxe1, MotionLess boxe2) {
		if (Math.floor(boxe1.getPosition().getX()/50) > Math.floor(boxe2.getPosition().getX()/50) || Math.floor(boxe1.getPosition().getX()/50) < Math.floor(boxe2.getPosition().getX()/50)) {
			return true;
		}
		else if (Math.floor(boxe1.getPosition().getY()/50) < Math.floor(boxe2.getPosition().getY()/50) || Math.floor(boxe1.getPosition().getY()/50) > Math.floor(boxe2.getPosition().getY()/50)) {
			return true;
		}

		return false;

	}

	public static Position calculePositionByDirection(Position positionEntity, Direction direction, int speed, int speedJump) {

		Position position = new Position(positionEntity);

		switch (direction) {
		case RIGHT :
			position.addX(speed);
			break;
		case LEFT : 
			position.addX(-speed);
			break;
		case DOWN :
			position.addY(speedJump);
			break;
		case UP : 
			position.addY(-speedJump);
			break;
		default :
			break;
		}
		return new Position(position);
	}


	public static void movements(Player entity, Direction orderDirection, boolean sprintOrder) {
		if (orderDirection != null && !entity.getGravity()) {
			if (orderDirection == Direction.LEFT || orderDirection == Direction.RIGHT) {
				if (canMove(entity.getPosition(), orderDirection, entity.getHitboxe(), entity.getSpeed(), entity.getSpeed())) {
					entity.setDirection(orderDirection);
					if (orderDirection == Direction.LEFT)
						entity.move();
					else
						smothMove(entity, entity.getDirection());

				}
			}

			else if (orderDirection == Direction.DOWN) {

				// super jump destroy block
				if (!entity.isTouchFloor() && canMove(entity.getPosition(), Direction.DOWN, entity.getHitboxe(), entity.getSpeed(), entity.getSpeed())) {
					entity.setDirection(Direction.DOWN);
					entity.setIsJumping(false);
					//entity.setOldBoxesSize(getBoxes(entity.getPosition()).size());
					entity.move();
				}
				if (!entity.isTouchFloor() && !canMove(entity.getPosition(), Direction.DOWN, entity.getHitboxe(), entity.getSpeed(), entity.getSpeed())) {
					Action.destroyIfCan(model.getMap().getDownBoxes(getTheoricalPosition(Direction.DOWN, entity.getPosition(), entity.getSpeed()), entity.getHitboxe()), entity);
				}

				// climb the scale
				if (canMove(entity.getPosition(), Direction.DOWN, entity.getHitboxe(), entity.getSpeed(), entity.getSpeed()) && entity.isClimbingLadder()) {
					entity.setDirection(Direction.DOWN);
					entity.move();
				}
			} else if (orderDirection == Direction.UP) {
				// climb the ladder
				if (canMove(entity.getPosition(), Direction.UP, entity.getHitboxe(), entity.getSpeed(), entity.getSpeed()) && entity.isClimbingLadder()) {
					entity.setDirection(Direction.UP);
					entity.move(Direction.UP);
				}
			} else {
				if (orderDirection == Direction.STATIC) {
					entity.setDirection(orderDirection);
				}
			}
			// is the entity is sprinting, she move twice
			if (sprintOrder) {
				if (entity.getDirection() == Direction.RIGHT || entity.getDirection() == Direction.LEFT)
					if (canMove(entity.getPosition(), entity.getDirection(), entity.getHitboxe(), entity.getSpeed(), entity.getSpeed())) {
						entity.move();
						if (entity.isTouchFloor()) {
							for (MotionLess boxe : model.getMap().getDownBoxes(calculePositionByDirection(entity.getPosition(), Direction.DOWN, entity.getSpeed(), entity.getSpeed()), entity.getHitboxe())) {
								if (boxe.getIsAlive())
									Animation.createBlockParticules(boxe.getName(), boxe.getPosition());
							}
						}
					}
			}
		}
	}


	public static void downGravity(MobileEntity entity) {
		if (!entity.isTouchFloor() && !entity.getIsJumping() && !entity.isClimbingLadder()) {
			if (canMove(entity.getPosition(), Direction.DOWN, entity.getHitboxe(), entity.getSpeed(), entity.getSpeed())) {
				entity.move(Direction.DOWN);
			}
		}
	}

	public static void jump1(MobileEntity entity, boolean jumpOrder, Direction direction) {
		if ((entity.isTouchFloor() && !entity.getGravity()) || entity.getIsJumping()) {
			// jump
			if (canMove(entity.getPosition(), Direction.UP, entity.getHitboxe(), entity.getSpeed(), entity.getSpeed())) {
				entity.jump();
			}
			// end of the jump
			else {
				entity.setIsJumping(false);
			}
			// destroy block on top if it is not solid enough to resist
			if (Action.destroyIfCan(model.getMap().getUpBoxes(getTheoricalPosition(entity.getDirection(), entity.getPosition(), entity.getSpeed()), entity.getHitboxe()), entity)) {
				entity.jump(40);
			}
		}
		if (!entity.isTouchFloor() && !entity.getGravity()
				&& (direction == Direction.LEFT || direction == Direction.RIGHT)) {

		}
		// jump forced
		if (!jumpOrder && !entity.isForceToJump()) {
			entity.setIsJumping(false);
		}
	}

	public static void jump(MobileEntity entity, boolean jumpOrder, Direction direction) {
		if (((entity.isTouchFloor() || entity.isClimbingLadder()) && !entity.getGravity())) {
			// jump
			if (canMove(entity.getPosition(), Direction.UP, entity.getHitboxe(), entity.getSpeed(), entity.getSpeed())) {
				entity.jump();
			}
			// end of the jump
			else {
				entity.setIsJumping(false);
				
			}
			// destroy block on top if it is not solid enough to resist
			if (Action.destroyIfCan(model.getMap().getUpBoxes(getTheoricalPosition(Direction.UP, entity.getPosition(), entity.getSpeed()), entity.getHitboxe()), entity)) {
				entity.jump(40);
			}
		}
		else if (entity.getIsJumping() && !entity.isTouchFloor()) {
			if (canMove(entity.getPosition(), Direction.UP, entity.getHitboxe(), entity.getSpeed(), entity.getSpeed())) {
				entity.jump();
			}
			else {
				if (Action.destroyIfCan(model.getMap().getUpBoxes(getTheoricalPosition(Direction.UP, entity.getPosition(), entity.getSpeed()), entity.getHitboxe()), entity)){}
				entity.setIsJumping(false);
			}
		}
		if (!entity.isTouchFloor() && !entity.getGravity()
				&& (direction == Direction.LEFT || direction == Direction.RIGHT)) {

		}
		// jump forced
		if (!jumpOrder && !entity.isForceToJump()) {
			entity.setIsJumping(false);
		}
	}

	public static void jump(MobileEntity entity, boolean jumpOrder, boolean touchFloor, int heightMini) {
		if (((touchFloor || entity.isClimbingLadder()) && !entity.getGravity()) || entity.getIsJumping()) {
			if (canMove(entity.getPosition(), Direction.UP, entity.getHitboxe(), entity.getSpeed(), entity.getSpeed())) {
				entity.jump(heightMini);
			} else {
				entity.setIsJumping(false);
			}
			if (Action.destroyIfCan(model.getMap().getUpBoxes(entity.getPosition(), entity.getHitboxe()), entity)) {
				entity.jump(40);
			}
		}
		if (!jumpOrder && !entity.isForceToJump()) {
			entity.setIsJumping(false);
		}
	}

	public static void smothMove(MobileEntity entity, Direction direction){
		for (int i = 0; i < entity.getSpeed(); i++) {
			entity.move(direction, 1);
			model.setMobilesHavesMoved();
		}

	}
}

//	public static ArrayList<MotionLess> getDownBoxes(MobileEntity entity, HitBoxe hitboxe) {
//		ArrayList<MotionLess> boxes = new ArrayList<>();
//		if (getBoxes(entity.getPosition(), hitboxe).size() == 2) {
//			boxes.add(model.getMap().getBelowBoxe(getBoxes(entity.getPosition(), hitboxe).get(getBoxes(entity.getPosition(), hitboxe).size() - 1).getPosition()));
//		}
//		else if (getBoxes(entity.getPosition(), hitboxe).size() == 4) {
//			boxes.add(model.getMap().getBelowBoxe(getBoxes(entity.getPosition(), hitboxe).get(1).getPosition()));
//			boxes.add(model.getMap().getBelowBoxe(getBoxes(entity.getPosition(), hitboxe).get(getBoxes(entity.getPosition(), hitboxe).size() - 1).getPosition()));
//		}
//		return boxes;
//	}


//perform the differents actions in parameter

/*------------------------------------------------------------------------------*/
/* GET/SET */
/*------------------------------------------------------------------------------*/
