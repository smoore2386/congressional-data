package cis;

import javax.swing.UIManager;

import cis.controller.CensusController;

/**
 * Main.java
 * 
 * CS2334 010 Programming Structures and Abstractions
 * Project #4
 * April 4, 2011
 * 
 * Matthew Crist
 * Shane Moore
 * 
 * <p>
 * This class acts as the Driver for the CIS environment.  It will utilize 
 * the Controller and instantiate it accordingly.  This class is also 
 * responsible for processing arguments that are passed from the command line
 * and from programs executing this program.
 * </p> 
 * 
 * @version 1.0
 *
 */
public class Main {
	/**
	 * <p>
	 * This is the main entry point for the program to be executed.
	 * </p>
	 * 
	 * @param args The arguments that are passed to the program from another 
	 *  		   program or the command line.
	 */
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		// Warning is redundant....
		CensusController controller = new CensusController();
	}	// end method
}	// end class