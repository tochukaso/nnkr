package co.jp.rough.db.spmj;

import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import co.jp.rough.db.SP_MJ_DTL_TABLE;
import co.jp.rough.db.SimpleMajanDBManager;

public class SpmjDtlDao {

    /**
     * spNo seq�ɕR�Â����R�[�h�̎擾���s���܂��B
     * @param spNo
     * @param seq
     * @return
     */
    public SP_MJ_DTL_TABLE selectSPMJDTL(Long spNo, Long seq) {
        SQLiteDatabase db = SimpleMajanDBManager.getSMHelper().getReadableDatabase();

        SP_MJ_DTL_TABLE data = null;
        Cursor cursor = db.query( SP_MJ_DTL_TABLE.TABLE_NAME, null, SP_MJ_DTL_TABLE.CLMN_SP_NO + "=? and " + SP_MJ_DTL_TABLE.CLMN_SEQ + "=?", new String[]{ String.valueOf( spNo), String.valueOf(seq)}, null, null, null);
        cursor.moveToFirst();
        data = makeSPMJDtlFromCursor( cursor);
        return data;

    }

    private static final String SELECTMAX = "SELECT MAX(SEQ) FROM SP_MJ_DTL where sp_no = ? ";
    /**
     * spNo�ɕR�t���ő��SEQ��ԋp���܂��B
     * @param spNo
     * @return
     */
    public Long selectSMMJDTLMaxSeq(Long spNo) {
        SQLiteDatabase db = SimpleMajanDBManager.getSMHelper().getReadableDatabase();

        String[] tableColumns  = new String[] {                SP_MJ_DTL_TABLE.CLMN_SEQ,
                "(select     (" + SP_MJ_DTL_TABLE.CLMN_SEQ + ") from " + SP_MJ_DTL_TABLE.TABLE_NAME + ") AS max"
        };
        String whereClause =  SP_MJ_DTL_TABLE.CLMN_SP_NO + "=?";

        String[] whereArgs = new String[] {
                String.valueOf(spNo)
        };

        Log.d("db.spmj" , "selectSMMJDTLMaxSeq spNo is " + spNo);
        String myString=DatabaseUtils.stringForQuery(db,SELECTMAX,whereArgs);
        Log.d("db.spmj" , "dbx seq is " + myString);
/**
        Cursor cursor = db.query( SP_MJ_DTL_TABLE.TABLE_NAME, tableColumns, whereClause,whereArgs, null, null, null);
        cursor.moveToFirst();
        Long index = cursor.getLong(cursor.getColumnIndex("max"));
**/
        Long index;
        if (myString == null) {
            index = new Long(0);
        } else {
            index = Long.valueOf(myString);
        }

        Log.d("db.spmj" , "max seq is " + String.valueOf(index));
        return index;
    }

    /**
     * SPMJDTL�̕ۑ����s���܂��B
     * �v���C�}���[�L�[���ݒ肳��Ă���A���A�v���C�}���[�L�[�����SPMJ�ɓo�^����Ă���ꍇ�ɁA�X�V
     * ��L�ȊO�̏ꍇ�͑}����s���܂��B
     * @param db
     * @param list
     */
    public void saveSPMJDTL(List<SP_MJ_DTL_TABLE> list) {
        for (SP_MJ_DTL_TABLE data : list) {
            saveSPMJDTL(data);
        }
    }

