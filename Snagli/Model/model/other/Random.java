package model.other;

import java.util.ArrayList;

import model.enumeration.GrabableEffect;
import model.enumeration.Power;

public class Random {
	public static int alea(int min, int max) {
		return (min + (int) Math.round(Math.random() * ((max - min) + 1)));
	}
	
	public static int alea( int max) {
		return  (int) Math.round(Math.random() * max);
	}
	
	public static GrabableEffect getGrabableObject() {
		ArrayList<GrabableEffect> list = new ArrayList<>();
		list.add(GrabableEffect.GIVE_MISSILE);
		list.add(GrabableEffect.GIVE_FIRE);
		list.add(GrabableEffect.GIVE_ICE);
		list.add(GrabableEffect.GIVE_MONEY);
		list.add(GrabableEffect.GIVE_MONEY);
		list.add(GrabableEffect.GIVE_POISON);
		list.add(GrabableEffect.GIVE_HEALTH);
		list.add(GrabableEffect.GIVE_REGENERATION);
		
		
		return list.get(Random.alea(list.size()-1));
		
	}
}
