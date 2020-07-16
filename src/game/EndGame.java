package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class EndGame extends Action {
	private Actor player;
	
	/**
	 * Constructor for EndGame
	 * for Actor to select quit option
	 * @param player the acting Actor
	 */
	public EndGame(Actor player) {
		this.player = player;
	}

	/**
	 * Remove actor from gameMap to indicate he quits the game
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		map.removeActor(player);
		return actor + " quits !";
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor+ " quit game ";
	}

	@Override
	public String hotKey() {
		return "";
	}
}
