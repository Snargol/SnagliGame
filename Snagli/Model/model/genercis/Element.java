package model.genercis;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import model.enumeration.ActionSprite;
import model.enumeration.EffectWhenTutch;
import model.enumeration.Opacity;

public abstract class Element {

	
	/*------------------------------------------------------------------------------*/
	/*                                 ATTRIBUTES                                   */
	/*------------------------------------------------------------------------------*/
	private ArrayList<Sprite> spriteArray = new ArrayList<Sprite>();
	private int selectedSpriteValue = 0;
	private Opacity opacity;
	private boolean isAlive = true;
	private ActionSprite action;
	protected SpriteSheet spriteSheet;
	private int widthDisplay;
	private int heightDisplay;
	protected Position position;
	private HitBoxe hitboxe = new HitBoxe(50, 50);
	private EffectWhenTutch   effectWhenTutch;

	/*------------------------------------------------------------------------------*/
	/*                            CONSTRUCTORS/DESTRUCTORS                          */
	/*------------------------------------------------------------------------------*/
	public Element( int xImagePos, int yImagePos, int nbSprites, Opacity opacity ,HitBoxe hitboxe,String name, int widthFrame, int heightFrame, int widthDisplay, int heightDisplay, Position position, EffectWhenTutch effectWhenTutch) {
		setOpacity(opacity);
		setPosition(position);
		setHitboxe(hitboxe);
		this.spriteSheet = new SpriteSheet("SpriteSheet/"+name);
		this.widthDisplay = widthDisplay;
		this.heightDisplay = heightDisplay;
		createSpriteArray(xImagePos, yImagePos, nbSprites, widthFrame, heightFrame);
		setEffectWhenTutch(effectWhenTutch);
		
	}
	
	public Element( int xImagePos, int yImagePos, int nbSprites, Opacity opacity, HitBoxe hitboxe,String file,String name, int widthFrame, int heightFrame, int widthDisplay, int heightDisplay, Position position, EffectWhenTutch effectWhenTutch) {
		setOpacity(opacity);
		setPosition(position);
		setHitboxe(hitboxe);
		this.hitboxe = hitboxe;
		this.spriteSheet = new SpriteSheet(file+name);
		this.widthDisplay = widthDisplay;
		this.heightDisplay = heightDisplay;
		createSpriteArray(xImagePos, yImagePos, nbSprites, widthFrame, heightFrame);
		setEffectWhenTutch(effectWhenTutch);
		
	}
	/*------------------------------------------------------------------------------*/
	/*                                 METHODS                                     */
	/*------------------------------------------------------------------------------*/
	
	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/

	public int getWidthDisplay() {
		return widthDisplay;
	}

	public int getHeightDisplay() {
		return heightDisplay;
	}

	public void setAction(ActionSprite action) {
		this.action = action;
	}

	public ActionSprite getAction() {
		return action;
	}


	public ArrayList<Sprite> getSpriteArray() { // TODO en fonction de l'action
		return this.spriteArray;
	}

	protected void setSpriteArray(ArrayList<Sprite> spriteArray) {
		this.selectedSpriteValue = 0;
		this.spriteArray = spriteArray;
	}

	public int getSpriteArraySize() { // TODO en fonction de l'action
		return this.spriteArray.size();	
	}

	public Opacity getOpacity() { 
		return this.opacity;
	}

	public void setOpacity(Opacity opacity) {
		this.opacity = opacity;
	}

	private Sprite getSelectedSprite() {
		return this.spriteArray.get(selectedSpriteValue);
	}

	protected void createSpriteArray( int xPos, int yPos, int nbSprites, int width, int height) {
		ArrayList<Sprite> spriteArray = new ArrayList<Sprite>();
		for(int i = 0; i < nbSprites; i++) {
			spriteArray.add(new Sprite( xPos+i, yPos, this.spriteSheet, width, height));
		}
		this.setSpriteArray(spriteArray);
	}
	
	protected ArrayList<Sprite> createSpecificSpriteArray( int xPos, int yPos, int nbSprites, int width, int height) {
		ArrayList<Sprite> spriteArray = new ArrayList<Sprite>();
		for(int i = 0; i < nbSprites; i++) {
			spriteArray.add(new Sprite( xPos+i, yPos, this.spriteSheet, width, height));
		}
		return spriteArray;
	}

	public BufferedImage getSelectedImage() {
		return getSelectedSprite().getImage();
	}

	public int getSelectedSpriteValue() {
		return this.selectedSpriteValue;
	}

	public void setSelectedSpriteValue(int value) {
		this.selectedSpriteValue = value;

	}

	public boolean getIsAlive() {
		return this.isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	
	public Position getDisplayPosition() {
		return null;
		
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}

	public HitBoxe getHitboxe() {
		return hitboxe;
	}
	public void setHitboxe(HitBoxe hitboxe) {
		this.hitboxe = hitboxe;
	}	
	
	public EffectWhenTutch getEffectWhenTutch() {
		return effectWhenTutch;
	}

	public void setEffectWhenTutch(EffectWhenTutch effectWhenTutch) {
		this.effectWhenTutch = effectWhenTutch;
	}

}

