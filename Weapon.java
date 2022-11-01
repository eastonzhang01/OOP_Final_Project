public abstract class Weapon {
	// weapons have a power min and max, when attacking with them you roll a random number between the two integers
	// power increase is a way for you to take a round to increase the power of your weapon to invest for future turns
	// powering up is a way to gain value if the opponent blocks
	private int powerMin;
	private int powerMax;
	private int powerIncrease;
	private String weaponName;
	
	public Weapon(String name, int powerMin, int powerMax, int powerIncrease) {
		this.weaponName = name;
		this.powerMin = powerMin;
		this.powerMax = powerMax;
		this.powerIncrease = powerIncrease;
	}

	public String getWeaponName() {
		return weaponName;
	}

	public void setWeaponName(String weaponName) {
		this.weaponName = weaponName;
	}

	public int getPowerMin() {
		return powerMin;
	}

	public void setPowerMin(int powerMin) {
		this.powerMin = powerMin;
	}

	public int getPowerMax() {
		return powerMax;
	}

	public void setPowerMax(int powerMax) {
		this.powerMax = powerMax;
	}

	public int getPowerIncrease() {
		return powerIncrease;
	}

	public void setPowerIncrease(int powerIncrease) {
		this.powerIncrease = powerIncrease;
	}
	
	// increase weapon min and max damage by powerIncrease value
	public void powerUp() {
		powerMin += powerIncrease;
		powerMax += powerIncrease;
	}
	
	// gets random dmg value between min and max and converts to int so have happy clean numbers
	public int getRealDmg() {
		int realDmg = (int) ((Math.random() * (powerMax+1 - powerMin)) + powerMin);
		//System.out.println(realDmg);
		return realDmg;
	}

	@Override
	public String toString() {
		return "Weapon: " + weaponName + "\nWeapon Damage: " + powerMin + "-" + powerMax + "\nPower Up Value: " + powerIncrease;
	}
	
	
	
}
