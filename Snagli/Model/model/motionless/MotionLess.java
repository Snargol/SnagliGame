package model.motionless;

import java.util.ArrayList;

import Interfaces.IApplyEntityEffects;
import model.enumeration.ActionSprite;
import model.enumeration.BlockEffect;
import model.enumeration.EffectWhenTutch;
import model.enumeration.EntityEffect;
import model.enumeration.GrabableEffect;
import model.enumeration.Opacity;
import model.enumeration.Permeability;
import model.enumeration.Resistance;
import model.enumeration.Solidity;
import model.genercis.Element;
import model.genercis.HitBoxe;
import model.genercis.Position;

public class MotionLess extends Element implements IApplyEntityEffects{
	private BlockEffect effect;
	private Permeability permeability;
	private Solidity solidity;
	private Position position;
	private String name;
	private boolean needFloor = false;
	private GrabableEffect grabableEffect = GrabableEffect.NONE;
	private Resistance resistanceOfTheEffect;

	//classic constructor
	public MotionLess(int xImagePos, int yImagePos, int nbSprites, 
			String name, Opacity opacity, Permeability permeability, Solidity solidity, Position position,boolean needFloor,
			EffectWhenTutch effectWhenTutch, GrabableEffect grabableEffect , Resistance resistanceOfTheEffect, 
			int widthFrame, int heightFrame, int widthDisplay, int heightDisplay) {
		super( xImagePos, yImagePos, nbSprites, opacity, new HitBoxe(50,50), name, widthFrame, heightFrame, widthDisplay, heightDisplay, position, effectWhenTutch );
		setPermeability(permeability);
		setSolidity(solidity);
		setName(name);
		setNeedFloor(needFloor);
		setGrabableEffect(grabableEffect);
		setResistanceOfTheEffect(resistanceOfTheEffect);
		// TODO Auto-generated constructor stub
	}
	
	//specific name constructor
	public MotionLess(int xImagePos, int yImagePos, int nbSprites, 
			String file, String name, Opacity opacity, Permeability permeability, Solidity solidity, Position position,boolean needFloor,
			EffectWhenTutch effectWhenTutch, GrabableEffect grabableEffect , Resistance resistanceOfTheEffect, 
			int widthFrame, int heightFrame, int widthDisplay, int heightDisplay) {
		super( xImagePos, yImagePos, nbSprites, opacity, new HitBoxe(50,50),file, name, widthFrame, heightFrame, widthDisplay, heightDisplay, position, effectWhenTutch);
		setPermeability(permeability);
		setSolidity(solidity);
		setName(name);
		setNeedFloor(needFloor);
		setGrabableEffect(grabableEffect);
		setResistanceOfTheEffect(resistanceOfTheEffect);
		// TODO Auto-generated constructor stub
	}

	
	 //int xImagePos, int yImagePos, int nbSprites, Opacity opacity, HitBoxe hitboxe,String file,String name, int widthFrame, int heightFrame, int widthDisplay, int heightDisplay, Position position
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setSolidity(Solidity solidity) {
		this.solidity = solidity;
	}

	public Permeability getPermeability() {
		return permeability;
	}

	public void setPermeability(Permeability permeability) {
		this.permeability = permeability;
	}

	public BlockEffect getEffect() {
		return effect;
	}

	public void setEffect(BlockEffect effect) {
		this.effect = effect;
	}

	public Solidity getSolidity() {
		return solidity;
	}

	@Override
	public void applyEntityEffect(ArrayList<EntityEffect> effect) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDamagesEntity(EntityEffect effect) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isResistantEntityEffect(EntityEffect effect) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ActionSprite getActionSpriteByEntityEffect(EntityEffect effect) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void applySpecialEntityEffect(EntityEffect effect) {
		// TODO Auto-generated method stub
		
	}

	public boolean isNeedFloor() {
		return needFloor;
	}

	public void setNeedFloor(boolean needFloor) {
		this.needFloor = needFloor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GrabableEffect getGrabableEffect() {
		return grabableEffect;
	}

	public void setGrabableEffect(GrabableEffect grabableEffect) {
		this.grabableEffect = grabableEffect;
	}

	public Resistance getResistanceOfTheEffect() {
		return resistanceOfTheEffect;
	}

	public void setResistanceOfTheEffect(Resistance resistanceOfTheEffect) {
		this.resistanceOfTheEffect = resistanceOfTheEffect;
	}
	
	
	
}
