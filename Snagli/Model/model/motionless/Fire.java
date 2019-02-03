package model.motionless;

import java.util.ArrayList;

import model.enumeration.EffectWhenTutch;
import model.enumeration.GrabableEffect;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.enumeration.Solidity;
import model.genercis.Position;
import model.genercis.Sprite;
import model.other.Random;

public class Fire extends MotionLess{

	public Fire(Position position) {
		super(0, 0, 8, "Fire", Opacity.PENETRABLE, Permeability.DAMAGE, Solidity.WEAK, position ,true, EffectWhenTutch.BURN, GrabableEffect.NONE, Resistance.FIRE, 50, 50, 50, 50);
	}

	protected void createSpriteArray( int xPos, int yPos, int nbSprites, int width, int height) {
		ArrayList<Sprite> spriteArray = new ArrayList<Sprite>();
		for (int i = 0; i < 4; i++) {
			yPos = Random.alea(2);
			for(int i1 = 0; i1 < nbSprites; i1++) {
				spriteArray.add(new Sprite( xPos+i1, yPos, this.spriteSheet, width, height));
			}
		}

		this.setSpriteArray(spriteArray);
	}
	
}
