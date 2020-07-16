package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;


public class GivePlansAction extends Action{
		private Actor subject;
		private Actor actor;
		private Item rocketPlan;
		
		/**
		 * Constructor to create GivePlansAction
		 * 
		 * @param actor target Actor
		 * @param subject the Actor that has GivePlansAction
		 * @param rocketPlan the Item rocketPlan
		 */
		public GivePlansAction(Actor actor, Actor subject, Item rocketPlan){
			this.subject = subject;
			this.setActor(actor);
			this.rocketPlan = rocketPlan;
		}

		/**
		 * Exchange rocketPlan Item from the inventory of the Actor who has rocketPlan 
		 * 
		 * This is done by removing the rocketPlan from the Actor's inventory
		 * and the Actor who has GivePlansAction will perform DropItemAction to drop rocketBody Item on map
		 * 
		 * The Actor who performs this Action will be removed from the map
		 *
		 * @param actor The actor performing the action.
		 * @param map The map the actor is on.
		 * @return a string description to tell user that rocketBody is dropped on the map
		 */
		@Override
		public String execute(Actor actor, GameMap map) {
			List <Item> targetList = actor.getInventory();
			for (Item myItem: targetList) {
				if (myItem.equals(rocketPlan)) {
					actor.removeItemFromInventory(myItem);
				}
			}
			
			for (Item item : subject.getInventory()) {
				for (Action action : item.getAllowableActions()) {
					if (action instanceof DropItemAction) {
						action.execute(subject, map);
						break;
					}
				}
			}
			map.removeActor(subject);
			return subject + ": Here is Rocket Body, Bye bye! :)";
			}

		/**
		 * String to show on menu to allow user to choose to exchange plans
		 */
		@Override
		public String menuDescription(Actor actor) {
			return actor + " exchange rocket plan with " + subject;
		}

		@Override
		public String hotKey() {
			return "";
		}

		/**
		 * Getter for actor
		 * @return actor
		 */
		public Actor getActor() {
			return actor;
		}

		/**
		 * Setter for actor
		 * @param actor the actor to be set to this action
		 */
		public void setActor(Actor actor) {
			this.actor = actor;
		}

	}


