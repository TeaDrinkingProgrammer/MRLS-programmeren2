package CCStatistics.GUI;

import java.util.ArrayList;
import java.util.List;

import CCStatistics.Domain.Certificate;
import CCStatistics.Domain.Student;
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
import javafx.scene.control.cell.PropertyValueFactory;
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

public class StudentCRUD extends Application {
    private ObservableList<Student> data = FXCollections.observableArrayList();
    private StudentLogic studentLogic = new StudentLogic();
    public void start(Stage window) {
        ArrayList<String[]> getAll = new ArrayList<>();

        BorderPane mainLayout = new BorderPane();
        Menu menuClass = new Menu();
        GridPane menu = menuClass.getMenu();
        GridPane updateDeleteBox = new GridPane();
        BorderPane subLayout = new BorderPane();
        GridPane actionScreen= new GridPane();
        Label screenTitle = new Label("Update/Delete X");
        screenTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        
        Label explaination = new Label("On this screen you can UpDel X.");
        Label explaination1 =  new Label("On the left you can see all the X, on the right you can UpDel them");
        
        TextField inputGet = new TextField();
        inputGet.setPromptText("Student email here");
         Button getButton = new Button("Get info about student");

         getButton.setOnAction(actionEvent ->  {
            data.clear();
            if(!inputGet.getText().isEmpty()){
                for(Student student :studentLogic.read(inputGet.getText())){
                    data.add(student);
                } 
            }
        });

        Button getAllButton = new Button("Get info about all students");
        getAllButton.setOnAction(actionEvent ->  {
            data.clear();
            for(Student student :studentLogic.getAll()){
                data.add(student);
            } 
        });

        TableView table = new TableView();
        table.setEditable(false);
    
        TableColumn emailCol = new TableColumn("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        
        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        
        TableColumn dateOfBirthCol = new TableColumn("Date of Birth");
        dateOfBirthCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        
        TableColumn genderCol = new TableColumn("Gender");
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        
        TableColumn streetCol = new TableColumn("Street");
        streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));
        
        TableColumn houseNumberCol = new TableColumn("House Number");
        houseNumberCol.setCellValueFactory(new PropertyValueFactory<>("houseNumber"));
        
        TableColumn postalcodeCol = new TableColumn("Postalcode");
        postalcodeCol.setCellValueFactory(new PropertyValueFactory<>("postalcode"));
        
        TableColumn countryCol = new TableColumn("Country");
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
       
        table.setItems(data);
        table.getColumns().addAll(emailCol, firstNameCol, lastNameCol,dateOfBirthCol,genderCol,streetCol
        ,houseNumberCol,postalcodeCol,countryCol);

        actionScreen.setMaxWidth(500);
        table.setMaxWidth(350);
        inputGet.setMaxWidth(350);

        actionScreen.add(screenTitle,0,0);
        actionScreen.add(new Label(""),0,1);
        actionScreen.add(explaination,0,2);
        actionScreen.add(explaination1,0,3);
        actionScreen.add(new Label(""),0,4);
        actionScreen.add(inputGet,0,5);
        actionScreen.add(getButton,1,5);
        actionScreen.add(getAllButton,0,6);
        actionScreen.add(table,0,7);

        //updateDeleteBox begins here
       // studentLogic.create("email", firstName, lastName, dateOfBirth, gender, street, houseNumber, postalcode, city, country);
        Label updateInfo = new Label("Here you can Create a student");
        TextField updateInputEmail = new TextField();
        TextField updateInputFirstName = new TextField();
        TextField updateInputLastName = new TextField();
        TextField updateInputDateOfBirth = new TextField();
        TextField updateInputGender = new TextField();
        TextField updateInputStreet = new TextField();
        TextField updateInputHouseNumber = new TextField();
        TextField updateInputPostalCode = new TextField();
        TextField updateInputCity = new TextField();
        TextField updateInputCountry = new TextField();
        updateInputEmail.setPromptText("Email");
        updateInputFirstName.setPromptText("FirstName");
        updateInputLastName.setPromptText("LastName");
        updateInputDateOfBirth.setPromptText("DateOfBirth");
        updateInputGender.setPromptText("Gender");
        updateInputStreet.setPromptText("Street");
        updateInputHouseNumber.setPromptText("HouseNumber");
        updateInputPostalCode.setPromptText("Postalcode");
        updateInputCity.setPromptText("City");
        updateInputCountry.setPromptText("Country");
        Button updateButton = new Button("Create Student");

       updateButton.setOnAction(actionEvent ->  {
            data.clear();
            for(Student student :studentLogic.getAll()){
                data.add(student);
            } 
        });

        Label deleteInfo = new Label("Here you can delete a student");
        TextField deleteInput = new TextField();
        deleteInput.setPromptText("studentEmail here");
        Button deleteButton = new Button("Delete");

        updateDeleteBox.add(updateInfo,0,0);
        updateDeleteBox.add(updateInputEmail,0,1);
        updateDeleteBox.add(updateInputFirstName,1,1);
        updateDeleteBox.add(updateInputLastName,0,2);
        updateDeleteBox.add(updateInputDateOfBirth,1,2);
        updateDeleteBox.add(updateInputGender,0,3);
        updateDeleteBox.add(updateInputStreet,1,3);
        updateDeleteBox.add(updateInputHouseNumber,0,4);
        updateDeleteBox.add(updateInputPostalCode,1,4);
        updateDeleteBox.add(updateInputCity,0,5);
        updateDeleteBox.add(updateInputCountry,1,5);
        updateDeleteBox.add(updateButton,0,6);

        updateDeleteBox.add(new Label(""),0,7);

        updateDeleteBox.add(deleteInfo,0,8);
        updateDeleteBox.add(deleteInput,0,9);
        updateDeleteBox.add(deleteButton,1,10);


        subLayout.setLeft(actionScreen);
        subLayout.setCenter(updateDeleteBox);
        mainLayout.setLeft(menu);
        BorderPane.setMargin(actionScreen, new Insets(0, 50, 0, 0));
        BorderPane.setMargin(menu, new Insets(0, 20, 0, 0));
        mainLayout.setPadding(new Insets(10,10,10,10));
        mainLayout.setCenter(subLayout);

        Scene view = new Scene(mainLayout, 1280,720);
        window.setTitle("UDScreen");
        window.setScene(view);
        window.show();
    }
}
