package model.factory;

import java.awt.color.ICC_ColorSpace;
import java.nio.channels.Pipe;

import model.enumeration.BlockEffect;
import model.enumeration.GrabableEffect;
import model.enumeration.Permeability;
import model.enumeration.Power;
import model.genercis.Position;
import model.genercis.SnagliModel;
import model.motionless.BigPics;
import model.motionless.Brick;
import model.motionless.Burger;
import model.motionless.Bush;
import model.motionless.Cloud;
import model.motionless.Coin;
import model.motionless.DarkBlock;
import model.motionless.Dirt;
import model.motionless.Fire;
import model.motionless.FlameLance;
import model.motionless.Flower;
import model.motionless.Glass;
import model.motionless.GoldenApple;
import model.motionless.Grass;
import model.motionless.GreyBrick;
import model.motionless.Heart;
import model.motionless.Hill;
import model.motionless.Ladder;
import model.motionless.LargePavingHerbs;
import model.motionless.LargePavingHole;
import model.motionless.Lava;
import model.motionless.LeftDarkStair;
import model.motionless.LittlePics;
import model.motionless.MirrorPaving;
import model.motionless.MotionLess;
import model.motionless.Octopus;
import model.motionless.Pillar;
import model.motionless.Propulsor;
import model.motionless.RightDarkStair;
import model.motionless.Rock;
import model.motionless.SimpleBlock;
import model.motionless.SmallPavingHerbsHole;
import model.motionless.SmallPavingHole;
import model.motionless.Special;
import model.motionless.Stone;
import model.motionless.Void;
import model.motionless.Wood;
import model.motionless.WoodDrum;

public abstract class BlockFactory {
	static SnagliModel model;

	private static GrabableEffect selectEffect() {
		int alea = (int) (Math.random() * ((15 - 0)) + 1);
		if (alea <= 3) {
			return GrabableEffect.GIVE_FIRE;
		}
		if (alea <= 6) {
			return GrabableEffect.GIVE_POISON;
		}
		if (alea <= 9) {
			return GrabableEffect.GIVE_ICE;
		}
		if (alea <= 15) {
			return GrabableEffect.GIVE_MONEY;
		}
		else 
			return GrabableEffect.GIVE_INVULNERABILITY;
	}
	
	public static void setModel(SnagliModel model1){
		model = model1;
	}
	
	public static SnagliModel getModel() {
		return model;
	}

	public static MotionLess createSpecial(Position position) {
		Special block = new Special(selectEffect(),position);
		return block;
	}
	
	public static MotionLess createGrass(Position position) {
		Grass block = new Grass(position);
		return block;
	}
	
	public static MotionLess createDirt(Position position) {
		Dirt block = new Dirt(position);
		return block;
	}
	
	
	public static MotionLess createGreyBrick(Position position) {
		GreyBrick block = new GreyBrick(position);
		return block;
	}
	
	public static MotionLess createLava(Position position) {
		Lava block = new Lava(position);
		return block;
	}
	
	public static MotionLess createFlower(Position position) {
		Flower block = new Flower(position);
		return block;
	}
	
	public static MotionLess createCloud(Position position) {
		Cloud block = new Cloud(position);
		return block;
	}
	
	public static MotionLess createLadder(Position position) {
		Ladder block = new Ladder(position);
		return block;
	}
	
	public static MotionLess createCoin(Position position) {
		Coin block = new Coin(position);
		return block;
	}
	
	public static MotionLess createBurger(Position position) {
		Burger block = new Burger(position);
		return block;
	}
	
	public static MotionLess createGoldenApple(Position position) {
		GoldenApple block = new GoldenApple(position);
		return block;
	}
	
	public static MotionLess createFireOctopus(Position position) {
		Octopus block = new Octopus("FireOctopus", GrabableEffect.GIVE_FIRE,position);
		return block;
	}
	
	public static MotionLess createIceOctopus(Position position) {
		Octopus block = new Octopus("IceOctopus", GrabableEffect.GIVE_ICE,position);
		return block;
	}
		
	public static MotionLess createPoisonOctopus(Position position) {
		Octopus block = new Octopus("PoisonOctopus", GrabableEffect.GIVE_POISON,position);
		return block;
	}
	
	public static MotionLess createMissilenOctopus(Position position) {
		Octopus block = new Octopus("MissileOctopus", GrabableEffect.GIVE_MISSILE,position);
		return block;
	}
	
	public static MotionLess createVoid(Position position) {
		Void block= new Void(position);
		return block;
	}
	public static MotionLess createBrick(Position position) {
		Brick block = new Brick(position);
		return block;
	}
	public static MotionLess createStone(Position position) {
		Stone block = new Stone(position);
		return block;
	}
	
	public static MotionLess createPillar(Position position) {
		Pillar block = new Pillar(position);
		return block;
	}
	
	public static MotionLess createLittlePics(Position position) {
		LittlePics block = new LittlePics(position);
		return block;
	}
	
	public static MotionLess createBigPics(Position position) {
		BigPics block = new BigPics(position);
		return block;
	}
	
	public static MotionLess createHill(Position position) {
		Hill block = new Hill(position);
		return block;
	}
	
	public static MotionLess createHeart(Position position) {
		Heart block = new Heart(position);
		return block;
	}
	
