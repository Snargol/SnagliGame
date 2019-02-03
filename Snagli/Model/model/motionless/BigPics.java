package model.motionless;

import model.enumeration.BlockEffect;
import model.enumeration.EffectWhenTutch;
import model.enumeration.GrabableEffect;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.enumeration.Solidity;
import model.genercis.Position;

public class BigPics extends MotionLess{

	public BigPics(Position position) {
		super(0, 0, 1, "BigPics", Opacity.PENETRABLE, Permeability.DAMAGE, Solidity.NORMAL, position ,true, EffectWhenTutch.NORMAL_DAMAGE, GrabableEffect.NONE, Resistance.PICS, 50, 50, 50, 50);
		// TODO Auto-generated constructor stub
	}

}
