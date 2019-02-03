package model.bar;

import model.enumeration.GrabableEffect;
import model.enumeration.Power;
import model.genercis.Frame;
import model.genercis.Position;
import model.genercis.SnagliModel;

public class InventoryBar {

	/*------------------------------------------------------------------------------*/
	/*                                 ATTRIBUTES                                   */
	/*------------------------------------------------------------------------------*/
	private Position position;
	private Frame backGroundFrame;
	private Size size;
	private Size minimumPowerSize = new Size(20, 20);
	private Size maximumPowerSize = new Size(45, 45);
	private Size currentPowerSize = new Size(45, 45);
	private Size actualPowerFrameSize  = new Size(70, 70);
	private Size actualPowerSize  = new Size(60, 60);
	private PowerObject actualPowerObject;
	private Position actualPowerObjectPosition;
	private int numberOfPower;
	private int spacement = 5;
	private SnagliModel model;

	/*------------------------------------------------------------------------------*/
	/*                            CONSTRUCTORS/DESTRUCTORS                          */
	/*------------------------------------------------------------------------------*/
	public InventoryBar(Position position, SnagliModel model) {
		setPosition(position);
		setSize(new Size(450, 70));
		setNumberOfPower(0);
		setModel(model);
		setActualPowerObjectPosition(new Position(position.getX() + 5, position.getY() + 5));
		setBackGroundFrame(new Frame(getSize().getWidth(), getSize().getHeight(), "InventoryBar", "Images/Bar/"));
		//actualPowerObject = new PowerObject(Power.MISSILE, new Frame(getActualPowerSize().getWidth(), getActualPowerSize().getHeight(), "MISSILEOctopus", "Images/Bar/PowerFrames/"), new Position(calculCoordonates(0)));
	}

	/*------------------------------------------------------------------------------*/
	/*                                 METHODS                                     */
	/*------------------------------------------------------------------------------*/

	//calcul the position with the index of the element
	public Position calculCoordonates(int index) {
		return new Position(getPosition().getX() + getActualPowerFrameSize().getWidth() + (getSpacement() * (index+1)) + (calculAdaptSize().getWidth() * (index+1)),getPosition().getY() + 10);
	}

	//set the size of the elements with the total numbers of items
	private void calculSize() {
		if (calculCumulSize() >= calculSizeInventory()) {
			setCurrentPowerSize(calculAdaptSize());
		}
		else if (getCurrentPowerSize().getWidth() != getMaximumPowerSize().getWidth() || getCurrentPowerSize().getHeight() != getMaximumPowerSize().getHeight()){
			System.out.println("setMaximalSize (InventoryBar)");
			setCurrentPowerSize(new Size(getMaximumPowerSize().getWidth(), getMaximumPowerSize().getHeight()));
		}
	}

	//adapt the size of the elements
	private void adaptSize(Size size) {
		for (PowerObject powerObject : getModel().getPowerObject()) {
			powerObject.setSize(new Size(size.getWidth(), size.getHeight()));
		}
	}

	//calcul and return the good size
	private Size calculAdaptSize() {
		int width;
		int height;

		if (calculCumulSize() >= calculSizeInventory()) {
			height = (int) (5 * (((double) getSize().getHeight())/6));
			width = (calculSizeInventory() - calculSpacements()) / getModel().getPowerObject().size();
		}
		else {
			height = getMaximumPowerSize().getHeight();
			width = getMaximumPowerSize().getWidth();
		}
		return new Size(width, height);
	}

	//calcul and return the good size with the numbers of elements
	public Size calculAdaptSize(int number) {
		int width;
		int height;

		height = (int) (5 * (((double) getSize().getHeight())/6));
		width = (calculSizeInventory() - calculSpacements()) / ((number > 0)? number : 1);

		return new Size(width, height);
	}

	//calcul the total spacements between each object
	private int calculSpacements() {
		return (getModel().getPowerObject().size() - 1)*getSpacement();
	}

	//calcul the size of the inventory without the actualPower
	public int calculSizeInventory() {
		return (getSize().getWidth() - getActualPowerFrameSize().getWidth() - getSpacement());
	}

	//calcul the total size
	private int calculCumulSize() {
		int size = 0;
		if (getModel().getPowerObject().size() > 0) {
			for (PowerObject powerObject : getModel().getPowerObject()) {
				size += powerObject.getSize().getWidth();
			}
			//add spacement
			size += calculSpacements();
		}
		return size;
	}

	//return a boolean that say if the inventory contains the search object
	public boolean containsGrabableEffect(GrabableEffect grabableEffect) {
		for (PowerObject powerObject : getModel().getPowerObject()) {
			if (powerObject.getGrabableEffect() == grabableEffect) {
				return true;
			}
		}
		return false;
	}
	
	//adapt the coordonates of elements
	public void adaptCoordonates() {
		for (int i = 0; i < model.getPowerObject().size(); i++) {
			model.getPowerObject().get(i).setPosition(calculCoordonates(i-1));
		}
	}



	/*------------------------------------------------------------------------------*/
	/*                                 GET/SET                                      */
	/*------------------------------------------------------------------------------*/

	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	public int getNumberOfPower() {
		return numberOfPower;
	}
	public void setNumberOfPower(int numberOfPower) {
		this.numberOfPower = numberOfPower;
	}
	public SnagliModel getModel() {
		return model;
	}
	public void setModel(SnagliModel model) {
		this.model = model;
	}
	public int getSpacement() {
		return spacement;
	}
	public void setSpacement(int spacement) {
		this.spacement = spacement;
	}
	public Size getMinimumPowerSize() {
		return minimumPowerSize;
	}
	public void setMinimumPowerSize(Size minimumPowerSize) {
		this.minimumPowerSize = minimumPowerSize;
	}
	public Size getMaximumPowerSize() {
		return maximumPowerSize;
	}
	public void setMaximumPowerSize(Size maximumPowerSize) {
		this.maximumPowerSize = maximumPowerSize;
	}
	public Size getActualPowerSize() {
		return actualPowerSize;
	}
	public void setActualPowerSize(Size actualPowerSize) {
		this.actualPowerSize = actualPowerSize;
	}
	public Size getCurrentPowerSize() {
		//calculSize();
		return currentPowerSize;
	}
	public void setCurrentPowerSize(Size currentPowerSize) {
		adaptSize(currentPowerSize);
		this.currentPowerSize = currentPowerSize;
	}

	public Frame getBackGroundFrame() {
		return backGroundFrame;
	}

	public void setBackGroundFrame(Frame backGroundFrame) {
		this.backGroundFrame = backGroundFrame;
	}

	public PowerObject getActualPowerObject() {
		return actualPowerObject;
	}

	
	public Position getActualPowerObjectPosition() {
		return actualPowerObjectPosition;
	}

	public void setActualPowerObjectPosition(Position actualPowerObjectPosition) {
		this.actualPowerObjectPosition = actualPowerObjectPosition;
	}

	public void setActualPowerObject(GrabableEffect effect) {
		this.actualPowerObject = new PowerObject(effect, new Frame(getSize().getWidth(), getSize().getHeight(), getModel().getNameByGrabableEffect(effect),"Images/Bar/PowerFrames/"), 1,  getActualPowerObjectPosition(), getActualPowerSize());
	}

	public Size getActualPowerFrameSize() {
		return actualPowerFrameSize;
	}

	public void setActualPowerFrameSize(Size actualPowerFrameSize) {
		this.actualPowerFrameSize = actualPowerFrameSize;
	}

	

}
