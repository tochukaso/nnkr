package co.jp.rough.nk;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import co.jp.rough.common.CommonFunc;
import co.jp.rough.common.ConfigManager;
import co.jp.rough.common.UserConfigManager;
import co.jp.rough.db.NK_MJ_TABLE;
import co.jp.rough.db.spmj.NkmjDao;
import co.jp.rough.hai.HaiConfigManager;
import co.jp.rough.hai.HaiManager;
import co.jp.rough.nnkr.NnkrActivity;
import co.jp.rough.nnkr.NnkrAnswerActivity;
import co.jp.rough.nnkr.R;

public class NKAnswerView extends View {

    private NnKrAnswerAction action;

    private Canvas canvas ;

    private Paint imgPaint;
    private Paint textPaint;

    private NK_MJ_TABLE data = null;
    
    private int ansPoint = 0;
    
    public static final double BASE_WIDTH_NUM = 0.05;
    public static final double BASE_HEIGHT_NUM = 0.1;

    public static final double OWN_HAI_WIDTH_NUM = 0.01;
    public static final double OWN_HAI_HEIGHT_NUM = 0.75;

    public static final double TEXT_LINE_WIDTH_NUM = 0.05;
    public static final double TEXT_LINE_HEIGHT_NUM = 0.05;

    public static final double VIEW_ANSWER_POSITION_WIDTH_NUM = 0.03;
    public static final double NEXT_QUESTION_POSTION_WIDTH_NUM = 0.75;

    public static final double BUTTON_HEIGHT_NUM = 0.68;

    public static int VIEW_QUESTION_IMAGE_WIDTH = ConfigManager.getWidth() / 5;
    public static int NEXT_QUESTION_IMAGE_WIDTH = ConfigManager.getWidth() / 5;
    public static int BUTTON_IMAGE_HEIGHT = ConfigManager.getHeight() / 25;

    private static int TEXT_SIZE = ConfigManager.getWidth() / 24;
    
    private int baseWidth = 0;
    private int baseHeight = 0;

    private int ownHaiWidth = 0;
    private int ownHaiHeight = 0;

    private int textLineWidth = 0;
    private int textLineHeight = 0;

    private int viewAnswerPositionWidth = 0;
    private int nextQuestionPositionWidth = 0;

    private int buttonHeight = 0;

    private Bitmap viewQuestion = null;
    private Bitmap nextQuestion = null;
    private Bitmap lastQuestion = null;
    
    private boolean isLastSeq = false;

    public NKAnswerView(Context context) {
        super(context);

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(TEXT_SIZE);
        textPaint.setAntiAlias(true);

        imgPaint = new Paint();

        baseWidth = (int) (ConfigManager.getWidth() * BASE_WIDTH_NUM);
        baseHeight = (int) (ConfigManager.getHeight() * BASE_HEIGHT_NUM);

        ownHaiWidth = (int) (ConfigManager.getWidth() * OWN_HAI_WIDTH_NUM);
        ownHaiHeight = (int) (ConfigManager.getHeight() * OWN_HAI_HEIGHT_NUM);

        textLineWidth = (int) (ConfigManager.getWidth() * TEXT_LINE_WIDTH_NUM);
        textLineHeight = (int) (ConfigManager.getHeight() * TEXT_LINE_HEIGHT_NUM);
        
        viewAnswerPositionWidth = (int) (ConfigManager.getWidth() * VIEW_ANSWER_POSITION_WIDTH_NUM);
        nextQuestionPositionWidth = (int) (ConfigManager.getWidth() * NEXT_QUESTION_POSTION_WIDTH_NUM);

        buttonHeight = (int) (ConfigManager.getHeight() * BUTTON_HEIGHT_NUM);
        viewQuestion = BitmapFactory.decodeResource(getResources(), R.drawable.view_question);
        nextQuestion = BitmapFactory.decodeResource(getResources(), R.drawable.next_question);
        lastQuestion = BitmapFactory.decodeResource(getResources(), R.drawable.last_question);
    }

    public void setData(NK_MJ_TABLE data) {
        this.data = data;
        
        ansPoint = Integer.parseInt(
                data.getAnswerData().getPointList().get(
                        Integer.parseInt(data.getAnswerData().getUserAnswer())));

        this.isLastSeq = new NkmjDao().selectNKMJMaxSeq().compareTo(this.data.getSeq()) <= 0;
    }
    
    public void setNKAction(NnKrAnswerAction action) {
        this.action = action;
    }

