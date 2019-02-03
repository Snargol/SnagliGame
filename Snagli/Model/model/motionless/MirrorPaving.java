package model.motionless;

import model.enumeration.EffectWhenTutch;
import model.enumeration.GrabableEffect;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.enumeration.Solidity;
import model.genercis.Position;
import model.other.Random;

public class MirrorPaving extends MotionLess{

	public MirrorPaving(Position position) {
		super(Random.alea(7), 0, 1, "MirrorPaving", Opacity.BLOCKING, Permeability.NONE, Solidity.UNBREKABLE, position ,false, EffectWhenTutch.NONE, GrabableEffect.NONE, Resistance.NONE, 50, 50, 50, 50);
	}
}
