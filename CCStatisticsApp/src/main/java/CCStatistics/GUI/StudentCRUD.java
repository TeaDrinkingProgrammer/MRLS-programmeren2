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
import javafx.scene.control.ComboBox;
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
        postalcodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        
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
        Label createInfo = new Label("Here you can Create a student");
        ArrayList<TextField> createInputs = new ArrayList<>();
        for(int i = 0; i <10 ; i++ ){
            createInputs.add(new TextField());
        }
        // TextField updateInputEmail = new TextField();
        // TextField updateInputFirstName = new TextField();
        // TextField updateInputLastName = new TextField();
        // TextField updateInputDateOfBirth = new TextField();
        // TextField updateInputGender = new TextField();
        // TextField updateInputStreet = new TextField();
        // TextField updateInputHouseNumber = new TextField();
        // TextField updateInputPostalCode = new TextField();
        // TextField updateInputCity = new TextField();
        // TextField updateInputCountry = new TextField();
        createInputs.get(0).setPromptText("Email");
        createInputs.get(1).setPromptText("FirstName");
        createInputs.get(2).setPromptText("LastName");
        createInputs.get(3).setPromptText("DateOfBirth");
        createInputs.get(4).setPromptText("Gender");
        createInputs.get(5).setPromptText("Street");
        createInputs.get(6).setPromptText("HouseNumber");
        createInputs.get(7).setPromptText("Postalcode");
        createInputs.get(8).setPromptText("City");
        createInputs.get(9).setPromptText("Country");
        Button createButton = new Button("Create Student");

       createButton.setOnAction(actionEvent ->  {
           Boolean canCreate = true;
           for(TextField textField : createInputs){
               if(textField.getText().isEmpty()){
                canCreate = false;
               }
           }
           if(canCreate){
            studentLogic.create(createInputs.get(0).getText(), createInputs.get(1).getText(), createInputs.get(2).getText(), createInputs.get(3).getText(), 
            createInputs.get(4).getText(), createInputs.get(5).getText(), createInputs.get(6).getText(), createInputs.get(7).getText(), createInputs.get(8).getText(), createInputs.get(9).getText());
           }
        });

        Label deleteInfo = new Label("Here you can delete a student");
        TextField deleteInput = new TextField();
        deleteInput.setPromptText("studentEmail here");
        Button deleteButton = new Button("Delete");

        Label deleteFeedback = new Label("");

        deleteButton.setOnAction(actionEvent ->  {
            if(!deleteInput.getText().isEmpty()){
                deleteFeedback.setText(studentLogic.delete(deleteInput.getText()));
            }
           
         });
        //studentLogic.update(columnToChange, changeInto, studentEmail)
        Label updateInfo = new Label("Here you can update information regarding a student");
        ObservableList options = FXCollections.observableArrayList(studentLogic.getColumns());
        ComboBox columnToChange = new ComboBox<>(options);
        columnToChange.setValue("Column");

        TextField changeInto = new TextField();
        changeInto.setPromptText("New Value");

        TextField studentEmail = new TextField();
        studentEmail.setPromptText("StudentEmail");

        updateDeleteBox.add(createInfo,0,0);
        updateDeleteBox.add(createInputs.get(0),0,1);
        updateDeleteBox.add(createInputs.get(1),1,1);
        updateDeleteBox.add(createInputs.get(2),0,2);
        updateDeleteBox.add(createInputs.get(3),1,2);
        updateDeleteBox.add(createInputs.get(4),0,3);
        updateDeleteBox.add(createInputs.get(5),1,3);
        updateDeleteBox.add(createInputs.get(6),0,4);
        updateDeleteBox.add(createInputs.get(7),1,4);
        updateDeleteBox.add(createInputs.get(8),0,5);
        updateDeleteBox.add(createInputs.get(9),1,5);
        updateDeleteBox.add(createButton,0,6);

        updateDeleteBox.add(new Label(""),0,7);

        updateDeleteBox.add(deleteInfo,0,8);
        updateDeleteBox.add(deleteInput,0,9);
        updateDeleteBox.add(deleteButton,1,9);
        updateDeleteBox.add(deleteFeedback,0,10);

        updateDeleteBox.add(new Label(""),0,11);
        updateDeleteBox.add(columnToChange,0,12);
        updateDeleteBox.add(changeInto,0,13);
        updateDeleteBox.add(studentEmail,0,14);

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
