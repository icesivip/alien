package ui;

import javax.swing.*;
import java.awt.*;

public class PanelMatrix extends JPanel {

	private JLabel[][]labBlanks;
	
	public PanelMatrix(SimplexInterface inter, double [][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				labBlanks[i][j] = new JLabel("" + matrix[i][j]);
				add(labBlanks[i][j]);
			}
		}
		
	}
}
