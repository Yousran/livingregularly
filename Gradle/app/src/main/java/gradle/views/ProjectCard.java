package gradle.views;

import gradle.models.Proyek;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class ProjectCard extends Button{
    public ProjectCard(Proyek projek) {
        super(projek.getNamaProjek());
        this.getStyleClass().addAll("cardProjek");
        setMaxWidth(Double.MAX_VALUE);
        this.setOnAction(e -> {
            System.out.println("Project " + projek.getNamaProjek() + " selected");
            //TODO buka projec tertentuu
        });
    }
}
