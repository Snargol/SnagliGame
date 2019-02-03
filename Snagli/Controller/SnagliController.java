import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import model.bar.CoinBar;
import model.bar.InventoryBar;
import model.bar.LifeBar;
import model.bar.StateBar;
import model.bar.TextsBar;
import model.entities.TemporalEntity;
import model.enumeration.Direction;
import model.enumeration.GrabableEffect;
import model.enumeration.Power;
import model.factory.BlockFactory;
import model.factory.MonsterFactory;
import model.genercis.HitBoxe;
import model.genercis.Map;
import model.genercis.Position;
import model.genercis.SnagliModel;
import model.mobile.Mob;
import model.mobile.MobileEntity;
import model.mobile.Player;
import model.motionless.MotionLess;
import model.other.MovableText;
import model.other.Random;
import model.particule.Particule;
import model.particule.ParticuleDebris;
import model.projectile.Projectile;
import view.SnagliView;

public class SnagliController {
	/*------------------------------------------------------------------------------*/
	/*                                 ATTRIBUTES                                   */
	/*------------------------------------------------------------------------------*/
	private Map 				map;
	private static SnagliModel 	model;
	private SnagliView 			view;
	private Movement			movement;
	private String 				LEVEL = "DB/level_06.txt";
	private int 				TIME_SLEEP = 20;

	private Animator 			animator;
	/*------------------------------------------------------------------------------*/
	/*                            CONSTRUCTORS/DESTRUCTORS                          */
	/*------------------------------------------------------------------------------*/
	public SnagliController(SnagliView view, SnagliModel model) {
		this.view = view;
		this.model = model;
		this.movement = new Movement(model, 50);
		Checks.setModel(model);
		Action.setModel(model);
		Animation.setModel(model);
		ApplyEffect.setModel(model);
		ProjectileController.setModel(model);
		load();
	}

