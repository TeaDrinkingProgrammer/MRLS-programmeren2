package CCStatistics.GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UDScreen extends Application {

    public void start(Stage window) {
        
        BorderPane mainLayout = new BorderPane();
        Menu menuClass = new Menu();
        GridPane menu = menuClass.getMenu();
        GridPane actionScreen= new GridPane();
        Label screenTitle = new Label("Update/Delete X");
        screenTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        
        Label explaination = new Label("On this screen you can UpDel X.");
        Label explaination1 =  new Label("On the left you can see all the X, on the right you can UpDel them");
        
        TextField inputGetAll = new TextField();
        inputGetAll.setPromptText("Get all X");

        Button getAll = new Button("Get all");
        
        actionScreen.add(screenTitle,0,0);
        actionScreen.add(new Label(""),0,1);
        actionScreen.add(explaination,0,2);
        actionScreen.add(explaination1,0,3);
        actionScreen.add(new Label(""),0,4);
        actionScreen.add(inputGetAll,0,5);
        actionScreen.add(getAll,1,5);



        mainLayout.setLeft(menu);
        BorderPane.setMargin(menu, new Insets(0, 20, 0, 0));
        mainLayout.setPadding(new Insets(10,10,10,10));
        mainLayout.setCenter(actionScreen);

        Scene view = new Scene(mainLayout, 315, 325);
        window.setTitle("UDScreen");
        window.setScene(view);
        window.show();
    }
}
