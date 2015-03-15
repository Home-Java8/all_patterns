package com.multithreading.dialogpackage;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class AlphabetModel extends DefaultTableModel {
    private int alphabetSize = 0;
    private Object [][] alphaTable = new Object[alphabetSize][2];
    private String [] colNames = {"letters", "entries"};
    AlphabetModel(){
        this.setDataVector(alphaTable, colNames);
    }
    public int getAlphabetSize(){
        return this.alphabetSize;
    }
    public void setAlphabetSize(int size){
        this.alphabetSize = size;
    }
    public void addRow(){
        Object[] data = {"",""};
        this.addRow(data);
    }
    public void reset()
    {
        Object[][]data = null;
        this.setDataVector(data, colNames);
    }
}