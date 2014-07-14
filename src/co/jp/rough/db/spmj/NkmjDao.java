package co.jp.rough.db.spmj;

import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import co.jp.rough.common.CommonFunc;
import co.jp.rough.db.NK_MJ_ANSWER;
import co.jp.rough.db.NK_MJ_DTL_TABLE;
import co.jp.rough.db.NK_MJ_SUTE_DTL_TABLE;
import co.jp.rough.db.NK_MJ_TABLE;
import co.jp.rough.db.NkMajanDBManager;

public class NkmjDao {

    public void save( NK_MJ_TABLE data){
        SQLiteDatabase db = NkMajanDBManager.getNKHelper().getWritableDatabase();
        saveNKMJ(db, data);

        if (data.getDtlData() != null) {
            new NkmjDtlDao().saveNKMJDTL(data.getDtlData());
        }

        if (data.getAnswerData() != null) {
            new NkmjAnswerDao().saveNKMJAnswer(data.getAnswerData());
        }
        
        List<NK_MJ_SUTE_DTL_TABLE> suteList = data.getSuteList();

        if (suteList != null && suteList.size() > 0) {
            new NkmjSuteDtlDao().saveNKMJSuteDTL(suteList);
        }
    }

    /**
     *
     * @param db
     * @param data
     * @return
     */
    public void saveNKMJ(SQLiteDatabase db, NK_MJ_TABLE data) {
        ContentValues values = new ContentValues();
        values.put( NK_MJ_TABLE.CLMN_NK_SEQ, data.getSeq());
        values.put( NK_MJ_TABLE.CLMN_MY_WIND, data.getWind());
        values.put( NK_MJ_TABLE.CLMN_ROUND, data.getRound());
        values.put( NK_MJ_TABLE.CLMN_SCORE, data.getScore());
        values.put( NK_MJ_TABLE.CLMN_INDEX, data.getIndex());

        if (!CommonFunc.isEmpty(data.getRegDm())) {
            values.put( NK_MJ_TABLE.CLMN_REG_DM, data.getRegDm());
        }
        values.put( NK_MJ_TABLE.CLMN_REG_ID, data.getRegId());
        values.put( NK_MJ_TABLE.CLMN_DORA, data.getDora());
        values.put( NK_MJ_TABLE.CLMN_KAN_DORA, data.getKanDora());

        if( selectNKMJ(data.getSeq()) == null){
            
            if (CommonFunc.isEmpty(data.getRegDm())) {
                values.put( NK_MJ_TABLE.CLMN_REG_DM, CommonFunc.getCurrentDate());
            }
            db.insert( NK_MJ_TABLE.TABLE_NAME, null, values);
        }
        else{
            db.update( NK_MJ_TABLE.TABLE_NAME, values,NK_MJ_TABLE.CLMN_NK_SEQ + "=?", new String[]{ String.valueOf( data.getSeq())});
        }
        
    }

    public NK_MJ_TABLE selectNKMJ(Long seq) {
        SQLiteDatabase db = NkMajanDBManager.getNKHelper().getReadableDatabase();

        NK_MJ_TABLE data = null;
        Cursor cursor = db.query( NK_MJ_TABLE.TABLE_NAME, null, NK_MJ_TABLE.CLMN_NK_SEQ + "=?", new String[]{ String.valueOf( seq)}, null, null, null);
        if (cursor.moveToFirst()) {
            data = makeNKMJFromCursor(cursor);
        }
        return data;

    }

    public NK_MJ_TABLE selectNKMJAll(Long seq) {
        SQLiteDatabase db = NkMajanDBManager.getNKHelper().getReadableDatabase();

        NK_MJ_TABLE data = null;
        Cursor cursor = db.query( NK_MJ_TABLE.TABLE_NAME, null, NK_MJ_TABLE.CLMN_NK_SEQ + "=?", new String[]{ String.valueOf( seq)}, null, null, null);
        if (cursor.moveToFirst()) {
            data = makeNKMJFromCursor(cursor);
            
            NK_MJ_DTL_TABLE dtlData = new NkmjDtlDao().selectNKMJDTL(seq);
            data.setDtlData(dtlData);

            NK_MJ_ANSWER answerData = new NkmjAnswerDao().selectNKMJAnswer(seq);
            data.setAnswerData(answerData);

            List<NK_MJ_SUTE_DTL_TABLE> suteData = new NkmjSuteDtlDao().selectNKMJSuteALL(seq);
            data.setSuteList(suteData);
        }
        
        return data;

    }

    
    private static final String SELECTMAX = "SELECT MAX(" + NK_MJ_TABLE.CLMN_NK_SEQ +") FROM " + NK_MJ_TABLE.TABLE_NAME;

    /**
     * @param spNo
     * @return
     */
    public Long selectNKMJMaxSeq() {
        SQLiteDatabase db = NkMajanDBManager.getNKHelper().getReadableDatabase();

        String myString=DatabaseUtils.stringForQuery(db,SELECTMAX,null);
        Long index;
        if (myString == null) {
            index = Long.valueOf(0L);
        } else {
            index = Long.valueOf(myString);
        }

        return index;
    }

    
    
    private NK_MJ_TABLE makeNKMJFromCursor(Cursor cursor) {
        NK_MJ_TABLE data = new NK_MJ_TABLE();

        data.setSeq(cursor.getLong(0));
        
        data.setRound(cursor.getString(1));
        data.setIndex(cursor.getString(2));
        data.setScore(cursor.getString(3));
        data.setWind(cursor.getString(4));
        
        data.setRegDm(cursor.getString(5));
        data.setRegId(cursor.getString(6));
        data.setDora(cursor.getString(7));
        data.setKanDora(cursor.getString(8));

        return data;
    }

}
