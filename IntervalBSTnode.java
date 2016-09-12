///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Scheduler.java
// File:             IntervalBSTnode.java
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
/**
 * Represents a node of IntervalBST that contains object of type Interval 
 * interface as its key. 
 * @author Shyamal Anadkat and Raghav Sharma
 *
 */
class IntervalBSTnode<K extends Interval> {

	// declaring class variables
	private K keyValue;
	private IntervalBSTnode<K> leftChild;
	private IntervalBSTnode<K> rightChild;
	private long maxEnd;

	/**
	 * Constructor with given key value 
	 * @param keyValue
	 */
	public IntervalBSTnode(K keyValue) {
		this.keyValue = keyValue;
		this.leftChild = null;
		this.rightChild = null;
	}

	/**
	 * Second constructor 
	 * @param keyValue
	 * @param leftChild
	 * @param rightChild
	 * @param maxEnd
	 */
	public IntervalBSTnode(K keyValue, IntervalBSTnode<K> leftChild, 
			IntervalBSTnode<K> rightChild, long maxEnd) {
		// implementing constructor
		this.keyValue = keyValue;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.maxEnd = maxEnd;

	}

	/**
	 * Returns the key value for this BST node. 
	 * @return key value. 
	 */
	public K getKey() { 
		return keyValue;
	}

	/**
	 * Returns the left child for this node. 
	 * @return left-child. 
	 */
	public IntervalBSTnode<K> getLeft() { 
		return leftChild;
	}

	/**
	 * Returns the right child for this BST node. 
	 * @return right-child. 
	 */
	public IntervalBSTnode<K> getRight() { 
		return rightChild;
	}

	/**
	 * Returns max end value of intervals in this node's subtree. 
	 * @return 
	 */
	public long getMaxEnd(){
		return maxEnd;
	}

	/**
	 * Sets the key value for this node. 
	 * @param newK
	 */
	public void setKey(K newK) { 
		keyValue = newK;
	}

	/**
	 * Sets new left child for this node. 
	 * @param newL
	 */
	public void setLeft(IntervalBSTnode<K> newL) { 
		leftChild = newL;
	}

	/**
	 * Sets new right child for this node. 
	 * @param newR
	 */
	public void setRight(IntervalBSTnode<K> newR) { 
		rightChild = newR;
	}

	/**
	 * Changes maxEnd to updated max end in subtree. 
	 * @param newEnd
	 */
	public void setMaxEnd(long newEnd) { 
		maxEnd = newEnd;
	}

	/**
	 * Returns start timestamp of this interval. 
	 * @return start timestamp(long) 
	 */
	public long getStart(){ 
		return this.keyValue.getStart();
	}

	/** 
	 * Returns end timestamp of this interval. 
	 * @return end timestamp (long)
	 */
	public long getEnd(){
		return this.keyValue.getEnd();
	}

	/**
	 * Returns the key value for this BST node. 
	 * @return key value. 
	 */
	public K getData(){
		return this.getKey();
	}

}


