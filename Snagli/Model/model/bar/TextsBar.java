package model.bar;

import java.util.ArrayList;

import model.genercis.Frame;
import model.genercis.Position;
import model.genercis.SnagliModel;
import model.other.Text;

public class TextsBar extends Bar{


	/*------------------------------------------------------------------------------*/
	/*                                 ATTRIBUTES                                   */
	/*------------------------------------------------------------------------------*/
	private Text score;
	private Text scoreData;
	private Text coins;
	private Text coinsData;
	private Text time;
	private Text timeData;
	private ArrayList<Text> texts = new ArrayList<>();
	private Frame background = new Frame(150, 60, "DatasBackGround", "Images/Bar/");
	private SnagliModel model;
	/*------------------------------------------------------------------------------*/
	/*                            CONSTRUCTORS/DESTRUCTORS                          */
	/*------------------------------------------------------------------------------*/
	public TextsBar(Position position, SnagliModel model) {
		super(position);
		this.model = model;
		this.score = new Text("Score : ", new Position(position.getX() + 5, position.getY() + 15));
		this.scoreData = new Text("NN", new Position(position.getX() + 70, position.getY() + 15));
		this.coins = new Text("Coins : ", new Position(position.getX() + 5, position.getY() + 35));
		this.coinsData = new Text(""+model.getPlayer().getMonnaie(), new Position(position.getX() + 70, position.getY() + 35));
		this.time = new Text("Time : ", new Position(position.getX() + 5, position.getY() + 55));
		this.timeData = new Text(""+model.getTimer().getTimeDisplay(), new Position(position.getX() + 70, position.getY() + 55));
		fillTexts();
	}
	/*------------------------------------------------------------------------------*/
	/*                                 METHODS                                     */
	/*------------------------------------------------------------------------------*/
	
	private void fillTexts() {
		texts.add(score);
		texts.add(scoreData);
		texts.add(coins);
		texts.add(coinsData);
		texts.add(time);
		texts.add(timeData);
	}
	
	private void updatedatas() {
		timeData.setText(""+model.getTimer().getTimeDisplay());
		coinsData.setText(""+model.getPlayer().getMonnaie());
	}
	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/

	public ArrayList<Text> getTexts() {
		updatedatas();
		return texts;
	}

	public void setTexts(ArrayList<Text> texts) {
		this.texts = texts;
	}

	public Frame getBackground() {
		return background;
	}

	public void setBackground(Frame background) {
		this.background = background;
	}
	
	
}
