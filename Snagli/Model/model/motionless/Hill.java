package model.motionless;

import model.enumeration.EffectWhenTutch;
import model.enumeration.GrabableEffect;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.enumeration.Solidity;
import model.genercis.Position;

public class Hill extends MotionLess{

	public Hill(Position position) {
		super((int)(Math.random() * ((7 - 0)) + 1), 0, 1, "Hill", Opacity.PENETRABLE, Permeability.GRABABLE, Solidity.UNBREKABLE, position ,true, EffectWhenTutch.NONE, GrabableEffect.NONE, Resistance.NONE, 100, 50, 400, 200);	
	}

}
