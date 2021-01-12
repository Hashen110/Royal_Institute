package com.institute.controller.sub;

import com.institute.bo.BoFactory;
import com.institute.bo.BoType;
import com.institute.bo.custom.StudentBO;
import com.institute.dto.CourseDTO;
import com.institute.dto.PaymentDTO;
import com.institute.dto.RegistrationDTO;
import com.institute.dto.StudentDTO;
import com.institute.util.AlertUtil;
import javafx.beans.value.ObservableValue;
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

public class AddStudentController implements Initializable {
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
    private Button btnAddStudent;

    private StudentBO studentBO = BoFactory.getBo(BoType.STUDENT);
    private ToggleGroup gender = new ToggleGroup();
    private ArrayList<CourseDTO> courses;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextField[] textFields = {txtRegistrationFee, txtDiscount};
        for (TextField textField : textFields) {
            textField.textProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> {
                if (!newValue.matches("\\d{0,5}?")) {
                    textField.setText(oldValue);
                }
            });
        }
        txtId.setText(studentBO.getNewId());
        radioMale.setToggleGroup(gender);
        radioFemale.setToggleGroup(gender);
        courses = studentBO.getCourses();
        for (CourseDTO cours : courses) {
            comboBoxCourse.getItems().add(cours.getId() + " - " + cours.getName());
        }
    }

    private void setTotal() {
        String selectedItem = comboBoxCourse.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            if (txtRegistrationFee.getText().isEmpty()) {
                for (CourseDTO cours : courses) {
                    if (selectedItem.substring(0, selectedItem.indexOf("-") - 1).equals(cours.getId())) {
                        lblTotal.setText(cours.getPrice() + "/=");
                        if (txtDiscount.getText().isEmpty()) {
                            lblNetTotal.setText(cours.getPrice() + "/=");
                        } else {
                            int discount = Integer.parseInt(txtDiscount.getText());
                            lblNetTotal.setText((cours.getPrice() - discount) + "/=");
                        }
                    }
                }
            } else {
                for (CourseDTO cours : courses) {
                    if (selectedItem.substring(0, selectedItem.indexOf("-") - 1).equals(cours.getId())) {
                        int regFee = Integer.parseInt(txtRegistrationFee.getText());
                        lblTotal.setText((regFee + cours.getPrice()) + "/=");
                        if (txtDiscount.getText().isEmpty()) {
                            lblNetTotal.setText((regFee + cours.getPrice()) + "/=");
                        } else {
                            int discount = Integer.parseInt(txtDiscount.getText());
                            lblNetTotal.setText(((cours.getPrice() + regFee) - discount) + "/=");
                        }
                    }
                }
            }
        }
    }

    @FXML
    private void comboBoxCourseOnAction(ActionEvent event) {
        setTotal();
    }

    @FXML
    private void btnAddStudentOnAction(ActionEvent event) {
        if (txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty() | datePickerBirthday.getValue() == null
                | comboBoxCourse.getSelectionModel().getSelectedItem() == null) {
            Alert alert = AlertUtil.getAlert("Error!", "Please enter student details", Alert.AlertType.ERROR);
            alert.showAndWait();
        } else {
            RadioButton gender = (RadioButton) this.gender.getSelectedToggle();
            if (gender == null) {
                Alert alert = AlertUtil.getAlert("Error!", "Please select gender", Alert.AlertType.ERROR);
                alert.showAndWait();
            } else {
                StudentDTO studentDTO = new StudentDTO(txtId.getText(), txtName.getText(), txtAddress.getText(), txtContact.getText(),
                        datePickerBirthday.getValue().toString(), gender.getText(), LocalDateTime.now().toString());
                double regFee = 0;
                if (!txtRegistrationFee.getText().isEmpty()) regFee = Double.parseDouble(txtRegistrationFee.getText());
                RegistrationDTO registrationDTO = new RegistrationDTO(studentBO.getNewRegId(), LocalDate.now().toString(), regFee, null, null, null);
                double discount = 0;
                if (!txtDiscount.getText().isEmpty()) discount = Double.parseDouble(txtDiscount.getText());
                PaymentDTO paymentDTO = new PaymentDTO(studentBO.getNewPaymentId(), LocalDateTime.now(),
                        Double.parseDouble(lblTotal.getText().substring(0, lblTotal.getText().indexOf("/"))),
                        discount,
                        Double.parseDouble(lblNetTotal.getText().substring(0, lblNetTotal.getText().indexOf("/"))));
                boolean result = studentBO.addStudent(studentDTO, registrationDTO, paymentDTO,
                        comboBoxCourse.getSelectionModel().getSelectedItem().substring(0, comboBoxCourse.getSelectionModel().getSelectedItem().indexOf("-") - 1));
                if (result) {
                    Alert alert = AlertUtil.getAlert("Message!", "Successfully added a new student", Alert.AlertType.INFORMATION);
                    alert.showAndWait();
                    clearAll();
                } else {
                    Alert alert = AlertUtil.getAlert("Error!", "Uncaught error! didn't add student", Alert.AlertType.ERROR);
                    alert.showAndWait();
                }
            }
        }
    }

    private void clearAll() {
        txtId.setText(studentBO.getNewId());
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        datePickerBirthday.setValue(null);
        comboBoxCourse.getSelectionModel().clearSelection();
        txtRegistrationFee.setText("");
        lblTotal.setText("0.0/=");
        txtDiscount.setText("");
        lblNetTotal.setText("0.0/=");
    }

    @FXML
    private void txtDiscountOnKeyReleased(KeyEvent event) {
        setTotal();
    }

    @FXML
    private void txtRegistrationFeeOnKeyReleased(KeyEvent event) {
        setTotal();
    }

}
