package co.jp.rough.db;

import android.content.Context;

public class SimpleMajanDBManager {
    private static SimpleMajanDataHelper smHelper;

    private static Context con;

    public static SimpleMajanDataHelper getSMHelper() {
        if (smHelper == null) {
            smHelper = new SimpleMajanDataHelper(con);

/**
            Log.e("SimpleMajanDBManager", "smHelper is null");
            throw new RuntimeException("smHelper is null");
**/
        }

        return smHelper;
    }

    public static void init (Context connect) {
        if (con == null) {
            con = connect;
        }
//        smHelper = new SimpleMajanDataHelper(connect);
    }
}
