package model.motionless;

import model.enumeration.EffectWhenTutch;
import model.enumeration.GrabableEffect;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.enumeration.Solidity;
import model.genercis.Position;

public class SimpleBlock extends MotionLess {

	public SimpleBlock(Position position) {
		super(0, 0, 1, "SimpleBlock", Opacity.BLOCKING, Permeability.NONE, Solidity.WEAK, position ,false, EffectWhenTutch.NONE, GrabableEffect.NONE, Resistance.NONE, 32, 32, 50, 50);
		// TODO Auto-generated constructor stub
	}

}
