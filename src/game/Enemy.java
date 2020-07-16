package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.AttackAction;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class Enemy extends Actor{
	
	protected List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();


	/**
	 * Constructor to create Enemy
	 * 
	 * This is superclass for other enemies such as Grunt, Goon, etc
	 * Extends from Actor class so will inherit methods from Actor class
	 * Enemies have cybernatic skill which allows them to travel in moonbase without oxygen tank and spacetravelling skill
	 * 
	 * @param name the name of the Enemy
	 * @param displayChar the character to represent the Enemy on gameMap
	 * @param priority use to determine the Enemy (Actor)'s order in one cycle of game
	 * @param hitPoints the 'life' of Enemy
	 */
	public Enemy(String name, char displayChar, int priority, int hitPoints) {
		super(name, displayChar, priority, hitPoints);
		this.addSkill(SkillsList.CYBERNATIC);
	}
	
	/**
	 * Add behaviour to the Enemy's actionFactories
	 * 
	 * Only subclasses of Enemy can use this method
	 * 
	 * @param behaviour the behaviour of Enemy to be added into actionFactories list
	 */
	protected void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}
	
    /** 
     * Select and return an action to perform on the current turn.
     *
     * By default, Enemy will choose a behaviour in their actionFactories to be performed 
     * Else they will act randomly. To change this, override this method.
     *
     * @param actions collection of possible Actions for this Enemy
	 * @param map     the map containing the Enemy
	 * @param display the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {	
		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if (action != null) {
				return action;
			}
		}
		
		return super.playTurn(actions,  map,  display);
	}
	
	 
	/**
     * Returns a collection of the Actions which can be performed by other Actor on Enemy:
     * Check if Enemy and otherActor are on the same map
     * Enemy will also check if otherActor is NewPlayer
     * If the above conditions met, Enemy will have AttackAction so that only NewPlayer can attack them so Enemy will not attack enemy
     * 
     * @param otherActor the Actor that might get actions that can be performed on Q
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return A collection of Actions.
	 */
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Location here = map.locationOf(this);
		Location there = map.locationOf(otherActor);
		char hereChar = here.getGround().getDisplayChar();
		char thereChar = there.getGround().getDisplayChar();
		if (hereChar == thereChar) {
			if (otherActor instanceof NewPlayer) {
				return new Actions(new AttackAction(otherActor, this));
			}
		}
		return new Actions();
		
	}


}
