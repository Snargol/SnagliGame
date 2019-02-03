package model.genercis;

import java.util.ArrayList;

import javax.swing.BoundedRangeModel;

import model.motionless.Barrier;
import model.motionless.Limit;
import model.motionless.MotionLess;

public class Map {
	/*------------------------------------------------------------------------------*/
	/*                                 ATTRIBUTES                                   */
	/*------------------------------------------------------------------------------*/
	private static int WIDTH;    
	private static int HEIGHT;
	private static int TAILLE_CASE = 50;
	private MotionLess[][] map;
	/*------------------------------------------------------------------------------*/
	/*                            CONSTRUCTORS/DESTRUCTORS                          */
	/*------------------------------------------------------------------------------*/
	public Map(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		map = new MotionLess[WIDTH][HEIGHT];
	}
	/*------------------------------------------------------------------------------*/
	/*                                 METHODS                                     */
	/*------------------------------------------------------------------------------*/
	public void setOnTheMap(MotionLess block, Position position) {
		map[position.getX()][position.getY()] = block;
	}

	public MotionLess getOnTheMap(Position position) {
		if(position.getX() < 0 || position.getX() >= WIDTH || position.getY() < 0) {
			return new Barrier(position);
		}
		else if (position.getY() >= HEIGHT) {
			return new Limit(position); 
		}
		return map[position.getX()][position.getY()];
	}

	public MotionLess getOnTheMap(int x, int y) {
		if(x < 0 || x >= WIDTH || y < 0) {
			return new Barrier(new Position(x,y));
		}
		else if (y >= HEIGHT) {
			return new Limit(new Position(x,y)); 
		}
		return map[x][y];
	}


	public ArrayList<MotionLess> getOnTheMap(Position position, int rayon) {
		ArrayList<MotionLess> boxes = new ArrayList<>();

		if(position.getX() < 0 || position.getX() >= WIDTH*50 || position.getY() < 0) {
			boxes.add(new Barrier(new Position(position.getX(),position.getY())));
			return boxes;
		}
		else if (position.getY() >= HEIGHT*50) {
			boxes.add(new Limit(new Position(position.getX(),position.getY())));
			return boxes; 
		}

		//the rayon must be a small value
		if (rayon >= 3) {
			rayon = 3;
		}
		if (rayon < 0) {
			rayon = 0;
		}
		int increment = 0;
		int x = position.getX() - rayon -1;
		int y = position.getY() - rayon;
		for (int i = 0; i < (rayon * 2 + 1)*(rayon * 2 + 1); i++ ) {
			//if x is equal to the last boxe in the line, then increment is set to 0
			increment = (x == (position.getX() + rayon))? 0 : 1;
			if (increment == 0) {
				increment = 1;
				//x is set to the start of the line
				x = position.getX() - rayon;
			}
			else {
				x += increment;
				increment = 0;
			}
			y += increment;
			boxes.add(getOnTheMap(x,y));
		}

		return boxes;

	}

	public MotionLess getBoxeByDisplayPosition(int x, int y) {
		return getBoxeByDisplayPosition(new Position(x,y));
	}

	public MotionLess getBoxeByDisplayPosition(Position position) {

		return getOnTheMap((int) Math.floor((double) position.getX() / 50), (int) Math.floor((double) position.getY() / 50));

	}


	public ArrayList<MotionLess> getOnTheMap(Position displayPosition, HitBoxe hitBoxe) {

		ArrayList<MotionLess> boxes = new ArrayList<>();

		if(displayPosition.getX() < 0 || displayPosition.getX() >= WIDTH*50 || displayPosition.getY() < 0) {
			boxes.add(new Barrier(new Position(displayPosition.getX(),displayPosition.getY())));
			return boxes;
		}
		else if (displayPosition.getY() >= HEIGHT*50) {
			boxes.add(new Limit(new Position(displayPosition.getX(),displayPosition.getY())));
			return boxes; 
		}

		int x = new Integer((int) Math.floor((double) displayPosition.getX() / 50)).intValue();//-hitBoxe.getWidth()/50;
		int y = new Integer((int) Math.floor((double) displayPosition.getY() / 50)).intValue();//-hitBoxe.getHeight()/50;

		//		int maxX = 	((int) Math.floor((double) displayPosition.getX() / 50) + (hitBoxe.getWidth() / 50 + ((hitBoxe.getWidth() / 50 == 1)? 0 : (hitBoxe.getWidth() / 50) - 1 )));
		//				
		////				((double) displayPosition.getX() / 50 - (int) Math.floor((double) displayPosition.getX() / 50) > 0)?
		////					((int) Math.floor((double) displayPosition.getX() / 50) + (hitBoxe.getWidth() / 50)) :
		////					((int) Math.floor((double) displayPosition.getX() / 50));
		//				
		//		int maxY = 	(((int) Math.floor((double) displayPosition.getY() / 50) +  ((hitBoxe.getHeight() / 50 == 1)? 0 : (hitBoxe.getHeight() / 50) - 1)));
		//				
		////				((double) displayPosition.getY() / 50 - (int) Math.floor((double) displayPosition.getY() / 50) > 0)? 
		////					(((int) Math.floor((double) displayPosition.getY() / 50)+ ((hitBoxe.getHeight() / 50 == 1)? 0 : (hitBoxe.getHeight() / 50) - 1))) : 
		////					(((int) Math.floor((double) displayPosition.getY() / 50) +  ((hitBoxe.getHeight() / 50 == 1)? 0 : (hitBoxe.getHeight() / 50) - 1)));
		//			

		double tempX = (double) displayPosition.getX() / 50;
		double tempY = (double) displayPosition.getY() / 50;

		int ix = (int) Math.floor(tempX); // integer part X
		double dx = tempX - ix; // decimal part X
		int iy = (int) Math.floor(tempY); // integer part Y
		double dy = tempY - iy; // decimal part Y


		int maxX = (ix + ((hitBoxe.getWidth() / TAILLE_CASE > 1)? hitBoxe.getWidth() / TAILLE_CASE - 1 : 0 ));
		maxX += (dx > 0)? 1 : 0;
		int maxY = (iy + ((hitBoxe.getHeight() / TAILLE_CASE > 1)? hitBoxe.getHeight() / TAILLE_CASE - 1 : 0 ));
		maxY += (dy > 0)? 1 : 0;

		//		int maxX = ((int) Math.floor((double) displayPosition.getX() / 50) + (hitBoxe.getWidth() / 50 + ((hitBoxe.getWidth() / 50 == 1)? 0 : (hitBoxe.getWidth() / 50) - 1 )));
		//		maxX = (((double) displayPosition.getX() / 50 ) - (int) Math.floor((double) displayPosition.getX() / 50)) > 0? maxX ++ : maxX;
		//		int maxY = 	(((int) Math.floor((double) displayPosition.getY() / 50) +  ((hitBoxe.getHeight() / 50 == 1)? 0 : (hitBoxe.getHeight() / 50) - 1)));
		//		maxY = ((double) displayPosition.getY() / 50 - (int) Math.floor((double) displayPosition.getY() / 50) > 0)? maxY ++ : maxY;

		do {
			if (x == maxX) {
				boxes.add(getOnTheMap(x,y));
				x = (int) Math.floor((double) displayPosition.getX() / 50);
				y ++;

			}
			else {
				boxes.add(getOnTheMap(x,y));
				x++;
			}
			if (y == maxY && x == maxX) {
				boxes.add(getOnTheMap(x,y));
			}
		}while (x < maxX || y < maxY);

		//		for (MotionLess motionLess : boxes) {
		//			System.out.println(motionLess.getPosition().getX() + " - " + motionLess.getPosition().getY());
		//		}
		//		System.out.println();

		return boxes;

	}



