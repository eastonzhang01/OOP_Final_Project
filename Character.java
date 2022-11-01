
public class Character {
	
	private double health;
	private Armor playerArmor;
	private Weapon playerWeapon;
	private int currentMove;
	private String characterName;
	
	public Character(Armor playerArmor, Weapon playerWeapon, String characterName) {
		this.health = 100;
		this.playerArmor = playerArmor;
		this.playerWeapon = playerWeapon;
		this.currentMove = 0;
		this.characterName = characterName;
	}
	
	public double getHealth() {
		return health;
	}
	public void setHealth(double health) {
		this.health = health;
	}
	public Armor getPlayerArmor() {
		return playerArmor;
	}
	public void setPlayerArmor(Armor playerArmor) {
		this.playerArmor = playerArmor;
	}
	public Weapon getPlayerWeapon() {
		return playerWeapon;
	}
	public void setPlayerWeapon(Weapon playerWeapon) {
		this.playerWeapon = playerWeapon;
	}
	public int getCurrentMove() {
		return currentMove;
	}
	public void setCurrentMove(int currentMove) {
		this.currentMove = currentMove;
	}
	public String getCharacterName() {
		return characterName;
	}
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	// easy method to lower health by some damage
	public void lowerHealth(int dmgTaken) {
		health -= dmgTaken;
	}
	
	public void attack(Character enemy) {
		System.out.println(characterName + " chose to attack.");
		int enemyDmgTaken;
		// if enemy is blocking
		if(enemy.getCurrentMove()==1 && enemy.playerArmor.getNumDefends()>0) {
			// enemy takes dmg = (1-reduction%)*player weapon dmg
			enemyDmgTaken = (int)Math.round((1-enemy.getPlayerArmor().getPercentReduction())*playerWeapon.getRealDmg());
			// if player weapon is physical their weapon gets eroded (attack power gets reduced)
			if(playerWeapon.getClass() == PhysicalWeapon.class) {
				PhysicalWeapon c = (PhysicalWeapon)playerWeapon;
				c.erode();
			}
		}
		// if enemy is not blocking
		else {
			// enemy takes dmg equal to weapon dmg
			enemyDmgTaken = playerWeapon.getRealDmg();
			// if player weapon is magical they get a power up
			if(playerWeapon.getClass() == MagicalWeapon.class && enemy.getCurrentMove()!=1) {
				MagicalWeapon f = (MagicalWeapon)playerWeapon;
				f.increaseDMG();
			}
		}
		// account for damages taken and show to user
		enemy.lowerHealth(enemyDmgTaken);
		System.out.println(enemy.characterName + " took " + enemyDmgTaken + " damage.");
	}
	
	public void block(Character enemy) {
		System.out.println(characterName + " chose to block.");
		
		// player needs to have at least 1 block left to block
		if(playerArmor.getNumDefends()>0) {
			// if enemy is attacking they always take retaliation dmg
			if(enemy.getCurrentMove()==0) {
				int retalDmg = getPlayerArmor().getTrueRetaliationDmg();
				// lower enemy health by retaliation dmg if enemy attack into block
				enemy.lowerHealth(retalDmg);
				System.out.println(characterName + " successfully blocked the attack!");
				System.out.println(enemy.characterName + " took " + retalDmg + " retaliation damage.");
				// if successfully block attack, some effect happens based on armor type
				if(playerArmor.getClass() == HeavyArmor.class) {
					// heavy armor gets bonus retaliation damage
					HeavyArmor h = (HeavyArmor)playerArmor;
					h.increaseDMG();
				}
				if(playerArmor.getClass() == LightArmor.class) {
					// light armor gets debuffed as their % reduction of incoming damage is reduced (take more damage on blocks from now on)
					LightArmor l = (LightArmor)playerArmor;
					l.erode();
				}
			}
			// whenver you block # of blocks goes down
			playerArmor.setNumDefends(playerArmor.getNumDefends()-1);
		}
		else {
			// if out of blocks nothing happens
			System.out.println("Out of Blocks!");
		}
	}
	
	public void powerUp() {
		// powering up just makes weapon stronger
		System.out.println(characterName + " chose to power up their weapon.");
		playerWeapon.powerUp();
	}
	
	// combat method connects the player moves to the different methods created before
	public void combat(Character enemy) {
		if(currentMove == 0) {
			attack(enemy);
		}
		if(currentMove == 1) {
			block(enemy);
		}
		if(currentMove==2) {
			powerUp();
		}
	}

	@Override
	public String toString() {
		return characterName + " has " + health + " health remaining.\n" + "Weapon Damage: " + playerWeapon.getPowerMin() + "-" + playerWeapon.getPowerMax()
		+ "\nArmor Retaliation Damage: " + playerArmor.getRetaliationDmg() + "\nArmor Damage Reduction: " + playerArmor.getPercentReduction()*100 + "%\nArmor blocks: " + playerArmor.getNumDefends();
	}
	
	
	
}
