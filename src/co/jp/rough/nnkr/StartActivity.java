package co.jp.rough.nnkr;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import co.jp.rough.common.CommonFunc;
import co.jp.rough.common.ConfigManager;
import co.jp.rough.common.Initilizar;
import co.jp.rough.common.Lank;
import co.jp.rough.common.UserConfigManager;
import co.jp.rough.db.NK_MJ_USER;
import co.jp.rough.db.spmj.NkmjDao;
import co.jp.rough.db.spmj.NkmjUserDao;
import co.jp.rough.hai.HaiConfigManager;
import co.jp.rough.logic.HaipaiCheck;
import co.jp.rough.nk.QuestionGetHttpRequest;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            Initilizar.initNkApp(this);
            Initilizar.initApp(this);
            QuestionGetHttpRequest req = new QuestionGetHttpRequest(this);
            try {
                req.execute();
            } catch(Exception e) {
                Log.w("getD", e);
            }

            ConfigManager.initilaize(getWindowManager());
            HaiConfigManager.init(getResources());
            
            UserConfigManager.init();
            
            setContentView(R.layout.start);
            LinearLayout layout = (LinearLayout) findViewById(R.id.startLayout);
            TextView tView = new TextView(getApplicationContext());
            tView.setText(UserConfigManager.getUser().getLank() + "(" + UserConfigManager.getUser().getPoint() + "pt)        "
                    + UserConfigManager.getLastAnsSeq() + "/" + new NkmjDao().selectNKMJMaxSeq());
            tView.setTextColor(Color.BLACK);
            layout.addView(tView, 1);
            addListenerOnButton();
        } catch (Exception e) {
            Log.e("Exception", e.getMessage(), e);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }
/**
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
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
            break;

        }
        return true;
    };
**/
    public void addListenerOnButton() {
        {
            ImageButton button = (ImageButton) findViewById(R.id.imageButton1);
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg) {
                    Intent intent = new Intent(arg.getContext() , NnkrActivity.class);
                    startActivity(intent);
                }
            });
        }
        
        {
            ImageButton button = (ImageButton) findViewById(R.id.imageButton2);
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg) {
                    Intent intent = new Intent(arg.getContext() , MainActivity.class);
                    startActivity(intent);
                }
            });
        }

        {
            ImageButton button = (ImageButton) findViewById(R.id.imageButton3);
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg) {
                    Intent intent = new Intent(arg.getContext() , SimpleMajanActivity.class);
                    startActivity(intent);
                }
            });
        }

    }
    
}

