package game;

import edu.monash.fit2099.engine.WeaponItem;

public class WaterPistol extends WeaponItem{
	protected int waterLevel;
	final int maxWaterLevel = 1;
	final int emptyWaterLevel = 0;


	/**
	 * Constructor for WaterPistol weapon
	 * Display with > in map
	 */
	public WaterPistol() {
		super("Water Pistol", '>', 25, "shoots");
		this.setWaterLevel(this.emptyWaterLevel);
	}

	/**
	 * Check if water level of water pistol is empty
	 * 
	 * @return true if water level is 0 and false otherwise
	 */
	public boolean isEmpty() {
		return waterLevel == this.emptyWaterLevel;
	}

	/**
	 * Getter for emptyWaterLevel attribute
	 * @return value of emptyWaterLevel
	 */
	public int getEmptyWaterLevel() {
		return emptyWaterLevel;
	}

	/**
	 * Getter for waterLevel
	 * 
	 * @return value of waterLevel field
	 */
	public int getWaterLevel() {
		return waterLevel;
	}

	/**
	 * Setter for waterLevel
	 * 
	 * @param waterLevel value of water level to be set
	 */
	public void setWaterLevel(int waterLevel) {
		this.waterLevel = waterLevel;
	}

	/**
	 * Getter for maxWaterLevel attribute
	 * @return value of maxWaterLevel
	 */
	public int getMaxWaterLevel() {
		return maxWaterLevel;
	}
}
