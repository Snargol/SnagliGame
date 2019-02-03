package model.factory;

import model.enumeration.BehaviourMob;
import model.genercis.Position;
import model.mobile.Mob;
import model.mobile.Snagli;

public abstract class MonsterFactory {

	public static Mob createSnagli(Position position) {
		Snagli entity = new Snagli(position, BehaviourMob.RUN_TO_PLAYER);
		return entity;
	}
	
	
	
	public static Mob getFromSymbol(char symbol, Position position) {
		
		switch(symbol) {
		case '2' : 
			return createSnagli(position);
		default : 
			return null;
		}

		
	}
}
