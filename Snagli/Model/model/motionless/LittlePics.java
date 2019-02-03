package model.motionless;

import model.enumeration.EffectWhenTutch;
import model.enumeration.GrabableEffect;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.enumeration.Solidity;
import model.genercis.Position;

public class LittlePics extends MotionLess{

	public LittlePics(Position position) {
	
		super(0, 0, 1, "LittlePics", Opacity.PENETRABLE, Permeability.DAMAGE, Solidity.SOLID, position ,true, EffectWhenTutch.SOFT_DAMAGE, GrabableEffect.NONE, Resistance.PICS, 32, 32, 50, 50);
		// TODO Auto-generated constructor stub
	}

}
