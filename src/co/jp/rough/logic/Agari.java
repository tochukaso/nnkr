package co.jp.rough.logic;

import java.util.ArrayList;
import java.util.Arrays;


public class Agari {
/**
    static BufferedImage repairBufferedImage(BufferedImage image) {
        Raster raster = image.getData();
        int height = raster.getHeight();
        int width = raster.getWidth();
        int datasize = width * height;
        final int GRAYSIZE = 256;
        int[] color = new int[4];
        byte[] pallet_grayscale = new byte[GRAYSIZE];
        for(int i=0;i<GRAYSIZE;i++) {
            pallet_grayscale[i] = (byte)i;
        }
        IndexColorModel icm = new IndexColorModel(8,GRAYSIZE,pallet_grayscale,pallet_grayscale,pallet_grayscale);
        byte[] indexout = new byte[datasize];
        int i = 0;
        for(int y=0;y<height;y++) {
            for(int x=0;x<width;x++) {
                raster.getPixel(x,y,color);
                indexout[i] = (byte)color[0];
                i++;
            }
        }
        BufferedImage out = new BufferedImage(width,height,BufferedImage.TYPE_BYTE_INDEXED,icm);
        Graphics g = out.createGraphics();
        g.drawImage(Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width,height,icm,indexout,0,width)),0,0,null);
        g.dispose();
        g = null;
        return(out);
    }

    public static void main(String[] args) {
        String tiles = "1112345678999";
        String[] readyhands = Mahjong2.getReadyHands(tiles);
        for (int i = 0; i < readyhands.length; i++) {
            System.out.println(readyhands[i]);
        }
    }

    /**
     * 麻雀の手牌が入力として与えられたとき「待ち」を出力する
     *
     * @param tiles
     *            入力の麻雀の手牌
     * @return
     */
    static public String[] getReadyHands(String tiles) {
        if (!isNormal(tiles)) {
            return (new String[0]);
        }
        char[] data = getSortingCharacters(tiles).toCharArray();
        ArrayList<String> out = new ArrayList<String>();
        // 隣あった2つが待ちの状態と考えて抜く
        char[] machi = new char[2];
        char[] buffer = new char[data.length - 2];
        for (int i = 0; i < data.length - 1; i++) {
            machi[0] = data[i];
            machi[1] = data[i + 1];
            if (!isMachi(machi)) {
                continue;
            }
            for (int j = 0, k = 0; j < buffer.length; k++) {
                if ((i == k) || (i + 1 == k)) {
                    continue;
                }
                buffer[j] = data[k];
                j++;
            }
            String[] memo = new String[4];
            checkMati(buffer, machi, memo, out, 0);
        }
        String[] dst = new String[out.size()];
        for (int i = 0; i < out.size(); i++) {
            dst[i] = out.get(i);
        }
        return (getSortStrings(dst));
    }

