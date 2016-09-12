///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Scheduler.java
// File:             IntervalBST.java
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
/**
 * The IntervalBST represents a modified Binary Search Tree which has an event
 * object as its key. It makes use of the start time of event to sort its nodes. 
 * @author Shyamal Anadkat and Raghav Sharma
 *
 */
public class IntervalBST<K extends Interval> implements SortedListADT<K> {
	// declaring class variables
	private IntervalBSTnode<K> root;

	/**
	 * Constructor
	 */
	public IntervalBST() {
		// implementing constructor
		root = null;
	}

	public void insert(K key) {
		try {
			root = insert(root, key);
		} catch (IntervalConflictException e) {
			e.printStackTrace();
		}
	}

	/**
	 * private function to insert data recursively
	 * @param node
	 * @param key
	 * @return
	 * @throws IntervalConflictException
	 */
	private IntervalBSTnode<K> insert(IntervalBSTnode<K> node, K key) throws 
	IntervalConflictException{
		if (node == null)
			return new IntervalBSTnode<K>(key);

		if (node.getKey().equals(key)) {
			throw new IntervalConflictException();
		}

		if (key.compareTo(node.getKey()) < 0){
			node.setLeft(insert(node.getLeft(), key));
			return node;
		}

		else{
			node.setRight(insert(node.getRight(), key));
			return node;
		}
	}

	public boolean delete(K key) {
		if (lookup(key) == null){
			return false;
		}
		else {
			root = delete(root, key);
			return true; 
		}
	}

	/**
	 * Deletes a key value from given IntervalBST. 
	 * @param n
	 * @param key
	 * @return the deleted node. 
	 */
	private IntervalBSTnode<K> delete(IntervalBSTnode<K> n, K key) {
		if (n == null) {
			return null;
		}

		if (key.compareTo(n.getKey()) == 0) {
			// n is the node to be removed
			if (n.getLeft() == null && n.getRight() == null) {
				return null;
			}
			if (n.getLeft() == null) {
				return n.getRight();
			}
			if (n.getRight() == null) {
				return n.getLeft();
			}

			// if we get here, then n has 2 children
			K smallVal = smallest(n.getRight());
			n.setKey(smallVal);
			n.setRight( delete(n.getRight(), smallVal) );
			return n; 
		}

		else if (key.compareTo(n.getKey()) < 0) {
			n.setLeft( delete(n.getLeft(), key) );
			return n;
		}

		else {
			n.setRight( delete(n.getRight(), key) );
			return n;
		}
	}

	/**
	 * Private function to find the smallest corresponding key
	 * @param node
	 * @return
	 */
	private K smallest(IntervalBSTnode<K> node)
	{
		if (node.getLeft() == null) {
			return node.getKey();
		} else {
			return smallest(node.getLeft());
		}
	}


	public K lookup(K key) {
		return lookUp(root, key);
	}

	/**
	 * Private method to search for an element recursively 
	 * @param node
	 * @param key
	 * @return the found node 
	 */
	private K lookUp(IntervalBSTnode<K> node, K key)
	{
		if (node == null) {
			return null;
		}

		if (node.getKey().compareTo(key) == 0) {
			return node.getKey();
		}

		if (key.compareTo(node.getKey()) < 0) {
			// key < this node's key; look in left subtree
			return lookUp(node.getLeft(), key);
		}

		else {
			// key > this node's key; look in right subtree
			return lookUp(node.getRight(), key);
		}
	}

	public int size() {
		return numNodes(root);
	}

	/**
	 * Private method to count the number of nodes in the tree
	 * @param root
	 * @return
	 */
	private int numNodes(IntervalBSTnode<K> root){
		if (root == null)
			return 0;
		else {
			int count = 1;
			count += numNodes(root.getLeft());
			count += numNodes(root.getRight());
			return count;				
		}
	}
	public boolean isEmpty() {
		return root == null;
	}

	/* (non-Javadoc)
	 * Iterator 
	 */
	public Iterator<K> iterator() {
		return new IntervalBSTIterator<K>(root);
	}

}


