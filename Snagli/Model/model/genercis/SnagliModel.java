package model.genercis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import model.bar.IGaugeBar;
import model.bar.InventoryBar;
import model.bar.PowerObject;
import model.bar.Size;
import model.bar.StateBar;
import model.bar.TextsBar;
import model.entities.TemporalEntity;
import model.enumeration.GrabableEffect;
import model.enumeration.Power;
import model.mobile.Balls;
import model.mobile.Mob;
import model.mobile.Player;
import model.other.MovableText;
import model.other.Timer;
import model.particule.Particule;
import model.particule.ParticuleDebris;
import model.projectile.Projectile;

public class SnagliModel extends Observable{

	/*------------------------------------------------------------------------------*/
	/*                                 ATTRIBUTES                                   */
	/*------------------------------------------------------------------------------*/
	private Map 									map;
	private CopyOnWriteArrayList<Mob> 				monsters 			=  new CopyOnWriteArrayList<>();
	private CopyOnWriteArrayList<Balls> 			balls    			=  new CopyOnWriteArrayList<>();
	private ArrayList<IGaugeBar>					gaugeBars 			=  new ArrayList<>();
	private CopyOnWriteArrayList<MovableText> 		texts     			=  new CopyOnWriteArrayList<>();
	private CopyOnWriteArrayList<ParticuleDebris> 	particulesDebris 	=  new CopyOnWriteArrayList<>();
	private CopyOnWriteArrayList<Particule> 		particules 			= new CopyOnWriteArrayList<Particule>();
	//private Vector<Particule>		particules    			=  new Vector<>();
	private CopyOnWriteArrayList<Projectile> 		projectiles			=  new CopyOnWriteArrayList<>();
	private CopyOnWriteArrayList<TemporalEntity> 	temporalEntities	=  new CopyOnWriteArrayList<>();
	
	private ArrayList<PowerObject> 		powerObject 		=  new ArrayList<>();
	private InventoryBar 				inventoryBar;
	private TextsBar					textBar;
	private StateBar 					stateBar;
	private Player 						player;
	private Timer 						timer;
	
	private int COO_PLAYER_DISPLAY_X = 750;
	private int widthScreen = 1400;
	private int SIZE_BOXE = 50;

	/*------------------------------------------------------------------------------*/
	/*                            CONSTRUCTORS/DESTRUCTORS                          */
	/*------------------------------------------------------------------------------*/
	public SnagliModel() {
		this.timer = new Timer();
		timer.start();
	}

	/*------------------------------------------------------------------------------*/
	/*                                 METHODS                                     */
	/*------------------------------------------------------------------------------*/

	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/

	public Timer getTimer() {
		return timer;
	}

	public CopyOnWriteArrayList<Mob> getMonsters() {
		return monsters;
	}

	public void setMonsters(CopyOnWriteArrayList<Mob> monsters) {
		this.monsters = monsters;
	}

	public CopyOnWriteArrayList<Balls> getBalls() {
		return balls;
	}

