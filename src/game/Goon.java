package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;

public class Goon extends Enemy{
	private NewPlayer target;

	/**
	 * Constructor to create Goon
	 * Represented by 'G' on map
	 * Goon has 50 hitpoints
	 * it has FollowBehaviour and InsultBehaviour by default
	 * it also has key item in its inventory when created
	 * the key item will be added to Door objects so that Door can check if Actor has this key
	 *  
	 * @param name the name of Goon
	 * @param player the target Actor
	 */
	public Goon(String name, NewPlayer player) {
		super(name, 'G', 2, 50);
		this.target = player;
		addBehaviour(new InsultBehaviour(target));		
		addBehaviour(new FollowBehaviour(target));
		Item key = Item.newInventoryItem("Key", 'k');
		addItemToInventory(key);
		Door.addKey(key);	
		}

    /** 
     * Select and return an action to perform on the current turn.
     *
     * Remove DropItemAction so that Goon will not drop its key randomly
     * 
     * Goon will choose a behaviour in its actionFactories to be performed 
     * if the behaviours are performable (not return null)
     * 
     * In this case. Goon will either perform InsultBehaviour or FollowBehaviour
     * There is 10% chance for Goon to insult Actor (Refer to InsultBehaviour)
     * If InsultBehaviour cannot be performed, it will try to perform FollowBehaviour (Refer to FollowBehaviour)
     * Else they will act randomly by choosing a random Action in actions
     *
     * @param actions collection of possible Actions for this Goon
	 * @param map     the map containing the Goon
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
	 * The basic weapon that Goon has. Goon will slap Actor with 20 damage
	 * Goon has double damage of Grunt (Grunt has 10 damage so Goon has 20)
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(20, "slaps");
	}
}
