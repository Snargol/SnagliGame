package model.motionless;

import model.enumeration.EffectWhenTutch;
import model.enumeration.GrabableEffect;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.enumeration.Solidity;
import model.genercis.Position;
import model.other.Random;

public class Flower extends MotionLess{

	public Flower(Position position) {
		super(0, Random.alea(18), 1, "Decort/", "Flowers", Opacity.PENETRABLE, Permeability.NONE, Solidity.WEAK, position ,true, EffectWhenTutch.NONE, GrabableEffect.NONE, Resistance.NONE, 32, 32, 50, 50);		// TODO Auto-generated constructor stub
	}

}
