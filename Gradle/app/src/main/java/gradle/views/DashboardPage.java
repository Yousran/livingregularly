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
    public DashboardPage(int UserId){
        super();

        Label title = new Label("Dashboard");
        title.getStyleClass().setAll("h2");
        this.setStyle("-fx-spacing : 1.2em");

        this.getChildren().addAll(title,projectList(UserId));
    }

    public VBox projectList(int UserId){
        VBox projectList = new VBox();
        Label label = new Label("Project List");

        Image image = new Image(getClass().getResourceAsStream("/icons/" + "calendar-regular-24.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);

        label.getStyleClass().add("subTitle");
        HBox subTitle = new HBox(imageView,label);
        
        VBox cardContainer = new VBox(10);

        for (Proyek projek : ProyekCtrl.getAllProjek(UserId)) {
            cardContainer.getChildren().add(new ProjectCard(projek));
        }        
        //TODO : saat scrollpane ditambahkan maka warna scrollpane tersebut tidak bisa diubah.sehingga tampilan kurang baiks
        // ScrollPane scrollPane = new ScrollPane(cardContainer);
        // scrollPane.getStyleClass().add("scroll-pane");
        // scrollPane.setFitToWidth(true);
        
        projectList.getChildren().addAll(subTitle,cardContainer);
        projectList.setStyle("-fx-spacing : 1.5em");
        return projectList;
    }

}
