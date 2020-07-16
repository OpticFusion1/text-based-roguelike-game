package game;

public class Ninja extends Enemy{
	
	private NewPlayer target;	

	/**
	 * Constructor to create Ninja
	 * 
	 * represented by 'n' on game map
	 * its target will be NewPlayer only
	 * Ninja has ThrowStunPowderBehaviour and StandStillBehaviour by default
	 * 
	 * @param name the name of Ninja 
	 * @param player targetted NewPlayer
	 */
	public Ninja(String name, NewPlayer player) {
		super(name, 'n', 4, 50);
		this.target = player;
		addBehaviour(new ThrowStunPowderBehaviour(target));
		addBehaviour(new StandStillBehaviour());
	}
}