	/*------------------------------------------------------------------------------*/
	/*                                 METHODS                                     */
	/*------------------------------------------------------------------------------*/
	private void buildMap(String level) {
		Path path = Paths.get(level);
		int x = 0;
		int y = 0;
		int width;
		int height;

		BlockFactory.setModel(model);
		List<String> lignes;
		try {
			lignes = Files.readAllLines(path);

			width = Integer.parseInt(lignes.get(0));
			height = Integer.parseInt(lignes.get(1));

			Map map = new Map(width, height);
			model.setMap(map);

			for (int i = 2; i < lignes.size();i++) {

				while(lignes.get(i).charAt(x) != '|') {
					if (lignes.get(i).charAt(x) == 'P') {
						model.setPlayer(new Player(new Position(x, y)));
						model.getMap().setOnTheMap(BlockFactory.getFromSymbol(' ', new Position(x, y)), new Position(x,y));
					}
					else if (isInteger(Character.toString(lignes.get(i).charAt(x)))) {
						model.addMonster(MonsterFactory.getFromSymbol(lignes.get(i).charAt(x),new Position(x,y)));
						model.getMap().setOnTheMap(BlockFactory.getFromSymbol(' ',new Position(x, y)), new Position(x,y));
					}
					else {
						model.getMap().setOnTheMap(BlockFactory.getFromSymbol(lignes.get(i).charAt(x),new Position(x, y)), new Position(x,y));	
					}
					x++;
				}
				x = 0;
				y ++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void buildBars() {
		model.addBar(new LifeBar(new Position(15,15)));
		model.addBar(new CoinBar(new Position(15,55)));
		model.setStateBar(new StateBar(new Position(780,15)));
		model.setTextBar(new TextsBar(new Position(600, 15), model));
		model.setInventoryBar(new InventoryBar(new Position(870, 10), model));
	}

	public boolean isInteger(String chaine) {
		try {
			Integer.parseInt(chaine);
		} catch (NumberFormatException e){
			return false;
		}
		return true;
	}

	public void load() {
		buildMap(LEVEL);
		buildBars();
	

		animator = new Animator(model);
		animator.setSpeed(100);
		animator.start();

		view.setModelHasLoad();
		
		ArrayList<GrabableEffect> list = new ArrayList<>();
		list.add(GrabableEffect.GIVE_FIRE);
		list.add(GrabableEffect.GIVE_ICE);
		list.add(GrabableEffect.GIVE_MISSILE);
		list.add(GrabableEffect.GIVE_POISON);
		list.add(GrabableEffect.GIVE_HEALTH);
		list.add(GrabableEffect.GIVE_INVULNERABILITY);
		
		for (int i = 0; i < 25;i++) {
			model.addPowerObject(list.get(Random.alea(3)));
		}

		EntityThread entityThread = new EntityThread(model);
		entityThread.start();

		gameLoop();
	}

	public void gameLoop() {
		while (true) {

			model.setMobilesHavesMoved();
			removeDeadEntity();
			animator.update(model.getTimer().getTimeMilliSeconds());
			//particulesAnimator.update(model.getTimer().getTimeMilliSeconds());
			actionsEntities();
			try {
				Thread.sleep(TIME_SLEEP);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void removeDeadEntity() {
		ArrayList<Integer> tab = new ArrayList<>();

		if (model.getProjectiles().size() > 0) {
			tab = new ArrayList<>();
			int i = 0;
			for (Projectile p : model.getProjectiles()) {
				if (!p.getIsAlive()) {
					tab.add(i);
				}
				i++;
			}
			for (Integer integer : tab) {
				if (model.getProjectiles().size() > integer.intValue())
					model.getProjectiles().remove(integer.intValue());
			}
		}
		if (model.getParticules().size() > 0) {
			tab = new ArrayList<>();
			int i = 0;
			for (Particule p : model.getParticules()) {
				if (!p.isAlive(model.getTimer().getTimeMilliSeconds())) {
					tab.add(i);
				}
				i++;
			}
			for (Integer integer : tab) {
				if (model.getParticules().size() > integer.intValue())
					model.getParticules().remove(integer.intValue());
			}
		}
		if (model.getParticulesDebris().size() > 0) {
			tab = new ArrayList<>();
			int i = 0;
			for (ParticuleDebris p_d : model.getParticulesDebris()) {
				if (!p_d.isAlive()) {
					tab.add(i);
				}
				i++;
			}
			for (Integer integer : tab) {
				if (model.getParticulesDebris().size() > integer.intValue())
					model.getParticulesDebris().remove(integer.intValue());
			}
		}
		if (model.getTexts().size() > 0) {
			tab = new ArrayList<>();
			int i = 0;
			for (MovableText text : model.getTexts()) {
				if (!text.isAlive()) {
					tab.add(i);
				}
				i++;
			}
			for (Integer integer : tab) {
				if (model.getTexts().size() > integer.intValue())
					model.getTexts().remove(integer.intValue());
			}
		}




	}

	public void actionsEntities() {
		playerTurn();
		projectileTurn();
	}

	public void playerTurn() {
		if (!model.getPlayer().getIsFreeze() && model.getPlayer().getIsAlive()) {
			String[] keys = view.getKeys();
			actionPerform(model.getPlayer(), getDirectionByKeysDirection(keys[0]), getFireOrderByKeyFireAndKeysDirection(keys[1], keys[0]), getJumpOrderByKeyJump(keys[2]), getSprintOrderByKeySprint(keys[3]), keys[4]);
		}
	}

	public void projectileTurn() {
		if (!model.getProjectiles().isEmpty()) {
			//int i = 0;
			for (Projectile projectile : model.getProjectiles()) {
				if (projectile.getIsAlive()) {
					actionPerform(projectile);
				}
				//i++;
			}
		}
	}

	public static void actionPerform(Player entity, Direction orderDirection, boolean fireOrder, boolean jumpOrder, boolean sprintOrder, String activePower) {

		entity.setTouchFloor(Checks.checkActiveFloor(entity, orderDirection));
		//Checks.checkEffectsPlayer(model.getTimer().getTimeMilliSeconds(), entity);
		Checks.checksinvulnerabilityAndEffects(model.getTimer().getTimeMilliSeconds(), entity);
		Checks.checkActivesBoxes(entity);

		Checks.checkDamagesEntity(entity);
		// if the entity does not touch the ground and does not jump, then it falls
		Movement.downGravity(entity); 
		
		//if possible, active the selected power
		ApplyEffect.activeSelectedPower(activePower);
		
		// if possible, apply the jumpOrder and detect obstacles
		if (jumpOrder || entity.getIsJumping()) {
			Movement.jump(entity, jumpOrder, orderDirection);
		}
		// if is possible, apply the orderDirection 
		Movement.movements(entity, orderDirection, sprintOrder);
		// if possible, apply the fireOrder
		Action.fire(entity, orderDirection,fireOrder);
	}

	public void actionPerform(Projectile projectile) {

		if (Movement.canMove(projectile.getPosition(), projectile.getDirection(), projectile.getHitboxe(), projectile.getSpeed(),projectile.getSpeed())) {
			projectile.move();
			Animation.createSmokeTrace(projectile);

		}
		else {
			if (projectile.getDirection() == Direction.RIGHT) {
				Action.explode(model.getMap().getBoxeByDisplayPosition(projectile.getPosition().getX() + 50,projectile.getPosition().getY()).getPosition(), 1);
			}
			else {
				Action.explode(model.getMap().getBoxeByDisplayPosition(projectile.getPosition()).getPosition(), 1);
			}
			projectile.setAlive(false);
		}
	}
	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/

	public Direction getDirectionByKeysDirection(String keysDirection) {
		switch (keysDirection) {
		case "0001" :
			return Direction.UP;
		case "0010" :
			return Direction.RIGHT;
		case "0011" :
			return Direction.DOWN;
		case "0100" :
			return Direction.LEFT;
		default : 
			return Direction.STATIC;
		}
	}

	public boolean getFireOrderByKeyFireAndKeysDirection(String keyFire, String keysDirection) {
		if (keyFire == "0001") {
			return true;
		}
		return false;
	}

	public boolean getJumpOrderByKeyJump(String keyJump) {
		if (keyJump == "0001") {
			return true;
		}
		return false;	
	}

	public boolean getSprintOrderByKeySprint(String keySprint) {
		if (keySprint == "0001") {
			return true;
		}
		return false;	
	}




}




























