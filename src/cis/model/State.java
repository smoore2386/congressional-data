package cis.model;

import java.io.Serializable; 
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.swing.JLabel;

/**
 * State.java
 * 
 * Project #4
 * CS 2334, Section 010
 * April 3, 2011
 * 
 * <p>
 * This class defines the properties that every State will contain.
 * </p>
 * 
 * @version 1.0
 * 
 */
public class State extends JLabel implements Serializable, Comparable<State>{
	/** Eclipse generated serial version id */
	private static final long serialVersionUID = -2875172696362991422L;
	/**the name of the state */
	private String name;
	/** the list of places associated with the state */
	private LinkedHashMap<Integer, Place> places;
	/** the total population of the state */
	private Integer totalPopulation;
	/** the congressional apportionment related to the state
	 * 	initialized to 1 as all states have 1 seat */
	private Integer apportion = 1;

	/**
	 * <p>
	 * Unargumented constructor that defines the default values for the State 
	 * class.
	 * </p>
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>name</code>, <code>places</code>,   must be initialized.
	 *           </dd>
	 * <dd>POST: <code>incomeRange</code>, <code>households</code> are assigned 
	 *           default values.</dd>
	 * </dt>
	 */
	public State() {
		this.name = "Unnamed State";
		this.places = new LinkedHashMap<Integer, Place>();
		this.apportion = 0;
	}	// end method

	/**
	 * <p>
	 * Argumented constructor that defines the default values for the State
	 * class.
	 * </p>
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>name</code>, <code>places</code>,   must be initialized.
	 *           </dd>
	 * <dd>POST: <code>name</code> is assigned value passed, and 
	 *           <code>places</code> are assigned default values.</dd>
	 * </dt>
	 */
	public State(String name){
		this.name = name;
		this.places = new LinkedHashMap<Integer, Place>();
		
		this.setText(name);
	}	// end constructor

	/**
	 * <p>
	 * Argumented constructor that defines the values for the State class
	 * </p>
	 * @param name The name of the state
	 * @param places The list of places associated with the state
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>name</code>, <code>places</code>, are assigned values and
	 *           must be initialized.</dd>
	 * <dd>POST: <code>name</code>, <code>places</code> are assigned values 
	 *           passed.</dd>
	 * </dt>
	 */
	public State(String name, LinkedHashMap<Integer, Place> places, Integer apportion){
		this.name   = name;
		this.places = places;
		this.apportion = 1;
		this.setText(name);
	}	// end constructor

	/**
	 * <p>
	 * A mutator method that changes the <code>name</code> String
	 * </p>
	 *  
	 * @param name  The name of the state
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd> PRE: <code>name</code> has its default value.</dd> 
	 * <dd>	POST: <code>name</code> is assigned the value passed.</dd>
	 * </dt>
	 */
	public void setName(String name) {
		this.name = name;
	}	// end method

	/**
	 * <p>
	 * Acquires the name of the state.
	 * </p>
	 * 
	 * @return The name of this state.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>name</code> must be assigned a value.</dd>
	 * <dd>POST: The value that <code>name</code> contains is returned to the 
	 *           requester.</dd>
	 * </dt>
	 */
	public String getName() {
		return name;
	}	// end method

	/**
	 * <p>
	 * A mutator method that changes the <code>places</code> 
	 * </p>
	 *  
	 * @param places  The places that coincide with the state
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd> PRE: <code>places</code> has its default value.</dd> 
	 * <dd>	POST: <code>places</code> is assigned the value passed.</dd>
	 * </dt>
	 */
	public void setPlaces(LinkedHashMap<Integer, Place> places) {
		this.places = places;
	}	// end method

	/**
	 * <p>
	 * Acquires the places associated with this state.
	 * </p>
	 * 
	 *@return a Map with the places .
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>places</code> must be assigned a value.</dd>
	 * <dd>POST: The value that <code>places</code> contains is returned to the 
	 *           requester.</dd>
	 * </dt>
	 */
	public LinkedHashMap<Integer, Place> getPlaces() {
		return places;
	}	// end method

