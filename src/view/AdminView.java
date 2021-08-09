package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.stage.Stage;
import sample.Main;

public class AdminView extends VBox {

    LoginView lv;

    public static Scene makeScene(LoginView loginView){
        return new Scene(new AdminView(loginView), 300, 300);
    }

    private Label podaciLbl;

    private Button napraviProfilBtn;
    private Button pregledKorisnikaBtn;
    private Button napraviSmeneBtn;
    private Button logoutBtn;

    public AdminView(LoginView loginView){
        lv = loginView;
        elements();
        listeners();
    }

    private void elements() {
        podaciLbl = new Label();
        podaciLbl.setText(lv.getKorisnik().toString());

        napraviProfilBtn = new Button("Napravi novi profil");
        napraviProfilBtn.setAlignment(Pos.CENTER);

        pregledKorisnikaBtn = new Button("Pregled korisnika");

        napraviSmeneBtn = new Button("Napravi smene");

        logoutBtn = new Button("Izlogujte se");
        VBox logoutVB = new VBox();
        logoutVB.getChildren().addAll(logoutBtn);
        logoutVB.setAlignment(Pos.BOTTOM_RIGHT);


        this.getChildren().addAll(podaciLbl, napraviProfilBtn, pregledKorisnikaBtn, napraviSmeneBtn, logoutVB);
        this.setSpacing(10);
        this.setPadding(new Insets(10));
    }

    private void listeners() {
        napraviProfilBtn.setOnAction(e -> {
            Stage stage = new Stage();
            stage.setTitle("Napravi profil");
            stage.setScene(NapraviProfilView.makeScene(lv));
            stage.show();
        });

        pregledKorisnikaBtn.setOnAction(e -> {
            Stage stage = new Stage();
            stage.setTitle("Pregled profila");
            stage.setScene(PregledProfilaView.makeScene(lv));
            stage.show();
        });

        napraviSmeneBtn.setOnAction(e -> {
            Stage stage = new Stage();
            stage.setTitle("Smene");
            stage.setScene(NapraviSmeneView.makeScene(lv));
            stage.show();
        });

        logoutBtn.setOnAction(e -> {
            Main.window.setScene(LoginView.makeScene());
        });
    }
}
