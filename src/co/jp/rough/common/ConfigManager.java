package co.jp.rough.common;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class ConfigManager {
    
    static int width = 0;
    static int height = 0;

    static boolean isInit = false;
    
    public static void initilaize(WindowManager manager) {
        if (isInit) return;
        
        Display disp = manager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        disp.getMetrics(metrics);

        width = metrics.widthPixels;
        height = metrics.heightPixels;
        
        isInit = true;
    }
    
    public static int getWidth() {
        return width;
    }
    
    public static int getHeight() {
        return height;
    }
    
}


