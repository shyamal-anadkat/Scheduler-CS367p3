///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Scheduler.java
// File:             Resource.java
// Semester:         CS 367 Fall 2015
//
// Author:           RAGHAV SHARMA
// CS Login:         sharma
// Lecturer's Name:  JAMES SKRENTNY
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     SHYAMAL ANADKAT
// Email:            anadkat@wisc.edu
// CS Login:         shyamal
// Lecturer's Name:  JAMES SKRENTNY
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of 
//                   of any information you find.
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.Iterator;

public class Resource {
	private String name;
	private SortedListADT<Event> events;
	
	Resource(String name){
		this.name = name;
		events = new IntervalBST<Event>();
	}
	
	public String getName(){
		return name;
	}
	
	public boolean addEvent(Event e){
		if(e == null) return false;
		events.insert(e);
		return true;
	}
	
	public boolean deleteEvent(long start){
		return events.delete(new Event(start, start, "", "", "", ""));
	}
	
	public Iterator<Event> iterator(){
		return events.iterator();
	}
	
}
