package model.motionless;

import model.enumeration.BlockEffect;
import model.enumeration.EffectWhenTutch;
import model.enumeration.GrabableEffect;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.enumeration.Solidity;
import model.genercis.Position;

public class Barrier extends MotionLess{

	public Barrier(Position position) {
		super(0, 0, 1, "Barrier", Opacity.BLOCKING, Permeability.NONE, Solidity.UNBREKABLE, position ,false, EffectWhenTutch.NONE, GrabableEffect.NONE, Resistance.NONE, 50, 50, 50, 50);
		// TODO Auto-generated constructor stub
	}

}
