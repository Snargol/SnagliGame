package model.motionless;

import model.enumeration.EffectWhenTutch;
import model.enumeration.GrabableEffect;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.enumeration.Solidity;
import model.genercis.Position;

public class Limit extends MotionLess{

	public Limit(Position position) {
		super(0, 0, 1, "Limit", Opacity.BLOCKING, Permeability.KILLER, Solidity.UNBREKABLE, position ,false, EffectWhenTutch.NONE, GrabableEffect.NONE, Resistance.NONE, 50, 50, 50, 50);
		// TODO Auto-generated constructor stub
	}

}