    @Override
    protected void onDraw(Canvas c) {
        this.canvas = c;
        super.onDraw(c);
        if (data != null) {
            // set backGround Color
            canvas.drawColor(Color.GREEN);

            canvas.drawText("あなたの選択した打牌:", baseWidth , baseHeight, textPaint);
            canvas.drawBitmap(HaiConfigManager.getHaiImage(
                    getAnswerHai(data.getAnswerData().getUserAnswer())),
                    baseWidth + TEXT_SIZE * 12, baseHeight - (int)(HaiManager.HAI_HEIGHT / 1.5d), imgPaint);
            
            double lineIndex = 1d;
            
            canvas.drawText("獲得ポイント:" + ansPoint, baseWidth , baseHeight + (int) (textLineHeight * lineIndex++), textPaint);

            canvas.drawText("最もおすすめの打牌:", baseWidth , baseHeight + (int)(textLineHeight * lineIndex++), textPaint);
            canvas.drawBitmap(HaiConfigManager.getHaiImage(getAnswerHai(getAnswerIndex(0))),
                    baseWidth + TEXT_SIZE * 12, baseHeight + (int)(textLineHeight * (lineIndex - 1)) - HaiManager.HAI_HEIGHT / 2, imgPaint);

            lineIndex += 0.5d;

            int comentIndex = 0;
            {
                String[] coments = data.getAnswerData().getComment1().split(CommonFunc.getLineSeparator());
                for (String coment : coments) {
                    canvas.drawText(coment, baseWidth, baseHeight + (int) (textLineHeight * lineIndex) + comentIndex++ * TEXT_SIZE, textPaint);
                }
            }

            lineIndex += 0.5d;
            if (!CommonFunc.isEmpty(data.getAnswerData().getComment2())) {
//                canvas.drawText("おすすめの打牌:", baseWidth , baseHeight + (int)(textLineHeight * lineIndex++), textPaint);
                {
                    String[] coments = data.getAnswerData().getComment2().split(CommonFunc.getLineSeparator());
                    for (String coment : coments) {
                        canvas.drawText(coment, baseWidth, baseHeight + (int) (textLineHeight * lineIndex) + comentIndex++ * TEXT_SIZE, textPaint);
                    }
                }
            }

            
            int widthPos = canvasHaiList(data.getDtlData().getHaiArray(), ownHaiWidth, ownHaiHeight);
            
            canvasHai(data.getDtlData().getHaiTumo(), widthPos + (int) (HaiManager.HAI_WIDTH * 0.1d), ownHaiHeight);

            canvas.drawBitmap(viewQuestion, viewAnswerPositionWidth, buttonHeight, imgPaint);
            if (!isLastSeq) {
                canvas.drawBitmap(nextQuestion, nextQuestionPositionWidth, buttonHeight, imgPaint);
            } else {
                canvas.drawBitmap(lastQuestion, nextQuestionPositionWidth - VIEW_QUESTION_IMAGE_WIDTH, buttonHeight, imgPaint);
            }

        }
    }

    private int getAnswerHai(int answerHaiIndex) {
        if (answerHaiIndex == 13) {
            return Integer.parseInt(data.getDtlData().getHaiTumo());
        } else {
            return Integer.parseInt(
                    data.getDtlData().getHaiArray()[answerHaiIndex]);
        }
    }

    private int getAnswerHai(String answerHaiIndex) {
        return getAnswerHai(CommonFunc.parseInt(answerHaiIndex));
    }

    private int getAnswerIndex(int bestIndex) {
        List<String> answerList = data.getAnswerData().getPointList();
        
        TreeMap<String, Integer> indexMap = new TreeMap<String, Integer>(
                new Comparator<String>() {
                    @Override
                    public int compare(String lhs, String rhs) {
                        return rhs.compareTo(lhs);
                    }
                }
                );
        for (int i = 0; i < answerList.size(); i++) {
            indexMap.put(answerList.get(i), i);
        }
        
        for (Map.Entry<String, Integer> entry : indexMap.entrySet()) {
            if (bestIndex == 0) {
                return entry.getValue();
            }
            bestIndex--;
        }
        
        return 0;
    }

    
    private int canvasHai(String hai, int width, int height) {
        Bitmap img = HaiConfigManager.getHaiImage(hai);
        canvas.drawBitmap(img,width,height,imgPaint);
        width += HaiManager.HAI_WIDTH;
        return width;
    }

    private int canvasHaiList(String[] haiArray, int width, int height) {
        for (int i = 0; i < haiArray.length; i++) {
            Bitmap img = HaiConfigManager.getHaiImage(haiArray[i]);
            canvas.drawBitmap(img,width,height,imgPaint);
            width += HaiManager.HAI_WIDTH;
        }
        return width;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        
        if (MotionEvent.ACTION_UP != event.getAction()) {
            return true;
        }
        float y = event.getY();
        float x = event.getX();

        if (y < buttonHeight || y > buttonHeight + BUTTON_IMAGE_HEIGHT || x == 0) {
            return true;
        }
        
        if (viewAnswerPositionWidth <= x &&
                x <= viewAnswerPositionWidth + VIEW_QUESTION_IMAGE_WIDTH) {
            Intent intent = new Intent(getContext(), NnkrActivity.class);
            NnkrAnswerActivity activity = this.action.getActivity();
            activity.startActivity(intent);
        }

        if (nextQuestionPositionWidth <= x &&
                x <= nextQuestionPositionWidth + NEXT_QUESTION_IMAGE_WIDTH &&
                ! isLastSeq) {
            UserConfigManager.setSessionLastAnsSeq(data.getSeq() + 1);
            Intent intent = new Intent(getContext(), NnkrActivity.class);
            NnkrAnswerActivity activity = this.action.getActivity();
            activity.startActivity(intent);
        }
        
        return true;
    }

}
