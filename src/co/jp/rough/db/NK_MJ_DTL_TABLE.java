package co.jp.rough.db;

import java.io.Serializable;

public class NK_MJ_DTL_TABLE implements Serializable {
    public static final String TABLE_NAME = "NK_MJ_DTL";

    public static final String CLMN_NK_SEQ = "nk_seq";

    public static final String CLMN_HAI_COMMON = "hai_";
    public static final int HAI_CNT = 13;
    
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

    public static final String CLMN_HAI_TUMO = "hai_tumo";
    public static final String CLMN_HAI_SUTE = "hai_sute";

    public Long getNkSeq() {
        return nkSeq;
    }
    public void setNkSeq(Long spNo) {
        this.nkSeq = spNo;
    }
    public String getHaiTumo() {
        return haiTumo;
    }
    public void setHaiTumo(String haiTumo) {
        this.haiTumo = haiTumo;
    }
    public String getHaiSute() {
        return haiSute;
    }
    public void setHaiSute(String haiSute) {
        this.haiSute = haiSute;
    }
    public String[] getHaiArray() {
        return haiArray;
    }
    public void setHaiArray(String[] haiArray) {
        this.haiArray = haiArray;
    }

    private String[] haiArray = null;

    private Long nkSeq;

    private String haiTumo = null;
    private String haiSute = null;

}
