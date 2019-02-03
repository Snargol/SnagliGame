package model.motionless;

import model.enumeration.EffectWhenTutch;
import model.enumeration.GrabableEffect;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.enumeration.Solidity;
import model.genercis.Position;

public class Ladder extends MotionLess{

	public Ladder(Position position) {
		super(0, 0, 1, "Ladder", Opacity.PENETRABLE, Permeability.SCALABLE, Solidity.UNBREKABLE, position ,false, EffectWhenTutch.NONE, GrabableEffect.NONE, Resistance.NONE, 32, 32, 50, 50);
		// TODO Auto-generated constructor stub
	}

}
