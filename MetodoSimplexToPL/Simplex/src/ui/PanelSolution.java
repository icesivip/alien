/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.net.InterfaceAddress;
import java.time.Clock;
import java.util.Enumeration;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Luis
 */
public class PanelSolution extends javax.swing.JPanel {

    private LinealProgrammingInterface ui;
    /**
     * Creates new form PanelSolution with n initial variables, 
     */
    public PanelSolution(LinealProgrammingInterface inter, String[] equations, int nVariables) {
        ui = inter;
        initComponents();
        for (int i = 0; i < equations.length; i++) {
                System.out.println(equations[i]);
        }
        //el +1 es para la Z y la constante
       String [] nameVars = new String[nVariables+1];
       String [] equation = equations[0].split(" ");
            for (int j = 0; j <= nVariables; j++) {
                nameVars[j] = equation[j*2+1];
            }
        TabMatrix = new JTable(equations.length, nVariables+2);
        JTableHeader tableHeader = TabMatrix.getTableHeader();
        TableColumnModel tableColumnModel = tableHeader.getColumnModel();
         for (int j = 0; j < nameVars.length; j++) {
                tableColumnModel.getColumn(j).setHeaderValue(nameVars[j]);
            }
                tableHeader.repaint();
        for (int i = 0; i < equations.length; i++) {
            equation = equations[i].split(" ");
            int j;
            for (j = 0; j <= nVariables; j++) {
                
                TabMatrix.setValueAt(Double.parseDouble(equation[j*2]), i, j);
            }
            TabMatrix.setValueAt(equation[j*2+1], i, j);
        }
        jScrollPane1.setViewportView(TabMatrix);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TabMatrix = new javax.swing.JTable();
        labIteration = new javax.swing.JLabel();
        panOperations = new javax.swing.JPanel();
        labOperations = new javax.swing.JLabel();
        butNext = new javax.swing.JButton();
        butBack = new javax.swing.JButton();

        TabMatrix.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Z", ""}
            },
            new String [] {
                "Variables", "Z"
            }
        ));
        jScrollPane1.setViewportView(TabMatrix);

        labIteration.setText("Iteration: ");

        labOperations.setText("Operations");

        javax.swing.GroupLayout panOperationsLayout = new javax.swing.GroupLayout(panOperations);
        panOperations.setLayout(panOperationsLayout);
        panOperationsLayout.setHorizontalGroup(
            panOperationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panOperationsLayout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(labOperations)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panOperationsLayout.setVerticalGroup(
            panOperationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panOperationsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labOperations)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        butNext.setText("Next");
        butNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butNextActionPerformed(evt);
            }
        });

        butBack.setText("Back");
        butBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                            .addComponent(panOperations, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(labIteration)
                        .addGap(179, 179, 179))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(butBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(butNext)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 3, Short.MAX_VALUE)
                .addComponent(labIteration)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panOperations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butNext)
                    .addComponent(butBack))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void butNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butNextActionPerformed
        double[][] itMatrix = ui.nextIteration();
        fillMatrix(itMatrix);
    }//GEN-LAST:event_butNextActionPerformed

    private void butBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butBackActionPerformed
        double[][] itMatrix = ui.lastIteration();
        fillMatrix(itMatrix);
    }//GEN-LAST:event_butBackActionPerformed

    private void fillMatrix(double[][] itMatrix){
        TabMatrix = new JTable(itMatrix.length, itMatrix[0].length+1);
        JTableHeader tableHeader = TabMatrix.getTableHeader();
        TableColumnModel tableColumnModel = tableHeader.getColumnModel();
        String[] varNames = ui.getVarNames();
        tableColumnModel.getColumn(0).setHeaderValue("Z");
         for (int j = 1; j < itMatrix[0].length; j++) {
                tableColumnModel.getColumn(j).setHeaderValue(varNames[j-1]);
            }
                tableHeader.repaint();
                TabMatrix.setValueAt(1.00, 0, 0);
        for (int i = 0; i < itMatrix.length; i++) {
            if(i>0)
                TabMatrix.setValueAt(0, i, 0);
            for (int j = 1; j < TabMatrix.getColumnCount(); j++) {
                TabMatrix.setValueAt(itMatrix[i][j-1], i, j);
            }
        }
        jScrollPane1.setViewportView(TabMatrix);
        if(ui.getResultType() != null)
            JOptionPane.showMessageDialog(this, ui.getResultType());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabMatrix;
    private javax.swing.JButton butBack;
    private javax.swing.JButton butNext;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labIteration;
    private javax.swing.JLabel labOperations;
    private javax.swing.JPanel panOperations;
    // End of variables declaration//GEN-END:variables
}
