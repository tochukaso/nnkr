package co.jp.rough.db;

import java.io.Serializable;
import java.util.List;

public class NK_MJ_ANSWER implements Serializable {
    public static final String TABLE_NAME = "NK_MJ_ANSWER";

    public static final String CLMN_NK_SEQ = "nk_seq";

    public static final String CLMN_ANS_COMMON = "ans_";
    public static final int ANS_CNT = 14;
    
    public static final String CLMN_ANS_1 = "ans_1";
    public static final String CLMN_ANS_2 = "ans_2";
    public static final String CLMN_ANS_3 = "ans_3";
    public static final String CLMN_ANS_4 = "ans_4";
    public static final String CLMN_ANS_5 = "ans_5";
    public static final String CLMN_ANS_6 = "ans_6";
    public static final String CLMN_ANS_7 = "ans_7";
    public static final String CLMN_ANS_8 = "ans_8";
    public static final String CLMN_ANS_9 = "ans_9";
    public static final String CLMN_ANS_10 = "ans_10";
    public static final String CLMN_ANS_11 = "ans_11";
    public static final String CLMN_ANS_12 = "ans_12";
    public static final String CLMN_ANS_13 = "ans_13";
    public static final String CLMN_ANS_14 = "ans_14";

    public static final String CLMN_COMMENT_1 = "comment_1";
    public static final String CLMN_COMMENT_2 = "comment_2";
    public static final String CLMN_COMMENT_3 = "comment_3";

    public static final String CLMN_REG_DM = "reg_dm";
    public static final String CLMN_UPD_DM = "upd_dm";
    public static final String CLMN_USER_ANSWER = "user_answer";

    
    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getComment1() {
        return comment1;
    }

    public void setComment1(String comment1) {
        this.comment1 = comment1;
    }

    public String getComment2() {
        return comment2;
    }

    public void setComment2(String comment2) {
        this.comment2 = comment2;
    }

    public String getComment3() {
        return comment3;
    }

    public void setComment3(String comment3) {
        this.comment3 = comment3;
    }

    public List<String> getPointList() {
        return pointList;
    }

    public void setPointList(List<String> pointList) {
        this.pointList = pointList;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getRegDm() {
        return regDm;
    }

    public void setRegDm(String regDm) {
        this.regDm = regDm;
    }

    public String getUpdDm() {
        return updDm;
    }

    public void setUpdDm(String updDm) {
        this.updDm = updDm;
    }

    private Long seq;
    
    private String comment1;
    private String comment2;
    private String comment3;

    private String userAnswer;
    private String regDm;
    private String updDm;
    
    private List<String> pointList;
    
}
