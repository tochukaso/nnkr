package co.jp.rough.db.spmj;

import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import co.jp.rough.db.SP_MJ_DTL_TABLE;
import co.jp.rough.db.SP_MJ_TABLE;
import co.jp.rough.db.SimpleMajanDBManager;

public class SpmjDao {

    public Long save( SP_MJ_TABLE data){
        SQLiteDatabase db = SimpleMajanDBManager.getSMHelper().getWritableDatabase();
        Long spNo = saveSPMJ(db, data);

        List<SP_MJ_DTL_TABLE> list = data.getDtlList();

        if (list != null && list.size() > 0) {
            for (SP_MJ_DTL_TABLE dtlData : list) {
                dtlData.setSpNo(spNo);
            }
            new SpmjDtlDao().saveSPMJDTL(list);
        }

        return spNo;
    }

    /**
     *
     * @param db
     * @param data
     * @return
     */
    public Long saveSPMJ(SQLiteDatabase db, SP_MJ_TABLE data) {
        ContentValues values = new ContentValues();
        //      values.put( SP_MJ_TABLE.CLMN_SP_NO, data.getSpNo());
        values.put( SP_MJ_TABLE.CLMN_REG_DM, data.getRegDm());
        values.put( SP_MJ_TABLE.CLMN_REG_ID, data.getRegId());
        values.put( SP_MJ_TABLE.CLMN_DORA, data.getDora());

        Long spNo = data.getSpNo();
        // ID��null�̏ꍇ��insert
        if( spNo == null){
            spNo = db.insert( SP_MJ_TABLE.TABLE_NAME, null, values);
        }
        else{
            db.update( SP_MJ_TABLE.TABLE_NAME, values,SP_MJ_TABLE.CLMN_SP_NO + "=?", new String[]{ String.valueOf( spNo)});
        }
        return spNo;
    }

    public SP_MJ_TABLE selectSPMJ(Long spNo) {
        SQLiteDatabase db = SimpleMajanDBManager.getSMHelper().getReadableDatabase();

        SP_MJ_TABLE data = null;
            Cursor cursor = db.query( SP_MJ_TABLE.TABLE_NAME, null, SP_MJ_TABLE.CLMN_SP_NO + "=?", new String[]{ String.valueOf( spNo)}, null, null, null);
            cursor.moveToFirst();
            data = makeSPMJFromCursor(cursor);
        return data;

    }

    private SP_MJ_TABLE makeSPMJFromCursor(Cursor cursor) {
        SP_MJ_TABLE data = new SP_MJ_TABLE();

        data.setSpNo(cursor.getLong(0));
        data.setRegDm(cursor.getString(1));
        data.setRegId(cursor.getString(2));
        data.setDora(cursor.getString(3));

        return data;
    }

}