	public void setBalls(CopyOnWriteArrayList<Balls> balls) {
		this.balls = balls;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public void addMonster(Mob mob) {
		monsters.add(mob);

	}

	public void setMobilesHavesMoved() {
		this.setChanged();
		this.notifyObservers();
	}

	public ArrayList<IGaugeBar> getGaugeBars() {
		return gaugeBars;
	}

	public void addBar(IGaugeBar bar) {
		this.gaugeBars.add(bar);
	}


	public CopyOnWriteArrayList<MovableText> getTexts() {
		return texts;
	}

	public void addtext(MovableText text) {
		this.texts.add(text);
	}
	
	public CopyOnWriteArrayList<ParticuleDebris> getParticulesDebris() {
		return particulesDebris;
	}

	public void addParticulesDebris(ParticuleDebris particuleDebris) {
		this.particulesDebris.add(particuleDebris);
	}
	
	public CopyOnWriteArrayList<Particule> getParticules() {
		return particules;
	}

	public void addParticule(Particule particule) {
		this.particules.add(particule);
	}

	public StateBar getStateBar() {
		return stateBar;
	}

	public void setStateBar(StateBar stateBar) {
		this.stateBar = stateBar;
	}

	public TextsBar getTextBar() {
		return textBar;
	}

	public void setTextBar(TextsBar textBar) {
		this.textBar = textBar;
	}
	
	public CopyOnWriteArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	public void addProjectile(Projectile projectile) {
		this.projectiles.add(projectile);
	}
	
	public CopyOnWriteArrayList<TemporalEntity> getTemporalEntities() {
		return temporalEntities;
	}

	public void addTemporalEntity(TemporalEntity temporalEntity) {
		this.temporalEntities.add(temporalEntity);
	}
	
	public int getDistanceGap() {
		if (player.getPosition().getX() < COO_PLAYER_DISPLAY_X ) {
			return 0;
		}
//		else if (player.getPosition().getX() + 16 * 50 > (getMap().getWidth() * 50)) {
//			return player.getPosition().getX();
//		}
		else if (player.getPosition().getX() + ((getWidthScreen()-COO_PLAYER_DISPLAY_X-getPlayer().getHitboxe().getWidth())) >= (getMap().getWidth() * SIZE_BOXE)) {
			//decalage des objets
			return COO_PLAYER_DISPLAY_X - (getMap().getWidth()*SIZE_BOXE - ((getWidthScreen()-COO_PLAYER_DISPLAY_X-getPlayer().getHitboxe().getWidth())));
		}
		return (COO_PLAYER_DISPLAY_X - player.getPosition().getX());
	}
	
	public int getDistanceGapMap() {
		if (player.getPosition().getX() < COO_PLAYER_DISPLAY_X ) {
			return 0;
		}
		else if (player.getPosition().getX() + ((getWidthScreen()-COO_PLAYER_DISPLAY_X-getPlayer().getHitboxe().getWidth())) >= (getMap().getWidth() * SIZE_BOXE)) {
			//decalage de la map
			return COO_PLAYER_DISPLAY_X - (getMap().getWidth()*SIZE_BOXE - (getWidthScreen() - (COO_PLAYER_DISPLAY_X + getPlayer().getHitboxe().getWidth())));
		}
		return (COO_PLAYER_DISPLAY_X - player.getPosition().getX());
	}
	
	public ArrayList<PowerObject> getPowerObject() {
		return powerObject;
	}

	public void setPowerObject(ArrayList<PowerObject> powerObject) {
		this.powerObject = powerObject;
	}
	
	public void addPowerObject(GrabableEffect grabableEffect) {
		if (getInventoryBar().containsGrabableEffect(grabableEffect)) {
			for (PowerObject powerObject : getPowerObject()) {
				if (powerObject.getGrabableEffect() == grabableEffect) {
					powerObject.setNumber(powerObject.getNumber() + 1);
				}
			}
		}
		else {
			getPowerObject().add(new PowerObject(
					grabableEffect, 
					new Frame(getInventoryBar().getCurrentPowerSize().getWidth(), getInventoryBar().getCurrentPowerSize().getHeight(),getNameByGrabableEffect(grabableEffect), "Images/Bar/PowerFrames/"),
					1, new Position(getInventoryBar().calculCoordonates(getPowerObject().size()-1)), 
					new Size(getInventoryBar().getCurrentPowerSize().getWidth(), getInventoryBar().getCurrentPowerSize().getHeight())));
		}
		
	}
	
	public String getNameByGrabableEffect(GrabableEffect grabableEffect) {
		switch (grabableEffect) {
		case GIVE_FIRE :
			return "FireOctopus";
//		case GIVE_HEALTH :
//			return "Heart";
		case GIVE_ICE :
			return "IceOctopus";
		case GIVE_INVULNERABILITY:
			return "Burger";
		case GIVE_MISSILE:
			return "MissileOctopus";
		case GIVE_POISON :
			return "PoisonOctopus";
//		case GIVE_REGENERATION : 
//			return "GoldenApple";
		default : 
			return "UNKNOW";
		}
	}
	
	public void removePowerObject(GrabableEffect grabableEffect) {
		ArrayList<Integer> list = new ArrayList<>();
		int i = 0;
		if (getInventoryBar().containsGrabableEffect(grabableEffect)) {
			for (PowerObject powerObject : getPowerObject()) {
				if (powerObject.getGrabableEffect() == grabableEffect) {
					powerObject.setNumber(powerObject.getNumber() - 1);
					if (powerObject.getNumber() <= 0) {
						list.add(i);
					}
				}
				i++;
			}
			for (Integer integer : list) {
				getPowerObject().remove(integer.intValue());
			}
			getInventoryBar().adaptCoordonates();
		}
//		else {
//			getPowerObject().add(new PowerObject(
//					power, 
//					new Frame(getInventoryBar().getCurrentPowerSize().getWidth(), getInventoryBar().getCurrentPowerSize().getHeight(),power+"Octopus", "Images/Bar/PowerFrames/"),
//					1, new Position(getInventoryBar().calculCoordonates(getPowerObject().size()-1)), 
//					new Size(getInventoryBar().getCurrentPowerSize().getWidth(), getInventoryBar().getCurrentPowerSize().getHeight())));
//		}
	}

	public int getWidthScreen() {
		return widthScreen;
	}

	public void setWidthScreen(int widthScreen) {
		this.widthScreen = widthScreen;
	}

	public InventoryBar getInventoryBar() {
		return inventoryBar;
	}

	public void setInventoryBar(InventoryBar inventoryBar) {
		this.inventoryBar = inventoryBar;
	}
	
	
}
