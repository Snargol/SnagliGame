package model.motionless;

import model.enumeration.BlockEffect;
import model.enumeration.EffectWhenTutch;
import model.enumeration.GrabableEffect;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.enumeration.Solidity;
import model.genercis.Position;

public class Octopus extends MotionLess{
	public Octopus (String name, GrabableEffect effect,Position position) {
		super(0, 0, 1, name, Opacity.PENETRABLE, Permeability.GRABABLE, Solidity.SOLID, position ,false, EffectWhenTutch.NONE, effect, Resistance.NONE, 50, 50, 50, 50);
	}
}
