package ui;

import java.awt.*;

import javax.swing.*;

/*
 * Principal class of ui
 */
public class SimplexInterface extends JFrame {

	//Attributes
	private JTextField textEcuation;
	private JButton buttonNextStep;
	private JButton buttonBackStep;
	private JButton buttonAddEcuation;
	private String objetiveFuntion;
	private int numberVariables;
	private int numberConstraints;
	private String objectiveCriterio;
	private JLabel[] labss;
	private JTextField[] txtss;
	
	public SimplexInterface() {
		
		
		objetiveFuntion = JOptionPane.showInputDialog(this, "Ingrese la función objetivo:");
		numberVariables = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el número de variables:"));
		numberConstraints = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el número de restricciones:"));
		//JFrame properties
		setTitle("Simplex Algorith");
		setSize(new Dimension(500, 500));
		setLayout(new BorderLayout());
	
		
		
		//Inicialization of attributes
		textEcuation = new JTextField();
		buttonNextStep = new JButton("Next");
		buttonBackStep = new JButton("Back");
		buttonAddEcuation = new JButton("Add Ecuation");
	
		
		add(buttonAddEcuation, BorderLayout.NORTH);
		
		
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(1,2,4,4));
		aux.add(buttonBackStep);
		aux.add(buttonNextStep);
		add(aux, BorderLayout.SOUTH);
		//add(textEcuation, BorderLayout.NORTH);
		
		
	}
	
	public static void main(String[] args) {
		SimplexInterface inter = new SimplexInterface();
		inter.setVisible(true);
		inter.setLocationRelativeTo(null);
		
	}

	public void iniciarRestricciones (int var, int restr) {
		labss = new JLabel[var*restr];
		txtss = new JTextField[var*restr];
		for (int i = 0; i < labss.length; i++) {
			for (int j = 0; j < restr; j++) {
				
			}
		}
	}
	
}
