package co.jp.rough.db;

import android.content.Context;

public class NkMajanDBManager {
    private static NkMajanDataHelper nkHelper;

    private static Context con;

    public static NkMajanDataHelper getNKHelper() {
        if (nkHelper == null) {
            nkHelper = new NkMajanDataHelper(con);

/**
            Log.e("SimpleMajanDBManager", "smHelper is null");
            throw new RuntimeException("smHelper is null");
**/
        }

        return nkHelper;
    }

    public static void init (Context connect) {
        if (con == null) {
            con = connect;
        }
        nkHelper = new NkMajanDataHelper(connect);
    }
}
