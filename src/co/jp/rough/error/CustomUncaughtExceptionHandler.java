package co.jp.rough.error;

import java.lang.Thread.UncaughtExceptionHandler;

import android.content.Context;
import android.util.Log;

public class CustomUncaughtExceptionHandler implements UncaughtExceptionHandler {

    private Context mContext;
    private UncaughtExceptionHandler mDefaultUncaughtExceptionHandler;

    public CustomUncaughtExceptionHandler(Context context) {
        mContext = context;

        // �f�t�H���g��O�n���h����ێ�����B
        mDefaultUncaughtExceptionHandler = Thread
                .getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        ex.printStackTrace();
        Log.e("Exception", ex.getMessage(), ex);
        // �f�t�H���g��O�n���h�������s���A�����I�����܂��B
        mDefaultUncaughtExceptionHandler.uncaughtException(thread, ex);
    }
}