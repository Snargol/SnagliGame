package model.entities;

import java.util.ArrayList;

import model.enumeration.EffectWhenTutch;
import model.enumeration.Opacity;
import model.genercis.Element;
import model.genercis.HitBoxe;
import model.genercis.Position;
import model.genercis.Sprite;
import model.motionless.MotionLess;

public class TemporalEntity extends Element{
	int timeAlive;
	int timeDead;
	long timeSave = 0;
	boolean spriteSheetFullyScanned = false;
	ArrayList<Sprite> appearanceSprites = new ArrayList<>();
	ArrayList<Sprite> disappearanceSprites = new ArrayList<>();
	ArrayList<Sprite> constantSprites = new ArrayList<>();


	public TemporalEntity(int timeAlive, int timeDead, int xImagePos, int yImagePos, int nbSprites, Opacity opacity, HitBoxe hitboxe,
			String name, int widthFrame, int heightFrame, int widthDisplay, int heightDisplay, Position position, EffectWhenTutch effectWhenTutch) {
		super(xImagePos, yImagePos, nbSprites, opacity, hitboxe, "Entity\\elements\\", name, widthFrame, heightFrame, widthDisplay,
				heightDisplay, position, effectWhenTutch);
		setTimeAlive(timeAlive);
		setTimeDead(timeDead);
	}


	public int getTimeAlive() {
		return timeAlive;
	}

	public void setTimeAlive(int timeAlive) {
		this.timeAlive = timeAlive;
	}

	public int getTimeDead() {
		return timeDead;
	}

	public void setTimeDead(int timeDead) {
		this.timeDead = timeDead;
	}

	public long getTimeSave() {
		return timeSave;
	}


	public void setTimeSave(long timeSave) {
		this.timeSave = timeSave;
	}

	public boolean isAlive(long time) {
		if (getIsAlive()) {
			if (getTimeSave() + getTimeAlive() <= time) {
				setTimeSave(time);
				setAlive(false);
			}
		}
		else {
			if (getTimeSave() + getTimeDead() <= time) {
				setTimeSave(time);
				setAlive(true);
			}
		}
		return getIsAlive();
	}

	public void checkSpriteArrayToDraw(long time) {
		if (!isAlive(time/1000)) {
			return;
		}

		double timeOfLife = (double) ((((double) time)/1000) - getTimeSave());
		int nmbOfFrames = constantSprites.size() * 3;
		//		double timeByframe = ((double) getTimeAlive()) / nmbOfFrames;
		double timeByframe = 0.1;


		if (timeOfLife >= ((constantSprites.size() * timeByframe)) && timeOfLife <= (getTimeAlive() - (4 * timeByframe)) && !getSpriteArray().equals(constantSprites)) {
			setSpriteArray(constantSprites);
		}
		else if (timeOfLife <= ((appearanceSprites.size() * timeByframe)) && !getSpriteArray().equals(appearanceSprites)) {
			setSpriteArray(appearanceSprites);
		}
		else if (timeOfLife >= (getTimeAlive() - (disappearanceSprites.size() * timeByframe)) && !getSpriteArray().equals(disappearanceSprites)) {
			setSpriteArray(disappearanceSprites);
		}
	}

	public boolean isSpriteSheetFullyScanned() {
		return spriteSheetFullyScanned;
	}


	public void setSpriteSheetFullyScanned(boolean spriteSheetFullyScanned) {
		this.spriteSheetFullyScanned = spriteSheetFullyScanned;
	}


	public ArrayList<Sprite> getAppearanceSprites() {
		return appearanceSprites;
	}


	protected void setAppearanceSprites(ArrayList<Sprite> appearanceSprites) {
		this.appearanceSprites = appearanceSprites;
	}


	public ArrayList<Sprite> getDisappearanceSprites() {
		return disappearanceSprites;
	}


	protected void setDisappearanceSprites(ArrayList<Sprite> disappearanceSprites) {
		this.disappearanceSprites = disappearanceSprites;
	}


	public ArrayList<Sprite> getConstantSprites() {
		return constantSprites;
	}


	protected void setConstantSprites(ArrayList<Sprite> constantSprites) {
		this.constantSprites = constantSprites;
	}






}
