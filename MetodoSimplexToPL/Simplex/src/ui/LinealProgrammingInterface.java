/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Frame;
import javax.swing.JFrame;
import simplex.*;

/**
 *
 * @author Luis
 */
public class LinealProgrammingInterface extends javax.swing.JFrame {

    private Simplex simplex;
    private String mode;
    /**
     * Creates new form Frame
     */
    public LinealProgrammingInterface() {
        initComponents();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Problem Title:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 14, -1, -1));

        txtTitle.setName("txtTitle"); // NOI18N
        txtTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTitleActionPerformed(evt);
            }
        });
        getContentPane().add(txtTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 11, 206, -1));
        getContentPane().add(txtNVariables, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 41, -1));

        jLabel2.setText("Objective Criterion");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(193, 52, -1, -1));

        jLabel3.setText("Number of Variables:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 52, -1, -1));

        jButton1.setText("Accept");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(302, 116, -1, -1));

        combOptim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Maximize", "Minimize" }));
        getContentPane().add(combOptim, new org.netbeans.lib.awtextra.AbsoluteConstraints(293, 49, -1, -1));

        jLabel4.setText("Number of Constraints:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 93, -1, -1));

        txtNCons.setName("txtConstraints"); // NOI18N
        txtNCons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNConsActionPerformed(evt);
            }
        });
        getContentPane().add(txtNCons, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 41, -1));

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
            panelB = new PanelBeginnerMode(this, txtNVariables.getText(), txtNCons.getText(), (String)combOptim.getSelectedItem(), ventana);
            ventana.add(panelB);
        } else {
            panelA = new PanelAdvanceMode(this, txtNVariables.getText(), txtNCons.getText(), (String)combOptim.getSelectedItem(), ventana);
            ventana.add(panelA);
        }
        ventana.setVisible(true);
        ventana.setTitle(txtTitle.getText());
        this.setVisible(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.pack();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtNConsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNConsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNConsActionPerformed

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
                LinealProgrammingInterface ui = new LinealProgrammingInterface();
                PanelBanner ban = new PanelBanner(ui, frame);
                frame.add(ban);
                frame.pack();
                frame.setVisible(true);
                frame.setTitle("SIMPOD");
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
           simplex = new Simplex(opti, equations);
    }

    double[][] nextIteration() {
        return simplex.nextIteration();
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

    void FinalSolution(String[] equations, String opti) {
        simplex = new Simplex(opti, equations);
        simplex.getFinalSolution();
    }

    double[][] lastIteration() {
        
        return null;
    }
}
