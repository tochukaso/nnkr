package co.jp.rough.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import co.jp.rough.db.SP_MJ_DTL_TABLE;
import co.jp.rough.hai.Hai;
import co.jp.rough.tehai.Tehai;

public class CommonFunc {

    private static final String LINE_SEPARATOR = "\n";
    
    public static String getLineSeparator() {
        return LINE_SEPARATOR;
    }
    
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        Date date = new Date();

        return sdf.format(date);
    }

    public static SP_MJ_DTL_TABLE convTehaiToSPMJ(Tehai tehai) {
        SP_MJ_DTL_TABLE spDtlTable = new SP_MJ_DTL_TABLE();

        List<Hai> list = tehai.getTehai();

        spDtlTable.setHai1(list.get(0).getHaiNm());
        spDtlTable.setHai2(list.get(1).getHaiNm());
        spDtlTable.setHai3(list.get(2).getHaiNm());
        spDtlTable.setHai4(list.get(3).getHaiNm());
        spDtlTable.setHai5(list.get(4).getHaiNm());
        spDtlTable.setHai6(list.get(5).getHaiNm());
        spDtlTable.setHai7(list.get(6).getHaiNm());
        spDtlTable.setHai8(list.get(7).getHaiNm());
        spDtlTable.setHai9(list.get(8).getHaiNm());
        spDtlTable.setHai10(list.get(9).getHaiNm());
        spDtlTable.setHai11(list.get(10).getHaiNm());
        spDtlTable.setHai12(list.get(11).getHaiNm());
        spDtlTable.setHai13(list.get(12).getHaiNm());

        return spDtlTable;
    }

    public static boolean isEmpty(String arg) {
        if (arg == null || arg.length() == 0) return true;
        return false;
    }

    public static boolean isEmpty(List arg) {
        if (arg == null || arg.size() == 0) return true;
        return false;
    }

    public static boolean isEmptyOrZERO(Long arg) {
        if (arg == null || arg == 0) return true;
        return false;
    }

    public static int parseInt(String arg) {
        if (arg == null) return 0;
        try {
            return Integer.parseInt(arg);
        } catch(NumberFormatException nf) {
            return 0;
        }
        
    }
    
    /**
     * 
     * @param wind(東,南,西,北)のいずれかの文字列を指定すること
     * @return
     */
    public static final int getWindIndex(String wind) {
        if ("東".equals(wind)) {
            return 0;
        } else if ("南".equals(wind)) {
            return 1;
        } else if ("西".equals(wind)) {
            return 2;
        } else if ("北".equals(wind)) {
            return 3;
        }
        return -1;
    }

    
}
