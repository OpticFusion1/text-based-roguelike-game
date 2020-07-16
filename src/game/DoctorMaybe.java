package game;



import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.AttackAction;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;


public class DoctorMaybe extends Enemy{
	private Item rocketEngine;
	
	/**
	 * Constructor to create Doctor Maybe
	 * 
	 * Doctor Maybe is represented by 'D' in gameMap, and have half hitpoint of Grunt
	 * Grunt has 50 hitpoints so Doctor Maybe has 25 hitpoints
	 * 
	 * has rocketEngine item in its inventory when Doctor Maybe is created and rocketEngine will be added to RocketPad location
	 * has StandStillBehaviour which allows Doctor Maybe to stay in its place throughout the game execution
	 * 
	 * @param name the name of Doctor Maybe
	 * @param player target Actor class object
	 */
	public DoctorMaybe (String name, NewPlayer player) {
		super(name, 'D', 5, 25);
		this.rocketEngine = Rocket.newInventoryItem("RocketEngine", 'E');
		addItemToInventory(this.rocketEngine);
		Rocket.AddRocketParts(this.rocketEngine);
		addBehaviour(new StandStillBehaviour());
	}
	
	
	/**
     * Select and return an action to perform on the current turn.
     *
     * Doctor Maybe will prioritise AttackAction
     * If there is no AttackAction in actions list, it will perform an action which not return null from actionFactories
     * 
     * In this case, Doctor Maybe has only StandStillBehaviour so it will check if StandStillBehaviour will return null
     * If StandStillBehaviour not returning null then it will perform StandStillBehaviour (which does nothing)
     *
     * @param actions collection of possible Actions for Doctor Maybe
	 * @param map     the map containing Doctor Maybe
	 * @param display the I/O object to which messages may be written
	 * @return the Action to be performed by Doctor Maybe
	 */
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		for (Action action: actions) {
			if (action instanceof AttackAction) {
				return action;
			}
		}
		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if (action != null) {
				return action;
			}
		}
		return null;
	}
	
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(5, "slaps");
	}
}