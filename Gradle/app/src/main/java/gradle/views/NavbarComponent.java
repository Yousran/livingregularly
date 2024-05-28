package gradle.views;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class NavbarComponent extends HBox {
    public NavbarComponent() {
        super();
        this.getStyleClass().add("navbar");
        this.getChildren().addAll(profilPict(),labelUsername());
    }

    public void bindToParent(Scene scene) {
        this.prefHeightProperty().bind(scene.heightProperty().multiply(0.2));
        this.prefWidthProperty().bind(scene.widthProperty());
    }

    private ImageView profilPict(){
        Image image = new Image(getClass().getResourceAsStream("/icons/" + "user-circle-regular-24.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        return imageView;
    }

    private Label labelUsername(){
        Label label = new Label("Hello, "+"Username"); 
        label.getStyleClass().add("h1");
        label.prefHeightProperty().bind(this.heightProperty());
        return label; 
    }
}
