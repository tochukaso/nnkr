package co.jp.rough.db;

import java.io.Serializable;
import java.util.List;

public class SP_MJ_TABLE implements Serializable {
    // �e�[�u����
    public static final String TABLE_NAME = "SP_MJ";

    // �J������
    public static final String CLMN_SP_NO = "sp_no";
    public static final String CLMN_REG_DM = "reg_dm";
    public static final String CLMN_REG_ID = "reg_id";

    public static final String CLMN_DORA = "dora";

    public Long getSpNo() {
        return spNo;
    }
    public void setSpNo(Long spNo) {
        this.spNo = spNo;
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

    private Long spNo;
    private String regDm = null;
    private String regId = null;
    private String dora = null;

    public List<SP_MJ_DTL_TABLE> getDtlList() {
        return dtlList;
    }

    public void setDtlList(List<SP_MJ_DTL_TABLE> dtlList) {
        this.dtlList = dtlList;
    }

    private List<SP_MJ_DTL_TABLE> dtlList;

}
