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
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCourseController implements Initializable {
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
    private Button btnAddCourse;

    private CourseBO courseBO = BoFactory.getBo(BoType.COURSE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtId.setText(courseBO.getNewCourseId());
        txtPrice.textProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d{0,6}?")) {
                txtPrice.setText(oldValue);
            }
        });
    }

    @FXML
    private void btnAddCourseOnAction(ActionEvent event) {
        if (txtName.getText().isEmpty() | txtType.getText().isEmpty() | txtDuration.getText().isEmpty() | txtPrice.getText().isEmpty()) {
            Alert alert = AlertUtil.getAlert("Error!", "Please enter course details", Alert.AlertType.ERROR);
            alert.showAndWait();
        } else {
            boolean result = courseBO.addCourse(new CourseDTO(txtId.getText(), txtName.getText(), txtType.getText(), txtDuration.getText(),
                    Double.parseDouble(txtPrice.getText())));
            if (result) {
                Alert alert = AlertUtil.getAlert("Message!", "Course is successfully added", Alert.AlertType.INFORMATION);
                alert.showAndWait();
                clear();
            } else {
                Alert alert = AlertUtil.getAlert("Error!", "Uncaught Error! course didn't add", Alert.AlertType.ERROR);
                alert.showAndWait();
            }
        }
    }

    private void clear() {
        txtId.setText(courseBO.getNewCourseId());
        txtName.setText("");
        txtType.setText("");
        txtDuration.setText("");
        txtPrice.setText("");
    }
}
