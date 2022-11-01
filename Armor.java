
public abstract class Armor {
	
	private String armorName;
	private int numDefends;
	private double percentReduction;
	private int retaliationDmg;
	// unlike the weapon, retaliation damage on armors is a constant number
	
	// defending will mitigate most-100% of damage and if the enemy attacked into a block, cause the enemy to take damage instead
	// however to make it so block isn't jsut he go to, each armor has a limited number of availabe blocks each game
	// so while blocking is strong, you want to time it perfectly
	
	public Armor(String armorName, int numDefends, double percentReduction, int retaliationDmg) {
		this.armorName = armorName;
		this.numDefends = numDefends;
		this.percentReduction = percentReduction;
		this.retaliationDmg = retaliationDmg;
	}

	public String getArmorName() {
		return armorName;
	}

	public void setArmorName(String armorName) {
		this.armorName = armorName;
	}

	public int getNumDefends() {
		return numDefends;
	}

	public void setNumDefends(int numDefends) {
		this.numDefends = numDefends;
	}

	public double getPercentReduction() {
		return percentReduction;
	}

	public void setPercentReduction(double percentReduction) {
		this.percentReduction = percentReduction;
	}

	public int getRetaliationDmg() {
		return retaliationDmg;
	}

	public void setRetaliationDmg(int retaliationDmg) {
		this.retaliationDmg = retaliationDmg;
	}
	
	// need this method so can override on the lightArmor because need to factor in critical strike
	public int getTrueRetaliationDmg() {
		return retaliationDmg;
	}

	
	@Override
	public String toString() {
		return "Armor Name: " + armorName + "\nNumber of Blocks: " + numDefends + "\nPercent Attack Damage Reduced on Block: " + percentReduction*100 + "%" + "\nRetaliation Damage on Block: " + retaliationDmg;
	}
	
	
	
}
