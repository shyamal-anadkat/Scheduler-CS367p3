///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Scheduler.java
// File:             SchedulerDB.java
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
import java.util.*;
/**
 * SchedulerDB class represents a database to store all resources, events etc 
 * and make changes to the database. 
 * @author Shyamal Anadkat and Raghav Sharma
 * @version 1.0 
 *
 */
public class SchedulerDB {

	private List<Resource> resources; //list of resources 

	/**
	 * Constructor; initializes the database.
	 */
	SchedulerDB(){
		resources = new ArrayList<Resource>();;
	}

	/**
	 * Adds a new resource to the database.
	 * @param resource
	 * @return true iff resource successfully added. 
	 * @exception throws IllegalArgumentException if null input. 
	 */
	public boolean addResource(String resource){
		if (resource == null) return false; 
		return resources.add((new Resource(resource)));
	}

	/**
	 * Removes a single resource from the database
	 * @param r
	 * @return true iff resource correctly removed. 
	 * @exception throws IllegalArgumentException if null input. 
	 */
	public boolean removeResource(String r){
		if (resources.isEmpty()) return false; 
		if (r == null || findResource(r) == null ) return false; 
		return resources.remove(findResource(r));
	}

	/**
	 * Adds a single event to resource and updates database. 
	 * @param r
	 * @param start
	 * @param end
	 * @param name
	 * @param organization
	 * @param description
	 * @return true iff event added correctly. 
	 */
	public boolean addEvent(String r, long start, long end, String name, 
			String organization, String description){
		if(organization == null || description == null ||
				name == null || r == null ) {return false;}
		return findResource(r).addEvent(new Event(start,end,name,r,organization,
				description));
	}

	/**
	 * Deletes a single event from resource. 
	 * @param start
	 * @param resource
	 * @return true iff event deleted successfully. 
	 */
	public boolean deleteEvent(long start, String resource){
		if(resource == null) return false; 
		if(findResource(resource) == null) return false; 
		return findResource(resource).deleteEvent(start);
	}

	/**
	 * Finds a resource from database using input name.  
	 * @param r
	 * @return the resource given name and null if resource not found. 
	 */
	public Resource findResource(String r){
		if (r == null) throw new IllegalArgumentException();
		int index = 0;
		boolean found = false; 

		for(int i = 0; i < resources.size(); i++ ){
			if(resources.get(i).getName().equals(r)){
				index = i;
				found = true; 
			}
		}
		if (found) {
			return resources.get(index);
		}
		else
			return null;
	}

	/**
	 * Returns all the resources in the database. 
	 * @return list of current resources in DB. 
	 */
	public List<Resource> getResources(){
		return resources;
	}

	/**
	 * Returns list of events for given resource. 
	 * @param resource
	 * @return list of events and null if null input. 
	 */
	public List<Event> getEventsInResource(String resource){
		if(resource == null) {return null;}
		if (findResource(resource) == null) {return null;}

		List<Event> events = new ArrayList<Event>();
		Iterator<Event> itr = findResource(resource).iterator();	

		while(itr.hasNext())
			events.add(itr.next());

		return events;
	}

	/**
	 * Returns a list of events all resources between given start and end. 
	 * @param start
	 * @param end
	 * @return list of events as described. 
	 */
	public List<Event> getEventsInRange(long start, long end){
		if(start < 0 || end < 0) return null;
		List<Event> eventsInRange = new ArrayList<Event>();
		List<Event> allEvents = getAllEvents();


		for(int i = 0; i < allEvents.size(); i++){
			if(allEvents.get(i).getEnd() > start && 
					allEvents.get(i).getStart() < start)
				eventsInRange.add(allEvents.get(i));

			else if(allEvents.get(i).getStart() < end && start < 
					allEvents.get(i).getStart())
				eventsInRange.add(allEvents.get(i));

			else if(allEvents.get(i).getEnd() > end && 
					allEvents.get(i).getStart() < start)
				eventsInRange.add(allEvents.get(i));

			else if(end > allEvents.get(i).getStart() && start < 
					allEvents.get(i).getStart())
				eventsInRange.add(allEvents.get(i));

		}
		return eventsInRange;
	}	

	/**
	 * Returns a list of events from the given resource between the given
	 * start and end.
	 * @param start
	 * @param end
	 * @param resource
	 * @return list of events as described. 
	 */
	public List<Event> getEventsInRangeInResource(long start, long end, 
			String resource){
		if(resource == null) return null; 
		if(start < 0 || end < 0) return null;
		if (findResource(resource) == null) {return null;}
		List<Event> eventsInResource = getEventsInResource(resource);
		List<Event> eventsInRangeInResource = new ArrayList<Event>();

		for(int i = 0; i < eventsInResource.size(); i++){

			if(eventsInResource.get(i).getEnd() > start && 
					eventsInResource.get(i).getStart() < start)
				eventsInRangeInResource.add(eventsInResource.get(i));

			else if(eventsInResource.get(i).getStart() < end && start <
					eventsInResource.get(i).getStart())
				eventsInRangeInResource.add(eventsInResource.get(i));

			else if(eventsInResource.get(i).getEnd() > end && 
					eventsInResource.get(i).getStart() < start)
				eventsInRangeInResource.add(eventsInResource.get(i));

			else if(end > eventsInResource.get(i).getStart() && start < 
					eventsInResource.get(i).getStart())
				eventsInRangeInResource.add(eventsInResource.get(i));

		}
		return eventsInRangeInResource;
	}

	/** 
	 * Returns list of all events in DB stored for all resources. 
	 * @return list of all events as described. 
	 */
	public List<Event> getAllEvents(){

		List<Event> allEvents = new ArrayList<Event>();
		List<Event> tempList =new ArrayList<Event>();

		for(int i = 0; i < resources.size(); i++ ){
			tempList = (getEventsInResource(resources.get(i).getName()));

			for(int j = 0; j < tempList.size(); j++){
				allEvents.add(tempList.get(j));
			}
			tempList.clear();
		}
		return allEvents;
	}

	/** 
	 * Returns list of events for a given organization. 
	 * @param org
	 * @return list of events as described. 
	 */
	public List<Event> getEventsForOrg(String org){
		if(org==null) return null; 
		List<Event> events = new ArrayList<Event>();
		List<Event> allEvents = getAllEvents();
		boolean found = false; 
		for(int i = 0; i < allEvents.size(); i++){
			if(allEvents.get(i).getOrganization().equals(org)) {
				events.add(allEvents.get(i));
				found = true;
			}
		}	
		if (!found) {
			return null; 
		}
		return events;
	}
}













