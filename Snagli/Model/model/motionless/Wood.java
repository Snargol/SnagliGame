package model.motionless;

import model.enumeration.EffectWhenTutch;
import model.enumeration.GrabableEffect;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.enumeration.Solidity;
import model.genercis.Position;
import model.other.Random;

public class Wood extends MotionLess{

	public Wood(Position position) {
		super(Random.alea(3), 0, 1, "Wood", Opacity.BLOCKING, Permeability.NONE, Solidity.NORMAL, position ,false, EffectWhenTutch.NONE, GrabableEffect.NONE, Resistance.NONE, 50, 50, 50, 50);
	}

}
