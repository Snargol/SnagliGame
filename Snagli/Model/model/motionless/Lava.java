package model.motionless;

import model.enumeration.EffectWhenTutch;
import model.enumeration.GrabableEffect;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.enumeration.Solidity;
import model.genercis.Position;

public class Lava extends MotionLess{

	public Lava(Position position) {
		super(0, 0, 6, "Lava", Opacity.PENETRABLE, Permeability.KILLER, Solidity.UNBREKABLE, position ,false, EffectWhenTutch.BURN, GrabableEffect.NONE, Resistance.LAVA, 50, 50, 50, 50);
		// TODO Auto-generated constructor stub
	}

}
