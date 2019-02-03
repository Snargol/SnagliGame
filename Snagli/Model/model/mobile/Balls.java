package model.mobile;

import javax.swing.Spring;

import model.enumeration.EffectWhenTutch;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Power;
import model.genercis.HitBoxe;
import model.genercis.Position;

public class Balls extends MobileEntity{
	private int damages;
	private Power power;
	private Spring curve; //change type later
	
	public Balls(Position position,String name,  int widthFrame, int heightFrame, int widthDisplay, int heightDisplay) {
		super( 0, 0, 1, name, Opacity.BLOCKING, new HitBoxe(50,50), position,  widthFrame, heightFrame, widthDisplay, heightDisplay, EffectWhenTutch.NORMAL_DAMAGE);
		// TODO Auto-generated constructor stub
	}
}
