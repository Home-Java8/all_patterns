package com.multithreading.dialogpackage;

/*
* It's graphical user interface class
*
*/
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.*;

/**
 *
 * http://javatalks.ru/topics/4446
 *
 * @author Admin
 */
public class DialogFrame extends javax.swing.JFrame {
    private FileToParse fileToParse;
    private SelectedAlphabet alphabet;
    private Thread thread = null;
    boolean dynOutline = false;
    /** Creates new form DialogFrame */
    public DialogFrame() throws IOException {
        fileToParse = new FileToParse("heap.txt");
        alphabet = new SelectedAlphabet("alphabet.txt");
        initComponents();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jProgressBar1 = new javax.swing.JProgressBar();
        jButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("I would like to work for PL");
        setResizable(false);

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new AlphabetModel());
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Get entries to selected file");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton1MouseReleased(evt);
            }
        });

        jCheckBox1.setText("Dynamic output");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem1.setText("Open");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseReleased(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Alphabet");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem2MouseReleased1(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Exit");
        jMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem3MouseReleased(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox1)
                                .addContainerGap(80, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jCheckBox1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>
    private void jMenuItem1MouseReleased(java.awt.event.MouseEvent evt) {
        fileToParse = new FileToParse (evt);
    }

    private void jMenuItem2MouseReleased1(java.awt.event.MouseEvent evt) {
        try {
            alphabet = new SelectedAlphabet(evt);
        } catch (IOException ex) {
            Logger.getLogger(DialogFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        dynOutline = true;
    }

    private void jMenuItem3MouseReleased(java.awt.event.MouseEvent evt) {
        this.setVisible(false);
        this.dispose();
    }

    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {
// try {
        jButton1.setEnabled(false);
        jMenu1.setEnabled(false);
        jProgressBar1.setValue(0);
        thread = new Thread() {
            @Override public void run(){
                try {
                    AlphabetModel aModel = (AlphabetModel) (jTable1.getModel());
                    alphabet.loadAlphabet();
                    alphabet.initTableByAlphabet((AlphabetModel)jTable1.getModel());
//-------------------------------------------------------------
                    SwingUtilities.invokeLater(new TextParser(jTable1, alphabet,
                            fileToParse, jProgressBar1, dynOutline, (AlphabetModel)jTable1.getModel()));
                } catch (IOException ex) {
                    Logger.getLogger(DialogFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        thread.start();
        jMenu1.setEnabled(true);
        jButton1.setEnabled(true);
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new DialogFrame().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(DialogFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
// End of variables declaration
}
