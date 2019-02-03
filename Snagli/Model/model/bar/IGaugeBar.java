package model.bar;

import model.genercis.FrameBar;

public interface IGaugeBar extends IBar{
	
	/*------------------------------------------------------------------------------*/
	/*                                 METHODES                                     */
	/*------------------------------------------------------------------------------*/
	public void adaptBar(int actual, int Max);
	void chooseColorBar(int actual, int Max);
	void adaptSizeBar(int actual, int Max);
	
	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/
	public FrameBar getActualFrame();
	public FrameBar getOutLine();
}
