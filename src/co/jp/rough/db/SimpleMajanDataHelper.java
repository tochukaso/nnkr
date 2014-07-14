package co.jp.rough.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SimpleMajanDataHelper extends SQLiteOpenHelper {

    // �f�[�^�x�[�X���̒萔
    private static final String DB_NAME = "SP_MJ";

    /**
     * �R���X�g���N�^
     */
    public SimpleMajanDataHelper(Context context) {
        // �w�肵���f�[�^�x�[�X�������݂��Ȃ��ꍇ�́A�V���ɍ쐬����onCreate()���Ă΂��
        // �o�[�W������ύX�����onUpgrade()���Ă΂��
        super(context, DB_NAME, null, 1);
    }

    /**
     * �f�[�^�x�[�X�̐����ɌĂяo�����̂ŁA �X�L�[�}�̐������s��
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();

        try{
            // �e�[�u���̐���
            createSPMJ(db);

            createSPMJDTL(db);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    /**
     * �f�[�^�x�[�X�̍X�V
     *
     * �e�N���X�̃R���X�g���N�^�ɓn��version��ύX�����Ƃ��ɌĂяo�����
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // �f�[�^�x�[�X�̍X�V
        db.execSQL("DROP TABLE IF EXISTS " + SP_MJ_TABLE.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SP_MJ_DTL_TABLE.TABLE_NAME);
        onCreate(db);
    }

    private void createSPMJ(SQLiteDatabase db)  {
        StringBuilder createSql = new StringBuilder();
        createSql.append("create table " + SP_MJ_TABLE.TABLE_NAME + " (");
        createSql.append(SP_MJ_TABLE.CLMN_SP_NO + " integer primary key autoincrement not null,");
        createSql.append(SP_MJ_TABLE.CLMN_REG_DM + " text,");
        createSql.append(SP_MJ_TABLE.CLMN_REG_ID + " text,");
        createSql.append(SP_MJ_TABLE.CLMN_DORA + " text");
        createSql.append(")");
        db.execSQL( createSql.toString());

    }

    private void createSPMJDTL(SQLiteDatabase db)  {
        StringBuilder createSql = new StringBuilder();
        createSql.append("create table " + SP_MJ_DTL_TABLE.TABLE_NAME + " (");
        createSql.append(SP_MJ_DTL_TABLE.CLMN_SP_NO + " integer  not null,");
        createSql.append(SP_MJ_DTL_TABLE.CLMN_SEQ + " integer not null,");
        createSql.append(SP_MJ_DTL_TABLE.CLMN_HAI_1 + " text,");
        createSql.append(SP_MJ_DTL_TABLE.CLMN_HAI_2 + " text,");
        createSql.append(SP_MJ_DTL_TABLE.CLMN_HAI_3 + " text,");
        createSql.append(SP_MJ_DTL_TABLE.CLMN_HAI_4 + " text,");
        createSql.append(SP_MJ_DTL_TABLE.CLMN_HAI_5 + " text,");
        createSql.append(SP_MJ_DTL_TABLE.CLMN_HAI_6 + " text,");
        createSql.append(SP_MJ_DTL_TABLE.CLMN_HAI_7 + " text,");
        createSql.append(SP_MJ_DTL_TABLE.CLMN_HAI_8 + " text,");
        createSql.append(SP_MJ_DTL_TABLE.CLMN_HAI_9 + " text,");
        createSql.append(SP_MJ_DTL_TABLE.CLMN_HAI_10 + " text,");
        createSql.append(SP_MJ_DTL_TABLE.CLMN_HAI_11 + " text,");
        createSql.append(SP_MJ_DTL_TABLE.CLMN_HAI_12 + " text,");
        createSql.append(SP_MJ_DTL_TABLE.CLMN_HAI_13 + " text,");

        createSql.append(SP_MJ_DTL_TABLE.CLMN_HAI_TUMO + " text,");
        createSql.append(SP_MJ_DTL_TABLE.CLMN_HAI_SUTE + " text,");
        createSql.append("PRIMARY KEY (" + SP_MJ_DTL_TABLE.CLMN_SP_NO + ", " + SP_MJ_DTL_TABLE.CLMN_SEQ + ")");

        createSql.append(")");
        db.execSQL( createSql.toString());

    }


}
