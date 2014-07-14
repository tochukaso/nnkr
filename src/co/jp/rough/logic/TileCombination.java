package co.jp.rough.logic;

import java.util.ArrayList;
import java.util.List;

import co.jp.rough.hai.Hai;

public class TileCombination {

    private List<Hai[]> ankoList;
    private List<Hai[]> mentsuList;
    private List<Hai[]> ryanmenList;
    private List<Hai[]> pentyanList;

    private List<Hai[]> kantyanList;
    private List<Hai[]> atamaList;
    private List<Hai[]> tankiList;

    private Hai[] waitNumber;

    private Integer shantenNumber = Integer.MAX_VALUE;

    public TileCombination() {
        ankoList = new ArrayList<Hai[]>();
        mentsuList = new ArrayList<Hai[]>();
        ryanmenList = new ArrayList<Hai[]>();
        pentyanList = new ArrayList<Hai[]>();
        kantyanList = new ArrayList<Hai[]>();
        atamaList = new ArrayList<Hai[]>();
        tankiList = new ArrayList<Hai[]>();
    }

    public TileCombination(TileCombination tile) {
        ankoList = new ArrayList<Hai[]>(tile.getAnkoList());
        mentsuList = new ArrayList<Hai[]>(tile.getMentsuList());
        ryanmenList = new ArrayList<Hai[]>(tile.getRyanmenList());
        pentyanList = new ArrayList<Hai[]>(tile.getPentyanList());
        kantyanList = new ArrayList<Hai[]>(tile.getKantyanList());
        atamaList = new ArrayList<Hai[]>(tile.getAtamaList());
        tankiList = new ArrayList<Hai[]>(tile.getTankiList());
    }

    public void add(TileCombination tc) {
        if (tc == null) return;
        
        this.ankoList.addAll(tc.getAnkoList());
        this.mentsuList.addAll(tc.getMentsuList());
        this.ryanmenList.addAll(tc.getRyanmenList());
        this.pentyanList.addAll(tc.getPentyanList());
        this.kantyanList.addAll(tc.getKantyanList());
        this.atamaList.addAll(tc.getAtamaList());
        this.tankiList.addAll(tc.getTankiList());

    }
    
    public List<Hai[]> getAnkoList() {
        return ankoList;
    }
    public void setAnkoList(List<Hai[]> ankoList) {
        this.ankoList = ankoList;
    }
    public List<Hai[]> getMentsuList() {
        return mentsuList;
    }
    public void setMentsuList(List<Hai[]> mentsuList) {
        this.mentsuList = mentsuList;
    }
    public List<Hai[]> getRyanmenList() {
        return ryanmenList;
    }
    public void setRyanmenList(List<Hai[]> ryanmenList) {
        this.ryanmenList = ryanmenList;
    }
    public List<Hai[]> getPentyanList() {
        return pentyanList;
    }
    public void setPentyanList(List<Hai[]> pentyanList) {
        this.pentyanList = pentyanList;
    }
    public List<Hai[]> getAtamaList() {
        return atamaList;
    }
    public void setAtamaList(List<Hai[]> atamaList) {
        this.atamaList = atamaList;
    }
    public List<Hai[]> getTankiList() {
        return tankiList;
    }
    public void setTankiList(List<Hai[]> tankiList) {
        this.tankiList = tankiList;
    }

    public List<Hai[]> getKantyanList() {
        return kantyanList;
    }

    public void setKantyanList(List<Hai[]> kantyanList) {
        this.kantyanList = kantyanList;
    }

    public void addAnkoList(Hai[] arg) {
        ankoList.add(arg);
    }

    public void addMentsuList(Hai[] arg) {
        mentsuList.add(arg);
    }
    public void addRyanmenList(Hai[] arg) {
        ryanmenList.add(arg);
    }
    public void addPentyanList(Hai[] arg) {
        pentyanList.add(arg);
    }
    public void addKantyanList(Hai[] arg) {
        kantyanList.add(arg);
    }
    public void addAtamaList(Hai[] arg) {
        atamaList.add(arg);
    }
    public void addTankiList(Hai[] arg) {
        tankiList.add(arg);
    }


    public Hai[] getWaitNumber() {
        return waitNumber;
    }

    public void setWaitNumber(Hai[] waitNumber) {
        this.waitNumber = waitNumber;
    }

    public Integer getShantenNumber() {
        return shantenNumber;
    }

    public void setShantenNumber(Integer shantenNumber) {
        this.shantenNumber = shantenNumber;
    }


    public TileCombination copy() {
        TileCombination cop = new TileCombination();
        cop.ankoList = new ArrayList<Hai[]>(this.ankoList);
        cop.mentsuList = new ArrayList<Hai[]>(this.mentsuList);
        cop.ryanmenList = new ArrayList<Hai[]>(this.ryanmenList);
        cop.pentyanList = new ArrayList<Hai[]>(this.pentyanList);
        cop.kantyanList = new ArrayList<Hai[]>(this.kantyanList);
        cop.atamaList = new ArrayList<Hai[]>(this.atamaList);
        cop.tankiList = new ArrayList<Hai[]>(this.tankiList);
        return cop;
	}

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TileCombination)) {
            return false;
        }
        TileCombination comp = (TileCombination) obj;

        if (!compList(this.ankoList, comp.getAnkoList())) return false;
        if (!compList(this.mentsuList, comp.getMentsuList())) return false;
        if (!compList(this.pentyanList, comp.getPentyanList())) return false;
        if (!compList(this.kantyanList, comp.getKantyanList())) return false;
        if (!compList(this.atamaList, comp.getAtamaList())) return false;
        if (!compList(this.tankiList, comp.getTankiList())) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int sum = 0;
        int K = 14;
        sum += getHash(this.ankoList, K-=this.ankoList.size() * 3);
        sum += getHash(this.mentsuList, K-=this.mentsuList.size() * 3);
        sum += getHash(this.pentyanList, K-=this.pentyanList.size() * 2);
        sum += getHash(this.kantyanList, K-=this.kantyanList.size() * 2);
        sum += getHash(this.atamaList, K-=this.atamaList.size() * 2);
        sum += getHash(this.tankiList, K-=this.tankiList.size() * 1);

        return sum;
    }

    private int getHash(List<Hai[]> list, int K) {
        int hash = 0;
        for (Hai[] array : list) {
            for (Hai hai : array) {
                hash += hai.getHaiNum() * K--;
            }
        }
        return hash;
    }

    private boolean compList(List<Hai[]> comp, List<Hai[]> comp2) {

        if (comp == null || comp2 == null || comp.size() != comp2.size()) {
            return false;
        }

        for (int i = 0; i < comp.size(); i++) {
            Hai[] c1 = comp.get(i);
            Hai[] c2 = comp2.get(i);

            if (c1.length != c2.length) {
                return false;
            }

            for (int j = 0; j < c1.length; j++) {
                if (c1[j] != c2[j]) return false;
            }

        }

        return true;
    }
}
