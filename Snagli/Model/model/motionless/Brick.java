package model.motionless;

import model.enumeration.BlockEffect;
import model.enumeration.EffectWhenTutch;
import model.enumeration.GrabableEffect;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.enumeration.Solidity;
import model.genercis.Position;

public class Brick extends MotionLess{

	public Brick(Position position) {
		super((int)(Math.random() * ((3 - 0)) + 1), 0, 1, "Brick", Opacity.BLOCKING, Permeability.NONE, Solidity.WEAK, position ,false, EffectWhenTutch.NONE, GrabableEffect.NONE, Resistance.NONE, 32, 32, 50, 50);
	}

}
