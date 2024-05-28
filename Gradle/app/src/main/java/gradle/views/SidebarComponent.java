package gradle.views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class SidebarComponent extends VBox {
    private Button btnDashboard, btnAddNewProject, btnSettings, btnLogout;
    private DashboardScene dashboardScene;

    public SidebarComponent(DashboardScene dashboardScene) {
        super();
        this.dashboardScene = dashboardScene;
        this.getStyleClass().add("sidebar");
        loadBtn();
        btnDashboard.fire();
    }

    private void loadBtn() {
        btnDashboard = createBtn("Dashboard", "home-regular-24.png");
        btnAddNewProject = createBtn("Add New Project", "plus-circle-regular-24.png");
        btnSettings = createBtn("Settings", "cog-regular-24.png");
        btnLogout = createBtn("Logout", "log-out-circle-regular-24.png");

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        this.getChildren().addAll(btnDashboard, btnAddNewProject, btnSettings, spacer, btnLogout);
    }

    private Button createBtn(String text, String iconPath) {
        Button button = new Button(text);
        button.getStyleClass().add("sidebar-btn");

        Image image = new Image(getClass().getResourceAsStream("/icons/" + iconPath));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        button.setGraphic(imageView);
        button.setContentDisplay(ContentDisplay.LEFT);

        button.prefWidthProperty().bind(this.widthProperty());
        button.setOnAction(event -> BtnClickHandler(button));
        return button;
    }

    private void BtnClickHandler(Button clickedButton) {
        btnSetFalse();
        clickedButton.getStyleClass().add("active");
        dashboardScene.updateContent(clickedButton.getText());
    }

    private void btnSetFalse() {
        btnDashboard.getStyleClass().remove("active");
        btnAddNewProject.getStyleClass().remove("active");
        btnSettings.getStyleClass().remove("active");
        btnLogout.getStyleClass().remove("active");
    }

    public void bindToParent(Scene scene) {
        this.prefHeightProperty().bind(scene.heightProperty());
        this.prefWidthProperty().bind(scene.widthProperty().multiply(0.2));
    }
}