    /**
     * SPMJDTL�̕ۑ����s���܂��B
     * �v���C�}���[�L�[���ݒ肳��Ă���A���A�v���C�}���[�L�[�����SPMJ�ɓo�^����Ă���ꍇ�ɁA�X�V
     * ��L�ȊO�̏ꍇ�͑}����s���܂��B
     * @param db
     * @param list
     */
    public void saveSPMJDTL(SP_MJ_DTL_TABLE data) {
        SQLiteDatabase db = SimpleMajanDBManager.getSMHelper().getWritableDatabase();
            Long spNo = data.getSpNo();

            if (spNo == null) {
                Log.e("db.spmj", "saveSPMJDTL error spNo is null");
                throw new IllegalArgumentException("spNo is null");
            }

            Long seq = data.getSeq();
            Log.d("db.spmj", "seq is " + seq);
            ContentValues value = makeSPMJDtlValue(data);

            if (seq != null ) {
                SP_MJ_DTL_TABLE tableData = selectSPMJDTL(spNo, data.getSeq());

                Log.d("db.spmj", "tableData is " + (tableData != null));

                if (tableData != null) {
                    db.update( SP_MJ_DTL_TABLE.TABLE_NAME, value,SP_MJ_DTL_TABLE.CLMN_SP_NO + "=? and " + SP_MJ_DTL_TABLE.CLMN_SEQ + "=?", new String[]{ String.valueOf( data.getSpNo()), String.valueOf(data.getSeq())});
                    Log.d("db.spmj", "isUpdate finish");

                    return ;
                }
            }

            seq = Long.valueOf(selectSMMJDTLMaxSeq(spNo) + 1);

            Log.d("db.spmj", "DB seq is " + seq);

            value.put( SP_MJ_DTL_TABLE.CLMN_SEQ, seq);
            db.insert( SP_MJ_DTL_TABLE.TABLE_NAME, null, value);

            Log.d("db.spmj", "dtl insert finish");
    }

    private ContentValues makeSPMJDtlValue(SP_MJ_DTL_TABLE data) {
        ContentValues values = new ContentValues();
        values.put( SP_MJ_DTL_TABLE.CLMN_SP_NO, data.getSpNo());
        values.put( SP_MJ_DTL_TABLE.CLMN_SEQ, data.getSeq());
        values.put( SP_MJ_DTL_TABLE.CLMN_HAI_1, data.getHai1());

        values.put( SP_MJ_DTL_TABLE.CLMN_HAI_2, data.getHai2());
        values.put( SP_MJ_DTL_TABLE.CLMN_HAI_3, data.getHai3());
        values.put( SP_MJ_DTL_TABLE.CLMN_HAI_4, data.getHai4());
        values.put( SP_MJ_DTL_TABLE.CLMN_HAI_5, data.getHai5());
        values.put( SP_MJ_DTL_TABLE.CLMN_HAI_6, data.getHai6());
        values.put( SP_MJ_DTL_TABLE.CLMN_HAI_7, data.getHai7());
        values.put( SP_MJ_DTL_TABLE.CLMN_HAI_8, data.getHai8());
        values.put( SP_MJ_DTL_TABLE.CLMN_HAI_9, data.getHai9());
        values.put( SP_MJ_DTL_TABLE.CLMN_HAI_10, data.getHai10());
        values.put( SP_MJ_DTL_TABLE.CLMN_HAI_11, data.getHai11());
        values.put( SP_MJ_DTL_TABLE.CLMN_HAI_12, data.getHai12());
        values.put( SP_MJ_DTL_TABLE.CLMN_HAI_13, data.getHai13());

        values.put( SP_MJ_DTL_TABLE.CLMN_HAI_TUMO, data.getHaiTumo());
        values.put( SP_MJ_DTL_TABLE.CLMN_HAI_SUTE, data.getHaiSute());
        return values;
    }


    private SP_MJ_DTL_TABLE makeSPMJDtlFromCursor(Cursor cursor) {

        if (cursor == null || !cursor.moveToFirst()) {
            Log.i("SimpleMajanDao", "crusor is null");
            return null;
        }

        SP_MJ_DTL_TABLE data = new SP_MJ_DTL_TABLE();
        data.setSpNo(cursor.getLong(0));
        data.setSeq(cursor.getLong(1));

        data.setHai1(cursor.getString(2));
        data.setHai2(cursor.getString(3));
        data.setHai3(cursor.getString(4));
        data.setHai4(cursor.getString(5));
        data.setHai5(cursor.getString(6));
        data.setHai6(cursor.getString(7));
        data.setHai7(cursor.getString(8));
        data.setHai8(cursor.getString(9));
        data.setHai9(cursor.getString(10));
        data.setHai10(cursor.getString(11));
        data.setHai11(cursor.getString(12));
        data.setHai12(cursor.getString(13));
        data.setHai13(cursor.getString(14));

        data.setHaiTumo(cursor.getString(15));
        data.setHaiSute(cursor.getString(16));

        return data;
    }



}
