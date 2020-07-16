package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class Rocket extends Item{
	private Location movingLocation;
	private static boolean hasBuild = false;
	private static ArrayList<Item> rockets = new ArrayList<>();
	
	/**
	 * Constructor for RocketPad
	 */
	
	public Rocket(Location newLocation) {
		super("Rocket",'R');
		this.movingLocation = newLocation;
	}
	
	/**
	 * Add rocketParts Item to the list which allow RocketPad to check if Actor has those rocket parts to build a rocket
	 * 
	 * @param myRocketBody the parts of rocket (Item) which are required to travel to Mars
	 */	
	public static void AddRocketParts(Item myRocketBody) {
		rockets.add(myRocketBody);
	}
	
	/**
	 *return the lists of rocket part 
	 * 
	 * 
	 * @return list of rokcet parts
	 */
	public List<Item> getRocketParts() {
		return Collections.unmodifiableList(new ArrayList<Item>(rockets));
	}
	
	/**
	 *Setter to set the rocket has built
	 */
	public void HasBuilt() {
		Rocket.hasBuild = true;
		
	}
	
	/**
	 * Getter to get if the rocket has built
	 * @return boolean if the rocket has built
	 */
	
	public boolean checkHasBuilt() {
		return Rocket.hasBuild;
	}
	
	/**
     * Returns a collection of the Actions containing FlyToOtherMap that the otherActor can do on Rocket
     *
	 * @return A collection of Actions
	 */
	
	
	@Override
	public Actions getAllowableActions() {
		super.allowableActions.clear();
		super.allowableActions.add(new FlyToOtherMap(movingLocation));
		return allowableActions;
	}

}
