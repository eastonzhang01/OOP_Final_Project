# Welcome to Easton's Final Project

### Introduction
Hello, for my ITP265 final project, I decided to create a rock, paper, scissors type of game where it is just you versus the computer.
Just like rock, paper, scissors, there will be multiple rounds. Each round, you and the computer will make a choice to either attack, defend, or power up.
This choice will be made blind and after both sides have chosen, the resulting outcome will be shown.
The game was balanced around the fact that blocking counters attacking, powering up counters blocking, and attacking counters powering up.
Once you or your opponent have dipped below 0 health, the game will be over with either you winning, the computer winning, or a tie. 
For more information, refer to the UML image. 

### Weapons
Weapons are one of the main components of the combat in this game. Weapons determine your attack power and your power up value.
Each weapon has a minimum attack power and a maximum attack power. When you choose to attack in a round, a random number within the min and max is chosen(inclusive).
When choosing to power up, your weapon's min and max power are increased. There are two types of weapons: physical and magical.

##### Physical Weapons
Physical weapons have a few special traits. First, if an opponent blocks a physical weapon's attack, the weapon will be eroded and its min and max attack power will decrease.
On the other hand, each attack has a chance to critically strike, which will multiply the weapon's damage. 
In terms of design philosophy, physical weapons are meant to support an aggressive playstyle.
With a lower power-up value and high base min and max power you are encouraged to attack frequently for more chances at critically striking. 

##### Magical Weapons
Magical weapons on the other hand have a ramp up effect each attack. Each time you attack wtih a magic weapon, the weapon's max attack power increases. 
In terms of design philosophy, magical weapons lend themselves to a slower playstyle. 
With a lower base attack power, but better ramp effects. Players who use magical weapons will only get stronger as the game goes on. 

### Armour
Armour is the second main component of the combat. Armour determines the number of blocks you get, the percent reduction in damage you take if you get hit, 
and the retaliation damage you inflict if you successfully block an attack.
Regardless of your opponent's move, your number of blocks will decrease if you chose to block. There are two types of armours: heavy and light.

##### Heavy Armour
Similar to magical weapons, if you successfully block an attack, your retaliation damage will increase.
Heavy armour lends itself to a slower grindier playstyle as heavy armour tends to have more blocks but lower base retaliation damage. 

##### Light Armour
Similar to physical weapons, light armour has an erosion and critical strike component.
Upon successfully blocking an attack, light armour's % reduction damage is reduced, so you will take more damage on the next successful block. 
On the other hand, if you successfully block an attack, you have the chance to critically strike, multiplying your retaliation damage against the opponent.
Light Armour lends itself to a fast, aggressive playstyle. Light Armours will have lower base blocks and % reduction, but will allow you to hit back harder.

### Account Settings
Every account has an email, username, password, and player name. All valid accounts are written in the AccountInformation.tsv file. 
When signing in, you must enter a correct username and password from one of the accounts listed. New accounts need to be manually added to the tsv file.
You can change your player name for an account once you have signed in with a specific account. 
Each match you play with a specific palyer name, will be logged. You can then choose to look through your match history.
Your match history will be written to a txt file with name based on your player name. 

### Enemies
Each round you start, a random enemy will be chosen from Archmage, Warlock, Paladin, and Rogue. These four enemies will each have their own unique weapons and armours.
Furthermore, each enemy will have a unique combination of weapons and armours such that there is an emey with each combination. 
Enemies will choose their move by random, but won't block if they have 0 blocks left. 

### Getting started
To run the program, 
```
javac ProgramTester.java
java ProgramTester
```
Once the program is running, you will need to sign in to start playing. Once signed-in, you can play against a random enemy using different predefined weapons and armours.
After playing a few matches, you can choose to have your match history written to a txt file which will show the enemey you fought, the date, and whether you won for each match.
To change accounts, you can sign out and sign back in with a different account. 

Thank you for checking out my program!


