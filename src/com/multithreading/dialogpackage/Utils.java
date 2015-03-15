package com.multithreading.dialogpackage;


import java.awt.Component;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Utils {

    public final static String txt = "txt";
    public final static String gart = "gart";
    public final static String xml = "xml";
    public final static String log = "log";


    /*
     * Get the extension of a file.
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }


    public static void save(Object parent, String result) {
        File file = new File(PropertyManager.getProperty("gart.home"));
        JFileChooser fileChooser = new JFileChooser(file);

        int retVal = fileChooser.showSaveDialog((Component) parent);
        if (retVal == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(file));
                out.write(result);
                out.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e, "Unable to save the result",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}


//import java.io.File;
//import javax.swing.ImageIcon;
//
///* Utils.java is used by FileChooserDemo2.java. */
//public class Utils {
//    public final static String jpeg = "jpeg";
//    public final static String jpg = "jpg";
//    public final static String gif = "gif";
//    public final static String tiff = "tiff";
//    public final static String tif = "tif";
//    public final static String png = "png";
//
//    /*
//     * Get the extension of a file.
//     */
//    public static String getExtension(File f) {
//        String ext = null;
//        String s = f.getName();
//        int i = s.lastIndexOf('.');
//
//        if (i > 0 &&  i < s.length() - 1) {
//            ext = s.substring(i+1).toLowerCase();
//        }
//        return ext;
//    }
//
//    /** Returns an ImageIcon, or null if the path was invalid. */
//    protected static ImageIcon createImageIcon(String path) {
//        java.net.URL imgURL = Utils.class.getResource(path);
//        if (imgURL != null) {
//            return new ImageIcon(imgURL);
//        } else {
//            System.err.println("Couldn't find file: " + path);
//            return null;
//        }
//    }
//}
