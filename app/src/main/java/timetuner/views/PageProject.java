package timetuner.views;

import java.time.LocalDate;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import timetuner.SelfUtils;
import timetuner.controllers.BudgetController;
import timetuner.controllers.ProjectController;
import timetuner.controllers.TeamMemberController;
import timetuner.controllers.UserController;
import timetuner.models.Budget;
import timetuner.models.Project;
import timetuner.models.User;
import timetuner.App;

public class PageProject extends VBox implements InterfacePageProject {
    Project project;
    VBox budgetListVBox = new VBox();
    VBox teamListVBox = new VBox();
    Label remainingBudget;
    TextField usernameField = new TextField();
    TextField budgetNameField = new TextField();
    TextField budgetPriceField = new TextField();
    TextField editProjectNameField = new TextField();
    DatePicker editDueDatePicker = new DatePicker();
    TextField editTotalBudgetField = new TextField();
    boolean isEditMode = false;

    public PageProject(Project project) {
        super();
        this.project = project;
        HBox hBox = new HBox();
        VBox vBox = new VBox(projectStatus(), budgetStatus());
        vBox.setStyle("-fx-spacing: 1em;");
        remainingBudget.getStyleClass().add("h5");

        vBox.prefWidthProperty().bind(hBox.widthProperty().multiply(0.70));
        teamStatus().prefWidthProperty().bind(hBox.widthProperty().multiply(0.30));

        hBox.getStyleClass().add("container");
        hBox.getChildren().addAll(vBox, teamStatus());

        Label subTitleLabel = new Label("Detail Project");
        subTitleLabel.getStyleClass().add("h3");

        Button toggleEditButton = new Button("Edit");
        toggleEditButton.getStyleClass().add("btn");
        toggleEditButton.setOnAction(event -> toggleEditMode(toggleEditButton));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox subTitle = new HBox(subTitleLabel, spacer, toggleEditButton);
        subTitle.getStyleClass().add("container");

        this.getChildren().addAll(subTitle, hBox);

        editTotalBudgetField.addEventFilter(KeyEvent.KEY_TYPED, this::validateTotalBudgetField);
        editDueDatePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });
    }

    private void toggleEditMode(Button toggleEditButton) {
        isEditMode = !isEditMode;
        if (isEditMode) {
            toggleEditButton.setText("Save");
            enableEditing(true);
        } else {
            toggleEditButton.setText("Edit");
            saveProjectDetails();
            enableEditing(false);
        }
    }

    private void enableEditing(boolean enable) {
        this.getChildren().clear();

        Label subTitleLabel = new Label("Detail Project");
        subTitleLabel.getStyleClass().add("h3");

        Button toggleEditButton = new Button(enable ? "Save" : "Edit");
        toggleEditButton.getStyleClass().add("btn");
        toggleEditButton.setOnAction(event -> toggleEditMode(toggleEditButton));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox subTitle = new HBox(subTitleLabel, spacer, toggleEditButton);
        subTitle.getStyleClass().add("container");

        this.getChildren().add(subTitle);

        HBox hBox = new HBox();
        VBox vBox = new VBox(enable ? projectEditFields() : projectStatus(), budgetStatus());
        vBox.setStyle("-fx-spacing: 1em;");

        vBox.prefWidthProperty().bind(hBox.widthProperty().multiply(0.70));
        teamStatus().prefWidthProperty().bind(hBox.widthProperty().multiply(0.30));

        hBox.getStyleClass().add("container");
        hBox.getChildren().addAll(vBox, teamStatus());

        this.getChildren().add(hBox);
    }

    private HBox projectEditFields() {
        HBox hbox = new HBox(10);
        hbox.getStyleClass().add("card");

        editProjectNameField.setText(project.getProject_name());
        editDueDatePicker.setValue(LocalDate.parse(project.getDue_date()));
        editTotalBudgetField.setText(String.valueOf(project.getBudget()));

        Label labelProjectName = new Label("Project Name");
        labelProjectName.getStyleClass().add("h5");
        VBox vboxName = new VBox(labelProjectName, editProjectNameField);

        Label labelDueDate = new Label("Due Date");
        labelDueDate.getStyleClass().add("h5");
        VBox vboxDueDate = new VBox(labelDueDate, editDueDatePicker);

        Label labelTotalBudget = new Label("Total Budget");
        labelTotalBudget.getStyleClass().add("h5");
        VBox vboxTotalBudget = new VBox(labelTotalBudget, editTotalBudgetField);

        hbox.getChildren().addAll(vboxName, vboxDueDate, vboxTotalBudget);
        return hbox;
    }

    @Override
    public HBox projectStatus() {
        HBox hbox = new HBox(10);
        hbox.getStyleClass().add("card");
        hbox.getChildren().addAll(
            createPropertySection("Project Name", project.getProject_name()),
            createPropertySection("Due Date", project.getDue_date()),
            createPropertySection("Time Left", SelfUtils.calculateTimeLeft(project.getDue_date()))
        );
        return hbox;
    }

    @Override
    public VBox teamStatus() {
        VBox teamStatus = new VBox();
        teamStatus.getStyleClass().add("card");

        Label subTitleLabel = new Label("Team Members:");
        subTitleLabel.getStyleClass().add("h4");

        usernameField.setPromptText("Enter Username");
        usernameField.getStyleClass().add("text-field");

        Button addMemberBtn = new Button();
        addMemberBtn.getStyleClass().add("btn-icon");
        Image image = new Image(getClass().getResourceAsStream("/icons/user-plus-solid-240.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        addMemberBtn.setGraphic(imageView);
        addMemberBtn.setOnAction(event -> addNewTeamMember());

        HBox field = new HBox(addMemberBtn, usernameField);

        teamList().setStyle("-fx-background-color:-color-card;");
        ScrollPane scrollPane = new ScrollPane(teamList());
        scrollPane.setStyle("-fx-background-color:transparent;");
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        teamStatus.getChildren().addAll(subTitleLabel, field, scrollPane);
        return teamStatus;
    }

    @Override
    public VBox budgetStatus() {
        VBox budgetStatus = new VBox();
        budgetStatus.getStyleClass().add("card");

        Label totalBudget = new Label("Budget : " + SelfUtils.intToRupiah(project.getBudget()));
        totalBudget.getStyleClass().add("h5");
        HBox headHBox = new HBox(totalBudget);

        budgetNameField.setPromptText("Budget Name");
        budgetPriceField.setPromptText("Value");

        Button addBudgetBtn = new Button();
        addBudgetBtn.getStyleClass().add("btn-icon");
        Image image = new Image(getClass().getResourceAsStream("/icons/list-plus-regular-240.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        addBudgetBtn.setGraphic(imageView);
        addBudgetBtn.setOnAction(event -> addNewBudgetHandler());

        HBox addBudget = new HBox(addBudgetBtn, budgetNameField, budgetPriceField);
        remainingBudget = new Label("Remaining Budget : " + SelfUtils.intToRupiah(SelfUtils.calculateBudget(project.getBudget(), BudgetController.getBudgets(project.getId()))));

        budgetList().setStyle("-fx-background-color:-color-card;");
        ScrollPane scrollPane = new ScrollPane(budgetList());
        scrollPane.setStyle("-fx-background-color:transparent;");
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        budgetStatus.getChildren().addAll(headHBox, addBudget, scrollPane, remainingBudget);
        return budgetStatus;
    }

    @Override
    public VBox teamList() {
        teamListVBox.getChildren().clear();
        List<User> users = TeamMemberController.getUsers(project.getId());
        for (User user : users) {
            teamListVBox.getChildren().add(teamMember(user));
        }
        return teamListVBox;
    }

    @Override
    public HBox teamMember(User user) {
        HBox teamMember = new HBox();
        teamMember.getStyleClass().add("container");
        if (!user.getUsername().equals(App.loggedUser.getUsername())) {
            Button deleteBtn = new Button();
            deleteBtn.getStyleClass().add("btn-icon");
            Image deleteImage = new Image(getClass().getResourceAsStream("/icons/minus-circle-regular-240.png"));
            ImageView deleteImageView = new ImageView(deleteImage);
            deleteImageView.setFitWidth(20);
            deleteImageView.setFitHeight(20);
            deleteBtn.setGraphic(deleteImageView);
            deleteBtn.setOnAction(event -> deleteTeamMemberHandler(user));
            teamMember.getChildren().add(deleteBtn);
        } else {
            Button deleteBtn = new Button();
            deleteBtn.getStyleClass().add("btn-icon");
            Image deleteImage = new Image(getClass().getResourceAsStream("/icons/user-circle-regular-240.png"));
            ImageView deleteImageView = new ImageView(deleteImage);
            deleteImageView.setFitWidth(20);
            deleteImageView.setFitHeight(20);
            deleteBtn.setGraphic(deleteImageView);
            teamMember.getChildren().add(deleteBtn);
        }

        Label username = new Label(user.getUsername());
        username.getStyleClass().add("h5");

        HBox.setHgrow(username, Priority.ALWAYS);

        teamMember.getChildren().add(username);
        return teamMember;
    }

    @Override
    public VBox budgetList() {
        budgetListVBox.getChildren().clear();
        List<Budget> budgets = BudgetController.getBudgets(project.getId());
        for (Budget budget : budgets) {
            budgetListVBox.getChildren().add(budgetItem(budget));
        }
        return budgetListVBox;
    }

    public HBox budgetItem(Budget budget) {
        HBox budgetItem = new HBox();
        budgetItem.getStyleClass().add("container");

        Button editBtn = new Button();
        editBtn.getStyleClass().add("btn-icon");
        Image editImage = new Image(getClass().getResourceAsStream("/icons/calendar-edit-regular-240.png"));
        ImageView editImageView = new ImageView(editImage);
        editImageView.setFitWidth(20);
        editImageView.setFitHeight(20);
        editBtn.setGraphic(editImageView);
        editBtn.setOnAction(event -> editBudgetHandler(budget, budgetItem));

        Button deleteBtn = new Button();
        deleteBtn.getStyleClass().add("btn-icon");
        Image deleteImage = new Image(getClass().getResourceAsStream("/icons/minus-circle-regular-240.png"));
        ImageView deleteImageView = new ImageView(deleteImage);
        deleteImageView.setFitWidth(20);
        deleteImageView.setFitHeight(20);
        deleteBtn.setGraphic(deleteImageView);
        deleteBtn.setOnAction(event -> deleteBudgetHandler(budget));

        Label budgetLabel = new Label(budget.getBudget_name() + " : " + SelfUtils.intToRupiah(budget.getPrice()));
        budgetLabel.getStyleClass().add("h5-thin");

        HBox.setHgrow(budgetLabel, Priority.ALWAYS);

        budgetItem.getChildren().addAll(editBtn, deleteBtn, budgetLabel);
        return budgetItem;
    }

    @Override
    public void addNewTeamMember() {
        String username = usernameField.getText();
        if (username == null || username.isEmpty()) {
            usernameField.clear();
            usernameField.getStyleClass().add("error");
            usernameField.setPromptText("Username is required");
            usernameField.setOnKeyTyped(event -> usernameField.getStyleClass().remove("error"));
            return;
        }

        User user = UserController.findUser(username);
        if (user != null) {
            if (TeamMemberController.isMemberExists(project.getId(), user.getId())) {
                usernameField.clear();
                usernameField.getStyleClass().add("error");
                usernameField.setPromptText("User already in team");
                usernameField.setOnKeyTyped(event -> usernameField.getStyleClass().remove("error"));
                return;
            }
            TeamMemberController.addMember(project.getId(), user.getId());
            refreshTeamList();
        } else {
            usernameField.clear();
            usernameField.getStyleClass().add("error");
            usernameField.setPromptText("User Not Found");
            usernameField.setOnKeyTyped(event -> usernameField.getStyleClass().remove("error"));
        }
    }

    @Override
    public void refreshTeamList() {
        teamList();
    }

    @Override
    public void addNewBudgetHandler() {
        boolean isEmptyField = false;

        if (budgetNameField.getText().isEmpty()) {
            budgetNameField.getStyleClass().add("error");
            budgetNameField.setPromptText("Budget name is required");
            isEmptyField = true;
        }

        if (budgetPriceField.getText().isEmpty()) {
            budgetPriceField.getStyleClass().add("error");
            budgetPriceField.setPromptText("Budget price is required");
            isEmptyField = true;
        } else {
            try {
                Integer.parseInt(budgetPriceField.getText());
            } catch (NumberFormatException e) {
                budgetPriceField.getStyleClass().add("error");
                budgetPriceField.clear();
                budgetPriceField.setPromptText("Must be a number");
                isEmptyField = true;
            }
        }

        if (isEmptyField) {
            budgetNameField.setOnKeyTyped(event -> budgetNameField.getStyleClass().remove("error"));
            budgetPriceField.setOnKeyTyped(event -> budgetPriceField.getStyleClass().remove("error"));
            return;
        }

        BudgetController.addBudget(project.getId(), budgetNameField.getText(), Integer.parseInt(budgetPriceField.getText()));
        refreshBudgetList();

        budgetNameField.clear();
        budgetPriceField.clear();
    }

    @Override
    public void refreshBudgetList() {
        budgetList();
        int updatedRemainingBudget = SelfUtils.calculateBudget(project.getBudget(), BudgetController.getBudgets(project.getId()));
        remainingBudget.setText("Remaining Budget : " + SelfUtils.intToRupiah(updatedRemainingBudget));
    }

    private HBox createPropertySection(String title, String value) {
        HBox hbox = new HBox();

        VBox vbox = new VBox();
        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("h5");
        Label valueLabel = new Label(value);
        valueLabel.getStyleClass().add("h5-thin");

        vbox.getChildren().addAll(titleLabel, valueLabel);

        Region spacerLeft = new Region();
        HBox.setHgrow(spacerLeft, Priority.ALWAYS);
        Region spacerRight = new Region();
        HBox.setHgrow(spacerRight, Priority.ALWAYS);

        hbox.getChildren().addAll(spacerLeft, vbox, spacerRight);
        return hbox;
    }

    private void editBudgetHandler(Budget budget, HBox budgetItem) {
        TextField editNameField = new TextField(budget.getBudget_name());
        TextField editPriceField = new TextField(String.valueOf(budget.getPrice()));
        Button saveEditBtn = new Button("Save Edit");
        saveEditBtn.getStyleClass().add("btn");
        saveEditBtn.setOnAction(event -> saveEditBudget(budget, editNameField, editPriceField));

        budgetItem.getChildren().clear();
        budgetItem.getChildren().addAll(editNameField, editPriceField, saveEditBtn);
    }

    private void saveEditBudget(Budget budget, TextField editNameField, TextField editPriceField) {
        String newName = editNameField.getText();
        int newPrice;
        try {
            newPrice = Integer.parseInt(editPriceField.getText());
        } catch (NumberFormatException e) {
            editPriceField.getStyleClass().add("error");
            editPriceField.setPromptText("Must be a number");
            editPriceField.setOnKeyTyped(event -> editPriceField.getStyleClass().remove("error"));
            return;
        }

        budget.setBudget_name(newName);
        budget.setPrice(newPrice);
        BudgetController.updateBudget(budget.getId(), newName, newPrice);
        refreshBudgetList();
    }

    private void deleteBudgetHandler(Budget budget) {
        BudgetController.deleteBudget(budget.getId());
        refreshBudgetList();
    }

    private void deleteTeamMemberHandler(User user) {
        TeamMemberController.deleteMember(project.getId(), user.getId());
        refreshTeamList();
    }

    private void saveProjectDetails() {
        project.setProject_name(editProjectNameField.getText());
        project.setDue_date(editDueDatePicker.getValue().toString());
        project.setBudget(Integer.parseInt(editTotalBudgetField.getText()));

        ProjectController.updateProject(project.getId(), project.getProject_name(), project.getDue_date(), project.getBudget());

        refreshProjectView();
    }

    private void refreshProjectView() {
        this.getChildren().clear();

        Label subTitleLabel = new Label("Detail Project");
        subTitleLabel.getStyleClass().add("h3");

        Button toggleEditButton = new Button("Edit");
        toggleEditButton.getStyleClass().add("btn");
        toggleEditButton.setOnAction(event -> toggleEditMode(toggleEditButton));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox subTitle = new HBox(subTitleLabel, spacer, toggleEditButton);
        subTitle.getStyleClass().add("container");

        this.getChildren().add(subTitle);

        HBox hBox = new HBox();
        VBox vBox = new VBox(projectStatus(), budgetStatus());
        vBox.setStyle("-fx-spacing: 1em;");

        vBox.prefWidthProperty().bind(hBox.widthProperty().multiply(0.70));
        teamStatus().prefWidthProperty().bind(hBox.widthProperty().multiply(0.30));

        hBox.getStyleClass().add("container");
        hBox.getChildren().addAll(vBox, teamStatus());

        this.getChildren().add(hBox);
    }

    private void validateTotalBudgetField(KeyEvent event) {
        String input = event.getCharacter();
        if (!input.matches("[0-9]")) {
            event.consume();
        }
        String currentText = editTotalBudgetField.getText() + input;
        try {
            if (Integer.parseInt(currentText) < 0) {
                event.consume();
            }
        } catch (NumberFormatException e) {
        }
    }
}