///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Scheduler.java
// File:             SortedListADT.java
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
 * The Sorted List ADT represents a sorted list, that is, a list in which the
 * elements are kept in order from smallest to largest (based on the ordering
 * given by compareTo). You do not need to handle duplicates.
 * 
 * DO NOT CHANGE THIS FILE
 *  
 * @author CS 367
 * @param <K> a class which implements the Comparable<K> interface
 */
public interface SortedListADT<K> {
    
    /**
     * Inserts the given key into the Sorted List.
     * 
     * @param key the key value to insert into the Sorted List
     */
    void insert(K key);
    
    /**
     * Deletes the given key from the Sorted List.  If the key is in the Sorted
     * List, the key is deleted and true is returned.  If the key is not in the
     * Sorted List, the Sorted List is unchanged and false is returned.
     * @param key the key value to delete from the Sorted List
     * @return true if the deletion is successful (i.e., the key was in the 
     * Sorted List and has been removed), false otherwise (i.e., the key was not
     * in the Sorted List)
     */
    boolean delete(K key);
    
    /**
     * Searches for the given key in the Sorted List and returns it.  If the 
     * key is not in the Sorted List, null is returned.
     * @param key the key to search for
     * @return the key from the Sorted List, if it is there; null if the key is
     * not in the Sorted List
     */
    K lookup(K key);
    
    /**
     * Returns the number of items in the Sorted List.
     * @return the number of items in the Sorted List
     */
    int size();
    
    /**
     * Returns true if and only if the Sorted List is empty.
     * @return true if the Sorted List is empty, false otherwise
     */
    boolean isEmpty();
    
    /**
     * Returns an iterator over the Sorted List that iterates over the items in 
     * the Sorted List from smallest to largest.
     * @return an iterator over the Sorted List
     */
    Iterator<K> iterator();
}
