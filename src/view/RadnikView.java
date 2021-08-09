package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Database;
import model.RadniDani;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RadnikView extends VBox {

    private LoginView lv;

    public static Scene makeScene(LoginView loginView){
        return new Scene(new RadnikView(loginView), 300, 300);
    }

    private Label podaci;
    private Label radniDani;
    private Label tekst;


    private List<RadniDani> listaDana;

    public RadnikView(LoginView loginView){
        lv = loginView;
        listaDana = new ArrayList<>();
        elements();
    }

    private void elements() {
        podaci = new Label();
        radniDani = new Label();
        tekst = new Label("Cao");

        String radnik = lv.getKorisnik().getIme() + " " + lv.getKorisnik().getPrezime();
        System.out.println(radnik);
        podaci.setText(radnik + " " + lv.getKorisnik().getUloga());

        for (Map.Entry<RadniDani, List<String>> mapa : Database.getInstance().getListaDana().entrySet()){
            for(String string : mapa.getValue()){
                System.out.println(string);
                if (string.contains(radnik)) listaDana.add(mapa.getKey());
            }
        }

        System.out.println(Database.getInstance().getListaDana());

        radniDani.setText(String.valueOf(listaDana));

        this.getChildren().addAll(podaci, radniDani);
    }
}
