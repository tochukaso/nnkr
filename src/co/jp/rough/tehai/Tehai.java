package co.jp.rough.tehai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.util.Log;
import co.jp.rough.hai.Hai;
import co.jp.rough.hai.HaiManager;
import co.jp.rough.logic.TileCombination;
import co.jp.rough.logic.TotalTiles;

public class Tehai {

    public static final int MAX_CNT = 14;

    List<Hai> tehaiList;
    TotalTiles tiles;

    public Tehai() {
        tehaiList = new ArrayList<Hai>();
        tiles = new TotalTiles();
    }

    public void setHaipai() {
        tehaiList = new ArrayList<Hai>();
        for (int i = 0; i < MAX_CNT - 1; i++) {
            addHai(HaiManager.drawHai());
        }
        sortHai();
        for (int i = 0; i < 4; i++) {
            tiles.getSubtiles()[i].setCombination();
        }
        
        tiles.analize();
    }

    public void addHai(Hai hai) {
        if (tehaiList.size() >= MAX_CNT) {
            Log.e("Tehai", "already hai cnt is 14. forbit add");
            return;
        }
        tehaiList.add(hai);
        addTiles(hai);
    }

    public Hai removeHai(int haiIndex) {
        Hai hai = tehaiList.remove(haiIndex);
        sortHai();
        removeTiles(hai);

        return hai;
    }

    public int getShantenNumber() {
        this.tiles.analize();
        return this.tiles.getShantenNumber();
    }

    private List addList(List<TileCombination> list, int tileIndex, int getIndex) {
        List<TileCombination> res = new ArrayList<TileCombination>(list);
        TileCombination now = tiles.getSubTile(tileIndex).getCombinationList().get(getIndex);
        if (now == null) {
            return res;
        }
        res.add(now);
        return res;
    }

    public List<Hai> getTehai() {
        return tehaiList;
    }

    public void sortHai() {
        Collections.sort(tehaiList);
    }
    private void addTiles(Hai hai) {
        int haiType = hai.getHaiType();
        tiles.getSubTile(haiType).addTile(hai.getHaiValue());
        if (tehaiList.size() >= 13) {
            tiles.getSubTile(haiType).setCombination();
        }
    }

    private void removeTiles(Hai hai) {
        int haiType = hai.getHaiType();
        tiles.getSubTile(haiType).removeTile(hai.getHaiValue());
        if (tehaiList.size() >= 13) {
            tiles.getSubTile(haiType).setCombination();
        }
    }

}
