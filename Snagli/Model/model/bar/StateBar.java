package model.bar;

import model.enumeration.StateType;
import model.genercis.Frame;
import model.genercis.Position;

public class StateBar extends Bar{
	/*------------------------------------------------------------------------------*/
	/*                                 ATTRIBUTES                                   */
	/*------------------------------------------------------------------------------*/
	private Frame backGround = new Frame(60, 60, "StateGreenBackGround", "Images/Bar/");
	private State fullLife;
	private State midLife;
	private State noLife;
	private State dead;
	private State actualState;
	
	/*------------------------------------------------------------------------------*/
	/*                            CONSTRUCTORS/DESTRUCTORS                          */
	/*------------------------------------------------------------------------------*/
	public StateBar(Position position) { 
		super(position);
		
		fullLife = new State("FullLife", 11, position);
		midLife = new State("MidLife", 21, position);
		noLife = new State("NoLife", 11, position);
		dead = new State("Dead", 8, position);
		actualState = fullLife;
	}

	
	/*------------------------------------------------------------------------------*/
	/*                                 METHODS                                     */
	/*------------------------------------------------------------------------------*/
	
	public void selectState(StateType state) {
		switch (state) {
		case FULL_LIFE :
			actualState = fullLife;
			break;
		case MID_LIFE :
			actualState = midLife;
			break;
		case NO_LIFE :
			actualState = noLife;
			break;
		case DEAD :
			actualState = dead;
			break;
		default :
			actualState = dead;
		}
	}
	
	public void selectRedBackGround() {
		backGround.setImage("Images/Bar/StateRedBackGround.png");
	}
	
	public void selectGreenBackGround() {
		backGround.setImage("Images/Bar/StateGreenBackGround.png");
	}
	
	public void selectOrangeBackGround() {
		backGround.setImage("Images/Bar/StateOrangeBackGround.png");
	}
	
	
	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/
	public Frame getBackGround() {
		return backGround;
	}

	public void setBackGround(Frame backGround) {
		this.backGround = backGround;
	}

	public State getActualState() {
		return actualState;
	}
	
}
