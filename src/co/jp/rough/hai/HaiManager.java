package co.jp.rough.hai;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.util.Log;

public class HaiManager {
    
    /** �v�摜�̉��� **/
    public static int HAI_WIDTH = 24;

    /** �v�摜�̏c�� **/
    public static int HAI_HEIGHT = 32;

    public static void setHaiWidth(int width) {
        HAI_WIDTH = width;
    }

    public static void setHaiHeight(int height) {
        HAI_HEIGHT = height;
    }
    
    public static final int HAI_CNT = 34;

    public static final int RED_CNT = 1;

    public static final int RED_MAN = 5;
    public static final int RED_PIN = 14;
    public static final int RED_SOU = 23;


    private static List<Hai> haiList;

    static {
        initHaiList();
    }

    public static void initHaiList() {
        List<Hai> list = new Vector<Hai>();
        for (int i = 1; i <= HAI_CNT; i++) {
            list.addAll(makeHaiUnit(i));
        }
        haiList = list;
    }

    public static Hai drawHai() {
        while (true) {
//            Log.d("HaiManager",  "listSize is " + haiList.size());
            int haiNum = (int) Math.floor(Math.random() * haiList.size());
//            Log.d("HaiManager",  "haiNum is " + haiNum);
            Hai hai = haiList.get(haiNum);
//            Log.d("HaiManager",  "hai is " + hai.getHaiNum() + " : isRed is " + hai.isRed());
            if (hai != null) {
                haiList.remove(haiNum);
                return hai;
            }
        }
    }

    /**
     * 牌山に残っている牌の数(未使用の牌)の数を返却する
     * @return
     */
    public static int reftHaiCnt () {
        return haiList.size();
    }

    private static List<Hai> makeHaiUnit(int num) {
        List<Hai> list = new ArrayList<Hai>(4);
        boolean redFlg = false;
        if (num == RED_MAN || num == RED_PIN || num == RED_SOU) {
            redFlg = true;
        }
        for (int i = 0; i < 4; i++) {
            Hai hai = new Hai(num, i == 0 && redFlg);
            list.add(hai);
        }
        return list;
    }

}
