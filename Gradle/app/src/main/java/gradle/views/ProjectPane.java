package gradle.views;

import gradle.controllers.AnggaranCtrl;
import gradle.controllers.ProyekCtrl;
import gradle.models.Anggaran;
import gradle.models.Proyek;
import gradle.models.Team;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;

public class ProjectPane extends VBox{
    public ProjectPane(Proyek proyek){
        super();

        Label title = new Label(proyek.getNamaProjek());
        title.getStyleClass().setAll("h2");
        // this.getStyleClass().setAll("btnLogin");
        this.setStyle("-fx-spacing : 2em");
        this.getChildren().addAll(title,projectDetail(proyek));
    }

    public HBox projectDetail(Proyek proyek){
        HBox projectDetail = new HBox();
        ProjectCard projectCard = new ProjectCard(proyek);
        BudgetCard budgetCard = new BudgetCard(proyek);
        TeamCard teamCard = new TeamCard(proyek);
        VBox detail = new VBox();

        detail.setStyle("-fx-spacing: 2em;");
        projectCard.getStyleClass().add("CardPane1");
        budgetCard.getStyleClass().add("CardPane1");
        teamCard.getStyleClass().add("CardPane1");
        detail.getChildren().addAll(projectCard,budgetCard);



        projectDetail.setStyle("-fx-spacing: 2em;");

        detail.prefWidthProperty().bind(projectDetail.widthProperty().multiply(0.8));
        teamCard.prefWidthProperty().bind(projectDetail.widthProperty().multiply(0.2));
        projectDetail.getChildren().addAll(detail,teamCard);

        return projectDetail;
    }
}
