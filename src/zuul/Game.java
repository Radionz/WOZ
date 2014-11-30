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
 * @author Nicolas Sarroche, Dorian Blanc
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
		lessons = new Lesson[10];
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
			//True false question
			// IO.addToFileByName(String.valueOf(k), "Question " + k +" ? T", IO.PossibleFiles.POO_QUESTION.getPath());
			IO.addToFileByName("1", "Can 'private String name;' be access out of the class ? F",IO.PossibleFiles.POO_QUESTION.getPath());
			IO.addToFileByName("2", "If I declare 'private String a;' can I do 'a = 3' ? F", IO.PossibleFiles.POO_QUESTION.getPath());
			IO.addToFileByName("3", "Does it compile :\n private String s\nprivate String b; ? F", IO.PossibleFiles.POO_QUESTION.getPath());

			IO.addToFileByName("4", "If I declare a class A and B which extends A can I do 'A = new B();' ? T", IO.PossibleFiles.POO_QUESTION.getPath());
			IO.addToFileByName("5", "If I declare a class A and B which extends A can I do 'B = new A();' ? F", IO.PossibleFiles.POO_QUESTION.getPath());
			IO.addToFileByName("6", "If a class B extends a class A can this constructor be right \n" +
					"B(){\n super(); \n} ? T", IO.PossibleFiles.POO_QUESTION.getPath());

			IO.addToFileByName("7", "In 'private String s = new String(\"toto\");', String is the dynamic type of s ? T", IO.PossibleFiles.POO_QUESTION.getPath());
			IO.addToFileByName("8", "In 'private List l = new ArrayList<String>()', String is the dynamic type of s ? F", IO.PossibleFiles.POO_QUESTION.getPath());
			IO.addToFileByName("9", "In 'private List l = new ArrayList<String>()', ArrayList is the dynamic type of s ? F", IO.PossibleFiles.POO_QUESTION.getPath());

			IO.addToFileByName("10", "Can A class 'public class Toto' can be instanciated ? T", IO.PossibleFiles.POO_QUESTION.getPath());
			IO.addToFileByName("11", "Can a class 'public abstract class Toto' be instanciate ? F", IO.PossibleFiles.POO_QUESTION.getPath());
			IO.addToFileByName("12", "An interface can be a static type ? T", IO.PossibleFiles.POO_QUESTION.getPath());

			IO.addToFileByName("13", "A class A can inherit of 2 other classes ? F", IO.PossibleFiles.POO_QUESTION.getPath());
			IO.addToFileByName("14", "A class A can inherit a class B if it call it's constructor by 'super()' ? T", IO.PossibleFiles.POO_QUESTION.getPath());
			IO.addToFileByName("15", "Two class can't inherit the same class ? F", IO.PossibleFiles.POO_QUESTION.getPath());

			IO.flushJSON();

			IO.addToFileByName("1", "Welcome in this first POO Lesson.. Today we will learn about Java syntax.. If you write 'private' the field won't be accessible out of the class.. If you declare a variable of a certain type, you can't affect it another type.. Don't forget to add ';' at the end of every line of code.. This is it for this POO lesson, see you for another one..", IO.PossibleFiles.POO_LESSON.getPath());
			IO.addToFileByName("2", "Welcome in this second POO Lesson.. Today let me introduce you inheritance.. You can declare a class B which inherit another class A.. you write it this way 'A a= new B()'.. But this 'B = new A()' is wrong.. You have to add the instruction 'super();' in the constructor of B for the code to compile..  This is it for this POO lesson, see you for another one..", IO.PossibleFiles.POO_LESSON.getPath());
			IO.addToFileByName("3", "Welcome in this third POO Lesson.. Today we will learn about static type.. Basically, we call 'static type' the type of the declaration.. if you declare 'A a= new C()', the 'static type' of a is A..  This is it for this short POO lesson about static type, see you for another one..", IO.PossibleFiles.POO_LESSON.getPath());
			IO.addToFileByName("4", "Welcome in this fourth POO Lesson.. Today we will learn about some properties of instance.. Some class can be not instanciate.. if you set the constructor as 'private' of if you declare the class as 'abstract' you won't be able to instanciate an object for this class.. And just before you leave, remember that, an interface can be the static type for an object.. This is it for this POO lesson, see you for another one..", IO.PossibleFiles.POO_LESSON.getPath());
			IO.addToFileByName("5", "Welcome in this fifth POO Lesson.. Today we will learn more about inheritance!. We learned that a class B can inherit from another class A.. By doing this an infinite number of class can inherit of class A.. But be careful, you can't inherit from two different classes !. Don't forget to add the instruction 'super()' to the constructor of the class.. This it if for this POO lesson..", IO.PossibleFiles.POO_LESSON.getPath());
			IO.flushJSON();

			IO.addToFileByName("1", "Welcome in this lesson of Biology !. Each type of protein is usually sent to a particular part of the cell. An important part of cell biology is the investigation of molecular mechanisms by which proteins are moved to different places inside cells or secreted from cells.\n Most proteins are synthesized by ribosomes in the rough endoplasmic reticulum (RER). Ribosomes contain the nucleic acid RNA, which assembles and joins amino acids to make proteins. They can be found alone or in groups within the cytoplasm as well as on the RER. This is it for this lesson on Cells", IO.PossibleFiles.OTHER_LESSON.getPath());
			IO.addToFileByName("2", "Welcome in this lesson of Astrophysics !.The basic notion of an intra-universe wormhole is that it is a compact region of spacetime whose boundary is topologically trivial, but whose interior is not simply connected. Formalizing this idea leads to definitions such as the following, taken from Matt Visser's Lorentzian Wormholes.\n" +
					"\n" +
					"    If a Minkowski spacetime contains a compact region Ω, and if the topology of Ω is of the form Ω ~ R x Σ, where Σ is a three-manifold of the nontrivial topology, whose boundary has topology of the form ∂Σ ~ S2, and if, furthermore, the hypersurfaces Σ are all spacelike, then the region Ω contains a quasipermanent intra-universe wormhole.\n", IO.PossibleFiles.OTHER_LESSON.getPath());
			IO.addToFileByName("3", "Welcome in this lesson of cooking !.    Heat oven to 180C/160C fan/gas 4 and grease 3 x 20cm sandwich tins, lining the bases with baking parchment and greasing the parchment too.\n" +
					"    Beat the butter and sugar in a large bowl with an electric whisk until light and fluffy. Add the vanilla extract and the egg whites, a little at a time, beating until fully combined before adding more. Mix together the flour, cornflour and baking powder. Add the dry ingredients in 3 additions, alternating with the buttermilk. Divide the batter between the tins and level the tops.\n" +
					"    Bake for 25-30 mins or until a skewer inserted into the middle comes out clean. Allow the cakes to cool in the tins for 10 mins, then turn out onto a wire rack, peeling off the parchment. Cool completely.\n" +
					"    To make the buttercream, put the egg whites and sugar in a big bowl (the bowl of your tabletop mixer, if you have one) with the vanilla seeds and set over a pan of gently simmering water. Lightly whisk until the sugar has fully dissolved – you can test this by dipping two fingers into the bowl and rubbing them together; if you can’t feel any grains of sugar, the mixture is ready. Remove the bowl from the heat and keep whisking until a thick meringue has formed. Continue whisking until the meringue has cooled to room temperature, then slowly add in the butter, 1 tbsp at a time. By the time all the butter has been incorporated, the mixture should have transformed into a silky-smooth buttercream. If it hasn’t, continue to whisk until it does. If it still refuses to thicken, it may be the mixture is still too warm, so chill for 10 mins, then continue whisking. Add the vanilla extract and mix to combine.\n" +
					"    To assemble the cake, place a sponge on a cake board or serving plate and top with a thin layer of buttercream. Repeat with the remaining cake layers and finish by spreading the remaining buttercream over the top and sides of the cake (see below). To get a smooth finish, use the edge of a palette knife, and drag carefully around the sides of the cake, smoothing out the buttercream. To decorate the cake, press the sides of it with the edible polka dot sprinkles creating a full border at the bottom with less and less the further up the cake you go. Best served within 2 days of baking, but the cake will keep for up to 4 days.\n This is it for this lesson for how to bake a cake.", IO.PossibleFiles.OTHER_LESSON.getPath());
			IO.addToFileByName("4", "Welcome in this lesson of physics !.The theory of relativity transformed theoretical physics and astronomy during the 20th century. When first published, relativity superseded a 200-year-old theory of mechanics created primarily by Isaac Newton.\n" +
					"\n" +
					"In the field of physics, relativity improved the science of elementary particles and their fundamental interactions, along with ushering in the nuclear age. With relativity, cosmology and astrophysics predicted extraordinary astronomical phenomena such as neutron stars, black holes, and gravitational waves.", IO.PossibleFiles.OTHER_LESSON.getPath());
			IO.addToFileByName("5","Welcome to this lesson of statistics .. Mathematical statistics is the application of mathematics to statistics, which was originally conceived as the science of the state — the collection and analysis of facts about a country: its economy, land, military, population, and so forth. Mathematical techniques used for this include mathematical analysis, linear algebra, stochastic analysis, differential equations, and measure-theoretic probability theory", IO.PossibleFiles.OTHER_LESSON.getPath());
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
		for(int i = 0; i < 15; i++){
			questions[i] = new Question(i+1);
			if(i < 5){
				lessons[i] = new Lesson(true, i+1);
			}else if(i < 10){
				lessons[i] = new Lesson(false, i-4);
			}
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
			
		case ANSWER:
			answerQuestion(command);
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
			System.out.println(currentRoom.getItemString());
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
			System.out.println(currentRoom.getActionString());
			return;
		}

		String mehtod = command.getSecondWord();

		// Try to use item in the current rooms.
		System.out.println(currentRoom.doSomething(mehtod));
	}
	
	private void answerQuestion(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know...
			System.out.println("What to answer ? ('true' or 'false')");
			return;
		}

		String answer = command.getSecondWord();

		if((currentRoom instanceof ExamRoom)){
			if (((ExamRoom) currentRoom).isExamInProcess() && (answer.equals("true") || answer.equals("false"))) {
				System.out.println(((ExamRoom) currentRoom).answerQuestion(answer));
			}
		}
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
