package CCStatistics.GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class HomeScreen extends Application {
    public void start(Stage window) {
        Menu menuClass = new Menu(window);
        GridPane menu = menuClass.getMenu();

        BorderPane mainLayout = new BorderPane();
        GridPane actionScreen = new GridPane();

        Label appTitle = new Label("Codecademy Statistics");
        Label welcomeText = new Label(
                "Welcome to the Codecademy Statistics App. Please choose a screen on the left menu");
        Label versionText = new Label("v1.0");

        VBox contentPane = new VBox();
        BorderPane footer = new BorderPane();

        // appTitleBox.getChildren().add(appTitle);
        // appTitleBox.setAlignment(Pos.CENTER);
        appTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

        footer.setRight(versionText);

        contentPane.getChildren().addAll(appTitle, welcomeText);

        actionScreen.getChildren().addAll(contentPane);
        actionScreen.setMinWidth(500);
        actionScreen.setAlignment(Pos.TOP_LEFT);

        // mainLayout.setTop(appTitleBox);
        mainLayout.setLeft(menu);
        mainLayout.setCenter(actionScreen);
        mainLayout.setBottom(footer);
        // BorderPane.setMargin(appTitleBox, new Insets(0, 0, 20, 0));
        BorderPane.setMargin(menu, new Insets(0, 20, 0, 0));
        mainLayout.setPadding(new Insets(10, 10, 10, 10));

        Scene view = new Scene(mainLayout, 1280, 720);

        window.setTitle("Codecademy Statistics");
        window.setScene(view);
        window.show();
    }
}
