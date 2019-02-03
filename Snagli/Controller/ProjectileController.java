import model.genercis.SnagliModel;
import model.projectile.ProjectileBounces;

public class ProjectileController {
	private static SnagliModel model;
	
	private void bouncesMove(ProjectileBounces entity) {
		if (!entity.isAlive()) {
			return;
		}
		
		if (Checks.checkActiveFloor(entity, direction))
	}
	
	
	
	
	public static void setModel(SnagliModel model1) {
		model = model1;
	}
}
