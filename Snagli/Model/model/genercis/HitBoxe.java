package model.genercis;

public class HitBoxe {
	private int width;
	private int height;
	
	public HitBoxe(int width, int height) {
		setHeight(height);
		setWidth(width);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getNbrBoxes() {
		return (width/50 * height/50);
	}
	
	
	
	
	
}
