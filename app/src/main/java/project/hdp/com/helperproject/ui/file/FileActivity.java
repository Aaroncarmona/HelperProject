package project.hdp.com.helperproject.ui.file;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

import project.hdp.com.helperproject.R;
import project.hdp.com.helperproject.constant.Constant;
import project.hdp.com.helperproject.util.StorageHelper;
import project.hdp.com.helperproject.util.StorageTextHelper;
import project.hdp.com.helperproject.util.Util;
import project.hdp.com.helperproject.util.enums.Directory;
import project.hdp.com.helperproject.util.enums.Extension;
import project.hdp.com.helperproject.util.enums.Prefix;

/*
* El uso de los archivos un poco mas ordenados
* DELEGADO
*   obtener permisos
* ENUM
*   DIRECTORY
*       directorios de la aplicacion
*   EXTENSION
*       extensiones que se manejaran
*   PREFIX
*       el acronimo del archivo
*
* StorageHelper
*   leer la clase para mas informacion
*
* */


public class FileActivity extends AppCompatActivity {

    private FileDelegate fileDelegate;
    private TextInputEditText tietText;

    private File file;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_file);

        init();

        if ( !fileDelegate.hasPermission() ) {
            fileDelegate.requestPermission();
        }

    }

    public void init(){
        fileDelegate = new FileDelegate(this);
        tietText = findViewById(R.id.tietText);

        file = StorageHelper.createFile(Directory.FILES, Prefix.FILES , Extension.TXT);

        findViewById(R.id.fabSave).setOnClickListener( v -> {
            if (fileDelegate.hasPermission()) {
                save();
                tietText.setText("");
            }  else  Util.toast(R.string.no_permission);
        });
    }

    public void save(){

        if ( StorageTextHelper.write(file , tietText.getText().toString()) ) {

            String text = StorageTextHelper.read(file);

            if ( !text.contains(Constant.READ_FILE_ERR_STRING)){
                Util.toast(text);
                //en caso de eliminar el archivo -> StorageHelper.deleteDirectory(Directory.FILES);
            } else {
                //hubo un error al momento de obtener el texto del archivo
                Util.toast(R.string.no_obtener_texto);
            }
        } else {
            //no se pudo escribir en el archivo
            Util.toast(R.string.no_escribir_archivo);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        fileDelegate.onRequestPermitionsResult(requestCode , permissions , grantResults);
    }
}
