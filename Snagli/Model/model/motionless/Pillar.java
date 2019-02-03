package model.motionless;

import model.enumeration.EffectWhenTutch;
import model.enumeration.GrabableEffect;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.enumeration.Solidity;
import model.genercis.Position;

public class Pillar extends MotionLess{

	public Pillar(Position position) {
		super((int)(Math.random() * ((3 - 0)) + 1), 0 ,1, "Pillar", Opacity.PENETRABLE, Permeability.NONE, Solidity.UNBREKABLE, position ,false, EffectWhenTutch.NONE, GrabableEffect.NONE, Resistance.NONE, 50, 150, 50, 150);
		// TODO Auto-generated constructor stub
	}

}
