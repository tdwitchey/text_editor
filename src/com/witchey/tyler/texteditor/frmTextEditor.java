/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.witchey.tyler.texteditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author TW65409
 */
public class frmTextEditor extends javax.swing.JFrame {

    //Global Variable
    File myFile;
    
    /**
     * Creates new form frmTextEditor
     */
    public frmTextEditor() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaEditor = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuNew = new javax.swing.JMenuItem();
        menuOpen = new javax.swing.JMenuItem();
        menuSave = new javax.swing.JMenuItem();
        menuSaveAs = new javax.swing.JMenuItem();
        menuExit = new javax.swing.JMenuItem();
        menuEdit = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Notepad");

        textAreaEditor.setColumns(20);
        textAreaEditor.setRows(5);
        jScrollPane1.setViewportView(textAreaEditor);

        menuFile.setText("File");

        menuNew.setText("New");
        menuNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNewActionPerformed(evt);
            }
        });
        menuFile.add(menuNew);

        menuOpen.setText("Open");
        menuOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpenActionPerformed(evt);
            }
        });
        menuFile.add(menuOpen);

        menuSave.setText("Save");
        menuSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSaveActionPerformed(evt);
            }
        });
        menuFile.add(menuSave);

        menuSaveAs.setText("Save As ...");
        menuSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSaveAsActionPerformed(evt);
            }
        });
        menuFile.add(menuSaveAs);

        menuExit.setText("Exit");
        menuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExitActionPerformed(evt);
            }
        });
        menuFile.add(menuExit);

        jMenuBar1.add(menuFile);

        menuEdit.setText("Edit");
        jMenuBar1.add(menuEdit);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpenActionPerformed
        // clear the text area
        this.textAreaEditor.setText("");
        
        //choose the file to be opened
        JFileChooser myFileChooser = new JFileChooser();
        myFileChooser.setDialogTitle("Open Text File");
        myFileChooser.showOpenDialog(this);
        
        //make sure the user did not click cancel
        if(myFileChooser.getSelectedFile() != null)
        {
            //get a file object
            myFile = new File(myFileChooser.getSelectedFile().getPath());
            
            //
            try
            {
                //create file reader object
                FileReader myReader = new FileReader(myFile);
                
                //create buffered reader object
                BufferedReader myBuffer = new BufferedReader(myReader);
                
                //read a line from the text file
                String strLine = myBuffer.readLine();
                
                //loop through the rest of the lines
                while (strLine != null)
                {
                    this.textAreaEditor.append(strLine);
                    this.textAreaEditor.append("\n");
                    strLine = myBuffer.readLine();
                }
                
                //clean up
                myBuffer.close();
                myReader.close();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, e.toString());
            }
            
            
        }
    }//GEN-LAST:event_menuOpenActionPerformed

    private void menuNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNewActionPerformed
        // clear the text area
        this.textAreaEditor.setText("");
        
        //clear the file object
        myFile = null;
    }//GEN-LAST:event_menuNewActionPerformed

    private void menuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExitActionPerformed
        // exit the application
        System.exit(0);
    }//GEN-LAST:event_menuExitActionPerformed

    private void menuSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSaveActionPerformed
        // make sure file is not null
        if(myFile != null)
        {
            //call method to save
            saveFileContents();
        }
        else
        {
            //if new, show save as dialog
            menuSaveAsActionPerformed(evt);
        }
    }//GEN-LAST:event_menuSaveActionPerformed

    private void menuSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSaveAsActionPerformed
        // change the file object
        JFileChooser myChooser = new JFileChooser();
        myChooser.setDialogTitle("Save As ...");
        myChooser.showSaveDialog(this);
        
        //make sure user did not click cancel
        if(myChooser.getSelectedFile() != null)
        {
            //change to new file object
            myFile = new File(myChooser.getSelectedFile().getPath());
            
            // call method to save
            saveFileContents();
        }
    }//GEN-LAST:event_menuSaveAsActionPerformed

    private void saveFileContents()
    {
        try
        {
            //create file writer
            FileWriter myWriter = new FileWriter(myFile);
            
            //create a print writer
            PrintWriter myPrinter = new PrintWriter(myFile);
            
            //write the contents of the text area to the file
            myPrinter.print(this.textAreaEditor.getText());
            
            //clean up
            myWriter.close();
            myPrinter.close();
            
            //message to the user
            JOptionPane.showMessageDialog(this, "Changes were saved successfully.");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, e.toString());
        }
    }
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
            java.util.logging.Logger.getLogger(frmMyTextEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMyTextEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMyTextEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMyTextEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmTextEditor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menuEdit;
    private javax.swing.JMenuItem menuExit;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenuItem menuNew;
    private javax.swing.JMenuItem menuOpen;
    private javax.swing.JMenuItem menuSave;
    private javax.swing.JMenuItem menuSaveAs;
    private javax.swing.JTextArea textAreaEditor;
    // End of variables declaration//GEN-END:variables
}
