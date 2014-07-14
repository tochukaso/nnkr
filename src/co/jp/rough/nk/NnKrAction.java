package co.jp.rough.nk;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import co.jp.rough.common.CommonFunc;
import co.jp.rough.common.UserConfigManager;
import co.jp.rough.db.NK_MJ_TABLE;
import co.jp.rough.db.spmj.NkmjDao;
import co.jp.rough.nnkr.NnkrActivity;
import co.jp.rough.nnkr.StartActivity;

public class NnKrAction {

    public static final String URL = "http://tochukaso0529.appspot.com/";
    
    NKView view;
    
    NnkrActivity act = null;
    
    public void startGame(Activity activity) {
        try {
            this.act = (NnkrActivity) activity;
            view = new NKView(activity);

            // getData
            NkmjDao dao = new NkmjDao();
            NK_MJ_TABLE data = dao.selectNKMJAll(getViewQuestionIndex());
            if (data == null) {
                Intent titleIntent = new Intent( act, StartActivity.class);
                act.startActivity(titleIntent);
            } else {
                view.setNKData(data);
                view.setNKAction(this);
                activity.setContentView(view);
            }
            
        } catch(Exception e) {
            Log.w("getD", e);
        }
        
    }

    void setData() {
        view.setNKData(null);
    }
    
    public final NnkrActivity getActivity() {
        return this.act;
    }
    
    private Long getViewQuestionIndex() {
        
        if (CommonFunc.isEmptyOrZERO(UserConfigManager.getSessionLastAnsSeq())) {
            Long lastIndex = Math.min(new NkmjDao().selectNKMJMaxSeq(), UserConfigManager.getLastAnsSeq() + 1);
            UserConfigManager.setSessionLastAnsSeq(lastIndex);
        }
        return UserConfigManager.getSessionLastAnsSeq();
    }
    
}

