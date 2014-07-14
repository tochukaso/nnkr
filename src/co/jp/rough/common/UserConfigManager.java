package co.jp.rough.common;

import co.jp.rough.db.NK_MJ_USER;
import co.jp.rough.db.spmj.NkmjUserDao;

public class UserConfigManager {

    static NK_MJ_USER userData = null;
    
    static Long sessionLastAnsSeq = null;
    
    public static void init() {
        
        userData = new NkmjUserDao().selectNKMJ();
        
        if (userData == null) {
            userData = new NK_MJ_USER();
            userData.setLank(Lank.getLank(0));
            userData.setPoint("0");
            userData.setRegDm(CommonFunc.getCurrentDate());
            userData.setLastAnswerSeq(0L);
            new NkmjUserDao().save(userData);
        }
    }
    
    public static NK_MJ_USER getUser() {
        return userData;
    }

    public static Long getLastAnsSeq() {
        return userData.getLastAnswerSeq();
    }

    public static Long getSessionLastAnsSeq() {
        return sessionLastAnsSeq;
    }

    public static void setSessionLastAnsSeq(Long seq) {
        sessionLastAnsSeq = seq;
    }

}
