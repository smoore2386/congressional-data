package cis.view;

import cis.model.CensusModel;
import cis.model.Place;
import cis.model.State;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;

/**
 * SelectionView.java
 * 
 * CS2334 010 Programming Structures and Abstractions
 * Project #4
 * April 4, 2011
 * 
 * <p>
 * This class contains all the necessary GUI elements and functions that will 
 * all the user to manipulate data contained within the 
 * <code>censusModel</code>.
 * </p>
 * 
 * @version 1.0
 *
 */
public class SelectionView extends JFrame implements ActionListener {
	/** Eclipse generated serial version id */
	private static final long serialVersionUID = -8283850008337927028L;

	/** The CensusModel that will be manipulated by this view. */
	private CensusModel censusModel;
	
	// GUI Components //********************************************************
	/** The menu that will be responsible for displaying the options for loading 
	    the model or creating a new model. */
	private JMenuBar  menuBar  = new JMenuBar();
	
	/** The menu that will contain program specific functions for operation. */
	private JMenu     fileMenu = new JMenu("File");
	/** The menu that will contain data specific functions for a selected 
	    place. */
	private JMenu     dataMenu = new JMenu("Data");
	
	/** The menu option that creates for a new, empty Census. */
	private JMenuItem newCensusMenuItem       = new JMenuItem("New Census");
	/** The menu option that imports a Census from a text/ascii file. */
	private JMenuItem importCensusMenuItem  = new JMenuItem("Import Census...");
	/** The menu option that exports a Census to a text/ascii file. */
	private JMenuItem exportCensusMenuItem  = new JMenuItem("Export Census...");
	/** The menu option that loads a Census from binary input. */
	private JMenuItem loadCensusMenuItem      = new JMenuItem("Load Census...");
	/** The menu option that saves a Census to a binary output. */
	private JMenuItem saveCensusMenuItem      = new JMenuItem("Save Census...");
	/** The menu option that exists the program. */
	private JMenuItem exitProgramMenuItem     = new JMenuItem("Exit");
	
	/** The menu option that will graph the data for a selected place. */
	private JMenuItem graphDataMenuItem       = new JMenuItem("Graph Data...");
	/** The menu option that will allow for the modification of data contained 
	    by a selected place. */
	private JMenuItem enterModifyDataMenuItem = new JMenuItem(
			"Enter/Modify Data...");
	/** The menu option that will clear all the data contained in a selected 
	    place. */
	private JMenuItem clearDataMenuItem       = new JMenuItem("Clear All Data");
	/** The menu option that will have a pie chart of the nationwide apportionment
	  . */
	private JMenuItem apportionmentChart	  = new JMenuItem("View Apportionment");
	
	/** The panel that layouts out both lists. */
	private JPanel    layoutPanel             = new JPanel(new GridLayout(1,2));
	/** The panel that places the buttons for state functions accordingly. */
	private JPanel    stateButtonPanel        = new JPanel(new GridLayout(3,3));
	/** The panel that places the buttons for place functions accordingly. */
	private JPanel    placeButtonPanel        = new JPanel(new GridLayout(3,3));
	
	private JPanel    statePanel = new JPanel(new BorderLayout());
	private JPanel    placePanel = new JPanel(new BorderLayout());
	
	/** The button that will initiate the creation of a new state. */
	private JButton   newStateButton          = new JButton("New State");
	/** The button that will delete a selected state. */
	private JButton   deleteStateButton       = new JButton("Delete State");
	/** The button that will delete all the states in the model. */
	private JButton   deleteAllStatesButton  = new JButton("Delete All States");
	
	/** The button that will initiate the creation a new place. */
	private JButton   newPlaceButton          = new JButton("New County");
	/** The button that will delete a selected place. */
	private JButton   deletePlaceButton       = new JButton("Delete County");
	/** The button that will delete all places from the model. */
	private JButton   deleteAllPlacesButton   
	                                       = new JButton("Delete All Counties");

	/** The list that will store all the states for the user to select. */
	private JList statesList                  = new JList();
	/** The list that will store all the places for the user to select. */
	private JList placesList                  = new JList();
	
	/** The scroller that will allow the user to scroll through the states. */
	private JScrollPane stateScroller        = new JScrollPane(this.statesList);
	/** The scroller that will allow the user to scroll through the places. */
	private JScrollPane placeScroller        = new JScrollPane(this.placesList);
	
