package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import model.Database;
import model.Korisnik;
import model.RadniDani;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

public class NapraviSmeneView extends VBox {

    private LoginView lv;

    public static Scene makeScene(LoginView loginView){
        return new Scene(new NapraviSmeneView(loginView), 700,500);
    }

    private Label ponedeljakLbl;
    private Label radniciPonedeljakLbl;

    private Label utorakLbl;
    private Label radniciUtorakLbl;

    private Label sredaLbl;
    private Label radniciSredaLbl;

    private Label cetvrtakLbl;
    private Label radniciCetvrtakLbl;

    private Label petakLbl;
    private Label radniciPetakLbl;

    private Button generisiBtn;
    private Button sacuvajBtn;

    private TreeMap<Integer, Korisnik> mapaKorisnika;
    private TreeMap<RadniDani, List<String>> mapaSmena;
    private static int flag1;
    private static int flag2;
    private static List<Integer> listaBrojeva;

    public NapraviSmeneView(LoginView loginView){
        lv = loginView;
        mapaKorisnika = new TreeMap<>();
        mapaSmena = new TreeMap<>();
        listaBrojeva = new ArrayList<>();
        flag1 = 0;
        flag2 = 0;
        elements();
        listeners();
    }

    private void elements() {
        ponedeljakLbl = new Label("Ponedeljak: ");
        ponedeljakLbl.setFont(Font.font(24));

        utorakLbl = new Label("Utorak: ");
        utorakLbl.setFont(Font.font(24));

        sredaLbl = new Label("Sreda: ");
        sredaLbl.setFont(Font.font(24));

        cetvrtakLbl = new Label("Cetvrtak: ");
        cetvrtakLbl.setFont(Font.font(24));

        petakLbl = new Label("Petak: ");
        petakLbl.setFont(Font.font(24));

        radniciPonedeljakLbl = new Label();
        radniciPonedeljakLbl.setFont(Font.font(24));

        radniciUtorakLbl = new Label();
        radniciUtorakLbl.setFont(Font.font(24));

        radniciSredaLbl = new Label();
        radniciSredaLbl.setFont(Font.font(24));

        radniciCetvrtakLbl = new Label();
        radniciCetvrtakLbl.setFont(Font.font(24));

        radniciPetakLbl = new Label();
        radniciPetakLbl.setFont(Font.font(24));

        generisiBtn = new Button("Generisi smene");

        HBox ponedeljakHB = new HBox();
        ponedeljakHB.getChildren().addAll(ponedeljakLbl, radniciPonedeljakLbl);
        ponedeljakHB.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        HBox utorakHB = new HBox();
        utorakHB.getChildren().addAll(utorakLbl, radniciUtorakLbl);
        utorakHB.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        HBox sredaHB = new HBox();
        sredaHB.getChildren().addAll(sredaLbl, radniciSredaLbl);
        sredaHB.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        HBox cetvrtakHB = new HBox();
        cetvrtakHB.getChildren().addAll(cetvrtakLbl, radniciCetvrtakLbl);
        cetvrtakHB.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        HBox petakHB = new HBox();
        petakHB.getChildren().addAll(petakLbl, radniciPetakLbl);
        petakHB.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        HBox buttonHB = new HBox();
        buttonHB.getChildren().addAll(generisiBtn);
        buttonHB.setSpacing(10);
        buttonHB.setPadding(new Insets(5,0,0,0));

        this.getChildren().addAll(ponedeljakHB, utorakHB, sredaHB, cetvrtakHB, petakHB, buttonHB);
    }

    private void listeners() {
        generisiBtn.setOnAction(e -> {
            Database.getInstance().getListaDana().put(RadniDani.PONEDELJAK, izaberiRendom());
            radniciPonedeljakLbl.setText(String.valueOf(Database.getInstance().getListaDana().get(RadniDani.PONEDELJAK)));

            Database.getInstance().getListaDana().put(RadniDani.UTORAK, izaberiRendom());
            radniciUtorakLbl.setText(String.valueOf(Database.getInstance().getListaDana().get(RadniDani.UTORAK)));

            Database.getInstance().getListaDana().put(RadniDani.SREDA, izaberiRendom());
            radniciSredaLbl.setText(String.valueOf(Database.getInstance().getListaDana().get(RadniDani.SREDA)));

            Database.getInstance().getListaDana().put(RadniDani.CETVRTAK, izaberiRendom());
            radniciCetvrtakLbl.setText(String.valueOf(Database.getInstance().getListaDana().get(RadniDani.CETVRTAK)));

            Database.getInstance().getListaDana().put(RadniDani.PETAK, izaberiRendom());
            radniciPetakLbl.setText(String.valueOf(Database.getInstance().getListaDana().get(RadniDani.PETAK)));


        });
    }

    private List<String> izaberiRendom(){
        List<String> listaKorisnika = new ArrayList<>();

        int brojac = 0;
        String korisnik1;
        String korisnik2;
        for(Korisnik k : Database.getInstance().getListaKorisnika()){
            if(k.getIdFK() == lv.getKorisnik().getId()){
                mapaKorisnika.put(brojac++, k);
            }
        }

        if(listaBrojeva.size() == mapaKorisnika.size()){
            listaBrojeva.clear();
        }

        Random r = new Random();
        while(true) {
            int broj1 = r.nextInt(mapaKorisnika.size());
            int broj2 = r.nextInt(mapaKorisnika.size());

            if(broj1 != flag1 && broj1 != flag2 && broj2 != flag1 && broj2 != flag2 && broj1 != broj2
                    && !listaBrojeva.contains(broj1) && !listaBrojeva.contains(broj2)){
                korisnik1 = mapaKorisnika.get(broj1).getIme() + " " + mapaKorisnika.get(broj1).getPrezime();
                korisnik2 = mapaKorisnika.get(broj2).getIme() + " " + mapaKorisnika.get(broj2).getPrezime();
                flag1 = broj1;
                flag2 = broj2;
                listaBrojeva.add(broj1);
                listaBrojeva.add(broj2);
                break;
            }
        }
        listaKorisnika.add(korisnik1 + ", " + korisnik2);
        return listaKorisnika;
    }

    public TreeMap<RadniDani, List<String>> getMapaSmena() {
        return mapaSmena;
    }
}
