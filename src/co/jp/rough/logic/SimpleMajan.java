package co.jp.rough.logic;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import co.jp.rough.common.CommonFunc;
import co.jp.rough.db.SP_MJ_DTL_TABLE;
import co.jp.rough.db.SP_MJ_TABLE;
import co.jp.rough.db.spmj.SpmjDao;
import co.jp.rough.db.spmj.SpmjDtlDao;
import co.jp.rough.hai.Hai;
import co.jp.rough.hai.HaiManager;
import co.jp.rough.nnkr.TehaiView;
import co.jp.rough.tehai.Tehai;

public class SimpleMajan {

    TehaiView view;

    public void startGame(Activity activity) {
        HaiManager.initHaiList();
        view = new TehaiView(activity, this);

        Tehai tehai = new Tehai();
        tehai.setHaipai();

        view.setTehai(tehai);

        Hai dora = HaiManager.drawHai();
        view.setDora(dora);

        view.setSeq(Long.valueOf(1));
        activity.setContentView(view);

        view.setSuteList(new ArrayList<Hai>());

        saveInital();
    }


    /**
     * �ĊJ
     * @param activity
     */
    public void reStartGame(Activity activity) {
    }


    public Tehai getTehai() {
        return(view.getTehai());
    }

    public void saveInital() {

        SP_MJ_TABLE data = new SP_MJ_TABLE();

        data.setRegDm(CommonFunc.getCurrentDate());
        data.setRegId("y-ohmori");
        data.setDora(view.getDora().getHaiNm());

        Long spNo = new SpmjDao().save(data);
        view.setSpNo(spNo);
    }

    public void saveTehai() {

        List<SP_MJ_DTL_TABLE> list = new ArrayList<SP_MJ_DTL_TABLE>();

        SP_MJ_DTL_TABLE dtl = CommonFunc.convTehaiToSPMJ(getTehai());
        dtl.setSeq(view.getSeq());

        dtl.setSpNo(view.getSpNo());

        if (view.getSute() != null) {
            dtl.setHaiSute(view.getSute().getHaiNm());
        }

        if (view.getTumo() != null) {
            dtl.setHaiTumo(view.getTumo().getHaiNm());
        }

        list.add(dtl);

        new SpmjDtlDao().saveSPMJDTL(dtl);

    }

    public SP_MJ_TABLE convTehaiToSPMJ(Tehai tehai) {
        SP_MJ_TABLE spTable = new SP_MJ_TABLE();
        spTable.setRegDm(CommonFunc.getCurrentDate());

        return spTable;
    }

}

