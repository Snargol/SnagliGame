package model.motionless;

import model.enumeration.EffectWhenTutch;
import model.enumeration.GrabableEffect;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.enumeration.Solidity;
import model.genercis.Position;

public class GoldenApple extends MotionLess{

	public GoldenApple(Position position) {
		super(0, 0, 1, "GoldenApple", Opacity.PENETRABLE, Permeability.GRABABLE, Solidity.WEAK, position ,true, EffectWhenTutch.NONE, GrabableEffect.GIVE_REGENERATION, Resistance.NONE, 52, 52, 40, 40);	
		// TODO Auto-generated constructor stub
	}
	
	
}
