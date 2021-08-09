package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Database;
import model.Korisnik;

import java.util.ArrayList;
import java.util.List;

public class PregledProfilaView extends HBox {

    LoginView lv;

    public static Scene makeScene(LoginView loginView){
        return new Scene(new PregledProfilaView(loginView), 500, 500);
    }

    private ListView<String> korisniciLV;

    private VBox infoVB;

    private Label imeLbl;
    private Label prezimeLbl;
    private Label ulogaLbl;
    private Label usernameLbl;

    public PregledProfilaView(LoginView loginView){
        lv = loginView;
        elements();
        listeners();
    }

    private void elements() {
        korisniciLV = new ListView<>();
        for(Korisnik k : Database.getInstance().getListaKorisnika()){
            if(k.getUloga().equals("RADNIK") && k.getIdFK() == lv.getKorisnik().getId()){
                String korisnik = k.getIme() + " " + k.getPrezime();
                korisniciLV.getItems().add(korisnik);
            }
        }

        imeLbl = new Label();
        prezimeLbl = new Label();
        ulogaLbl = new Label();
        usernameLbl = new Label();

        infoVB = new VBox();
        infoVB.getChildren().addAll(imeLbl, prezimeLbl, ulogaLbl, usernameLbl);
        infoVB.setSpacing(10);

        this.getChildren().addAll(korisniciLV, infoVB);
        this.setPadding(new Insets(10));
        this.setSpacing(10);
    }

    private void listeners() {
        korisniciLV.setOnMouseClicked(e ->{
            for(Korisnik k : Database.getInstance().getListaKorisnika()){
                if(korisniciLV.getSelectionModel().getSelectedItem().contains(k.getIme()) &&
                korisniciLV.getSelectionModel().getSelectedItem().contains(k.getPrezime())){
                    imeLbl.setText(k.getIme());
                    prezimeLbl.setText(k.getPrezime());
                    ulogaLbl.setText(k.getUloga());
                    usernameLbl.setText(k.getUsername());
                }
            }
        });
    }

}
