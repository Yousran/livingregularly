package gradle.views;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DashboardScene {
    Stage stage;
    public static VBox content;
    private int UserId;
    
    public DashboardScene(Stage stage) {
        this.stage = stage;
        content = new VBox();
    }
    public void show(int UserId) {
        this.UserId = UserId;

        VBox body = new VBox();
        NavbarComponent navbar = new NavbarComponent();
        HBox main = new HBox();
        SidebarComponent sidebar = new SidebarComponent(this);
        
        StackPane root = new StackPane();
        root.getChildren().add(body);
        StackPane.setAlignment(body, Pos.CENTER);
        
        Scene scene = new Scene(root, 1024, 720);
        navbar.bindToParent(scene);
        sidebar.bindToParent(scene);
        content.getStyleClass().add("content");
        content.prefWidthProperty().bind(scene.widthProperty().multiply(0.8));
        
        main.getChildren().addAll(sidebar, content);
        body.getChildren().addAll(navbar, main);

        scene.getStylesheets().add(getClass().getResource("/Styles/Style.css").toExternalForm());
        stage.setTitle("Dashboard Page");
        stage.setScene(scene);
        stage.show();
    }

    public void updateContent(String page) {
        content.getChildren().clear();
        
        switch (page) {
            case "Dashboard":
                content.getChildren().add(new DashboardPage(UserId));
                break;
            case "Add New Project":
                content.getChildren().add(new Label("Add a new project here"));
                break;
            case "Settings":
                content.getChildren().add(new Label("Modify settings here"));
                break;
            case "Logout":
                UserId = 0;
                LoginScene loginScene = new LoginScene(stage);
                loginScene.show();;
                break;
        }
    }

}
