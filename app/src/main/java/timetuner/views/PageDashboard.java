package timetuner.views;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;
import timetuner.controllers.ProjectController;
import timetuner.models.Project;

public class PageDashboard extends VBox {

    public PageDashboard() {
        super();
        this.getStyleClass().add("container");
        Label subTitle = new Label("Project List");
        subTitle.getStyleClass().add("h3");
        this.getChildren().add(subTitle);

        VBox projectList = new VBox();
        projectList.setStyle("-fx-padding: 0 1em; -fx-spacing: 1em;-fx-background-color:-color-background;");
        projectList.setSpacing(10);

        if (ProjectController.getAllProjects().isEmpty()) {
            Label noProjectsLabel = new Label("No projects available. Please add a new project.");
            noProjectsLabel.getStyleClass().add("h3");
            projectList.getChildren().add(noProjectsLabel);
        } else {
            for (Project project : ProjectController.getAllProjects()) {
                CardProject card = new CardProject(project);
                projectList.getChildren().add(card);
            }
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(projectList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color:transparent;");
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
        this.getChildren().add(scrollPane);
    }
}