	/**
	 * <p>
	 * Adds a place to this state
	 * </p>
	 * 
	 * @param newPlace The name of the state
	 * @param overwrite Whether the data should be overwritten if it exists.
	 * 
	 * @return returns true if completed successfully
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>name</code> and <code>data</code> must be initialized and
	 *           hold values.</dd>
	 * <dd>POST: The <code>name</code> and <code>data</code> are added to the 
	 *           list of places
	 * </dt>
	 */
	public boolean addPlace(Place newPlace, boolean overwrite) {
		// Verify the map exists
		if(this.placeExists(newPlace)) {
			// Verify user overwrite for existing data
			if(overwrite) {
				this.places.remove(newPlace.getKeyValue());
			} else {
				// Data exists, and user does not want to overwrite that data.
				return false;
			}	// end if
		}	// end if

		// Adds a place to the places map
		places.put(newPlace.getKeyValue(), newPlace);
		return true;
	}	// end method

	/**
	 * <p>
	 * Clears all the places from the <code>places</code> map.
	 * </p>
	 * 
	 * @return Successful completion of the clearing of this map.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>name</code> and <code>data</code> must be initialized and
	 *           hold values.</dd>
	 * <dd>POST: The <code>name</code> and <code>data</code> are added to the 
	 *           list of places.</dd>
	 * </dt>
	 */
	public boolean clearPlaces() {
		// Clear the places map
		this.places.clear();

		// Verify the map is actually empty.
		if(this.places.size() > 0) {
			return false;
		} else {
			return true;
		}	// end if
	}	// end method

	/**
	 * <p>
	 * Removes a place from the <code>places</code> map.
	 * </p>
	 * 
	 * @param toRemove The value to be removed.
	 * @return Success of removal.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>places</code> must be assigned a value.</dd>
	 * <dd>POST: The value passed by the method is removed from the 
	 *           <code>places</code> list and true is returned or if the value 
	 *           does not exist, false is returned.</dd>
	 * </dt>
	 */
	public boolean removePlace(Place toRemove) {
		// Verify the value exists for removal.
		if(this.placeExists(toRemove)) {
			this.places.remove(toRemove.getKeyValue());
			return true;
		} else {
			return false;
		}	// end if
	}	// end method

	/**
	 * <p>
	 * Acquires a place based off its name.
	 * </p>
	 * 
	 * @param place The name of the place to acquire.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  The place must be defined.</dd>
	 * <dd>POST: The place is returned to the requester.</dd>
	 * </dt>
	 */
	public Place getPlace(String place) {
		return this.getPlaces().get(place.hashCode());
	}	// end method

	/**
	 * <p>
	 * Checks if place exists in <code>places</code> 
	 * </p>
	 * 
	 * @param place   the place being checked if exists in <code>places</code>
	 * @return 	   true if place exists 
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>place</code>  must be initialized and hold values.</dd>
	 * <dd>POST: The <code>place</code> is checked against <code>places<code/> 
	 *           and returns true if exists
	 * </dt>
	 */
	public boolean placeExists(Place place){
		// Verify the value exists.
		if(this.places.get(place.getKeyValue()) != null) {	
			return true;
		} else {
			return false;
		}	// end if
	}	// end method

	/**
	 * <p>
	 * Checks this state for equivalence against the value passed.
	 * </p>
	 * 
	 * @param toCompare The value that will be compared against this class.
	 * @return The equivalence check to this class.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>name</code> must be assigned a value.</dd>
	 * <dd>POST: The hashCode of <code>name</code> is checked against the 
	 *           hashCode of the value passed.</dd>
	 * </dt>
	 */
	public boolean equals(State toCompare) {
		return this.getKeyValue().equals(toCompare.getKeyValue());
	}	// end method

	/**
	 * <p>
	 * Acquire the hash code representation for this class.
	 * </p>
	 * 
	 * @return The hash code representation of this class based on the 
	 *          <code>name</code>.
	 *          
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>name</code> must be assigned a value.</dd>
	 * <dd>POST: The hash value of <code>name</code> is returned to the
	 *           requester.</dd>
	 * </dt>
	 */
	public Integer getKeyValue() {
		return new Integer(this.name.hashCode());
	}	// end method

