package game;

import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class TalkAction extends Action{
	private Actor subject;
	private String result;
	private Item rocketPlan;
	

	/**
	 * Constructor to create TalkAction
	 * 
	 * @param actor the Actor that can perform this Action
	 * @param subject the Actor that contains this Action which allow other Actor to perform this Action
	 * @param rocketPlan the rocketPlan Item
	 */
	public TalkAction(Actor actor, Actor subject, Item rocketPlan) {
		this.subject = subject;
		this.rocketPlan = rocketPlan;
	}
	
	/**
	 * Talk to Actor which returns different strings depends on Actor's inventory (check if Actor has rocketPlan)
	 * Move to a random position in the map after talking to Actor
	 * 
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a string to show Actors' words
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		boolean flag = false;
		List <Item> targetList = actor.getInventory();
		for (Item myItem: targetList) {
			if (myItem.equals(rocketPlan)) {
				flag = true;
			}
		}
		result = actor + ": ";
		if (flag) {
			result += "Hand them over, I don't have all day!";}
		else {
			result +=  "I can give you somthing that will help, but I'm going to need the plans";}
		Random r = new Random();
		int x = r.nextInt(22);
		int y = r.nextInt(10);
		Location myLocation = map.at(x, y);
		while (!myLocation.canActorEnter(subject)){
			Random j = new Random();
			 x = j.nextInt(22);
			 y = j.nextInt(10);
			 myLocation = map.at(x, y);
		}
		map.moveActor(subject, myLocation);
		return result;
		}

	/**
	 * String to allow player choose to perform this action from menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " Talks to " + subject;
	}

	@Override
	public String hotKey() {
		return "";
	}

}
