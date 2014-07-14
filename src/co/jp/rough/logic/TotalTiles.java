package co.jp.rough.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.util.Log;
import co.jp.rough.hai.Hai;


public class TotalTiles {

    /**
     * subtiles[0] = 万ズ
     * subtiles[1] = ピンズ
     * subtiles[2] = ソーズ
     * subtiles[3] = 字牌
     */
    SubTile[] subtiles;

    public SubTile[] getSubtiles() {
        return subtiles;
    }

    public int getShantenNumber() {
        return shantenNumber;
    }

    public List<Hai> getMatiList() {
        return matiList;
    }

    private int shantenNumber = Integer.MAX_VALUE;

    private List<Hai> matiList = new ArrayList<Hai>();
    
    public TotalTiles() {
        subtiles = new SubTile[4];
        for (int i = 0; i < 4; i ++) {
            subtiles[i] = new SubTile(i);
        }
    }
    
    public TotalTiles(SubTile[] tiles) {
        subtiles = tiles;
    }

    /**
     * 引数の配列は、以下の順番の通りに並んでいること
     * マンズ、
     * ピンズ
     * ソーズ
     * 字牌
     * @param tiles
     */
    public TotalTiles(Integer[][] tiles) {
        subtiles = new SubTile[4];

        for (int i = 0; i < tiles.length; i++) {
            SubTile sub = new SubTile(i);
            sub.setTileType(i);
            sub.setTiles(tiles[i]);
            sub.setCombination();
            subtiles[i] = sub;
        }
        
    }

    public SubTile getSubTile(int index) {
        return this.subtiles[index];
    }
    
    private TileCombination addTile(TileCombination addTileCombination, TileCombination tC) {
        TileCombination res = new TileCombination(addTileCombination);
        if (tC != null && tC.hashCode() != 0) {
            res.add(tC);
        }
        
        return res;
    }

    
    public void analize() {
        // analize shantenNumber.
        /**
        int mentsuCnt = 0;
        int tartsuCnt = 0;
        int atamaCnt = 0;
        int tankiCnt = 0;
        **/

        List<TileCombination> manzList = subtiles[0].getCombinationList();
        if (manzList == null || manzList.size() == 0) {
            manzList = new ArrayList<TileCombination>();
            manzList.add(new TileCombination());
        }

        List<TileCombination> pinzList = subtiles[1].getCombinationList();
        if (pinzList == null || pinzList.size() == 0) {
            pinzList = new ArrayList<TileCombination>();
            pinzList.add(new TileCombination());
        }

        List<TileCombination> sozList = subtiles[2].getCombinationList();
        if (sozList == null || sozList.size() == 0) {
            sozList = new ArrayList<TileCombination>();
            sozList.add(new TileCombination());
        }

        List<TileCombination> jiList = subtiles[3].getCombinationList();
        if (jiList == null || jiList.size() == 0) {
            jiList = new ArrayList<TileCombination>();
            jiList.add(new TileCombination());
        }

        TileCombination analize = new TileCombination();
        int localShantenNumber = 6;
        for (TileCombination manz : manzList) {
            TileCombination manzAdd = addTile(analize, manz);
            for (TileCombination pinz : pinzList) {
                TileCombination pinzAdd = addTile(manzAdd, pinz);
                for (TileCombination soz : sozList) {
                    TileCombination sozAdd = addTile(pinzAdd, soz);
                    for (TileCombination ji : jiList) {
                        TileCombination jiAdd = addTile(sozAdd, ji);
                        Mati mati = getShantenNumber(jiAdd);
                        localShantenNumber = Math.min(localShantenNumber, mati.shantenNumber);

                        if (mati.shantenNumber == 0) {
//                            this.matiList.addAll(Arrays.asList(mati.waitHai));
                        }
                        Log.d("シャン点数 " + mati.shantenNumber + " : " + "待ち" + mati.getMati(), "");
                        
                    }
                }
            }
        }
        shantenNumber = localShantenNumber;
    }
    
