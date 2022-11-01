import java.util.ArrayList;

public class Account {
	
	private String email;
	private String userName;
	private String password;
	private String name;
	private ArrayList<MatchRecord> matchHistory;
	private BFF helper;
	
	public Account(String email, String userName, String password, String name) {
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.matchHistory = new ArrayList<>();
		this.helper = new BFF();
	}
	
	// General Getters and Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<MatchRecord> getMatchHistory() {
		return matchHistory;
	}
	public void setMatchHistory(ArrayList<MatchRecord> matchHistory) {
		this.matchHistory = matchHistory;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	// simple add match record to account, matches are tied to account
	public void addMatch(MatchRecord m) {
		matchHistory.add(m);
	}
	
	// simple way to print match history to console
	public void printMatchHistory() {
		int count =1;
		helper.printFancy("Account: " + name);
		for(MatchRecord m: matchHistory) {
			helper.printFancy(count + ": " + m.toString());
			count++;
		}
	}

	@Override
	public String toString() {
		return "Account [email=" + email + ", userName=" + userName + ", password=" + password + ", name=" + name + "]";
	}
	
	
}
