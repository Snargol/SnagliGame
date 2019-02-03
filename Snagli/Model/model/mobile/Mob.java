package model.mobile;

import java.util.ArrayList;

import model.enumeration.BehaviourMob;
import model.enumeration.EffectWhenTutch;
import model.enumeration.Opacity;
import model.genercis.HitBoxe;
import model.genercis.Position;
import model.genercis.Sprite;

public class Mob extends MobileEntity{

	private BehaviourMob behaviour;
	private ArrayList<Sprite> burnSprites;
	private ArrayList<Sprite> deadSprites;
	private ArrayList<Sprite> splashSprites;
	
	public Mob(int xSpritePos, int ySpritePos, int nbSprites,String name, Opacity opacity, HitBoxe hitboxe, Position position,
			BehaviourMob behaviour,  int widthFrame, int heightFrame, int widthDisplay, int heightDisplay) {
		super(xSpritePos, ySpritePos, nbSprites, name, opacity, hitboxe, position,  widthFrame, heightFrame, widthDisplay, heightDisplay, EffectWhenTutch.NORMAL_DAMAGE);
		this.behaviour = behaviour;
		// TODO Auto-generated constructor stub
	}
	
}
;