package project.hdp.com.helperproject.util;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import project.hdp.com.helperproject.ui.VApp;

public class Util {

    public static void toast( String text ) {
        toast(VApp.getInstance() , text);
    }

    public static void toast( int id ){
        toast(VApp.getInstance() , id);
    }

    public static void toast(Context context , int id ){
        toast(context , context.getString(id));
    }

    public static void toast(Context context , String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
