package com.institute.controller.sub;

import com.institute.bo.BoFactory;
import com.institute.bo.BoType;
import com.institute.bo.custom.CourseBO;
import com.institute.dto.CourseDTO;
import com.institute.util.AlertUtil;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditCourseController implements Initializable {
    @FXML
    private TextField txtSelectCourse;

    @FXML
    private ComboBox<String> comboBoxCourse;

    @FXML
    private Button btnSelectCourse;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtType;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtPrice;

    @FXML
    private Button btnEditCourse;

    private CourseBO courseBO = BoFactory.getBo(BoType.COURSE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadComboBox();
        txtPrice.textProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d{0,6}?")) {
                txtPrice.setText(oldValue);
            }
        });
    }

    private void loadComboBox() {
        comboBoxCourse.getItems().remove(0, comboBoxCourse.getItems().size());
        ArrayList<CourseDTO> courses = courseBO.getCourses();
        for (CourseDTO cours : courses) {
            comboBoxCourse.getItems().add(cours.getId() + " - " + cours.getName());
        }
    }

    @FXML
    private void btnEditCourseOnAction(ActionEvent event) {
        if (txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtType.getText().isEmpty() | txtDuration.getText().isEmpty() | txtPrice.getText().isEmpty()) {
            Alert alert = AlertUtil.getAlert("Error!", "Please enter course details", Alert.AlertType.ERROR);
            alert.showAndWait();
        } else {
            boolean result = courseBO.editCourse(new CourseDTO(txtId.getText(), txtName.getText(), txtType.getText(), txtDuration.getText(),
                    Double.parseDouble(txtPrice.getText())));
            if (result) {
                Alert alert = AlertUtil.getAlert("Message!", "Successfully edited a course", Alert.AlertType.INFORMATION);
                alert.showAndWait();
                loadComboBox();
                clear();
            } else {
                Alert alert = AlertUtil.getAlert("Error!", "Uncaught error! course didn't edit", Alert.AlertType.ERROR);
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void btnSelectCourseOnAction(ActionEvent event) {
        if (txtSelectCourse.getText().isEmpty()) {
            Alert alert = AlertUtil.getAlert("Error!", "Please enter course id", Alert.AlertType.ERROR);
            alert.showAndWait();
        } else {
            CourseDTO course = courseBO.getCourse(txtSelectCourse.getText());
            if (course == null) {
                Alert alert = AlertUtil.getAlert("Error!", "Invalid course Id", Alert.AlertType.ERROR);
                alert.showAndWait();
            } else {
                txtId.setText(course.getId());
                txtName.setText(course.getName());
                txtType.setText(course.getType());
                txtDuration.setText(course.getDuration());
                txtPrice.setText(String.valueOf(course.getPrice()));
            }
        }
    }

    @FXML
    private void comboBoxCourseOnAction(ActionEvent event) {
        String selectedItem = comboBoxCourse.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            ArrayList<CourseDTO> courses = courseBO.getCourses();
            for (CourseDTO cours : courses) {
                if (selectedItem.substring(0, selectedItem.indexOf("-") - 1).equals(cours.getId())) {
                    txtId.setText(cours.getId());
                    txtName.setText(cours.getName());
                    txtType.setText(cours.getType());
                    txtDuration.setText(cours.getDuration());
                    txtPrice.setText(String.valueOf(cours.getPrice()));
                }
            }
        }
    }

    private void clear() {
        txtSelectCourse.setText("");
        comboBoxCourse.getSelectionModel().clearSelection();
        txtId.setText("");
        txtName.setText("");
        txtType.setText("");
        txtDuration.setText("");
        txtPrice.setText("");
    }
}
