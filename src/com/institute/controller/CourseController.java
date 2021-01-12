package com.institute.controller;

import com.institute.bo.BoFactory;
import com.institute.bo.BoType;
import com.institute.bo.custom.CourseBO;
import com.institute.dto.CourseDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class CourseController implements Initializable {
    @FXML
    private Label lblDateTime;

    @FXML
    private TableView<CourseDTO> tableCourse;

    @FXML
    private Button btnAddCourse;

    @FXML
    private Button btnEditCourse;

    @FXML
    private Label lblTableCount;

    @FXML
    private Label lblTotalCourses;

    private CourseBO courseBO = BoFactory.getBo(BoType.COURSE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTime();
        lblTotalCourses.setText(String.valueOf(courseBO.getCourseCount()));
        generateTable();
        loadTable();
    }

    private void loadTable() {
        tableCourse.getItems().remove(0, tableCourse.getItems().size());
        tableCourse.setItems(FXCollections.observableArrayList(courseBO.getCourses()));
        lblTableCount.setText("No Of Records : " + courseBO.getCourses().size());
    }

    private void generateTable() {
        tableCourse.setStyle("-fx-font-size: 16px;");
        tableCourse.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tableCourse.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tableCourse.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("type"));
        tableCourse.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("duration"));
        tableCourse.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("price"));
        for (int i = 0; i < tableCourse.getColumns().size(); i++) {
            tableCourse.getColumns().get(i).setPrefWidth(230);
            tableCourse.getColumns().get(i).setStyle("-fx-alignment: center;");
        }
    }

    private void loadTime() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            lblDateTime.setText(LocalDate.now().toString() + " " + LocalTime.now().toString().substring(0, 8));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @FXML
    private void btnAddCourseOnAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/institute/view/sub/AddCourse.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add Course");
        stage.setOnCloseRequest(e -> {
            lblTotalCourses.setText(String.valueOf(courseBO.getCourseCount()));
            loadTable();
        });
        try {
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            scene.getWindow().centerOnScreen();
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnEditCourseOnAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/institute/view/sub/EditCourse.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Edit Course");
        stage.setOnCloseRequest(e -> {
            lblTotalCourses.setText(String.valueOf(courseBO.getCourseCount()));
            loadTable();
        });
        try {
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            scene.getWindow().centerOnScreen();
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
