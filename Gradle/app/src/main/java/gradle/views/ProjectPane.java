package gradle.views;

import gradle.controllers.ProyekCtrl;
import gradle.models.Proyek;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProjectPane extends VBox{
    public ProjectPane(Proyek proyek){
        super();

        Label title = new Label(proyek.getNamaProjek());
        title.getStyleClass().setAll("h2");

        this.getChildren().addAll(title);
    }
}
