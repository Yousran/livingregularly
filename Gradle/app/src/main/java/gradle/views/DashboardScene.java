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

        navbar.getStyleClass().add("navbar");
        sidebar.getStyleClass().add("sidebar");
        content.setStyle("-fx-background-color: #FFFFFF;");

        main.getChildren().addAll(sidebar, content);
        body.getChildren().addAll(navbar, main);

        StackPane root = new StackPane();
        root.getChildren().add(body);
        StackPane.setAlignment(body, Pos.CENTER);

        Scene scene = new Scene(root, 1024, 720);
        stage.setTitle("Dashboard Page");
        scene.getStylesheets().add(getClass().getResource("/Styles/Style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

        navbar.prefHeightProperty().bind(scene.heightProperty().multiply(0.1));
        main.prefHeightProperty().bind(scene.heightProperty().multiply(0.9));
        
        navbar.prefWidthProperty().bind(scene.widthProperty());
        sidebar.prefWidthProperty().bind(scene.widthProperty().multiply(0.2));
        content.prefWidthProperty().bind(scene.widthProperty().multiply(0.8));

    }
}
