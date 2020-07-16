package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.SkipTurnAction;

public class StandStillBehaviour extends SkipTurnAction implements ActionFactory{

	/**
	 * Constructor to create StandStillBehaviour
	 * 
	 * inherits from SkipTurnAction
	 */
	public StandStillBehaviour() {
		super();
	}

	@Override
	public String menuDescription(Actor actor) {
		return "";
	}

	/**
	 * Performs this action
	 * 
	 * Performing is by executing super class SkipTurnAction's execute()
	 * 
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		return this;
	}
	
	


}
