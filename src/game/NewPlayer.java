package game;

import edu.monash.fit2099.engine.*;

public class NewPlayer extends Player{
	
	private Location earthLocation;
	private boolean isStunned = false;
	private boolean hasWon = false;
	private int stunCounter = 2;
	private Actor target;
	protected int oxygenPoint = 0;

	/**
	 * Constructor to create NewPlayer
	 * 
	 * @param name the name of NewPlayerOnject
	 * @param displayChar character to represents the NewPLayer object
	 * @param priority the order for NewPlayer to perform Action in one turn of the game
	 * @param hitPoints the 'life' of NewPlayer
	 */
	public NewPlayer(String name, char displayChar, int priority, int hitPoints) {
		super(name, displayChar, priority, hitPoints);
	}
	
	
	/**
	 * Getter for target
	 * @return target Actor
	 */
	public Actor getTarget() {
		return target;
	}


	/**
	 * Setter for target, to set target Actor for NewPlayer
	 * @param target Actor targeted by NewPlayer
	 */
	public void setTarget(Actor target) {
		this.target = target;
	}

	/**
	 * Getter to check if the NewPlaye is stunned
	 * 
	 * @return true if NewPlayer is stunned and false otherwise
	 */
	public boolean getIsStunned() {
		return isStunned;
	}

	/**
	 * Setter to modify NewPlayer's stun condition
	 * 
	 * @param isStunned true to set NewPlayer to be stunned or false to set NewPlayer to be not stunned
	 */
	public void setIsStunned(boolean isStunned) {
		this.isStunned = isStunned;
	}
	
	/**
	 * Getter to get the number of turns that NewPlayer has been stunned
	 * 
	 * @return number of turns that NewPlayer has been stunned
	 */
	public int getStunCounter() {
		return stunCounter;
	}

	/**
	 * Setter to set the last location of NewPlayer in the earth
	 * 
	 * @param last location of NewPlayer in the earth
	 */
	public void setEarthLocation(Location earthLocation) {
		this.earthLocation = earthLocation;
	}
	
	/**
	 * Getter to get the last location of NewPlayer in the earth
	 * 
	 * @return last location of NewPlayer in the earth
	 */
	public Location getEarthLocation() {
		return this.earthLocation;
	}

	/**
	 * Getter to get the oxygen point of player
	 * 
	 * @return oxygen point of player
	 */

    public int getOxygenPoint() {
		return oxygenPoint;
	}
    
    /**
	 * Setter to get the oxygen point of player
	 * 
	 * @param oxygen point of player
	 */

	public void setOxygenPoint(int oxygenPoint) {
		this.oxygenPoint = oxygenPoint;
	}

	/**
	 * Count total oxygenPoint that NewPlayer currently having
	 * @return value of OxygenPoint of NewPlayer
	 */
	public int getPlayerOxygen() {
		this.setOxygenPoint(0);
		for (Item item : this.getInventory()) {
			if (item instanceof OxygenTank) {
				this.setOxygenPoint(oxygenPoint + ((OxygenTank) item).getPoints());
		}}
		return this.getOxygenPoint();
	}
	/** 
     * Select and return an action to perform on the current turn.
     *
     * Menu will have EndGame option to quit the game
     * 
     * If NewPlayer is stunned, update the stunCounter, the menu will be shown but NewPlayer can only perform SkipTurnAction
     * If NewPlayer is on moonbase, 
     *  - decrease oxygenPoint by 1 and if OxygenTank is empty, remove the OxygenTank
     *  - send NewPlayer back to Earth if NewPlayer do not have enough oxygen
     * 
     * else it will show menu to allow NewPlayer to choose Action to be performed
     *
     * @param actions collection of possible Actions for NewPlayer
	 * @param map     the map containing the NewPlayer
	 * @param display the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		actions.add(new EndGame(this));
		Crater crater = new Crater();
		if (getIsStunned()) {
			stunCounter -= 1;
			if (stunCounter > 0) {
				isStunned = true;
			}
			else if (stunCounter <= 0) {
				isStunned = false;
				stunCounter = 2;
			}
			showMenu(actions, display);
			return new SkipTurnAction();
		}
		else {
			isStunned = false;
		}

		if (map.groundAt(map.locationOf(this)).getDisplayChar() == crater.getDisplayChar()) {
			for (Item item : this.getInventory()) {	
				if (item instanceof OxygenTank) {
					((OxygenTank) item).setPoints(((OxygenTank) item).getPoints() - 1);
					if (((OxygenTank) item).isEmpty()) {
						this.removeItemFromInventory(item);			
					}	
					break;
				}		
				
			}
			if (!(this.getOxygenPoint() > 0)) {
				return new FlyToOtherMap(this.getEarthLocation());
			}
		}		
		return showMenu(actions, display);
	}
	
	/**
	 * NewPlayer has this intrinsic weapon by default
	 * NewPlayer can punch other Actor for 20 damage
	 */
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(20, "punches");
	}

	/**
	 * Setter to set if the player's inventory has Yugo Maxx body
	 * 
	 * 
	 */
	public void hasWon(){
		this.hasWon = true;
	}
	
	/**
	 * Getter to  get if the player's inventory has Yugo Maxx body
	 * 
	 * @return if the player's inventory has Yugo Maxx body
	 */
	public boolean checkHasWon() {
		return this.hasWon;
	}
	
	@Override 
	/**
     * Returns a collection of the Actions which can be performed by other Actor on NewPlayer:
     * OtherActor will have AttackAction allows other Actor to attack NewPlayer
     * If other Actor is in the same map with player else it will return empty action list
     * 
     * @param otherActor the Actor that might get actions that can be performed on NewPlayer
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return A collection of Actions.
	 */
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Location here = map.locationOf(this);
		Location there = map.locationOf(otherActor);
		char hereChar = here.getGround().getDisplayChar();
		char thereChar = there.getGround().getDisplayChar();
		if (hereChar==thereChar) {
			return new Actions(new AttackAction(otherActor, this));
		}
		return new Actions();
		
	}
	

	


	
	



}
