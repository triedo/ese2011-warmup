package calendarpackage;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class CalendarAppTest {

	User albert,berta;
	Event party,dentist,exam, test;
	Calendarapp capp;
	ArrayList<Event> list1;
	Iterator<Event> iter;
	
	@Before public void setUp() {

		capp = new Calendarapp();
		albert = new User("albert123","abcd");		//password: abcd
		berta = new User("berta","1234");
		capp.addNewUser(albert);
		capp.addNewUser(berta);
		capp.login(albert, "abcd");
		capp.login(berta, "1234");
		party = new Event("2011", "10.01", "20.00", "2011", "10.02", "03.00", "party");
		dentist = new Event("2011", "10.03", "10.00", "2011", "10.03", "11.00", "dentistVisit");
		exam = new Event("2011", "10.01", "08.15", "2011", "10.01", "10.15", "party");
		albert.addPublicEvent(party, capp);
		albert.addPrivateEvent(dentist);
		berta.addPrivateEvent(exam);
	}
	
	@Test
	public void testUserList() {
		assertTrue(capp.users.contains(albert));
	}
	
	@Test 
	public void testLoginLogout() {
		assertTrue(albert.isLoggedIn);
		capp.logout(albert, "incorrectPassword");
		assertTrue(albert.isLoggedIn);
		capp.logout(albert, "abcd");
		assertFalse(albert.isLoggedIn);
	}
	
	@Test
	public void testUserConstrutor() {
		assertEquals("albert123",albert.username);
	}
	
	@Test
	public void testEvent() {
		
		assertEquals("10.02", party.endday);
		assertEquals(party.toStringYearDay(), "2011-10.01");
		assertTrue(party.isOnAGivenDay("2011", "10.01"));
		assertFalse(party.isOnAGivenDay("2011", "09.30"));
		assertTrue(party.isOnSameDayOrLater("2011", "09.30"));
		assertFalse(party.isOnSameDayOrLater("2011", "10.30"));
	}
	
	@Test
	public void testAddEvents() {
		assertTrue(capp.publicEvents.contains(party));
		assertTrue(albert.privateEvents.contains(dentist));
	}
	
	@Test
	public void testSeeEventsOn1() {
		list1 = albert.SeeEventsOn("2011", "10.01", capp);
		assertTrue(list1.contains(party));
		list1 = albert.SeeEventsOn("2011", "10.03", capp);
		assertTrue(list1.contains(dentist));
	}
	
	@Test
	public void testSeeEventsOn2() {
		list1 = berta.SeeEventsOn("2011", "10.03", capp);
		assertFalse(list1.contains(dentist));				//because dentist is private
	}
	
	@Test
	public void testSeeEventsOn3() {
		list1 = berta.SeeEventsOn("2011", "10.01", capp);
		assertTrue(list1.size()==2);						//contains party and exam
	}
	
	@Test
	public void testIteratorOverEventsStartAt1() {
		iter = albert.iteratorOverEventsStartAt("2013", "01.01", capp);
		assertFalse(iter.hasNext());
	}
	
	@Test
	public void testIteratorOverEventsStartAt2() {
		iter = albert.iteratorOverEventsStartAt("2011", "10.02", capp);
		assertTrue(iter.hasNext());
	}
	
	@Test
	public void testIteratorOverEventsStartAt3() {
		iter = albert.iteratorOverEventsStartAt("2011", "10.01", capp);
		assertTrue(iter.hasNext());
		test = iter.next();
		assertTrue(iter.hasNext());
		test = iter.next();
		assertFalse(iter.hasNext());
		
	}
}






