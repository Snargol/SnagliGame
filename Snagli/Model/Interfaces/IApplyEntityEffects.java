package Interfaces;

import java.util.ArrayList;

import model.enumeration.ActionSprite;
import model.enumeration.EntityEffect;

public interface IApplyEntityEffects {
	
	// apply EntityEffect to the element
	public void applyEntityEffect(ArrayList<EntityEffect> effect);
	
	//get The damages corresponding to the effect
	public int getDamagesEntity(EntityEffect effect);
	
	// test is the element is resistant to the effect
	public boolean isResistantEntityEffect(EntityEffect effect);
	
	// return an action corresponding to the effect
	public ActionSprite getActionSpriteByEntityEffect(EntityEffect effect);
	
	// apply specials effect (ICE or POISON for exemple)
	public void applySpecialEntityEffect(EntityEffect effect);
	
}
