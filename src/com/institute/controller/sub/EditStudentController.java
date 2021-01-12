package com.institute.controller.sub;

import com.institute.bo.BoFactory;
import com.institute.bo.BoType;
import com.institute.bo.custom.StudentBO;
import com.institute.dto.StudentDTO;
import com.institute.util.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditStudentController implements Initializable {
    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private DatePicker datePickerBirthday;

    @FXML
    private RadioButton radioMale;

    @FXML
    private RadioButton radioFemale;

    @FXML
    private ComboBox<String> comboBoxStudent;

    @FXML
    private TextField txtStudent;

    @FXML
    private Button btnSelectStudent;

    @FXML
    private Button btnEditStudent;

    private StudentBO studentBO = BoFactory.getBo(BoType.STUDENT);
    private ToggleGroup gender = new ToggleGroup();
    private StudentDTO studentDTO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        radioMale.setToggleGroup(gender);
        radioFemale.setToggleGroup(gender);
        loadComboBox();
    }

    private void loadComboBox() {
        comboBoxStudent.getItems().remove(0, comboBoxStudent.getItems().size());
        ArrayList<StudentDTO> allStudent = studentBO.getAllStudent();
        for (StudentDTO studentDTO : allStudent) {
            comboBoxStudent.getItems().add(studentDTO.getId() + " - " + studentDTO.getName());
        }
    }

    @FXML
    private void btnEditStudentOnAction(ActionEvent event) {
        RadioButton gender = (RadioButton) this.gender.getSelectedToggle();
        boolean result = studentBO.editStudent(new StudentDTO(txtId.getText(), txtName.getText(), txtAddress.getText(), txtContact.getText(),
                datePickerBirthday.getValue().toString(), gender.getText(), this.studentDTO.getDate_joined()));
        if (result) {
            Alert alert = AlertUtil.getAlert("Message", "Successfully edita a student", Alert.AlertType.INFORMATION);
            alert.showAndWait();
            clear();
            loadComboBox();
        } else {
            Alert alert = AlertUtil.getAlert("Error!", "Uncaught error! Student didn't edit", Alert.AlertType.ERROR);
            alert.showAndWait();
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
                put(student);
                this.studentDTO = student;
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
                    put(studentDTO);
                    this.studentDTO = studentDTO;
                }
            }
        }
    }

    private void put(StudentDTO studentDTO) {
        txtId.setText(studentDTO.getId());
        txtName.setText(studentDTO.getName());
        txtAddress.setText(studentDTO.getAddress());
        txtContact.setText(studentDTO.getContact());
        datePickerBirthday.setValue(LocalDate.parse(studentDTO.getBirthday()));
        if (studentDTO.getGender().equals("Male")) {
            gender.selectToggle(radioMale);
        } else if (studentDTO.getGender().equals("Female")) {
            gender.selectToggle(radioFemale);
        }
    }

    private void clear() {
        comboBoxStudent.getSelectionModel().clearSelection();
        txtStudent.setText("");
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        datePickerBirthday.setValue(null);
    }
}
