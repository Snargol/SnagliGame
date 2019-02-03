package model.motionless;

import model.enumeration.EffectWhenTutch;
import model.enumeration.GrabableEffect;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.enumeration.Solidity;
import model.genercis.Position;
import model.other.Random;

public class Glass extends MotionLess{

	public Glass(Position position) {
		super(0, Random.alea(3), 1, "Glass", Opacity.BLOCKING, Permeability.NONE, Solidity.WEAK, position ,true, EffectWhenTutch.NONE, GrabableEffect.NONE, Resistance.NONE, 32, 32, 50, 50);	
	}

}
