package project.hdp.com.helperproject.ui.file;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import project.hdp.com.helperproject.ui.VApp;

public class FileDelegate {
    private static final String TAG = FileDelegate.class.getSimpleName();

    private Activity act;

    private String [] permissions = new String [] {
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private final int REQUEST_CODE = 10;

    public FileDelegate(Activity act){
        this.act = act;
    }

    public boolean hasPermission(){
        boolean valid = true;
        for ( String permission : this.permissions){
            if ( ContextCompat.checkSelfPermission(this.act , permission) != PackageManager.PERMISSION_GRANTED){
                valid = false;
            }
        }
        return valid;
    }

    public void requestPermission(){
        if ( !hasPermission() ) {
            ActivityCompat.requestPermissions(this.act, this.permissions, this.REQUEST_CODE);
        }
    }

    public void onRequestPermitionsResult( int requestCode , String [] permissions , int [] grantResults ){
        switch (requestCode){
            case REQUEST_CODE:
                if ( grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                    Log.d(TAG , "first");
                } else {
                    Log.d(TAG , "seconds");
                    act.finish();
                }
                break;
        }
    }
}
