package game;

import edu.monash.fit2099.engine.*;


public class Grunt extends Enemy {
	private NewPlayer target;

	/**
	 * Constructor to create Grunt
	 * 
	 * Represented by 'g' on map
	 * Grunt has 50 hitpoints
	 * it has FollowBehaviour by default
	 * it also has key item in its inventory when created
	 * the key item will be added to Door objects so that Door can check if Actor has this key
	 *  
	 * @param name the name of Grunt
	 * @param player the target Actor
	 */
	// Grunts have 50 hitpoints and are always represented with a g
	public Grunt(String name, NewPlayer player) {
		super(name, 'g', 3, 50);
		this.target = player;
		addBehaviour(new FollowBehaviour(player));
		Item key = Item.newInventoryItem("Key", 'k');
		addItemToInventory(key);
		Door.addKey(key);
		}

		
    /** 
     * Select and return an action to perform on the current turn.
     *
     * Remove DropItemAction so that Grunt will not drop its key randomly
     * 
     * Grunt will choose a behaviour in its actionFactories to be performed 
     * if the behaviours are performable (not return null)
     * 
     * In this case. Grunt will try to perform FollowBehaviour if it is performable
     * Else they will act randomly by choosing a random Action in actions
     *
     * @param actions collection of possible Actions for this Grunt
	 * @param map     the map containing the Grunt
	 * @param display the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {		
		
		for (Action action: actions) {
			if (action instanceof DropItemAction) {
				actions.remove(action);
			}
		}
		
		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if (action != null) {
				return action;
			}
		}	
		return super.playTurn(actions,  map,  display);
	}

	
	/**
	 * The basic weapon that Grunt has. Grunt will slap Actor with 10 damage
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "slaps");
	}
}
