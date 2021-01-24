package CCStatistics.GUI;

import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Menu {
    GridPane menu;
    // Accordion menu;

    public Menu(){
        menu = new GridPane();
        // menu = new Accordion();
        Accordion accordion = new Accordion();

        TitledPane crudStudent = new TitledPane();
        VBox submenuCrudStudent = new VBox();
        crudStudent.setText("CRUD Student");
        crudStudent.setContent(submenuCrudStudent);

        submenuCrudStudent.setSpacing(4);
        submenuCrudStudent.setPadding(new Insets(5, 5, 5, 5));
        submenuCrudStudent.getChildren().add(new Button("Create"));
        submenuCrudStudent.getChildren().add(new Button("Read"));
        submenuCrudStudent.getChildren().add(new Button("Update"));
        submenuCrudStudent.getChildren().add(new Button("Delete"));


        TitledPane crudCertificate = new TitledPane();
        VBox submenuCrudCertificate = new VBox();
        crudCertificate.setText("CRUD Certificate");
        crudCertificate.setContent(submenuCrudCertificate);

        submenuCrudCertificate.setSpacing(4);
        submenuCrudCertificate.getChildren().add(new Button("Create"));
        submenuCrudCertificate.getChildren().add(new Button("Read"));
        submenuCrudCertificate.getChildren().add(new Button("Update"));
        submenuCrudCertificate.getChildren().add(new Button("Delete"));


        TitledPane crudSignup = new TitledPane();
        VBox submenuCrudSignup = new VBox();
        crudSignup.setText("CRUD SignUp");
        crudSignup.setContent(submenuCrudSignup);

        submenuCrudSignup.setSpacing(4);
        submenuCrudSignup.getChildren().add(new Button("Create"));
        submenuCrudSignup.getChildren().add(new Button("Read"));
        submenuCrudSignup.getChildren().add(new Button("Update"));
        submenuCrudSignup.getChildren().add(new Button("Delete"));


        TitledPane rudCourse = new TitledPane();
        VBox submenurudCourse = new VBox();
        rudCourse.setText("RUD Course");
        rudCourse.setContent(submenurudCourse);

        submenurudCourse.setSpacing(4);
        submenurudCourse.getChildren().add(new Button("Read"));
        submenurudCourse.getChildren().add(new Button("Update"));
        submenurudCourse.getChildren().add(new Button("Delete"));

        TitledPane materials = new TitledPane();
        VBox submenuMaterials = new VBox();
        materials.setText("Materials");
        materials.setContent(submenuMaterials);

        submenuMaterials.setSpacing(4);
        submenuMaterials.getChildren().add(new Button("Webcasts"));
        submenuMaterials.getChildren().add(new Button("Modules"));

        accordion.getPanes().addAll(crudStudent,crudCertificate,crudSignup,rudCourse,materials);
        menu.add(accordion,0,0);
        //menu = accordion;

    }
    public GridPane getMenu(){
        return menu;
    }
    // public Accordion getMenu(){
    //     return menu;
    // }
}
