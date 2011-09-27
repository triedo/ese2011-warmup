package calendarpackage;

import java.util.ArrayList;

public class Calendarapp {
	
	ArrayList<User> users;
	ArrayList<Event> publicEvents;
	
	Calendarapp(){
		users = new ArrayList<User>();
		publicEvents = new ArrayList<Event>();
	}
	
	public void addNewUser(User user) {
		users.add(user);
	}
	
	public void login(User username, String userpassword){
		if ((users.contains(username))&&(username.password.compareTo(userpassword) == 0))
			username.isLoggedIn = true;
	}
	
	public void logout(User username, String userpassword){
		if ((users.contains(username))&&(username.password.compareTo(userpassword) == 0))
			username.isLoggedIn = false;
	}
}
