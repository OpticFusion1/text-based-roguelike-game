package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class FillWaterAction extends Action{
	private WaterPistol item;
	
	/**
	 * Constructor for FillWaterAction
	 * @param item WaterPistol item to be filled with water
	 */
	public FillWaterAction(WaterPistol item) {
		this.item = item;
	}

	/**
	 * Fill water to WaterPistol by setting waterLevel to maxWaterLevel of WaterPistol
	 * if waterLevel is already max, WaterLevel cannot be filled
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		if (item.isEmpty()){
			item.setWaterLevel(item.getMaxWaterLevel());
			return "Water pistol is filled with water now.";
		}
		return "Water level of water pistol is already full. Unable to fill with water anymore. ";
	}

	/**
	 * Description of FillWaterAction tobe displayed on menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " fills up water pistol.";
	}

	@Override
	public String hotKey() {
		return "";
	}



}
