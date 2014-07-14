package co.jp.rough.db.spmj;

import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import co.jp.rough.db.NK_MJ_DTL_TABLE;
import co.jp.rough.db.NkMajanDBManager;

public class NkmjDtlDao {

    /**
     * spNo seq�ɕR�Â����R�[�h�̎擾���s���܂��B
     * @param spNo
     * @param seq
     * @return
     */
    public NK_MJ_DTL_TABLE selectNKMJDTL(Long seq) {
        SQLiteDatabase db = NkMajanDBManager.getNKHelper().getReadableDatabase();

        NK_MJ_DTL_TABLE data = null;
        Cursor cursor = db.query(
                NK_MJ_DTL_TABLE.TABLE_NAME,
                null,
                NK_MJ_DTL_TABLE.CLMN_NK_SEQ + "=? ",
                new String[]{ String.valueOf(seq)}, null, null, null);
        cursor.moveToFirst();
        data = makeNKMJDtlFromCursor( cursor);
        return data;

    }

    private static final String SELECTMAX = "SELECT MAX(SEQ) FROM NK_MJ_DTL where NK_no = ? ";

    /**
     * SPMJDTL�̕ۑ����s���܂��B
     * �v���C�}���[�L�[���ݒ肳��Ă���A���A�v���C�}���[�L�[�����SPMJ�ɓo�^����Ă���ꍇ�ɁA�X�V
     * ��L�ȊO�̏ꍇ�͑}����s���܂��B
     * @param db
     * @param list
     */
    public void saveNKMJDTL(List<NK_MJ_DTL_TABLE> list) {
        for (NK_MJ_DTL_TABLE data : list) {
            saveNKMJDTL(data);
        }
    }

    /**
     * SPMJDTL�̕ۑ����s���܂��B
     * �v���C�}���[�L�[���ݒ肳��Ă���A���A�v���C�}���[�L�[�����SPMJ�ɓo�^����Ă���ꍇ�ɁA�X�V
     * ��L�ȊO�̏ꍇ�͑}����s���܂��B
     * @param db
     * @param list
     */
    public void saveNKMJDTL(NK_MJ_DTL_TABLE data) {
        SQLiteDatabase db = NkMajanDBManager.getNKHelper().getWritableDatabase();
            Long spNo = data.getNkSeq();

            if (spNo == null) {
                throw new IllegalArgumentException("spNo is null");
            }

            ContentValues value = makeNKMJDtlValue(data);

            NK_MJ_DTL_TABLE tableData = selectNKMJDTL(spNo);

            Log.d("db.spmj", "tableData is " + (tableData != null));

            if (tableData != null) {
                db.update( NK_MJ_DTL_TABLE.TABLE_NAME,
                           value,
                           NK_MJ_DTL_TABLE.CLMN_NK_SEQ + "=? ",
                           new String[]{ String.valueOf( data.getNkSeq())});

                return ;
            } else {
                db.insert( NK_MJ_DTL_TABLE.TABLE_NAME, null, value);
            }
    }

    private ContentValues makeNKMJDtlValue(NK_MJ_DTL_TABLE data) {
        ContentValues values = new ContentValues();
        values.put( NK_MJ_DTL_TABLE.CLMN_NK_SEQ, data.getNkSeq());
        
        if (data.getHaiArray() != null ) {
            for (int i = 1; i <= data.getHaiArray().length; i++) {
                values.put( NK_MJ_DTL_TABLE.CLMN_HAI_COMMON + i, data.getHaiArray()[i - 1]);
            }
        }
        values.put( NK_MJ_DTL_TABLE.CLMN_HAI_TUMO, data.getHaiTumo());
        values.put( NK_MJ_DTL_TABLE.CLMN_HAI_SUTE, data.getHaiSute());
        return values;
    }


    private NK_MJ_DTL_TABLE makeNKMJDtlFromCursor(Cursor cursor) {

        if (cursor == null || !cursor.moveToFirst()) {
            Log.i("NkMajanDao", "crusor is null");
            return null;
        }

        NK_MJ_DTL_TABLE data = new NK_MJ_DTL_TABLE();
        int cursorIndex = 0;

        data.setNkSeq(cursor.getLong(cursorIndex++));
        String[] haiArray = new String[NK_MJ_DTL_TABLE.HAI_CNT];
        for (int i = 0; i < NK_MJ_DTL_TABLE.HAI_CNT; i++) {
            haiArray[i] = cursor.getString(cursorIndex++);
        }
        data.setHaiArray(haiArray);
        
        data.setHaiTumo(cursor.getString(cursorIndex++));
        data.setHaiSute(cursor.getString(cursorIndex++));

        return data;
    }

}