	////////////////////////
	// Constructors       //
	////////////////////////////////////////////////////////////////////////////
	/**
	 * <p>
	 * This is the default constructor that takes no arguments.  It constructs 
	 * the GUI and places all elements accordingly.  A default value is assigned
	 * to the <code>censusModel</code>.
	 * </p>
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  All GUI elements are initialized but not assigned a value.  The 
	 *           <code>censusModel</code> is initialized but not assigned a 
	 *           value.</dd>
	 * <dd>POST: All GUI elements are assigned values, added to their respective 
	 *           components and the view initialized for user interaction.  The 
	 *           <code>censusModel</code> is assigned a default value.</dd>
	 * </dt>
	 */
	public SelectionView() {
		makeLayout();
	}	// end constructor
	
	////////////////////////////
	// Mutators               //
	////////////////////////////////////////////////////////////////////////////
	/**
	 * <p>
	 * This method creates the total layout so that multiple constructors can
	 * be used.
	 * </p>
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  All layout components must be assigned a value.</dd>
	 * <dd>POST: The layout components are added to the view.</dd>
	 * </dt>
	 */
	public void makeLayout() {		
		////////////////////////////////////////////////////////////////////////
		// MENU CONSTRUCTION                                                  //
		////////////////////////////////////////////////////////////////////////
		// Add all the file menu components to the menu.
		this.fileMenu.add(this.newCensusMenuItem);
		this.fileMenu.add(this.loadCensusMenuItem);
		this.fileMenu.addSeparator();
		this.fileMenu.add(this.saveCensusMenuItem);
		this.fileMenu.addSeparator();
		this.fileMenu.add(this.importCensusMenuItem);
		this.fileMenu.add(this.exportCensusMenuItem);
		this.fileMenu.addSeparator();
		this.fileMenu.add(this.exitProgramMenuItem);
		
		// Add all the data menu components to the menu.
		this.dataMenu.add(this.graphDataMenuItem);
		this.dataMenu.add(this.apportionmentChart);
		this.dataMenu.addSeparator();
		this.dataMenu.add(this.enterModifyDataMenuItem);
		this.dataMenu.add(this.clearDataMenuItem);
		
		// Defaulted items to be disabled until condition met
		this.saveCensusMenuItem.setEnabled(false);
		this.exportCensusMenuItem.setEnabled(false);
		this.graphDataMenuItem.setEnabled(false);
		this.enterModifyDataMenuItem.setEnabled(false);
		this.clearDataMenuItem.setEnabled(false);
		
		// Add both menus to the menu bar.
		this.menuBar.add(fileMenu);
		this.menuBar.add(dataMenu);
		
		// Add the menu bar to the frame.
		this.setJMenuBar(this.menuBar);
		
		////////////////////////////////////////////////////////////////////////
		// STATES LIST CONSTRUCTION                                           //
		////////////////////////////////////////////////////////////////////////
		this.stateButtonPanel.add(Box.createVerticalStrut(20));
		this.stateButtonPanel.add(Box.createVerticalStrut(20));
		this.stateButtonPanel.add(Box.createVerticalStrut(20));
		this.stateButtonPanel.add(this.newStateButton);
		this.stateButtonPanel.add(this.deleteStateButton);
		this.stateButtonPanel.add(this.deleteAllStatesButton);
		this.stateButtonPanel.add(Box.createVerticalStrut(20));
		this.stateButtonPanel.add(Box.createVerticalStrut(20));
		this.stateButtonPanel.add(Box.createVerticalStrut(20));
		this.stateButtonPanel.setBorder(BorderFactory.createEtchedBorder(
				EtchedBorder.RAISED));
		
		this.newStateButton.setEnabled(false);
		this.deleteStateButton.setEnabled(false);
		this.deleteAllStatesButton.setEnabled(false);
		
		this.statesList.setModel(new DefaultListModel());
		this.statesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.stateScroller.setBorder(
				BorderFactory.createTitledBorder("States"));
		
		this.statePanel.add(stateScroller, BorderLayout.CENTER);
		this.statePanel.add(stateButtonPanel, BorderLayout.SOUTH);
		
		////////////////////////////////////////////////////////////////////////
		// PLACES LIST CONSTRUCTION                                           //
		////////////////////////////////////////////////////////////////////////
		this.placeButtonPanel.add(Box.createVerticalStrut(20));
		this.placeButtonPanel.add(Box.createVerticalStrut(20));
		this.placeButtonPanel.add(Box.createVerticalStrut(20));
		this.placeButtonPanel.add(this.newPlaceButton);
		this.placeButtonPanel.add(this.deletePlaceButton);
		this.placeButtonPanel.add(this.deleteAllPlacesButton);
		this.placeButtonPanel.add(Box.createVerticalStrut(20));
		this.placeButtonPanel.add(Box.createVerticalStrut(20));
		this.placeButtonPanel.add(Box.createVerticalStrut(20));
		this.placeButtonPanel.setBorder(BorderFactory.createEtchedBorder(
				EtchedBorder.RAISED));
		
		this.newPlaceButton.setEnabled(false);
		this.deletePlaceButton.setEnabled(false);
		this.deleteAllPlacesButton.setEnabled(false);
		
		this.placesList.setModel(new DefaultListModel());
		this.placesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.placeScroller.setBorder(
				BorderFactory.createTitledBorder("Counties"));
		
		this.placePanel.add(placeScroller, BorderLayout.CENTER);
		this.placePanel.add(placeButtonPanel, BorderLayout.SOUTH);
		
		////////////////////////////////////////////////////////////////////////
		// MAIN LAYOUT                                                        //
		////////////////////////////////////////////////////////////////////////
		this.layoutPanel.add(this.statePanel);
		this.layoutPanel.add(this.placePanel);
		
		this.add(this.layoutPanel, BorderLayout.CENTER);
	}	// end method
	
