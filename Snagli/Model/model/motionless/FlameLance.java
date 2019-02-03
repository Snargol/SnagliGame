package model.motionless;

import model.enumeration.EffectWhenTutch;
import model.enumeration.GrabableEffect;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.enumeration.Solidity;
import model.genercis.Position;

public class FlameLance extends MotionLess{

	public FlameLance(Position position) {
		super(0, 0, 1, "FlameLance", Opacity.PENETRABLE, Permeability.NONE, Solidity.NORMAL, position ,true, EffectWhenTutch.NONE, GrabableEffect.NONE, Resistance.NONE, 50, 50, 50, 50);
	}

}
