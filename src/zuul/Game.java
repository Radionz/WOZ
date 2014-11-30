package zuul;

import zuul.entities.Player;
import zuul.entities.items.Coffee;
import zuul.entities.items.Item;
import zuul.io.Command;
import zuul.io.CommandWord;
import zuul.io.IO;
import zuul.io.Parser;
import zuul.rooms.*;
import zuul.studies.Lesson;
import zuul.studies.Question;

import java.io.IOException;

/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game. Users can walk
 * around some scenery. That's all. It should really be extended to make it more
 * interesting!
 * 
 * To play this game, create an instance of this class and call the "play"
 * method.
 * 
 * This main class creates and initialises all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates and executes the
 * commands that the parser returns.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2011.08.10
 */

public class Game {

	private static Player player;
	private Parser parser;
	private Room currentRoom;

	private static Question[] questions;
	private static Lesson[] lessons;

	/**Lesson[]
	 * Create the game and initialise its internal map.
	 */
	public Game() {
		questions = new Question[15];
		lessons = new Lesson[15];
		createRooms();
		init();
		parser = new Parser();
	}

	public static Player getPlayer() {
		return player;
	}

	public static Question[] getQuestions() {
		return questions;
	}

	/**
	 * Create all the rooms and link their exits together.
	 */
	private void createRooms() {
		Room outside, theater, pub, lab, office;

		// create the rooms
		outside = new Room("outside the main entrance of the university");
		ClassRoom classroom = new ClassRoom("in the classroom", false, 0);
		ExamRoom examroom = new ExamRoom("in the examroom");
		LabRoom labroom = new LabRoom("in the labroom");
		Library library = new Library("in the library");
		LunchRoom lunchroom = new LunchRoom("in the lunchroom");
		Corridor corridor = new Corridor("in the corridor");
		Corridor corridor2 = new Corridor("in the corridor");
		theater = new Room("in a lecture theater");
		pub = new Room("in the campus pub");
		office = new Room("in the computing admin office");

		// initialise rooms exits
		outside.setExit(Room.Exits.EAST, library);
		outside.setExit(Room.Exits.SOUTH, corridor);
		outside.setExit(Room.Exits.WEST, lunchroom);
		outside.addItem(new Item("Chocolate bar",1));
		outside.addUsableItem(new Item("Chocolate bar",1));
		outside.addItem(new Coffee());
		

		lunchroom.setExit(Room.Exits.WEST, pub);
		library.setExit(Room.Exits.WEST, theater);

		corridor.setExit(Room.Exits.EAST, labroom);
		corridor.setExit(Room.Exits.WEST, classroom);
		corridor.setExit(Room.Exits.SOUTH, corridor2);

		corridor2.setExit(Room.Exits.EAST, office);
		corridor2.setExit(Room.Exits.WEST, examroom);
		corridor2.setExit(Room.Exits.SOUTH, outside);

		currentRoom = outside; // start game outside
	}


	private void init(){
		// TEST LESSON
		try {
			for(int k = 0; k < 15 ; k++){
				IO.addToFileByName(String.valueOf(k), "Question " + k +" ? T", IO.PossibleFiles.POO_QUESTION.getPath());
			}
			IO.flushJSON();
			for(int k = 0; k < 5 ; k++){
				IO.addToFileByName(String.valueOf(k), "POO_lesson :" + k +" . " +" suite POO_lesson :" + k , IO.PossibleFiles.POO_LESSON.getPath());
			}
			IO.flushJSON();
			for(int k = 0; k < 10 ; k++){
				IO.addToFileByName(String.valueOf(k), "lesson :" + k +" . " +" suite lesson :" + k*k, IO.PossibleFiles.OTHER_LESSON.getPath());
			}
			IO.flushJSON();
			/*
			IO.addToFileByName(String.valueOf(1), "Is object class inheritable ? T", IO.PossibleFiles.POO_QUESTION.getPath());
			IO.addToFileByName(String.valueOf(2), "is a class abstract instanciable ? F", IO.PossibleFiles.POO_QUESTION.getPath());
			IO.addToFileByName(String.valueOf(3), "Do you like coffee ? T", IO.PossibleFiles.POO_QUESTION.getPath());
			 */
			//IO.flushJSON();

			//IO.addToFileByName(String.valueOf(1), "Hello everyone. Today we're gonna learn about class descriptor. Really easy. See ya !", IO.PossibleFiles.POO_LESSON.getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		for(int i = 0; i < 15 ; i++){
			questions[i] = new Question(i);
			if(i%3==0){
				lessons[i/3] = new Lesson(true, i/3);
			}
			lessons[4+(2*(i/3))+(i%3)] = new Lesson(false, (2*(i/3))+(i%3)-1);
		}
		for(Lesson l : lessons){
			System.out.println(l.toString()+'\n');
		}
	}

