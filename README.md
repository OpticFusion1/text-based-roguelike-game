# basecode

We have modify the game package, all the code inside the engine code is remains same.

We had created new Enemy class which is extended from the Actor Class.

The Goon, Grunt, Ninja, Q and Doctor Maybe and YugoMaxx class are extended from the Enemy class.

There are three classes that we extend from Action class and implements from Action Factory which are StandStillBehaviour,
InsultBehaviour and ThrowStuntPowderBehaviour, Random Behaviour.

GivePlansAction, TalkAction, OpenDoorAction, FlyToOtherMap, FillWaterAction, PressButtonAction and EndGame are the classes that we extend from Action class to be used in getAllowableActions.

RocketPad and Rocket is the subclass of the Item class, so when the player stands on the item, the item will provides the player list of allowableActions.

Water is the class that extending from Ground, so the player cannot stands on the water, and can have FillWaterAction while standing besides the water(ground).

Oxyen Dispenser is the subclass of Item which will give the Player PressButtonAction to produce oxygen tank which provides the PickUpItem action tp the player.

EndGame Action is added to the newPlayer actions, so it will show on the menu as a option for the player to quit game.

newWorld extends from the World class so it will keep track if the newPlayer is still on the map, hence once the player is disappear from the map, the game automatically end and shows its corresponding prompt to the user

Door class is created to store the ArrayList of keyItem.

Rocket class is created to store the ArrayList of the rocketbody parts.
