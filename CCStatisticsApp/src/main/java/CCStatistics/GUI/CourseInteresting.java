package CCStatistics.GUI;

import CCStatistics.Domain.Course;
import CCStatistics.Logic.CourseInterestingToLogic;
import java.util.ArrayList;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class CourseInteresting {
    private Scene coursesInterestingView;

    public void courseInterestingScreen(Stage window) {
        BorderPane mainLayout = new BorderPane();
        // Pakt menu van menuklasse
        Menu menuClass = new Menu(window);
        // Links in mainLayout
        GridPane menu = menuClass.getMenu();
        // Rechts
        GridPane actionScreen = new GridPane();

        // Hoofdonderdeel, zit in actionScreen

        // Titel
        Label screenTitle = new Label("Courses");
        screenTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        actionScreen.setMinWidth(500);

        // Course info
        Label enterCourse = new Label("Enter course:");
        TextField inputCourse = new TextField();
        Label courseNotExist = new Label();
        Button submitButton = new Button("Start");
        Label recomCoursesTitle = new Label("Recommended course(s):");
        Label recommendedCourses = new Label("");
        ScrollPane scroller = new ScrollPane(recommendedCourses);

        recommendedCourses.setPadding(new Insets(5, 5, 5, 5));
        scroller.setMinHeight(120.0);

        // Stuurt de naam van de course naar courseLogic
        submitButton.setOnAction((event) -> {
            CourseInterestingToLogic interesting = new CourseInterestingToLogic();
            ArrayList<Course> coursesList = interesting.getAllInterestingCourses(inputCourse.getText());
            StringBuilder courses = new StringBuilder();

            if (coursesList != null) {
                for (Course course : coursesList) {
                    courseNotExist.setText("Success! Connection established.");
                    courses.append(course.getName() + "\n");
                }

                if (courses.toString().equals("No courses found!")) {
                    recommendedCourses.setText("No courses found!");
                } else {
                    recommendedCourses.setText(courses.toString());
                }

            } else {
                courseNotExist.setText("User not found!");
                recommendedCourses.setText("");
            }

        });
        // actionScreen.setAlignment(Pos.TOP_LEFT);

        actionScreen.add(screenTitle, 0, 1);
        actionScreen.add(enterCourse, 0, 2);
        actionScreen.add(inputCourse, 0, 3);
        actionScreen.add(submitButton, 0, 4);
        actionScreen.add(courseNotExist, 0, 5);
        actionScreen.add(recomCoursesTitle, 0, 6);
        actionScreen.add(recommendedCourses, 0, 7);
        actionScreen.add(scroller, 0, 8);

        actionScreen.setPrefSize(315, 320);
        // actionScreen.setAlignment(Pos.CENTER);
        actionScreen.setVgap(10);
        actionScreen.setHgap(10);
        actionScreen.setPadding(new Insets(10, 10, 10, 10));
        // mainactionScreen.setTop(screenTitleBox);
        mainLayout.setLeft(menu);
        mainLayout.setCenter(actionScreen);
        BorderPane.setMargin(menu, new Insets(0, 20, 0, 0));
        mainLayout.setPadding(new Insets(10, 10, 10, 10));

        coursesInterestingView = new Scene(mainLayout, 1280, 720);

        window.setTitle("Find recommended courses");
    }

    public Scene getCoursesInterestingView(Stage window) {
        this.courseInterestingScreen(window);
        return coursesInterestingView;
    }
}
