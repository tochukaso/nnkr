package co.jp.rough.hai;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.SparseArray;
import co.jp.rough.common.ConfigManager;

public class HaiConfigManager {

    static SparseArray<Bitmap> HaiImageMap;
    
    static {
        HaiImageMap = new SparseArray<Bitmap>();
    }
    
    public static void init(Resources res) {
        
        for (int i = 1; i <= HaiManager.HAI_CNT; i++) {
            HaiImageMap.put(i,
                            BitmapFactory.decodeResource(
                                    res,
                                    HaiEnum.getImg(i, false))
                            )  ;
        }

        int redIndex = 105;
        int fiveIndex = 5;
        // TODO オプション利かせる。
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.outHeight = HaiManager.HAI_HEIGHT;
        option.outWidth = HaiManager.HAI_WIDTH;

        for (int i = 0; i < 3; i++) {
            HaiImageMap.put(redIndex + i * 10,
                            BitmapFactory.decodeResource(
                                    res,
                                    HaiEnum.getImg(fiveIndex + i * 9, true) 
                                    ));
        }
        
    }
    
    public static Bitmap getHaiImage(int haiNum) {
        return HaiImageMap.get(haiNum);
    }

    public static Bitmap getHaiImage(String haiNum) {
        return HaiImageMap.get(Integer.parseInt(haiNum));
    }

}
