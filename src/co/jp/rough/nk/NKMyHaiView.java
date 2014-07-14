package co.jp.rough.nk;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import co.jp.rough.db.NK_MJ_DTL_TABLE;
import co.jp.rough.hai.Hai;
import co.jp.rough.hai.HaiEnum;
import co.jp.rough.hai.HaiManager;

public class NKMyHaiView extends View {

    private Canvas canvas ;
    private int w = 0;
    private int d = 0;
    private Paint imgPaint;

    private NK_MJ_DTL_TABLE data = null;
    
    
    public NKMyHaiView(Context context) {
        super(context);
        imgPaint = new Paint();
    }

    public void setPosw(int w) {
        this.w = w;
    }

    public void setPosh(int h) {
        this.d = h;
    }

    public void setData(NK_MJ_DTL_TABLE data) {
        this.data = data;
    }
    
    @Override
    protected void onDraw(Canvas c) {
        this.canvas = c;
        super.onDraw(c);

        if (data != null) {
            List<Hai> haiList = convHaiList(data.getHaiArray());
            canvasHai(haiList);
        }
    }

    
    List<Hai> convHaiList(String[] haiArray) {
        List<Hai> resList = new ArrayList<Hai>();

        for (int i = 0; i < haiArray.length; i++) {
            Hai hai = new Hai(Integer.valueOf(haiArray[i]), false);
            resList.add(hai);
        }
        return resList;
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
