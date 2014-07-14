package co.jp.rough.nk;

import java.io.BufferedInputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import co.jp.rough.common.CommonFunc;
import co.jp.rough.db.NK_MJ_ANSWER;
import co.jp.rough.db.NK_MJ_DTL_TABLE;
import co.jp.rough.db.NK_MJ_SUTE_DTL_TABLE;
import co.jp.rough.db.NK_MJ_TABLE;
import co.jp.rough.db.spmj.NkmjDao;

public class QuestionGetHttpRequest 
                            extends AsyncTask<Uri.Builder, Void, String> {

    private static final int LINE_CNT = 7;
    
    public QuestionGetHttpRequest(Activity activity) {
        super();
    }
    
    @Override
    protected String doInBackground(Uri.Builder... builder) {
        return getWords();
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("getD", "data Get Call Back [" + result + "]");
        try {
            saveData(result);
        } catch (Exception e) {
            Log.w("getD", e);
        }
    }

    public void saveData (String data) {
        
        String[] lines = data.split("\r\n");
        int lineIndex = 0;
        
        if (lines == null || lines.length == 0 || lines[0] == null || "".equals(lines[0])) {
            return;
        }
        
        int N = getIndex(lines[lineIndex++]);
        NkmjDao dao = new NkmjDao();
        
        Long index = new NkmjDao().selectNKMJMaxSeq();
        
        for (int i = 0; i < N; i++) {
            if (index.compareTo(Long.parseLong(lines[lineIndex].split(",")[0])) < 0) {
                NK_MJ_TABLE add = makeNkMjData(Arrays.copyOfRange(lines,lineIndex,lineIndex + LINE_CNT));
                try {
                    dao.save(add);
                } catch (Exception e) {
                    Log.w("getD", e);
                }
            }
            lineIndex += LINE_CNT;
        }
    }
    
    public NK_MJ_TABLE makeNkMjData(String[] args) {
        if (args == null || args.length != LINE_CNT) {
            Log.w("getD", "illegalData is contain");
            return null;
        }
        
        int lineIndex = 0;
        
        NK_MJ_TABLE base = convCSVToNkMj(args[lineIndex++]);
        
        {
            base.setDtlData(convCSVToNkMjDtl(args[lineIndex++]));
        }

        {
            base.setAnswerData(convCSVToAnswer(args[lineIndex++]));
        }

        
        {
            List<NK_MJ_SUTE_DTL_TABLE> dtlList = new ArrayList<NK_MJ_SUTE_DTL_TABLE>();
            for (int i = 0; i < 4; i++) {
                dtlList.add(convCSVToNkMjSuteDtl(args[lineIndex++]));
            }
            base.setSuteList(dtlList);
        }
        
        return base;
    }
    
    public NK_MJ_TABLE convCSVToNkMj(String arg) {
        NK_MJ_TABLE base = new NK_MJ_TABLE();
        String[] elm = arg.split(",");
        int length = elm.length;
        int i = 0;
        base.setSeq(Long.parseLong(elm[i++]));
        base.setRound(elm[i++]);
        base.setIndex(elm[i++]);
        base.setScore(elm[i++]);
        base.setWind(elm[i++]);
        base.setRegDm(elm[i++]);
        base.setRegId(elm[i++]);
        base.setDora(elm[i++]);
        if (length > i)
        base.setKanDora(elm[i++]);
        return base;
    }
    
    public NK_MJ_DTL_TABLE convCSVToNkMjDtl(String arg) {
        NK_MJ_DTL_TABLE base = new NK_MJ_DTL_TABLE();
        String[] elm = arg.split(",");
        int length = elm.length;
        int i = 0;
        base.setNkSeq(Long.parseLong(elm[i++]));
        String[] haiArray = new String[13];
        for (int index = 0; index < 13; index++) {
            haiArray[index] = elm[i++];
        }
        base.setHaiArray(haiArray);
        if (length > i) base.setHaiTumo(elm[i++]);
        if (length > i) base.setHaiSute(elm[i++]);
        return base;
    }

    public NK_MJ_ANSWER convCSVToAnswer(String arg) {
        NK_MJ_ANSWER base = new NK_MJ_ANSWER();
        String[] elm = arg.split(",");
        int length = elm.length;
        int i = 0;
        base.setSeq(Long.parseLong(elm[i++]));

        List<String> pointList = new ArrayList<String>();
        base.setPointList(pointList);
        
        for (int index = 0; index < 14; index++) {
            pointList.add(elm[i++]);
        }
        if (length > i) base.setComment1(chengeLF(elm[i++]));
        if (length > i) base.setComment2(chengeLF(elm[i++]));
        if (length > i) base.setComment3(chengeLF(elm[i++]));

        return base;
    }

    public String addLF(String str) {
        return str.replaceAll("。", "。" + CommonFunc.getLineSeparator());
    }
    public String chengeLF(String str) {
        String res = addLF(str);
        
        return res.replaceAll(";", CommonFunc.getLineSeparator());
    }

    
    public NK_MJ_SUTE_DTL_TABLE convCSVToNkMjSuteDtl(String arg) {
        NK_MJ_SUTE_DTL_TABLE base = new NK_MJ_SUTE_DTL_TABLE();
        String[] elm = arg.split(",");
        int length = elm.length;

        int i = 0;
        base.setNkSeq(Long.parseLong(elm[i++]));
        base.setDtlSeq(Long.parseLong(elm[i++]));
        base.setWind(elm[i++]);
        base.setScore(elm[i++]);
        base.setSuteCnt(Integer.parseInt(elm[i++]));
        String[] haiArray = new String[16];
        Arrays.fill(haiArray, "");
        for (int index = 0; index < base.getSuteCnt(); index++) {
            haiArray[index] = elm[i++];
        }
        base.setSutehaiArray(haiArray);
        return base;
    }

    static final String getWords()  {
        BufferedInputStream bi = null;
        try {
            URL url = new URL(NnKrAction.URL);
    
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(10000);
            con.setReadTimeout(10000);
            con.connect();
            bi = new BufferedInputStream(con.getInputStream());
            StringBuilder sb = new StringBuilder();
            String s = null;
            
            StringWriter writer = new StringWriter();
            IOUtils.copy(bi, writer, "UTF-8");
            /**
            do {
                byte[] b = new byte[1024];
                bi.read(b);
                s = new String(b, "UTF-8");
                sb.append(s);
            } while (!CommonFunc.isEmpty(s));
            **/
            con.disconnect();
//            return sb.toString();
            return writer.toString();

        } catch (Exception e) {
            Log.w("getD", e);
            return "";
        } finally {
            try {
                bi.close();
            } catch (Exception e) {
                Log.w("getD", e);
            }
        }
    }
    
    private int getIndex(String str) {
        return CommonFunc.parseInt(str.replaceAll(",", ""));
    }
    
}