	public MotionLess getTopBoxe(Position position) {
		return getOnTheMap(position.getX(), position.getY()-1);
	}

	public MotionLess getBelowBoxe(Position position) {
		return getOnTheMap(position.getX(), (position.getY())+1);
	}

	//returns the lower boxes, in contact with the entity
	public ArrayList<MotionLess> getDownBoxes(Position displayPosition, HitBoxe hitboxe) {
		ArrayList<MotionLess> boxes = new ArrayList<>();

		double tempX = (double) displayPosition.getX() / 50;
		double tempY = (double) displayPosition.getY() / 50;

		int ix = (int) Math.floor(tempX); // integer part X
		double dx = tempX - ix; // decimal part X
		int iy = (int) Math.floor(tempY); // integer part Y
		double dy = tempY - iy; // decimal part Y

		if (dx > 0) {
			for (int i = ix ; i <= ix + hitboxe.getWidth()/TAILLE_CASE;i++) {
				if (dy > 0) {
					boxes.add(getOnTheMap(i, (int) iy + hitboxe.getHeight()/50));
				}
				else {
					boxes.add(getOnTheMap(i, (int) iy + hitboxe.getHeight()/50 - 1));
				}
			}
		}
		else {
			for (int i = ix ; i <= ix + hitboxe.getWidth()/TAILLE_CASE - 1;i++) {
				if (dy > 0) {
					boxes.add(getOnTheMap(i, (int) iy + hitboxe.getHeight()/50));
				}
				else {
					boxes.add(getOnTheMap(i, (int) iy + hitboxe.getHeight()/50 - 1));
				}
			}
		}

		return boxes;
	}

	//returns the upper boxes, in contact with the entity
	public ArrayList<MotionLess> getUpBoxes(Position displayPosition, HitBoxe hitboxe) {
		ArrayList<MotionLess> boxes = new ArrayList<>();

		double tempX = (double) displayPosition.getX() / 50;
		double tempY = (double) displayPosition.getY() / 50;

		int ix = (int) Math.floor(tempX); // integer part X
		double dx = tempX - ix; // decimal part X
		int iy = (int) Math.floor(tempY); // integer part Y
		//double dy = tempY - iy; // decimal part Y

		if (dx > 0) {
			for (int i = ix ; i <= ix + hitboxe.getWidth()/TAILLE_CASE;i++) {
				boxes.add(getOnTheMap(i, iy));
			}
		}
		else {
			for (int i = ix ; i <= ix + hitboxe.getWidth()/TAILLE_CASE - 1;i++) {
				boxes.add(getOnTheMap(i, iy));
			}
		}

		return boxes;
	}

	//returns the upper boxes, above the entity
//	public ArrayList<MotionLess> getTopBoxes(Position displayPosition, HitBoxe hitboxe) {
//		ArrayList<MotionLess> boxes = new ArrayList<>();
//
//		double tempX = (double) displayPosition.getX() / 50;
//		double tempY = (double) displayPosition.getY() / 50;
//
//		int ix = (int) Math.floor(tempX); // integer part X
//		double dx = tempX - ix; // decimal part X
//		int iy = (int) Math.floor(tempY); // integer part Y
//		double dy = tempY - iy; // decimal part Y
//
//		if (dx > 0) {
//			for (int i = ix ; i <= ix + hitboxe.getWidth()/TAILLE_CASE;i++) {
//				boxes.add(getOnTheMap(i, iy - 1));
//			}
//		}
//		else {
//			boxes.add(getOnTheMap(ix,  iy - 1));
//		}
//
//		return boxes;
//	}


	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/


	public MotionLess[][] getMap() {
		return this.map;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public int getWidth() {
		return WIDTH;
	}
}
