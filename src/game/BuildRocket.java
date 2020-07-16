package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class BuildRocket extends Action{
	Rocket rocket;
	final int completed = 2;
	
	/**
	 * Constructor of FlyToMats action
	 * 
	 * @param actor the Actor acting
	 */
	public BuildRocket(Rocket rocket){
		this.rocket = rocket;
	}
	
	/**
	 * build rocket Actor has enough item to build rocket if rocket has not built
	 * 
	 * @return a message to indicate end of the game
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		List <Item>rocketList = rocket.getRocketParts();
		Location current = map.locationOf(actor);
		if (hasCompleteRocket(actor)){
		List <Item> targetList = actor.getInventory();
		for (Item obj: targetList) {
			for(Item rocket:rocketList) {
				if (obj.equals(rocket)){
					actor.removeItemFromInventory(obj);
					}
				}
			}
		rocket.HasBuilt();
		map.addItem(rocket, current.x(),current.y());
		return actor + " successfully built a rocket";
		}
		return "Insufficient rocket parts! "+actor+" failed to build a rocket";
	}
	
	/**
	 * Check if Actor has both rocketBody and rocketEngine Item in its inventory
	 * 
	 * @param actor the acting Actor
	 * @return true if the Actor has both rocketBody and rocketEngine, false otherwise
	 */
	
	public boolean hasCompleteRocket(Actor actor) {
		List <Item>rocketList = rocket.getRocketParts();
		List <Item> targetList = actor.getInventory();
		int count ;
		count = 0;
		for (Item item : targetList) {
			if (rocketList.contains(item)) {
				count++;
			}
		}
		if (count == completed) {
			return true;
		}
		return false;
	}
	/**
	 * Return a string allows Actor to choose to fly to Mars
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " builds a rocket";
	}

	@Override
	public String hotKey() {
		return "";
	}


}

