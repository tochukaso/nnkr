package co.jp.rough.nnkr;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import co.jp.rough.hai.Hai;
import co.jp.rough.hai.HaiEnum;
import co.jp.rough.hai.HaiManager;
import co.jp.rough.tehai.Tehai;

public class HaipaiView extends View {

    private Canvas canvas ;
    private int w = 0;
    private int d = 0;
    private Paint paint;

    public HaipaiView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas c) {
        this.canvas = c;
        super.onDraw(c);
        for (int i = 0; i < 11; i++) {
            canvasHai(makeTehai());
        }
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
//            Log.d("tehaiView", String.valueOf(imgNum));
            Bitmap img = BitmapFactory.decodeResource(res, imgNum);
            canvas.drawBitmap(img,w,d,paint);
//            Log.d("tehaiView", "view " + hai.getHaiNum());
            w = w + HaiManager.HAI_WIDTH;
        }
        w = 0;
        d = d + HaiManager.HAI_HEIGHT;
    }

}
