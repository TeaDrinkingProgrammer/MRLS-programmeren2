package CCStatistics.GUI;

import java.util.ArrayList;
import CCStatistics.Domain.Student;
import CCStatistics.Logic.StudentLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class StudentCRUD {
    private ObservableList<Student> data = FXCollections.observableArrayList();
    private StudentLogic studentLogic = new StudentLogic();
    private Scene studentView;

    public void studentCrudScreen(Stage window) {
        // MainLayout bevat links het menu en rechts de inhoud. Deze kant (subLayout) is
        // weer ondergedeelt in het deel voor de select queries (ReadBox) en update,
        // delete en create queries (CUDBox)
        BorderPane mainLayout = new BorderPane();
        Menu menuClass = new Menu(window);
        GridPane menu = menuClass.getMenu();
        GridPane CUDBox = new GridPane();
        BorderPane subLayout = new BorderPane();
        GridPane ReadBox = new GridPane();
        Label screenTitle = new Label("Students");
        screenTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

        Label explaination = new Label("On this screen you can get and change information about students.");
        Label explaination1 = new Label("On the left you get, and on the right you can change or add info");

        // Input voor de select queries
        TextField inputGet = new TextField();
        inputGet.setPromptText("Student email here");
        Button getButton = new Button("Get info about student");

        getButton.setOnAction(actionEvent -> {
            data.clear();
            if (!inputGet.getText().isEmpty()) {
                // vraagt een select query op voor de studentemail die is ingevoerd
                for (Student student : studentLogic.read(inputGet.getText())) {
                    data.add(student);
                }
            }
        });

        // Vraag een selectquery op voor alle studenten
        Button getAllButton = new Button("Get info about all students");
        getAllButton.setOnAction(actionEvent -> {
            data.clear();
            for (Student student : studentLogic.getAll()) {
                data.add(student);
            }
        });

        // De tabel
        TableView table = new TableView();
        table.setEditable(false);
        // Elke kolom is gekoppeld aan een waarde in de Student klasse
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
        table.getColumns().addAll(emailCol, firstNameCol, lastNameCol, dateOfBirthCol, genderCol, streetCol,
                houseNumberCol, postalcodeCol, countryCol);

        ReadBox.setMaxWidth(500);
        table.setMaxWidth(350);
        inputGet.setMaxWidth(350);

        // Voegt alles van de linkerkant van het scherm toe
        ReadBox.add(screenTitle, 0, 0);
        ReadBox.add(new Label(""), 0, 1);
        ReadBox.add(explaination, 0, 2);
        ReadBox.add(explaination1, 0, 3);
        ReadBox.add(new Label(""), 0, 4);
        ReadBox.add(inputGet, 0, 5);
        ReadBox.add(getButton, 1, 5);
        ReadBox.add(getAllButton, 0, 6);
        ReadBox.add(table, 0, 7);

        // Hier begint het rechterscherm (updateDelete)
        Label createInfo = new Label("Here you can Create a student");
        ArrayList<TextField> createInputs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            createInputs.add(new TextField());
        }
        createInputs.get(0).setPromptText("Email");
        createInputs.get(1).setPromptText("FirstName");
        createInputs.get(2).setPromptText("LastName");
        createInputs.get(3).setPromptText("DateOfBirth (d-m-y)");
        createInputs.get(4).setPromptText("Gender (M/V)");
        createInputs.get(5).setPromptText("Street");
        createInputs.get(6).setPromptText("HouseNumber");
        createInputs.get(7).setPromptText("Postalcode");
        createInputs.get(8).setPromptText("City");
        createInputs.get(9).setPromptText("Country");
        Button createButton = new Button("Create Student");

        createButton.setOnAction(actionEvent -> {
            Boolean canCreate = true;
            // controleerd eerst of alle velden ingevuld zijn
            for (TextField textField : createInputs) {
                if (textField.getText().isEmpty()) {
                    canCreate = false;
                }
            }
            if (canCreate) {
                // Als alle velden ingevuld zijn wordt het toegevoegd
                studentLogic.create(createInputs.get(0).getText(), createInputs.get(1).getText(),
                        createInputs.get(2).getText(), createInputs.get(3).getText(), createInputs.get(4).getText(),
                        createInputs.get(5).getText(), createInputs.get(6).getText(), createInputs.get(7).getText(),
                        createInputs.get(8).getText(), createInputs.get(9).getText());
            }
        });

        // Het delete deel
        Label deleteInfo = new Label("Here you can delete a student");
        TextField deleteInput = new TextField();
        deleteInput.setPromptText("studentEmail here");
        Button deleteButton = new Button("Delete");

        // Geeft de return waarde van de query (x rows deleted)
        Label deleteFeedback = new Label("");

        deleteButton.setOnAction(actionEvent -> {
            if (!deleteInput.getText().isEmpty()) {
                deleteFeedback.setText(studentLogic.delete(deleteInput.getText()));
            }

        });

        Label updateInfo = new Label(
                "Here you can update information regarding a student (if it worked which it does not)");
        // JavaFX gebruikt net een andere lijst dan een ArrayList. Zo wordt dit omgezet.
        // De kollomen komen uit studentLogic die ze uit de DAO haalt. De DAO maakt
        // gebruik van dezelfde lijst om typefouten te voorkomen.
        ObservableList options = FXCollections.observableArrayList(studentLogic.getColumns());
        ComboBox columnToChange = new ComboBox<>(options);
        columnToChange.setValue("Column");

        TextField changeInto = new TextField();
        changeInto.setPromptText("New Value");

        TextField studentEmail = new TextField();
        studentEmail.setPromptText("StudentEmail");

        Button updateButton = new Button("Update");

        updateButton.setOnAction(actionEvent -> {
            if (!changeInto.getText().isEmpty() || studentEmail.getText().isEmpty()) {
                System.out.println(columnToChange.getValue().toString());
                studentLogic.update(columnToChange.getValue().toString(), changeInto.getText(), studentEmail.getText());
                // Update geeft studentLogic.getAll terug
                data.clear();
                for (Student student : studentLogic.getAll()) {
                    data.add(student);
                }
            }

        });

        // Voegt alle onderdelen toe aan CUDBox
        CUDBox.add(createInfo, 0, 0);
        CUDBox.add(createInputs.get(0), 0, 1);
        CUDBox.add(createInputs.get(1), 1, 1);
        CUDBox.add(createInputs.get(2), 0, 2);
        CUDBox.add(createInputs.get(3), 1, 2);
        CUDBox.add(createInputs.get(4), 0, 3);
        CUDBox.add(createInputs.get(5), 1, 3);
        CUDBox.add(createInputs.get(6), 0, 4);
        CUDBox.add(createInputs.get(7), 1, 4);
        CUDBox.add(createInputs.get(8), 0, 5);
        CUDBox.add(createInputs.get(9), 1, 5);
        CUDBox.add(createButton, 0, 6);

        CUDBox.add(new Label(""), 0, 7);

        CUDBox.add(deleteInfo, 0, 8);
        CUDBox.add(deleteInput, 0, 9);
        CUDBox.add(deleteButton, 1, 9);
        CUDBox.add(deleteFeedback, 0, 10);

        CUDBox.add(new Label(""), 0, 11);
        CUDBox.add(updateInfo, 0, 12);
        CUDBox.add(columnToChange, 0, 13);
        CUDBox.add(changeInto, 0, 14);
        CUDBox.add(studentEmail, 0, 15);
        CUDBox.add(updateButton, 0, 16);

        // Definieerd de layout van de pagina
        mainLayout.setLeft(menu);
        mainLayout.setCenter(subLayout);

        BorderPane.setMargin(ReadBox, new Insets(0, 50, 0, 0));
        BorderPane.setMargin(menu, new Insets(0, 20, 0, 0));
        mainLayout.setPadding(new Insets(10, 10, 10, 10));

        subLayout.setLeft(ReadBox);
        subLayout.setCenter(CUDBox);

        // Laat het scherm zien
        studentView = new Scene(mainLayout, 1280, 720);
        window.setTitle("Students");
    }

    public Scene getStudentView(Stage window) {
        this.studentCrudScreen(window);
        return studentView;
    }
}
