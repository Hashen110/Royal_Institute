package com.institute.controller;

import com.institute.bo.BoFactory;
import com.institute.bo.BoType;
import com.institute.bo.custom.LoginBO;
import com.institute.dto.LoginDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Label lblTotalLogin;
    @FXML
    private Label lblTodayLogin;
    @FXML
    private Label lblYesterdayLogin;
    @FXML
    private Label lblThisMonthLogin;
    @FXML
    private TableView<LoginDTO> tableLogin;
    @FXML
    private Label lblTableCount;
    @FXML
    private Label lblDateTime;
    @FXML
    private ComboBox<String> comboBoxLogin;

    private LoginBO loginBO = BoFactory.getBo(BoType.LOGIN);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadBoxes();
        loadTime();
        comboBoxLogin.getItems().addAll("All Logins", "Today Logins", "Yesterday Logins", "This Month Logins", "Past Month Logins");
        comboBoxLogin.getSelectionModel().select("All Logins");
        generateTable();
        loadTable(comboBoxLogin.getSelectionModel().getSelectedItem());
    }

    private void generateTable() {
        tableLogin.setStyle("-fx-font-size: 16px;");
        tableLogin.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tableLogin.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date_login"));
        tableLogin.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("username"));
        tableLogin.getColumns().get(0).setPrefWidth(383);
        tableLogin.getColumns().get(1).setPrefWidth(383);
        tableLogin.getColumns().get(2).setPrefWidth(383);
        tableLogin.getColumns().get(0).setStyle("-fx-alignment: center;");
        tableLogin.getColumns().get(1).setStyle("-fx-alignment: center;");
        tableLogin.getColumns().get(2).setStyle("-fx-alignment: center;");
    }

    private void loadTable(String selectedItem) {
        if(selectedItem != null){
            tableLogin.getItems().remove(0, tableLogin.getItems().size());
            if(selectedItem.equals("All Logins")){
                ArrayList<LoginDTO> totalLogin = loginBO.getTotalLogin();
                if(totalLogin != null){
                    tableLogin.setItems(FXCollections.observableArrayList(totalLogin));
                    lblTableCount.setText("No Of Records : " + totalLogin.size());
                }
            }else if(selectedItem.equals("Today Logins")){
                ArrayList<LoginDTO> totalLogin = loginBO.getTodayLogin();
                if(totalLogin != null){
                    tableLogin.setItems(FXCollections.observableArrayList(totalLogin));
                    lblTableCount.setText("No Of Records : " + totalLogin.size());
                }
            }else if(selectedItem.equals("Yesterday Logins")){
                ArrayList<LoginDTO> totalLogin = loginBO.getYesterdayLogin();
                if(totalLogin != null){
                    tableLogin.setItems(FXCollections.observableArrayList(totalLogin));
                    lblTableCount.setText("No Of Records : " + totalLogin.size());
                }
            }else if(selectedItem.equals("This Month Logins")){
                ArrayList<LoginDTO> totalLogin = loginBO.getThisMonthLogin();
                if(totalLogin != null){
                    tableLogin.setItems(FXCollections.observableArrayList(totalLogin));
                    lblTableCount.setText("No Of Records : " + totalLogin.size());
                }
            }else if(selectedItem.equals("Past Month Logins")){
                ArrayList<LoginDTO> totalLogin = loginBO.getPastMonthLogin();
                if(totalLogin != null){
                    tableLogin.setItems(FXCollections.observableArrayList(totalLogin));
                    lblTableCount.setText("No Of Records : " + totalLogin.size());
                }
            }
        }
    }

    private void loadBoxes() {
        lblTotalLogin.setText(String.valueOf(loginBO.totalLogin()));
        lblTodayLogin.setText(String.valueOf(loginBO.todayLogin()));
        lblYesterdayLogin.setText(String.valueOf(loginBO.yesterdayLogin()));
        lblThisMonthLogin.setText(String.valueOf(loginBO.thisMonthLogin()));
    }

    @FXML
    void comboBoxLoginOnAction(ActionEvent event) {
        tableLogin.getItems().remove(0, tableLogin.getItems().size());
        loadTable(comboBoxLogin.getSelectionModel().getSelectedItem());
    }

    private void loadTime() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            lblDateTime.setText(LocalDate.now().toString() + " " + LocalTime.now().toString().substring(0, 8));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
