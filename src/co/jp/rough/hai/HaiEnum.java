package co.jp.rough.hai;

import android.graphics.BitmapFactory;
import android.util.Log;
import co.jp.rough.nnkr.R;

public enum HaiEnum {

    man1(R.drawable.man1,1),
    man2(R.drawable.man2,2),
    man3(R.drawable.man3,3),
    man4(R.drawable.man4,4),
    man5(R.drawable.man5,5),
    man6(R.drawable.man6,6),
    man7(R.drawable.man7,7),
    man8(R.drawable.man8,8),
    man9(R.drawable.man9,9),
    pin1(R.drawable.pin1,10),
    pin2(R.drawable.pin2,11),
    pin3(R.drawable.pin3,12),
    pin4(R.drawable.pin4,13),
    pin5(R.drawable.pin5,14),
    pin6(R.drawable.pin6,15),
    pin7(R.drawable.pin7,16),
    pin8(R.drawable.pin8,17),
    pin9(R.drawable.pin9,18),
    sou1(R.drawable.sou1,19),
    sou2(R.drawable.sou2,20),
    sou3(R.drawable.sou3,21),
    sou4(R.drawable.sou4,22),
    sou5(R.drawable.sou5,23),
    sou6(R.drawable.sou6,24),
    sou7(R.drawable.sou7,25),
    sou8(R.drawable.sou8,26),
    sou9(R.drawable.sou9,27),
    ji1(R.drawable.ji1_ton,28),
    ji2(R.drawable.ji2_nan,29),
    ji3(R.drawable.ji3_sha,30),
    ji4(R.drawable.ji4_pei,31),
    ji5(R.drawable.ji5_haku,32),
    ji6(R.drawable.ji6_hatsu,33),
    ji7(R.drawable.ji7_chun,34),
    man5_aka(R.drawable.man_aka5, 105),
    pin5_aka(R.drawable.pin_aka5, 115),
    sou5_aka(R.drawable.sou_aka5, 125);

    private final int img;
    private final int num;

    private HaiEnum(int img, int num) {
        this.img = img;
        this.num = num;
    }

    public static int getImg(int num, boolean isRed) {
        return HaiEnum.values()[num - 1].img(isRed);
    }


    public int img() { return img; }
    public int img(boolean isRed) {
        if (isRed) {
            if (man5.equals(this)) {
                return man5_aka.img;
            } else if (pin5.equals(this)) {
                return pin5_aka.img;
            } else if (sou5.equals(this)){
                return sou5_aka.img;
            }
            Log.e("HaiEnum", "合致する赤の種別はありません。" + this);
            throw new RuntimeException("not mutch img");
        } else {
            return img;
        }
    }

    public int Number() { return num; }
}
