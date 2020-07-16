package game;


import java.util.List;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;



public class Q extends Enemy {
	private Item rocketBody;
	private Item rocketPlan;
	
	/**
	 * Constructor to create Q
	 * 
	 * Q will have rocketBody Item in its inventory
	 * RocketPad will add a rocketBody Item when Q is created so RocketPad can check if Actor has rocketBody
	 * Q has StandStillBehaviour by default (which allows Q to stay on same place throughout the game
	 * 
	 * @param name
	 * @param player
	 * 
	 * @param rocketPlan
	 */
	public Q (String name, NewPlayer player, Item rocketPlan) {
		super(name, 'Q', 6, 100);
		this.rocketPlan = rocketPlan;
		this.rocketBody = Item.newInventoryItem("RocketBody", 'B');
		addItemToInventory(this.rocketBody);
		Rocket.AddRocketParts(this.rocketBody);
		addBehaviour(new StandStillBehaviour());	
	}
	
	
    /**
     * Returns a collection of the Actions which can be performed by other Actor on Q:
     * Q will have TalkAction allows other Actor to talk to Q
     * If other Actor has rocketPlan Item in its inventory:
     * Q will have GivePlansAction which allows other Actor to exchange rocketPlans with Q
     * 
     * @param otherActor the Actor that might get actions that can be performed on Q
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return A collection of Actions.
	 */
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions otherActorActions = new Actions();
		TalkAction talk = new TalkAction(otherActor,this, rocketPlan);
		otherActorActions.add(talk);
		List <Item> targetList = otherActor.getInventory();
		for (Item myItem: targetList) {
			if (myItem.equals(rocketPlan)) {
				GivePlansAction give = new GivePlansAction(otherActor, this, rocketPlan);
				otherActorActions.add(give);
			}}
		return otherActorActions;
	}
}