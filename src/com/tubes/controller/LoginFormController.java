package com.tubes.controller;

import com.tubes.dao.UserDaoImpl;
import com.tubes.entity.UserEntity;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtPassword;
    private ObservableList<UserEntity> userEntities;
    private UserDaoImpl userDao;
    private Alert alert;

    public List<UserEntity> getUserEntities() {
        if (userEntities == null){
            userEntities = FXCollections.observableArrayList();
            userEntities.addAll(getUserDao().showAll());
        }
        return userEntities;
    }

    public UserDaoImpl getUserDao() {
        if (userDao==null){
            userDao = new UserDaoImpl();
        }
        return userDao;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void btnLogin(ActionEvent actionEvent) {
        UserEntity u = new UserEntity();
        u.setUsername(txtUser.getText());
        u.setPassword(txtPassword.getText());
        alert = new Alert(Alert.AlertType.ERROR);
        if (u.getUsername().equals("") || u.getPassword().equals("")){
            alert.setTitle("ERROR");
            alert.setContentText("Please fill username/password");
            alert.showAndWait();
        }else{
            for(UserEntity user : userEntities){
                if(user.getUsername().equals(u.getUsername())){
                    if (user.getPassword().equals(u.getPassword())){
                        //Pindah ke form Item
                    }else {
                        alert.setTitle("ERROR");
                        alert.setContentText("Username/Password yang dimasukkan salah");
                        alert.showAndWait();
                        break;
                    }
                }else {
                    alert.setTitle("ERROR");
                    alert.setContentText("Username/Password yang dimasukkan salah");
                    alert.showAndWait();
                    break;
                }
            }
        }
    }

    @FXML
    private void btnCancel(ActionEvent actionEvent) {
        Platform.exit();
    }
}
