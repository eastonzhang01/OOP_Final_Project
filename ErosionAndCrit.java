
public interface ErosionAndCrit {
	// classes that implement this have the advantage of having a critical strike chance
	// however they have the disadvantage of "erosion", where the weapon or armor power slowly decreases with use
	// this is meant to encourage more aggressive gameplay
	public void erode();
	public int criticalStrike(int attack);
}
