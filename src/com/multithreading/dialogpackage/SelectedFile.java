package com.multithreading.dialogpackage;

import java.io.File;
import javax.swing.filechooser.*;
import javax.swing.JFileChooser;

/*
* Abstract file class
*/
public class SelectedFile {
    protected File sFile;
    protected String pathToFile;
    protected long fileSize;
    protected JFileChooser fChoosen = new JFileChooser();;
    int returnVal;
    private class TXTFilter extends FileFilter {

        //Accept all directories and *.txt.
        public boolean accept(File sf) {
            if (sf.isDirectory()) {
                return true;
            }
            Utils ut = new Utils();
            String extension = ut.getExtension(sf);
            if (extension != null) {
                if (extension.equals(ut.txt) ) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

        //The description of this filter
        public String getDescription() {
            return "*.txt";
        }
    }
    protected void initSelectedFile() {
        fChoosen.addChoosableFileFilter(new TXTFilter());
        returnVal = fChoosen.showOpenDialog(fChoosen);
        if ( returnVal == JFileChooser.APPROVE_OPTION )
        {
/*
get file information
*/
            sFile = fChoosen.getSelectedFile();
            pathToFile = sFile.getAbsolutePath();
            this.setFileSize(sFile.length());
        }
    }
    public void setFilePath(String path){
        pathToFile = path;
    }
    public void setFileSize(long size){
        fileSize = size;
    }
    public long getFileSize(){
        return this.fileSize;
    }
    public String getFilePath(){
        return this.pathToFile;
    }
}
