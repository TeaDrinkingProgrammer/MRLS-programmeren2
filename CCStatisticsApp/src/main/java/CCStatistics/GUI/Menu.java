package CCStatistics.GUI;

import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu {
    GridPane menu;
    // Het menu werkt qua layout, maar heeft geen links

    public Menu(Stage window) {
        // Vraagt studentCRUD pagina op
        StudentCRUD studentCRUD = new StudentCRUD();

        CourseInteresting courseInteresting = new CourseInteresting();
        menu = new GridPane();
        Accordion accordion = new Accordion();

        // maakt een pane met daarin een VBox. Hierin zit een knop die naar de
        // studentCRUD leidt
        TitledPane crudStudent = new TitledPane();
        VBox submenuCrudStudent = new VBox();
        crudStudent.setText("Student");
        Button studentButton = new Button("Student");
        submenuCrudStudent.setPadding(new Insets(5, 5, 5, 5));
        submenuCrudStudent.getChildren().add(studentButton);

        crudStudent.setContent(submenuCrudStudent);

        studentButton.setOnAction(actionEvent -> {
            window.setScene(studentCRUD.getStudentView(window));
        });

        // maakt een pane met daarin een VBox. Hierin zit een knop die naar de Courses
        // leidt
        TitledPane courseInterestingPane = new TitledPane();
        VBox submenuCourseInteresting = new VBox();
        courseInterestingPane.setText("Courses");
        Button courseInterestingButton = new Button("Courses");
        submenuCourseInteresting.setPadding(new Insets(5, 5, 5, 5));
        submenuCourseInteresting.getChildren().add(courseInterestingButton);

        courseInterestingPane.setContent(submenuCourseInteresting);

        courseInterestingButton.setOnAction(actionEvent -> {
            window.setScene(courseInteresting.getCoursesInterestingView(window));
        });

        // Hieronder zijn ook de andere paginas gedefinieerd, maar deze zijn
        // uiteindelijk niet geimplementeerd

        // TitledPane crudCertificate = new TitledPane();
        // VBox submenuCrudCertificate = new VBox();
        // crudCertificate.setText("CRUD Certificate");
        // crudCertificate.setContent(submenuCrudCertificate);

        // submenuCrudCertificate.setSpacing(4);
        // submenuCrudCertificate.getChildren().add(new Button("Create"));
        // submenuCrudCertificate.getChildren().add(new Button("Read"));
        // submenuCrudCertificate.getChildren().add(new Button("Update"));
        // submenuCrudCertificate.getChildren().add(new Button("Delete"));

        // TitledPane crudSignup = new TitledPane();
        // VBox submenuCrudSignup = new VBox();
        // crudSignup.setText("CRUD SignUp");
        // crudSignup.setContent(submenuCrudSignup);

        // submenuCrudSignup.setSpacing(4);
        // submenuCrudSignup.getChildren().add(new Button("Create"));
        // submenuCrudSignup.getChildren().add(new Button("Read"));
        // submenuCrudSignup.getChildren().add(new Button("Update"));
        // submenuCrudSignup.getChildren().add(new Button("Delete"));

        // TitledPane rudCourse = new TitledPane();
        // VBox submenurudCourse = new VBox();
        // rudCourse.setText("RUD Course");
        // rudCourse.setContent(submenurudCourse);

        // submenurudCourse.setSpacing(4);
        // submenurudCourse.getChildren().add(new Button("Read"));
        // submenurudCourse.getChildren().add(new Button("Update"));
        // submenurudCourse.getChildren().add(new Button("Delete"));

        // TitledPane materials = new TitledPane();
        // VBox submenuMaterials = new VBox();
        // materials.setText("Materials");
        // materials.setContent(submenuMaterials);

        // submenuMaterials.setSpacing(4);
        // submenuMaterials.getChildren().add(new Button("Webcasts"));
        // submenuMaterials.getChildren().add(new Button("Modules"));

        accordion.getPanes().addAll(crudStudent, courseInterestingPane);
        menu.add(accordion, 0, 0);

    }

    public GridPane getMenu() {
        return menu;
    }
}
