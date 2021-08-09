package view;

import connections.Connections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Database;
import model.Korisnik;
import model.Uloge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class NapraviProfilView extends GridPane {

    public static Scene makeScene(LoginView loginView){
        return new Scene(new NapraviProfilView(loginView), 300, 400);
    }

    private LoginView lv;

    private Label imeLbl;
    private Label prezimeLbl;
    private Label ulogaLbl;
    private Label usernameLbl;
    private Label passwordLbl;
    private Label potvrdaPasswordLbl;

    private TextField imeTF;
    private TextField prezimeTF;
    private TextField usernameTF;

    private PasswordField passwordPF;
    private PasswordField potvrdaPasswordPF;

    private ComboBox<Uloge> ulogeCB;

    private Button napraviProfilBtn;

    public NapraviProfilView(LoginView loginView){
        lv = loginView;
        elements();
        listeners();
    }

    private void elements() {
        imeLbl = new Label("Ime");
        prezimeLbl = new Label("Prezime");
        ulogaLbl = new Label("Uloga");
        usernameLbl = new Label("Korisnicko ime");
        passwordLbl = new Label("Sifra");
        potvrdaPasswordLbl = new Label("Potvrdite sifru");

        imeTF = new TextField();
        prezimeTF = new TextField();
        usernameTF = new TextField();

        passwordPF = new PasswordField();
        potvrdaPasswordPF = new PasswordField();

        ulogeCB = new ComboBox<>();
        ulogeCB.getItems().addAll(Uloge.values());
        ulogeCB.getSelectionModel().select(0);

        napraviProfilBtn = new Button("Napravi profil");

        this.add(imeLbl, 0, 0);
        this.add(imeTF, 1, 0);

        this.add(prezimeLbl, 0, 1);
        this.add(prezimeTF, 1, 1);

        this.add(ulogaLbl, 0, 2);
        this.add(ulogeCB, 1, 2);

        this.add(usernameLbl, 0, 3);
        this.add(usernameTF, 1, 3);

        this.add(passwordLbl, 0, 4);
        this.add(passwordPF, 1, 4);

        this.add(potvrdaPasswordLbl, 0, 5);
        this.add(potvrdaPasswordPF, 1, 5);

        this.add(napraviProfilBtn, 1, 6);

        this.setVgap(10);
        this.setHgap(10);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(10));
    }

    private void listeners() {
        napraviProfilBtn.setOnAction(e -> {
            Connection c = Connections.getInstance().napraviKonekciju();
            if (passwordPF.getText().equals(potvrdaPasswordPF.getText()) &&
                    !imeTF.getText().isEmpty() || !prezimeTF.getText().isEmpty() ||
                    !usernameTF.getText().isEmpty() || !passwordPF.getText().isEmpty()) {

                if (ulogeCB.getSelectionModel().getSelectedItem().toString().equals("ADMIN")) {
                    try {
                        String query = "insert into Korisnici (ime, prezime, uloga, username, password) values(?, ?, ?, ?, ?)";

                        PreparedStatement ps = c.prepareStatement(query);
                        String ime = imeTF.getText();
                        ps.setString(1, imeTF.getText());

                        String prezime = prezimeTF.getText();
                        ps.setString(2, prezimeTF.getText());

                        String uloga = ulogeCB.getSelectionModel().getSelectedItem().toString();
                        ps.setString(3, ulogeCB.getSelectionModel().getSelectedItem().toString());

                        String username = usernameTF.getText();
                        ps.setString(4, usernameTF.getText());

                        String password = passwordPF.getText();
                        ps.setString(5, passwordPF.getText());

                        Korisnik k = new Korisnik(ime, prezime, uloga, username, password);
                        Database.getInstance().getListaKorisnika().add(k);

                        ps.execute();
                        c.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                if (ulogeCB.getSelectionModel().getSelectedItem().toString().equals("RADNIK")) {
                    try {
                        String query = "insert into Korisnici (ime, prezime, uloga, username, password, adminFK) " +
                                "values(?, ?, ?, ?, ?, ?)";

                        PreparedStatement ps = c.prepareStatement(query);
                        String ime = imeTF.getText();
                        ps.setString(1, imeTF.getText());

                        String prezime = prezimeTF.getText();
                        ps.setString(2, prezimeTF.getText());

                        String uloga = ulogeCB.getSelectionModel().getSelectedItem().toString();
                        ps.setString(3, ulogeCB.getSelectionModel().getSelectedItem().toString());

                        String username = usernameTF.getText();
                        ps.setString(4, usernameTF.getText());

                        String password = passwordPF.getText();
                        ps.setString(5, passwordPF.getText());

                        ps.setInt(6, lv.getKorisnik().getId());

                        Korisnik k = new Korisnik(ime, prezime, uloga, username, password);
                        Database.getInstance().getListaKorisnika().add(k);

                        ps.execute();
                        c.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("GRESKA");
                    alert.setHeaderText("Sifre se ne poklapaju");
                    alert.show();
                }
        });
    }
}
