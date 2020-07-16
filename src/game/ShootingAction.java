package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

public class ShootingAction extends Action {

	private Actor subject;
	private Actor actor;

	/**
	 * Constructor for ShootingAction
	 * 
	 * @param actor Actor that can perform this Action
	 * @param subject the target Actor
	 */
	public ShootingAction(Actor actor, Actor subject) {
		this.actor = actor;
		this.subject = subject;
	}

	/**
	 * Actor shoots target and empties his WaterPistol
	 * The acting actor has 70% chance of hitting target and cause damage to target
	 * If target is unconscious, remove the target from the map and show its unconscious body on map
	 * Unconscious bosy of target is an Item so actor can pick it up
	 * 
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Random rand = new Random();
		int prob = rand.nextInt(10);
		int damage = 0;
		
		Weapon weapon = actor.getWeapon();
		
		for (Item item : actor.getInventory()) {
			if (item instanceof WaterPistol) {
				((WaterPistol)item).setWaterLevel(((WaterPistol) item).getEmptyWaterLevel());
			}
		}
	
		String result = actor + " shoots water at " + subject +"." + System.lineSeparator();
	
		if (prob >= 7) {
			return actor + " misses " + subject + ".";
		}

		if (weapon instanceof WaterPistol) {
			damage = weapon.damage();
		}
		
		result = actor + " " + weapon.verb() + " " + subject + " for " + damage + " damage.";

		subject.hurt(damage);
		if (!subject.isConscious()) {
			Item sleepingActor = new Item("Unconscious " + subject, '%');
			map.locationOf(subject).addItem(sleepingActor);
			map.removeActor(subject);
			result += System.lineSeparator() + subject + " is knocked out.";
		}

		return result;	

	}
	
	/**
	 * Description of the action to be shown on menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " shoots " + subject;
	}

	@Override
	public String hotKey() {
		return "";
	}


}