    /**
     * 待ち状態を調べる
     *
     * @param tiles
     * @param machi
     *            (これがかぶったときは動作しなくてもいいと思う(｡･ω･｡))
     * @param memo
     * @param out
     *            存在する待ちをここに代入する
     */
    static private void checkMati(char[] tiles, char[] machi, String[] memo,
            ArrayList<String> out, int depth) {
        char[] tripleset = new char[3];
        char[] nokori;
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                nokori = getRemoveSheungFast(tiles, tripleset, 0);
            } else {
                nokori = getRemovePongFast(tiles, tripleset);
            }
            if (nokori != null) {
                memo[depth] = getString(tripleset);
                if (nokori.length == 2) {
                    if (isAtama(nokori)) {
                        memo[depth + 1] = getString(nokori);
                        String text = getOutput(memo, getString(machi));
                        if (!out.contains(text)) {
                            out.add(text);
                        }
                    }
                    // 単騎（4枚で調べ直す）
                    else {
                        String force = getSortingCharacters(getString(nokori)
                                + getString(machi));
                        char[] data = force.toCharArray();
                        for (int k = 0; k < 3; k++) {
                            if (k == 0) {
                                nokori = getRemoveSheungFast(data, tripleset, 0);
                            } else if (k == 1) {
                                nokori = getRemoveSheungFast(data, tripleset, 1);
                            } else {
                                nokori = getRemovePongFast(data, tripleset);
                            }
                            if (nokori != null) {
                                memo[depth + 1] = getString(tripleset);
                                String text = getOutput(memo, getString(nokori));
                                if (!out.contains(text)) {
                                    out.add(text);
                                }
                            }
                        }
                    }
                } else {
                    checkMati(nokori, machi, memo, out, depth + 1);
                }
            }
        }
    }

    /**
     * 表示用
     *
     * @param in1
     *            順子・刻子・アタマ
     * @param in2
     *            待ち
     * @return
     */
    static private String getOutput(String[] in1, String in2) {
        String[] sort = getSortStrings(in1);
        String text = "";
        int j;
        for (j = 0; j < sort.length; j++) {
            text = text.concat("(" + sort[j] + ")");
        }
        text = text.concat("[" + in2 + "]");
        return (text);
    }

    /**
     * ソートして返す
     *
     * @param in
     * @return
     */
    static private String[] getSortStrings(String[] in) {
        String[] out = new String[in.length];
        System.arraycopy(in, 0, out, 0, in.length);
        Arrays.sort(out);
        return (out);
    }

    /**
     * 「待ち」に成りえるかどうか調べる。
     *
     * @param tiles
     * @return
     */
    static private boolean isMachi(char[] tiles) {
        if (tiles.length == 2) {
            if (Math.abs(tiles[1] - tiles[0]) <= 2) {
                return (true);
            }
        }
        return (false);
    }

    /**
     * 「頭」に成りえるかどうか調べる。
     *
     * @param tiles
     * @return
     */
    static private boolean isAtama(char[] tiles) {
        if (tiles.length == 2) {
            if (tiles[0] == tiles[1]) {
                return (true);
            }
        }
        return (false);
    }

    /**
     * 「順子」があるか調べる。あったら消す。
     *
     * @param tiles
     * @param shuntsu
     *            消した順子
     * @param x
     *            見つかっても無視する回数
     * @return 順子をぬいたもの
     */
    static private char[] getRemoveSheungFast(char[] tiles, char[] shuntsu,
            int x) {
        int[] tiletable = new int[9];
        for (int i = 0; i < tiles.length; i++) {
            tiletable[tiles[i] - '1']++;
        }
        boolean isshuntsu = false;
        for (int i = 0, j = 0; i < tiletable.length - 2; i++) {
            if ((tiletable[i] >= 1) && (tiletable[i + 1] >= 1)
                    && (tiletable[i + 2] >= 1)) {
                if (j < x) {
                    j++;
                    continue;
                }
                shuntsu[0] = (char) (i + '1');
                shuntsu[1] = (char) (i + '2');
                shuntsu[2] = (char) (i + '3');
                tiletable[i + 0]--;
                tiletable[i + 1]--;
                tiletable[i + 2]--;
                isshuntsu = true;
                break;
            }
        }
        if (!isshuntsu) {
            return (null);
        }
        return (getCharacters(tiletable));
    }

    /**
     * 「刻子」があるか調べる。あったら消す。
     *
     * @param tiles
     * @param kotsu
     *            消した刻子
     * @return 刻子をぬいたもの
     */
    static private char[] getRemovePongFast(char[] tiles, char[] kotsu) {
        int[] tiletable = new int[9];
        for (int i = 0; i < tiles.length; i++) {
            tiletable[tiles[i] - '1']++;
        }
        int ispong = -1;
        for (int i = 0; i < tiletable.length; i++) {
            if (tiletable[i] >= 3) {
                ispong = i;
                tiletable[i] -= 3;
                break;
            }
        }
        if (ispong == -1) {
            return (null);
        }
        for (int i = 0; i < 3; i++) {
            kotsu[i] = (char) (ispong + '1');
        }
        return (getCharacters(tiletable));
    }

    /**
     * テーブル（9種の碑がそれぞれいくつあるか）から文字配列に変換します。
     *
     * @param characters
     * @return
     */
    static private char[] getCharacters(int[] tiletable) {
        int sum = 0;
        for (int i = 0; i < tiletable.length; i++) {
            sum += tiletable[i];
        }
        char[] out = new char[sum];
        for (int i = 0, k = 0; i < tiletable.length; i++) {
            for (int j = 0; j < tiletable[i]; j++) {
                out[k] = (char) (i + '1');
                k++;
            }
        }
        return (out);
    }

    /**
     * 文字配列を文字列に変換します。
     *
     * @param characters
     * @return
     */
    static private String getString(char[] characters) {
        String out = "";
        for (int i = 0; i < characters.length; i++) {
            out = out.concat(String.valueOf(characters[i]));
        }
        return (out);
    }

    /**
     * 与えられた麻雀の手牌をソートする
     *
     * @param tiles
     * @return
     */
    static private String getSortingCharacters(String tiles) {
        char[] data = tiles.toCharArray();
        Arrays.sort(data);
        return (getString(data));
    }

    /**
     * 与えられた麻雀の手牌が条件にそっているか調べる。
     *
     * @param tiles
     * @return
     */
    static private boolean isNormal(String tiles) {
        return ((tiles.matches("[1-9]{13}")));
    }

    /*
    static private boolean isNormal(String tiles) {
        tiles = tiles.replaceAll(" ", "");
        if (tiles.length() != 13) {
            return (false);
        }
        char[] data = tiles.toCharArray();
        boolean isnormal = true;
        for (int i = 0; i < data.length; i++) {
            if ((data[i] < '1') && ('9' < data[i])) {
                isnormal = false;
                break;
            }
        }
        return (isnormal);
    }*/
}