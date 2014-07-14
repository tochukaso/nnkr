package co.jp.rough.nk;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import co.jp.rough.common.CommonFunc;
import co.jp.rough.common.ConfigManager;
import co.jp.rough.common.Lank;
import co.jp.rough.common.UserConfigManager;
import co.jp.rough.db.NK_MJ_SUTE_DTL_TABLE;
import co.jp.rough.db.NK_MJ_TABLE;
import co.jp.rough.db.NK_MJ_USER;
import co.jp.rough.db.spmj.NkmjAnswerDao;
import co.jp.rough.db.spmj.NkmjDao;
import co.jp.rough.db.spmj.NkmjUserDao;
import co.jp.rough.hai.Hai;
import co.jp.rough.hai.HaiConfigManager;
import co.jp.rough.hai.HaiManager;
import co.jp.rough.nnkr.NnkrActivity;
import co.jp.rough.nnkr.NnkrAnswerActivity;
import co.jp.rough.nnkr.R;

public class NKView extends View{

    private NnKrAction action;
    
    private Canvas canvas ;

    public static final double BASE_WIDTH_NUM = 0.5;
    public static final double BASE_HEIGHT_NUM = 0.35;

    public static final double MAIN_CIRCLE_WIDTH_NUM = 0.21;
    public static final double MAIN_CIRCLE_HEIGHT_NUM = 0.15;

    public static final double OWN_HAI_WIDTH_NUM = 0.01;
    public static final double OWN_HAI_HEIGHT_NUM = 0.75;

    public static final double VIEW_ANSWER_POSITION_WIDTH_NUM = 0.03;
    public static final double NEXT_QUESTION_POSTION_WIDTH_NUM = 0.75;

    public static final double ALREADY_HEIGHT_NUM = 0.68;
    
    public static int VIEW_ANSWER_IMAGE_WIDTH = ConfigManager.getWidth() / 5;
    public static int NEXT_QUESTION_IMAGE_WIDTH = ConfigManager.getWidth() / 5;
    public static int ALREADY_BUTTON_IMAGE_HEIGHT = ConfigManager.getHeight() / 25;

    private int baseWidth = 0;
    private int baseHeight = 0;

    private int baseCircleWidth = 0;
    private int baseCircleHeight = 0;

    private int ownHaiWidth = 0;
    private int ownHaiHeight = 0;

    private int viewAnswerPositionWidth = 0;
    private int nextQuestionPositionWidth = 0;

    private int alreadyHeight = 0;
    private int alreadyImageHeight = 0;
    
    private Paint bgPaint;
    private Paint textPaint;
    private Paint mainTextPaint;
    private Paint imgPaint;

    private NK_MJ_TABLE data = null;

    private boolean isAlreadyAnswer = false;

    private Bitmap alreadyAnswer = null;
    private Bitmap lastQuestion = null;
    private Bitmap viewAnswer = null;
    private Bitmap nextQuestion = null;

    private boolean isLastSeq = false;
    public NKView(Context context) {
        super(context);
        bgPaint = new Paint();
        bgPaint.setARGB(255,100,100,255);
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStrokeWidth(5);
        
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(ConfigManager.getWidth() / 22);
        textPaint.setAntiAlias(true);

        mainTextPaint = new Paint();
        mainTextPaint.setColor(Color.BLACK);
        mainTextPaint.setTextSize(ConfigManager.getWidth() / 20);
        mainTextPaint.setAntiAlias(true);

        
        imgPaint = new Paint();
        
        baseWidth = (int) (ConfigManager.getWidth() * BASE_WIDTH_NUM);
        baseHeight = (int) (ConfigManager.getHeight() * BASE_HEIGHT_NUM);

        baseCircleWidth = (int) (ConfigManager.getWidth() * MAIN_CIRCLE_WIDTH_NUM);
        baseCircleHeight = (int) (ConfigManager.getHeight() * MAIN_CIRCLE_HEIGHT_NUM);

        ownHaiWidth = (int) (ConfigManager.getWidth() * OWN_HAI_WIDTH_NUM);
        ownHaiHeight = (int) (ConfigManager.getHeight() * OWN_HAI_HEIGHT_NUM);

        viewAnswerPositionWidth = (int) (ConfigManager.getWidth() * VIEW_ANSWER_POSITION_WIDTH_NUM);
        nextQuestionPositionWidth = (int) (ConfigManager.getWidth() * NEXT_QUESTION_POSTION_WIDTH_NUM);

        alreadyHeight = (int) (ConfigManager.getHeight() * ALREADY_HEIGHT_NUM);
//        alreadyImageHeight = (int) (ConfigManager.getHeight() * ALREADY_BUTTON_IMAGE_HEIGHT);
        alreadyImageHeight = ALREADY_BUTTON_IMAGE_HEIGHT;

        alreadyAnswer = BitmapFactory.decodeResource(getResources(), R.drawable.already_answer);
        lastQuestion = BitmapFactory.decodeResource(getResources(), R.drawable.last_question);
        viewAnswer = BitmapFactory.decodeResource(getResources(), R.drawable.view_answer);
        nextQuestion = BitmapFactory.decodeResource(getResources(), R.drawable.next_question);
        
    }

