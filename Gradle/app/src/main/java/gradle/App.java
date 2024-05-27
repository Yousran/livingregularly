package gradle;
import java.io.File;

import gradle.views.LoginScene;
import gradle.views.RegisterScene;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(true);
        LoginScene loginScene = new LoginScene(stage);
        // loginScene.show(primaryStage);
        loginScene.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
