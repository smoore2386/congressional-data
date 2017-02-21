package cis.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * Data.java
 * 
 * Project #4
 * CS 2334, Section 010
 * April 3, 2011
 * 
 * <p>
 * This class defines the year the census was taken and states included in the
 * census.
 * </p>
 * 
 * @version 1.0
 * 
 */
public class Census implements Serializable {
	/** Eclipse generated serial version id */
	private static final long serialVersionUID = -8722441592866697298L;
	/** The year the Census was taken*/ 	 
	private Integer year;
	/** The list of states in the Census*/
	private LinkedHashMap<Integer, State> states;
	/** The list of available income levels. */
	private ArrayList<String> incomeLevels;

	/**
	 * <p>
	 * Unargumented constructor that defines the default values for the Census 
	 * class.
	 * </p>
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>year</code>, <code>states</code>,   must be initialized.
	 *           </dd>
	 * <dd>POST: <code>incomeRange</code>, <code>households</code> are assigned 
	 *           default values.</dd>
	 * </dt>
	 */
	public Census() {
		this.year         = 0;
		this.states       = new LinkedHashMap<Integer, State>();
		this.incomeLevels = new ArrayList<String>();
	}	// end constructor 
	
	/**
	 * <p>
	 * Constructor that defines the default values for the Census class
	 * </p>
	 * 
	 * @param year the year the census was taken
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>year</code>, <code>states</code> must be initialized.
	 *           </dd>
	 * <dd>POST: <code>year</code>, <code>states</code> are assigned default 
	 *           values.</dd>
	 * </dt>
	 */
	public Census(Integer year) {
		this.year         = year;
		this.states       = new LinkedHashMap<Integer, State>();
		this.incomeLevels = new ArrayList<String>();
	}	// end constructor 
	
	/**
	 * <p>
	 * Constructor that defines the default values for the Census class
	 * </p>
	 * 
	 * @param year the year the census was taken
	 * @param states the states where the census was taken
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>year</code>, <code>states</code> must be initialized.
	 *           </dd>
	 * <dd>POST: <code>year</code>, <code>states</code> are assigned default 
	 *           values.</dd>
	 * </dt>
	 */
	public Census(Integer year, LinkedHashMap<Integer, State> states) {
		this.year         = year;
		this.states       = states;
		this.incomeLevels = new ArrayList<String>();
	}	// end constructor
	
	/**
	 * <p>
	 * Acquires the year the census was taken.
	 * </p>
	 * 
	 * @return The year the census was taken
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>year</code> must be assigned a value.</dd>
	 * <dd>POST: The value that <code>year</code> contains is returned to the 
	 *           requester.</dd>
	 * </dt>
	 */
	public Integer getYear() {
		return year;
	}	// end method

	/**
	 * <p>
	 * Acquires the list of states.
	 * </p>
	 * 
	 * @return The list of states.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>states</code> must be assigned a value.</dd>
	 * <dd>POST: The value that <code>states</code> contains is returned to the
	 *           requester.</dd>
	 * </dt>
	 */
	public LinkedHashMap<Integer, State> getStates(){
		return states;
	}	// end method
	
	/**
	 * <p>
	 * Acquires the state based upon its string representation.
	 * </p>
	 * 
	 * @param state The name of the state to acquire
	 * @return The state with the given name.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  The state must be defined.</dd>
	 * <dd>POST: The state is returned to the requester.</dd>
	 * <dt> 
	 */
	public State getState(String state) {
		return this.states.get(state.hashCode());
	}
	
	/**
	 * <p>
	 * Acquires all available income levels for this census.
	 * </p>
	 * 
	 * @return The array of all the income levels available for the census.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>incomeLevels</code> must be assigned values.</dd>
	 * <dd>POST: The values stored in <code>incomeLevels</code> are returned to 
	 *           the requester.</dd>
	 * </dt>
	 */
	public ArrayList<String> getIncomeLevels() {
		return this.incomeLevels;
	}	// end method
	
	/**
	 * <p>
	 * Sets the income levels that the census will be compared against.
	 * </p>
	 * 
	 * @param incomeLevels The income levels that will be assigned to the data.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>incomeLevels</code> must be initiliazed.</dd>
	 * <dd>POST: <code>incomeLevels</code> is assigned the value passed.</dd>
	 * </dt>
	 */
	public void setIncomeLevels(ArrayList<String> incomeLevels) {
		this.incomeLevels = incomeLevels;
	}	// end method
	
