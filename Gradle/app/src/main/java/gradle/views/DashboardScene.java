package gradle.views;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DashboardScene {
    Stage stage;
    public DashboardScene(Stage stage){
        this.stage = stage;
    }
    public void show() {
        VBox body = new VBox();
        HBox navbar = new HBox();
        HBox main = new HBox();
        VBox sidebar = new VBox();
        VBox content = new VBox();

        // Mengatur warna menggunakan setStyle
        navbar.setStyle("-fx-background-color: #333333;"); // Dark gray
        sidebar.setStyle("-fx-background-color: #CCCCCC;"); // Light gray
        content.setStyle("-fx-background-color: #FFFFFF;"); // White
        body.setStyle("-fx-background-color: black;");

        main.getChildren().addAll(sidebar, content);
        body.getChildren().addAll(navbar, main);

        StackPane root = new StackPane();
        root.getChildren().add(body);
        StackPane.setAlignment(body, Pos.CENTER);

        Scene scene = new Scene(root, 1024, 720);
        stage.setTitle("Dashboard Page");
        stage.setScene(scene);
        stage.show();

        // Mengatur ukuran komponen berdasarkan persentase
        navbar.prefWidthProperty().bind(scene.widthProperty());
        navbar.prefHeightProperty().bind(scene.heightProperty().multiply(0.1)); // 10% dari tinggi scene

        sidebar.prefWidthProperty().bind(scene.widthProperty().multiply(0.2)); // 20% dari lebar scene
        content.prefWidthProperty().bind(scene.widthProperty().multiply(0.8));

        main.prefHeightProperty().bind(scene.heightProperty().multiply(0.9)); // 90% dari tinggi scene
    }
}
