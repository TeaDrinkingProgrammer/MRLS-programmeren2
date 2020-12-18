package CCStatistics.GUI;
import CCStatistics.Domain.Course;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class Courses extends Application {
    public void start(Stage window) {
        CourseInterestingToLogic interesting = new CourseInterestingToLogic();

        Label enterCourse = new Label("Enter course:");
        TextField inputCourse = new TextField();
        Button submitButton = new Button("Start");
        Label recomCoursesTitle = new Label("Recommended course(s):");
        Label recommendedCourses = new Label("");

        GridPane layout = new GridPane();
        layout.add(enterCourse, 0, 0);
        layout.add(inputCourse, 0, 1);
        layout.add(submitButton, 0, 2);
        layout.add(recomCoursesTitle, 0, 3);
        layout.add(recommendedCourses, 0, 4);

        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        submitButton.setOnAction((event) -> {
            ArrayList<Course> coursesList = getAllInterestingCourses(inputCourse.getText);
            StringBuilder courses = new StringBuilder();
            for(Course course: coursesList) {
                courses.append(course.getName + "\n");
            }

            recommendedCourses.setText(courses.toString);
        });

        Scene view = new Scene(layout);

        window.setScene(view);
        window.show();
    }
}
