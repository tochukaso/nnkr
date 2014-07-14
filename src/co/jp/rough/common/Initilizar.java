package co.jp.rough.common;

import android.content.Context;
import co.jp.rough.db.NkMajanDBManager;
import co.jp.rough.db.SimpleMajanDBManager;

public class Initilizar {

    public static boolean initApp(Context con) {
        SimpleMajanDBManager.init(con);
        return true;
    }

    public static boolean initNkApp(Context con) {
        NkMajanDBManager.init(con);
        return true;
    }

}
