package com.institute.controller;

import com.institute.bo.BoFactory;
import com.institute.bo.BoType;
import com.institute.bo.custom.StudentBO;
import com.institute.dto.StudentDTO;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
    @FXML
    private Label lblTotalStudents;

    @FXML
    private Label lblMaleStudents;

    @FXML
    private Label lblFemaleStudents;

    @FXML
    private TableView<StudentDTO> tableStudent;

    @FXML
    private ComboBox<String> comboBoxStudent;

    @FXML
    private Button btnAddStudent;

    @FXML
    private Button btnEditStudent;

    @FXML
    private Button btnViewStudent;

    @FXML
    private Label lblDateTime;

    @FXML
    private Label lblTableCount;

    private StudentBO studentBO = BoFactory.getBo(BoType.STUDENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadBoxes();
        loadTime();
        comboBoxStudent.getItems().addAll("All Students", "Male Students", "Female Students");
        comboBoxStudent.getSelectionModel().select("All Students");
        generateTable();
        loadTable(comboBoxStudent.getSelectionModel().getSelectedItem());
    }

    private void loadTable(String selectedItem) {
        if (selectedItem != null) {
            tableStudent.getItems().remove(0, tableStudent.getItems().size());
            if (selectedItem.equals("All Students")) {
                ArrayList<StudentDTO> allStudent = studentBO.getAllStudent();
                if (allStudent != null) {
                    tableStudent.setItems(FXCollections.observableArrayList(allStudent));
                    lblTableCount.setText("No Of Records : " + allStudent.size());
                }
            } else if (selectedItem.equals("Male Students")) {
                ArrayList<StudentDTO> allStudent = studentBO.getMaleStudent();
                if (allStudent != null) {
                    tableStudent.setItems(FXCollections.observableArrayList(allStudent));
                    lblTableCount.setText("No Of Records : " + allStudent.size());
                }
            } else if (selectedItem.equals("Female Students")) {
                ArrayList<StudentDTO> allStudent = studentBO.getFemaleStudent();
                if (allStudent != null) {
                    tableStudent.setItems(FXCollections.observableArrayList(allStudent));
                    lblTableCount.setText("No Of Records : " + allStudent.size());
                }
            }
        }
    }

    private void generateTable() {
        tableStudent.setStyle("-fx-font-size: 16px;");
        tableStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tableStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tableStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tableStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tableStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("birthday"));
        tableStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));
        tableStudent.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("date_joined"));
        for (int i = 0; i < tableStudent.getColumns().size(); i++) {
            tableStudent.getColumns().get(i).setPrefWidth(164);
            tableStudent.getColumns().get(i).setStyle("-fx-alignment: center;");
        }
    }

    private void loadTime() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            lblDateTime.setText(LocalDate.now().toString() + " " + LocalTime.now().toString().substring(0, 8));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void loadBoxes() {
        lblTotalStudents.setText(String.valueOf(studentBO.getTotalStudents()));
        lblMaleStudents.setText(String.valueOf(studentBO.getTotalMaleStudents()));
        lblFemaleStudents.setText(String.valueOf(studentBO.getTotalFemaleStudents()));
    }

    @FXML
    private void btnAddStudentOnAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/institute/view/sub/AddStudent.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add Student");
        stage.setOnCloseRequest(e -> {
            loadBoxes();
            loadTable(comboBoxStudent.getSelectionModel().getSelectedItem());
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
    private void btnEditStudentOnAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/institute/view/sub/EditStudent.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Edit Student");
        stage.setOnCloseRequest(e -> {
            loadBoxes();
            loadTable(comboBoxStudent.getSelectionModel().getSelectedItem());
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
    private void btnViewStudentOnAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/institute/view/sub/ViewStudent.fxml"));
        Stage stage = new Stage();
        stage.setTitle("View Student");
        stage.setOnCloseRequest(e -> {
            loadBoxes();
            loadTable(comboBoxStudent.getSelectionModel().getSelectedItem());
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
    private void comboBoxStudentOnAction(ActionEvent event) {
        tableStudent.getItems().remove(0, tableStudent.getItems().size());
        loadTable(comboBoxStudent.getSelectionModel().getSelectedItem());
    }
}
