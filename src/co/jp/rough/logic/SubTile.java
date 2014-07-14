package co.jp.rough.logic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import co.jp.rough.hai.Hai;

/**
 * 
 * @author Yasu
 *
 */
public class SubTile {
        
    /**
     * 1 : マンズ
     * 2 : ピンズ
     * 3 : ソーズ
     * 4 : 字牌
     */
    private int tileType;

    /**
     * 0 = 総枚数
     * 1~9 タイル
     */
    private Integer[] tiles;

    private List<TileCombination> combinationList;

    public List<TileCombination> getCombinationList() {
        return combinationList;
    }

    public void setCombinationList(List<TileCombination> combinationList) {
        this.combinationList = combinationList;
    }

    public void setCombination() {
        combinationList = new ArrayList<TileCombination>();
        Deque<Work> que = new ArrayDeque<Work>() {
            @Override
            public void addFirst(Work e) {
                if (!this.contains(e)) {
                    super.addFirst(e);
                }
            }
        };

        Work first = new Work();
        first.unfixed = Arrays.copyOf(tiles, tiles.length);
//        System.arraycopy(tiles, 0, first.unfixed, 0, tiles.length);
        que.addFirst(first);

        while(!que.isEmpty()) {
            Work now = que.removeLast();
            Integer[] unfixed = now.unfixed;
            TileCombination fixed = now.fixed;
            int target = 0;
            int targetCnt = 0;
            int tartsuCnt = fixed.getRyanmenList().size() + fixed.getKantyanList().size() + fixed.getPentyanList().size();

            for (int i = 1; i < tiles.length; i++) {
                if (unfixed[i] != 0) {
                    target = i;
                    targetCnt = unfixed[i];
                    break;
                }
            }
            if (targetCnt == 0) {
                combinationList.add(fixed);
            }

            // �Í�
            if (targetCnt >= 3) {
                Integer[] next = Arrays.copyOf(unfixed, unfixed.length);
                next[target] -=3;

                TileCombination nextCombi = fixed.copy();
                Hai[] anko = new Hai[3];
                Arrays.fill(anko, new Hai(this.tileType, target, false));
                nextCombi.addAnkoList(anko);

                Work neo = new Work(next, nextCombi);
                que.addFirst(neo);
            }
            if (tileType != 3) {
                if (target <= 7  && unfixed[target + 1] > 0 && unfixed[target + 2] > 0) {
                    Integer[] next = Arrays.copyOf(unfixed, unfixed.length);
                    next[target] -=1;
                    next[target + 1] -=1;
                    next[target + 2] -=1;

                    TileCombination nextCombi = fixed.copy();
                    Hai[] mentsu = new Hai[3];
                    mentsu[0] = new Hai(this.tileType, target, false);
                    mentsu[1] = new Hai(this.tileType, target + 1, false);
                    mentsu[2] = new Hai(this.tileType, target + 2, false);
                    nextCombi.addMentsuList(mentsu);

                    Work neo = new Work(next, nextCombi);
                    que.addFirst(neo);
                }

                if (target <= 8  && unfixed[target + 1] > 0 && tartsuCnt < 1) {
                    Integer[] next = Arrays.copyOf(unfixed, unfixed.length);
                    next[target] -=1;
                    next[target + 1] -=1;

                    TileCombination nextCombi = fixed.copy();
                    Hai[] pentyan = new Hai[2];
                    pentyan[0] = new Hai(this.tileType, target, false);
                    pentyan[1] = new Hai(this.tileType, target + 1, false);
                    nextCombi.addPentyanList(pentyan);

                    Work neo = new Work(next, nextCombi);
                    que.addFirst(neo);
                }

                if (target <= 7  && unfixed[target + 2] > 0  && tartsuCnt < 1) {
                    Integer[] next = Arrays.copyOf(unfixed, unfixed.length);
                    next[target] -=1;
                    next[target + 2] -=1;

                    TileCombination nextCombi = fixed.copy();
                    Hai[] kantyan = new Hai[2];
                    kantyan[0] = new Hai(this.tileType, target, false);
                    kantyan[1] = new Hai(this.tileType, target + 2, false);
                    nextCombi.addKantyanList(kantyan);

                    Work neo = new Work(next, nextCombi);
                    que.addFirst(neo);
                }
            }
            if (targetCnt >= 2) {
                Integer[] next = Arrays.copyOf(unfixed, unfixed.length);
                next[target] -= 2;

                TileCombination nextCombi = fixed.copy();
                Hai[] atama = new Hai[2];
                Arrays.fill(atama, new Hai(this.tileType, target, false));
                nextCombi.addAtamaList(atama);

                Work neo = new Work(next, nextCombi);
                que.addFirst(neo);
            } else if (targetCnt == 1 ) {
                Integer[] next = Arrays.copyOf(unfixed, unfixed.length);
                next[target] -= 1;

                TileCombination nextCombi = fixed.copy();
                Hai[] tanki = new Hai[1];
                tanki[0] = new Hai(this.tileType, target, false);
                nextCombi.addTankiList(tanki);

                Work neo = new Work(next, nextCombi);
                que.addFirst(neo);
            }

        }

/**
        // analize shantenNumber.
        {
            for (TileCombination tile : combinationList) {
                Set<Integer> waitNum = new HashSet<Integer>();

                if (tile.getAtamaList().size() == 6) {
                    waitNum.add(tile.getTankiList().get(0)[0]);
                    tile.setShantenNumber(0);
                    tile.setWaitNumber(waitNum.toArray(new Integer[0]));
                    continue;
                }

                int shantenNumber = -1;

                int mentsuCnt = tile.getAnkoList().size() + tile.getMentsuList().size();
                int tartsuCnt = tile.getPentyanList().size() + tile.getKantyanList().size();
                int atamaCnt = tile.getAtamaList().size();
                int tankiCnt = tile.getTankiList().size();
                

                shantenNumber += (4 - Math.min(4, mentsuCnt)) * 2;

                shantenNumber -= Math.min(4 - mentsuCnt, tartsuCnt + Math.max(atamaCnt - 1, 0));

                shantenNumber += 1 - Math.min(1, atamaCnt);

                shantenNumber += 1 - Math.min(1, Math.max(atamaCnt, tankiCnt));

                tile.setShantenNumber(shantenNumber);
                
                if (shantenNumber == 0) {
                    if (tile.getPentyanList().size() == 1) {
                        int one = tile.getPentyanList().get(0)[0];
                        int two = tile.getPentyanList().get(0)[1];
                        if (one != 1) {
                            waitNum.add(one - 1);
                        }
                        if (two != 9) {
                            waitNum.add(two + 1);
                        }
                    } else if (tile.getKantyanList().size() == 1) {
                        waitNum.add(tile.getKantyanList().get(0)[0] + 1);
                    } else if (atamaCnt == 2) {
                        waitNum.add(tile.getAtamaList().get(0)[0]);
                        waitNum.add(tile.getAtamaList().get(1)[0]);
                    } else if (tankiCnt == 1) {
                        waitNum.add(tile.getTankiList().get(0)[0]);
                    }
                    tile.setWaitNumber(waitNum.toArray(new Integer[0]));
                }
                
            }

        }
**/
        
    }

