package model.motionless;

import model.enumeration.EffectWhenTutch;
import model.enumeration.GrabableEffect;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.enumeration.Solidity;
import model.genercis.Position;
import model.other.Random;

public class SmallPavingHerbsHole extends MotionLess{

	public SmallPavingHerbsHole(Position position) {
		super(Random.alea(7), 0, 1, "SmallPavingHerbsHole", Opacity.BLOCKING, Permeability.NONE, Solidity.NORMAL, position ,false, EffectWhenTutch.NONE, GrabableEffect.NONE, Resistance.NONE, 50, 50, 50, 50);
	}
}
