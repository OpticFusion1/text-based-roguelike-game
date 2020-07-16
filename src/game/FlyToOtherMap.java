package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;


public class FlyToOtherMap extends Action{
		private Location newLocation;
		private String targetMap;
		private Crater crater;
		
		/**
		 * Constructor of FlyToMats action
		 * 
		 * @param actor the Actor acting
		 */
		public FlyToOtherMap(Location newLocation){
			this.newLocation = newLocation;
			this.crater = new Crater();
		}
		
		/**
		 * Move NewPlayer to Rocket of another map if player can enter the other map and no other Actor is on the Rocket
		 * Remove the Actor from gameMap indicating Actor wins if Actor's inventory contains Unconscious YugoMaxx
		 * 
		 * @return a message to indicate end of the game
		 */
		@Override
		public String execute(Actor actor, GameMap map) {
			if (actor instanceof NewPlayer) {
				((NewPlayer) actor).setEarthLocation(map.locationOf(actor));
			}
			
			boolean flag = false;
			List <Item> targetList = actor.getInventory();
			for (Item myItem: targetList) {
				String name = myItem.toString();
				if (name.equals("Unconscious YugoMaxx")) {
					flag = true;
				}
			}
			
			if ((flag) && actor instanceof NewPlayer){
				map.removeActor(actor);
				return actor + " fly back to the earth with Yugo Maxx unconscious body";
			}
			else {
				if (!(map.groundAt(map.locationOf(actor)).getDisplayChar() == crater.getDisplayChar())) {
					if (newLocation.canActorEnter(actor)) {
						map.moveActor(actor, newLocation);
					}
					else {
						if (newLocation.containsActor() && !(newLocation.getActor() instanceof NewPlayer)) {
							return actor + ", Please wait. Someone is on the rocket";
							}
						else if (!(crater.canActorEnter(actor))) {
							return actor + " does not have enough skills to travel to moon.";

						}
					}
				}
				else {
					if(!newLocation.containsActor()) {
						map.moveActor(actor, newLocation);
					}
					else {
						if (!(actor instanceof NewPlayer)) {
							return actor + ", Please wait. Someone is on the rocket";
						}
					}
				}	
			}
			return menuDescription(actor);
		}

		/**
		 * Return a string allows Actor to choose to fly to Mars
		 */
		@Override
		public String menuDescription(Actor actor) {
			char newLocationGround = newLocation.getGround().getDisplayChar();
			if (newLocationGround == crater.getDisplayChar()) {
				targetMap = "Moon";
			}
			else {
				targetMap = "Earth";
			}
			return actor + " fly to " + targetMap;
		}

		@Override
		public String hotKey() {
			return "";
		}

	}
