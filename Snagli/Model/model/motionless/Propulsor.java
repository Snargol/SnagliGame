package model.motionless;

import model.enumeration.EffectWhenTutch;
import model.enumeration.GrabableEffect;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.enumeration.Solidity;
import model.genercis.Position;

public class Propulsor extends MotionLess{

	public Propulsor(Position position) {
		super(0, 0, 6, "Propulsor", Opacity.PENETRABLE, Permeability.ELEVATOR, Solidity.SOLID, position ,true, EffectWhenTutch.NONE, GrabableEffect.NONE, Resistance.NONE, 50, 50, 50, 50);
		// TODO Auto-generated constructor stub
	}

}
