
public class HeavyArmor extends Armor implements Overwhelm{
	// implement overwhelm means Heavy Armor's retaliation dmg increases with each successful block
	private int retaliationIncrease;

	public HeavyArmor(String armorName, int numDefends, double percentReduction, int retaliationDmg, int retaliationIncrease) {
		super(armorName, numDefends, percentReduction, retaliationDmg);
			this.retaliationIncrease = retaliationIncrease;
	}

	public int getRetaliationIncrease() {
		return retaliationIncrease;
	}

	public void setRetaliationIncrease(int retaliationIncrease) {
		this.retaliationIncrease = retaliationIncrease;
	}

	@Override
	public void increaseDMG() {
		setRetaliationDmg(getRetaliationDmg() + retaliationIncrease);
		System.out.println("Armor Retaliation Damage Increased!");
	}
	// retaliation dmg increase method

	@Override
	public String toString() {
		return "Armor Type: Heavy\n" + super.toString() + "\nRetaliation Damage Increase on Successful Block: " + retaliationIncrease;
	}
	
	

}
