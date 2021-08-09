package model;

import java.util.List;

public class Smena {

    private String dan;
    private List<Korisnik> radniciL;

    public Smena(String dan, List<Korisnik> radniciL) {
        this.dan = dan;
        this.radniciL = radniciL;
    }

    public String getDan() {
        return dan;
    }

    public void setDan(String dan) {
        this.dan = dan;
    }

    public List<Korisnik> getRadniciL() {
        return radniciL;
    }

    public void setRadniciL(List<Korisnik> radniciL) {
        this.radniciL = radniciL;
    }

    @Override
    public String toString() {
        return  dan + ' ' + radniciL;
    }
}
