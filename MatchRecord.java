import java.time.LocalDateTime;

public class MatchRecord {
	private Character enemyFought;
	private LocalDateTime dateOfMatch;
	private boolean victory;
	
	// record enemy fought, date, and if won from each match
	public MatchRecord(Character enemyFought, boolean victory) {
		this.enemyFought = enemyFought;
		this.dateOfMatch = LocalDateTime.now();
		this.victory = victory;
	}

	public Character getEnemyFought() {
		return enemyFought;
	}
	public LocalDateTime getDateOfMatch() {
		return dateOfMatch;
	}
	public boolean isVictory() {
		return victory;
	}

	
	@Override
	public String toString() {
		return "MatchRecord- \nFought: " + enemyFought.getCharacterName() + "\nTime of Match: " + dateOfMatch + "\nVictorious?: " + victory;
	}
	
	
	
}
