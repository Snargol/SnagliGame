package model.particule;

import java.util.ArrayList;

import model.genercis.Position;
import model.other.Random;

public class ParticuleStatic {
	/*------------------------------------------------------------------------------*/
	/*                                 ATTRIBUTES                                   */
	/*------------------------------------------------------------------------------*/
	private ArrayList<Particule> particules = new ArrayList<>();
	/*------------------------------------------------------------------------------*/
	/*                            CONSTRUCTORS/DESTRUCTORS                          */
	/*------------------------------------------------------------------------------*/
	public ParticuleStatic(String name, Position position, int number, int timeLife, long time) {
		for (int i= 0; i < number ; i++) {
			particules.add(new Particule(name, new Position(position),Random.alea(-30, 50), Random.alea(15, 80), timeLife, time));
		}
	}
	
	public ParticuleStatic(String name, Position position, int time) {
		for (int i= 0; i < 3 ; i++) {
			particules.add(new Particule(name, new Position(position),Random.alea(-30, 50), Random.alea(15, 80), 1500, time));
		}
	}
	/*------------------------------------------------------------------------------*/
	/*                                 METHODS                                     */
	/*------------------------------------------------------------------------------*/
	
	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/
	
	public ArrayList<Particule> getParticules() {
		return particules;
	}
	
	public boolean isAlive(long time) {
		for (Particule particule : particules) {
			if (!particule.isAlive(time))
				return false;
		}
		return true;
	}
	
}
