package co.jp.rough.db;

import java.io.Serializable;

public class NK_MJ_SUTE_DTL_TABLE implements Serializable {
    public static final String TABLE_NAME = "NK_MJ_SUTE_DTL";

    public static final String CLMN_NK_SEQ = "nk_seq";
    public static final String CLMN_NK_DTL_SEQ = "nk_sute_dtl_seq";

    public static final String CLMN_NK_WIND = "wind";

    public static final String CLMN_NK_SCORE = "score";
    
    public static final String CLMN_ST_CNT = "suteCnt"; 
    
    public static final String CLMN_HAI_COMMON = "hai_";
    
    public static final String CLMN_HAI_1 = "hai_1";
    public static final String CLMN_HAI_2 = "hai_2";
    public static final String CLMN_HAI_3 = "hai_3";
    public static final String CLMN_HAI_4 = "hai_4";
    public static final String CLMN_HAI_5 = "hai_5";
    public static final String CLMN_HAI_6 = "hai_6";
    public static final String CLMN_HAI_7 = "hai_7";
    public static final String CLMN_HAI_8 = "hai_8";
    public static final String CLMN_HAI_9 = "hai_9";
    public static final String CLMN_HAI_10 = "hai_10";
    public static final String CLMN_HAI_11 = "hai_11";
    public static final String CLMN_HAI_12 = "hai_12";
    public static final String CLMN_HAI_13 = "hai_13";
    public static final String CLMN_HAI_14 = "hai_14";
    public static final String CLMN_HAI_15 = "hai_15";
    public static final String CLMN_HAI_16 = "hai_16";

    public Long getNkSeq() {
        return nkSeq;
    }
    public void setNkSeq(Long nkSeq) {
        this.nkSeq = nkSeq;
    }
    public Long getDtlSeq() {
        return dtlSeq;
    }
    public void setDtlSeq(Long seq) {
        this.dtlSeq = seq;
    }
    
    public int getSuteCnt() {
        return suteCnt;
    }
    public void setSuteCnt(int suteCnt) {
        this.suteCnt = suteCnt;
    }
    public String[] getSutehaiArray() {
        return sutehaiArray;
    }
    public void setSutehaiArray(String[] sutehaiArray) {
        this.sutehaiArray = sutehaiArray;
    }
    public String getWind() {
        return wind;
    }
    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getScore() {
        return score;
    }
    public void setScore(String score) {
        this.score = score;
    }

    private String score = null;

    private int suteCnt = 0;
    private String[] sutehaiArray = null;
    private String wind = null;

    private Long nkSeq;
    private Long dtlSeq = null;

}
