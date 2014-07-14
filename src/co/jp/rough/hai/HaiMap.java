package co.jp.rough.hai;

import java.util.HashMap;
import java.util.Map;

import co.jp.rough.nnkr.R;

public class HaiMap {

    private static Map<String, Integer> haiMap;

    static {
        haiMap = new HashMap<String, Integer>();
        haiMap.put(String.valueOf(1),R.drawable.man1);
        haiMap.put(String.valueOf(2),R.drawable.man2);
        haiMap.put(String.valueOf(3),R.drawable.man3);
        haiMap.put(String.valueOf(4),R.drawable.man4);
        haiMap.put(String.valueOf(5),R.drawable.man5);
        haiMap.put(String.valueOf(6),R.drawable.man6);
        haiMap.put(String.valueOf(7),R.drawable.man7);
        haiMap.put(String.valueOf(8),R.drawable.man8);
        haiMap.put(String.valueOf(9),R.drawable.man9);
        haiMap.put(String.valueOf(10),R.drawable.pin1);
        haiMap.put(String.valueOf(11),R.drawable.pin2);
        haiMap.put(String.valueOf(12),R.drawable.pin3);
        haiMap.put(String.valueOf(13),R.drawable.pin4);
        haiMap.put(String.valueOf(14),R.drawable.pin5);
        haiMap.put(String.valueOf(15),R.drawable.pin6);
        haiMap.put(String.valueOf(16),R.drawable.pin7);
        haiMap.put(String.valueOf(17),R.drawable.pin8);
        haiMap.put(String.valueOf(18),R.drawable.pin9);
        haiMap.put(String.valueOf(19),R.drawable.sou1);
        haiMap.put(String.valueOf(20),R.drawable.sou2);
        haiMap.put(String.valueOf(21),R.drawable.sou3);
        haiMap.put(String.valueOf(22),R.drawable.sou4);
        haiMap.put(String.valueOf(23),R.drawable.sou5);
        haiMap.put(String.valueOf(24),R.drawable.sou6);
        haiMap.put(String.valueOf(25),R.drawable.sou7);
        haiMap.put(String.valueOf(26),R.drawable.sou8);
        haiMap.put(String.valueOf(27),R.drawable.sou9);
        haiMap.put(String.valueOf(28),R.drawable.ji1_ton);
        haiMap.put(String.valueOf(29),R.drawable.ji2_nan);
        haiMap.put(String.valueOf(30),R.drawable.ji3_sha);
        haiMap.put(String.valueOf(31),R.drawable.ji4_pei);
        haiMap.put(String.valueOf(32),R.drawable.ji5_haku);
        haiMap.put(String.valueOf(33),R.drawable.ji6_hatsu);
        haiMap.put(String.valueOf(34),R.drawable.ji7_chun);

    }
    public static int getImg(int num) {
        return haiMap.get(String.valueOf(num));
    }
}
