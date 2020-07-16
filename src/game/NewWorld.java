package game;

import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.World;

public class NewWorld  extends World{
	/**
	 * Constructor of newWorld
	 * 
	 * @param display
	 */
	public NewWorld(Display display) {
		super(display);
	}
	
	@Override
	/**
	 * Run the game.
	 *
	 * On each iteration the game loop does the following:
	 *  - displays the player's map
	 *  - processes the actions of every Actor in the game, regardless of map
	 *  -After every turn, check if the player still on the map
	 *
	 * We could either only process the actors on the current map, which would make
	 * time stop on the other maps, or we could process all the actors.  We chose to
	 * process all the actors.
	 *
	 * @throws IllegalStateException if the player doesn't exist
	 */
	public void run() {
		if(player == null)
			throw new IllegalStateException();
		while (stillRunning()) {
			GameMap playersMap = actorLocations.locationOf(player).map();
			playersMap.draw(display);
			for (Actor actor : actorLocations) {
				processActorTurn(actor);
				if (!stillRunning()){
					break;
				}
			}
		}
		display.println(endGameMessage());
	}
	/**
	 * check if the player's inventory contains "Unconscious YugoMaxx"
	 */
	public boolean hasWon() {
		boolean flag = false;
		List <Item> targetList = player.getInventory();
		for (Item myItem: targetList) {
			String name = myItem.toString();
			if (name.equals("Unconscious YugoMaxx")) {
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * Return a string that can be displayed when the game ends based on the player
	 *
	 * @return the string "Game Over"
	 */
	protected String endGameMessage() {
		if (!player.isConscious()) {
			return "Player loses";
		}
		if (hasWon()) {
			return "Player wins";
		}
		return "Game End";
	}
}
