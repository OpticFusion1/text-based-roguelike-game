package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class RocketPad extends Item{
	protected Rocket rocket;
	/**
	 * Constructor for RocketPad
	 */
	public RocketPad(Location currentlocation) {
		super("RocketPad",'^');
		this.rocket = new Rocket(currentlocation);
	}
	/**
     * Returns a collection of the Actions containing BuildRocket that the otherActor can do on RocketPad
     * if the rocket haven't build by checking on (checkHasBuilt)
     * Else it will return allowableActions in superclass Ground
     *
     * @param otherActor the Actor that might be performing BuildRocket
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return A collection of Actions
	 */
	
	public Actions getAllowableActions() {
		super.allowableActions.clear();
		if (!rocket.checkHasBuilt()) {
			super.allowableActions.add(new BuildRocket(rocket));
		}
		return allowableActions;
	}

	
    
	
	
}
