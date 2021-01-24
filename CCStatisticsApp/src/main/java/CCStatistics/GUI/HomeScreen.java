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
import javafx.stage.Stage;

public class HomeScreen extends Application {
    public void start(Stage window) {
        Menu menuClass = new Menu();
        GridPane menu = menuClass.getMenu();
        
        BorderPane mainLayout = new BorderPane();
        GridPane actionScreen = new GridPane();

        Label appTitle = new Label("Codecademy Statistics");
        Label welcomeText = new Label("Some Text");
        Label versionText = new Label("v1.0");

        VBox homeScreen = new VBox();
        VBox appTitleBox = new VBox();
        BorderPane footer = new BorderPane();
        
        //menu.add(welcomeText,1,0);

        appTitleBox.getChildren().add(appTitle);
        footer.setRight(versionText);
        
        appTitleBox.setAlignment(Pos.CENTER);
        appTitle.setFont(new Font("Arial", 20));
        
        homeScreen.setPadding(new Insets(10, 10, 10, 10));
        homeScreen.setSpacing(50);

        mainLayout.setTop(appTitleBox);
        mainLayout.setLeft(menu);
        mainLayout.setCenter(actionScreen);
        mainLayout.setBottom(footer);

        Scene view = new Scene(mainLayout, 315, 325);

        window.setTitle("Codecademy Statistics");
        window.setScene(view);
        window.show();
        // Label appTitle = new Label("Codecademy Statistics");
        // Button nav1 = new Button("Courses");
        // Button nav2 = new Button("Students");
        // Button nav3 = new Button("Registrations");
        // Button nav4 = new Button("Certificates");
        // Label versionText = new Label("v1.0");

        // VBox homeScreen = new VBox();
        // VBox appTitleBox = new VBox();
        // VBox innerHomeScreen = new VBox();
        // BorderPane footer = new BorderPane();
        
        // appTitleBox.getChildren().add(appTitle);
        // innerHomeScreen.getChildren().addAll(nav1, nav2, nav3, nav4);
        // footer.setRight(versionText);
        // homeScreen.getChildren().addAll(appTitleBox, innerHomeScreen, footer);
        
        // appTitleBox.setAlignment(Pos.CENTER);
        // appTitle.setFont(new Font("Arial", 20));
        
        // innerHomeScreen.setAlignment(Pos.CENTER);
        // innerHomeScreen.setSpacing(20);
        
        // homeScreen.setPadding(new Insets(10, 10, 10, 10));
        // homeScreen.setSpacing(50);

        // Scene view1 = new Scene(homeScreen, 315, 325);

        // window.setTitle("Codecademy Statistics");
        // window.setScene(view1);
        // window.show();
    }
}