    private class Work {
        Integer[] unfixed;
        TileCombination fixed;
        Work() {
            fixed = new TileCombination();
            unfixed = new Integer[10];
        }
        Work(Integer[] uf, TileCombination fx) {
            unfixed = uf;
            fixed = fx;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Work)) {
                return false;
            }
            return this.fixed.equals(((Work)obj).fixed);
        }
    
        @Override
        public int hashCode() {
            return fixed.hashCode();
        }

    }

    public int getTileType() {
        return tileType;
    }

    public void setTileType(int tileType) {
        this.tileType = tileType;
    }

    public Integer[] getTiles() {
        return tiles;
    }

    public void setTiles(Integer[] args) {
        Arrays.fill(tiles, 0);
        
        for (int t : args) {
            tiles[t]++;
        }
    }

    public SubTile(int tileType) {
        tiles = new Integer[10];
        Arrays.fill(tiles, 0);
        
        this.tileType = tileType;
    }

    public void setTile(int index, int count) {
        tiles[0] += (count - tiles[index]);
        tiles[index] = count;
    }

    /**
     * <pre>
     * 指定されたインデックスの牌を一枚増やします。
     * </pre>
     * @param index
     */
    public void addTile(int index) {
        tiles[0]++;
        tiles[index]++;
    }

    /**
     * <pre>
     * 指定されたインデックスの牌を一枚減らします。
     * </pre>
     * @param index
     */
    public void removeTile(int index) {
        tiles[0]--;
        tiles[index]--;
    }
    
    public int getTargetTileCnt(int index) {
        return tiles[index];
    }

    public int getTileSumCnt() {
        return tiles[0];
    }

    public void setArrayTile(Integer[] array) {
        int cnt = 0;
        int index = 0;
        for (int i = 1; i <= 9; i++) {
            for (int j = index; j < array.length; j++) {
                if (i == array[j]) {
                    index++;
                    cnt ++;
                } else {
                    break;
                }
            }
            this.setTile(i, cnt);
            cnt = 0;
        }
    }

}
