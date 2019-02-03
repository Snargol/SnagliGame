package model.genercis;

import model.enumeration.BlockEffect;

public class Effect {
	/*------------------------------------------------------------------------------*/
	/*                                 ATTRIBUTES                                   */
	/*------------------------------------------------------------------------------*/
	private long startTime;
	private int timeGap;
	private int timeLifeMax;
	private int timeLife;
	private int lifeAlteration;
	private int totalLifeAlteration;
	private int actualAlteration;
	private int maxLifeAlteration;
	private BlockEffect blockEffect;
	private boolean isAlive = true;
	/*------------------------------------------------------------------------------*/
	/*                            CONSTRUCTORS/DESTRUCTORS                          */
	/*------------------------------------------------------------------------------*/

	public Effect(BlockEffect blockEffect, int timeLifeMax, int timeGap, int lifeAlteration) {
		setBlockEffect(blockEffect);
		setTimeLife(timeLifeMax);
		setTimeGap(timeGap);
		setLifeAlteration(lifeAlteration);
	}
	
	public Effect(int timeGap, int totalLifeAlteration, int nbAlteration, BlockEffect blockEffect) {
		setTimeGap(timeGap);
		setMaxLifeAlteration(totalLifeAlteration);
		setLifeAlteration(totalLifeAlteration / nbAlteration);
		setBlockEffect(blockEffect);
	}
	
	/*------------------------------------------------------------------------------*/
	/*                                 METHODS                                     */
	/*------------------------------------------------------------------------------*/
	
	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/
	
	public int getTimeGap() {
		return timeGap;
	}
	public void setTimeGap(int timeGap) {
		this.timeGap = timeGap;
	}
	public int getTimeLifeMax() {
		return timeLifeMax;
	}
	public void setTimeLifeMax(int timeLifeMax) {
		this.timeLifeMax = timeLifeMax;
	}
	public int getTimeLife() {
		return timeLife;
	}
	public void setTimeLife(int timeLife) {
		this.timeLife = timeLife;
	}
	public int getLifeAlteration() {
		return lifeAlteration;
	}
	public void setLifeAlteration(int lifeAlteration) {
		this.lifeAlteration = lifeAlteration;
	}
	public int getTotalLifeAlteration() {
		return totalLifeAlteration;
	}
	public void setTotalLifeAlteration(int totalLifeAlteration) {
		this.totalLifeAlteration = totalLifeAlteration;
	}
	public BlockEffect getBlockEffect() {
		return blockEffect;
	}
	public void setBlockEffect(BlockEffect blockEffect) {
		this.blockEffect = blockEffect;
	}
	public boolean isAlive(long time) {
		if (totalLifeAlteration >= maxLifeAlteration) {
			setAlive(false);
		}
//		if (timeLifeMax != 0) {
//			setTimeLife((int) (time - startTime));
//			if (timeLife >= timeLifeMax) {
//				setAlive(false);
//			}
//		}
//		if (actualAlteration >= totalLifeAlteration) {
//			setAlive(false);
//		}
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public int getActualAlteration() {
		return actualAlteration;
	}

	public void setActualAlteration(int actualAlteration) {
		this.actualAlteration = actualAlteration;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public int getMaxLifeAlteration() {
		return maxLifeAlteration;
	}

	public void setMaxLifeAlteration(int maxLifeAlteration) {
		this.maxLifeAlteration = maxLifeAlteration;
	}
	
	
	
	
	
}
