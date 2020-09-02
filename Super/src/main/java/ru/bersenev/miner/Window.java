/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.bersenev.miner;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * @author �
 */
public class Window extends javax.swing.JFrame {
    private Save save;
    private CastomButton[][] massButton;
    private boolean[][] booleanMassBomb;
    private int kolFlag;

    private Bomb[] objectMassBomb;
    private boolean permissionToPlay;



    /**
     * Creates new form Window
     */
    public Window() {
        save = new Save();
        initComponents();
        save.setLength(10);
       save.setKolBomb(20);
        save.setNomRadioButton(2);

    }

    public void start() {

        permissionToPlay = true;
        kolFlag = 0;

        objectMassBomb = new Bomb[save.getKolBomb()];
        jLabel3.setText("���������� ���� �� ���� : " + Integer.toString(save.getKolBomb()));
        jPanel2.removeAll();
        jLabel2.setText("���������� ������ : " + Integer.toString(kolFlag));
        jLabel1.setText("-");

        initBomb();
        initButton();
        jPanel2.revalidate();
        jPanel2.repaint();
    }

    private void initBomb() {
        booleanMassBomb = new boolean[save.getLength()][save.getLength()];
        int prohod = 0;
        int i;
        int j;
        Random rand = new Random();
        while (prohod < save.getKolBomb()) {
            i = rand.nextInt(save.getLength());
            j = rand.nextInt(save.getLength());
            if (booleanMassBomb[i][j])
                continue;
            booleanMassBomb[i][j] = true;
            objectMassBomb[prohod] = new Bomb(i, j);
            prohod++;
        }
        for (boolean[] m : booleanMassBomb) {
            for (boolean n : m) {
                if (n)
                    System.out.print("1 ");
                else
                    System.out.print("0 ");
            }
            System.out.print("\n");
        }
        System.out.print("\n \n");
    }

    private void initButton() {

        massButton = new CastomButton[save.getLength()][save.getLength()];

        java.awt.GridBagConstraints gridBagConstraints;
        gridBagConstraints = new java.awt.GridBagConstraints();
        for (int i = 0; i < save.getLength(); i++) {
            for (int j = 0; j < save.getLength(); j++) {
                massButton[i][j] = new CastomButton(i, j);
                massButton[i][j].addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent event) {
                        CastomButton button = (CastomButton) event.getSource();
                        if (event.getButton() == MouseEvent.BUTTON3) {
                            mouseRight(button);
                        }
                        if (event.getButton() == MouseEvent.BUTTON1) {
                            mouseLeft(button);
                        }
                    }
                });
                massButton[i][j].setText("*");
                massButton[i][j].setPreferredSize(new java.awt.Dimension(40, 40));

                gridBagConstraints.gridx = j;
                gridBagConstraints.gridy = i;
                jPanel2.add(massButton[i][j], gridBagConstraints);
            }
        }
    }

    private void mouseRight(CastomButton button) {
        if (permissionToPlay && save.getKolBomb() != kolFlag) {
            if ((button.getText()).equals("*")) {
                button.setText("#");
                kolFlag++;
                jLabel2.setText("���������� ������ : " + Integer.toString(kolFlag));
                jPanel2.revalidate();
                jPanel2.repaint();
                return;
            }
        }
        if (permissionToPlay && (button.getText()).equals("#")) {
            button.setText("*");
            kolFlag--;
            jLabel2.setText("���������� ������ : " +Integer.toString(kolFlag));
            jPanel2.revalidate();
            jPanel2.repaint();
            return;
        }

    }

    private void mouseLeft(CastomButton button) {
        if (permissionToPlay) {
            if ((button.getText()).equals("#")) {
                kolFlag--;
                jLabel2.setText(Integer.toString(kolFlag));
                jPanel2.revalidate();
                jPanel2.repaint();
            }
            if (booleanMassBomb[button.getCastomHeight()][button.getCastomWidth()]) {
                button.setText("@");
                jLabel1.setText("vi proigrali");
                permissionToPlay = false;
            } else {
                numberBomb(button);
            }
        }
    }

    private void numberBomb(CastomButton button) {
        int kol = 0;
        for (int i = button.getCastomHeight() - 1; i <= button.getCastomHeight() + 1; i++) {
            for (int j = button.getCastomWidth() - 1; j <= button.getCastomWidth() + 1; j++) {
                if (i < 0 || i >= save.getLength() || j < 0 || j >= save.getLength())
                    continue;
                if ((i == button.getCastomHeight()) && (j == button.getCastomWidth()))
                    continue;
                if (booleanMassBomb[i][j])
                    kol++;

            }
        }
        switch (kol) {
            case 1:
              //  button.setForeground(Color.blue);
                button.setForeground(new Color(255, 93, 91));
                break;
            case 2:
                button.setForeground(new Color(103, 94, 255));
                break;
            case 3:
                button.setForeground(new Color(254, 18, 255));
                break;
            case 4:
                button.setForeground(new Color(10, 118, 255));
                break;
            case 5:

                button.setForeground(new Color(19, 20, 255));
                break;
            case 6:
                button.setForeground(new Color(76, 3, 255));
                break;
            case 7:
                button.setForeground(new Color(249, 72, 6));
                break;
            case 8:
                button.setForeground(new Color(255, 10, 25));
                break;
            default:
                button.setForeground(new Color(103, 103, 103));
                break;

        }
        button.setText(Integer.toString(kol));
        button.setEnabled(false);


        if (kol == 0) {
            for (int i = button.getCastomHeight() - 1; i <= button.getCastomHeight() + 1; i++) {
                for (int j = button.getCastomWidth() - 1; j <= button.getCastomWidth() + 1; j++) {
                    if (i < 0 || i >= save.getLength() || j < 0 || j >= save.getLength())
                        continue;
                    if (massButton[i][j].isEnabled())
                        mouseLeft(massButton[i][j]);
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        jPanel1.setPreferredSize(new java.awt.Dimension(600, 100));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        jButton1.setText("������ ����� ����");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        jButton2.setText("���� �����");
        jButton2.setToolTipText("");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        jLabel2.setText("0");
        jPanel1.add(jLabel2);

        jLabel3.setText("���������� ���� �� ����");
        jPanel1.add(jLabel3);

        jLabel1.setText("-");
        jLabel1.setToolTipText("");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1);

        jPanel2.setBackground(new java.awt.Color(230, 230, 240));
        jPanel2.setPreferredSize(new java.awt.Dimension(600, 600));
        jPanel2.setLayout(new java.awt.GridBagLayout());
        getContentPane().add(jPanel2);

        jMenu1.setText("����");

        jMenuItem1.setText("���������");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (save.getKolBomb() == kolFlag) {
            for (Bomb bomb : objectMassBomb) {
                if (!massButton[bomb.getHeight()][bomb.getWidth()].getText().equals("#")) {
                    jLabel1.setText("nepravilno postavlen flag");
                    permissionToPlay = false;
                    return;
                }
            }
            jLabel1.setText("vi viigrali");
            permissionToPlay = false;

        } else {
            jLabel1.setText("kol bomb > flagov");
            permissionToPlay = false;
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       new NewGame(window,save).setVisible(true);

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     start();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                 window = new Window();
                window.setVisible(true);
            }
        });
    }
static Window window;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
