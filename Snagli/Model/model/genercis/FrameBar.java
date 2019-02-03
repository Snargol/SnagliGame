package model.genercis;

public class FrameBar extends Frame{
	
	/*------------------------------------------------------------------------------*/
	/*                                 PARAMETRES                                   */
	/*------------------------------------------------------------------------------*/
	private static String PATH = "Images/Bar/";
	private int widthMax;
	/*------------------------------------------------------------------------------*/
	/*                                 CONSTRUCTEURS                                */
	/*------------------------------------------------------------------------------*/
	public FrameBar(int width, int height, String name) {
		super(width, height, name, getPATH());
		this.widthMax = width;
	}
	/*------------------------------------------------------------------------------*/
	/*                                 METHODES                                     */
	/*------------------------------------------------------------------------------*/
	
	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/
	

	public void setWidthByPercentage(int percentage) {
		//this.width = 
		size.setWidth(Math.round((widthMax * percentage)/100));
	}

	public void setHeight(int height) {
		size.setHeight(height);
	}
	
	private static String getPATH() {
		return PATH;
	}
	
	
}
