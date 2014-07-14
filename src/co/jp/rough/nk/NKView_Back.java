package co.jp.rough.nk;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import co.jp.rough.common.ConfigManager;
import co.jp.rough.db.NK_MJ_TABLE;
import co.jp.rough.hai.Hai;
import co.jp.rough.hai.HaiEnum;
import co.jp.rough.hai.HaiManager;
import co.jp.rough.nnkr.R;
import co.jp.rough.tehai.Tehai;

public class NKView_Back extends FrameLayout {

    private Canvas canvas ;
    private int w = 0;
    private int d = 0;
    private Paint bgPaint;
    private Paint txetPaint;
    private Paint imgPaint;

    private NK_MJ_TABLE data = null;
    private Bitmap backGround = null;
    
    LinearLayout mainLayout = null;
    LinearLayout subLayout = null;

    NKSuteHaiView suteView1 = null;
    NKSuteHaiView suteView2 = null;
    NKSuteHaiView suteView3 = null;
    NKSuteHaiView suteView4 = null;

    NKMyHaiView userHaiView = null;

    public NKView_Back(Context context) {
        super(context);
        bgPaint = new Paint();
        bgPaint.setARGB(255,100,100,255);
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStrokeWidth(5);
        
        txetPaint = new Paint();
        txetPaint.setColor(Color.BLACK);
        txetPaint.setTextSize(12);
        txetPaint.setAntiAlias(true);

        imgPaint = new Paint();
        backGround = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.table);

        mainLayout = new LinearLayout(context);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        this.addView(mainLayout);
        
        suteView1 = new NKSuteHaiView(context);

        suteView1.setPosh(0);
        suteView1.setPosw(0);
        suteView1.setSizeh(ConfigManager.getHeight() / 5);
        suteView1.setSizew(ConfigManager.getWidth());
        suteView1.layout(0, 0, ConfigManager.getHeight() / 5, ConfigManager.getWidth());
        
        
        suteView1.setLayoutParams(new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT));
        
        mainLayout.addView(suteView1);

        subLayout = new LinearLayout(context);
        subLayout.setOrientation(LinearLayout.HORIZONTAL);
        subLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        subLayout.setGravity(Gravity.CENTER);

        subLayout.layout(ConfigManager.getHeight() / 5, 0, ConfigManager.getHeight() / 4, ConfigManager.getWidth());

        suteView2 = new NKSuteHaiView(context);
        suteView2.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        subLayout.addView(suteView2);
        
        suteView3 = new NKSuteHaiView(context);
        suteView3.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));


        subLayout.addView(suteView3);

        mainLayout.addView(subLayout);

        suteView4 = new NKSuteHaiView(context);

        suteView4.setPosh(ConfigManager.getHeight() / 2);
        suteView4.setPosw(ConfigManager.getWidth() / 2);
        suteView4.setSizeh(ConfigManager.getHeight() / 5);
        suteView4.setSizew(ConfigManager.getWidth());

        suteView4.layout(ConfigManager.getHeight() / 3, 0, ConfigManager.getHeight() / 5, ConfigManager.getWidth());


        suteView4.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        mainLayout.addView(suteView4);

        userHaiView = new NKMyHaiView(context);
        userHaiView.layout(ConfigManager.getHeight() / 2, 0, ConfigManager.getHeight() / 4, ConfigManager.getWidth());
        
        userHaiView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        mainLayout.addView(userHaiView);
    }

    public void setNKData(NK_MJ_TABLE data) {
        this.data = data;
        
        suteView1.setData(data.getSuteList().get(0));
        suteView2.setData(data.getSuteList().get(1));
        suteView3.setData(data.getSuteList().get(2));
        suteView4.setData(data.getSuteList().get(3));
        suteView4.isDebug = true;
        
        userHaiView.setData(data.getDtlData());
    }
    
    @Override
    protected void onDraw(Canvas c) {
        this.canvas = c;
        super.onDraw(c);
        canvas.drawColor(Color.GREEN);
/**
        if (data != null) {
            // set backGround Color

            canvas.drawRect(ConfigManager.getHeight() / 4,ConfigManager.getWidth() / 3,
                (int) (ConfigManager.getHeight() / 3), (int) (ConfigManager.getWidth() / 2),bgPaint);

            canvas.drawText(data.getRound(),240,240,txetPaint);
            canvas.drawText(data.getIndex(),260,240,txetPaint);
    
            List<Hai> haiList = convHaiList(data.getDtlList().get(0).getHaiArray());
            canvasHai(haiList);
        }
**/
    }

    
    List<Hai> convHaiList(String[] haiArray) {
        List<Hai> resList = new ArrayList<Hai>();

        for (int i = 0; i < haiArray.length; i++) {
            Hai hai = new Hai(Integer.valueOf(haiArray[i]), false);
            resList.add(hai);
        }
        return resList;
    }
    
    /**
     * 
     * @return
     */
    private List<Hai> makeTehai() {
        int reftHaiCnt = HaiManager.reftHaiCnt();
        List<Hai> canvasList;
        if (reftHaiCnt > Tehai.MAX_CNT) {
            Tehai tehai = new Tehai();
            tehai.setHaipai();
            canvasList = tehai.getTehai();
        } else {
            canvasList = new ArrayList<Hai>();
            canvasList.add(HaiManager.drawHai());
            canvasList.add(HaiManager.drawHai());
        }
        return canvasList;
    }

    private void canvasHai(List<Hai> list ) {
        Resources res = this.getContext().getResources();

        for (Hai hai : list) {

            int imgNum = HaiEnum.getImg(hai.getHaiNum(), hai.isRed());
            Bitmap img = BitmapFactory.decodeResource(res, imgNum);
            canvas.drawBitmap(img,w,d,imgPaint);
            w = w + HaiManager.HAI_WIDTH;
        }
        w = 0;
        d = d + HaiManager.HAI_HEIGHT;
    }

}
