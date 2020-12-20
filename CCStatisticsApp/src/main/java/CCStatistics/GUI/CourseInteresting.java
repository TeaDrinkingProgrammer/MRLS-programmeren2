package CCStatistics.GUI;

import CCStatistics.Domain.Course;
import CCStatistics.Logic.CourseInterestingToLogic;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class CourseInteresting extends Application {

    public void start(Stage window) {
        Label enterUser = new Label("User:");
        TextField inputUser = new TextField();
        Label enterCourse = new Label("Enter course:");
        TextField inputCourse = new TextField();
        Label courseNotExist = new Label();
        Button submitButton = new Button("Start");
        Label recomCoursesTitle = new Label("Recommended course(s):");
        Label recommendedCourses = new Label("");
        ScrollPane scroller = new ScrollPane(recommendedCourses);
                
        recommendedCourses.setPadding(new Insets(5, 5, 5, 5));
        scroller.setMinHeight(120.0);

        HBox toLogin = new HBox();
        toLogin.getChildren().addAll(enterUser, inputUser);
        toLogin.setSpacing(20);

        GridPane layout = new GridPane();
        layout.add(toLogin, 0, 0);
        layout.add(enterCourse, 0, 1);
        layout.add(inputCourse, 0, 2);
        layout.add(submitButton, 0, 3);
        layout.add(courseNotExist, 0, 4);
        layout.add(recomCoursesTitle, 0, 6);
        layout.add(recommendedCourses, 0, 7);
        layout.add(scroller, 0, 8);

        layout.setPrefSize(315, 320);
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        submitButton.setOnAction((event) -> {
            CourseInterestingToLogic interesting = new CourseInterestingToLogic();
            ArrayList<Course> coursesList = interesting.getAllInterestingCourses(inputCourse.getText(), inputUser.getText());
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

        Scene view = new Scene(layout);

        window.setTitle("Find recommended courses");
        window.setScene(view);
        window.show();
    }
}