    public void setNKData(NK_MJ_TABLE data) {
        this.data = data;
        
        this.isAlreadyAnswer = !CommonFunc.isEmpty(data.getAnswerData().getUserAnswer());
        this.isLastSeq = new NkmjDao().selectNKMJMaxSeq().compareTo(this.data.getSeq()) <= 0;
    }

    public void setNKAction(NnKrAction action) {
        this.action = action;
    }

    @Override
    protected void onDraw(Canvas c) {
        this.canvas = c;
        super.onDraw(c);
        if (data != null) {
            // set backGround Color
            canvas.drawColor(Color.GREEN);

            canvas.drawRect(baseWidth - baseCircleWidth,
                            baseHeight - baseCircleHeight,
                            baseWidth + baseCircleWidth,
                            baseHeight + baseCircleHeight,
                            bgPaint);

            canvas.drawText(data.getSeq() + "問目",
                    baseWidth - (int) (baseCircleWidth / 2.2d),
                    baseHeight - (int) (baseCircleHeight / 2.5d),
                    textPaint);

            canvas.drawText(data.getRound() + "局",
                            baseWidth - (int) (baseCircleWidth / 2.2d),
                            baseHeight - (int) (baseCircleHeight / 9d),
                            mainTextPaint);

            canvas.drawText("ドラ",
                    baseWidth - (int) (baseCircleWidth / 2.2d),
                    baseHeight + (int) (baseCircleHeight / 7d),
                    mainTextPaint);

            Bitmap dora = HaiConfigManager.getHaiImage(Integer.parseInt(data.getDora()));
            
            canvas.drawBitmap(dora,
                             baseWidth - (int) (baseCircleWidth / 2d),
                             baseHeight  + (int) (baseCircleHeight / 6d),
                             imgPaint);

            canvas.save();
            
            int index = CommonFunc.getWindIndex(data.getWind());
            
            for (int i = 0; i < 4; i++) {
                if (index > 3) {
                    index = 0;
                }
                NK_MJ_SUTE_DTL_TABLE suteDtl = data.getSuteList().get(index++);
                
                canvas.drawText(suteDtl.getWind() + " " + suteDtl.getScore(),baseWidth - baseCircleWidth / 2,baseHeight + (int) (baseCircleHeight / 1.2d),textPaint);

                List<Hai> haiList = convHaiList(suteDtl.getSutehaiArray());
                canvasSuteHai(haiList, baseWidth - baseCircleWidth, baseHeight + (int) (baseCircleHeight * 1.05d));
                canvas.rotate(-90, baseWidth, baseHeight);
            }
            canvas.restore();

            int widthPos = canvasHaiList(data.getDtlData().getHaiArray(), ownHaiWidth, ownHaiHeight);
            
            canvasHai(data.getDtlData().getHaiTumo(), widthPos + (int) (HaiManager.HAI_WIDTH * 0.1d), ownHaiHeight);

            if (isAlreadyAnswer) {
                canvas.drawBitmap(viewAnswer, viewAnswerPositionWidth, alreadyHeight, imgPaint);
                if (!isLastSeq) {
                    canvas.drawBitmap(nextQuestion, nextQuestionPositionWidth, alreadyHeight, imgPaint);
                    canvas.drawBitmap(alreadyAnswer, baseWidth - (int) (baseCircleWidth / 1.6) , alreadyHeight, imgPaint);
                } else {
                    canvas.drawBitmap(lastQuestion, baseWidth - (int) (baseCircleWidth / 1.6), alreadyHeight, imgPaint);
                }
            }
            

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

    private int canvasHaiList(String[] haiArray, int width, int height) {
        for (int i = 0; i < haiArray.length; i++) {
            Bitmap img = HaiConfigManager.getHaiImage(haiArray[i]);
            canvas.drawBitmap(img,width,height,imgPaint);
            width += HaiManager.HAI_WIDTH;
        }
        return width;
    }

    private int canvasHai(String hai, int width, int height) {
        Bitmap img = HaiConfigManager.getHaiImage(hai);
        canvas.drawBitmap(img,width,height,imgPaint);
        width += HaiManager.HAI_WIDTH;
        return width;
    }

    
    private void canvasSuteHai(List<Hai> list ,int width, int height) {
        for (Hai hai : list) {
            Bitmap img = HaiConfigManager.getHaiImage(hai.getHaiNum());
            canvas.drawBitmap(img,width,height,imgPaint);
            width += HaiManager.HAI_WIDTH;
            if (width >= baseWidth + baseCircleWidth) {
                width = baseWidth - baseCircleWidth;
                height += (int) (HaiManager.HAI_HEIGHT * 1.05);
            }
        }
    }

    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        
        if (MotionEvent.ACTION_UP != event.getAction()) {
            return true;
        }
        float y = event.getY();
        float x = event.getX();

        if (isAlreadyAnswer) {
            return actionIsAlreadyAnswer(x, y);
        } else {
            return actionIsNotAlreadyAnswer(x, y);
        }
    }

    private boolean actionIsAlreadyAnswer(float x, float y) {
        
        if (y < alreadyHeight || y > alreadyHeight + alreadyImageHeight || x == 0) {
            return true;
        }
        
        if (viewAnswerPositionWidth <= x &&
                x <= viewAnswerPositionWidth + VIEW_ANSWER_IMAGE_WIDTH) {
            Intent answerIntent = new Intent(getContext(), NnkrAnswerActivity.class);
            NnkrActivity activity = this.action.getActivity();
            activity.startActivity(answerIntent);
        }

        if (nextQuestionPositionWidth <= x &&
                x <= nextQuestionPositionWidth + NEXT_QUESTION_IMAGE_WIDTH  && !isLastSeq) {
            UserConfigManager.setSessionLastAnsSeq(data.getSeq() + 1);
            this.setNKData(new NkmjDao().selectNKMJAll(data.getSeq() + 1));

            //再描画を促す。
            invalidate();
        }

        return true;
    }

    private boolean actionIsNotAlreadyAnswer(float x, float y) {
        
        int answerHaiIndex;

        Log.d("nkView", y + " : " + x + " : " + baseCircleHeight );
        if (y < ownHaiHeight || x == 0) {
            return true;
        }
        answerHaiIndex = (int) Math.floor(x / HaiManager.HAI_WIDTH);
        Log.d("nkView", "ansIndex : " + answerHaiIndex );

        if (0 <= answerHaiIndex && answerHaiIndex <= 13) {

            new NkmjAnswerDao().updateUserAnswer(data.getSeq(), String.valueOf(answerHaiIndex));

            NK_MJ_USER userData = new NkmjUserDao().selectNKMJ();
            
            NK_MJ_USER updateData = new NK_MJ_USER();
            
            String addPoint = data.getAnswerData().getPointList().get(answerHaiIndex);
            int afterPoint = CommonFunc.parseInt(userData.getPoint()) + CommonFunc.parseInt(addPoint);
            updateData.setPoint(String.valueOf(afterPoint));
            updateData.setLank(Lank.getLank(afterPoint));
            updateData.setLastAnswerSeq(data.getSeq());
            // update user LastAnswer and AddPoint
            new NkmjUserDao().save(updateData);;

            Intent answerIntent = new Intent(getContext(), NnkrAnswerActivity.class);
            NnkrActivity activity = this.action.getActivity();
            activity.startActivity(answerIntent);
//            NKAnswerView ansView = new NKAnswerView(getContext());
//            ansView.setData(data.getAnswerData());
//            activity.setContentView(ansView);
        }

        return true;
    }

}
