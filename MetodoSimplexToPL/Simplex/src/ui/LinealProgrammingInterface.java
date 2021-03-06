/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Frame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import simplex.*;

/**
 *
 * @author Luis
 */
public class LinealProgrammingInterface extends javax.swing.JFrame {

    private Simplex simplex;
    private String mode;
    private JFrame mainMenu;
    /**
     * Creates new form Frame
     */
    public LinealProgrammingInterface(JFrame last) {
        initComponents();
        mainMenu = last;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        txtTitle = new javax.swing.JTextField();
        txtNVariables = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        combOptim = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtNCons = new javax.swing.JTextField();
        butBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Problem Title:");

        txtTitle.setName("txtTitle"); // NOI18N
        txtTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTitleActionPerformed(evt);
            }
        });

        jLabel2.setText("Objective Criterion");

        jLabel3.setText("Number of Variables:");

        jButton1.setText("Accept");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        combOptim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Maximize", "Minimize" }));

        jLabel4.setText("Number of Constraints:");

        txtNCons.setName("txtConstraints"); // NOI18N
        txtNCons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNConsActionPerformed(evt);
            }
        });

        butBack.setText("Back");
        butBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(30, 30, 30)
                        .addComponent(txtNVariables, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(65, 65, 65)
                        .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(butBack)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(54, 54, 54))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(combOptim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtNCons)
                                    .addGap(18, 18, 18))))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNVariables, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNCons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combOptim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(butBack))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTitleActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFrame ventana = new JFrame();
        PanelBeginnerMode panelB;
        PanelAdvanceMode panelA;
        if(mode.equals("Beginner")){
            panelB = new PanelBeginnerMode(this, txtNVariables.getText(), txtNCons.getText(), (String)combOptim.getSelectedItem(), ventana, this);
            ventana.add(panelB);
        } else {
            panelA = new PanelAdvanceMode(this, txtNVariables.getText(), txtNCons.getText(), (String)combOptim.getSelectedItem(), ventana, this);
            ventana.add(panelA);
        }
        ventana.setVisible(true);
        ventana.setTitle(txtTitle.getText());
        this.setVisible(false);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.pack();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtNConsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNConsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNConsActionPerformed

    private void butBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butBackActionPerformed
        mainMenu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_butBackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LinealProgrammingInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LinealProgrammingInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LinealProgrammingInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LinealProgrammingInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                LinealProgrammingInterface ui = new LinealProgrammingInterface(frame);
                PanelBanner ban = new PanelBanner(ui, frame);
                frame.add(ban);
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setTitle("SIMPOD");
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butBack;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> combOptim;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtNCons;
    private javax.swing.JTextField txtNVariables;
    private javax.swing.JTextField txtTitle;
    // End of variables declaration//GEN-END:variables

    void InitializeProcess(String[] equations, String opti) {
        try {
            simplex = new Simplex(opti, equations);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,ex.getMessage());
        }
    }

    double[][] nextIteration() {
        return simplex.nextIteration();
    }
    public int actualIteration() {
        return simplex.getIterationID();
    }
    public String getResultType() {
        return simplex.getMessageSol();
    }
    
    public String[] getVarNames() {
        return simplex.getEveryVariableName();
    }

    void setMode(String mode) {
        this.mode = mode;
    }

    double[][] FinalSolution(String[] equations, String opti) {
        try {
            simplex = new Simplex(opti, equations);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,ex.getMessage());
        }
        return simplex.getFinalSolution();
    }

    double[][] lastIteration() {
        double[][] alv = null;
        try {
            int iteration = simplex.getIterationID()-1;
            simplex = new Simplex(simplex.getCriterion(), simplex.getInitialM());
            alv = simplex.getActualMatrix();
            for (int i = 0; i < iteration; i++) {
                alv = nextIteration();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,ex.getMessage());
        }
        return alv;
    }

    String getSolution() {
        return simplex.getSolutionInWords();
    }

    String getOperations() {
        return simplex.getOperationsDone();
    }

    double[] getThetaColumn() {
        return simplex.getTheta();
    }

    String startAnalysis() {
        return simplex.buildAnalysis();
    }

    void getIntervals() {
       simplex.getIntervals();
    }
}
