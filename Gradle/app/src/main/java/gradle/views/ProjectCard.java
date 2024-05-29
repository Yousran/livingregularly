package gradle.views;

import gradle.models.Proyek;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProjectCard extends Button{
    public ProjectCard(Proyek projek) {
        HBox cardContent = new HBox();
        cardContent.getStyleClass().add("card-content");
        
        Label nameLabel = new Label(projek.getNamaProjek());
        nameLabel.getStyleClass().add("name-label");

        // Label teamLabel = new Label("Team: " + projek.getMyTeam());
        Label dateLabel = new Label("Date: " + projek.getTanggal());
        Label timeLabel = new Label("Time: " + projek.getTanggal());
        Label budgetLabel = new Label("Budget: Rp. " + projek.getPengeluaran());
        
        // cardContent.getChildren().addAll(nameLabel, teamLabel, dateLabel, timeLabel, budgetLabel);
        cardContent.getChildren().addAll(nameLabel, dateLabel, timeLabel, budgetLabel);
        this.setGraphic(cardContent);
        
        this.getStyleClass().addAll("cardProjek");
        setMaxWidth(Double.MAX_VALUE);
        this.setOnAction(e -> {
            System.out.println("Project " + projek.getNamaProjek() + " selected");
            DashboardScene.content.getChildren().clear();
            DashboardScene.content.getChildren().add(new ProjectPane(projek));
        });
    }
    //TODO : buatkan mini card sehingga card dapat memiliki banyak versii
}
