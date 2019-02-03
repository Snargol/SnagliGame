package model.factory;

import model.entities.Flame;
import model.entities.TemporalEntity;
import model.enumeration.Opacity;
import model.genercis.HitBoxe;
import model.genercis.Position;
import model.motionless.MotionLess;

public abstract class TemporalEntityFactory {

	public static TemporalEntity createFlame(Position position, MotionLess block) {
		TemporalEntity flame = new Flame(position, block);
		return flame;
	}

}
	