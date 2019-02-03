package model.genercis;

import java.awt.image.BufferedImage;

public class Sprite {
	/*------------------------------------------------------------------------------*/
	/*                                 ATTRIBUTES                                   */
	/*------------------------------------------------------------------------------*/
	private BufferedImage image;
	/*------------------------------------------------------------------------------*/
	/*                            CONSTRUCTORS/DESTRUCTORS                          */
	/*------------------------------------------------------------------------------*/
	public Sprite( int xPos, int yPos, SpriteSheet spriteSheet, int width, int height) {
		this(xPos, yPos, width, height, spriteSheet);
	}

	public Sprite( int xPos, int yPos, int width, int height, SpriteSheet spriteSheet) {
		this.selectImage(spriteSheet.get(), xPos, yPos, width, height);
	}
	/*------------------------------------------------------------------------------*/
	/*                                 METHODS                                     */
	/*------------------------------------------------------------------------------*/
	private void selectImage(BufferedImage spriteSheet, int xPos, int yPos, int width, int height) {
		BufferedImage image = spriteSheet.getSubimage((xPos * width), (yPos * height), width, height);
		setImage(image);
	}
	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/

	public BufferedImage getImage() {
		return this.image;
	}
	
	private void setImage(BufferedImage image) {
		this.image = image;
	}

}