    private Mati getShantenNumberN(TileCombination tile) {
        
        Mati mati = new Mati();
        
        Set<Hai> waitNum = new HashSet<Hai>();

        if (tile.getAtamaList().size() == 6) {
            waitNum.add(tile.getTankiList().get(0)[0]);
            mati.shantenNumber = 0;
            return mati;
        }

        int shantenNumber = 8;

        int mentsuCnt = tile.getAnkoList().size() + tile.getMentsuList().size();
        int tartsuCnt = tile.getPentyanList().size() + tile.getKantyanList().size();
        int atamaCnt = tile.getAtamaList().size();
        int tankiCnt = tile.getTankiList().size();
        

        shantenNumber -= mentsuCnt * 2;

        
        if (4 - mentsuCnt <= tartsuCnt) {
            shantenNumber -= Math.min(4 - mentsuCnt, tartsuCnt);
            shantenNumber -= Math.min(atamaCnt, 1);
        } else {
            shantenNumber -= Math.min(4 - mentsuCnt, tartsuCnt + atamaCnt);
        }

        mati.shantenNumber = shantenNumber;
/**
        if (shantenNumber == 0) {
            if (tile.getPentyanList().size() == 1) {
                int one = tile.getPentyanList().get(0)[0].getHaiValue();
                int two = tile.getPentyanList().get(0)[1].getHaiValue();
                int haiType = tile.getPentyanList().get(0)[0].getHaiType();
                if (one != 1) {
                    waitNum.add(new Hai(haiType, one- 1));
                }
                if (two != 9) {
                    waitNum.add(new Hai(haiType, two + 1));
                }
            } else if (tile.getKantyanList().size() == 1) {
                    waitNum.add(new Hai(tile.getKantyanList().get(0)[0].getHaiType(), tile.getKantyanList().get(0)[0].getHaiValue() + 1));
            } else if (atamaCnt == 2) {
                waitNum.add(tile.getAtamaList().get(0)[0]);
                waitNum.add(tile.getAtamaList().get(1)[0]);
            } else if (tankiCnt == 1) {
                waitNum.add(tile.getTankiList().get(0)[0]);
            }
        }
**/
        return mati;
    }

    
    private Mati getShantenNumber(TileCombination tile) {
        
        Mati mati = new Mati();
        
        Set<Hai> waitNum = new HashSet<Hai>();
/**
        if (tile.getAtamaList().size() == 6) {
            waitNum.add(tile.getTankiList().get(0)[0]);
            mati.shantenNumber = 0;
            return mati;
        }
**/
        int shantenNumber = -1;

        int mentsuCnt = tile.getAnkoList().size() + tile.getMentsuList().size();
        int tartsuCnt = tile.getPentyanList().size() + tile.getKantyanList().size();
        int atamaCnt = tile.getAtamaList().size();
        int tankiCnt = tile.getTankiList().size();
        

        shantenNumber += (4 - Math.min(4, mentsuCnt)) * 2;

        shantenNumber -= Math.min(4 - mentsuCnt, tartsuCnt + Math.max(atamaCnt - 1, 0));

        shantenNumber += 1 - Math.min(1, atamaCnt);

        shantenNumber += 1 - Math.min(1, Math.max(atamaCnt, tankiCnt));

        
        mati.shantenNumber = Math.min(shantenNumber, 6 - tile.getAtamaList().size());
        if (shantenNumber == 0) {
            if (tile.getPentyanList().size() == 1) {
                int one = tile.getPentyanList().get(0)[0].getHaiValue();
                int two = tile.getPentyanList().get(0)[1].getHaiValue();
                int haiType = tile.getPentyanList().get(0)[0].getHaiType();
                if (one != 1) {
                    waitNum.add(new Hai(haiType, one- 1));
                }
                if (two != 9) {
                    waitNum.add(new Hai(haiType, two + 1));
                }
            } else if (tile.getKantyanList().size() == 1) {
                    waitNum.add(new Hai(tile.getKantyanList().get(0)[0].getHaiType(), tile.getKantyanList().get(0)[0].getHaiValue() + 1));
            } else if (atamaCnt == 2) {
                waitNum.add(tile.getAtamaList().get(0)[0]);
                waitNum.add(tile.getAtamaList().get(1)[0]);
            } else if (tankiCnt == 1) {
                waitNum.add(tile.getTankiList().get(0)[0]);
            }
        }
        return mati;
    }

    
    
    
    class Mati {
        int shantenNumber;
        Hai[] waitHai;
        
        public Mati() {
            shantenNumber = -1;
        }
        
        String getMati() {
            if (waitHai == null || waitHai.length == 0) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            
            for (Hai hai : waitHai) {
                sb.append(hai.getHaiNum()).append(",");
            }
            if (sb.length() > 0) {
                sb.substring(sb.length() -1);
            }
            return sb.toString();
        }
        
    }
    
}