	/**
	 * <p>
	 * A mutator method that changes the <code>year</code> int
	 * </p>
	 *  
	 * @param year  The year when census was taken
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd> PRE: <code>year</code> has its default value.</dd> 
	 * <dd>	POST: <code>year</code> is assigned the value passed.</dd>
	 * </dt>
	 */
	public void setYear(Integer year){
		this.year = year;
	}	// end method
	
	/**
	 * <p>
	 * A mutator method that changes <code>places</code> ArrayList<Place>
	 * </p>
	 *  
	 * @param states the list of places for specified State
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd> PRE: <code>places</code> has its default value.</dd> 
	 * <dd>	POST: <code>places</code> is assigned the value passed.</dd>
	 * </dt>
	 */
	public void setStates(LinkedHashMap<Integer, State> states){
		this.states = states;
	}	// end method
	
	/**
	 * <p>
	 * Adds a new state the the census, based on the overwrite.
	 * </p>
	 * 
	 * @param toAdd The state to be added to the census.
	 * @param overwrite Overwriting of existing values.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>states</code> needs to be assigned a value.</dd>
	 * <dd>POST: The value passed is added to the <code>states</code> map, given
	 *           the approved overwrite value.</dd>
	 * </dt>
	 */
	public boolean addState(State toAdd, boolean overwrite) {
		// Verify state exists
		if(this.stateExists(toAdd)) {
			// Make sure we can overwrite it.
			if(overwrite) {
				this.states.remove(toAdd.getKeyValue());
			} else {
				return false;
			}	// end if
		}	// end if
		
		this.states.put(toAdd.getKeyValue(), toAdd);
		
		return true;
	}	// end method
	
	/**
	 * <p>
	 * Clears the list of states from the list
	 * </p>
	 * 
	 * @return if the states have been cleared
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  All variables (year, population) must be assigned a value.</dd>
	 * <dd>POST: String is compiled and returned to the requester.</dd>
	 * </dt>
	 */
	public boolean clearStates(){
		this.states.clear();
		
		// Verify the list is empty.
		if(this.states.size() > 0) {
			return false;
		} else {
			return true;
		}	// end if
	}	// end method
	
	/**
	 * <p>
	 * removes a state from the list
	 * </p>
	 * 
	 * @return if the state has been removed
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  All variables (year, population) must be assigned a value.</dd>
	 * <dd>POST: String is compiled and returned to the requester.</dd>
	 * </dt>
	 */
	public boolean removeState(State toRemove){
		if(this.stateExists(toRemove)) {
			this.states.remove(toRemove.getKeyValue());
			return true;
		} else {
			return false;
		}	// end if
	}	// end method
	
	/**
	 * <p>
	 * Sees if state already exists in the List of states
	 * </p>
	 * 
	 * @param toFind name of state being searched for
	 * @return true if state exists
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>stateName</code> is compared with the objects of 
	 *           <code>states</code> List
	 * <dd>POST: returns true of the state exists in the list</dd>
	 * </dt>
	 */
	public boolean stateExists(State toFind){
		if(this.states.get(toFind.getKeyValue()) != null) {
			return true;
		} else {
			return false;
		}	// end if
	}	// end method


	/**
	 * <p>
	 * Returns the appropriate string representation for this class for output 
	 * in legible format.
	 * </p>
	 * 
	 * @return The string representation for this class.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  All variables (year, states) must be assigned values.</dd>
	 * <dd>POST: String is compiled and returned to the requester.</dd>
	 * </dt>
	 */
	public String toString(){	
		String output = "\"Geography\";\"Households: Total\"";
		
		Iterator<String> incomes = this.incomeLevels.iterator();
		// Compile the income levels for this census
		while(incomes.hasNext()) {
			output += ";\"Households: Household income; " 
				   +  incomes.next().replace(',', ';') + "\"";
		}	// end if
		
		Iterator<State> states = this.states.values().iterator();
		
		// Cycle through all available states in this census.
		while(states.hasNext()) {
			output += states.next().toString();
		}	// end while
		
		return output;
	}	// end method
}	// end class