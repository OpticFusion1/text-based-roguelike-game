package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class Water extends Ground {

	/**
	 * Constructor for Water
	 * Represented by symbol 'W' on map
	 */
	public Water() {
		super('W');
	}
	
	/**
	 * Make the terrain with Water not passable so Actor cannot walk on it
	 * 
	 * @param actor the Actor acting
	 * @return false
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}


	/**
	 * Return FillWaterAction which allows Actor to fill WaterPistol with water if Actor has WaterPistol
	 * Else it will call super class allowableAction which returns an empty Action list
	 * 	 
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Door from the Actor
	 * @return Action that can be performed by Actor on Water
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		for (Item item : actor.getInventory()) {
			if (item instanceof WaterPistol) {
				return new Actions(new FillWaterAction((WaterPistol)item));
				}
			}
		return super.allowableActions(actor, location, direction);
	}
	
	

}
