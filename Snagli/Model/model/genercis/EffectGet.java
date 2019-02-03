package model.genercis;
import model.enumeration.BlockEffect;
import model.enumeration.EffectWhenTutch;
import model.enumeration.EffectWhenTutch;;

public class EffectGet {

	static int HEAL = 30;
	static int REGENERATION = 5;
	static int BURN_EFFECT = 7;
	static int BURN = 34;
	static int VERY_SOFT_DAMAGE = 5;
	static int SOFT_DAMAGE = 10;
	static int NORMAL_DAMAGE = 34;
	static int HARD_DAMAGE = 50;
	static int POISON = 5;


	public static int ValueOfEffectWhenTutch(EffectWhenTutch effect) {
		switch (effect) {
		case VERY_SOFT_DAMAGE:
			return VERY_SOFT_DAMAGE;
		case SOFT_DAMAGE :
			return SOFT_DAMAGE;
		case NORMAL_DAMAGE:
			return NORMAL_DAMAGE;
		case HARD_DAMAGE:
			return HARD_DAMAGE;
		case POISON:
			return POISON;
		case BURN:
			return BURN;
		case BURN_EFFECT:
			return BURN_EFFECT;
		default:
			return 0;
		}
	}

	public static Effect EffectOfEffectWhenTutch(EffectWhenTutch effect) {
		return null;

	}
}
