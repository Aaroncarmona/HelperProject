package project.hdp.com.helperproject.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import project.hdp.com.helperproject.constant.Constant;

public class StorageTextHelper {

    public static boolean write( File file , String text) {
        try {
            PrintWriter w = new PrintWriter(file);
            w.print(text);
            w.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    public static String read( File file ) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            String aux = "";
            while((str = br.readLine()) != null ){
                aux += str;
            }
            br.close();
            return aux;
        } catch (IOException e) {
            return Constant.READ_FILE_ERR_STRING;
        }
    }
}
