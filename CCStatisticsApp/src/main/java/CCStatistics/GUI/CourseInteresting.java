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
import javafx.scene.layout.GridPane;

public class CourseInteresting extends Application {

    public void start(Stage window) {
        Label enterCourse = new Label("Enter course:");
        TextField inputCourse = new TextField();
        Label courseNotExist = new Label();
        Button submitButton = new Button("Start");
        Label recomCoursesTitle = new Label("Recommended course(s):");
        Label recommendedCourses = new Label("");

        GridPane layout = new GridPane();
        layout.add(enterCourse, 0, 0);
        layout.add(inputCourse, 0, 1);
        layout.add(courseNotExist, 0, 2);
        layout.add(submitButton, 0, 3);
        layout.add(recomCoursesTitle, 0, 4);
        layout.add(recommendedCourses, 0, 5);

        layout.setPrefSize(300, 180);
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        submitButton.setOnAction((event) -> {
            CourseInterestingToLogic interesting = new CourseInterestingToLogic();
            ArrayList<Course> coursesList = interesting.getAllInterestingCourses(inputCourse.getText());
            StringBuilder courses = new StringBuilder();

            if (coursesList != null) {
                for (Course course : coursesList) {
                    courses.append(course.getName() + "\n");
                }
                recommendedCourses.setText(courses.toString());
            } else {
                courseNotExist.setText("No such course, fool!");
            }
            
        });

        Scene view = new Scene(layout);

        window.setTitle("Courses interesting to");
        window.setScene(view);
        window.show();
    }
}