	/**
	 * <p>
	 * This method populates the states list with entries from the model.
	 * </p>
	 * 
	 * @return Successful completion of the operation.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  The model must be assigne as well as the list view assigned.
	 *           </dd>
	 * <dd>POST: The elements in the model are added to the list.</dd>
	 * </dt>
	 */
	public boolean populateStatesList() {
		// Verify that the list is empty before adding additional elements
		if(this.statesList.getModel().getSize() > 0) {
			((DefaultListModel)this.statesList.getModel()).removeAllElements();
		}	// end if
		
		// Acquire the iterator to add all the elements contained in the census
		// to the list model.
		Iterator<State> states = this.censusModel.getCensus().getStates()
		                                         .values().iterator();
		
		// Populate the list with the updated states
		while(states.hasNext()) {
			((DefaultListModel)this.statesList.getModel()).addElement(
					states.next().getName());
		}	// end while
		
		this.statesList.repaint();
		
		if(this.censusModel.getCensus().getStates().size() >= 0) {
			this.newStateButton.setEnabled(true);
			this.saveCensusMenuItem.setEnabled(true);
			this.exportCensusMenuItem.setEnabled(true);
			
			if(this.censusModel.getCensus().getStates().size() > 0) {
				this.deleteAllStatesButton.setEnabled(true);
			}	// end if
		}	// end if
		
		return true;
	}	// end method
	
	public boolean populatePlacesList(State state) {
		// Verify the list is empty before adding additional elements
		if(this.placesList.getModel().getSize() > 0) {
			((DefaultListModel)this.placesList.getModel()).removeAllElements();
		}	// end if
		
		if(this.censusModel.getCensus().stateExists(state)) {
			Iterator<Place> places = 
				this.censusModel.getCensus()
				                 .getStates().get(state.getKeyValue())
				                 .getPlaces().values().iterator();
			
			// Populate the list with the updated places.
			while(places.hasNext()) {
				((DefaultListModel)this.placesList.getModel()).addElement(
						places.next().getName());
			}	// end while
			
			return true;
		} else {	// State does not exist so places cannot be rendered
			return false;
		}	// end if
	}	// end method
	
	/**
	 * <p>
	 * Assigns the <code>censusModel</code> to the value passed by the method.
	 * </p>
	 * 
	 * @param censusModel The value that will be assigned to 
	 *                    <code>censusModel</code>.
	 *                    
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  The <code>censusModel</code> needs to be initialized.</dd>
	 * <dd>POST: <code>censusModel</code> is assigned the value passed by the 
	 *           method.</dd>
	 * </dt>
	 */
	public void setModel(CensusModel censusModel) {
		this.censusModel = censusModel;
	}	// end method
	
	/**
	 * <p>
	 * The action that will be invoked with a certain ActionEvent is passed as a
	 * value to this function.
	 * </p>
	 * 
	 * @param event The ActionEvent that triggered this ActionListener.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  This class must be assigned as a listener to the model.</dd>
	 * <dd>POST: This class will perform a function based on the action the 
	 *           invoker takes.</dd>
	 * </dt>
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		populateStatesList();
		
		if(event.getActionCommand().equals("New Census")) {
			this.add(new JLabel(this.censusModel.getCensus().getYear() + 
				" Census"), BorderLayout.NORTH);
			this.setVisible(false);
			this.setVisible(true);
			this.toFront();
		}
	}	// end method
	
	/////////////////////////////
	// Accessors               //
	////////////////////////////////////////////////////////////////////////////
	/**
	 * <p>
	 * Acquires the value that is stored by <code>censusModel</code>.
	 * </p>
	 * 
	 * @return The value stored by <code>censusModel</code>.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>censusModel</code> must hold a value.</dd>
	 * <dd>POST: The value stored by <code>censusModel</code> is returned to the 
	 *           requester.</dd>
	 * </dt>
	 */
	public CensusModel getModel() {
		return this.censusModel;
	}	// end method
	
