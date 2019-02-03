package model.genercis;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.bar.Size;

public class Frame {
	/*------------------------------------------------------------------------------*/
	/*                                 PARAMETRES                                   */
	/*------------------------------------------------------------------------------*/
	protected BufferedImage image;
	protected String PATH = new String();
	protected Size size = new Size(0, 0);
	protected Position position;
	
	/*------------------------------------------------------------------------------*/
	/*                                 CONSTRUCTEURS                                */
	/*------------------------------------------------------------------------------*/

	public Frame(int width, int height, String name, String PATH) {
		this.size.setWidth(width);
		this.size.setHeight(height);
		this.PATH = PATH;
		this.PATH += name + ".png";
		//System.out.println(this.PATH);
		loadImage();
	}
	
	/*------------------------------------------------------------------------------*/
	/*                                 METHODES                                     */
	/*------------------------------------------------------------------------------*/
	
	private void loadImage() {
		try {
			setImage(ImageIO.read(new File(PATH)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/
	
	public BufferedImage getImage() {
		return this.image;
	}
	
	protected void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public void setImage(String path) {
		this.PATH = path;
		loadImage();
	}

	public int getWidth() {
		return size.getWidth();
	}

	public int getHeight() {
		// TODO Auto-generated method stub
		return size.getHeight();
	}

	public void setWidth(int width) {
		this.size.setWidth(width);
	}

	public void setHeight(int height) {
		this.size.setHeight(height);
	}
	
	
}
