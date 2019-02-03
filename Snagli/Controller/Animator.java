	
import model.entities.TemporalEntity;
import model.genercis.SnagliModel;
import model.mobile.Mob;
import model.motionless.MotionLess;
import model.particule.Particule;
import model.particule.ParticuleDebris;


public class Animator {
	/*------------------------------------------------------------------------------*/
	/*                                 ATTRIBUTES                                   */
	/*------------------------------------------------------------------------------*/
	private 			SnagliModel	model = null;
	private 			int 			speedMapAnimation 			= 100;
	private 			int				speedTemporalEntities		= 100; 
	private 			int 			speedPlayerAnimation 		= 100;
	private 			int 			speedParticulesAnimation 	= 100;
	private 			int 			speedMobsAnimation 			= 100;
	private 			int 			speedStateAnimation 		= 150;
	private 			int				speedDebrisParticules		= 300;
	private volatile 	boolean 		running 					= false;
	private 			long 			previousTime;
	private 			long 			previousTimeParticules;
	private 			long 			previousTimeDebris;
	private 			long 			previousTimeState;
	private 			long 			previousTimeTemporal;
	private 			int 			speed;
	private 			int 			frameAtPause;
	private 			int 			currentFrame;


	/*------------------------------------------------------------------------------*/
	/*                            CONSTRUCTORS/DESTRUCTORS                          */
	/*------------------------------------------------------------------------------*/
	public Animator(SnagliModel model) {
		this.model = model;
	}


	/*------------------------------------------------------------------------------*/
	/*                                 METHODS                                     */
	/*------------------------------------------------------------------------------*/
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void update(long time) {
		if (running) {
			if(model.getMonsters()!= null) {
				if(time - previousTime >= speedMobsAnimation) {
					currentFrame++;
					for(Mob monster : model.getMonsters()) {
						if(monster.getSelectedSpriteValue() >= monster.getSpriteArraySize()-1) {
							monster.setSelectedSpriteValue(0);
						}
						else {
							monster.setSelectedSpriteValue(monster.getSelectedSpriteValue()+1);
						}
					}
				} 
			}
			if(model.getTemporalEntities()!= null) {
				if(time - previousTimeTemporal >= speedTemporalEntities) {
					currentFrame++;
					for(TemporalEntity entity : model.getTemporalEntities()) {
						if(entity.getSelectedSpriteValue() >= entity.getSpriteArraySize()-1) {
							entity.setSelectedSpriteValue(0);
							entity.setSpriteSheetFullyScanned(true);
						}
						else {
							entity.setSelectedSpriteValue(entity.getSelectedSpriteValue()+1);
						}
						entity.checkSpriteArrayToDraw(model.getTimer().getTimeMilliSeconds());
					}
					previousTimeTemporal = time;
				} 
			}
			if(model.getParticules() != null) {
				if(time - previousTimeParticules >= speedParticulesAnimation) {
					currentFrame++;
					for (Particule particule : model.getParticules()) {
						if(particule.getSelectedSpriteValue() >= particule.getSpriteArraySize()-1) {
							particule.setSelectedSpriteValue(0);
						}
						else {
							particule.setSelectedSpriteValue(particule.getSelectedSpriteValue()+1);
						}
					}
					previousTimeParticules = time;
				}
			}
			if(model != null) {
				if (model.getParticulesDebris() != null) {
					if(time - previousTimeDebris >= speedDebrisParticules) {
						currentFrame++;
						for(ParticuleDebris part : model.getParticulesDebris()) {
							if(part.getDebris1().getSelectedSpriteValue() >= part.getDebris1().getSpriteArraySize()-1) {
								part.getDebris1().setSelectedSpriteValue(0);
							}
							else {
								part.getDebris1().setSelectedSpriteValue(part.getDebris1().getSelectedSpriteValue()+1);
							}
							if(part.getDebris2().getSelectedSpriteValue() >= part.getDebris2().getSpriteArraySize()-1) {
								part.getDebris2().setSelectedSpriteValue(0);
							}
							else {
								part.getDebris2().setSelectedSpriteValue(part.getDebris2().getSelectedSpriteValue()+1);
							}
							if(part.getDebris3().getSelectedSpriteValue() >= part.getDebris3().getSpriteArraySize()-1) {
								part.getDebris3().setSelectedSpriteValue(0);
							}
							else {
								part.getDebris3().setSelectedSpriteValue(part.getDebris3().getSelectedSpriteValue()+1);
							}
						}
						previousTimeDebris = time;
					}
				}
			}
			if (model.getMap() != null) {
				if(time - previousTime >= speedMapAnimation) {
					currentFrame++;
					for(int y = 0; y < model.getMap().getHeight(); y++) {
						for(int x = 0; x < model.getMap().getWidth(); x++) {
							MotionLess block = model.getMap().getOnTheMap(x, y);
							if(block.getIsAlive()) {
								if(block.getSelectedSpriteValue() >= block.getSpriteArraySize()-1) {
									block.setSelectedSpriteValue(0);
								}
								else {
									block.setSelectedSpriteValue(block.getSelectedSpriteValue()+1);
								}
							}
						}
					}
					previousTime = time;
				}
			}
			if (model.getStateBar() != null) {
				if (time - previousTimeState >= speedStateAnimation) {
					if(model.getStateBar().getActualState().getSelectedSpriteValue() >= model.getStateBar().getActualState().getSpriteArraySize()-1) {
						model.getStateBar().getActualState().setSelectedSpriteValue(0);
					}
					else {
						model.getStateBar().getActualState().setSelectedSpriteValue(model.getStateBar().getActualState().getSelectedSpriteValue()+1);
					}
					previousTimeState = time;
				}
			}
		}



	}


	public void start() {
		running = true;
		previousTime = 0;
		frameAtPause = 0;
		currentFrame = 0;
	}


	public void stop() {
		running = false;
	}


	public void pause() {
		frameAtPause = currentFrame;
		running = false;
	}


	public void resume() {
		currentFrame = frameAtPause;
		running = true;
	}
	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/








}
