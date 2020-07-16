package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

public class Crater extends Ground{
	/**
	 * Constructor to create Door object
	 * 
	 * Crater is represented by '*' in gameMap
	 */

	public Crater() {
		super('*');
	}
	
	/**
	 * Check the type of Actor to enter this terrain.
	 * if is NewPlayer return if player has SPSCETRAVELLER skill and getPlayerOxygen is more then 0
	 * else return if actor has CYBERNATIC Skill
	 * 
	 * @param actor the Actor acting
	 * @return false
	 */

	@Override
	public boolean canActorEnter(Actor actor) {
		if (actor instanceof NewPlayer) {
			NewPlayer player = (NewPlayer) actor;
			return (player.hasSkill(SkillsList.SPACETRAVELLER) && player.getPlayerOxygen()>0);
		}
		else
			return actor.hasSkill(SkillsList.CYBERNATIC);
	}




}
