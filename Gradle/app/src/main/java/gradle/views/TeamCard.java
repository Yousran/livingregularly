package gradle.views;

import gradle.models.Anggaran;
import gradle.models.Proyek;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;

public class TeamCard extends VBox{
    public TeamCard(Proyek projek){
        this.getStyleClass().add("cardProjek");
        
        Label totalBudgetLabel = new Label("Budget : Rp. ");
        this.getChildren().add(totalBudgetLabel);
    }
}
