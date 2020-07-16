package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Range;


public class ThrowStunPowderBehaviour extends Action implements ActionFactory{
	
	private NewPlayer target;

	/**
	 * Constructor to create ThrowStunPowderBehaviour
	 * 
	 * @param subject the targetted Actor
	 */
	public ThrowStunPowderBehaviour(NewPlayer subject) {
		this.target = subject;		
	}
	
	/**
	 * Return this Action if distance between Actor and target is within 5 squares or null otherwise
	 * Return null if there is object that block Actor from stun powder
	 * Do nothing if target is not detected
	 * 
	 * @return ThrowStunPowderBehaviour or null
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		try {			
			Location here = map.locationOf(actor);
			Location there = map.locationOf(target);
			char hereChar = here.getGround().getDisplayChar();
			char thereChar = there.getGround().getDisplayChar();
			if (hereChar==thereChar) {
				Range xs, ys;
				xs = new Range(Math.min(here.x(), there.x()), Math.abs(here.x() - there.x()) + 1);
				ys = new Range(Math.min(here.y(), there.y()), Math.abs(here.y() - there.y()) + 1);
	
				for (int x : xs) {
					for (int y : ys) {
						if(map.at(x, y).getGround().blocksThrownObjects())
							return null;
					}
				}
				
				if (withinFiveSquare(here,there) == true) {
					return this;
				}
			}
			return null;
		}
		
		catch (NullPointerException e) {};

		return null;		
	}
		
	/**
	 * Throw stun powder on target
	 * If probability is true, stun powder will hit on target, else it will miss the target Actor
	 * If stun powder hits on target, it will has no effect if target is already stunned
	 * Else target will be stunned for 2 turns (Refer to NewPlayer class)
	 * Move one step away from target Actor
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a string to show insulting words
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Random rand = new Random();
		boolean probability = rand.nextBoolean();

		String result = actor + " throws stun powder at " + target + "." + System.lineSeparator();
		
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);

		probability = rand.nextBoolean();		
		
		if (probability == true) {
			result += actor + " misses " + target + "." + System.lineSeparator();
		}
		
		else {
			result += "Stun powder hits " + target + System.lineSeparator();
			if (target.getIsStunned()) {
				result += "Stun powder has no effect on " + target + System.lineSeparator();
				return menuDescription(actor) + System.lineSeparator()+ result;
			}
			else {
				target.setIsStunned(true);
				result += target + " stunned for two turns." + System.lineSeparator();
			}
		}
		
		int currentX = distanceX(here, there);
		int currentY = distanceY(here, there);
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(target)) {
				int newX = distanceX(destination, there);
				int newY = distanceY(destination, there);
				if (newX > currentX  || newY > currentY) {
					map.moveActor(actor, destination);
					result += actor + " moves to " + exit.getName();
					break;
				}
			}
		}
		return result;
	}
			

	@Override
	public String menuDescription(Actor actor) {
		return "";
	}

	@Override
	public String hotKey() {
		return "";
	}
	
	/**
	 * Calculate the horizontal distance between two Locations
	 * 
	 * @param a first Location
	 * @param b second Location
	 * @return horizontal distance between two Locations
	 */
	protected int distanceX(Location a, Location b) {
		return Math.abs(a.x()-b.x());}
	
	/**
	 * Calculate the verticle distance between two Locations
	 * 
	 * @param a first Location
	 * @param b second Location
	 * @return verticle distance between two Locations
	 */
	protected int distanceY(Location a, Location b) {
		return Math.abs(a.y()-b.y());
	}
	
	/**
	 * Check if the distance between two Location is within 5 squares
	 * 
	 * @param a first Location
	 * @param b second Location
	 * @return true if the distance between two Location is within 5 squares, false otherwise
	 */
	protected boolean withinFiveSquare(Location a, Location b) {
		return (distanceX(a,b) < 6 && distanceY(a,b) < 6);
	}
	
}