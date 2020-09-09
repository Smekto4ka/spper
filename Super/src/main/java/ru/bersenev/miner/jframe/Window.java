/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.bersenev.miner.jframe;

import ru.bersenev.miner.user.service.User;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
// long start = System.currentTimeMillis();

/**
 * @author я
 */
public class Window extends javax.swing.JFrame {
    private long time;
    private CastomButton[][] massButton;
    private boolean[][] booleanMassBomb;
    private int kolFlag;
    private User user;
    private Bomb[] objectMassBomb;
    private boolean permissionToPlay;


    /**
     * Creates new form Window
     */
    public Window() {

        initComponents();
        CentreWindow.centreWindow(this);


    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public void start(User user) {
        this.user = user;
        permissionToPlay = true;
        kolFlag = 0;

        objectMassBomb = new Bomb[user.getKolBomb()];
        jLabel3.setText("количество бомб на поле : " + Integer.toString(user.getKolBomb()));
        jPanel2.removeAll();
        jLabel2.setText("количество флагов : " + Integer.toString(kolFlag));
        jLabel1.setText("-");

        initBomb();
        initButton();
        jPanel2.revalidate();
        jPanel2.repaint();
        time = System.currentTimeMillis();
    }

    private void initBomb() {
        booleanMassBomb = new boolean[user.getLength()][user.getLength()];
        int prohod = 0;
        int i;
        int j;
        Random rand = new Random();
        while (prohod < user.getKolBomb()) {
            i = rand.nextInt(user.getLength());
            j = rand.nextInt(user.getLength());
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

        massButton = new CastomButton[user.getLength()][user.getLength()];

        java.awt.GridBagConstraints gridBagConstraints;
        gridBagConstraints = new java.awt.GridBagConstraints();
        for (int i = 0; i < user.getLength(); i++) {
            for (int j = 0; j < user.getLength(); j++) {
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
                massButton[i][j].setPreferredSize(new java.awt.Dimension(50, 50));

                gridBagConstraints.gridx = j;
                gridBagConstraints.gridy = i;
                jPanel2.add(massButton[i][j], gridBagConstraints);
            }
        }
    }

    private void mouseRight(CastomButton button) {
        if (permissionToPlay && user.getKolBomb() != kolFlag) {
            if ((button.getText()).equals("*")) {
                button.setText("#");
                kolFlag++;
                jLabel2.setText("количество флагов : " + Integer.toString(kolFlag));
                jPanel2.revalidate();
                jPanel2.repaint();
                return;
            }
        }
        if (permissionToPlay && (button.getText()).equals("#")) {
            button.setText("*");
            kolFlag--;
            jLabel2.setText("количество флагов : " + Integer.toString(kolFlag));
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
                if (i < 0 || i >= user.getLength() || j < 0 || j >= user.getLength())
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
                    if (i < 0 || i >= user.getLength() || j < 0 || j >= user.getLength())
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
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        jPanel1.setPreferredSize(new java.awt.Dimension(600, 100));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        jButton1.setText("начать новую игру");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        jButton2.setText("дать ответ");
        jButton2.setToolTipText("");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        jLabel2.setText("0");
        jPanel1.add(jLabel2);

        jLabel3.setText("количество бомб на поле");
        jPanel1.add(jLabel3);

        jLabel1.setText("-");
        jLabel1.setToolTipText("");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1);

        jPanel2.setBackground(new java.awt.Color(230, 230, 240));
        jPanel2.setPreferredSize(new java.awt.Dimension(600, 600));
        jPanel2.setLayout(new java.awt.GridBagLayout());
        getContentPane().add(jPanel2);

        jMenu1.setText("Меню");

        jMenuItem1.setText("настройки");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("сменить пользователя");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("таблица результатов");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (user.getKolBomb() == kolFlag) {
            for (Bomb bomb : objectMassBomb) {
                if (!massButton[bomb.getHeight()][bomb.getWidth()].getText().equals("#")) {
                    jLabel1.setText("nepravilno postavlen flag");
                    permissionToPlay = false;
                    return;
                }
            }
            if (permissionToPlay) {
                time = System.currentTimeMillis() - time;
            }
            jLabel1.setText("vi viigrali");
            permissionToPlay = false;
            new Win(user, time).setVisible(true);

        } else {
            jLabel1.setText("kol bomb > flagov");
            permissionToPlay = false;
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new Settings(window, user).setVisible(true);

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        start(user);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new Users(window).setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new ResultPane(user).setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * @param args the command line arguments
     */
  /*  public static void main(String args[]) {
        /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
     */
     /*   try {
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
      /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                 window = new Window();
                window.setVisible(true);
            }
        });
    }*/
    Window window;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
