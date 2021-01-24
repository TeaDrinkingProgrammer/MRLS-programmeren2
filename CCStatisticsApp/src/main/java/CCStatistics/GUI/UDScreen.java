package CCStatistics.GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UDScreen extends Application {

    public void start(Stage window) {
        
        BorderPane mainLayout = new BorderPane();
        Menu menuClass = new Menu();
        GridPane menu= menuClass.getMenu();
        // Accordion menu= menuClass.getMenu();
        BorderPane actionScreen = new BorderPane();

        
        Label screenTitle = new Label("Update/Delete something");


        
        actionScreen.setTop(screenTitle);
        mainLayout.setLeft(menu);
        mainLayout.setCenter(actionScreen);

        Scene view = new Scene(mainLayout);

        window.setTitle("UDScreen");
        window.setScene(view);
        window.show();
    }
}