	public static MotionLess createPropulsor(Position position) {
		Propulsor block = new Propulsor(position);
		return block;
	}
	
	public static MotionLess createRock(Position position) {
		Rock block = new Rock(position);
		return block;
	}
	
	public static MotionLess createBush(Position position) {
		Bush block = new Bush(position);
		return block;
	}
	
	public static MotionLess createWoodDrum(Position position) {
		WoodDrum block = new WoodDrum(position);
		return block;
	}
	
	public static MotionLess createGlass(Position position) {
		Glass block = new Glass(position);
		return block;
	}
	
	public static MotionLess createSimpleBlock(Position position) {
		SimpleBlock block = new SimpleBlock(position);
		return block;
	}
	
	public static MotionLess createSmallPavingHerbsHole(Position position) {
		SmallPavingHerbsHole block = new SmallPavingHerbsHole(position);
		return block;
	}
	
	public static MotionLess createSmallPavingHole(Position position) {
		SmallPavingHole block = new SmallPavingHole(position);
		return block;
	}
	
	public static MotionLess createLargePavingHerbs(Position position) {
		LargePavingHerbs block = new LargePavingHerbs(position);
		return block;
	}
	
	public static MotionLess createLargePavingHole(Position position) {
		LargePavingHole block = new LargePavingHole(position);
		return block;
	}
	
	public static MotionLess createMirrorPaving(Position position) {
		MirrorPaving block = new MirrorPaving(position);
		return block;
	}
	
	public static MotionLess createWood(Position position) {
		Wood block = new Wood(position);
		return block;
	}
	
	public static MotionLess createDarkBlock(Position position) {
		DarkBlock block = new DarkBlock(position);
		return block;
	}
	
	public static MotionLess createLeftDarkStair(Position position) {
		LeftDarkStair block = new LeftDarkStair(position);
		return block;
	}
	
	public static MotionLess createRightDarkStair(Position position) {
		RightDarkStair block = new RightDarkStair(position);
		return block;
	}
	
	public static MotionLess createFire(Position position) {
		Fire block = new Fire(position);
		return block;
	}
	
	public static MotionLess createFlameLance(Position position) {
		FlameLance block = new FlameLance(position);
		getModel().addTemporalEntity(TemporalEntityFactory.createFlame(position, block));
		return block;
	}
	
	public static MotionLess getFromSymbol(char symbol, Position position) {
		
		switch(symbol) {
		case '?' : 
			return createSpecial(position);
		case '$' : 
			return createCoin(position);
		case 'A' : 
			return createGoldenApple(position);
		case 'B' : 
			return createBurger(position);
		case 'C' : 
			return createCloud(position);
		case 'D' : 
			return createDirt(position);
		case 'E' : 
			return createPropulsor(position);
		case 'F' :
			return createRock(position);
		case 'G' : 
			return createGrass(position);
		case 'H' : 
			return createLadder(position);
		case 'I' : 
			return null;
		case 'J' :
			return createPillar(position);
		case 'K' :
			return createLittlePics(position);
		case 'L' : 
			return createLava(position);	
		case 'M' : 
			return createPoisonOctopus(position);
		case 'N' : 
			return createFireOctopus(position);
		case 'O' : 
			return createIceOctopus(position);
		case 'Q' :
			return createBigPics(position);
		case 'R' : 
			return createGreyBrick(position);
		case 'S' : 
			return createStone(position);
		case 'T' : 
			return createBrick(position);
		case 'U' :
			return createHill(position);
		case 'V' : 
			return createFlower(position);
		case 'W' :
			return createHeart(position);
		case 'X' :
			return createWoodDrum(position);
		case 'Y' : 
			return createBush(position);
		case 'Z' : 
			return createGlass(position);
		case 'a' :
			return createSimpleBlock(position);
		case 'b' : 
			return createSmallPavingHerbsHole(position);
		case 'c' : 
			return createSmallPavingHole(position);
		case 'd' : 
			return createLargePavingHerbs(position);
		case 'e' : 
			return createLargePavingHole(position);	
		case 'f' : 
			return createMirrorPaving(position);
		case 'g' : 
			return createWood(position);
		case 'h' : 
			return createDarkBlock(position);
		case 'i' : 
			return createLeftDarkStair(position);
		case 'j' : 
			return createRightDarkStair(position);
		case 'k' : 
			return createFire(position);
		case 'l' : 
			return createFlameLance(position);
		case 'm' : 
			return createMirrorPaving(position);
		case 'n':
			return createMissilenOctopus(position);
		default : 
			return createVoid(position);
		}
	}
	
	public static MotionLess getFromGrabableEffect(GrabableEffect effect, Position position) {
		switch (effect) {
		case GIVE_FIRE:
			return createFireOctopus(position);
		case GIVE_ICE : 
			return createIceOctopus(position);
		case GIVE_MISSILE :
			return createBurger(position);
		case GIVE_POISON :
			return createPoisonOctopus(position);
		case GIVE_HEALTH :
			return createHeart(position);
		case GIVE_MONEY : 
			return createCoin(position);
		case GIVE_REGENERATION :
			return createGoldenApple(position);
		default :
			return null;
		}	
	}
}
