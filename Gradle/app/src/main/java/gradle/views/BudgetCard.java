package gradle.views;

import gradle.controllers.AnggaranCtrl;
import gradle.models.Anggaran;
import gradle.models.Proyek;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.List;

public class BudgetCard extends VBox {
    private TextField itemField = new TextField();
    private TextField priceField = new TextField();
    private int projek_id;
    private int initialBudget;
    private VBox budgetItems = new VBox(4);
    private Label remainingBudgetLabel;

    public BudgetCard(Proyek projek) {
        this.getStyleClass().add("cardProjek");
        this.projek_id = projek.getProjekId();
        this.initialBudget = projek.getPengeluaran();

        HBox header = new HBox();
        Label totalBudgetLabel = new Label("Budget : Rp. " + initialBudget);
        totalBudgetLabel.getStyleClass().add("h3");
        header.getChildren().addAll(totalBudgetLabel);

        HBox inputFields = new HBox(5);
        Button addBtn = addBudgetBtn();
        inputFields.getChildren().addAll(addBtn, itemField, priceField);
        itemField.setPromptText("Enter item name");
        priceField.setPromptText("Enter price");

        remainingBudgetLabel = new Label();
        remainingBudgetLabel.getStyleClass().add("h3");

        this.getChildren().addAll(header, inputFields, budgetItems, remainingBudgetLabel);

        displayBudgetItems(); // Initial display of budget items and remaining budget
    }

    private void displayBudgetItems() {
        budgetItems.getChildren().clear();
        List<Anggaran> anggarans = AnggaranCtrl.getAllAnggaran(projek_id);
        int totalSpent = 0;

        for (Anggaran anggaran : anggarans) {
            Label itemLabel = new Label(anggaran.getItem() + " : Rp. " + anggaran.getHarga());
            itemLabel.getStyleClass().addAll("Item", "h4");
            budgetItems.getChildren().add(itemLabel);
            totalSpent += anggaran.getHarga();
        }

        int remainingBudget = initialBudget - totalSpent;
        remainingBudgetLabel.setText("Remaining Budget : Rp. " + remainingBudget);
    }

    private Button addBudgetBtn() {
        Button btn = new Button();
        btn.getStyleClass().addAll("icon-btn");
        Image image = new Image(getClass().getResourceAsStream("/icons/plus-circle-regular-24.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        btn.setGraphic(imageView);
        btn.setOnAction(event -> addItem());
        return btn;
    }

    private void addItem() {
        String item = itemField.getText();
        String harga = priceField.getText();

        if (!item.isEmpty() && !harga.isEmpty()) {
            try {
                int price = Integer.parseInt(harga);
                boolean success = AnggaranCtrl.addAnggaran(item, price, projek_id);
                if (success) {
                    itemField.clear();
                    priceField.clear();
                    displayBudgetItems();
                } else {
                    System.out.println("Failed to add budget item.");
                }
            } catch (NumberFormatException e) {
                priceField.clear();
                priceField.setPromptText("Invalid number. Please enter a valid price.");
            }
        }
    }
}
