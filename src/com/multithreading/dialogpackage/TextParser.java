package com.multithreading.dialogpackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;

public class TextParser implements Runnable{
    private JTable Table;
    private AlphabetModel localModel;
    private SelectedAlphabet selectedAlphabet;
    private FileToParse fileToParse;
    private JProgressBar ProgressBar;
    private boolean dOutput;
    private Map entries = new HashMap();
    TextParser(JTable jTable, SelectedAlphabet jselectedAlphabet,
               FileToParse jfileToParse, JProgressBar jProgressBar,
               boolean jdynOutline, AlphabetModel locModel) throws IOException {
        Table = jTable;
        selectedAlphabet = jselectedAlphabet;
        fileToParse = jfileToParse;
        ProgressBar = jProgressBar;
        dOutput = jdynOutline;
        localModel = locModel;
    }
    private void loadText() throws IOException
    {
        if (entries!=null)
            entries.clear();
        FileInputStream in = null;
        int count = 0;
        try {
            in = new FileInputStream(this.fileToParse.getFilePath());
            int k;
            while ((k = in.read()) != -1)
            {
                count++;
                char key =(char)k;
                if((selectedAlphabet.getAlphabetVector()).contains(key))
                {
                    if(!entries.containsKey(key)){
//System.err.println(key+" ");
                        entries.put(key, 1);
                    }
                    else{
                        Integer value = (Integer) entries.get(key);
                        int entry = value.intValue();
                        entries.put(key,(int) entry+1);
                        System.err.println(entries.get(key));
                    }
// if(dOutput)
//{
                    localModel.setValueAt(entries.get(key),
                            (selectedAlphabet.getAlphabetVector()).indexOf(key),1);
                    localModel.fireTableDataChanged();
//}
                    this.ProgressBar.setValue(count*100/(int)fileToParse.getFileSize());
                }
            }
            UpdateTable();
        }
        catch (IOException ioex)
        {
            JOptionPane.showMessageDialog(null,"Unable to read file", "ERROR!",JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            if (in != null)
            {
                in.close();
                return;
            }
        }
        return;
    }
    private void UpdateTable()//our table finaly results
    {
        Iterator it = selectedAlphabet.getAlphabetVector().iterator();
        while ( it.hasNext())
        {
            Object oIt = it.next();
            localModel.setValueAt(entries.get(oIt),
                    (selectedAlphabet.getAlphabetVector()).indexOf(oIt),1);
        }
        localModel.fireTableDataChanged();
    }

    public void run(){
        try {
            loadText();
        } catch (IOException ex) {
            Logger.getLogger(TextParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
