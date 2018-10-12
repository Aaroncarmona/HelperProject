package project.hdp.com.helperproject.util;

import android.os.Environment;

import java.io.File;

import project.hdp.com.helperproject.constant.Constant;
import project.hdp.com.helperproject.util.enums.Directory;
import project.hdp.com.helperproject.util.enums.Extension;
import project.hdp.com.helperproject.util.enums.Prefix;

public class StorageHelper {
    private static final String TAG = StorageHelper.class.getSimpleName();

    //crea un archivo ( directorio , acronimo , extension ) el nombre del archivo se auto genera
    public static File createFile( Directory directory , Prefix prefix , Extension extension ){
        return new File(getDirectory(directory) , generateStringFile(prefix , extension));
    }

    //crea un archivo ( directory , acronimo , nombre , extension)
    public static File createFile(Directory directory , Prefix prefix , String name , Extension extension ){
        return new File(getDirectory(directory), generateStringFile(prefix , name , extension));
    }

    //se crea un archivo con la extension ya dada JPG
    public static File createFileJpg( Directory directory , Prefix prefix ) {
        return new File(getDirectory(directory) , generateStringFile(prefix , Extension.JPG));
    }

    //se genera el nombre del archivo con un nombre auto generado
    private static String generateStringFile( Prefix prefix , Extension extension){
        return generateStringFile(prefix , String.valueOf(System.currentTimeMillis()), extension);
    }

    //se genera el nombre del archivo
    private static String generateStringFile(Prefix prefix , String name , Extension extension ) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix.prefix);
        sb.append("_");
        sb.append(name);
        sb.append(extension.ext);
        return sb.toString();
    }

    //se obtiene un el directorio en un archivo
    public static File getDirectory(Directory directory){
        try{
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Environment.getExternalStorageDirectory());
            stringBuilder.append(File.separator);
            stringBuilder.append(Constant.DIRECTORY_BASE);
            stringBuilder.append(File.separator);
            stringBuilder.append(directory.name);
            File file = new File(stringBuilder.toString());
            if (!file.exists()) {
                file.mkdirs();
            }
            return file;
        }catch (Exception e){
            return null;
        }
    }

    //se eliminan los datos del directorio seleccionado
    public static int deleteDirectory(Directory directory){
        File dir = getDirectory(directory);
        int aux = 0;
        if ( dir.isDirectory() && dir.exists() ) {
            String [] childrenList = dir.list();
            for ( String child : childrenList ) {
                if ( new File(dir.getAbsolutePath() , child ).delete() ) {
                    aux++;
                }
            }
        }
        return aux;
    }
}
