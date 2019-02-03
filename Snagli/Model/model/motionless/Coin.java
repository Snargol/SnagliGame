package model.motionless;

import model.enumeration.EffectWhenTutch;
import model.enumeration.GrabableEffect;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.enumeration.Solidity;
import model.genercis.Position;

public class Coin extends MotionLess{

	public Coin(Position position) {
		
		super(0, 0, 9, "Coin", Opacity.PENETRABLE, Permeability.GRABABLE, Solidity.WEAK, position ,false, EffectWhenTutch.NONE, GrabableEffect.GIVE_MONEY, Resistance.NONE, 50, 50, 50, 50);
		// TODO Auto-generated constructor stub
	}

}
