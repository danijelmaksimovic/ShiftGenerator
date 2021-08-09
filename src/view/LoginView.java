package view;

import connections.Connections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Database;
import model.Korisnik;
import model.RadniDani;
import sample.Main;

import java.sql.*;
import java.util.List;
import java.util.TreeMap;

public class LoginView extends VBox {

    public static Scene makeScene(){
        return new Scene(new LoginView(), 300,300);
    }

    private Label nazivLbl;
    private Label usernameLbl;
    private Label passwordLbl;

    private TextField usernameTF;
    private PasswordField passwordPF;

    private Button loginBtn;

    private Korisnik korisnik;

    private TreeMap<RadniDani, List<String>> mapaDana;

    public LoginView() {
        elements();
        listeners();
    }

    private void elements() {
        nazivLbl = new Label("Shift Generator");
        usernameLbl = new Label("Korisnicko ime: ");
        passwordLbl = new Label("Sifra: ");

        usernameTF = new TextField();
        passwordPF = new PasswordField();

        loginBtn = new Button("Ulogujte se");

        VBox usernameVB = new VBox();
        usernameVB.getChildren().addAll(usernameLbl, usernameTF);
        usernameVB.setSpacing(5);
        usernameVB.setPadding(new Insets(20, 5, 5, 5));

        VBox passwordVB = new VBox();
        passwordVB.getChildren().addAll(passwordLbl, passwordPF);
        passwordVB.setSpacing(5);
        passwordVB.setPadding(new Insets(5));

        this.getChildren().addAll(nazivLbl, usernameVB, passwordVB, loginBtn);
        this.setPadding(new Insets(10));
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
    }

    private void listeners() {
        loginBtn.setOnAction(e -> {
            for(Korisnik k : Database.getInstance().getListaKorisnika()){
                if(k.getUsername().equals(usernameTF.getText()) && k.getPassword().equals(passwordPF.getText())){
                    korisnik = k;
                    if(k.getUloga().equals("ADMIN")) Main.window.setScene(AdminView.makeScene(this));
                    else Main.window.setScene(RadnikView.makeScene(this));
                    break;
                }
            }
            if(korisnik == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("GRESKA");
                alert.setHeaderText("Pogresni kredencijali");
                alert.show();
            }
        });
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }
}
