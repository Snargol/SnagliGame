package model.bar;

import model.genercis.FrameBar;
import model.genercis.Position;

public class LifeBar implements IGaugeBar {

	/*------------------------------------------------------------------------------*/
	/*                                 PARAMETRES                                   */
	/*------------------------------------------------------------------------------*/
	private FrameBar GreenLifebar = new FrameBar(550, 20, "GreenLifeBar");
	private FrameBar OrangeLifebar = new FrameBar(550, 20, "OrangeLifeBar");
	private FrameBar RedLifebar = new FrameBar(550, 20, "RedLifeBar");
	private FrameBar ActualGauge;
	private FrameBar OutLine = new FrameBar(550,20, "OutLineLifeBar");
	private Position position;
	
	/*------------------------------------------------------------------------------*/
	/*                                 CONSTRUCTEURS                                */
	/*------------------------------------------------------------------------------*/
	
	public LifeBar(Position position) {
		this.ActualGauge = GreenLifebar;
		this.position = position;
	}
	
	/*------------------------------------------------------------------------------*/
	/*                                 METHODES                                     */
	/*------------------------------------------------------------------------------*/
	@Override
	public void adaptBar(int life, int lifeMax) {
		chooseColorBar( life,  lifeMax);
		adaptSizeBar( life,  lifeMax);
	}
	
	@Override
	public void chooseColorBar(int life, int lifeMax) {
		float percentage = (float) life/lifeMax;
		
		if (percentage <= 0.33) {
			this.ActualGauge = RedLifebar;
		}
		else if (percentage <= 0.66) {
			this.ActualGauge = OrangeLifebar;
		}
		else {
			this.ActualGauge = GreenLifebar;
		}
	}
	@Override
	public void adaptSizeBar(int life, int lifeMax) {
		float percentage = (float) life/lifeMax;
		
		ActualGauge.setWidthByPercentage((int) (percentage*100));
	}
	
	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/
	@Override
	public FrameBar getActualFrame() {
		return ActualGauge;
	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public FrameBar getOutLine() {
		return OutLine;
	}
	
	
}
