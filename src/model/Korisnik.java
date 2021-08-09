package model;

public class Korisnik {

    private int id;
    private String ime;
    private String prezime;
    private String uloga;
    private String username;
    private String password;
    private int idFK;

    public Korisnik(int id, String ime, String prezime, String uloga, String username, String password) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.uloga = uloga;
        this.username = username;
        this.password = password;
    }

    public Korisnik(int id, String ime, String prezime, String uloga, String username, String password, int idFK) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.uloga = uloga;
        this.username = username;
        this.password = password;
        this.idFK = idFK;
    }

    public Korisnik(String ime, String prezime, String uloga, String username, String password) {
        this.ime = ime;
        this.prezime = prezime;
        this.uloga = uloga;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return ime + ' ' + prezime + ' ' + uloga;
    }

    public int getIdFK() {
        return idFK;
    }

    public void setIdFK(int idFK) {
        this.idFK = idFK;
    }
}
