package model.motionless;

import model.enumeration.EffectWhenTutch;
import model.enumeration.GrabableEffect;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.enumeration.Solidity;
import model.genercis.Position;
import model.other.Random;

public class Grass extends MotionLess{

	public Grass(Position position) {
		super(Random.alea(3), 0, 1, "Grass", Opacity.BLOCKING, Permeability.NONE, Solidity.WEAK, position ,false, EffectWhenTutch.NONE, GrabableEffect.NONE, Resistance.NONE, 32, 32, 50, 50);	
	}

}
