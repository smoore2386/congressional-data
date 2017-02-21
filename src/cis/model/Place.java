package cis.model;
 
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.swing.JLabel;

/**
 * Place.java
 * 
 * Project #4
 * CS 2334, Section 010
 * April 3, 2011
 * 
 * <p>
 * This class defines the properties that every place will contain.
 * </p>
 * 
 * @version 1.0
 * 
 */
public class Place extends JLabel implements Serializable{

	/** Eclipse generated serial version id */
	private static final long serialVersionUID = -5990666740804838457L;
	/** the name of the place */
	private String name;
	/** the total population of the place */
	private Integer population;
	/** the data associated with this place*/
	private LinkedHashMap<Integer, Data> data;
	/** the income ranges that need to be defined for each place. */
	private String[] incomeRanges = {
			"Less than $10,000", 
			"$10,000 to $14,999", 
			"$15,000 to $19,999", 
			"$20,000 to $24,999", 
			"$25,000 to $29,999",
			"$30,000 to $34,999",
			"$35,000 to $39,999", 
			"$40,000 to $44,999", 
			"$45,000 to $49,999",
			"$50,000 to $59,999",
			"$60,000 to $74,999",
			"$75,000 to $99,999",
			"$100,000 to $124,999",
			"$125,000 to $149,999",
			"$150,000 to $199,999",
			"$200,000 or more"
	};

	/**
	 * <p>
	 * Unargumented constructor that defines the default values for the Place 
	 * class.
	 * </p>
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>name</code>, <code>data</code>,   must be initialized.
	 * </dd>
	 * <dd>POST: <code>name</code>, <code>data</code> are assigned default 
	 *           values.</dd>
	 * </dt>
	 */
	public Place() {
		this.name = "Unknown Place";
		this.data = new LinkedHashMap<Integer, Data>();
		this.populateData();
	}	// end constructor
	
	/**
	 * <p>
	 * Argumented constructor that defines the default values for the Place 
	 * class.
	 * </p>
	 * 
	 * @param name the name of the place
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>name</code>, <code>data</code>,   must be initialized.
	 * </dd>
	 * <dd>POST: <code>name</code> is assigned value passed, and 
	 *           <code>data</code> is assigned a default value.</dd>
	 * </dt>
	 */
	public Place(String name) {
		this.name = name;
		this.data = new LinkedHashMap<Integer, Data>();
		this.populateData();
	}	// end constructor
	
	/**
	 * <p>
	 * Argumented constructor that defines the values for the Place class
	 * </p>
	 * 
	 * @param name The name of the place
	 * @param data The data associated with the place
	 *  
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>name</code>, <code>data</code>, are assigned values and 
	 *           must be initialized.</dd>
	 * <dd>POST: <code>name</code>, <code>data</code> are assigned values 
	 *           passed.</dd>
	 * </dt>
	 */
	public Place(String name, LinkedHashMap<Integer, Data> data) {
		this.name = name;
		this.data = data;
	}	// end constructor
	
	/**
	 * <p>
	 * Acquires the name of the place
	 * </p>
	 * 
	 * @return the name of the place
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
	 * Acquires the data associated with this place.
	 * </p>
	 * 
	 *@return a Map with the places .
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>data</code> must be assigned a value.</dd>
	 * <dd>POST: The value that <code>data</code> contains is returned to the 
	 *           requester.</dd>
	 * </dt>
	 */
	public LinkedHashMap<Integer, Data> getData(){
		return data;
	}	// end method
	
	/**
	 * <p>
	 * A mutator method that changes the <code>name</code> String
	 * </p>
	 *  
	 * @param name The name of the place
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd> PRE: <code>name</code> has its default value.</dd> 
	 * <dd>	POST: <code>name</code> is assigned the value passed.</dd>
	 * </dt>
	 */
	public void setName(String name){
		this.name = name;
	}	// end method
	
	/**
	 * <p>
	 * A mutator method that changes the <code>year</code> int
	 * </p>
	 *  
	 * @param data The year when demographic was taken
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd> PRE: <code>data</code> has its default value.</dd> 
	 * <dd>	POST: <code>data</code> is assigned the value passed.</dd>
	 * </dt>
	 */
	public void setData(LinkedHashMap<Integer, Data> data){
		this.data = data;
	}	// end method
	
	/**
	 * <p>
	 * Adds a new Data to this place
	 * </p>
	 * 
	 * @param data      The data associated with this place.
	 * @param overwrite User requests to overwrite the data.
	 * @return returns true if completed successfully
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>data</code> must be initialized and hold values.</dd>
	 * <dd>POST: The <code>data</code> of the place is  added to the list of 
	 *           data
	 * </dt>
	 */
	public boolean addData(Data data, boolean overwrite){
		// Verify that the data exists
		if(this.dataExists(data)) {
			// Verify user action to overwrite data
			if(overwrite) {
				// User wants to overwrite data, remove old entry
				this.data.remove(data.getKeyValue());
			} else {
				// User does not wish to overwrite this value
				return false;
			}	// end if
		}
		
		// Add the object to the map
		this.data.put(data.getKeyValue(), data);
		
		return true;
	}	// end method
	
