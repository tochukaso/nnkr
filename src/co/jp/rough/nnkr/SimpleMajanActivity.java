package co.jp.rough.nnkr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import co.jp.rough.common.ConfigManager;
import co.jp.rough.hai.HaiManager;
import co.jp.rough.logic.SimpleMajan;

public class SimpleMajanActivity extends Activity {
    SimpleMajan majan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);

            int width = ConfigManager.getWidth();
            int heigt = ConfigManager.getHeight();

            HaiManager.setHaiWidth(width / 14);
            HaiManager.setHaiHeight((int)(HaiManager.HAI_WIDTH * 1.4d));
            
            majan = new SimpleMajan();
            majan.startGame(this);
        } catch (Exception e) {
            Log.e("Exception", e.getMessage(), e);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.simple_majan, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
/**
        case R.id.menu_haiPaiView:
            Intent registIntent = new Intent( this, MainActivity.class);
            startActivity(registIntent);
            break;
        case R.id.menu_saveGame:

            majan.saveTehai();
            break;
**/
        case R.id.menu_title:
            Intent titleIntent = new Intent( this, StartActivity.class);
            startActivity(titleIntent);
            break;

        }
        return true;
    };


}
