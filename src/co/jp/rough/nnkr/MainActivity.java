package co.jp.rough.nnkr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import co.jp.rough.common.ConfigManager;
import co.jp.rough.common.Initilizar;
import co.jp.rough.error.CustomUncaughtExceptionHandler;
import co.jp.rough.hai.HaiManager;
import co.jp.rough.logic.HaipaiCheck;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            HaiManager.initHaiList();
//            Initilizar.initApp(this);

            int width = ConfigManager.getWidth();
            int heigt = ConfigManager.getHeight();

            HaiManager.setHaiWidth(width / 14);
            HaiManager.setHaiHeight((int)(HaiManager.HAI_WIDTH * 1.4d));
            
            super.onCreate(savedInstanceState);
            CustomUncaughtExceptionHandler customUncaughtExceptionHandler =
                    new CustomUncaughtExceptionHandler(getApplicationContext());
            Thread.setDefaultUncaughtExceptionHandler(customUncaughtExceptionHandler);

            setContentView(new HaipaiView(this));

            //        mainView.setOnItemClickListener(this);

        } catch (Exception e) {
            Log.e("Exception", e.getMessage(), e);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
/**
        case R.id.menu_haiPaiView:
            HaipaiCheck haiCheck = new HaipaiCheck();
            haiCheck.start(this);
            break;
        case R.id.menu_simpleGame:
            Intent registIntent = new Intent( this, SimpleMajanActivity.class);
            startActivity(registIntent);
            break;
        case R.id.menu_nnkr:
            Intent nnkrIntent = new Intent( this, NnkrActivity.class);
            startActivity(nnkrIntent);
**/
        case R.id.menu_title:
            Intent titleIntent = new Intent( this, StartActivity.class);
            startActivity(titleIntent);
            break;

        }
        return true;
    };

}

