package Interfaces;

import model.enumeration.ActionSprite;
import model.enumeration.BlockEffect;

public interface IApplyBlockEffects {

	// apply BlockEntity to the entity
	public boolean applyBlockEffect(BlockEffect effect);
	
	//get The damages corresponding to the effect
	public int getDamagesBlock();
	
	// return an action corresponding to the effect
	public ActionSprite getActionSpriteByBlockEffect(BlockEffect effect);

	// test is the entity is resistant to the effect
	boolean isResistantBlockEffect(BlockEffect effect);
	
}
