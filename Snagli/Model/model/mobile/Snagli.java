package model.mobile;

import model.enumeration.BehaviourMob;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.genercis.HitBoxe;
import model.genercis.Position;

public class Snagli extends Mob{
	public Snagli(Position position, BehaviourMob behaviour) {
		super( 0, 0, 10, "Snagli", Opacity.BLOCKING, new HitBoxe(50,50), position, behaviour, 32, 32, 50, 50);
		// TODO Auto-generated constructor stub
		setLifeMax(35);
		setDamage(34);
		setSpeed(2);
		//setResistance(Resistance.POISON, Resistance.PICS);
	}
}
