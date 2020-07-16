package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.demo.Floor;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;


public class OpenDoorAction extends Action {

	private String direction;
	private Location doorLocation;
	private ArrayList<Item> keys;
	
	/**
	 * Constructor to create OpenDoorAction
	 * 
	 * @param direction the direction of Door from Actor
	 * @param doorLocation location of Door from Actor
	 * @param keys a list of keys Item which can open the Door
	 */
	public OpenDoorAction(String direction, Location doorLocation, ArrayList<Item> keys) {
		this.direction = direction;
		this.doorLocation = doorLocation;
		this.keys = keys;
	}
	
	/**
	 * Open the Door by replacing unpassable Door with passable Floor 
	 * Remove one key Item from Actor's inventory
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a string to indicate the door is opened.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		
		List <Item> targetList = actor.getInventory();
		for (Item myItem: targetList) {
			if (keys.contains(myItem)) {
				actor.removeItemFromInventory(myItem);
				break;
			}
		}
		
		map.add(new Floor(), doorLocation);
		return "The door is opened by " + actor;
	}

	/**
	 * String to show on menu to allow user to choose to open Door
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " opens the door on " + direction;
	}

	@Override
	public String hotKey() {
		return "";
	}
}