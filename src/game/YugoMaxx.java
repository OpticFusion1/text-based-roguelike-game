package game;


import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;

public class YugoMaxx extends Enemy {
	private NewPlayer target;
	
	/**
	 * Constructor for YugoMaxx
	 * Represented by symbol 'Y' on map
	 * YugoMaxx has 50 hitpoints
	 * Has RandomBehaviour (which allows it to wander around moon randomly)
	 * and StandStillBehaviour by default
	 * 
	 */
	public YugoMaxx() {
		super("YugoMaxx", 'Y', 5, 50);
	}

	/**
	 * Setter for target, to set a target NewPlayer for YugoMaxx
	 * 
	 * @param target NewPlayer player as target of YugoMaxx
	 */
	public void setTarget(NewPlayer target) {
		this.target = target;
	}

	/**
	 * The basic weapon that YugoMaxx has. YugoMaxx will hit target with 20 damage
	 * YugoMaxx has same damage as Goon (Goon has 20 damage)
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(20, "hits");
	}
	
	/**
	 * Set action that otherActor can do to YugoMaxx
	 * If otherActor has WaterPistol and the WaterPistol is not empty, 
	 * otherActor can shoot YugoMaxx with ShootingAction (refer to ShootingAction)
	 * else otherActor cannot do anything to YugoMaxx
	 */
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		for (Item item : otherActor.getInventory()) {	
			if (item instanceof WaterPistol) {
				if (!((WaterPistol) item).isEmpty()){
					return new Actions(new ShootingAction(otherActor, this));
				}
			}
		}
		return new Actions();
	}
}
