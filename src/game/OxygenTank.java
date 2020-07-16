package game;


import edu.monash.fit2099.engine.Item;

public class OxygenTank extends Item{
	private int points;
	final int maxPoint = 10;
	final int emptyPoint = 0;

	/**
	 * Constructor for OxygenTank
	 * Represented by symbol 'T'
	 * has max point of 10
	 */
	public OxygenTank() {
		super("Oxygen Tank", 'T');
		this.setPoints(maxPoint);
	}
	
	/**
	 * To check if OxygenTank is empty
	 * @return true if points of OxygenTank is 0, false otherwise
	 */
	public boolean isEmpty() {
		return getPoints() == emptyPoint;
	}

	/**
	 * Getter for maxPoint
	 * @return value of maxPoint attribute
	 */
	public int getMaxPoint() {
		return maxPoint;
	}

	/**
	 * Getter for emptyPoint
	 * @return value of emptyPoint attribute
	 */
	public int getEmptyPoint() {
		return emptyPoint;
	}

	/**
	 * Getter for points of OxygenTank
	 * @return points value of OxygenTank
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Setter of points of OxygenTank
	 * @param points value of points to be set to OxygenTank points attribute
	 */
	public void setPoints(int points) {
		this.points = points;
	}



}
