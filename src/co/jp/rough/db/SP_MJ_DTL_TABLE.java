package co.jp.rough.db;

import java.io.Serializable;

public class SP_MJ_DTL_TABLE implements Serializable {
    // �e�[�u����
    public static final String TABLE_NAME = "SP_MJ_DTL";

    // �J������
    public static final String CLMN_SP_NO = "sp_no";

    public static final String CLMN_SEQ = "seq";

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

    public Long getSpNo() {
        return spNo;
    }
    public void setSpNo(Long spNo) {
        this.spNo = spNo;
    }
    public Long getSeq() {
        return seq;
    }
    public void setSeq(Long seq) {
        this.seq = seq;
    }
    public String getHai1() {
        return hai1;
    }
    public void setHai1(String hai1) {
        this.hai1 = hai1;
    }
    public String getHai2() {
        return hai2;
    }
    public void setHai2(String hai2) {
        this.hai2 = hai2;
    }
    public String getHai3() {
        return hai3;
    }
    public void setHai3(String hai3) {
        this.hai3 = hai3;
    }
    public String getHai4() {
        return hai4;
    }
    public void setHai4(String hai4) {
        this.hai4 = hai4;
    }
    public String getHai5() {
        return hai5;
    }
    public void setHai5(String hai5) {
        this.hai5 = hai5;
    }
    public String getHai6() {
        return hai6;
    }
    public void setHai6(String hai6) {
        this.hai6 = hai6;
    }
    public String getHai7() {
        return hai7;
    }
    public void setHai7(String hai7) {
        this.hai7 = hai7;
    }
    public String getHai8() {
        return hai8;
    }
    public void setHai8(String hai8) {
        this.hai8 = hai8;
    }
    public String getHai9() {
        return hai9;
    }
    public void setHai9(String hai9) {
        this.hai9 = hai9;
    }
    public String getHai10() {
        return hai10;
    }
    public void setHai10(String hai10) {
        this.hai10 = hai10;
    }
    public String getHai11() {
        return hai11;
    }
    public void setHai11(String hai11) {
        this.hai11 = hai11;
    }
    public String getHai12() {
        return hai12;
    }
    public void setHai12(String hai12) {
        this.hai12 = hai12;
    }
    public String getHai13() {
        return hai13;
    }
    public void setHai13(String hai13) {
        this.hai13 = hai13;
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

    private Long spNo;
    private Long seq = null;
    private String hai1 = null;
    private String hai2 = null;
    private String hai3 = null;
    private String hai4 = null;
    private String hai5 = null;
    private String hai6 = null;
    private String hai7 = null;
    private String hai8 = null;
    private String hai9 = null;
    private String hai10 = null;
    private String hai11 = null;
    private String hai12 = null;
    private String hai13 = null;

    private String haiTumo = null;
    private String haiSute = null;

}
