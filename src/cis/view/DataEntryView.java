package cis.view;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import cis.model.Data;
import cis.model.Place;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;

/**
 * DataEntryView.java
 * 
 * CS2334 010 Programming Structures and Abstractions
 * Project #4
 * April 4, 2011
 * 
 * <p>
 * This class contains the view that will be responsible for manipulating the 
 * data that is contained with the model.
 * </p>
 * 
 * @version 1.0
 *
 */
public class DataEntryView extends JDialog implements ActionListener {
	/** Eclipse generated serial version id. */
	private static final long serialVersionUID = 5138995355010150525L;
	/** The tablemodel for this table that will visually store the data. */
	private DataEntryTableModel tableModel;
	/** The table that will make the tablemodel visible for editing. */
	private JTable            table;
	/** A scroller so all data can be viewable. */
	private JScrollPane       scroller;
	/** The place that contains the data to be rendered to this table. */
	private Place place;
	/** The column names for this table. */
	private Object[] columns = { "Income Level", "Number of Households" };
	
	///////////////////////////
	// Constructors          //
	////////////////////////////////////////////////////////////////////////////
	/**
	 * <p>
	 * Default constructor for this class that takes no arguments.  This 
	 * constructor will create and initialize the GUI for user interaction.
	 * </p>
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  All GUI components need to be initialized.</dd>
	 * <dd>POST: All GUI elements are assigns and the layout is created and made
	 *           viewable to the user.</dd>
	 * </dt>
	 */
	public DataEntryView(Place place) {
		this.place      = place;
		this.tableModel = new DataEntryTableModel(columns, 0);
		this.table      = new JTable(tableModel);
		this.scroller   = new JScrollPane(table);
		
		this.table.setColumnSelectionAllowed(false);
		
		this.add(new JLabel(place.getName()), BorderLayout.NORTH);
		this.add(this.scroller, BorderLayout.CENTER);
		this.setModal(true);
		
		this.setData(place);
		
		this.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-200, 
				Toolkit.getDefaultToolkit().getScreenSize().height/2-300, 400, 
				600);
	}	// end constructor
	
	////////////////////////
	// Mutators           //
	////////////////////////////////////////////////////////////////////////////
	/**
	 * <p>
	 * Assigns the value to be manipulated by the user / or displayed in the 
	 * table.
	 * </p>
	 * 
	 * @param place The value to be manipulated by the table.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>tableModel</code> must be assigned and initialized.</dd>
	 * <dd>POST: <code>tableModel</code> is populated by the data contents of 
	 *           the value passed.</dd>
	 */
	public void setData(Place place) {
		// TODO Populate the tableModel with the data of the place
		Iterator<Data> data = place.getData().values().iterator();
		
		while(data.hasNext()) {
			Data next = data.next();
			String[] row = {next.getIncomeRange(), next.getHouseholds()
					.toString()};
			this.tableModel.addRow(row);
		}
	}	// end method
	
	///////////////////////////
	// Accessors             //
	////////////////////////////////////////////////////////////////////////////
	/**
	 * <p>
	 * Acquires a new Place representation of the data contained within the 
	 * <code>tableModel</code>.
	 * </p>
	 * 
	 * @return A Place containing the data in the <code>tableModel</code>.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>tableModel</code> must be assigned a value.</dd>
	 * <dd>POST: The values stored in <code>tableModel</code> populate a new 
	 *           Place, which is returned to the requester.</dd>
	 * </dt>
	 */
	public Place getData() {
		return this.place;
	}	// end method

	/**
	 * <p>
	 * Acquires the table that is assigned to this view component.
	 * </p>
	 * 
	 * @return The Table assigned to this view.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  The table must be assigned a value.</dd>
	 * <dd>POST: The table is returned to the user.</dd>
	 * </dt>
	 */
	public JTable getTable() {
		return this.table;
	}
	
	/**
	 * <p>
	 * Updates the table based on a change in data.  Since this window is modal, 
	 * this update may not be performed.
	 * </p>
	 * 
	 * @param ae The Event that was triggered.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  The view must be initialized and assigned.</dd>
	 * <dd>POST: Thew view is updated accordingly.</dd>
	 * </dt>
	 *
	 */
	public void actionPerformed(ActionEvent ae) {
		Place place = (Place)ae.getSource();
		this.setData(place);
	}	// end method
	
	/**
	 * This class defines the basic model (Default Table Model, but allows for 
	 * the first cell to not be edited.
	 */
	class DataEntryTableModel extends DefaultTableModel {
		/** Eclipse generate serial version ID */
		private static final long serialVersionUID = -3952405620754756307L;

		/** Argumented constructor that is used in this view. */
		public DataEntryTableModel(Object[] columns, int rows) {
			super(columns, rows);
		}	// end constructor
		
		/**
		 * Makes the first column uneditable since these are static values.
		 */
		@Override
		public boolean isCellEditable(int row, int column) {
			if(column == 0) {
				return false;
			} else {
				return true;
			}	// end if
		}	// end method
	}	// end class
}	// end class