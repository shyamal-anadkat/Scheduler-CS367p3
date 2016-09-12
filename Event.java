///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Scheduler.java
// File:             Event.java
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
import java.text.SimpleDateFormat;
/**
 * Event class represents events to be held in.
 * @author Shyamal Anadkat and Raghav Sharma
 *
 */
public class Event implements Interval{

	// declaring variables
	private long start;
	private long end;
	private String name;
	private String resource;
	private String organization;
	private String description;
	private static String dateFormat = "MM/dd/yyyy,HH:mm";
	private static SimpleDateFormat simpleDateFormat = new 
			SimpleDateFormat(dateFormat);
	private int mul = 1000;

	/**
	 * Constructor for an event 
	 * @param start
	 * @param end
	 * @param name
	 * @param resource
	 * @param organization
	 * @param description
	 */
	Event(long start, long end, String name, String resource, String 
			organization, String description){
		// implementing constructor
		if(organization == null || description == null ||
				name == null || resource == null 
				|| start < 0 || end < 0 ) 
			throw new IllegalArgumentException();
		this.start = start;
		this.end = end;
		this.name = name;
		this.resource = resource;
		this.organization = organization;
		this.description = description;
	}

	@Override
	public long getStart(){
		return start;
	}

	@Override
	public long getEnd(){
		return end;
	}

	/**
	 * Returns the name of the event. 
	 * @return event name. 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the resource for the event.
	 * @return string - resource. 
	 */
	public String getResource() {
		return resource;	
	}

	/**
	 * Returns the organization name of the event. 
	 * @return organization name. 
	 */
	public String getOrganization(){
		return organization;
	}

	/**
	 * Returns the event description.
	 * @return description. 
	 */
	public String getDescription() {
		return description;
	}

	@Override
	public int compareTo(Interval i) {
		if(i==null) throw new IllegalArgumentException();
		if(this.getStart() == i.getStart()) {
			return 0; 
		}
		if (this.getStart() < i.getStart())
			return -1;
		else 
			return 1;
	}


	/**
	 * Returns true if two events have same start times.
	 * @param e
	 * @return true iff two events have same start timestamp; false otherwise.
	 */
	public boolean equals(Event e) {
		if(e == null) throw new IllegalArgumentException();
		if (this.getStart() == e.getStart())
			return true;
		return false;
	}

	@Override
	public boolean overlap(Interval i) {

		if(this.getEnd() > i.getStart() && this.getStart() < i.getStart())
			return true;
		else if(this.getStart() < i.getEnd() && i.getStart() < this.getStart())
			return true;
		else if(this.getEnd() > i.getEnd() && this.getStart() < i.getStart())
			return true;
		else if(i.getEnd() > this.getStart() && i.getStart() < this.getStart())
			return true;
		return false;
	}

	@Override
	public String toString(){

		return (this.getName() + "\nBy: " + this.getOrganization() + "\nIn: "
				+ this.getResource() + "\nStart: " + 
				simpleDateFormat.format(this.getStart()*mul) + "\nEnd:"
				+ " " + simpleDateFormat.format(this.getEnd()*mul) + 
				"\nDescription: " + this.getDescription());
	}
}


