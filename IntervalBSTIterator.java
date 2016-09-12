///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Scheduler.java
// File:             .java
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
import java.util.*;
/**
 * IntervalBSTIterator class traverses the IntervalBST nodes using in-order 
 * traversal and returns the nodes sorted by their start timestamp in ascending
 * order. 
 * @author Shyamal Anadkat and Raghav Sharma
 *
 */
public class IntervalBSTIterator<K extends Interval> implements Iterator<K> {

	//for keeping track of nodes
	private Stack<IntervalBSTnode<K>> stack = new Stack<>(); 
	private IntervalBSTnode<K> curr;


	/**
	 * Traverses over the IntervalBST node (inorder)
	 * @param root
	 */
	public IntervalBSTIterator(IntervalBSTnode<K> root) {

		curr = root;
	} 
	public boolean hasNext() {
		return (!stack.isEmpty() || curr != null);
	}

	public K next() {

		while (curr != null) {
			stack.push(curr);
			curr = curr.getLeft();
		}

		curr = stack.pop();
		IntervalBSTnode<K> node = curr;
		curr = curr.getRight();

		return node.getKey();

	}

	public void remove() {
		// DO NOT CHANGE: you do not need to implement this method
		throw new UnsupportedOperationException();
	}
}


