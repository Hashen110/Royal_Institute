package com.institute.controller;

import com.institute.bo.BoFactory;
import com.institute.bo.BoType;
import com.institute.bo.custom.RegistrationBO;
import com.institute.dto.RegistrationDTO;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {
    @FXML
    private Label lblTotalRegistrations;

    @FXML
    private Label lblTodayRegistrations;

    @FXML
    private Label lblYesterdayRegistrations;

    @FXML
    private Label lblDateTime;

    @FXML
    private TableView<RegistrationDTO> tableRegistration;

    @FXML
    private Button btnAddRegistration;

    @FXML
    private Button btnEditRegistration;

    @FXML
    private Label lblTableCount;

    private RegistrationBO registrationBO = BoFactory.getBo(BoType.REGISTRATION);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTime();
        loadBoxes();
        generateTable();
        loadTable();
    }

    private void loadTable() {
        tableRegistration.getItems().remove(0, tableRegistration.getItems().size());
        tableRegistration.setItems(FXCollections.observableArrayList(registrationBO.getRegistrations()));
        lblTableCount.setText("No Of Records : " + registrationBO.getRegistrations().size());
    }

    private void generateTable() {
        tableRegistration.setStyle("-fx-font-size: 16px;");
        tableRegistration.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tableRegistration.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
        tableRegistration.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("fee"));
        tableRegistration.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("payment"));
        tableRegistration.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("student"));
        tableRegistration.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("course"));
        for (int i = 0; i < tableRegistration.getColumns().size(); i++) {
            tableRegistration.getColumns().get(i).setPrefWidth(191);
            tableRegistration.getColumns().get(i).setStyle("-fx-alignment: center;");
        }
    }

    private void loadBoxes() {
        lblTotalRegistrations.setText(String.valueOf(registrationBO.getTotalRegistrations()));
        lblTodayRegistrations.setText(String.valueOf(registrationBO.getTodayRegistrations()));
        lblYesterdayRegistrations.setText(String.valueOf(registrationBO.getYesterdayRegistrations()));
    }

    private void loadTime() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            lblDateTime.setText(LocalDate.now().toString() + " " + LocalTime.now().toString().substring(0, 8));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    @FXML
    private void btnAddRegistrationOnAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/institute/view/sub/AddRegistration.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add Registration");
        stage.setOnCloseRequest(e -> {
            loadBoxes();
            loadTable();
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
    private void btnEditRegistrationOnAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/institute/view/sub/EditRegistration.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Edit Registration");
        stage.setOnCloseRequest(e -> {
            loadBoxes();
            loadTable();
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
}
