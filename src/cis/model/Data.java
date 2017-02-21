package cis.model;

import java.io.Serializable;

/**
 * Data.java
 * 
 * Project #4
 * CS 2334, Section 010
 * April 3, 2011
 * 
 * <p>
 * This class defines Data relating to households and income range for a place.
 * </p>
 * 
 * @version 1.0
 * 
 */
public class Data implements Serializable {
	/** Eclipse generated serial version id */
	private static final long serialVersionUID = 4397999000237903374L;
	/** the income range data*/
	private String incomeRange;
	/** the number of households of the area */
	private Integer households;
		
	/**
	 * <p>
	 * Unargumented constructor that defines the default values for the Data 
	 * class.
	 * </p>
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>incomeRange</code>, <code>households</code> must be 
	 *           initialized.</dd>
	 * <dd>POST: <code>incomeRange</code>, <code>households</code> are assigned 
	 *           default values.</dd>
	 * </dt>
	 */
	public	Data() {
		this.incomeRange = "0";
		this.households  = 0;
	}	// end constructor
	
	/**
	 * <p>
	 * Constructor that defines the default values for the income range and the 
	 * number of households.
	 * </p>
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>incomeRange</code>, <code>households</code> must be 
	 *           initialized.</dd>
	 * <dd>POST: <code>incomeRange</code>, <code>households</code> are assigned 
	 *           default values.</dd>
	 * </dt>
	 */
	public Data(String incomeRange){
		this.incomeRange = incomeRange;
	}	// end constructor
	
	/**
	  * <p>
	  * Argumented constructor defined for incomerange and households
	  * </p>
	  *
	  * @param incomeRange the range of income of the area
	  * @param households the amount of households in the area
	  * 
	  * <dt><b>Conditions:</b>
	  * <dd>PRE:  <code>incomeRange</code>, <code>households</code>,  must be 
	  *           initialized.</dd>
	  * <dd>POST: <code>incomeRange</code>, <code>households</code>, are 
	  *           assigned the values passed by the constructor.</dd>
	  * </dt>
	  */
	public Data(String incomeRange, Integer households){
		this.incomeRange = incomeRange;
		this.households = households;
	}	// end method
	
	/**
	 * <p>
	 * A mutator method that changes the <code>households</code> Integer
	 * </p>
	 *  
	 * @param households The number of households in the area
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd> PRE: <code>households</code> has its default value.</dd> 
	 * <dd>	POST: <code>households</code> is assigned the value passed.</dd>
	 * </dt>
	 */
	public void setHouseholds(Integer households) {
		this.households = households;
	}	// end method
	
	/**
	 * <p>
	 * Acquires the households of the place.
	 * </p>
	 * 
	 * @return The number of households of place.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>households</code> must be assigned a value.</dd>
	 * <dd>POST: The value that <code>households</code> contains is returned to 
	 *           the requester.</dd>
	 * </dt>
	 */
	public Integer getHouseholds() {
		return households;
	}	// end method
	
	/**
	 * <p>
	 * A mutator method that changes the <code>incomeRange</code> Integer
	 * </p>
	 *  
	 * @param incomeRange  The incomeRange of the households of the area
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd> PRE: <code>incomeRange</code> has its default value.</dd> 
	 * <dd>	POST: <code>incomeRange</code> is assigned the value passed.</dd>
	 * </dt>
	 */
	public void setIncomeRange(String incomeRange) {
		this.incomeRange = incomeRange;
	}	// end method
	
	/**
	 * <p>
	 * Acquires the incomeRange.
	 * </p>
	 * 
	 * @return The income range of place.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>incomeRange</code> must be assigned a value.</dd>
	 * <dd>POST: The value that <code>incomeRange</code> contains is returned to
	 *           the requester.</dd>
	 * </dt>
	 */
	public String getIncomeRange() {
		return incomeRange;
	}	// end method
	
	/**
	 * <p>
	 * Returns the appropriate string representation for this class for output 
	 * in legible format.<br/>
	 * Output Format: "households"  - with quotation marks
	 * </p>
	 * 
	 * @return The string representation for this class.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  All variables must be assigned values.</dd>
	 * <dd>POST: String is compiled and returned to the requester.</dd>
	 */
	public String toString(){
		return "\"" + households.toString() + "\"";
	}	// end method

	/** 
	 * <p>
	 * Acquires the hash key value for this item.
	 * </p>
	 * 
	 *  @return The hash representation for this object.
	 *
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  The <code>incomeRange</code> for this object must hold a value 
	 *           to acquire the hash code representation.</dd>
	 * <dd>POST: The hash code that represents <code>incomeRange</code> is 
	 *           returned to the requester.</dd>
	 * </dt>
	 */
	public Integer getKeyValue() {
		return new Integer(this.incomeRange.hashCode());
	}	// end method

	/** 
	 * <p>
	 * Acquires the equality of this object to another.
	 * </p>
	 * 
	 *  @return The equivalence of this object to that of the compared.
	 *  
	 *  <dt><b>Conditions:</b>
	 *  <dd>PRE:  <code>incomeRange</code> must hold a value.</dd>
	 *  <dd>POST: <code>incomeRange</code> is compared to the income range of 
	 *            the data object passed.</dd>
	 *  </dt>
	 */
	public boolean equals(Data compared) {
		return this.incomeRange.equals(compared.getIncomeRange());
	}	// end method
}	// end class