	/**
	 * <p>
	 * Removes a data entry from the map.
	 * </p>
	 * 
	 * @param toRemove The value that will be removed from the data map.
	 * @return Success of removal.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>data</code> must contain values.</dd>
	 * <dd>POST: An attempt is made to remove the value from <code>data</code>.
	 *           </dd>
	 * </dt>
	 */
	public boolean removeData(Data toRemove) {
		// Verify the value exists before trying to remove.
		if(this.dataExists(toRemove)) {
			this.data.remove(toRemove.getKeyValue());
			return true;
		} else {
			return false;
		}	// end if
	}	// end method
	
	/**
	 * <p>
	 * Acquires the total number of households for this Place object.
	 * </p>
	 * 
	 * @return The total number of households in the Data map.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>data</code> must be assigned a value.</dd>
	 * <dd>POST: The number of households is calculated based upon the addition 
	 *           of all the households stored by each income level in the
	 *           <code>data</code> map.</dd>
	 * </dt>
	 */
	public Integer getTotalHouseholds() {
		// Default to return a total of 0 households in case Data stores nothing
		int totalHouseholds = 0;
		
		// Iterator to cycle through all data to calculate total households.
		Iterator<Data> incomes = this.data.values().iterator();
		
		while(incomes.hasNext()) {
			totalHouseholds += incomes.next().getHouseholds();
		}	// end while
		
		return totalHouseholds;
	}	// end method
	
	/**
	 * <p>
	 * Acquires the hash key representation of this class based off the name of
	 * the class.
	 * </p>
	 * 
	 * @return The hash key representation for this class.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>name</code> must be assigned a value.</dd>
	 * <dd>POST: The hash code representation for <code>name</code> is returned 
	 *           to the requester.</dd>
	 * </dt>
	 */
	public Integer getKeyValue() {
		return new Integer(this.name.hashCode());
	}	// end method
	
	/**
	 * <p>
	 * Checks for equivalence of this Place with the one passed.
	 * </p>
	 * 
	 * @param toCompare the Place value to compare.
	 * @return a boolean based on equivalence.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>name</code> must contain a value.</dd>
	 * <dd>POST: Compares the hashCode of this class to the hashCode of the 
	 *           value passed.</dd>
	 * </dt>
	 */
	public boolean equals(Place toCompare) {
		return this.getKeyValue().equals(toCompare.getKeyValue());
	}	// end method
	
	/**
	 * <p>
	 * Clears the list of census data of place
	 * </p>
	 * 
	 * @return returns true if completed successfully
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>data</code> must be initialized and hold values.</dd>
	 * <dd>POST: <code>data</code> is cleared 
	 * </dt>
	 */
	public boolean clearData(){
		// Clear the map.
		this.data.clear();
		
		// Verify the data is cleared
		if(this.data.size() > 0) {
			return false;
		} else {
			return true;
		}	// end if
	}	// end method
	
	/**
	 * <p>
	 * Checks to see if specified data exists
	 * </p>
	 * 
	 * @param income  The data information to be compared
	 * @return returns true if data already exists
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>data</code> must be initialized and hold values.</dd>
	 * <dd>POST: <code>income</code> is checked against the <code>data</code> 
	 *           List
	 * </dt>
	 */
	public boolean dataExists(Data income){
		// Make sure the value exists in the map.
		if(this.data.get(income.getKeyValue()) != null) {
			return true;
		} else {
			return false;
		}	// end if
	}	// end method
	
	/**
	 * <p>
	 * Populates all income levels with the correct keys.
	 * </p>
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  data must be defined and initialized.</dd>
	 * <dd>POST: income levels are inserted as data values.</dd>
	 * </dt>
	 */
	private void populateData() {
		for(int i = 0; i < this.incomeRanges.length; i++) {
			this.addData(new Data(incomeRanges[i], 0), true);
		}	// end for
	}	// end method
	
	/**
	 * <p>
	 * Returns the appropriate string representation for this class for output 
	 * in legible format.<br/>
	 * Output format: ;"totalhouseholds";"incomerangehouseholds";...<br/>
	 * It is important to note that the name of this place is not compiled in 
	 * the "toString()" method.  This is because during textual output, the 
	 * county comes before the state.  Because we cannot determine which state 
	 * this place belongs within this class, we must acquire this value with the
	 * getName() method and compile that string in the toString() method in the 
	 * State class.
	 * </p>
	 * 
	 * @return The string representation for this class.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  All variables must be assigned values.</dd>
	 * <dd>POST: String is compiled and returned to the requester.</dd>
	 */
	public String toString(){
		// Number of households
		String output = ";\"" + this.getTotalHouseholds() + "\"";
		Iterator<Data> iter = this.data.values().iterator();
	
		// Compile the list of the makeup of each household.
		while(iter.hasNext()){
			output += ";" + iter.next().toString();
		}	// end while
	
		return output;
	}	// end method

	public void setPopulation(Integer population) {
		this.population = population;
	}

	public Integer getPopulation() {
		return population;
	}
}	// end class