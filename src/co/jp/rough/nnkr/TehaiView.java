package co.jp.rough.nnkr;

import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import co.jp.rough.common.ConfigManager;
import co.jp.rough.hai.Hai;
import co.jp.rough.hai.HaiEnum;
import co.jp.rough.hai.HaiManager;
import co.jp.rough.logic.SimpleMajan;
import co.jp.rough.tehai.Tehai;

/**
 * 手牌のビューアーです。
 * <pre>
 *
 * </pre>
 * @author y-ohmori
 *
 */
public class TehaiView extends View {

    SimpleMajan mj;

    private Tehai tehai ;

    private Long spNo ;
    private Long seq ;
    private Hai tumo ;
    private Hai sute ;
    private Hai dora ;

    private List<Hai> suteList ;

    private Canvas canvas ;

    private Paint paint;

    public Long getSpNo() {
        return spNo;
    }

    public void setSpNo(Long spNo) {
        this.spNo = spNo;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public Hai getTumo() {
        return tumo;
    }

    public void setTumo(Hai tumo) {
        this.tumo = tumo;
    }

    public Hai getSute() {
        return sute;
    }

    public void setSute(Hai sute) {
        this.sute = sute;
    }

    public Hai getDora() {
        return dora;
    }

    public void setDora(Hai dora) {
        this.dora = dora;
    }
    public TehaiView(Context context, SimpleMajan mj) {
        super(context);
        this.mj = mj;
    }

    public void setTehai(Tehai tehai) {
        this.tehai = tehai;
    }

    public Tehai getTehai() {
        return this.tehai;
    }
    public List<Hai> getSuteList() {
        return suteList;
    }

    public void setSuteList(List<Hai> suteList) {
        this.suteList = suteList;
    }


    @Override
    protected void onDraw(Canvas c) {
        this.canvas = c;
        super.onDraw(c);
        canvasTeHai();

        tumo = HaiManager.drawHai();
        tehai.addHai(tumo);

        this.seq = this.seq + 1;

        canvasDrawHai();

        canvasDora();

        canvasInfo();

        canvasSuteList();

        mj.saveTehai();
    }

    private void canvasTeHai() {
        Resources res = this.getContext().getResources();
        int w = 0;
        int h = 0;
        for (Hai hai : tehai.getTehai()) {
            int imgNum = HaiEnum.getImg(hai.getHaiNum(), hai.isRed());
//            Log.d("tehaiView", String.valueOf(imgNum));
            Bitmap img = BitmapFactory.decodeResource(res, imgNum);
            canvas.drawBitmap(img,w,h,paint);
//            Log.d("tehaiView", "view " + hai.getHaiNum());
            w = w + HaiManager.HAI_WIDTH;
        }
    }

    private void canvasDrawHai() {
        Resources res = this.getContext().getResources();
        int imgNum = HaiEnum.getImg(tumo.getHaiNum(), tumo.isRed());
        Bitmap img = BitmapFactory.decodeResource(res, imgNum);
        canvas.drawBitmap(img,0,HaiManager.HAI_HEIGHT,paint);
    }

    private void canvasDora() {
        Resources res = this.getContext().getResources();
        int imgNum = HaiEnum.getImg(dora.getHaiNum(), dora.isRed());
        Bitmap img = BitmapFactory.decodeResource(res, imgNum);
        canvas.drawBitmap(img,HaiManager.HAI_WIDTH * 2,HaiManager.HAI_HEIGHT,paint);
    }

    private void canvasSuteList() {
        Resources res = this.getContext().getResources();
        int w = 0;
        int h = (int)((HaiManager.HAI_HEIGHT + 5) * 2.05) + HaiManager.HAI_HEIGHT;
        for (Hai hai : suteList) {
            if (w >= HaiManager.HAI_WIDTH * 13) {
                w = 0;
                h = h + HaiManager.HAI_HEIGHT + 5;
            }
            int imgNum = HaiEnum.getImg(hai.getHaiNum(), hai.isRed());
            Bitmap img = BitmapFactory.decodeResource(res, imgNum);
            canvas.drawBitmap(img,w, h ,paint);
            w = w + HaiManager.HAI_WIDTH;
        }
    }

    private void canvasInfo() {
        Paint p = new Paint();
        p.setColor(Color.BLACK);
//        p.setTextSize(16);
        p.setTextSize(ConfigManager.getWidth() / 18);
        p.setTypeface(Typeface.DEFAULT_BOLD);
//        canvas.drawText("闘牌ID : " + avoidNull(spNo) + "  順目 : " + avoidNull(seq) + "  シャン点数 : " + tehai.getShantenNumber(),0,HaiManager.HAI_HEIGHT * 2 + 20,p);
        canvas.drawText( avoidNull(seq) + " 順目 : " + tehai.getShantenNumber() + " シャン点" ,20 ,(int)(HaiManager.HAI_HEIGHT * 2.4),p);
    }
    
    private String avoidNull(Long l) {
        if (l == null) {
            return "1";
        }
        return String.valueOf(l);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (MotionEvent.ACTION_UP != event.getAction()) {
            return true;
        }
        float y = event.getY();
        float x = event.getX();

        if (Tehai.MAX_CNT - 1 > tehai.getTehai().size()) {
            return false;
        }

        int removeHainum;

        if (y > HaiManager.HAI_HEIGHT * 2) {
            return true;
        } else if (y > HaiManager.HAI_HEIGHT) {
            if (x > HaiManager.HAI_WIDTH) {
                return false;
            }
            removeHainum = tehai.getTehai().size() - 1;
        } else {
            if (x > 14 * HaiManager.HAI_WIDTH) {
                return false;
            } else if (x > 13 * HaiManager.HAI_WIDTH) {
                removeHainum = tehai.getTehai().size() - 2;
            } else {
                removeHainum = (int) Math.floor(x / HaiManager.HAI_WIDTH);
            }
        }

        // 牌を捨てる
        sute = tehai.removeHai(removeHainum);

        suteList.add(sute);

        /**
         * シャン点数の計算
         *
         */
        
        
        // 再描画の指示
        invalidate();
        return true;
    }

}
