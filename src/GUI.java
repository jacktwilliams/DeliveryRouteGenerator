/*
 * XP Programming - Route Generator Project
 * CS410 - Software Engineering
 * Professor Sudharsan Iyengar
 * January 30, 2019
 * Jack Williams & Chang Moua
 * 
 * Gui class
 */

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class GUI extends JFrame{
	Listener l;
	
	private JPanel topPanel = new JPanel();
	private JTextPane resultPane = new JTextPane();
	JScrollPane scroll = new JScrollPane(resultPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JButton firstButton = new JButton("Start Generator");
	private JButton secondButton = new JButton("Exact Location");
	private JButton thirdButton = new JButton("Approximate Location");
	private JButton bestRouteB = new JButton("Optimum Route");
	private JButton heuristicsB = new JButton("Use Heuristics");
	
	private final String EXACTHINT = "Exact Location Mode uses the internet to find the coordinates of each city. It is guaranteed to produce the shortest route between cities.";
	private final String APPROXHINT = "Approximate Location Mode approximates distances between cities using zip codes. This will work regardless of internet connection.";
	private final String GENHINT = "Read Address and Layout files and produce a delivery route.";
	private final String BESTHINT = "Checks every possible route to find the best route between cities.";
	private final String HEURISTICHINT = "Uses heuristics to find a good route. Useful for 10+ cities.";
	
	public GUI() {
		
		//Frame
		this.setSize(1000, 800);
		this.setTitle("Route Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Buttons 
		Dimension d = new Dimension(100, 100);
		Font f1 = new Font("Arial", Font.BOLD, 20);
		firstButton.setMinimumSize(d);
		firstButton.setFont(f1);
		firstButton.setToolTipText(GENHINT);
		secondButton.setMaximumSize(d);
		secondButton.setFont(f1);
		secondButton.setToolTipText("[SELECTED]" + EXACTHINT);
		thirdButton.setMaximumSize(d);
		thirdButton.setFont(f1);
		thirdButton.setToolTipText(APPROXHINT);
		bestRouteB.setMaximumSize(d);
		bestRouteB.setFont(f1);
		bestRouteB.setToolTipText(BESTHINT);
		heuristicsB.setMaximumSize(d);
		heuristicsB.setFont(f1);
		heuristicsB.setToolTipText(HEURISTICHINT);
		
		//Top Panel layout
		topPanel.setPreferredSize(new Dimension(1200, 60));
		topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		topPanel.add(firstButton);
		topPanel.add(secondButton);
		topPanel.add(thirdButton);
		topPanel.add(bestRouteB);
		topPanel.add(heuristicsB);
		
		//result TextPane layout
		resultPane.setPreferredSize(new Dimension(500, 800));
		resultPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//BorderLayout
		this.setLayout(new BorderLayout());
		this.getContentPane().add(scroll);
		getContentPane().add(BorderLayout.NORTH, topPanel);
		getContentPane().add(BorderLayout.SOUTH, resultPane);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		JOptionPane.showMessageDialog(null, "Click any of the buttons to start the program\n"
				+ "Hover over them to get a short description");
		
		//start with exactLocation and Best Route Selected
		this.resetButton2();
		this.selectBestB();
	}
	
	public void disableGeneratorButton() {
		firstButton.setEnabled(false);
	}
	
	public void enableGeneratorButton() {
		firstButton.setEnabled(true);
	}
	
	//disables Exact location button
	public void resetButton2() {
		secondButton.setEnabled(false);
		secondButton.setToolTipText("[SELECTED]" + EXACTHINT);
		thirdButton.setEnabled(true);
		thirdButton.setToolTipText(APPROXHINT);
	}
	
	//disables approximate location button
	public void resetButton3() {
		secondButton.setEnabled(true);
		secondButton.setToolTipText(EXACTHINT);
		thirdButton.setEnabled(false);
		thirdButton.setToolTipText("[SELECTED]" + APPROXHINT);
	}
	
	public void selectBestB() {
		bestRouteB.setEnabled(false);
		heuristicsB.setEnabled(true);
	}
	
	public void selectHeuristicsB() {
		heuristicsB.setEnabled(false);
		bestRouteB.setEnabled(true);
	}
	
	public void setOutput(String s) {
		Font f2 = new Font("Arial", Font.PLAIN, 15);
		resultPane.setFont(f2);
		resultPane.setText(s);
	}
	
	public void appendOutput(String s) {
		Font f2 = new Font("Arial", Font.PLAIN, 15);
		resultPane.setFont(f2);
		resultPane.setText(resultPane.getText() + s);
	}

	public void setListener(Listener myList) {
		l = myList;
		
		firstButton.addActionListener(myList);
		secondButton.addActionListener(myList);
		thirdButton.addActionListener(myList);
	}
	
}
