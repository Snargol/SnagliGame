package view;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.annotation.Generated;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import model.bar.IGaugeBar;
import model.bar.PowerObject;
import model.entities.TemporalEntity;
import model.enumeration.Direction;
import model.genercis.Element;
import model.genercis.Position;
import model.genercis.SnagliModel;
import model.mobile.Mob;
import model.motionless.MotionLess;
import model.other.MovableText;
import model.other.Text;
import model.particule.Particule;
import model.particule.ParticuleDebris;
import model.particule.ParticuleStatic;
import model.projectile.Projectile;


public class GraphicsBuilder implements Runnable  {
	private final SnagliModel model;

	public GraphicsBuilder(final SnagliModel model) {
		this.model = model;
	}

	public void applyModelToGraphic(final Graphics graphics) {
		if(model.getMap() != null && model.getPlayer() != null && model.getMonsters() != null) {
			clearScreen(graphics);
			drawMonsters(graphics);
			drawTemporalEntities(graphics);
			drawNewPlayer(graphics);
			drawAdaptableMap(graphics);
			drawProjectiles(graphics);
			drawParticules(graphics);
			drawDebris(graphics);
			drawTexts(graphics);
			drawBars(graphics);
			//drawScore(graphics);
		}



	}

	private void clearScreen(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, 2000, 2000);
	}

	
	private void drawProjectiles(Graphics g) {
		if(!model.getProjectiles().isEmpty()) {
			for (Projectile projectile : model.getProjectiles()) {
				if (projectile.getIsAlive() == true) {
					g.drawImage(projectile.getSelectedImage(), projectile.getPosition().getX()+model.getDistanceGap(), projectile.getPosition().getY(), 50, 50, null);
				}
			}
		}
	}

	private void drawDebris(Graphics g) {
		for (ParticuleDebris particuleDebris : model.getParticulesDebris()) {
			if (particuleDebris.isAlive()) {
				Position position = new Position(particuleDebris.getDebris1().getPosition());
				g.drawImage(particuleDebris.getDebris1().getSelectedImage(), position.getX()+model.getDistanceGap(), position.getY(), particuleDebris.getDebris1().getWidthDisplay(), particuleDebris.getDebris1().getHeightDisplay(), null);
				position = new Position(particuleDebris.getDebris2().getPosition());
				g.drawImage(particuleDebris.getDebris2().getSelectedImage(), position.getX()+model.getDistanceGap(), position.getY(), particuleDebris.getDebris2().getWidthDisplay(), particuleDebris.getDebris2().getHeightDisplay(), null);
				position = new Position(particuleDebris.getDebris3().getPosition());
				g.drawImage(particuleDebris.getDebris3().getSelectedImage(), position.getX()+model.getDistanceGap(), position.getY(), particuleDebris.getDebris3().getWidthDisplay(), particuleDebris.getDebris3().getHeightDisplay(), null);

				System.out.println("");
			}

		}
	}

	private void drawParticules(Graphics g) {
		if (model.getParticules() != null) {
			for (Particule particule : model.getParticules()) {
				if(particule.isAlive(model.getTimer().getTimeMilliSeconds()))
					g.drawImage(particule.getSelectedImage(), particule.getPosition().getX()+particule.getPositionX()+model.getDistanceGap(), particule.getPosition().getY()+particule.getPositionY(), particule.getWidthDisplay(), particule.getHeightDisplay(), null);

			}
		}
	}

	private void drawTexts(Graphics g) {
		for (MovableText text : model.getTexts()) {
			g.setFont(new Font("stencil", Font.BOLD, 25));
			g.setColor(text.getColor());
			g.drawString(text.getText(), text.getPosition().getX()+model.getDistanceGap(), text.getPosition().getY());

		}
	}

	private void drawMonsters(Graphics g) {
		if(!model.getMonsters().isEmpty()) {
			for (Mob monster : model.getMonsters()) {
				if (monster.getIsAlive() == true) {
					g.drawImage(monster.getSelectedImage(), monster.getPosition().getX()*50+model.getDistanceGap(), monster.getPosition().getY()*50, 50, 50, null);
				}
			}
		}
	}
	
	private void drawTemporalEntities(Graphics g) {
		if(!model.getTemporalEntities().isEmpty()) {
			for (TemporalEntity entity : model.getTemporalEntities()) {
				if (entity.getIsAlive()) {
					g.drawImage(entity.getSelectedImage(), entity.getPosition().getX()+model.getDistanceGap(), entity.getPosition().getY(), entity.getWidthDisplay(), entity.getHeightDisplay(), null);
				}
			}
		}
	}

	private void drawBars(Graphics g) {
		drawGauge(g);
		drawStateBar(g);
		drawTextsBar(g);
		drawInventoryBar(g);
	}

	private void drawGauge(Graphics g) {
		if(!model.getGaugeBars().isEmpty()) {
			for (IGaugeBar bar : model.getGaugeBars()) {
				g.drawImage(bar.getActualFrame().getImage(), bar.getPosition().getX(), bar.getPosition().getY(), bar.getActualFrame().getWidth(), bar.getActualFrame().getHeight(), null);
				g.drawImage(bar.getOutLine().getImage(), bar.getPosition().getX(), bar.getPosition().getY(), bar.getOutLine().getWidth(), bar.getOutLine().getHeight(), null);
			}
		}
	}

	private void drawStateBar(Graphics g) {
		if (model.getStateBar() != null && model.getStateBar().getActualState() != null) {
			g.drawImage(model.getStateBar().getBackGround().getImage(), model.getStateBar().getPosition().getX(), model.getStateBar().getPosition().getY(), model.getStateBar().getBackGround().getWidth(), model.getStateBar().getBackGround().getHeight(), null);
			g.drawImage(model.getStateBar().getActualState().getSelectedImage(), model.getStateBar().getActualState().getPosition().getX(), model.getStateBar().getActualState().getPosition().getY(), model.getStateBar().getActualState().getWidth(), model.getStateBar().getActualState().getHeight(), null);
		}
	}
	
	private void drawTextsBar(Graphics g) {
		if (model.getTextBar() != null) {
			g.drawImage(model.getTextBar().getBackground().getImage(), model.getTextBar().getPosition().getX(), model.getTextBar().getPosition().getY(), model.getTextBar().getBackground().getWidth(), model.getTextBar().getBackground().getHeight(), null);
			Font font = new Font("Courier", Font.BOLD, 15);
			g.setFont(font);
			g.setColor(Color.BLACK);
			for (Text text : model.getTextBar().getTexts()) {
				g.drawString(text.getText(), text.getPosition().getX(), text.getPosition().getY());
			}

		}
	}
	
	private void drawInventoryBar(Graphics g) {
		//draw he background
		g.drawImage(model.getInventoryBar().getBackGroundFrame().getImage(), model.getInventoryBar().getPosition().getX(), model.getInventoryBar().getPosition().getY(), model.getInventoryBar().getSize().getWidth(), model.getInventoryBar().getSize().getHeight(), null);
		
		//draw the rectangles
		g.drawRect( model.getInventoryBar().getPosition().getX()+5, 
					model.getInventoryBar().getPosition().getY()+5,
					model.getInventoryBar().getActualPowerFrameSize().getWidth()-10, 
					model.getInventoryBar().getActualPowerFrameSize().getHeight()-10);
		
		g.drawRect( model.getInventoryBar().getPosition().getX()+model.getInventoryBar().getActualPowerFrameSize().getWidth(), 
					model.getInventoryBar().getPosition().getY()+5,
					model.getInventoryBar().calculSizeInventory(), 
					model.getInventoryBar().calculAdaptSize(model.getPowerObject().size()).getHeight());
		
		
		//draw all the powers
		g.setFont(new Font("arial", Font.BOLD, 15));
		int i = 0;
		
		for (PowerObject powerObject : model.getPowerObject()) {
			i++;
			g.drawImage(powerObject.getFrame().getImage(), powerObject.getPosition().getX(), powerObject.getPosition().getY(), powerObject.getSize().getWidth(), powerObject.getSize().getHeight(), null);
			g.setColor(Color.YELLOW);
			g.drawString(i+"", powerObject.getTextPosition().getX()-(int) (((double) 3/5) * powerObject.getSize().getWidth()), powerObject.getTextPosition().getY());
			
			g.setColor(Color.GREEN);
			g.drawString(powerObject.getNumber()+"", powerObject.getTextPosition().getX(), powerObject.getTextPosition().getY());

		}
		//draw actual power
		if (model.getInventoryBar().getActualPowerObject() != null)
			g.drawImage(model.getInventoryBar().getActualPowerObject().getFrame().getImage(), model.getInventoryBar().getActualPowerObjectPosition().getX(), model.getInventoryBar().getActualPowerObjectPosition().getY(), model.getInventoryBar().getActualPowerSize().getWidth(), model.getInventoryBar().getActualPowerSize().getHeight(), null);
	
	}
	
	private void drawNewPlayer(Graphics g) {
		int coordinate;
		if (model.getDistanceGap() == 0) {
			coordinate = model.getPlayer().getPosition().getX();
		}
//		else if ( model.getDistanceGap() <= -3100) {
//			coordinate = model.getPlayer().getPosition().getX() - 3100;
//		}
		else if (model.getPlayer().getPosition().getX() + 12 * 50 >= model.getMap().getWidth() * 50) {
			coordinate = 1400 - (model.getMap().getWidth() * 50 - model.getPlayer().getPosition().getX()+50);
//			System.out.println(model.getPlayer().getPosition().getX());
//			System.out.println(model.getDistanceGap());
//			System.out.println();
		}
		else {
			coordinate = 750;
		}
		
		
		
		
		if (model.getPlayer().getIsAlive() == true)
			g.drawImage(model.getPlayer().getSelectedImage(), coordinate, model.getPlayer().getPosition().getY(), model.getPlayer().getWidthDisplay(), model.getPlayer().getHeightDisplay(), null);
	}
	
	private void drawAdaptableMap(Graphics g) {
		int x = 0, y = 0;
		
		int rayonXL = 16;
		int rayonY = 16;
		
		int ix = (int) Math.floor((double) model.getPlayer().getPosition().getX() / 50); // integer part X
		int iy = (int) Math.floor((double) model.getPlayer().getPosition().getY() / 50); // integer part 
		
		int decalage = model.getDistanceGapMap();
		
		int coordinate;
		int extendViewStartX;
		int extendViewEndX;
		
		if (ix > rayonXL) {
			extendViewStartX = ix + rayonXL;
		}
		else {
			extendViewStartX = ix + rayonXL*2;
		}
		if (ix > model.getMap().getWidth() - 750) {
			extendViewEndX = ix - rayonXL;
		}
		else {
			extendViewEndX = ix - rayonXL *2;
		}

		for(y = (iy-rayonY > 0)? iy - rayonY:0; y < ((iy+rayonY < model.getMap().getHeight())? iy + rayonY:model.getMap().getHeight()); y++) {
			for(x = (ix-rayonXL >= 0)? ix - rayonXL : 0; x < (( ix + rayonXL >= model.getMap().getWidth())? model.getMap().getWidth() : (ix > rayonXL)? ix + rayonXL: ix + rayonXL*2); x++) {
				if( model.getMap().getOnTheMap(x, y).getIsAlive()) {
					g.drawImage( model.getMap().getOnTheMap(x, y).getSelectedImage(), x*50+decalage, y*50,  model.getMap().getOnTheMap(x, y).getWidthDisplay(),  model.getMap().getOnTheMap(x, y).getHeightDisplay(), null);
				}
			}
		}
	}
	
	


	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
	
}