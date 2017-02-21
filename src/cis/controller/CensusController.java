package cis.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import cis.model.CensusModel;
import cis.model.Data;
import cis.model.Place;
import cis.model.State;
import cis.view.DataEntryView;
import cis.view.PieChartView;
import cis.view.SelectionView;

/**
 * CensusController.java
 * 
 * CS2334 010 Programming Structures and Abstractions
 * Project #4
 * April 4, 2011
 * 
 * <p>
 * This class will assign a model to the view component and assign action
 * listeners to listen for triggers on the changes in data or interactions from
 * the user to proceed with an action on the model or the view.
 * </p>
 * 
 * @version 1.0
 */
public class CensusController {
	/**
	 * This class contains all the data that will trigger viewable components in
	 * the <code>selectionView</code> to be enabled.  It will also perform the 
	 * functions upon critical program data and the output it produces.
	 */
	private CensusModel   censusModel;
	/**
	 * This class contains all the GUI elements that will allow the user to 
	 * interact with the program to produce results and manipulations upon the 
	 * data contained within the <code>censusModel</code>.
	 */
	private SelectionView selectionView;
	
	/**
	 * When adding a new place, the state is de-selected, so store this 
	 * temporarily to edit the place.
	 */
	public String lastSelectedState;
	
	/**
	 * When changing data, the place is deselected, so store this temporarily 
	 * to edit the data.
	 */
	public String lastSelectedPlace;
	
	/**
	 * The view that will be responsible for editing data contained in Place.
	 */
	public DataEntryView entryView;
	
