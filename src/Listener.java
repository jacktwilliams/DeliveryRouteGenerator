/*
 * XP Programming - Route Generator Project
 * CS410 - Software Engineering
 * Professor Sudharsan Iyengar
 * January 30, 2019
 * Jack Williams & Chang Moua
 * 
 * Listener class
 * 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import org.apache.http.client.ClientProtocolException;

public class Listener implements ActionListener {
	private boolean exactLoc = true;
	private GUI g;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton clicked = (JButton) arg0.getSource();
		String text = clicked.getText();
		String outPut = " ";
		
		if(text.equals("Start Generator")) {
			g.setOutput(outPut);
			g.disableGeneratorButton();
			RouteGenDriver.startRouteGeneration(g, exactLoc);
			g.enableGeneratorButton();
			
		}else if(text.equals("Exact Location")) {
			g.resetButton2();
			exactLoc = true;
			
		}else if(text.equals("Approximate Location")) {
			g.resetButton3();
			exactLoc = false;
		}
	}
	
	public void setGui(GUI g) {
		this.g = g;
	}
}
