package cis.view;

import cis.model.Data;
import cis.model.Place;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * PieChartView.java
 * 
 * CS2334 010 Programming Structures and Abstractions
 * Project #4
 * April 4, 2010
 * 
 * <p>
 * This class is responsible for make that data that is contained within a Place 
 * class visible to the user.  This data is salary demographics of a certain 
 * county/metro area/ etc.
 * </p>
 *
 * @version 1.0
 * 
 */
public class PieChartView extends JPanel implements ActionListener {
	/** Eclipse generated serial version id. */
	private static final long serialVersionUID = -3813987372362753735L;
	/** This Place that holds the data that will be registered with this 
	    view component. */
	private Place place;
	/** The frame that will contain this pie chart view. */
	private JFrame frame;
	/** The name of the state that is being graphed. */
	private String stateName;
	/** Colors to use on the chart. */
	private Color[] colors = {
			Color.blue, 
			Color.cyan, 
			Color.darkGray, 
			Color.gray, 
			Color.green, 
			Color.lightGray, 
			Color.magenta, 
			Color.orange, 
			Color.pink, 
			Color.red, 
			Color.yellow, 
			Color.white, 
			Color.getHSBColor(0.9f, 0.6f, 0.3f), 
			Color.getHSBColor(0.6f, 0.4f, 0.9f), 
			Color.getHSBColor(0.7f, 0.7f, 0.4f), 
			Color.getHSBColor(0.3f, 0.6f, 0.7f)
	};
	
	///////////////////////
	// Constructors      //
	////////////////////////////////////////////////////////////////////////////	
	/**
	 * <p>
	 * Argumented constructor that will create a Pie Chart and display the GUI 
	 * with the data stored in the Place that is passed.
	 * </p>
	 * 
	 * @param place The Place that contains the data that key will be graphed.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  All GUI elements need to be initialized.</dd>
	 * <dd>POST: All GUI elements are assigned values and <code>place</code> is 
	 *           assigned the value passed.  <code>place</code> is then graphed 
	 *           by the view and the data is made visible to the requester.</dd>
	 * </dt>
	 */
	public PieChartView(Place place, String state) {
		this.stateName = state;
		this.place     = place;
		
		frame = new JFrame(place.getName() + ", " + stateName);
		
		frame.add(this);
		frame.setResizable(false);
		frame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 
				300, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 
				300, 600, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}	// end constructor
	
	///////////////////////////////////
	// Mutators                      //
	////////////////////////////////////////////////////////////////////////////
	/**
	 * <p>
	 * The action that will be performed when this action listener is trigger by 
	 * a change in the data model.
	 * </p>
	 * 
	 * @param e The event that triggered this action listener.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  This class must be assign as an action listener to the data 
	 *           model.</dd>
	 * <dd>POST: This class will perform a repaint of the panel upon data 
	 *           change.</dd>
	 * </dt>
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Place updatedPlace = (Place)e.getSource();
		this.setPlace(updatedPlace, stateName);
	}	// end method
	
	/**
	 * <p>
	 * This method will paint this view with custom elements of the key, title 
	 * and the actual pie chart that shows the data.
	 * </p>
	 * 
	 * @param g The graphics class that will hold all the functions for painting 
	 *          this panel with custom elements.
	 *          
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  There must be a <code>place</code> assigned to this class.</dd>
	 * <dd>POST: The data that is contained within <code>place</code> is drawn 
	 *           to the panel.</dd>
	 * </dt>
	 */
	@Override
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, 600, 600);
		this.drawTitle(g);
		this.drawChart(g);
		this.drawKey(g);
	}	// end method
	
	/**
	 * <p>
	 * Paints the key for each pie slice to the panel.
	 * </p>
	 * 
	 * @param g The Graphics class that contains the functions to paint to this 
	 *          panel.
	 *          
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>place</code> must be assigned a value.</dd>
	 * <dd>POST: The key is constructed from data contained within 
	 *           <code>place</code>.</dd>
	 * </dt>
	 */
	private void drawKey(Graphics g) {
		Iterator<Data> data = place.getData().values().iterator();
		int y_offset = 30;
		int i = 0;
		
		while(data.hasNext()) {
			g.setColor(colors[i]);
			g.fillRect(420, y_offset, 10, 10);
			g.setColor(Color.BLACK);
			g.drawRect(420, y_offset, 11, 11);
			g.drawString(data.next().getIncomeRange(), 435, y_offset+10);
			i++;
			y_offset += 30;
		}	// end while
	}	// end method
	
	/**
	 * <p>
	 * Paints the pie chart to the panel.
	 * </p>
	 * 
	 * @param g The Graphics class that contains the functions to paint to this 
	 *          panel.
	 *          
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>place</code> must be assigned a value.</dd>
	 * <dd>POST: The chart is constructed from data contained within 
	 *           <code>place</code>.</dd>
	 * </dt>
	 */
	private void drawChart(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawOval(10, 75, 400, 400);
		
		Iterator<Data> values = place.getData().values().iterator();
		
		int totalHouseholds = place.getTotalHouseholds();
		int totalAngle      = 0;
		int i = 0;	// used for coloring
		
		while(values.hasNext()) {
			Data value = values.next();
			
			if(value.getHouseholds() == 0) {
				// Do not draw this entry
			} else {
				// Draw it!
				float angle = ((float)value.getHouseholds() / 
						(float)totalHouseholds) * 360f;
				totalAngle += (int)Math.round(angle);
				
				g.setColor(colors[i]);
				g.fillArc(10, 75, 400, 400, totalAngle-Math.round(angle), 
						Math.round(angle));
				i++;
				g.setColor(Color.BLACK);
				g.drawArc(10, 75, 400, 400, totalAngle-Math.round(angle), 
						Math.round(angle));
			}	// end if
		}	// end while
	}	// end method
	
	/**
	 * <p>
	 * Paints the title to the panel.
	 * </p>
	 * 
	 * @param g The Graphics class that contains the functions to paint to this 
	 *          panel.
	 *          
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>place</code> must be assigned a value.</dd>
	 * <dd>POST: The title is constructed from data contained within 
	 *           <code>place</code>.</dd>
	 * </dt>
	 */
	private void drawTitle(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawString(this.place.getName() + ", " + this.stateName, 10, 20);
	}	// end method
	
	///////////////////////////////////
	// Accessors                     //
	////////////////////////////////////////////////////////////////////////////
	/**
	 * <p>
	 * Acquires the place that is being drawn to this panel.
	 * </p>
	 * 
	 * @return The value stored by <code>place</code>.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  <code>place</code> must be assigned a value.</dd>
	 * <dd>POST: The value stored by <code>place</code> is returned to the 
	 *           requester.</dd>
	 * </dt>
	 */
	public Place getPlace() {
		return this.place;
	}	// end method
	
	/**
	 * <p>
	 * Sets the place that will be graphed to the panel.
	 * </p>
	 * 
	 * @param place The place that contains the data to be graphed.
	 * @param stateName The name of the state to be graphed.
	 * 
	 * <dt><b>Conditions:</b>
	 * <dd>PRE:  The value passed must contain data to be graphed.</dd>
	 * <dd>POST: The data is pie charted.</dd>
	 * </dt>
	 */
	public void setPlace(Place place, String stateName) {
		this.place = place;
		repaint();
	}	// end method
}	// end class