import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class LoadGame {
	private BFF helper;
	private Random random;
	private HashSet<Account> accountsSet;
	private Account user;
	private boolean loggedIn;
	
	public LoadGame() {
		helper = new BFF();
		random = new Random();
		user = null;
		loggedIn = false;
		accountsSet = new HashSet<>();
		readAccountInfo();
	}
	
	public void readAccountInfo() {
		// read in pre-made file with different accounts
		String fileName = "AccountInformation.tsv"; // changed from src/
		// File reader makes so each line is an element in an arraylist
		ArrayList<String> fileData = FileReader.readFile(fileName);
		// for each line of data, the data is parsed so the individual components can be changed to an object
		// then add to a set of accounts
		helper.printFancy("Available Account Information");
		for(int i=1; i<fileData.size(); i++) {
			Account u = parseData(fileData.get(i));
			accountsSet.add(u);
			System.out.println(u);
		}
		
		/*
		for(String s: fileData) {
			helper.printFancy(s);
		}
		*/
	}
	private Account parseData(String fileLine) {
		Scanner sc = new Scanner(fileLine);
		sc.useDelimiter("\t");
		// have tab separated file, and each piece of data is an element for an account object
		String email = sc.next();
		String userName = sc.next();
		String password = sc.next();
		String playerName = sc.next();
		Account a = new Account(email, userName, password, playerName);
		// create account and return to be added to set
		return a;
	}

	public void run() {
		helper.printFancy("Welcome to Easton's Final Project Game!");
		for(GameMenu g: GameMenu.values()) {
			// use enum to display game menu
			System.out.println(g.ordinal() + ": " + g.getDisplayString());
		}
		// ask user for choice from 0 to enum options
		int choice = helper.inputInt("What would you like to do?", 0, GameMenu.values().length-1);
		// 5 is quit, so keep looping until quit
		while(choice != 5) {
			helper.printFancy("You picked " + choice + " which is: " + GameMenu.values()[choice]);
			
			// switch statement allows being able to go through each case
			switch(GameMenu.values()[choice]) {
			// First option, and almost must is to have player sign in
			case SIGN_IN:
				signIn();
				break;
			// user must be signed in, to play a match, as the match history is saved to account object
			case PLAY_MATCH:
				if(loggedIn) {
					play();
				}
				else {
					printMustBe();
				}
				break;
			// user must be signed into view match history as the data is tied to each account
			case VIEW_MATCH_HISTORY:
				if(loggedIn) {
					user.printMatchHistory();
					writeMatchHistory();
				}
				else {
					printMustBe();
				}
				break;
			// simple choice to just change player name
			case CHANGE_PLAYERNAME:
				if(loggedIn) {
					String nameChange = helper.inputWord("Change Player Name to: ");
					user.setName(nameChange);
					System.out.println("Name Change successful!");
				}
				else {
					printMustBe();
				}
				break;
			// very simple sign out method if want to change accounts
			case SIGN_OUT:
				signOut();
				break;
			case QUIT:
				break;
			default:
				System.out.println("Not implemented yet");
				break;
			}
			
			// allow make more choices at end so can break out of while loop
			for(GameMenu g: GameMenu.values()) {
				System.out.println(g.ordinal() + ": " + g.getDisplayString());
			}
			choice = helper.inputInt("What would you like to do?", 0, GameMenu.values().length-1);
		}
		// say thanks once user finishes
		helper.printFancy("Thank you for stopping by!!!");
	}
	
	public void writeMatchHistory() {
		// create filename for match history based on player name
		String filename = user.getName() + "MatchHistory.txt";
		int count = 1;
		try {
			// try to open, write, and close file all in one try
			FileWriter writer = new FileWriter(filename);
			writer.write("Player Name: " + user.getName() + "\n");
			writer.write("________________________________\n");
			for(MatchRecord m: user.getMatchHistory()) {
				// write into file in same style print onto console, show enemy, date, and victory or not
				writer.write(count + ": " + m.toString() + "\n");
				writer.write("________________________________\n");
				count++;
			}
			writer.close();
			// not most efficient way to do it; however, it gets the job done
			helper.printFancy("Match History successfully written to file: " + filename);
		}
		catch(IOException e) {
			System.out.println("IOException caught: " + e.getMessage());
			// catch any errors in case
		}
	}
	
	public void printMustBe() {
		helper.printFancy("Must be logged In!");
	}
	
	public void signIn() {
		// can't log in if someone else already logged in 
		if(!loggedIn) {
			// make user type in user name first, and user name must match one of the accounts read in from the file
			boolean gotUserName = false;
			Account attemptLogin = null;
			// always go into the while loop to ask for a String
			while(gotUserName == false) {
				String userName = helper.inputWord("Please input a user name");
				// check if the user input is equal to any of the names in the account set, which is from the accounts in the externally read in file
				for(Account a: accountsSet) {
					if(a.getUserName().equals(userName)) {
						gotUserName = true;
						// if there is a match, save that account as the one the user is trying to log in to
						attemptLogin = a;
					}
				}
				if(gotUserName == false) {
					helper.printFancy(userName + " is not a registered user name");
				}
			}
			
			// make user type in password and it must match password for the account it put in the user name for
			int passwordAttempts = 3;
			boolean gotPassword = false;
			
			while(passwordAttempts>0 && gotPassword==false) {
				String password = helper.inputWord("Please input password (" + passwordAttempts + " attempts left)");
				if(attemptLogin.getPassword().equals(password)) {
					// password must match only the specific username's password not any in the set
					gotPassword = true;
				}
				// only get three attempst and if use all, then no log in
				if(!attemptLogin.getPassword().equals(password)) {
					passwordAttempts --;
				}
			}
			// if user passed by not using all attempts, that means they successfully logged in
			if(passwordAttempts >0) {
				user = attemptLogin;
				loggedIn = true;
				helper.printFancy("Sign-In Successful!");
			}
			// if user passes while loop by using up their 3 attempts, no log in
			if(passwordAttempts<=0) {
				helper.printFancy("Login Unsuccessful. Failed Password too many times.");
			}
			
		}
		else {
			helper.printFancy("An account is already logged in.");
		}
	}
	
	public void signOut() {
		// signing out just makes so user no longer exists
		if(loggedIn) {
			user = null;
			loggedIn = false;
			helper.printFancy("Log-Out Successful!");
		}
		else {
			helper.printFancy("Error in Log-out");
		}
		
	}

	public void play() {
		// creating all the weapons and armors available to the player
		PhysicalWeapon spear = new PhysicalWeapon("Spear", 21, 26, 8, 5, 1.5, .20);
		MagicalWeapon wand = new MagicalWeapon("Wand", 14, 18, 10, 3);
		HeavyArmor plateMail = new HeavyArmor("Plate", 5, 1.0, 6, 2);
		LightArmor leatherArmor = new LightArmor("Leather", 4, .90, 10, .04, 2.0, .25);
		
		HashMap<String, Weapon> weaponDetails = new HashMap<>();
		HashMap<String, Armor> armorDetails = new HashMap<>();
		
		weaponDetails.put(spear.getWeaponName(), spear);
		weaponDetails.put(wand.getWeaponName(), wand);
		armorDetails.put(plateMail.getArmorName(), plateMail);
		armorDetails.put(leatherArmor.getArmorName(), leatherArmor);
		
		// creating all the weapons and armors available to the enemies
		HeavyArmor warlockArmor = new HeavyArmor("Veil", 5, .95, 9, 3);
		LightArmor archmageArmor = new LightArmor("Robe", 3, .88, 12, .03, 2.2, .25);
		HeavyArmor paladinArmor = new HeavyArmor("Justicar", 7, 1.0, 5, 1);
		LightArmor rogueArmor = new LightArmor("Cloack", 3, .85, 15, .05, 2.0, .33);
		
		MagicalWeapon warlockWeapon = new MagicalWeapon("Scepter", 15, 17, 8, 4);
		MagicalWeapon archmageWeapon = new MagicalWeapon("Staff", 12, 18, 10, 5);
		PhysicalWeapon paladinWeapon = new PhysicalWeapon("Hammer", 23, 26, 7, 4, 1.4, .15);
		PhysicalWeapon rogueWeapon = new PhysicalWeapon("Dagger", 20, 25, 7, 6, 2.0, .25);
		
		ArrayList<Armor> enemyArmors = new ArrayList<>();
		ArrayList<Weapon> enemyWeapons = new ArrayList<>();
		
		enemyArmors.add(warlockArmor);
		enemyArmors.add(archmageArmor);
		enemyArmors.add(paladinArmor);
		enemyArmors.add(rogueArmor);
		
		enemyWeapons.add(warlockWeapon);
		enemyWeapons.add(archmageWeapon);
		enemyWeapons.add(paladinWeapon);
		enemyWeapons.add(rogueWeapon);
		
		// map makes it easy to iterate through and show the options
		helper.print("Weapon Options: ");
		for(Weapon w: weaponDetails.values()) {
			helper.printFancy(w.toString());
		}
		// get user choice, unfortunatley it is case sensitive
		String userWeaponChoice = helper.inputWord("Please choose a Weapon (Spear or Wand).", weaponDetails);
		// display the armor choices for player
		helper.print("Armor Options: ");
		for(Armor a: armorDetails.values()) {
			helper.printFancy(a.toString());
		}
		// player choose armor, but is case sensitive
		String userArmorChoice = helper.inputWord("Please choose an Armor (Plate or Leather).", armorDetails);
		
		// create new character based on user inputs
		Character yuh = new Character(armorDetails.get(userArmorChoice), weaponDetails.get(userWeaponChoice), user.getName());
		
		// random int generated for enemy
		helper.printFancy("Assigning Random Enemy...");
		int enemyNum = random.nextInt(0, Enemy.values().length);
		// then choose from arrayList the specific weapons, armor, and names for the enemy
		// keep same int for all since order is all the same (archmage shouldn't be using a physical weapon)
		Character sampleEnemy = new Character(enemyArmors.get(enemyNum), enemyWeapons.get(enemyNum), Enemy.values()[enemyNum].getName());
		
		helper.printFancy("Starting Game...");
		boolean quit = false;
		
		// print user and enemy important stats
		helper.printFancy(yuh.toString());
		helper.printFancy(sampleEnemy.toString());
		
		// play until user chooses to quit, enemy dies, or player dies
		while(yuh.getHealth()>0 && sampleEnemy.getHealth()>0 && quit == false){
			// get user input
			int userMove = helper.inputInt("0: Attack\n1: Block\n2: Power Up", 0, 2);
			// set current move to user input
			yuh.setCurrentMove(userMove);
			// enemy current move is based off random int
			int enemyMove = random.nextInt(3);
			// make so enemy doesn't block when it is out of blocks
			if(sampleEnemy.getPlayerArmor().getNumDefends()<=0) {
				enemyMove = random.nextInt(2);
				if(enemyMove == 1) {
					enemyMove = 2;
				}
			}
			//setting enemy current move to the randomly generated number
			sampleEnemy.setCurrentMove(enemyMove);
			
			// both player and enemy get a "combat" based on moves chosen
			yuh.combat(sampleEnemy);
			sampleEnemy.combat(yuh);
			
			// show important stats after each round
			helper.printFancy(yuh.toString());
			helper.printFancy(sampleEnemy.toString());
			quit = helper.inputBoolean("Would you like to quit? True/False");
		}
		
		boolean isVictorious = false;
		// if player health goes to 0, or player quits, they loose
		if(yuh.getHealth()<=0 || quit==true) {
			helper.printFancy("DEFEAT!!!");
		}
		// player only wins when get enemy to 0
		if(sampleEnemy.getHealth()<=0) {
			helper.printFancy("VICTORY!!!");
			isVictorious = true;
		}
		// still counts as lose when get a tie
		if(yuh.getHealth()<=0 && sampleEnemy.getHealth()<=0) {
			helper.printFancy("IT IS A TIE!!! (Will Count as Defeat)");
			isVictorious = false;
		}
		
		// new match record is created from match, and then added to the user match history
		MatchRecord match = new MatchRecord(sampleEnemy, isVictorious);
		user.addMatch(match);
	}
}
