
public enum GameMenu {
	SIGN_IN("Sign in to start playing!"),
	PLAY_MATCH("Play a match and test your skills!"),
	VIEW_MATCH_HISTORY("View your previous encounters."),
	CHANGE_PLAYERNAME("Change your in-game player name"),
	SIGN_OUT("Sign out of the current account"),
	QUIT("Quit");
	// menu ENUM for different options
	
	private String description;
	
	private GameMenu(String description){
		this.description = description;
	}
	public String getDisplayString(){
		return this.description;
	}
}
