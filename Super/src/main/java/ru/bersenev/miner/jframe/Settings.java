/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.bersenev.miner.jframe;

//import com.sun.deploy.security.SelectableSecurityManager;


import ru.bersenev.miner.user.service.User;
import ru.bersenev.miner.user.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author я
 */
public class Settings extends javax.swing.JFrame {
    private Window windowGame;
    private User users;

    /**
     * Creates new form NewGame
     */
    public Settings(Window windowGame, User users) {
        this.users = users;
        this.windowGame = windowGame;
        initComponents();
        CentreWindow.centreWindow(this);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jSpinner1.setValue(users.getLength());
        if (users.getNomRadioButton() == 2)
            jSpinner2.setValue(users.getKolBomb());
        else
            jSpinner2.setValue(users.getPercent());
        buttonGroup();

    }

    private void buttonGroup() {
        ButtonGroup group = new ButtonGroup();
        group.add(jRadioButton1);
        group.add(jRadioButton2);
        if (users.getNomRadioButton() == 1)
            jRadioButton1.setSelected(true);
        else
            jRadioButton2.setSelected(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jSpinner2 = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(550, 300));
        getContentPane().setLayout(new java.awt.FlowLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(550, 200));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("enter the field width:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jLabel1, gridBagConstraints);

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel());
        jSpinner1.setPreferredSize(new java.awt.Dimension(60, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jSpinner1, gridBagConstraints);

        jLabel2.setText("enter the number of bombs ( < 30%):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jLabel2, gridBagConstraints);

        jRadioButton1.setText("the number of bombs is set as a percentage");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jRadioButton1, gridBagConstraints);

        jRadioButton2.setText("the number of bombs is given by the number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jRadioButton2, gridBagConstraints);

        jSpinner2.setPreferredSize(new java.awt.Dimension(60, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jSpinner2, gridBagConstraints);

        jButton1.setText("seve");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jButton1, gridBagConstraints);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (((Integer) jSpinner1.getValue()) < 5)
            users.setLength(5);
        else
            users.setLength((Integer) jSpinner1.getValue());

        if (jRadioButton1.isSelected()) {
            if ((Integer) jSpinner2.getValue() < 30) {
                users.setPercent((Integer) jSpinner2.getValue());
                users.setKolBomb((int) (Math.pow(users.getLength(), 2) * 0.01 * ((Integer) jSpinner2.getValue())));
            } else {
                users.setKolBomb((int) (Math.pow(users.getLength(), 2) * 0.3));
                users.setPercent(30);
            }
        } else {
            users.setKolBomb((Integer) jSpinner2.getValue());
            if (users.getKolBomb() > Math.pow(users.getLength(), 2) * 0.3)
                users.setKolBomb((int) (Math.pow(users.getLength(), 2) * 0.3));
        }
        if (jRadioButton1.isSelected())
            users.setNomRadioButton(1);
        else
            users.setNomRadioButton(2);
new UserService().updateUser(users);

        windowGame.start(users);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
 /*   public static void main(String args[]) {
        /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
     */
    /*    try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
  /*     java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewGame().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    // End of variables declaration//GEN-END:variables
}
