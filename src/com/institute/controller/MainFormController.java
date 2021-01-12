package com.institute.controller;

import com.institute.bo.BoFactory;
import com.institute.bo.BoType;
import com.institute.bo.custom.MainFormBO;
import com.institute.entity.User;
import com.institute.util.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
    private static User user;
    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    private MainFormBO mainFormBO = BoFactory.getBo(BoType.MAINFORM);

    public static User getUser() {
        return user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtUsername.requestFocus();
    }

    @FXML
    private void btnLoginOnAction(ActionEvent event) {
        if (txtUsername.getText().isEmpty() | txtPassword.getText().isEmpty()) {
            Alert alert = AlertUtil.getAlert("Error!", "Please enter username and password", Alert.AlertType.ERROR);
            alert.showAndWait().ifPresent(e -> {
                if (e == ButtonType.CLOSE) {
                    alert.close();
                }
            });
        } else {
            User user = mainFormBO.checkLogin(txtUsername.getText(), txtPassword.getText());
            if (user == null) {
                Alert alert = AlertUtil.getAlert("Error!", "Invalid credentials", Alert.AlertType.ERROR);
                alert.showAndWait().ifPresent(e -> {
                    if (e == ButtonType.CLOSE) {
                        alert.close();
                    }
                });
            } else {
                MainFormController.user = user;
                Stage stage = (Stage) this.root.getScene().getWindow();
                try {
                    Parent parent = FXMLLoader.load(this.getClass().getResource("/com/institute/view/Home.fxml"));
                    stage.setScene(new Scene(parent));
                    stage.centerOnScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
