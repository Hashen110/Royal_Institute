package com.institute.controller.sub;

import com.institute.bo.BoFactory;
import com.institute.bo.BoType;
import com.institute.bo.custom.StudentBO;
import com.institute.dto.CourseDTO;
import com.institute.dto.StudentDTO;
import com.institute.util.AlertUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewStudentController implements Initializable {
    @FXML
    private ComboBox<String> comboBoxStudent;

    @FXML
    private TextField txtStudent;

    @FXML
    private Button btnSelectStudent;

    @FXML
    private TableView<StudentDTO> tableStudent;

    @FXML
    private TableView<CourseDTO> tableCourse;

    private StudentBO studentBO = BoFactory.getBo(BoType.STUDENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadComboBox();
        generateTables();
    }

    private void generateTables() {
        tableStudent.setStyle("-fx-font-size: 16px;");
        tableStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tableStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tableStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tableStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tableStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("birthday"));
        tableStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));
        tableStudent.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("date_joined"));
        for (int i = 0; i < tableStudent.getColumns().size(); i++) {
            tableStudent.getColumns().get(i).setStyle("-fx-alignment: center;");
            tableStudent.getColumns().get(i).setMinWidth(107);
        }
        tableCourse.setStyle("-fx-font-size: 16px;");
        tableCourse.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tableCourse.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tableCourse.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("type"));
        tableCourse.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("duration"));
        tableCourse.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("price"));
        for (int i = 0; i < tableCourse.getColumns().size(); i++) {
            tableCourse.getColumns().get(i).setStyle("-fx-alignment: center;");
            tableCourse.getColumns().get(i).setMinWidth(150);
        }
    }

    @FXML
    private void btnSelectStudentOnAction(ActionEvent event) {
        if (txtStudent.getText().isEmpty()) {
            Alert alert = AlertUtil.getAlert("Error!", "Please enter student Id", Alert.AlertType.ERROR);
            alert.showAndWait();
        } else {
            StudentDTO student = studentBO.getStudent(txtStudent.getText());
            if (student != null) {
                loadTable(student);
            }
        }
    }

    @FXML
    private void comboBoxStudentOnAction(ActionEvent event) {
        ArrayList<StudentDTO> allStudent = studentBO.getAllStudent();
        for (StudentDTO studentDTO : allStudent) {
            String select = comboBoxStudent.getSelectionModel().getSelectedItem();
            if (select != null) {
                if (select.substring(0, select.indexOf("-") - 1).equals(studentDTO.getId())) {
                    loadTable(studentDTO);
                }
            }
        }
    }

    private void loadComboBox() {
        comboBoxStudent.getItems().remove(0, comboBoxStudent.getItems().size());
        ArrayList<StudentDTO> allStudent = studentBO.getAllStudent();
        for (StudentDTO studentDTO : allStudent) {
            comboBoxStudent.getItems().add(studentDTO.getId() + " - " + studentDTO.getName());
        }
    }

    private void loadTable(StudentDTO studentDTO) {
        tableStudent.getItems().remove(0, tableStudent.getItems().size());
        tableCourse.getItems().remove(0, tableCourse.getItems().size());
        tableStudent.getItems().add(studentDTO);
        ArrayList<CourseDTO> studentCourses = studentBO.getStudentCourses(studentDTO.getId());
        if (studentCourses != null) {
            if (studentCourses.size() != 0) {
                tableCourse.setItems(FXCollections.observableArrayList(studentCourses));
            }
        }
    }
}
