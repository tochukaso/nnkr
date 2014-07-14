package co.jp.rough.logic;

import android.app.Activity;
import co.jp.rough.hai.HaiManager;
import co.jp.rough.nnkr.HaipaiView;

public class HaipaiCheck {

    public void start(Activity activity) {
        HaiManager.initHaiList();
        HaipaiView view = new HaipaiView(activity);
        activity.setContentView(view);
    }

}
