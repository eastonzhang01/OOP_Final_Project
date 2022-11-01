
public class PhysicalWeapon extends Weapon implements ErosionAndCrit{
	// physical weapons have an errosion effect, but a chance to crit to balance
	// physical weapons encourage user to hit hard, but few times
	private int erosionAttack;
	private double critDmgIncrease;
	private double critChance;
	
	public PhysicalWeapon(String name, int powerMin, int powerMax, int powerIncrease, int erosionAttack, double critDmgIncrease, double critChance) {
		super(name, powerMin, powerMax, powerIncrease);
		this.erosionAttack = erosionAttack;
		this.critDmgIncrease = critDmgIncrease;
		this.critChance = critChance;
	}
	
	public int getErosionAttack() {
		return erosionAttack;
	}

	public void setErosionAttack(int erosionAttack) {
		this.erosionAttack = erosionAttack;
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
		setPowerMin(getPowerMin() - erosionAttack);
		setPowerMax(getPowerMax() - erosionAttack);
		System.out.println("Weapon power has been eroded.");
	}
	// both weapon min and max power are eroded

	@Override
	public int criticalStrike(int attack) {
		int critAttack = (int)Math.round(attack * critDmgIncrease);
		return critAttack;
		// if get a crit, new damage is just supposed to damage * multiplyer
	}

	@Override
	public int getRealDmg() {
		double critStrike = Math.random();
		int returnDmg = super.getRealDmg();
		if(critStrike <= critChance) {
			System.out.println("CRITICAL STRIKE!");
			returnDmg = criticalStrike(super.getRealDmg());
		}
		return returnDmg;
		// need this get real damage for case that user gets a critical strike
	}

	@Override
	public String toString() {
		return "Weapon Type: Physical\n" + super.toString() + "\nAttack Power Decrease per Attack on block: " + erosionAttack + "\nCritical Strike Chance: " + critChance*100 + "%" + "\nCritical Strike Damage Multiplyer: " + critDmgIncrease*100 + "%";
	}
	
	

}
