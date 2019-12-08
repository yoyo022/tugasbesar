package com.tubes.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {
    @FXML
    private TextField txtNamaItem;
    @FXML
    private TextField txtHarga;
    @FXML
    private ComboBox comboCat;
    @FXML
    private Button btnOpenFile;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void openFileAct(ActionEvent actionEvent) {
    }

    @FXML
    private void saveAct(ActionEvent actionEvent) {
    }

    @FXML
    private void resetAct(ActionEvent actionEvent) {
    }

    @FXML
    private void updateAct(ActionEvent actionEvent) {
    }

    @FXML
    private void deleteAct(ActionEvent actionEvent) {
    }
}