	/**
	 * <p>
	 * Acquires the menu item that is stored by <code>newCensusMenuItem</code>.
	 * </p>
	 * 
	 * @return Acquires the Menu Item that will create a new Census.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>newCensusMenuItem</code> must be assigned a value.</dd>
	 * <dd>POST: The value stored by <code>newCensusMenuItem</code> is returned 
	 *           to the requester.</dd>
	 * </dt>
	 */
	public JMenuItem getNewCensusMenuItem() {
		return this.newCensusMenuItem;
	}	// end method
	
	/**
	 * <p>
	 * Acquires the menu item that is stored by 
	 * <code>importCensusMenuItem</code>.</p>
	 * 
	 * @return Acquires the Menu Item that will import a Census.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>importCensusMenuItem</code> must be assigned a 
	 *           value.</dd>
	 * <dd>POST: The value stored by <code>importCensusMenuItem</code> is 
	 *           returned to the requester.</dd>
	 * </dt>
	 */
	public JMenuItem getImportCensusMenuItem() {
		return this.importCensusMenuItem;
	}	// end method
	
	/**
	 * <p>
	 * Acquires the menu item that is stored by 
	 * <code>exportCensusMenuItem</code>.</p>
	 * 
	 * @return Acquires the Menu Item that will export a Census.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>exportCensusMenuItem</code> must be assigned a 
	 *           value.</dd>
	 * <dd>POST: The value stored by <code>exportCensusMenuItem</code> is 
	 *           returned to the requester.</dd>
	 * </dt>
	 */
	public JMenuItem getExportCensusMenuItem() {
		return this.exportCensusMenuItem;
	}	// end method
	
	/**
	 * <p>
	 * Acquires the menu item that is stored by <code>loadCensusMenuItem</code>.
	 * </p>
	 * 
	 * @return Acquires the Menu Item that will load a Census.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>loadCensusMenuItem</code> must be assigned a value.</dd>
	 * <dd>POST: The value stored by <code>loadCensusMenuItem</code> is returned 
	 *           to the requester.</dd>
	 * </dt>
	 */
	public JMenuItem getLoadCensusMenuItem() {
		return this.loadCensusMenuItem;
	}	// end method
	
	/**
	 * <p>
	 * Acquires the menu item that is stored by <code>saveCensusMenuItem</code>.
	 * </p>
	 * 
	 * @return Acquires the Menu Item that will save a Census.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>saveCensusMenuItem</code> must be assigned a value.</dd>
	 * <dd>POST: The value stored by <code>saveCensusMenuItem</code> is returned 
	 *           to the requester.</dd>
	 * </dt>
	 */
	public JMenuItem getSaveCensusMenuItem() {
		return this.saveCensusMenuItem;
	}	// end method
	
	/**
	 * <p>
	 * Acquires the menu item that is stored by 
	 * <code>exitProgramMenuItem</code>.</p>
	 * 
	 * @return Acquires the Menu Item that will exit the program.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>exitProgramMenuItem</code> must be assigned a value.</dd>
	 * <dd>POST: The value stored by <code>exitProgramMenuItem</code> is 
	 *           returned to the requester.</dd>
	 * </dt>
	 */
	public JMenuItem getExitMenuItem() {
		return this.exitProgramMenuItem;
	}	// end method
	
	/**
	 * <p>
	 * Acquires the menu item that is stored by <code>graphDataMenuItem</code>.
	 * </p>
	 * 
	 * @return Acquires the Menu Item that will display the PieChartView.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>graphDataMenuItem</code> must be assigned a value.</dd>
	 * <dd>POST: The value stored by <code>graphDataMenuItem</code> is returned 
	 *           to the requester.</dd>
	 * </dt>
	 */
	public JMenuItem getGraphDataMenuItem() {
		return this.graphDataMenuItem;
	}	// end method
	
	/**
	 * <p>
	 * Acquires the menu item that is stored by 
	 * <code>enterModifyDataMenuItem</code>.</p>
	 * 
	 * @return Acquires the Menu Item that will display the DataEntryView.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>enterModifyDataMenuItem</code> must be assigned a 
	 *           value.</dd>
	 * <dd>POST: The value stored by <code>enterModifyDataMenuItem</code> is 
	 *           returned to the requester.</dd>
	 * </dt>
	 */
	public JMenuItem getEnterModifyDataMenuItem() {
		return this.enterModifyDataMenuItem;
	}	// end method
	
