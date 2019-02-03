package model.entities;

import model.enumeration.EffectWhenTutch;
import model.enumeration.Opacity;
import model.genercis.HitBoxe;
import model.genercis.Position;
import model.motionless.MotionLess;

public class Flame extends TemporalEntity {
	private MotionLess block;

	public Flame(Position position, MotionLess block) {
		super(3,4, 0, 1, 4, Opacity.PENETRABLE, new HitBoxe(50, 150), "Flame", 50, 150, 50,150, new Position(position.getX()*50, position.getY()*50 - 100), EffectWhenTutch.BURN_EFFECT);

		setBlock(block);
		setAppearanceSprites(createSpecificSpriteArray(0, 0, 4, 50, 150));
		setConstantSprites(createSpecificSpriteArray(0,1,4,50,150));
		setDisappearanceSprites(createSpecificSpriteArray(0,2,4,50,150));

		setSpriteArray(getAppearanceSprites());
		// TODO Auto-generated constructor stub
	}

	public boolean isAlive(long time) {
		if (!block.getIsAlive()) {
			setAlive(false);
			return false;
		}
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

	public MotionLess getBlock() {
		return block;
	}

	public void setBlock(MotionLess block) {
		this.block = block;
	}


}
