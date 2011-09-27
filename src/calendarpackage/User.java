package calendarpackage;

import java.util.ArrayList;
import java.util.Iterator;

// represents an user with his calendar
public class User {

	String username;
	String password;
	Boolean isLoggedIn;
	ArrayList<Event> privateEvents;
	
	User(String name, String password){
		username =name;
		this.password = password;
		isLoggedIn = false;
		privateEvents = new ArrayList<Event>();
	}
	
	public String addPrivateEvent(Event event) {
		if (isLoggedIn){
				privateEvents.add(event);
				return "done";
		}
		else
			return "not logged in";
	}
	
	public String addPublicEvent(Event event, Calendarapp app) {
		if (isLoggedIn){
				app.publicEvents.add(event);
				return "done";
		}
		else
			return "not logged in";
	}
	
	public ArrayList<Event> SeeEventsOn(String year, String day, Calendarapp app){
		ArrayList<Event> list = new ArrayList<Event>();
		
		if (isLoggedIn){
			Iterator<Event> iter1 = privateEvents.iterator();
			Iterator<Event> iter2 = app.publicEvents.iterator();
			while (iter1.hasNext()){
				Event temp = iter1.next();
				if (temp.isOnAGivenDay(year, day))
						list.add(temp);
			}
			while (iter2.hasNext()){
				Event temp2 = iter2.next();
				if (temp2.isOnAGivenDay(year, day))
						list.add(temp2);
			}
		}
			
		else {
			System.out.println("you are not logged in");
		}
		return list;
	}
	
	public Iterator<Event> iteratorOverEventsStartAt(String year, String day, Calendarapp app){
		ArrayList<Event> list = new ArrayList<Event>();
		
		if (isLoggedIn){
			Iterator<Event> iter1 = privateEvents.iterator();
			Iterator<Event> iter2 = app.publicEvents.iterator();
			while (iter1.hasNext()){
				Event temp = iter1.next();
				if (temp.isOnSameDayOrLater(year, day))
						list.add(temp);
			}
			while (iter2.hasNext()){
				Event temp2 = iter2.next();
				if (temp2.isOnSameDayOrLater(year, day))
						list.add(temp2);
			}
		}
			
		else {
			System.out.println("you are not logged in");
		}
		return list.iterator();
	}
	
	
}
