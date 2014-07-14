package co.jp.rough.db;

import java.io.Serializable;
import java.util.List;

public class NK_MJ_TABLE implements Serializable {
    public static final String TABLE_NAME = "NK_MJ";

    public static final String CLMN_NK_SEQ = "nk_seq";

    // 局数(東1局～西1局)
    public static final String CLMN_ROUND = "round";
    // 順目
    public static final String CLMN_INDEX = "nk_index";
    // 持ち点
    public static final String CLMN_SCORE = "score";
    // 自風
    public static final String CLMN_MY_WIND = "my_wind";
    
    public static final String CLMN_REG_DM = "reg_dm";
    public static final String CLMN_REG_ID = "reg_id";

    public static final String CLMN_DORA = "dora";
    public static final String CLMN_KAN_DORA = "kan_dora";

    public Long getSeq() {
        return seq;
    }
    public void setSeq(Long seq) {
        this.seq = seq;
    }
    public String getRegDm() {
        return regDm;
    }
    public void setRegDm(String regDm) {
        this.regDm = regDm;
    }
    public String getRegId() {
        return regId;
    }
    public void setRegId(String regId) {
        this.regId = regId;
    }
    public String getDora() {
        return dora;
    }
    public void setDora(String dora) {
        this.dora = dora;
    }
    public String getRound() {
        return round;
    }
    public void setRound(String round) {
        this.round = round;
    }
    public String getIndex() {
        return index;
    }
    public void setIndex(String index) {
        this.index = index;
    }
    public String getScore() {
        return score;
    }
    public void setScore(String score) {
        this.score = score;
    }
    public String getKanDora() {
        return kanDora;
    }
    public void setKanDora(String kanDora) {
        this.kanDora = kanDora;
    }
    public String getWind() {
        return wind;
    }
    public void setWind(String kaze) {
        this.wind = kaze;
    }

    public NK_MJ_DTL_TABLE getDtlData() {
        return dtlData;
    }
    public void setDtlData(NK_MJ_DTL_TABLE dtlData) {
        this.dtlData = dtlData;
    }
    public List<NK_MJ_SUTE_DTL_TABLE> getSuteList() {
        return suteList;
    }
    public void setSuteList(List<NK_MJ_SUTE_DTL_TABLE> suteList) {
        this.suteList = suteList;
    }

    public NK_MJ_ANSWER getAnswerData() {
        return answerData;
    }
    public void setAnswerData(NK_MJ_ANSWER answerData) {
        this.answerData = answerData;
    }

    private Long seq;

    private String round = null;
    private String index = null;
    private String score = null;

    private String wind = null;

    private String regDm = null;
    private String regId = null;
    private String dora = null;
    private String kanDora = null;

    private NK_MJ_DTL_TABLE dtlData;
    private List<NK_MJ_SUTE_DTL_TABLE> suteList;

    private NK_MJ_ANSWER answerData;
    
}
