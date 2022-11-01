
public class MagicalWeapon extends Weapon implements Overwhelm{
	// magical weapons grows more powerful overtime with more attacks against non-blocking enemies
	private int attackIncrease;

	public MagicalWeapon(String name, int powerMin, int powerMax, int powerIncrease, int attackIncrease) {
		super(name, powerMin, powerMax, powerIncrease);
		this.attackIncrease=attackIncrease;
	}

	public int getAttackIncrease() {
		return attackIncrease;
	}

	public void setAttackIncrease(int attackIncrease) {
		this.attackIncrease = attackIncrease;
	}

	@Override
	public void increaseDMG() {
		setPowerMax(getPowerMax()+attackIncrease);
		System.out.println("Magical weapon max power has been increased.");
	}
	// increase weapon only high roll power

	@Override
	public String toString() {
		return "Weapon Type: Magical\n" + super.toString() + "\nMaximum Attack Power Increase per Attack on non-block: " + attackIncrease;
	}
	
	
}
