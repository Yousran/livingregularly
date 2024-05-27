package gradle.views;

import java.io.File;

import gradle.controllers.UserRegis;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterScene {
    Stage stage;

    public RegisterScene(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        TextField username = new TextField();
        username.getStyleClass().addAll("inputan");
        username.setPromptText("username");
        TextField email = new TextField();
        email.getStyleClass().addAll("inputan");
        email.setPromptText("Email");
        PasswordField pass = new PasswordField();
        pass.getStyleClass().addAll("inputan");
        pass.setPromptText("Password");

        String imagePath = "D:\\Universitas Hasanuddin\\SEMESTER 2\\PRAKTIKUM\\PROJECT\\Gradle\\app\\src\\main\\resources\\img\\icon-profil.png";
        Image image = new Image(new File(imagePath).toURI().toString());
        ImageView timeTuner = new ImageView(image);
        timeTuner.setFitHeight(80);
        timeTuner.setFitWidth(80);

        Button signupBtn = new Button("Sign Up");
        signupBtn.getStyleClass().addAll("btn", "btnRegis");

        VBox containerLogin = new VBox(20);
        VBox containerField = new VBox(10);
        HBox containerBtn = new HBox(10);
        containerBtn.getChildren().addAll(signupBtn);
        containerBtn.setAlignment(Pos.CENTER);

        HBox.setHgrow(signupBtn, Priority.ALWAYS);

        containerField.getChildren().addAll(username, email, pass);
        containerLogin.getChildren().addAll(timeTuner, containerField, containerBtn);
        containerField.getStyleClass().addAll("container");
        containerField.setAlignment(Pos.CENTER);
        containerLogin.setAlignment(Pos.CENTER);
        containerField.setPadding(new Insets(20));
        containerBtn.setPadding(new Insets(0, 5, 0, 5));

        containerLogin.maxWidthProperty().bind(stage.widthProperty().multiply(0.3));
        containerLogin.maxHeightProperty().bind(stage.heightProperty().multiply(0.5));
        // containerLogin.getStyleClass().add("-fx-background-color:black");

        // Menyetel lebar masing-masing komponen untuk memenuhi VBox
        email.setMaxWidth(Double.MAX_VALUE);
        pass.setMaxWidth(Double.MAX_VALUE);

        signupBtn.setMaxWidth(Double.MAX_VALUE);

        signupBtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = username.getText();
                String email1 = email.getText();
                String password = pass.getText();

                boolean hasil = UserRegis.registerUser(name, email1, password);

                if (hasil) {
                    LoginScene loginScene = new LoginScene(stage);
                    loginScene.show();
                } else {
                    // TODO MASIH KURANG
                }
            }
        });

        // Membuat StackPane untuk mengatur posisi VBox di tengah layar
        StackPane root = new StackPane();
        root.getChildren().add(containerLogin);
        root.getStyleClass().addAll("root");
        StackPane.setAlignment(containerLogin, Pos.CENTER);

        // Menyetel scene dan stage
        Scene scene = new Scene(root, 1024, 720);
        scene.getStylesheets().add(getClass().getResource("/Styles/Style.css").toExternalForm());
        stage.setTitle("SignUp Page");
        stage.setScene(scene);
        stage.show();
    }
}