	///////////////////////////
	// Constructors          //
	////////////////////////////////////////////////////////////////////////////
	/**
	 * <p>
	 * This is the default constructor for this class that does not take any 
	 * arguments.  Default values are assigned to the <code>censusModel</code> 
	 * and the <code>selectionView</code> since these classes do not warrant 
	 * initialization restrictions - the model data acts upon this information.
	 * </p>
	 */
	public CensusController() {
		censusModel   = new CensusModel();
		selectionView = new SelectionView();
		
		selectionView.setModel(this.censusModel);
		this.addListeners();
		selectionView.setBounds(0,0,800,400);
		selectionView.setVisible(true);
		selectionView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	// end constructor
	
	////////////////////////
	// Mutators           //
	////////////////////////////////////////////////////////////////////////////
	
	public void addListeners() {
		this.censusModel.addActionListener(this.selectionView);
		
		//New Census Menu Item//////////////////////////////////////////////////
		this.selectionView.getNewCensusMenuItem().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						String year = JOptionPane.showInputDialog(selectionView,
								"What year is this Census recording?");
						
						if(year.length() > 0) {
							selectionView.getModel().newCensus(
									new Integer(year));
							selectionView.setModel(censusModel);
							
							selectionView.getNewStateButton().setEnabled(true);
							selectionView.getSaveCensusMenuItem()
							.setEnabled(true);
							selectionView.getExportCensusMenuItem()
							.setEnabled(true);
						} else {
							JOptionPane.showMessageDialog(selectionView, 
									"Invalid input.");
							this.actionPerformed(ae);
						}
					}	// end method
				}	// end class
		);
		
		//Load Census Menu Item/////////////////////////////////////////////////
		this.selectionView.getLoadCensusMenuItem().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						JFileChooser fileChooser = new JFileChooser();
						
						int option = fileChooser.showOpenDialog(selectionView);
						
						switch(option) {
						case JFileChooser.APPROVE_OPTION:
							try {
								selectionView.getModel().loadCensus(
										fileChooser.getSelectedFile()
										.getAbsolutePath());
							} catch(ClassNotFoundException cnfe) {
								JOptionPane.showMessageDialog(selectionView, 
										cnfe.getMessage());
							} catch(IOException ioe) {
								JOptionPane.showMessageDialog(selectionView, 
										ioe.getMessage());
							}	// end try/catch
						}	// end case
					}	// end method
				}	// end class
		);
		
		//Import Census Menu Item///////////////////////////////////////////////
		this.selectionView.getImportCensusMenuItem().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						JFileChooser fileChooser = new JFileChooser();
				
						int option = fileChooser.showOpenDialog(selectionView);
				
						switch(option) {
						case JFileChooser.APPROVE_OPTION:
							try {
								String value = JOptionPane.showInputDialog(
										selectionView, 
										"What year did this census take place?",
										"Census Year", 
										JOptionPane.QUESTION_MESSAGE);
								selectionView.getModel().importCensus(
										fileChooser.getSelectedFile()
										.getAbsolutePath(), new Integer(value));
							} catch(ClassNotFoundException cnfe) {		
								JOptionPane.showMessageDialog(selectionView, 
										cnfe.getMessage());
							} catch(IOException ioe) {
								JOptionPane.showMessageDialog(selectionView, 
										ioe.getMessage());
							}	// end try/catch
						}	// end switch
					}	// end method
				}	// end class
		);
		
		//Save Census Menu Item/////////////////////////////////////////////////
		this.selectionView.getSaveCensusMenuItem().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						JFileChooser fileChooser = new JFileChooser();
						
						int option = fileChooser.showSaveDialog(selectionView);
						
						switch(option) {
						case JFileChooser.APPROVE_OPTION:
							try {
								selectionView.getModel().saveCensus(
										fileChooser.getSelectedFile()
										.getAbsolutePath());
							} catch(IOException ioe) {
								JOptionPane.showMessageDialog(selectionView, 
										ioe.getMessage());
							}	// end try/catch
						}	// end case
					}	// end method
				}	// end class
		);
		
		//Export Census Menu Item///////////////////////////////////////////////
		this.selectionView.getExportCensusMenuItem().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						JFileChooser fileChooser = new JFileChooser();
						
						int option = fileChooser.showSaveDialog(selectionView);
						
						switch(option) {
						case JFileChooser.APPROVE_OPTION:
							try {
								selectionView.getModel().exportCensus(
										fileChooser.getSelectedFile()
										.getAbsolutePath());
							} catch(IOException ioe) {
								JOptionPane.showMessageDialog(selectionView, 
										ioe.getMessage());
							}	// end try/catch
						}	// end case
					}	// end method
				}	// end class
		);
		
		//Exit Menu Item////////////////////////////////////////////////////////
		this.selectionView.getExitMenuItem().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						System.exit(0);
					}	// end method
				}	// end class
		);
		
		//Modify Place Data Menu Item///////////////////////////////////////////
		this.selectionView.getEnterModifyDataMenuItem().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						entryView = new DataEntryView(selectionView.getModel()
								.getCensus().getState(
								(String)selectionView.getStateList()
								.getSelectedValue()).getPlace(
								(String)selectionView.getPlaceList()
								.getSelectedValue()
								));
						
						entryView.getTable().getModel().addTableModelListener(
								new TableModelListener () {
							public void tableChanged(TableModelEvent tme) {
								String place = (String)selectionView
								.getPlaceList().getSelectedValue();
								String state = (String)selectionView
								.getStateList().getSelectedValue();
								lastSelectedState = state;
								lastSelectedPlace = place;
								
								Data newData = new Data(
										(String)entryView.getTable()
										.getModel()
										.getValueAt(tme.getFirstRow(), 0),
										new Integer((String)entryView.getTable()
												.getModel().getValueAt(tme
														.getFirstRow(), 1))
										);
								selectionView.getModel().addData(state, place,
										newData, true);
								selectionView.getStateList()
								.setSelectedValue(lastSelectedState, true);
								selectionView.getPlaceList()
								.setSelectedValue(lastSelectedPlace, true);
							}
						});
						
						entryView.setVisible(true);
						selectionView.getStateList()
						.setSelectedValue(lastSelectedState, true);
					}	// end method
				}	// end class
		);
		
		//Graph Data Menu Item//////////////////////////////////////////////////
		this.selectionView.getGraphDataMenuItem().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						PieChartView chartView = new PieChartView(
								selectionView.getModel().getCensus().getState(
										(String)selectionView.getStateList()
										.getSelectedValue()).getPlace(
										(String)selectionView.getPlaceList()
										.getSelectedValue()
								), 
								(String)selectionView.getStateList()
								.getSelectedValue()
						);
						
						censusModel.addActionListener(chartView);
					}	// end method
				}	// end class
		);
		this.selectionView.getApportionmentChart().addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent ae){
						selectionView.getModel().apportionIterative();
						
					}//end method
				
				}//end class
		);
		//Clear Data Menu Item//////////////////////////////////////////////////
		this.selectionView.getClearDataMenuItem().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
							selectionView.getModel().clearData(
									(String)selectionView.getStateList()
									.getSelectedValue(), 
									(String)selectionView.getPlaceList()
									.getSelectedValue()
							);
					}	// end method
				}	// end class
		);
		
		//State List Selection Listener/////////////////////////////////////////
		this.selectionView.getStateList().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent lse) {
						if(selectionView.getStateList()
								.getSelectedValue() != null) {
							selectionView.populatePlacesList(
									new State((String)selectionView
											.getStateList()
											.getSelectedValue()));
							selectionView.getDeleteStateButton()
							.setEnabled(true);
							selectionView.getNewPlaceButton()
							.setEnabled(true);
							
							if(selectionView.getPlaceList().getModel()
									.getSize() > 0) {
								selectionView.getDeleteAllPlacesButton()
								.setEnabled(true);
							} else {
								selectionView.getDeleteAllPlacesButton()
								.setEnabled(false);
							}
						} else {
							selectionView.getPlaceList().removeAll();
							selectionView.getDeleteStateButton()
							.setEnabled(false);
					
							if(selectionView.getStateList().getModel()
									.getSize() == 0) {
								selectionView.getDeleteAllStatesButton()
								.setEnabled(false);
							}	// end if
						}	// end if
					}	// end method
				}	// end class
		);
		
		//Place List Selection Listener/////////////////////////////////////////
		this.selectionView.getPlaceList().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent lse) {
						if(selectionView.getPlaceList().getSelectedValue()
								!= null) {
							selectionView.getDeletePlaceButton()
							.setEnabled(true);
							selectionView.getEnterModifyDataMenuItem()
							.setEnabled(true);
							selectionView.getGraphDataMenuItem()
							.setEnabled(true);
							selectionView.getClearDataMenuItem()
							.setEnabled(true);
						} else {
							selectionView.getDeletePlaceButton()
							.setEnabled(false);
							selectionView.getEnterModifyDataMenuItem()
							.setEnabled(false);
							selectionView.getGraphDataMenuItem()
							.setEnabled(false);
							selectionView.getClearDataMenuItem()
							.setEnabled(false);
						}	// end if
						
						if(selectionView.getPlaceList().getModel()
								.getSize() == 0) {
							selectionView.getDeleteAllPlacesButton()
							.setEnabled(false);
						} else {
							selectionView.getDeleteAllPlacesButton()
							.setEnabled(true);
						}	// end if
					}	// end method
				}	// end class
		);
		
		//Delete All States Button//////////////////////////////////////////////
		this.selectionView.getDeleteAllStatesButton().addActionListener(
				new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int option = JOptionPane.showConfirmDialog(selectionView, 
						"Are you sure you wish to delete all elements of " +
						"this census?", "Delete All States", 
						JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE);
				
				switch(option) {
				case JOptionPane.YES_OPTION:
					((DefaultListModel)selectionView.getStateList().getModel())
					.removeAllElements();
					selectionView.getModel().clearStates();
					selectionView.getStateList().repaint();
					
					if(selectionView.getPlaceList().getModel().getSize() > 0) {
						((DefaultListModel)selectionView.getPlaceList()
								.getModel()).removeAllElements();
					}
				}	// end switch
			}	// end method
		});	// end class
		
		//New State Button//////////////////////////////////////////////////////
		this.selectionView.getNewStateButton().addActionListener(
				new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String name = JOptionPane.showInputDialog(selectionView, 
						"What is the name of the state you wish to create?", 
						"Create New State", JOptionPane.INFORMATION_MESSAGE);
				State  newState = new State(name);
				
				if(selectionView.getModel().getCensus().stateExists(newState)) {
					int option = JOptionPane.showConfirmDialog(selectionView, 
							"Entry already exists.  Do you wish to overwrite " +
							"the current data?");
					
					switch(option) {
					case JOptionPane.OK_OPTION:
						selectionView.getModel().addState(newState, true);
					} // end switch
				} else {
					selectionView.getModel().addState(newState, true);
				}	// end if
			}	// end method
		});	// end class
		
		//Delete State Button///////////////////////////////////////////////////
		this.selectionView.getDeleteStateButton().addActionListener(
				new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(selectionView.getStateList().getSelectedValue() != null) {
					selectionView.getModel().deleteState(new State((String)
							selectionView.getStateList().getSelectedValue()));
					((DefaultListModel)selectionView.getStateList()
							.getModel()).removeElement(selectionView
									.getStateList().getSelectedValue());
					selectionView.getStateList().repaint();
					
					if(selectionView.getPlaceList().getModel().getSize() > 0) {
						((DefaultListModel)selectionView.getPlaceList()
								.getModel()).removeAllElements();
					}	// end if
				}	// end if
			}	// end method
		});	// end class
		
		//New Place Button//////////////////////////////////////////////////////
		this.selectionView.getNewPlaceButton().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						// Prompt user for place name.
						String placeName = JOptionPane.showInputDialog(
								selectionView, 
								"What is the name of the place in which you " +
								"wish to create income demographics?");
						lastSelectedState = (String)selectionView.getStateList()
						.getSelectedValue();
						Place newPlace    = new Place(placeName);
						
						// Make sure the value does not already exist.
						if(selectionView.getModel().getCensus()
								.getState(lastSelectedState)
								.placeExists(newPlace)) {
							int option = JOptionPane.showConfirmDialog(
									selectionView, 
									"Place already exists.  Do you wish to " +
									"overwrite data?", "Place Exists", 
									JOptionPane.YES_NO_OPTION, 
									JOptionPane.QUESTION_MESSAGE);
							
							// What did the user want?
							switch(option) {
							case JOptionPane.YES_OPTION:
								selectionView.getModel().addPlace(
										(String)selectionView.getStateList()
										.getSelectedValue(), newPlace, true);
							}	// end switch
						} else {
							selectionView.getModel().addPlace(
									(String)selectionView.getStateList()
									.getSelectedValue(), newPlace, false);
						}	// end if
						
						selectionView.getStateList().setSelectedValue(
								lastSelectedState, true);
					}	// end method
				}	// end class
		);
		
		//Delete Place Button///////////////////////////////////////////////////
		this.selectionView.getDeletePlaceButton().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						String placeName = (String)selectionView.getPlaceList()
						.getSelectedValue();
						lastSelectedState = (String)selectionView.getStateList()
						.getSelectedValue();
						
						selectionView.getModel().deletePlace(lastSelectedState,
								new Place(placeName));
						((DefaultListModel)selectionView.getPlaceList()
								.getModel()).removeElement(placeName);
						selectionView.getStateList().setSelectedValue(
								lastSelectedState, true);
					}	// end method
				}	// end class
		);
		
		//Delete All Places Button//////////////////////////////////////////////
		this.selectionView.getDeleteAllPlacesButton().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						
						int option = JOptionPane.showConfirmDialog(
								selectionView, 
								"Are you sure you wish to delete all place " +
								"records for this state??", 
								"Delete All Places", 
								JOptionPane.YES_NO_OPTION, 
								JOptionPane.QUESTION_MESSAGE);
						
						switch(option) {
						case JOptionPane.YES_OPTION:
							if(selectionView.getStateList()
									.getSelectedValue() != null) {
								if(selectionView.getPlaceList().getModel()
										.getSize() > 0) {
									((DefaultListModel)selectionView
											.getPlaceList().getModel())
											.removeAllElements();
									lastSelectedState = (String)selectionView
									.getStateList().getSelectedValue();
									selectionView.getModel()
									.clearPlaces(lastSelectedState);
									selectionView.getStateList()
									.setSelectedValue(lastSelectedState, true);
								}	// end if
							}	// end if
						}	// end switch
					}	// end method
				}	// end class
		);
	}	// end method
	
	////////////////////////
	// Accessors          //
	////////////////////////////////////////////////////////////////////////////
	/**
	 * <p>
	 * This method acquires the value that is assigned to the 
	 * <code>selectionView</code> assigned to this controller.
	 * </p>
	 * 
	 * @return The SelectionView that is assigned to this controller.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  There must be a value assigned to <code>selectionView</code>.
	 *           </dd>
	 * <dd>POST: The value stored by <code>selectionView</code> is returned to
	 *           the requester.</dd>
	 * </dt>
	 */
	public SelectionView getView() {
		return this.selectionView;
	}	// end method
}	// end class