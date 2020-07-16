package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;


public class Door extends Ground {
	
	private static ArrayList<Item> keys = new ArrayList<>();
	
	/**
	 * Constructor to create Door object
	 * 
	 * Door is represented by '+' in gameMap
	 */
	public Door() {
		super('+');
	}

	/**
	 * Check if the Actor has key item in its inventory
	 * 
	 * @param actor the Actor acting
	 * @return true if the Actor has key in its inventory and false otherwise
	 */
	public boolean hasKey(Actor actor) {
		for (Item item : actor.getInventory()) {
			if (keys.contains(item)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Add key object into keys list
	 * 
	 * This allows Door to have a list of keys that can open it
	 * 
	 * @param key the Item key
	 */
	public static void addKey(Item key) {
		keys.add(key);
	}
	
	/**
	 * Make the terrain with Door not passable
	 * 
	 * @param actor the Actor acting
	 * @return false
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	/**
	 * Make Door blocks thrown objects
	 * 
	 * @return true
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
	
	/**
	 * Returns OpenDoorAction if the Actor has key in its inventory
	 * which allows Actor to open the Door
	 * 
	 * Else it will call super class allowableAction which returns an empty Action list
	 *
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Door from the Actor
	 * @return Action to be performed
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		if (hasKey(actor)) {
			return new Actions(new OpenDoorAction(direction, location, keys));
		}
		return super.allowableActions(actor, location, direction);

	}
	
	

}

