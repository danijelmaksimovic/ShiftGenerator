package model;

import connections.Connections;
import view.LoginView;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Database {

    public static Database instance;
    private List<Korisnik> listaKorisnika;
    private TreeMap<RadniDani, List<String>> listaDana;

    static {
        instance = new Database();
    }

    public Database(){
        listaKorisnika = new ArrayList<>();
        listaDana = new TreeMap<>();
        load();
    }

    public static Database getInstance() {
        return instance;
    }

    private void load() {
        Connection myConnection = Connections.getInstance().napraviKonekciju();

        try {
            Statement myStatement = myConnection.createStatement();

            ResultSet myRS = myStatement.executeQuery("select * from Korisnici");

            while(myRS.next()){
                int id = myRS.getInt("id");
                String ime = myRS.getString("ime");
                String prezime = myRS.getString("prezime");
                String uloga = myRS.getString("uloga");
                String username = myRS.getString("username");
                String password = myRS.getString("password");
                int idFK = myRS.getInt("adminFK");

                Korisnik k = new Korisnik(id, ime, prezime, uloga, username, password, idFK);
                listaKorisnika.add(k);

        }
        myConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Korisnik> getListaKorisnika() {
        return listaKorisnika;
    }

    public TreeMap<RadniDani, List<String>> getListaDana() {
        return listaDana;
    }
}
