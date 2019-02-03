package model.motionless;

import model.enumeration.BlockEffect;
import model.enumeration.EffectWhenTutch;
import model.enumeration.GrabableEffect;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.enumeration.Solidity;
import model.genercis.Position;

public class Burger extends MotionLess{

	public Burger(Position position) {
		super(0, 0, 7, "Burger", Opacity.PENETRABLE, Permeability.GRABABLE, Solidity.WEAK, position ,false, EffectWhenTutch.NONE, GrabableEffect.GIVE_HEALTH, Resistance.NONE,48, 48, 50, 50);
		// TODO Auto-generated constructor stub
	}

}