	/**
	 * <p>
	 * Acquires the menu item that is stored by <code>clearDataMenuItem</code>.
	 * </p>
	 * 
	 * @return Acquires the Menu Item that will clear all data from a place.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>clearDataMenuItem</code> must be assigned a value.</dd>
	 * <dd>POST: The value stored by <code>clearDataMenuItem</code> is returned 
	 *           to the requester.</dd>
	 * </dt>
	 */
	public JMenuItem getClearDataMenuItem() {
		return this.clearDataMenuItem;
	}	// end method
	
	/**
	 * <p>
	 * Acquires the button that is stored by <code>newStateButton</code>.
	 * </p>
	 * 
	 * @return Acquires the button that will create a new state.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>newStateButton</code> must be assigned a value.</dd>
	 * <dd>POST: The value stored by <code>newStateButton</code> is returned 
	 *           to the requester.</dd>
	 * </dt>
	 */
	public JButton getNewStateButton() {
		return this.newStateButton;
	}	// end method
	
	/**
	 * <p>
	 * Acquires the button that is stored by <code>deleteStateButton</code>.
	 * </p>
	 * 
	 * @return Acquires the button that will delete a state.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>deleteStateButton</code> must be assigned a value.</dd>
	 * <dd>POST: The value stored by <code>deleteStateButton</code> is returned 
	 *           to the requester.</dd>
	 * </dt>
	 */
	public JButton getDeleteStateButton() {
		return this.deleteStateButton;
	}	// end method
	
	/**
	 * <p>
	 * Acquires the button that is stored by <code>deleteAllStatesButton</code>.
	 * </p>
	 * 
	 * @return Acquires the button that will delete all states.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>deleteAllStatesButton</code> must be assigned a 
	 *           value.</dd>
	 * <dd>POST: The value stored by <code>deleteAllStatesButton</code> is 
	 *           returned to the requester.</dd>
	 * </dt>
	 */
	public JButton getDeleteAllStatesButton() {
		return this.deleteAllStatesButton;
	}	// end method
	
	/**
	 * <p>ive
	 * Acquires the button that is stored by <code>newPlaceButton</code>.
	 * </p>
	 * 
	 * @return Acquires the button that will create a new place.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>newPlaceButton</code> must be assigned a value.</dd>
	 * <dd>POST: The value stored by <code>newPlaceButton</code> is returned 
	 *           to the requester.</dd>
	 * </dt>
	 */
	public JButton getNewPlaceButton() {
		return this.newPlaceButton;
	}	// end method
	
	/**
	 * <p>
	 * Acquires the button that is stored by <code>deleteStateButton</code>.
	 * </p>
	 * 
	 * @return Acquires the button that will delete a place.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>deletePlaceButton</code> must be assigned a value.</dd>
	 * <dd>POST: The value stored by <code>deletePlaceButton</code> is returned 
	 *           to the requester.</dd>
	 * </dt>
	 */
	public JButton getDeletePlaceButton() {
		return this.deletePlaceButton;
	}	// end method
	
	/**
	 * <p>
	 * Acquires the button that is stored by <code>deleteAllPlacesButton</code>.
	 * </p>
	 * 
	 * @return Acquires the button that will delete all places.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>deleteAllPlacesButton</code> must be assigned a 
	 *           value.</dd>
	 * <dd>POST: The value stored by <code>deleteAllPlacesButton</code> is 
	 *           returned to the requester.</dd>
	 * </dt>
	 */
	public JButton getDeleteAllPlacesButton() {
		return this.deleteAllPlacesButton;
	}	// end method
	
	public void setApportionmentChart(JMenuItem apportionmentChart) {
		this.apportionmentChart = apportionmentChart;
	}

	public JMenuItem getApportionmentChart() {
		return apportionmentChart;
	}

	/**
	 * <p>
	 * Acquires the list of states that is associated with this view.
	 * </p>
	 * 
	 * @return The list that states are added to.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  The list must be assigned a value.</dd>
	 * <dd>POST: The list is returned to the user.</dd>
	 * </dt>
	 */
	public JList getStateList() {
		return this.statesList;
	}	// end method
	
	/**
	 * <p>
	 * Acquires the list of places that is associated with this view.
	 * </p>
	 * 
	 * @return The list that places are added to.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  The list must be assigned a value.</dd>
	 * <dd>POST: The list is returned to the user.</dd>
	 * </dt>
	 */
	public JList getPlaceList() {
		return this.placesList;
	}	// end method
}	// class