
public class LightArmor extends Armor implements ErosionAndCrit{
	// Light armor's percent reduction goes down with each successful block
	// but light armor has a % chance to do massive retaliation damage when it successfully blocks
	private double erosionReduction;
	private double critDmgIncrease;
	private double critChance;

	public LightArmor(String armorName, int numDefends, double percentReduction, int retaliationDmg, double erosionReduction, double critDmgIncrease, double critChance) {
		super(armorName, numDefends, percentReduction, retaliationDmg);
		this.erosionReduction = erosionReduction;
		this.critDmgIncrease = critDmgIncrease;
		this.critChance = critChance;
	}

	public double getErosionReduction() {
		return erosionReduction;
	}

	public void setErosionReduction(double erosionReduction) {
		this.erosionReduction = erosionReduction;
	}

	public double getCritDmgIncrease() {
		return critDmgIncrease;
	}

	public void setCritDmgIncrease(double critDmgIncrease) {
		this.critDmgIncrease = critDmgIncrease;
	}

	public double getCritChance() {
		return critChance;
	}

	public void setCritChance(double critChance) {
		this.critChance = critChance;
	}

	@Override
	public void erode() {
		setPercentReduction(getPercentReduction() - erosionReduction);
		System.out.println("Armor Defense has been eroded");
		// weapon power decreases
	}

	@Override
	public int criticalStrike(int attack) {
		int critDmg = (int)Math.round(attack*critDmgIncrease);
		return critDmg;
		// take in what was supposed to be the damage and multiply it
	}
	
	@Override
	public int getTrueRetaliationDmg() {
		double critStrike = Math.random();
		int returnDmg = super.getTrueRetaliationDmg();;
		if(critStrike <= critChance) {
			System.out.println("PERFECT COUNTER!");
			returnDmg = criticalStrike(super.getTrueRetaliationDmg());
		}
		return returnDmg;
	}
	// chance that user gets a critical strike, so can't just use the normal getter methods

	@Override
	public String toString() {
		return "Armor Type: Light\n" + super.toString() + "\nDecrease in Block Strength on Successful Block: " + erosionReduction*100 + "%" + "\nPerfect Counter Chance: " + critChance*100 + "%" + "\nPerfect Counter Multiplyer: " + critDmgIncrease*100 + "%";
	}
	
	

}
