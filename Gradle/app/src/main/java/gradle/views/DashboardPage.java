package gradle.views;

import gradle.controllers.ProyekCtrl;
import gradle.models.Proyek;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DashboardPage extends VBox{
    public DashboardPage(){
        super();

        Label title = new Label("Dashboard");
        title.getStyleClass().setAll("h2");

        this.getChildren().addAll(title,projectList());
    }

    public VBox projectList(){
        VBox projectList = new VBox();
        Label label = new Label("Project List");

        Image image = new Image(getClass().getResourceAsStream("/icons/" + "calendar-regular-24.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);

        label.getStyleClass().add("subTitle");
        HBox subTitle = new HBox(imageView,label);
        
        VBox cardContainer = new VBox(10);
        cardContainer.setStyle("-fx-background-color: rgba(0,0,0,0)");

        for (Proyek projek : ProyekCtrl.getAllProjek()) {
            cardContainer.getChildren().add(new ProjectCard(projek));
        }        
        ScrollPane scrollPane = new ScrollPane(cardContainer);
        scrollPane.setStyle("-fx-background-color: rgba(0,0,0,0)");
        scrollPane.setFitToWidth(true);
        
        projectList.getChildren().addAll(subTitle,scrollPane);
        return projectList;
    }

}