package gradle.views;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import gradle.models.Anggaran;
import gradle.models.Proyek;
import gradle.models.User;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class AddProjectPage extends VBox {
    public AddProjectPage(){
        super();

        HBox titleHBox = new HBox();
        Button btnSave = new Button("save");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);


        Label title = new Label("Add Projek Baru");
        title.getStyleClass().setAll("h2");
        titleHBox.getChildren().addAll(title, spacer, btnSave);

        this.getChildren().addAll(titleHBox,projectDetail());
    }

    public HBox projectDetail() {
        HBox projectDetail = new HBox();
        HBox projectCard = createProjectCard();
        VBox budgetCard = createBudgetCard();
        VBox teamCard = createTeamCard();
        VBox detail = new VBox();
    
        detail.setStyle("-fx-spacing: 2em;");
        detail.getChildren().addAll(projectCard, budgetCard);
    
        projectDetail.setStyle("-fx-spacing: 2em;");
    
        detail.prefWidthProperty().bind(projectDetail.widthProperty().multiply(0.8));
        teamCard.prefWidthProperty().bind(projectDetail.widthProperty().multiply(0.2));
        projectDetail.getChildren().addAll(detail, teamCard);
        
        return projectDetail;
    }
    
    private HBox createProjectCard() {
        HBox projectCard = new HBox(10);
        projectCard.getStyleClass().add("cardProjek");

        TextField nameField = new TextField();
        nameField.setPromptText("Nama Proyek");
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Tanggal");
        TextField timeField = new TextField();
        timeField.setPromptText("Waktu");
        TextField budgetField = new TextField();
        budgetField.setPromptText("Anggaran");

        projectCard.getChildren().addAll(nameField, datePicker, timeField, budgetField);

        return projectCard;
    }
    private TextField itemField = new TextField();
    private TextField priceField = new TextField();
    private TextField totalBudgetField = new TextField();
    private List<Anggaran> tempAnggarans = new ArrayList<>();
    private VBox budgetItems = new VBox(4);
    private Label remainingBudgetLabel = new Label("Remaining Budget: ");

    private VBox createBudgetCard() {
        VBox budgetCard = new VBox();
        budgetCard.getStyleClass().add("cardProjek");
        HBox header = new HBox();
        totalBudgetField.setPromptText("Budget");
        header.getChildren().add(totalBudgetField);
        
        HBox inputFields = new HBox(5);
        Button addBtn = addBudgetBtn();
        inputFields.getChildren().addAll(addBtn, itemField, priceField);
        itemField.setPromptText("Enter item name");
        priceField.setPromptText("Enter price");

        budgetCard.getChildren().addAll(header, inputFields, budgetItems, remainingBudgetLabel);

        displayBudgetItems();
        return budgetCard;
    }
    private void displayBudgetItems() {
        budgetItems.getChildren().clear();
        int totalSpent = 0;

        for (Anggaran anggaran : tempAnggarans) {
            Label itemLabel = new Label(anggaran.getItem() + " : Rp. " + anggaran.getHarga());
            budgetItems.getChildren().add(itemLabel);
            totalSpent += anggaran.getHarga();
        }

        try {
            int initialBudget = Integer.parseInt(totalBudgetField.getText());
            int remainingBudget = initialBudget - totalSpent;
            remainingBudgetLabel.setText("Remaining Budget : Rp. " + remainingBudget);
        } catch (NumberFormatException e) {
            remainingBudgetLabel.setText("Invalid initial budget");
        }
    }

    private Button addBudgetBtn() {
        Button btn = new Button("+");
        btn.setOnAction(event -> addItem());
        return btn;
    }

    private void addItem() {
        String item = itemField.getText();
        String harga = priceField.getText();

        if (!item.isEmpty() && !harga.isEmpty()) {
            try {
                int price = Integer.parseInt(harga);
                Anggaran newAnggaran = new Anggaran(0, item, price);
                tempAnggarans.add(newAnggaran);
                itemField.clear();
                priceField.clear();
                displayBudgetItems();
            } catch (NumberFormatException e) {
                priceField.clear();
                priceField.setPromptText("Invalid number. Please enter a valid price.");
            }
        }
    }

    private TextField teamNameField = new TextField();
    private List<String> teamMembers = new ArrayList<>();
    private VBox teamMembersContainer = new VBox(4);
    
    private VBox createTeamCard() {
        VBox teamCard = new VBox(10);
        teamCard.getStyleClass().add("cardProjek");

        teamNameField.setPromptText("Enter team name");
        Button addMemberBtn = new Button("+");
        addMemberBtn.setOnAction(event -> addMember());

        HBox teamNameContainer = new HBox(5);
        teamNameContainer.getChildren().addAll(teamNameField, addMemberBtn);

        teamCard.getChildren().addAll(teamNameContainer, teamMembersContainer);

        return teamCard;
    }

    private void addMember() {
        String memberName = teamNameField.getText();
        if (!memberName.isEmpty()) {
            teamMembers.add(memberName);
            teamNameField.clear();
            displayTeamMembers();
        }
    }

    private void displayTeamMembers() {
        teamMembersContainer.getChildren().clear();
        for (String memberName : teamMembers) {
            teamMembersContainer.getChildren().add(createMemberCard(memberName));
        }
    }

    private HBox createMemberCard(String memberName) {
        HBox memberCard = new HBox(5);
        memberCard.getStyleClass().add("memberCard");

        ImageView profileImageView = new ImageView(new Image("/icons/user-circle-regular-24.png"));
        profileImageView.setFitHeight(20);
        profileImageView.setFitWidth(20);

        Label memberLabel = new Label(memberName);
        memberLabel.getStyleClass().add("h4");
//
        memberCard.getChildren().addAll(profileImageView, memberLabel);
        return memberCard;
    }
    
}
