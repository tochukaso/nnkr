package co.jp.rough.nk;

import android.app.Activity;
import android.util.Log;
import co.jp.rough.common.UserConfigManager;
import co.jp.rough.db.spmj.NkmjAnswerDao;
import co.jp.rough.db.spmj.NkmjDao;
import co.jp.rough.nnkr.NnkrActivity;
import co.jp.rough.nnkr.NnkrAnswerActivity;

public class NnKrAnswerAction {
    
    NKAnswerView view;
    
    NnkrAnswerActivity act = null;
    
    public void startGame(Activity activity) {
        try {
            this.act = (NnkrAnswerActivity) activity;
            view = new NKAnswerView(activity);
            view.setNKAction(this);
            view.setData(new NkmjDao().selectNKMJAll(UserConfigManager.getSessionLastAnsSeq()));
            
            activity.setContentView(view);
            
        } catch(Exception e) {
            Log.w("answer", e);
        }
    }
    public final NnkrAnswerActivity getActivity() {
        return this.act;
    }

}

