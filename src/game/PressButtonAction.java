package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class PressButtonAction extends Action{
	
	/**
	 * Constructor does nothing
	 */
	public PressButtonAction() {
	}

	/**
	 * If there is an OxygenTank on the location of Actor, OxygenTank is not produced
	 * Else add OxygenTank onto the location of actor (actor is on Oxygen Dispenser with PressButtonAction)
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Item oxygenTank = new OxygenTank();
		
		for (Item item : map.locationOf(actor).getItems()) {
			if (item instanceof OxygenTank) {
				return "Oxygen tank is already on the map. Butom not working";
			}
		}
		map.locationOf(actor).addItem(oxygenTank);
		return "Oxygen tank is produced.";
	}

	/**
	 * Description of the Action to be displayed in menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " presses button on Oxygen Dispenser.";
	}

	@Override
	public String hotKey() {
		return "";
	}


}
