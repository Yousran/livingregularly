package gradle.views;

import gradle.controllers.TeamCtrl;
import gradle.models.Proyek;
import gradle.models.User;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class TeamCard extends VBox {
    public TeamCard(Proyek projek) {
        this.getStyleClass().add("cardProjek");

        Label teamNameLabel = new Label(projek.getNamaProjek());
        teamNameLabel.getStyleClass().add("h3");
        this.getChildren().add(teamNameLabel);

        List<User> members = TeamCtrl.getTeamMembers(projek.getProjekId());
        addTeamMembers(members);
    }

    private void addTeamMembers(List<User> members) {
        for (User member : members) {
            this.getChildren().add(createMemberCard(member));
        }
    }

    private HBox createMemberCard(User member) {
        HBox memberCard = new HBox(5);
        memberCard.getStyleClass().add("memberCard");

        ImageView profileImageView = new ImageView();
        Image profileImage = new Image(getClass().getResourceAsStream("/icons/user-circle-regular-24.png"));
        profileImageView.setImage(profileImage);
        profileImageView.setFitHeight(20);
        profileImageView.setFitWidth(20);

        Label memberLabel = new Label(member.getNama());
        memberLabel.getStyleClass().add("h4");

        memberCard.getChildren().addAll(profileImageView, memberLabel);
        return memberCard;
    }
}