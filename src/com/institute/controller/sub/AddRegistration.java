package com.institute.controller.sub;

import com.institute.bo.BoFactory;
import com.institute.bo.BoType;
import com.institute.bo.custom.RegistrationBO;
import com.institute.dto.CourseDTO;
import com.institute.dto.PaymentDTO;
import com.institute.dto.RegistrationDTO;
import com.institute.dto.StudentDTO;
import com.institute.util.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddRegistration implements Initializable {
    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtStudentName;

    @FXML
    private ComboBox<String> comboBoxStudent;

    @FXML
    private TextField txtStudent;

    @FXML
    private Button btnSelectStudent;

    @FXML
    private ComboBox<String> comboBoxCourse;

    @FXML
    private TextField txtRegistrationFee;

    @FXML
    private TextField txtDiscount;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Button btnAddRegistration;

    private RegistrationBO registrationBO = BoFactory.getBo(BoType.REGISTRATION);
    private ArrayList<CourseDTO> courses;
    private ArrayList<StudentDTO> students;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        courses = registrationBO.getCourses();
        students = registrationBO.getStudents();
        loadComboBox();
    }

    private void loadComboBox() {
        comboBoxStudent.getItems().remove(0, comboBoxStudent.getItems().size());
        comboBoxCourse.getItems().remove(0, comboBoxCourse.getItems().size());
        for (StudentDTO student : students) {
            comboBoxStudent.getItems().add(student.getId() + " - " + student.getName());
        }
        for (CourseDTO cours : courses) {
            comboBoxCourse.getItems().add(cours.getId() + " - " + cours.getName());
        }
    }

    @FXML
    private void btnAddRegistrationOnAction(ActionEvent event) {
        if (txtStudentId.getText().isEmpty() | comboBoxCourse.getSelectionModel().getSelectedItem() == null) {
            Alert alert = AlertUtil.getAlert("Error!", "Please enter Registration details", Alert.AlertType.ERROR);
            alert.showAndWait();
        } else {
            double fee = 0;
            if (!txtRegistrationFee.getText().isEmpty()) fee = Double.parseDouble(txtRegistrationFee.getText());
            RegistrationDTO registrationDTO = new RegistrationDTO(registrationBO.getNewId(), LocalDate.now().toString(),
                    fee, null, null, null);
            double discount = 0;
            if (!txtDiscount.getText().isEmpty()) discount = Double.parseDouble(txtDiscount.getText());
            PaymentDTO paymentDTO = new PaymentDTO(registrationBO.getNewPaymentId(), LocalDateTime.now(),
                    Double.parseDouble(lblTotal.getText().substring(0, lblTotal.getText().indexOf("/"))),
                    discount,
                    Double.parseDouble(lblNetTotal.getText().substring(0, lblNetTotal.getText().indexOf("/"))));
            boolean result = registrationBO.addRegistration(txtStudentId.getText(),
                    comboBoxCourse.getSelectionModel().getSelectedItem().substring(0, comboBoxCourse.getSelectionModel().getSelectedItem().indexOf("-") - 1),
                    registrationDTO, paymentDTO);
            if (result) {
                Alert alert = AlertUtil.getAlert("Message!", "Successfully added a new registration", Alert.AlertType.INFORMATION);
                alert.showAndWait();
            } else {
                Alert alert = AlertUtil.getAlert("Error!", "Uncaught error!", Alert.AlertType.ERROR);
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void btnSelectStudentOnAction(ActionEvent event) {
        if (txtStudent.getText().isEmpty()) {
            Alert alert = AlertUtil.getAlert("Error!", "Please enter student id", Alert.AlertType.ERROR);
            alert.showAndWait();
        } else {
            for (StudentDTO student : students) {
                if (txtStudent.getText().equals(student.getId())) {
                    txtStudentId.setText(student.getId());
                    txtStudentName.setText(student.getName());
                }
            }
        }
    }

    @FXML
    private void comboBoxCourseOnAction(ActionEvent event) {
        setTotal();
    }

    @FXML
    private void comboBoxStudentOnAction(ActionEvent event) {
        if (comboBoxStudent.getSelectionModel().getSelectedItem().isEmpty()) {
            Alert alert = AlertUtil.getAlert("Error!", "Please select student", Alert.AlertType.ERROR);
            alert.showAndWait();
        } else {
            String selectedItem = comboBoxStudent.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                for (StudentDTO student : students) {
                    if (selectedItem.substring(0, selectedItem.indexOf("-") - 1).equals(student.getId())) {
                        System.out.println(selectedItem);
                        System.out.println(selectedItem.substring(0, selectedItem.indexOf("-") - 1));
                        txtStudentId.setText(student.getId());
                        txtStudentName.setText(student.getName());
                    }
                }
            }
        }
    }

    @FXML
    private void txtDiscountOnKeyReleased(KeyEvent event) {
        setTotal();
    }

    @FXML
    private void txtRegistrationFeeOnKeyReleased(KeyEvent event) {
        setTotal();
    }

    private void clearAll() {
        comboBoxStudent.getSelectionModel().clearSelection();
        txtStudent.setText("");
        txtStudentId.setText("");
        txtStudentName.setText("");
        comboBoxCourse.getSelectionModel().clearSelection();
        txtRegistrationFee.setText("");
        lblTotal.setText("0.0/=");
        txtDiscount.setText("");
        lblNetTotal.setText("0.0/=");
    }

    private void setTotal() {
        String selectedItem = comboBoxCourse.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            double regFee = 0;
            double discount = 0;
            if (!txtRegistrationFee.getText().isEmpty()) regFee = Double.parseDouble(txtRegistrationFee.getText());
            if (!txtDiscount.getText().isEmpty()) discount = Double.parseDouble(txtDiscount.getText());
            for (CourseDTO cours : courses) {
                if (selectedItem.substring(0, selectedItem.indexOf("-") - 1).equals(cours.getId())) {
                    lblTotal.setText((regFee + cours.getPrice()) + "/=");
                    lblNetTotal.setText((Double.parseDouble(lblTotal.getText().substring(0, lblTotal.getText().indexOf("/"))) - discount) + "/=");
                }
            }
        }
    }
}
