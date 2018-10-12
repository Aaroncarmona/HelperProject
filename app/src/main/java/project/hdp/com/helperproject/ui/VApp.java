package project.hdp.com.helperproject.ui;

import android.app.Application;
import android.content.Context;

public class VApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getInstance(){
        return context;
    }
}
