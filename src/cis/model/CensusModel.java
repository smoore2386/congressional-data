package cis.model;
/**

 * CensusModel.java
 * 
 * Project #4
 * CS 2334, Section 010
 * April 3, 2011
 * 
 * <p>
 * This class defines the basic properties that will be available to a model class that holds values of the Census
 * for retrieval. The model handles the loading importing/exporting of the Census data.
 * </p>
 * 
 * @version 1.0
 * 
 */

import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CensusModel {
	/** New Census for the model*/
	private Census census;
	/** list of ActionListeners */
	private ArrayList<ActionListener> listeners;

	/**
	 * <p>
	 * Unargumented constructor that defines the default values for the Census
	 * class.
	 * </p>
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>census</code>, <code>listeners</code>,   must be 
	 *           initialized.</dd>
	 * <dd>POST: <code>census</code>, <code>listeners</code> are assigned 
	 *           default values.</dd>
	 * </dt>
	 */
	public CensusModel(){
		this.listeners = new ArrayList<ActionListener>();
		this.census    = null;
	}	// end constructor

	/**
	 * <p>
	 * Argumented constructor that defines the default values for the 
	 * CensusModel class
	 * </p>
	 * 
	 * @param census  The census information for the specified year
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>census</code>,assigned value and must be initialized.
	 *           </dd>
	 * <dd>POST: <code>census</code> is assigned a value.</dd>
	 * </dt>
	 */
	public CensusModel(Census census){
		this.listeners = new ArrayList<ActionListener>();
		this.census    = census;
	}

	/**
	 * <p>
	 * Creates a new census for the user to input census information into via 
	 * <code>importCensus</code> or <code>loadCensus</code>.
	 * </p>
	 * 
	 * @param year The year to create the new Census to
	 * 
	 * <dt><b>Conditions:</b><br/>
	 * <dd>PRE: <code>fileLocation</code> is acquired.</dd>
	 * <dd>POST: All data is loaded using either <code>importCensus</code> or 
	 *           <code>loadCensus</code> the <code>fileLocation</code> and 
	 *           assigned into <code>Census</code> and <code>metroModel</code>.
	 *           </dd>
	 * </dt>
	 */
	public void newCensus(Integer year){
		this.census = new Census(year);
		// Trigger Event
		this.processEvent(new ActionEvent(this.census, 
				ActionEvent.ACTION_PERFORMED, "New Census"));
	}	// end method

	/**
	 * <p>
	 * Adds a new state to the census, given the overwrite is correct.
	 * </p>
	 * 
	 * @param state The state to be added to the census.
	 * @param overwrite Option to overwrite existing values.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  The census must be defined.</dd>
	 * <dd>POST: The state is added to the census giver the overwrite is 
	 *           correct.</dd>
	 * </dt>
	 */
	public void addState(State state, boolean overwrite) {
		this.census.addState(state, overwrite);
		// Trigger the event, a change in the model occured.
		this.processEvent(new ActionEvent(this, 
				ActionEvent.ACTION_PERFORMED, "State Added"));
	}	// end method

	/**
	 * <p>
	 * Deletes a single state from the census.
	 * </p>
	 *  
	 * @param state The state to be deleted.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  The state must be defined to be removed.</dd>
	 * <dd>POST: The state is removed from the census.</dd>
	 * </dt>
	 */
	public void deleteState(State state) {
		this.census.removeState(state);
		// Trigger the event, there was a change in the model
		this.processEvent(new ActionEvent(this.census, 
				ActionEvent.ACTION_PERFORMED, "State Deleted"));
	}	// end method

	/**
	 * <p>
	 * Removes all the states from the census.
	 * </p>
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  Census must be defined.</dd>
	 * <dd>POST: All states contained in the census are removed.</dd>
	 * </dt>
	 */
	public void clearStates() {
		this.census.clearStates();
		// Trigger the event, there was a change in the model.
		this.processEvent(new ActionEvent(this.census, 
				ActionEvent.ACTION_PERFORMED, "States Cleared"));
	}	// end method

	/**
	 * <p>
	 * Adds a new place or overwrites an existing place based on value passed.
	 * </p>
	 * 
	 * @param state The state that contains the place.
	 * @param place The place to be added.
	 * @param overwrite An option to overwrite existing values.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  The state, that the place is being added, must be defined.</dd>
	 * <dd>POST: The place is added to the state, given overwrite is correct.
	 *           </dd>
	 * </dt> 
	 */
	public void addPlace(String state, Place place, boolean overwrite) {
		this.census.getState(state).addPlace(place, overwrite);
		// Trigger the event the model was changed.
		this.processEvent(new ActionEvent(this.census.getState(state), 
				ActionEvent.ACTION_PERFORMED, "Place Added"));
	}	// end method

	/**
	 * <p>
	 * Removes a place from the census model.
	 * </p>
	 * 
	 * @param state The state that contains the place.
	 * @param place The place to be removed.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  The place must be contained within the state.</dd>
	 * <dd>POST: The place is removed from the state.</dd>
	 * </dt>
	 */
	public void deletePlace(String state, Place place) {
		this.census.getState(state).removePlace(place);
		// Trigger the event the model was changed.
		this.processEvent(new ActionEvent(this.census.getState(state), 
				ActionEvent.ACTION_PERFORMED, "Place Deleted"));
	}	// end method

	/**
	 * <p>
	 * Removes all the places from a state.
	 * </p>
	 * 
	 * @param state The state that contains the place values.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  The state must be defined.</dd>
	 * <dd>POST: The places that are contained by the state are removed.</dd>
	 * </dt>
	 */
	public void clearPlaces(String state) {
		this.census.getState(state).getPlaces().clear();
		// Trigger the event, the model was changed.
		this.processEvent(new ActionEvent(this, 
				ActionEvent.ACTION_PERFORMED, "All Places Deleted"));
	}	// end method

	/**
	 * <p>
	 * Adds the data to a place.
	 * </p>
	 * 
	 * @param state The state that contains the place.
	 * @param place The place that contains the data.
	 * @param data  The data to be added.
	 * @param overwrite The option to overwrite existing data.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  The place that will be holding the data needs to be defined.
	 *           </dd>
	 * <dd>POST: The data is added to the place.</dd>
	 * </dt>
	 */
	public void addData(String state, String place, Data data, 
			boolean overwrite) {
		this.census.getState(state).getPlace(place).addData(data, overwrite);
		// Trigger the event, the model was changed
		this.processEvent(new ActionEvent(this.census.getState(state)
				.getPlace(place), ActionEvent.ACTION_PERFORMED, "Data Added"));
	}	// end method

	/**
	 * <p>
	 * Clears all data from a place.
	 * </p>
	 *  
	 * @param state The state that contains the place.
	 * @param place The place that contains the data.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  The state and place must be defined.</dd>
	 * <dd>POST: The data is cleared from the place.</dd>
	 * </dt>
	 */
	public void clearData(String state, String place) {
		this.census.getState(state).getPlace(place).clearData();
		// Trigger the event, the model was changed.
		this.processEvent(new ActionEvent(this.getCensus().getState(state)
				.getPlace(place), ActionEvent.ACTION_PERFORMED, "Data Removed"));
	}	// end method

	/**
	 * <p>
	 * Loads the data from the text file <code>Census</code> 
	 * </p>
	 * 
	 * @param fileLocation The location of the file to be imported
	 * 
	 * <dt><b>Conditions:</b><br/>
	 * <dd>PRE: <code>file</code> is acquired.</dd>
	 * <dd>POST: All data is loaded from the <code>file</code> and assigned into
	 *           <code>countyModel</code> and <code>metroModel</code>.</dd>
	 * </dt>
	 */
	public boolean importCensus(String fileLocation, Integer year) throws 
	ClassNotFoundException, 
	ClassCastException, 
	IOException {
		// Create a new Census to import this data into.
		this.census = new Census(year);		

		// Create the file reader and a buffer
		File           file         = new File(fileLocation);
		FileReader     reader       = new FileReader(file);
		BufferedReader buffedReader = new BufferedReader(reader);

		// The first line contains the income ranges - parse it
		String            firstLine   = buffedReader.readLine();
		String[]          incomes     = firstLine.split("\";\"");
		ArrayList<String> incomeRange = new ArrayList<String>();


		for(int i = 0; i < incomes.length; i++) {
			if(incomes[i].contains("Households: Household income; ")) {
				/* Format of the income is as follows in the import:
				 * Households: Household income; $xx;xxx to $xx;xxx
				 * This is true for each element.  We acquire the position of 
				 * the first semicolon and acquire the text after that for a 
				 * representation of the income level.
				 */
				incomes[i] = incomes[i].replace('"', ' ').trim();	
				incomeRange.add(incomes[i].substring(incomes[i].indexOf(';')+2, 
						incomes[i].length()).replace(';', ','));

			}	// end if
		}	// end for

		// Assign the income levels to this census
		this.census.setIncomeLevels(incomeRange);

		// Read the rest of the file
		while(buffedReader.ready()){
			String line = buffedReader.readLine();

			// Splits the data based on the delimiter ";"
			String[] data = line.split("\";\"");

			// Splits the county and state names up
			String   countyState    = data[0].substring(1);
			String[] countyAndState = countyState.split(", ");
			String   countyName     = countyAndState[0];
			String   stateName      = countyAndState[1];

			// Create a new state based on the stateName
			State newState = new State(stateName);

			// If the state already exists, acquire that state.
			if(this.census.stateExists(newState)) {
				newState = this.census.getStates().get(newState.getKeyValue());
			}	// end if

			// Create a new place based on the countyName
			Place newPlace = new Place(countyName);

			// Check if the place already exists.
			if(newState.placeExists(newPlace)) {
				newPlace = newState.getPlaces().get(newPlace.getKeyValue());
			}	// end if

			newPlace.setPopulation(Integer.parseInt(data[data.length-1].replace('"', ' ').trim()));
			// Process the place data
			for(int i = 2; i < data.length-1; i++) {
				if((i-2) < this.census.getIncomeLevels().size()) {
					data[i] = data[i].replace('"', ' ').trim();					
					newPlace.addData(
							new Data(this.census.getIncomeLevels().get(i-2), 
									new Integer(data[i]))
							,true);

				} else {
					// Malformed data.  Return false.
					return false;
				}	// end if
			}	// end for

			newState.addPlace(newPlace, true);
			this.census.addState(newState, true);
		}	// end while

		this.processEvent(new ActionEvent(this, 
				ActionEvent.ACTION_PERFORMED, "Census Imported"));

		return true;
	}	// end method

	/**
	 * <p>
	 * Loads the data from the ascii file file into the <code>CensusModel</code> 
	 * This method makes use of ObjectInputStream to carry out its function.
	 * </p>
	 * 
	 * @param fileLocation The file that contains the data to be loaded.
	 * 
	 * <dt><b>Conditions:</b><br/>
	 * <dd>PRE: <code>fileLocation</code> is acquired.</dd>
	 * <dd>POST: All data is loaded from the <code>fileLocation</code> and 
	 *           assigned into <code>Census</code> and <code>metroModel</code>.
	 *           </dd>
	 * </dt>
	 */
	public boolean loadCensus(String fileLocation) throws 
	ClassNotFoundException, 
	FileNotFoundException, 
	IOException {
		// Create a buffered input stream that can read binary from a file.
		InputStream       file   = new FileInputStream(fileLocation);
		InputStream       buffer = new BufferedInputStream(file);
		ObjectInputStream reader = new ObjectInputStream(buffer);

		// Assign this binary data to the new model.
		this.census = (Census)reader.readObject();

		// Verify the input was successful and will not create exceptions
		if(this.census != null && this.census.getStates() != null) {

			this.processEvent(new ActionEvent(this, 
					ActionEvent.ACTION_PERFORMED, "Census Loaded"));
			return true;
		} else {
			return false;
		}	// end if
	}	// end method

	/**
	 * <p>
	 * Saves the data as an ASCII file.
	 * </p>
	 * 
	 * @param fileLocation The file to be saved.
	 * 
	 * <dt><b>Conditions:</b><br/>
	 * <dd>PRE: <code>fileLocation</code> is acquired.</dd>
	 * <dd>POST: All data is saved to the <code>fileLocation</code> </dd>
	 * </dt>
	 */
	public boolean exportCensus(String fileLocation) throws IOException {
		// Initialize the writers and write the data to the output stream
		FileWriter     writer = new FileWriter(fileLocation);
		BufferedWriter buffer = new BufferedWriter(writer);
		buffer.write(this.census.toString());

		// Flush and close all output streams.
		buffer.flush();
		buffer.close();

		return false;
	}	// end method

	/**
	 * <p>
	 * Saves the data as a binary file 
	 * This method makes use of ObjectOutputStream to carry out its function.
	 * </p>
	 * 
	 * @param fileLocation The file to be saved.
	 * 
	 * <dt><b>Conditions:</b><br/>
	 * <dd>PRE: <code>fileLocation</code> is acquired.</dd>
	 * <dd>POST: All data is saved to the <code>fileLocation</code> </dd>
	 * </dt>
	 */
	public boolean saveCensus(String fileLocation) throws IOException {
		// Create a buffered stream that can output binary data to a file.
		OutputStream       file   = new FileOutputStream(fileLocation);
		OutputStream       buffer = new BufferedOutputStream(file);
		ObjectOutputStream writer = new ObjectOutputStream(buffer);

		// Write the model to the output stream.
		writer.writeObject(this.census);

		// Flush and close all output streams.
		writer.flush();
		writer.close();
		buffer.flush();
		buffer.close();
		file.flush();
		file.close();

		return true;
	}	// end method

	/**
	 * <p>
	 * Acquires the Census that this model represents.
	 * </p>
	 * 
	 * @return The value that is contained within <code>census</code>.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>census</code> must be assigned a value.</dd>
	 * <dd>POST: The value that is stored by <code>census</code> is returned to
	 *           the requester.</dd>
	 * </dt>
	 */
	public Census getCensus() {
		return this.census;
	}	// end method

	/**
	 * <p>
	 * Finds the correct Apportionment iteratively
	 * </p>
	 * 
	 * 
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>census</code> must be assigned a value.</dd>
	 * <dd>POST: The states that are stored by <code>census</code> will have correct
	 * 			 congressional apportionment</dd>
	 * </dt>
	 */
	public void apportionIterative(){

		//convert HashMap to ArrayList for ease of sorting
		List<State> stateList = new ArrayList<State>(census.getStates().values());
		//iterate for total amount of seats
		int seats = 385;
		while(seats > 0){
			Collections.sort(stateList);//sort the list for priority
			stateList.get(0).addSeat();//add seat
			seats-=1;//reduce total seat count

		}
		for(int i = 0; i < stateList.size(); i++){
			State stay = stateList.get(i);
			System.out.println(stay.getName()+ " " + stay.getApportion());
		}

	
	}
	/**
	 * <p>
	 * Finds the correct Apportionment Recursively
	 * </p>
	 * 
	 * 
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>census</code> must be assigned a value.</dd>
	 * <dd>POST: The states that are stored by <code>census</code> will have correct
	 * 			 congressional apportionment</dd>
	 * </dt>
	 */
	public int apportionRecursive(int n){
		//convert HashMap to ArrayList for ease of sorting
		List<State> stateList = new ArrayList<State>(census.getStates().values());
		//sort list
		
		if(n==0){
			for(int i = 0; i < stateList.size(); i++){
				State stay = stateList.get(i);
				System.out.println(stay.getName()+ " " + stay.getApportion() 
						);
			}
			return 0;
		}
		else{
			Collections.sort(stateList);
			stateList.get(0).addSeat();
			return apportionRecursive(n-1);
		}
	}

	/**
	 * <p>
	 * Processes events 
	 * </p>
	 * 
	 * @param event  The event triggered
	 * 
	 *   <dt><b>Conditions:</b><br/>
	 * <dd>PRE: <code>event</code> is passed to method</code>.</dd>
	 * <dd>POST: <code>event</code> is processed</dd>
	 * </dt>
	 */
	public void processEvent(ActionEvent event) {
		synchronized(this) {
			Iterator<ActionListener> iterator = this.listeners.iterator();

			// Cycle through all assigned listeners and trigger actionPerformed
			while(iterator.hasNext()) {
				iterator.next().actionPerformed(event);
			}	// end while
		}	// end synchronized
	}	// end method

	/**
	 * <p>
	 * Adds listener to <code>listeners</code>
	 * </p>
	 * @param listener the listener to be removed
	 * 
	 * <dt><b>Conditions:</b><br/>
	 * <dd>PRE: <code>listener</code> is passed to method</code>.</dd>
	 * <dd>POST: <code>listener</code> is added to <code>listeners</code> </dd>
	 * </dt>
	 */
	public synchronized void addActionListener(ActionListener listener) {
		this.listeners.add(listener);
	}	// end method

	/**
	 * <p>
	 * Removed specified from <code>listeners</code>
	 * </p>
	 * @param listener the listener to be removed
	 * 
	 * <dt><b>Conditions:</b><br/>
	 * <dd>PRE: <code>listener</code> is passed to method</code>.</dd>
	 * <dd>POST: <code>listener</code> is removed from <code>listeners</code>
	 *           </dd>
	 * </dt>
	 */
	public synchronized void removeActionListener(ActionListener listener) {
		this.listeners.remove(listener);
	}	// end method
}	// end class