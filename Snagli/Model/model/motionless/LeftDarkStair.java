package model.motionless;

import model.enumeration.EffectWhenTutch;
import model.enumeration.GrabableEffect;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.enumeration.Solidity;
import model.genercis.Position;

public class LeftDarkStair extends MotionLess{

	public LeftDarkStair(Position position) {
		super(0, 0, 1, "LeftDarkStair", Opacity.BLOCKING, Permeability.NONE, Solidity.NORMAL, position ,false, EffectWhenTutch.NONE, GrabableEffect.NONE, Resistance.NONE, 50, 50, 50, 50);
	}

}
