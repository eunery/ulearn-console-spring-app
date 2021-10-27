package com.example.consoleapp.utils;

import java.io.File;
import java.util.Locale;

public class FileUtils {
    public static String getFileExtension(File file){
        String filename = file.getName();
        if (!filename.contains(".")){
            return "";
        }
        return filename.substring(filename.lastIndexOf(".")+1).toLowerCase(Locale.ROOT);
    }
}
