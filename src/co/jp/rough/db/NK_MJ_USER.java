package co.jp.rough.db;

import java.io.Serializable;
import java.util.List;

public class NK_MJ_USER implements Serializable {
    public static final String TABLE_NAME = "NK_MJ_USER";

    public static final String CLMN_USER_ID = "user_id";
    public static final String CLMN_LANK = "lank";
    public static final String CLMN_POINT = "point";
    public static final String CLMN_LAST_ANSWER_SEQ = "last_answer_seq";
    
    public static final String CLMN_REG_DM = "reg_dm";
    public static final String CLMN_UPD_DM = "upd_dm";


    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getLank() {
        return lank;
    }
    public void setLank(String lank) {
        this.lank = lank;
    }
    public String getPoint() {
        return point;
    }
    public void setPoint(String point) {
        this.point = point;
    }
    public Long getLastAnswerSeq() {
        return lastAnswerSeq;
    }
    public void setLastAnswerSeq(Long lastAnswerSeq) {
        this.lastAnswerSeq = lastAnswerSeq;
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
    private String userId = null;
    private String lank = null;
    private String point = null;
    private Long lastAnswerSeq = null;

    private String regDm = null;
    private String updDm = null;
    
}
