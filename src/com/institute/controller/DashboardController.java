package com.institute.controller;

import com.institute.bo.BoFactory;
import com.institute.bo.BoType;
import com.institute.bo.custom.DashboardBO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private Label lblTotalStudents;

    @FXML
    private Label lblTotalCourses;

    @FXML
    private Label lblTotalPayments;

    @FXML
    private Label lblTotalUsers;

    @FXML
    private Label lblDateTime;

    private DashboardBO dashboardBO = BoFactory.getBo(BoType.DASHBOARD);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadBoxes();
        loadTime();
    }

    private void loadBoxes() {
        lblTotalStudents.setText(String.valueOf(dashboardBO.getTotalStudents()));
        lblTotalCourses.setText(String.valueOf(dashboardBO.getTotalCourses()));
        lblTotalPayments.setText(String.valueOf(dashboardBO.getTotalPayments()));
        lblTotalUsers.setText(String.valueOf(dashboardBO.getTotalUsers()));
    }

    private void loadTime() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            lblDateTime.setText(LocalDate.now().toString() + " " + LocalTime.now().toString().substring(0, 8));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
