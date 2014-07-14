package co.jp.rough.db.spmj;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import co.jp.rough.common.CommonFunc;
import co.jp.rough.db.NK_MJ_ANSWER;
import co.jp.rough.db.NkMajanDBManager;

public class NkmjAnswerDao {

    /**
     * @param spNo
     * @param seq
     * @return
     */
    public NK_MJ_ANSWER selectNKMJAnswer(Long seq) {
        SQLiteDatabase db = NkMajanDBManager.getNKHelper().getReadableDatabase();

        NK_MJ_ANSWER data = null;
        Cursor cursor = db.query(
                NK_MJ_ANSWER.TABLE_NAME,
                null,
                NK_MJ_ANSWER.CLMN_NK_SEQ + "=? ",
                new String[]{ String.valueOf(seq)}, null, null, null);
        cursor.moveToFirst();
        data = makeNKMJAnswerFromCursor( cursor);
        return data;

    }

    /**
     * @param db
     * @param list
     */
    public void saveNKMJAnswer(NK_MJ_ANSWER data) {
        SQLiteDatabase db = NkMajanDBManager.getNKHelper().getWritableDatabase();
            Long seq = data.getSeq();

            if (seq == null) {
                throw new IllegalArgumentException("spNo is null");
            }

            ContentValues value = makeNKMJAnswerValue(data);

            NK_MJ_ANSWER tableData = selectNKMJAnswer(seq);

            Log.d("nkmj", "tableData is " + (tableData != null));

            if (tableData != null) {
                db.update( NK_MJ_ANSWER.TABLE_NAME,
                           value,
                           NK_MJ_ANSWER.CLMN_NK_SEQ + "=? ",
                           new String[]{ String.valueOf( data.getSeq())});

                return ;
            } else {
                db.insert( NK_MJ_ANSWER.TABLE_NAME, null, value);
            }
    }

    /**
     * @param db
     * @param list
     */
    public void updateUserAnswer(Long seq, String answer) {
        SQLiteDatabase db = NkMajanDBManager.getNKHelper().getWritableDatabase();

            if (seq == null || answer == null) {
                throw new IllegalArgumentException("must parameter is null");
            }

            ContentValues value = new ContentValues();
            value.put(NK_MJ_ANSWER.CLMN_USER_ANSWER, answer);
            value.put(NK_MJ_ANSWER.CLMN_UPD_DM, CommonFunc.getCurrentDate());

            db.update( NK_MJ_ANSWER.TABLE_NAME,
                       value,
                       NK_MJ_ANSWER.CLMN_NK_SEQ + "=? ",
                       new String[]{ String.valueOf(seq)});
            return ;
    }

    
    
    private ContentValues makeNKMJAnswerValue(NK_MJ_ANSWER data) {
        ContentValues values = new ContentValues();
        values.put( NK_MJ_ANSWER.CLMN_NK_SEQ, data.getSeq());
        
        if (data.getPointList() != null ) {
            for (int i = 1; i <= data.getPointList().size(); i++) {
                values.put( NK_MJ_ANSWER.CLMN_ANS_COMMON+ i, data.getPointList().get(i - 1));
            }
        }
        values.put( NK_MJ_ANSWER.CLMN_COMMENT_1, data.getComment1());
        values.put( NK_MJ_ANSWER.CLMN_COMMENT_2, data.getComment2());
        values.put( NK_MJ_ANSWER.CLMN_COMMENT_3, data.getComment3());

        values.put( NK_MJ_ANSWER.CLMN_REG_DM, data.getRegDm());
        values.put( NK_MJ_ANSWER.CLMN_UPD_DM, data.getUpdDm());
        values.put( NK_MJ_ANSWER.CLMN_USER_ANSWER, data.getUserAnswer());

        
        return values;
    }

    private NK_MJ_ANSWER makeNKMJAnswerFromCursor(Cursor cursor) {

        if (cursor == null || !cursor.moveToFirst()) {
            Log.i("nkmj", "crusor is null");
            return null;
        }

        int cursorIndex = 0;

        NK_MJ_ANSWER data = new NK_MJ_ANSWER();
        data.setSeq(cursor.getLong(cursorIndex++));

        List<String> pointList = new ArrayList<String>();
        data.setPointList(pointList);
        for (int i = 0; i < NK_MJ_ANSWER.ANS_CNT; i++) {
            pointList.add(cursor.getString(cursorIndex++));
        }
        
        data.setComment1(cursor.getString(cursorIndex++));
        data.setComment2(cursor.getString(cursorIndex++));
        data.setComment3(cursor.getString(cursorIndex++));

        data.setUserAnswer(cursor.getString(cursorIndex++));
        data.setRegDm(cursor.getString(cursorIndex++));
        data.setUpdDm(cursor.getString(cursorIndex++));

        return data;
    }

}
