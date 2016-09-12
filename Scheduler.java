///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Scheduler.java
// File:             Scheduler.java
// Semester:         CS 367 Fall 2015
//
// Author:           RAGHAV SHARMA
// CS Login:         
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
import java.io.FileReader;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * The scheduler application (main program) 
 * @author Shyamal Anadkat and Raghav Sharma
 *
 */
public class Scheduler {

	private static SchedulerDB schedulerDB = new SchedulerDB();
	private static Scanner scanner = null;

	/**
	 * Main
	 * @param args
	 */
	public static void main(String args[]) {
		if (args.length != 1) {			
			System.err.println("Usage: java Scheduler <resourceListFile>");
			System.exit(1);
		}

		boolean didInitialize = 
				initializeFromInputFile(args[0]);

		if(!didInitialize) {
			System.err.println("Failed to initialize the application!");
			System.exit(1);
		}

		System.out.println("Welcome to the UW-Madison Scheduling Client");

		processUserCommands();
	}

	/**
	 * Initializes DB from the input txt file. 
	 * @param resourceListFile
	 * @return true if DB initialized correctly. 
	 */
	private static boolean initializeFromInputFile(String resourceListFile) {
		/*
		 * You will read the contents of the input file using Java File IO and
		 * then process them to initialize your database
		 */
		String currentLine = "";
		String resourceName = "";
		try {
			Scanner sc = new Scanner(new FileReader(resourceListFile));

			while(sc.hasNextLine()){
				currentLine = sc.nextLine();
				if(currentLine.trim().equals("#Resource:")){

					resourceName = sc.nextLine();
					schedulerDB.addResource(resourceName);
					String eventName = sc.nextLine();

					String start = sc.nextLine();

					String end = sc.nextLine();				

					String org = sc.nextLine();

					String description = sc.nextLine();

					schedulerDB.addEvent(resourceName, convertToTime(start), 
							convertToTime(end), eventName, org, description);

				}
				else{
					String eventName = currentLine;

					String start = sc.nextLine();

					String end = sc.nextLine();

					String org = sc.nextLine();

					String description = sc.nextLine();

					schedulerDB.addEvent(resourceName, convertToTime(start), 
							convertToTime(end), eventName, org, description);
				}
			}
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;		
	}

	/**
	 * Private method to process the user commands (back-end)
	 */
	private static void processUserCommands() {
		scanner = new Scanner(System.in);
		String command = null;		
		do {

			System.out.print("\nPlease Enter a command ([H]elp):");
			command = scanner.next();
			switch(command.toLowerCase()) {							
			case "v":
				processDisplayCommand();
				break;
			case "a":
				processAddCommand();
				break;
			case "d":
				processDeleteCommand();
				break;
			case "q":
				System.out.println("Quit");
				break;
			case "h":
				System.out.println("\nThis scheduler has commands that are "
						+ "entered as a single character indicated in [].\n"+
						"The main commands are to view, add, delete, or quit.\n"
						+ "The first three main commands need a secondary "
						+ "command possibly with additional input.\n" +
						"A secondary command's additional input is described "
						+ "within <>.\n"+
						"Please note that a comma (,) in the add event command "
						+ "represents a need to press\n"+
						"the return character during the command. Also note "
						+ "that times must be in the format\n"+
						"of mm/dd/yyyy,hh:mm.\n"+
						"[v]iew\n"+
						"	[r] = view all resources\n"+
						"	[e] = view all events\n"+
						"	[t] <resource name> = view events in a resource\n"+
						"	[o] <organization name> = view events with an "
						+ "organization\n"+
						"	[n] <start time> <end time> = view events within a "
						+ "time range\n"+
						"	[s] <start time> <end time> <resource name> = view "
						+ "events within in a time range in a resource\n"+
						"[a]dd\n"+
						"	[r] <resource name> = add a resource\n"+
						"	[e] <resource name>, = add an event\n"+
						"		      <start time> <end time> <event name>, \n"+
						"		      <organization name>, \n"+
						"		      <event description>\n"+
						"[d]elete\n"+
						"	[r] <resource name> = delete a resource\n"+
						"	[e] <event start time> <resource name> = delete an "
						+ "event\n"+
						"[q]uit\n");
				break;
			default:
				System.out.println("Unrecognized Command!");
				break;
			}
		} while (!command.equalsIgnoreCase("q"));
		scanner.close();
	}


	/**
	 * Private method to process the display comments to front-end. 
	 */
	private static void processDisplayCommand() {
		String restOfLine = scanner.next();
		Scanner in = new Scanner(restOfLine);
		String subCommand = in.next();

		switch(subCommand.toLowerCase()) {
		//additional input in comments (comma means return)
		case "r": 
			printResourceList(schedulerDB.getResources());
			break;
		case "e": 
			printEventList(schedulerDB.getAllEvents());
			break;
		case "t": // resource,
			printEventList(schedulerDB.getEventsInResource(
					scanner.nextLine().trim()));
			break;
		case "s": // start end resource,
			printEventList(schedulerDB.getEventsInRangeInResource(
					convertToTime(scanner.next()), convertToTime(scanner.next()) 
					, scanner.nextLine().trim()));
			break;
		case "o": // organization
			printEventList(schedulerDB.getEventsForOrg
					(scanner.nextLine().trim()));
			break;
		case "n": // start end
			printEventList(schedulerDB.getEventsInRange
					(convertToTime(scanner.next()), 
							convertToTime(scanner.next())));
			break;
		default: 
			System.out.println("Unrecognized Command!");
		}
		in.close();
	}

	/**
	 * Private method to process the add event commands. 
	 */
	private static void processAddCommand(){
		String restOfLine = scanner.next();
		Scanner in = new Scanner(restOfLine);
		String subCommand = in.next();
		switch(subCommand.toLowerCase()) {
		case "r": //resource
			if(!schedulerDB.addResource(scanner.nextLine().trim())){
				System.out.println("Could not add: no two resources may have "
						+ "the same name.");
			}else{
				System.out.println("Successfully added resource.");
			}
			break;
		case "e": //resource, start end name, organization, description
			try{
				if(!schedulerDB.addEvent(scanner.nextLine().trim(), 
						convertToTime(scanner.next()), 
						convertToTime(scanner.next()), 
						scanner.nextLine().trim(), scanner.nextLine().trim(), 
						scanner.nextLine().trim())){
					System.out.println("Could not add: resource not found.");
				}else{
					System.out.println("Successfully added event.");
				}
			}catch(IntervalConflictException expt){
				System.out.println("Could not add: this event conflicted with "
						+ "an existing event.");
			}
			break;
		default: 
			System.out.println("Unrecognized Command!");
		}
		in.close();
	}

	/**
	 * Private method to process the delete event command. 
	 */
	private static void processDeleteCommand(){
		String restOfLine = scanner.next();
		Scanner in = new Scanner(restOfLine);
		String subCommand = in.next();
		switch(subCommand.toLowerCase()) {
		case "r": // resource
			if(!schedulerDB.removeResource(scanner.nextLine().trim())){
				System.out.println("Could not delete. Resource not found.");
			}else{
				System.out.println("Successfully deleted resource.");
			}
			break;
		case "e":  // resource, start
			if(!schedulerDB.deleteEvent(convertToTime(scanner.next().trim()), 
					scanner.nextLine().trim())){
				System.out.println("Could not delete. Resource not found.");
			}else{
				System.out.println("Successfully deleted event.");
			}
			break;
		default: 
			System.out.println("Unrecognized Command!");
		}
		in.close();
	}

	/**
	 * Prints the resources list. 
	 * @param list
	 */
	private static void printResourceList(List<Resource> list){
		Iterator<Resource> itr = list.iterator();
		if(!itr.hasNext()){
			System.out.println("No resources to display.");
		}
		while(itr.hasNext()){
			System.out.println(itr.next().getName());
		}
	}

	/**
	 * Prints the event list 
	 * @param list
	 */
	private static void printEventList(List<Event> list){
		Iterator<Event> itr = list.iterator();
		if(!itr.hasNext()){
			System.out.println("No events to display.");
		}
		while(itr.hasNext()){
			System.out.println("\n" + itr.next().toString());
		}
	}

	/**
	 * Formats the string to give time in MM/dd/yyyy,HH:mm
	 * @param time
	 * @return formatted time
	 */
	private static long convertToTime(String time){
		long result = 0;
		Format format = new SimpleDateFormat("MM/dd/yyyy,HH:mm");
		try{
			Date date = (Date) format.parseObject(time);
			result = date.getTime()/1000;
		}catch(Exception e){
			System.out.println("Dates are not formatted correctly.  "
					+ "Must be \"MM/dd/yyyy,HH:mm\"");
		}
		return result;
	}

}




