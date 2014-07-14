package co.jp.rough.common;

public enum Lank {

    KYU_9(0, "9級"),
    KYU_8(10, "8級"),
    KYU_7(20, "7級"),
    KYU_6(30, "6級"),
    KYU_5(50, "5級"),
    KYU_4(70, "4級"),
    KYU_3(100, "3級"),
    KYU_2(130, "2級"),
    KYU_1(150, "1級"),
    DAN_SYO(200, "初段"),
    DAN_1(200, "1段"),
    DAN_2(300, "2段"),
    DAN_3(400, "3段"),
    DAN_4(500, "4段"),
    DAN_5(600, "5段"),
    DAN_6(700, "6段"),
    DAN_7(800, "7段"),
    DAN_8(900, "8段"),
    DAN_9(1000, "9段");
/**
    BRONZE(0, "ブロンズ"),
    SILVER(30, "シルバー"),
    GOLD(100, "ゴールド"),
    PLATINA(200, "プラチナ");
**/ 
    private final int point;
    private final String name;

    private Lank(int point, String name) {
        this.point = point;
        this.name = name;
    }

    public int Point() {
        return point;
    }

    public String Name() {
        return name;
    }
/**    
    public static String getLank(int point) {
        if (point < SILVER.point) {
            return BRONZE.name;
        } else if (point < GOLD.point) {
            return SILVER.name;
        } else if (point < PLATINA.point){
            return GOLD.name;
        } else {
            return PLATINA.name;
        }
    }
**/
    public static String getLank(int point) {
        if (point < KYU_8.point) {
            return KYU_9.name;
        } else if (point <  KYU_7.point) {
            return KYU_8.name;
        } else if (point <  KYU_6.point) {
            return KYU_7.name;
        } else if (point <  KYU_5.point) {
            return KYU_6.name;
        } else if (point <  KYU_4.point) {
            return KYU_5.name;
        } else if (point <  KYU_3.point) {
            return KYU_4.name;
        } else if (point <  KYU_2.point) {
            return KYU_3.name;
        } else if (point <  KYU_1.point) {
            return KYU_2.name;
        } else if (point <  DAN_SYO.point) {
            return KYU_1.name;
        } else if (point <  DAN_1.point) {
            return DAN_SYO.name;
        } else if (point <  DAN_2.point) {
            return DAN_1.name;
        } else if (point <  DAN_3.point) {
            return DAN_2.name;
        } else if (point <  DAN_4.point) {
            return DAN_3.name;
        } else if (point <  DAN_5.point) {
            return DAN_4.name;
        } else if (point <  DAN_6.point) {
            return DAN_5.name;
        } else if (point <  DAN_7.point) {
            return DAN_6.name;
        } else if (point <  DAN_8.point) {
            return DAN_7.name;
        } else if (point <  DAN_9.point) {
            return DAN_8.name;
        } else {
            return DAN_9.name;
        }
   
    }


}

