package co.jp.rough.db.spmj;

import static co.jp.rough.common.CommonFunc.*;

import java.util.Currency;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import co.jp.rough.db.NK_MJ_DTL_TABLE;
import co.jp.rough.db.NK_MJ_SUTE_DTL_TABLE;
import co.jp.rough.db.NK_MJ_USER;
import co.jp.rough.db.NkMajanDBManager;

public class NkmjUserDao {

    public void save( NK_MJ_USER data){
        SQLiteDatabase db = NkMajanDBManager.getNKHelper().getWritableDatabase();
        saveNKUser(db, data);
    }

    public void updateUserLastAnswer(Long lastAnswerSeq) {
        NK_MJ_USER data = new NK_MJ_USER();
        data.setLastAnswerSeq(lastAnswerSeq);
        save (data);
    }
    

    public void updateUserLastAnswerAndAddPoint(Long lastAnswerSeq, String addPoint) {
        NK_MJ_USER data = new NK_MJ_USER();
        data.setLastAnswerSeq(lastAnswerSeq);
        save (data);
    }
/**
     *
     * @param db
     * @param data
     * @return
     */
    public void saveNKUser(SQLiteDatabase db, NK_MJ_USER data) {
        ContentValues values = new ContentValues();
        if(!isEmpty(data.getUserId())) values.put( NK_MJ_USER.CLMN_USER_ID, data.getUserId());
        if(!isEmpty(data.getLank())) values.put( NK_MJ_USER.CLMN_LANK, data.getLank());
        if(!isEmpty(data.getPoint())) values.put( NK_MJ_USER.CLMN_POINT, data.getPoint());
        if(!isEmptyOrZERO(data.getLastAnswerSeq())) values.put( NK_MJ_USER.CLMN_LAST_ANSWER_SEQ, data.getLastAnswerSeq());
        if(!isEmpty(data.getRegDm())) {
            values.put( NK_MJ_USER.CLMN_REG_DM, data.getRegDm());
        } else {
            values.put( NK_MJ_USER.CLMN_UPD_DM, getCurrentDate());
        }
        
        NK_MJ_USER oldData = selectNKMJ();
        
        if( oldData == null){
            db.insert( NK_MJ_USER.TABLE_NAME, null, values);
        }
        else{
            db.update( NK_MJ_USER.TABLE_NAME, values, null, null);
        }
        
    }

    public NK_MJ_USER selectNKMJ() {
        SQLiteDatabase db = NkMajanDBManager.getNKHelper().getReadableDatabase();

        NK_MJ_USER data = null;
        Cursor cursor = db.query( NK_MJ_USER.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            data = makeNKMJFromCursor(cursor);
        }
        return data;
    }
    
    
    private NK_MJ_USER makeNKMJFromCursor(Cursor cursor) {
        NK_MJ_USER data = new NK_MJ_USER();

        data.setUserId(cursor.getString(0));
        
        data.setLank(cursor.getString(1));
        data.setPoint(cursor.getString(2));
        data.setLastAnswerSeq(cursor.getLong(3));
        data.setRegDm(cursor.getString(4));
        data.setUpdDm(cursor.getString(5));

        return data;
    }

}
