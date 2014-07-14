package co.jp.rough.db.spmj;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import co.jp.rough.db.NK_MJ_SUTE_DTL_TABLE;
import co.jp.rough.db.NkMajanDBManager;

public class NkmjSuteDtlDao {

    /**
     * @param spNo
     * @param seq
     * @return
     */
    public NK_MJ_SUTE_DTL_TABLE selectNKMJSuteDTL(Long seq, Long dtlSeq) {
        SQLiteDatabase db = NkMajanDBManager.getNKHelper().getReadableDatabase();

        NK_MJ_SUTE_DTL_TABLE data = null;
        Cursor cursor = db.query(
                NK_MJ_SUTE_DTL_TABLE.TABLE_NAME,
                null,
                NK_MJ_SUTE_DTL_TABLE.CLMN_NK_SEQ + "=? and " + NK_MJ_SUTE_DTL_TABLE.CLMN_NK_DTL_SEQ + "=?",
                new String[]{ String.valueOf(seq), String.valueOf(dtlSeq)}, null, null, null);
        cursor.moveToFirst();
        data = makeNKMJSuteDtlFromCursor( cursor);
        return data;

    }

    /**
     * @param seq
     * @return
     */
    public List<NK_MJ_SUTE_DTL_TABLE> selectNKMJSuteALL(Long seq) {
        List<NK_MJ_SUTE_DTL_TABLE> resList = new ArrayList<NK_MJ_SUTE_DTL_TABLE>();
        for (long i = 1; i <= 4; i++) {
            resList.add(selectNKMJSuteDTL(seq, i));
        }
        return resList;
    }

    
    private static final String SELECTMAX = "SELECT MAX("
                                            + NK_MJ_SUTE_DTL_TABLE.CLMN_NK_DTL_SEQ + ") "
                                            + "FROM NK_MJ_SUTE_DTL where "
                                            + NK_MJ_SUTE_DTL_TABLE.CLMN_NK_SEQ + " = ? ";

    /**
     * @param spNo
     * @return
     */
    public Long selectNKMJSuteDTLMaxSeq(Long spNo) {
        SQLiteDatabase db = NkMajanDBManager.getNKHelper().getReadableDatabase();

        String[] whereArgs = new String[] {
                String.valueOf(spNo)
        };

        String myString=DatabaseUtils.stringForQuery(db,SELECTMAX,whereArgs);
        Long index;
        if (myString == null) {
            index = new Long(0);
        } else {
            index = Long.valueOf(myString);
        }

        return index;
    }

    /**
     * @param db
     * @param list
     */
    public void saveNKMJSuteDTL(List<NK_MJ_SUTE_DTL_TABLE> list) {
        for (NK_MJ_SUTE_DTL_TABLE data : list) {
            saveNKMJSuteDTL(data);
        }
    }

    /**
     * @param db
     * @param list
     */
    public void saveNKMJSuteDTL(NK_MJ_SUTE_DTL_TABLE data) {
        SQLiteDatabase db = NkMajanDBManager.getNKHelper().getWritableDatabase();
            Long spNo = data.getNkSeq();

            if (spNo == null) {
                throw new IllegalArgumentException("spNo is null");
            }

            Long seq = data.getDtlSeq();
            ContentValues value = makeNKMJSuteDtlValue(data);

            NK_MJ_SUTE_DTL_TABLE tableData = selectNKMJSuteDTL(spNo, seq);

            Log.d("db.spmj", "tableData is " + (tableData != null));

            if (tableData != null) {
                db.update( NK_MJ_SUTE_DTL_TABLE.TABLE_NAME,
                           value,
                           NK_MJ_SUTE_DTL_TABLE.CLMN_NK_SEQ + "=? and " + NK_MJ_SUTE_DTL_TABLE.CLMN_NK_DTL_SEQ + "=?",
                           new String[]{ String.valueOf( data.getNkSeq()), String.valueOf(data.getDtlSeq())});

                return ;
            } else {
                seq = selectNKMJSuteDTLMaxSeq(spNo) + 1;
                value.put( NK_MJ_SUTE_DTL_TABLE.CLMN_NK_DTL_SEQ, seq);
                db.insert( NK_MJ_SUTE_DTL_TABLE.TABLE_NAME, null, value);
            }
    }

    private ContentValues makeNKMJSuteDtlValue(NK_MJ_SUTE_DTL_TABLE data) {
        ContentValues values = new ContentValues();
        values.put( NK_MJ_SUTE_DTL_TABLE.CLMN_NK_SEQ, data.getNkSeq());
        values.put( NK_MJ_SUTE_DTL_TABLE.CLMN_NK_DTL_SEQ, data.getDtlSeq());
        
        if (data.getSutehaiArray() != null ) {
            for (int i = 1; i <= data.getSutehaiArray().length; i++) {
                values.put( NK_MJ_SUTE_DTL_TABLE.CLMN_HAI_COMMON + i, data.getSutehaiArray()[i - 1]);
            }
        }

        values.put( NK_MJ_SUTE_DTL_TABLE.CLMN_NK_WIND, data.getWind());
        values.put( NK_MJ_SUTE_DTL_TABLE.CLMN_NK_SCORE, data.getScore());
        values.put( NK_MJ_SUTE_DTL_TABLE.CLMN_ST_CNT, data.getSuteCnt());
        return values;
    }


    private NK_MJ_SUTE_DTL_TABLE makeNKMJSuteDtlFromCursor(Cursor cursor) {

        if (cursor == null || !cursor.moveToFirst()) {
            Log.i("NkMajanDao", "crusor is null");
            return null;
        }

        NK_MJ_SUTE_DTL_TABLE data = new NK_MJ_SUTE_DTL_TABLE();
        int cursorIndex = 0;

        data.setNkSeq(cursor.getLong(cursorIndex++));
        data.setDtlSeq(cursor.getLong(cursorIndex++));
        data.setWind(cursor.getString(cursorIndex++));
        data.setScore(cursor.getString(cursorIndex++));

        data.setSuteCnt(cursor.getInt(cursorIndex++));

        String[] haiArray = new String[data.getSuteCnt()];
        for (int i = 0; i < data.getSuteCnt(); i++) {
            haiArray[i] = cursor.getString(cursorIndex++);
        }
        data.setSutehaiArray(haiArray);

        return data;
    }

}
