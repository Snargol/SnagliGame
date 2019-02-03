package model.bar;

import java.awt.image.BufferedImage;

import model.genercis.FrameBar;
import model.genercis.Position;

public class CoinBar implements IGaugeBar{

	/*------------------------------------------------------------------------------*/
	/*                                 ATTRIBUTES                                   */
	/*------------------------------------------------------------------------------*/
	private FrameBar 					ActualGauge         = new FrameBar(550,20, "CoinBar");
	private FrameBar 					OutLine 			= new FrameBar(550,20, "OutLineLifeBar");
	private Position 				position;
	/*------------------------------------------------------------------------------*/
	/*                            CONSTRUCTORS/DESTRUCTORS                          */
	/*------------------------------------------------------------------------------*/
	public CoinBar (Position position) {
		this.position = position;
		adaptBar(0, 100);
		
	}
	/*------------------------------------------------------------------------------*/
	/*                                 METHODS                                     */
	/*------------------------------------------------------------------------------*/
	@Override
	public void adaptBar(int actual, int Max) {
		adaptSizeBar(actual, Max);
	}

	@Override
	public void chooseColorBar(int actual, int Max) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adaptSizeBar(int actual, int Max) {
		float percentage = (float) actual/Max;
		
		ActualGauge.setWidthByPercentage((int) (percentage*100));
		
	}
	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/
	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public void setPosition(Position position) {
		// TODO Auto-generated method stub
		this.position = position;
		
	}

	@Override
	public FrameBar getActualFrame() {
		// TODO Auto-generated method stub
		return ActualGauge;
	}

	@Override
	public FrameBar getOutLine() {
		// TODO Auto-generated method stub
		return OutLine;
	}

}
