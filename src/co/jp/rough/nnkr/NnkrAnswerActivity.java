package co.jp.rough.nnkr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import co.jp.rough.common.CommonFunc;
import co.jp.rough.common.UserConfigManager;
import co.jp.rough.nk.NnKrAction;
import co.jp.rough.nk.NnKrAnswerAction;

public class NnkrAnswerActivity extends Activity {
    NnKrAnswerAction action;
    Long answerNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);

            action = new NnKrAnswerAction();
            action.startGame(this);
        
        } catch (Exception e) {
            Log.e("Exception", e.getMessage(), e);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_nnkr, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
        case R.id.menu_title:
            Intent registIntent = new Intent( this, StartActivity.class);
            startActivity(registIntent);
            break;
        case R.id.menu_before_question:
            
            Long lastQ = UserConfigManager.getSessionLastAnsSeq();
            if (!CommonFunc.isEmptyOrZERO(lastQ) && lastQ.compareTo(1L) >= 0) {
                lastQ--;
                UserConfigManager.setSessionLastAnsSeq(lastQ);
                Intent nkIntent = new Intent( this, NnkrActivity.class);
                startActivity(nkIntent);
            }
            break;
        }
        return true;
    };

    public final NnKrAnswerAction getAction() {
        return this.action;
    }
    
    public void setAnswerNo(Long num) {
        answerNo = num;
    }
    
}
