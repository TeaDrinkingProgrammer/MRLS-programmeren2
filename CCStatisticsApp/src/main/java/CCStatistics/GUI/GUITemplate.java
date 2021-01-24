package CCStatistics.GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUITemplate extends Application {

    public void start(Stage window) {
        
        BorderPane mainLayout = new BorderPane();
        Menu menuClass = new Menu();
        GridPane menu= menuClass.getMenu();
        GridPane actionScreen= new GridPane();
        Label screenTitle = new Label("Some text");

        actionScreen.add(screenTitle,0,0);
        
        mainLayout.setLeft(menu);
        BorderPane.setMargin(menu, new Insets(0, 20, 0, 0));
        mainLayout.setPadding(new Insets(10,10,10,10));
        mainLayout.setCenter(actionScreen);

        Scene view = new Scene(mainLayout, 315, 325);
        window.setTitle("Template");
        window.setScene(view);
        window.show();
    }
}
