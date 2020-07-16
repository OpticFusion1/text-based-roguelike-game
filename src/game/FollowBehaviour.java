package game;

import edu.monash.fit2099.engine.*;


public class FollowBehaviour implements ActionFactory {

	private Actor target;

	/**
	 * Constructor to create FollowBehaviour
	 * 
	 * @param subject the Actor to be followed by the Actor who has FollowBehaviour
	 */
	public FollowBehaviour(Actor subject) {
		this.target = subject;
	}

	/**
	 * Return MoveActorAction which allows the Actor who has this behaviour to follow target Actor
	 * if the Actor can move closer to target Actor
	 * 
	 * Do nothing if cannot detect target Actor location
	 * 
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		try {
			Location here = map.locationOf(actor);
			Location there = map.locationOf(target);
			int currentDistance = distance(here, there);
			for (Exit exit : here.getExits()) {
				Location destination = exit.getDestination();
				if (destination.canActorEnter(actor)) {
					int newDistance = distance(destination, there);
					if (newDistance < currentDistance) {
						return new MoveActorAction(destination, exit.getName());
					}
				}
			}
		}
		catch (NullPointerException e) {};

		return null;
	}

	// Manhattan distance.
	protected int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}
}