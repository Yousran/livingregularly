package timetuner.views;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import timetuner.App;

public class PageSettings extends VBox {
    private SceneMain sceneMain;

    public PageSettings(SceneMain sceneMain){
        this.sceneMain = sceneMain;
        this.getStyleClass().add("container");
        Label subTitle = new Label("Settings");
        subTitle.getStyleClass().add("h3");
        this.getChildren().add(subTitle);

        CheckBox darkModeToggle = new CheckBox("Dark Mode");
        darkModeToggle.getStyleClass().add("h5-thin");
        darkModeToggle.setSelected(App.style.equals("/styles/styles-dark.css"));
        darkModeToggle.setOnAction(event -> {
            App.style = darkModeToggle.isSelected() ? "/styles/styles-dark.css" : "/styles/styles.css";
            reloadStyle();
        });

        this.getChildren().add(darkModeToggle);
    }

    private void reloadStyle() {
        sceneMain.scene.getStylesheets().clear();
        String stylePath = getClass().getResource(App.style).toExternalForm();
        sceneMain.scene.getStylesheets().add(stylePath);
        sceneMain.updatePage("Settings");
    }
}
