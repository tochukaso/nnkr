package co.jp.rough.hai;

public class Hai implements Comparable<Hai> {

    public Hai(int num, boolean flg) {
        this.haiNum = num;
        this.isRed = flg;
    }

    public Hai(int haiType, int haiValue, boolean isRed) {
        this.haiNum = getHaiNum(haiType, haiValue, isRed);
        this.isRed = isRed;
    }

    public Hai(int haiType, int haiValue) {
        new Hai(haiType, haiValue, false);
    }

    /**
     * Enum用牌種別
     * 牌をユニークに特定するためのキー情報
    */
    public int getHaiNum() {
        return haiNum;
    }

    public void setHaiNum(int haiNum) {
        this.haiNum = haiNum;
    }

    public boolean isRed() {
        return isRed;
    }

    public void setRed(boolean isRed) {
        this.isRed = isRed;
    }

    private int haiNum = 0;

    public String getHaiNm() {
        return HaiEnum.values()[this.haiNum].toString();
    }
    private boolean isRed = false;

    @Override
    public int compareTo(Hai another) {
        Hai compHai = (Hai) another;
        if (this.haiNum > compHai.haiNum) {
            return 1;
        } else if (this.haiNum == compHai.haiNum) {
            if (this.isRed()) {
                return 1;
            } else if (compHai.isRed()) {
                return -1;
            }
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * <pre>
     * 牌の種別を返却します。
     * 0 : マンズ
     * 1 : ピンズ
     * 2 : ソーズ
     * 3 : 字牌
     * </pre>
     * @int 牌種別
     */
    public int getHaiType() {
        if ((1 <= haiNum && haiNum <=9) || haiNum == 105) {
            return 0;
        } else if ((10 <= haiNum && haiNum <= 18) || haiNum == 115) {
            return 1;
        } else if ((19 <= haiNum && haiNum <= 27) || haiNum == 125) {
            return 2;
        } else if (28 <= haiNum && haiNum <= 34) {
            return 3;
        } else {
            throw new RuntimeException("undefined haiNum [" + haiNum + "]");
        }
    }

    /**
     * <pre>
     * 牌の実質的な数値
     * あがりの判定やソートなど実質的な数値はこちらを使用する。
     * 数牌(マンズ、ピンズ、ソーズ)の場合、1~9
     * 字牌の場合、以下となる
     * 1 : 東
     * 2 : 南
     * 3 : 西
     * 4 : 北
     * 5 : 白
     * 6 : 發
     * 7 : 中
     * </pre>
     * @return
     */
    public int getHaiValue() {
        if ((1 <= haiNum && haiNum <=9)) {
            return haiNum;
        } else if ((10 <= haiNum && haiNum <= 18)) {
            return haiNum - 9;
        } else if ((19 <= haiNum && haiNum <= 27) || haiNum == 125) {
            return haiNum - 18;
        } else if (28 <= haiNum && haiNum <= 34) {
            return haiNum - 27;
        } else if (haiNum == 105 || haiNum == 115 || haiNum == 125) {
            return 5;
        } else {
            throw new RuntimeException("undefined haiNum [" + haiNum + "]");
        }
    }

    private int getHaiNum(int haiType, int haiValue, boolean isRed) {
        if (haiType == 0) {
            if (isRed) {
                return 105;
            }
            return haiValue;
        } else if (haiType == 1) {
            if (isRed) {
                return 115;
            }
            return haiValue + 9;
        } else if (haiType == 2) {
            if (isRed) {
                return 125;
            }
            return haiValue + 18;
        } else if (haiType == 3) {
            return haiValue + 27;
        } else {
            throw new RuntimeException("undefined haiType [" + haiType + "]");
        }
        
    }
    
}
