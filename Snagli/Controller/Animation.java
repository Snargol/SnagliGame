import model.genercis.Position;
import model.genercis.SnagliModel;
import model.motionless.MotionLess;
import model.other.Random;
import model.particule.Particule;
import model.particule.ParticuleDebris;
import model.particule.ParticuleStatic;
import model.projectile.Projectile;

public class Animation {
	/*------------------------------------------------------------------------------*/
	/*                                 ATTRIBUTES                                   */
	/*------------------------------------------------------------------------------*/
	private static SnagliModel model;
	/*------------------------------------------------------------------------------*/
	/*                            CONSTRUCTORS/DESTRUCTORS                          */
	/*------------------------------------------------------------------------------*/

	/*------------------------------------------------------------------------------*/
	/*                                 METHODS                                     */
	/*------------------------------------------------------------------------------*/
	public static void createStarParticule(Position position, int timeLife){
		model.addParticule(new Particule("Stars", Random.alea(2), 4,  new Position(position), timeLife, 50 ,100, 50,100 ,model.getTimer().getTimeMilliSeconds()));
	}
	
	public static void createStarParticule(Position position){
		model.addParticule(new Particule("Stars", Random.alea(2), 4,  new Position(position), 900, 50, 100, 50,100 ,model.getTimer().getTimeMilliSeconds()));
	}
	
	public static void createHealParticule(Position position, int timeLife){
		model.addParticule(new Particule("Heal", 0, 8,  new Position(position), timeLife, 50 ,100, 50,100 ,model.getTimer().getTimeMilliSeconds()));
	}
	
	public static void createHealParticule(Position position){
		model.addParticule(new Particule("Heal", 0, 8,  new Position(position), 1100, 50, 100, 50,100 ,model.getTimer().getTimeMilliSeconds()));
	}
	
	public static void createBlueParticule(Position position){
		model.addParticule(new Particule("ColorParticules" ,0, 12,  new Position(position.getX(), position.getY()), 1200, 100, 100, 200, 200,model.getTimer().getTimeMilliSeconds()));
	}
	
	public static void createOrangeParticule(Position position){
		model.addParticule(new Particule("ColorParticules" ,1, 12,  new Position(position.getX(), position.getY()), 1200, 100, 100, 200, 200,model.getTimer().getTimeMilliSeconds()));
	}
	
	public static void createPurpleParticule(Position position){
		model.addParticule(new Particule("ColorParticules" ,2, 12,  new Position(position.getX(), position.getY()), 1200, 100, 100, 200, 200,model.getTimer().getTimeMilliSeconds()));
	}

	public static void createGreenParticule(Position position){
		model.addParticule(new Particule("ColorParticules" ,3, 12,  new Position(position.getX(), position.getY()), 1200, 100, 100, 200, 200,model.getTimer().getTimeMilliSeconds()));
	}
	
	public static void createBlackParticule(Position position){
		model.addParticule(new Particule("ColorParticules" ,4, 12,  new Position(position.getX(), position.getY()), 1200, 100, 100, 200, 200,model.getTimer().getTimeMilliSeconds()));
	}
	
	public static void createMultiColorParticule(Position position){
		model.addParticule(new Particule("ColorParticules" ,5, 12,  new Position(position.getX(), position.getY()), 1200, 100, 100, 200, 200,model.getTimer().getTimeMilliSeconds()));
	}
	
	
	public static void createFire(Position position){
		int alea = Random.alea(40, 50);
		model.addParticule(new Particule("Fire", Random.alea(2), 8,  new Position(position.getX()+Random.alea(-5, 5), position.getY()+Random.alea(-5, 5)), 800, 50, 50, alea, alea,model.getTimer().getTimeMilliSeconds()));
	}
	
	public static void createSmoke(Position position){
		int alea = Random.alea(20, 30);
		model.addParticule(new Particule("Smoke", Random.alea(2), 8,  new Position(position.getX()+Random.alea(-5, 5), position.getY()+Random.alea(0, 10)), 800, 44, 37, alea, alea,model.getTimer().getTimeMilliSeconds()));
	}
	
	public static void createDownSmoke(Position position){
		model.addParticule(new Particule("Smoke", Random.alea(2), 8,  new Position(position.getX()+Random.alea(-20, 20), position.getY()+50+Random.alea(-5, 5)), 800, 44, 37, 50, 50,model.getTimer().getTimeMilliSeconds()));
	}
	
	public static void createExplosion(Position position){
		model.addParticule(new Particule("Explosion",0 , 30,  new Position(position.getX()-100+Random.alea(-5, 5), position.getY()-150 + Random.alea(-5, 5)), 3000, 150, 150, 300, 300,model.getTimer().getTimeMilliSeconds()));
	}
	
	public static void createLittleSmoke(Position position){
		model.addParticule(new Particule("Smoke", Random.alea(2), 8,  new Position(position.getX()+Random.alea(-10, 30), position.getY()+75+Random.alea(-10, 5)), 800, 44, 37, 25, 25,model.getTimer().getTimeMilliSeconds()));
	}
	
	public static void createBlockParticules(String BlockName, Position position) {
		if (BlockName == "Dirt" || BlockName == "Grass") {
			createDirtParticules(position);
		}
		else if (BlockName == "Brick") {
			createBrickParticules(position);
		}
	}
	 
	private static void createBrickParticules(Position position){
		model.addParticule(new Particule("BrickParticules", Random.alea(3), 5,  new Position(position.getX()*50, (position.getY()*50)-32), 500, 50, 32, 50, 32,model.getTimer().getTimeMilliSeconds()));
	}
	
	private static void createDirtParticules(Position position){
		model.addParticule(new Particule("DirtParticules", Random.alea(3), 5,  new Position(position.getX()*50, (position.getY()*50)-32), 500, 50, 32, 50, 32,model.getTimer().getTimeMilliSeconds()));
	}
	
	public static void createDebrisAnimation(MotionLess boxe) {
		if (!boxe.isNeedFloor())
			model.addParticulesDebris(new ParticuleDebris(adaptDebrisName(boxe.getName()), boxe.getPosition(), model.getMap().getHeight()));
	}
	
	public static void createSmokeTrace(Projectile projectile) {
		int alea = Random.alea(100);
		if (alea >= 95) {
			Animation.createFire(projectile.getPosition());
		}
		else if (alea <= 5) {
			Animation.createSmoke(projectile.getPosition());
		}
	}
	
	
	
	private static String adaptDebrisName(String name) {
		if (name == "Brick")
			return "Debris"+name;
		if (name == "Dirt" || name == "Grass")
			return "DebrisDirt";
		if (name == "GreyBrick")
			return "Debris"+name;
		if (name == "Stone") 
			return "Debris"+name;
		if (name == "DarkBlock" || name ==  "LeftDarkStair" || name == "RightDarkkStair") 
			return "DebrisDarkBlock";
		if (name == "Wood") 
			return "Debris"+name;
		else 
			return "Debris";
	}
	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/
	public static void setModel(SnagliModel model1) {
		model = model1;
	}
}
