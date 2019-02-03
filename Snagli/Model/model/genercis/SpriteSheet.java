package model.genercis;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	/*------------------------------------------------------------------------------*/
	/*                                 ATTRIBUTES                                   */
	/*------------------------------------------------------------------------------*/
	private BufferedImage spriteSheet = null;
	private String PATH = "Images/";
	/*------------------------------------------------------------------------------*/
	/*                            CONSTRUCTORS/DESTRUCTORS                          */
	/*------------------------------------------------------------------------------*/
	public SpriteSheet (String name) {
		this.PATH += name + ".png";
	}
	/*------------------------------------------------------------------------------*/
	/*                                 METHODS                                     */
	/*------------------------------------------------------------------------------*/
	private void loadImage() {
		try {
			//System.out.println(PATH);
			setSpriteSheet(ImageIO.read(new File(PATH)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void setSpriteSheet(BufferedImage image) {
		spriteSheet = image;
	}
	
	public BufferedImage get() {
		if(spriteSheet == null) {
			loadImage();
		}
		return spriteSheet;
	}
	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/

	

	


}
