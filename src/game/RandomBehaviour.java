package game;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class RandomBehaviour extends Action implements ActionFactory{
	private Actor target;
	private Actor subject;
	/**
	 * Constructor to create RandomBehavior
	 * 
	 * subject the targeted Actor
	 */
	public RandomBehaviour(Actor subject,Actor target) {
		this.subject = subject;
		this.target = target;
	}

	@Override
	public String menuDescription(Actor actor) {
		return "";
	}

	@Override
	/**
	 * Return RandomBehaviour which allows the Actor who performs this Action to randomly move around the map
	 * the Actor will always move randomly until the it is beside the player
	 * 
	 * @return this Action or null otherwise
	 */
	public Action getAction(Actor actor, GameMap map) {
		try {	
			Location here = map.locationOf(actor);
			Location there = map.locationOf(target);
			char hereChar = here.getGround().getDisplayChar();
			char thereChar = there.getGround().getDisplayChar();
			if (hereChar==thereChar) {
				int here_x = here.x();
				int here_y = here.y();
				int there_x = there.x();
				int there_y = there.y();
				if (here_x == there_x && Math.abs(here_y - there_y) ==1) {
					return null;
				}
				else if (here_y == there_y && Math.abs(here_x-there_x)==1)
				{
					return null;
				}
				else
					return this;
			}
			return null;
		}
		catch (NullPointerException e) {};
		return null;	
	}
	
	/**
	 * Move Actor randomly to any parts of the mars
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return menuDescription
	 */

	@Override
	public String execute(Actor actor, GameMap map) {
		Random r = new Random();
		int x = r.nextInt(11);
		int y = r.nextInt(10);
		Location myLocation = map.at(x, y);
		while (!myLocation.canActorEnter(actor)){
			Random j = new Random();
			 x = j.nextInt(11);
			 y = j.nextInt(10);
			 myLocation = map.at(x, y);
		}
		map.moveActor(actor, myLocation);
		return menuDescription(actor);
	}

	@Override
	public String hotKey() {
		return null;
	}

}
