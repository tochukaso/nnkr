package co.jp.rough.logic;


public class MahJong {

    private static int n = 13;

    private static Integer[][] tiles = {
                                        {1, 1, 1, 2, 2, 2, 3 },
                                        {},
                                        {6, 6, 6, 9, 9, 9},
                                        {}
    };

    //    private static Integer[] tiles = {1, 1, 2, 2, 2, 5, 5, 7, 7, 8, 8, 9, 9};

    public static void main(String[] args) {
        
        TotalTiles totalTiles = new TotalTiles(tiles);

        totalTiles.analize();
/**
        for (TileCombination t : totalTiles.subtiles[0].getCombinationList()) {

            for(Integer[] arg : t.getAnkoList()) {
                for (int i : arg) {
                    System.out.print(i);
                }
                System.out.println(" : ");
            }
            for(Integer[] arg : t.getMentsuList()) {
                for (int i : arg) {
                    System.out.print(i);
                }
                System.out.println(" : ");
            }

            for(Integer[] arg : t.getPentyanList()) {
                for (int i : arg) {
                    System.out.print(i);
                }
                System.out.println(" : ");
            }

            for(Integer[] arg : t.getKantyanList()) {
                for (int i : arg) {
                    System.out.print(i);
                }
                System.out.println(" : ");
            }

            for(Integer[] arg : t.getAtamaList()) {
                for (int i : arg) {
                    System.out.print(i);
                }
                System.out.println(" : ");
            }

            for(Integer[] arg : t.getTankiList()) {
                for (int i : arg) {
                    System.out.print(i);
                }
                System.out.println(" : ");
            }

            System.out.print(t.getShantenNumber() + ":");
            if (t.getShantenNumber() == 0) {
                for (Integer w : t.getWaitNumber()) {
                    System.out.print(w + " ");
                }
            }
            System.out.println();
        }
**/
    }

}
/**
class Type {
    Integer[] fixed;
    Integer[] unfixed;

    public Type(Integer[] f, Integer[] uf) {
        this.fixed = f;
        this.unfixed = uf;
    }

}

class TypeManager {
    public static Type factoryType(Integer[] fixed, Integer[] unfixed, int count) {
        Integer[] newFixed = new Integer[fixed.length + count];
        Integer[] newUnfixed = new Integer[unfixed.length - count];
        System.arraycopy(unfixed, count, newUnfixed, 0, newUnfixed.length);
        System.arraycopy(fixed, 0, newFixed, 0, fixed.length);
        System.arraycopy(unfixed, 0, newFixed, fixed.length, count);

        return new Type(newFixed, newUnfixed);
    }
}
**/