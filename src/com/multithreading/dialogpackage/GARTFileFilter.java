package com.multithreading.dialogpackage;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * 
 * @author Jungsoo Kim
 *
 */
public class GARTFileFilter extends FileFilter {

    //Accept all directories and txt, xml, log, and gart files.
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(f);
        if (extension != null) {
        	if (extension.equalsIgnoreCase(Utils.gart) ||
        			extension.equalsIgnoreCase(Utils.txt) ||
        			extension.equalsIgnoreCase(Utils.xml) ||
        			extension.equalsIgnoreCase(Utils.log))
        	{
        		return true;
        	} else {
        		return false;
        	}
        }

        return false;
    }

    //The description of this filter
    public String getDescription() {
        return "Files supported by GART";
    }
}
