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
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

@SuppressWarnings("serial")
public class GUI extends JFrame{
	Listener l;
	
	private JPanel topPanel = new JPanel();
	private JPanel botPanel = new JPanel();
	private JTextPane resultPane = new JTextPane();
	JScrollPane scroll = new JScrollPane(resultPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JButton firstButton = new JButton("Start Generator");
	private JButton secondButton = new JButton("Exact Location");
	private JButton thirdButton = new JButton("Approximate Location");
	
	private final String EXACTHINT = "Exact Location Mode uses the internet to find the coordinates of each city. It is guaranteed to produce the shortest route between cities.";
	private final String APPROXHINT = "Approximate Location Mode approximates distances between cities using zip codes. This will work regardless of internet connection.";
	private final String GENHINT = "Read Address and Layout files and produce a delivery route.";
	
	public GUI() {
		
		//Frame
		this.setSize(1200, 1000);
		this.setTitle("Route Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Buttons 
		Dimension d = new Dimension(100, 100);
		Font f1 = new Font("Arial", Font.BOLD, 30);
		firstButton.setMinimumSize(d);
		firstButton.setFont(f1);
		firstButton.setToolTipText(GENHINT);
		secondButton.setMaximumSize(d);
		secondButton.setFont(f1);
		secondButton.setToolTipText("[SELECTED]" + EXACTHINT);
		thirdButton.setMaximumSize(d);
		thirdButton.setFont(f1);
		thirdButton.setToolTipText(APPROXHINT);
		
		//Top Panel layout
		topPanel.setPreferredSize(new Dimension(1200, 60));
		//topPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		topPanel.add(firstButton);
		topPanel.add(secondButton);
		topPanel.add(thirdButton);
		
		//Botton Panel Layout
		botPanel.setPreferredSize(new Dimension(100, 450));
		//botPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		botPanel.add(scroll);
		
		//Result textpane Layout
		//resultPane.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		resultPane.setPreferredSize(new Dimension(1195, 435));
		
		//resultPane text center alignment
		StyledDocument doc = resultPane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		//BorderLayout
		this.setLayout(new BorderLayout());
		this.getContentPane().add(BorderLayout.NORTH, topPanel);
		this.getContentPane().add(BorderLayout.SOUTH, botPanel);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		JOptionPane.showMessageDialog(null, "Click any of the buttons to start the program\n"
				+ "Hover over them to get a short description");
		this.resetButton2();
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
