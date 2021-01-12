package com.institute.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private AnchorPane boxDashboard;

    @FXML
    private AnchorPane boxStudents;

    @FXML
    private AnchorPane boxCourses;

    @FXML
    private AnchorPane boxRegistrations;

    @FXML
    private AnchorPane boxPayments;

    @FXML
    private AnchorPane boxUsers;

    @FXML
    private AnchorPane boxLogins;

    @FXML
    private AnchorPane container;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boxDashboard.setId("boxDashboard");
        boxStudents.setId("boxStudents");
        boxCourses.setId("boxCourses");
        boxRegistrations.setId("boxRegistrations");
        boxPayments.setId("boxPayments");
        boxUsers.setId("boxUsers");
        boxLogins.setId("boxLogins");
        try {
            Parent parent = FXMLLoader.load(this.getClass().getResource("/com/institute/view/Dashboard.fxml"));
            container.getChildren().setAll(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void boxDashboardOnMouseClicked(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(this.getClass().getResource("/com/institute/view/Dashboard.fxml"));
            container.getChildren().setAll(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void boxStudentsOnMouseClicked(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(this.getClass().getResource("/com/institute/view/Student.fxml"));
            container.getChildren().setAll(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void boxCoursesOnMouseClicked(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(this.getClass().getResource("/com/institute/view/Course.fxml"));
            container.getChildren().setAll(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void boxRegistrationsOnMouseClicked(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(this.getClass().getResource("/com/institute/view/Registration.fxml"));
            container.getChildren().setAll(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void boxPaymentsOnMouseClicked(MouseEvent event) {

    }

    @FXML
    private void boxUsersOnMouseClicked(MouseEvent event) {

    }

    @FXML
    private void boxLoginsOnMouseClicked(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(this.getClass().getResource("/com/institute/view/Login.fxml"));
            container.getChildren().setAll(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
