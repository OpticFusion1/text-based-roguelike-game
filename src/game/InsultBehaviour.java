package game;

import edu.monash.fit2099.engine.*;

import java.util.Random;


public class InsultBehaviour extends Action implements ActionFactory {

	private Actor target;	
	/**
	 * Constructor for InsultBehaviour
	 * 
	 * @param subject the targetted Actor
	 */
	public InsultBehaviour(Actor subject) {
		this.target = subject;
	}

	/**
	 * Insult the Actor by shouting insulting words at Actor
	 * Insulting words will be chosen randomly from a list
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a string to show insulting words
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		final String[] InsultList = {"Loser Loser","HAHAHAHA U WILL NEVER WIN","JUST GIVE UP HAHAHAHAHA"};
		Random r = new Random();
		int randomNumber = r.nextInt(InsultList.length);
		return actor + ": " + InsultList[randomNumber];
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
	 * Return InsultBehaviour which allows the Actor who performs this Action to throw insulting words
	 * the Actor has 10% chance to perform this Action
	 * Else it won't perform the action (and will try next behaviour in the Actos's actionFactories)
	 * 
	 * @return this Action if probability is 10% or null otherwise
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		Random b = new Random();
		int num = b.nextInt(10);
		if (num == 1) {
			return this;
		}
		return null;
	}
}

