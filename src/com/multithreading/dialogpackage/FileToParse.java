package com.multithreading.dialogpackage;

import java.io.File;

public class FileToParse extends SelectedFile{

    FileToParse(String string) {
        this.sFile = new File(string);
        this.fChoosen.setSelectedFile(sFile);
        this.setFilePath(string);
        this.setFileSize(this.sFile.length());
    }
    public FileToParse(java.awt.event.MouseEvent evt){
        this.initSelectedFile();
    }

}