	/**
	 * <p>
	 * Returns the appropriate string representation for this class for output 
	 * in legible format.<br/>
	 * Format: "PlaceName, StateName";"householdcount";"incomecount";...
	 * </p>
	 * 
	 * @return The string representation for this class.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  All variables must be assigned values.</dd>
	 * <dd>POST: String is compiled and returned to the requester.</dd>
	 */
	public String toString(){
		String output = "";

		// Iterate through places arrayList
		Iterator<Place> iter = this.places.values().iterator();

		while(iter.hasNext()){
			Place place = iter.next();
			output += "\n\"" + place.getName() + ", " + this.getName() + "\"";
			output += place.toString();
		}	// end while

		return output;
	}	// end method

	/**
	 * <p>
	 * Calculates the total population of the state
	 * </p>
	 * 
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>totalPopulation</code> has a value.</dd>
	 * <dd>POST: <code>totalPopulation</code> is assigned the value of the population of
	 * 			 each of its respective places</dd>
	 */
	public void calculateTotalPop(){
		
		//acquire the values of the places
		Collection<Place> c = places.values();

		Iterator<Place> iter = c.iterator();

		Integer totalPop = 0;
		//loop for each place population
		for(int i =0; iter.hasNext(); i++){
			totalPop += iter.next().getPopulation();
		}
		this.totalPopulation = totalPop;
	}
	/**
	 * <p>
	 * Sets the state population.
	 * </p>
	 * 
	 *@param totalPopulation population to be altered
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  The <code>totalPopulation</code> must be defined.</dd>
	 * <dd>POST: The <code>totalPopulation</code> takes the value passed</dd>
	 * </dt>
	 */
	public void setTotalPopulation(Integer totalPopulation) {
		this.totalPopulation = totalPopulation;
	}
	/**
	 * <p>
	 * Acquires the state population.
	 * </p>
	 * 
	 * 
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  The <code>totalPopulation</code> must be defined.</dd>
	 * <dd>POST: The <code>totalPopulation</code> is returned to the requester.</dd>
	 * </dt>
	 */
	public Integer getTotalPopulation() {
		return this.totalPopulation;
	}
	/**
	 * <p>
	 * Acquires a place based off its name.
	 * </p>
	 * 
	 * @param apportion The apportionment to be set
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE: <code>apportion</code> is defined a value</dd>
	 * <dd>POST: The value of <code>apportion</code> take the value passed</dd>
	 * </dt>
	 */

	public void setApportion(Integer apportion) {
		this.apportion = apportion;
	}
	/**
	 * <p>
	 * Acquires the apportionment.
	 * </p>
	 * 
	 * 
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  The apportionment must be defined.</dd>
	 * <dd>POST: The apportionment is returned to the requester.</dd>
	 * </dt>
	 */
	public Integer getApportion() {
		return apportion;
	}
	/**
	 * <p>
	 * Adds a congressional seat to the state
	 * </p>
	 * 
	 * @return nextSeat state that will get next congressional seat
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  the state who gets next seat needs to be calculated</dd>
	 * <dd>POST: The state with highest <code>nextSeat</code> will acquire seat</dd>
	 * </dt>
	 */
	public Integer calculateApportion(){
		//current number of seats within state
		int n = getApportion();
		//calculate total population of state
		calculateTotalPop();
		int aofN = (int) (this.totalPopulation/(Math.sqrt(n*(n+1))));
		//which state will gain next seat
		int nextSeat = (int)(Math.sqrt(n/n+2)*(aofN));

		return nextSeat;
	}
	/**
	 * <p>
	 * Adds a congressional seat to the state
	 * </p>
	 * 
	 * 
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>apportionment</code> is defined</dd>
	 * <dd>POST: The <code>apportionment</code> gains one seat</dd>
	 * </dt>
	 */
	public void addSeat(){
		this.apportion+=1;
	}

	/**
	 * <p>
	 * Compares the states for priority sorting
	 * </p>
	 * 
	 * @param obj the state that is being compared for sorting
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  a list of states is unsorted</dd>
	 * <dd>POST: The list of states is sorted by priority for apportionment.</dd>
	 * </dt>
	 * @Override
	 */
	public int compareTo(State obj) {
		if(calculateApportion() > obj.calculateApportion()){

			return -1;
		}
		else if(calculateApportion() < obj.calculateApportion()){

			return 1;
		}
		else 

			return 0;

	}

}//end class
