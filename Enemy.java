
public enum Enemy {
	WARLOCK("Enemy Warlock"),
	ARCHMAGE("Enemy ArchMage"),
	PALADIN("Enemy Paladin"),
	ROGUE("Enemy Rogue");
	
	private String enemyName;
	
	private Enemy(String n) {
		this.enemyName = n;
	}

	public String getName() {
		return this.enemyName;
	}
}
