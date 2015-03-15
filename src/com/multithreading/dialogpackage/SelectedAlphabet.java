package com.multithreading.dialogpackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JOptionPane;

public class SelectedAlphabet extends SelectedFile{
    AlphabetModel localAlphabetModel;
    private int alphabetSize;
    private Vector isInAlphabet = new Vector();
    public SelectedAlphabet(java.awt.event.MouseEvent evt) throws IOException{
        this.initSelectedFile();
        this.loadAlphabet();
    }
    public SelectedAlphabet(String path) throws IOException{
        this.sFile = new File(path);
        this.fChoosen.setSelectedFile(sFile);
        this.setFilePath(path);
        this.setFileSize(this.sFile.length());
        this.loadAlphabet();
    }
    public boolean loadAlphabet() throws IOException //loading selected alphabet
    {
        if(isInAlphabet!=null){
            isInAlphabet.clear();
        }
        FileInputStream fin = null;


        try {
            fin = new FileInputStream(this.getFilePath());
            int c;
            int line = 0;
            while ((c =fin.read()) != -1)
            {
                if((c != 13)&& (c !=10))
                {
                    if(!isInAlphabet.contains((char)c)){
                        isInAlphabet.add((char)c);
                        line++;
                    }
                    else{
                        JOptionPane.showMessageDialog(null,
                                "Incorrect alphabet: \""+(char)c+
                                        "\"\nrepeated on line "+line+"\nUnable to parse", "ERROR!",
                                JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
            }
            this.setFileSize(line);
            alphabetSize = line;
        }
        catch (IOException ioex)
        {
            JOptionPane.showMessageDialog(null, "Caught IOException: "
                            + ioex.getMessage(), "ERROR!",
                    JOptionPane.ERROR_MESSAGE);
        }
        finally {
            if (fin != null)
            {
                fin.close();
                return true;
            }
        }
        return true;
    }
    public void initTableByAlphabet(AlphabetModel localAlphabetModel){
        localAlphabetModel.reset();
        int i = 0;
        while(i<isInAlphabet.size())
        {
            localAlphabetModel.addRow();
            localAlphabetModel.setValueAt(isInAlphabet.get(i), i,0);
            localAlphabetModel.fireTableDataChanged();
            i++;
        }
    }
    public Vector getAlphabetVector()
    {
        return isInAlphabet;
    }
    public int getAlphabetSize()
    {
        return alphabetSize;
    }
}