	/**
	 * Main play routine. Loops until end of play.
	 */
	public void play() {
		// TEST //
		Item item = new Item("an empty beer can", 0);
		//-TEST-//
		this.player = new Player(getPlayerName(), item);
		printWelcome();

		// Enter the main command loop. Here we repeatedly read commands and
		// execute them until the game is over.

		boolean finished = false;
		while (!finished) {
			Command command = parser.getCommand();
			finished = processCommand(command);
		}
		System.out.println("Thank you for playing.  Good bye.");
	}

	/**
	 * Print the beginning of the welcome message and allows the player to type his name.
	 * @return String of player's name.
	 */
	private String getPlayerName() {
		System.out.println();
		System.out.println("Welcome to the World of Zuul!");
		System.out.println("First, tell me, what's your sweet name ?");
		return parser.getPlayerName();
	}

	/**
	 * Print out the opening message for the player.
	 */
	private void printWelcome() {
		System.out.println();
		System.out.println("As you can see, " + this.player.getName() + ", World of Zuul is a new, incredibly boring adventure game.");
		System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
		System.out.println();
		System.out.println(currentRoom.getLongDescription());
	}

	/**
	 * Given a command, process (that is: execute) the command.
	 * 
	 * @param command
	 *            The command to be processed.
	 * @return true If the command ends the game, false otherwise.
	 */
	private boolean processCommand(Command command) {
		boolean wantToQuit = false;

		CommandWord commandWord = command.getCommandWord();

		switch (commandWord) {
		case UNKNOWN:
			System.out.println("I don't know what you mean...");
			break;

		case HELP:
			printHelp();
			break;

		case GO:
			goRoom(command);
			break;
			
		case DO:
			doSomething(command);
			break;
			
		case DROP:
			dropItem(command);
			break;

		case PICK:
			pickItem(command);
			break;

		case USE:
			useItem(command);
			break;

		case INVENTORY:
			printInventory(command);
			break;

		case QUIT:
			wantToQuit = quit(command);
			break;

		}
		return wantToQuit;
	}




	// implementations of user commands:
	private void printInventory(Command command) {
		System.out.println("You actually carry : " + player.getInventoryContent());
	}

	/**
	 * Print out some help information. Here we print some stupid, cryptic
	 * message and a list of the command words.
	 */
	private void printHelp() {
		System.out.println("You are lost. You are alone. You wander");
		System.out.println("around at the university.");
		System.out.println();
		System.out.println("Your command words are:");
		parser.showCommands();
	}

	/**
	 * Try to go in one direction. If there is an exit, enter the new rooms,
	 * otherwise print an error message.
	 */
	private void goRoom(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			System.out.println("Go where?");
			return;
		}

		String direction = command.getSecondWord();

		// Try to leave current rooms.
		Room nextRoom = currentRoom.getExit(direction);

		if (nextRoom == null) {
			System.out.println("There is no door!");
		} else {
			currentRoom = nextRoom;
			System.out.println(currentRoom.getLongDescription());
		}
	}

	/**
	 * Method allowing the player to drop an item in the current room.
	 * @param command
	 */
	private void dropItem(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			System.out.println("What to drop ?");
			return;
		}

		String itemName = command.getSecondWord();

		// Try to drop item in the current rooms.
		if(player.dropItem(currentRoom,itemName)){
			System.out.println("successfully dropped " + itemName);
		}else{
			System.out.println("You don't carry : " + itemName);
			System.out.println("You actually carry : " + player.getInventoryContent());
		}
	}

	private void pickItem(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			System.out.println("What to pick up ?");
			return;
		}

		String itemName = command.getSecondWord();

		// Try to pick item in the current rooms.
		if(currentRoom.hasItem(itemName)){
			player.pickUp(currentRoom,itemName);
			System.out.println("successfully picked " + itemName);
		}else{
			System.out.println("There is no : " + itemName);
			System.out.println(currentRoom.getItemString());
		}
	}

	private void useItem(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			System.out.println("What to use ?");
			return;
		}

		String itemName = command.getSecondWord();

		// Try to use item in the current rooms.
		if (currentRoom.canUseItem(itemName)) {
			System.out.println(player.use(itemName));
		}
		else {
			System.out.println("Impossible to use this item here.");
		}
	}

	private void doSomething(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			System.out.println("What to do ?");
			return;
		}

		String mehtod = command.getSecondWord();

		// Try to use item in the current rooms.
		System.out.println(currentRoom.doSomething(mehtod));
	}
	/**
	 * "Quit" was entered. Check the rest of the command to see whether we
	 * really quit the game.
	 * 
	 * @return true, if this command quits the game, false otherwise.
	 */
	private boolean quit(Command command) {
		if (command.hasSecondWord()) {
			System.out.println("Quit what?");
			return false;
		} else {
			return true; // signal that we want to quit
		}
	}

}
