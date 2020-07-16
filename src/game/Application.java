package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class Application {

	public static void main(String[] args) {
		NewWorld world = new NewWorld(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Crater(), new Door(), new Floor(), new Wall(), new Water());
		GameMap gameMap;

		List<String> map = Arrays.asList(
				"..........W............",
				"....#####....####.#....",
				"........#....#....#....",
				"....#.............#....",
				"....#####....##+###....",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................");
		gameMap = new GameMap(groundFactory, map);
		world.addMap(gameMap);
		
        List<String> moonbaseMap = Arrays.asList(
                "************",
                "************",
                "************",
                "************",
                "************",
                "************",
                "************",
                "************",
                "************",
                "************",
                "************");
        
        GameMap moonbase = new GameMap(groundFactory, moonbaseMap);
        world.addMap(moonbase);
        
        NewPlayer player = new NewPlayer("Player", '@', 1, 100);
		world.addPlayer(player, gameMap, 2, 2);
		
		YugoMaxx YugoMaxx = new YugoMaxx();
		moonbase.addActor(YugoMaxx, 10, 10);
		player.setTarget(YugoMaxx);
		YugoMaxx.setTarget(player);
		YugoMaxx.addBehaviour(new RandomBehaviour(YugoMaxx, player));
		YugoMaxx.addBehaviour(new StandStillBehaviour());
		
        RocketPad rocketPadMoon = new RocketPad(gameMap.at(0, 0));
        moonbase.addItem(rocketPadMoon, 0, 0);	
		
		RocketPad rocketPad = new RocketPad(moonbase.at(0, 0));
		gameMap.addItem(rocketPad, 0, 0);
		
		Rocket rocket = new Rocket(gameMap.at(0, 0));
		moonbase.addItem(rocket, 0, 0);
		
		Ninja ninja = new Ninja("Ninja", player);
		gameMap.addActor(ninja, 18, 8);
		
		Grunt grunt = new Grunt("Grunt1", player);
		gameMap.addActor(grunt, 0, 10);
	
		Grunt grunt2 = new Grunt("Grunt2", player);
		gameMap.addActor(grunt2,  5, 6);
	
		Goon gooon3 = new Goon("Goon1", player);
		gameMap.addActor(gooon3,  20, 3);
		
		Goon gooon4 = new Goon("Goon2", player);
		gameMap.addActor(gooon4,  12, 10);
		
		Item rocketPlan = new Item("Rocket Plan",'P');
		gameMap.addItem(rocketPlan, 15, 2);
		
		Q myQ = new Q("Q", player, rocketPlan);
		gameMap.addActor(myQ, 22, 5);
		
		DoctorMaybe drM = new DoctorMaybe("Doctor Maybe",player);
		gameMap.addActor(drM, 6, 3);
		
		Item spaceSuit = new Item("Spacesuit", 'S');
		gameMap.addItem(spaceSuit, 7, 8);
		spaceSuit.addSkill(SkillsList.SPACETRAVELLER);
		
		Item oxygenDispenser = Item.newFurniture("Oxygen Dispenser", 'O');
		oxygenDispenser.getAllowableActions().add(new PressButtonAction());
		gameMap.addItem(oxygenDispenser, 1, 6);

		WaterPistol waterPistol = new WaterPistol();
		gameMap.addItem(waterPistol, 4, 0);
		
		Ninja ninja1 = new Ninja("Ninja1", player);
		moonbase.addActor(ninja1, 8, 8);
		
		Grunt grunt3 = new Grunt("Grunt3", player);
		moonbase.addActor(grunt3, 1, 10);
	
		Grunt grunt4 = new Grunt("Grunt4", player);
		moonbase.addActor(grunt4,  5, 6);
	
		Goon gooon5 = new Goon("Goon5", player);
		moonbase.addActor(gooon5,  7, 3);
		
		Goon gooon6 = new Goon("Goon6", player);
		moonbase.addActor(gooon6,  6, 2);
		
		world.run();

		

	}
}

