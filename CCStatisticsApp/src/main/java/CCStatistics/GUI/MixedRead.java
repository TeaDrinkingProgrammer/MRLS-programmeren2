package CCStatistics.GUI;

import java.util.ArrayList;
import java.util.List;

import CCStatistics.Domain.Certificate;
import CCStatistics.Domain.Webcast;
import CCStatistics.Logic.StudentLogic;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

public class MixedRead extends Application {
    private final ObservableList<Webcast> data = FXCollections.observableArrayList(new Webcast(10, "Test.com", "ofwawa", "Anycompany"));
    private StudentLogic studentLogic = new StudentLogic();
    public void start(Stage window) {
        ArrayList<String[]> getAll = new ArrayList<>();

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

        Button getAllButton = new Button("Get all");

        
        TableView table = new TableView();
        table.setEditable(false);
    
        TableColumn firstNameCol = new TableColumn("First Name");
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn addressCol = new TableColumn("Email");

    //     ObservableList<ObservableList> csvData = FXCollections.observableArrayList(); 
        
    //     for(String[] dataList : studentLogic.getAll()) {
    //         ObservableList<String> row = FXCollections.observableArrayList();
    //        for( String rowData : dataList) {
    //          row.add(rowData); 
    //      }
    //       cvsData.add(row); // add each row to cvsData
    //    }
       
    //    table.setItems(cvsData); // finally add data to tableview
        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, addressCol);

        actionScreen.add(screenTitle,0,0);
        actionScreen.add(new Label(""),0,1);
        actionScreen.add(explaination,0,2);
        actionScreen.add(explaination1,0,3);
        actionScreen.add(new Label(""),0,4);
        actionScreen.add(inputGetAll,0,5);
        actionScreen.add(getAllButton,1,5);
        actionScreen.add(table,0,6);

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
