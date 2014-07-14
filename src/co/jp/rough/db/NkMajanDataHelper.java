package co.jp.rough.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NkMajanDataHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "NK_MJ";

    private static final int DB_VERSION = 17;
    
    public NkMajanDataHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();

        try{
            createNKMJ(db);
            createNKMJDTL(db);
            createNKMJSUTEDTL(db);

            createNKUser(db);
            createNKAnswer(db);
            
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NK_MJ_TABLE.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + NK_MJ_DTL_TABLE.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + NK_MJ_SUTE_DTL_TABLE.TABLE_NAME);

        db.execSQL("DROP TABLE IF EXISTS " + NK_MJ_USER.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + NK_MJ_ANSWER.TABLE_NAME);
        onCreate(db);
    }

    private void createNKMJ(SQLiteDatabase db)  {
        StringBuilder createSql = new StringBuilder();
        createSql.append("create table " + NK_MJ_TABLE.TABLE_NAME + " (");
        createSql.append(NK_MJ_TABLE.CLMN_NK_SEQ + " integer primary key not null,");

        createSql.append(NK_MJ_TABLE.CLMN_ROUND + " text,");
        createSql.append(NK_MJ_TABLE.CLMN_INDEX + " text,");
        createSql.append(NK_MJ_TABLE.CLMN_SCORE + " text,");
        createSql.append(NK_MJ_TABLE.CLMN_MY_WIND + " text,");

        createSql.append(NK_MJ_TABLE.CLMN_REG_DM + " text,");
        createSql.append(NK_MJ_TABLE.CLMN_REG_ID + " text,");
        createSql.append(NK_MJ_TABLE.CLMN_DORA + " text,");
        createSql.append(NK_MJ_TABLE.CLMN_KAN_DORA + " text");
        createSql.append(")");
        db.execSQL( createSql.toString());

    }

    private void createNKMJDTL(SQLiteDatabase db)  {
        StringBuilder createSql = new StringBuilder();
        createSql.append("create table " + NK_MJ_DTL_TABLE.TABLE_NAME + " (");
        createSql.append(NK_MJ_DTL_TABLE.CLMN_NK_SEQ + " integer  not null,");
        createSql.append(NK_MJ_DTL_TABLE.CLMN_HAI_1 + " text,");
        createSql.append(NK_MJ_DTL_TABLE.CLMN_HAI_2 + " text,");
        createSql.append(NK_MJ_DTL_TABLE.CLMN_HAI_3 + " text,");
        createSql.append(NK_MJ_DTL_TABLE.CLMN_HAI_4 + " text,");
        createSql.append(NK_MJ_DTL_TABLE.CLMN_HAI_5 + " text,");
        createSql.append(NK_MJ_DTL_TABLE.CLMN_HAI_6 + " text,");
        createSql.append(NK_MJ_DTL_TABLE.CLMN_HAI_7 + " text,");
        createSql.append(NK_MJ_DTL_TABLE.CLMN_HAI_8 + " text,");
        createSql.append(NK_MJ_DTL_TABLE.CLMN_HAI_9 + " text,");
        createSql.append(NK_MJ_DTL_TABLE.CLMN_HAI_10 + " text,");
        createSql.append(NK_MJ_DTL_TABLE.CLMN_HAI_11 + " text,");
        createSql.append(NK_MJ_DTL_TABLE.CLMN_HAI_12 + " text,");
        createSql.append(NK_MJ_DTL_TABLE.CLMN_HAI_13 + " text,");

        createSql.append(NK_MJ_DTL_TABLE.CLMN_HAI_TUMO + " text,");
        createSql.append(NK_MJ_DTL_TABLE.CLMN_HAI_SUTE + " text,");
        createSql.append("PRIMARY KEY (" + NK_MJ_DTL_TABLE.CLMN_NK_SEQ + ")");

        createSql.append(")");
        db.execSQL( createSql.toString());

    }

    private void createNKMJSUTEDTL(SQLiteDatabase db)  {
        StringBuilder createSql = new StringBuilder();
        createSql.append("create table " + NK_MJ_SUTE_DTL_TABLE.TABLE_NAME + " (");
        createSql.append(NK_MJ_SUTE_DTL_TABLE.CLMN_NK_SEQ + " integer  not null,");
        createSql.append(NK_MJ_SUTE_DTL_TABLE.CLMN_NK_DTL_SEQ + " integer not null,");
        createSql.append(NK_MJ_SUTE_DTL_TABLE.CLMN_NK_WIND + " text,");
        createSql.append(NK_MJ_SUTE_DTL_TABLE.CLMN_NK_SCORE + " text,");
        createSql.append(NK_MJ_SUTE_DTL_TABLE.CLMN_ST_CNT + " text,");
        createSql.append(NK_MJ_SUTE_DTL_TABLE.CLMN_HAI_1 + " text,");
        createSql.append(NK_MJ_SUTE_DTL_TABLE.CLMN_HAI_2 + " text,");
        createSql.append(NK_MJ_SUTE_DTL_TABLE.CLMN_HAI_3 + " text,");
        createSql.append(NK_MJ_SUTE_DTL_TABLE.CLMN_HAI_4 + " text,");
        createSql.append(NK_MJ_SUTE_DTL_TABLE.CLMN_HAI_5 + " text,");
        createSql.append(NK_MJ_SUTE_DTL_TABLE.CLMN_HAI_6 + " text,");
        createSql.append(NK_MJ_SUTE_DTL_TABLE.CLMN_HAI_7 + " text,");
        createSql.append(NK_MJ_SUTE_DTL_TABLE.CLMN_HAI_8 + " text,");
        createSql.append(NK_MJ_SUTE_DTL_TABLE.CLMN_HAI_9 + " text,");
        createSql.append(NK_MJ_SUTE_DTL_TABLE.CLMN_HAI_10 + " text,");
        createSql.append(NK_MJ_SUTE_DTL_TABLE.CLMN_HAI_11 + " text,");
        createSql.append(NK_MJ_SUTE_DTL_TABLE.CLMN_HAI_12 + " text,");
        createSql.append(NK_MJ_SUTE_DTL_TABLE.CLMN_HAI_13 + " text,");
        createSql.append(NK_MJ_SUTE_DTL_TABLE.CLMN_HAI_14 + " text,");
        createSql.append(NK_MJ_SUTE_DTL_TABLE.CLMN_HAI_15 + " text,");
        createSql.append(NK_MJ_SUTE_DTL_TABLE.CLMN_HAI_16 + " text,");

        createSql.append("PRIMARY KEY (" + NK_MJ_SUTE_DTL_TABLE.CLMN_NK_SEQ + ", " + NK_MJ_SUTE_DTL_TABLE.CLMN_NK_DTL_SEQ + ")");
        createSql.append(")");
        db.execSQL( createSql.toString());
    }

    private void createNKUser(SQLiteDatabase db)  {
        StringBuilder createSql = new StringBuilder();
        createSql.append("create table " + NK_MJ_USER.TABLE_NAME + " (");
        createSql.append(NK_MJ_USER.CLMN_USER_ID + " text,");

        createSql.append(NK_MJ_USER.CLMN_LANK + " text,");
        createSql.append(NK_MJ_USER.CLMN_POINT + " text,");
        createSql.append(NK_MJ_USER.CLMN_LAST_ANSWER_SEQ + " integer,");
        createSql.append(NK_MJ_USER.CLMN_REG_DM + " text,");
        createSql.append(NK_MJ_USER.CLMN_UPD_DM + " text");

        createSql.append(")");
        db.execSQL( createSql.toString());

    }

    private void createNKAnswer(SQLiteDatabase db)  {
        StringBuilder createSql = new StringBuilder();
        createSql.append("create table " + NK_MJ_ANSWER.TABLE_NAME + " (");
        createSql.append(NK_MJ_ANSWER.CLMN_NK_SEQ + "  integer primary key not null,");

        createSql.append(NK_MJ_ANSWER.CLMN_ANS_1 + " text,");
        createSql.append(NK_MJ_ANSWER.CLMN_ANS_2 + " text,");
        createSql.append(NK_MJ_ANSWER.CLMN_ANS_3 + " text,");
        createSql.append(NK_MJ_ANSWER.CLMN_ANS_4 + " text,");
        createSql.append(NK_MJ_ANSWER.CLMN_ANS_5 + " text,");
        createSql.append(NK_MJ_ANSWER.CLMN_ANS_6 + " text,");
        createSql.append(NK_MJ_ANSWER.CLMN_ANS_7 + " text,");
        createSql.append(NK_MJ_ANSWER.CLMN_ANS_8 + " text,");
        createSql.append(NK_MJ_ANSWER.CLMN_ANS_9 + " text,");
        createSql.append(NK_MJ_ANSWER.CLMN_ANS_10 + " text,");
        createSql.append(NK_MJ_ANSWER.CLMN_ANS_11 + " text,");
        createSql.append(NK_MJ_ANSWER.CLMN_ANS_12 + " text,");
        createSql.append(NK_MJ_ANSWER.CLMN_ANS_13 + " text,");
        createSql.append(NK_MJ_ANSWER.CLMN_ANS_14 + " text,");
        createSql.append(NK_MJ_ANSWER.CLMN_COMMENT_1 + " text,");
        createSql.append(NK_MJ_ANSWER.CLMN_COMMENT_2 + " text,");
        createSql.append(NK_MJ_ANSWER.CLMN_COMMENT_3 + " text,");
        
        createSql.append(NK_MJ_ANSWER.CLMN_USER_ANSWER + " text,");
        createSql.append(NK_MJ_ANSWER.CLMN_REG_DM + " text,");
        createSql.append(NK_MJ_ANSWER.CLMN_UPD_DM + " text");

        createSql.append(")");
        db.execSQL( createSql.toString());

    }
}
