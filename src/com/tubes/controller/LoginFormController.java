package com.tubes.controller;

import com.tubes.Main;
import com.tubes.dao.UserDaoImpl;
import com.tubes.entity.UserEntity;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPassword;
    private ObservableList<UserEntity> userEntities;
    private UserDaoImpl userDao;
    private Alert alert;
    @FXML
    private Button login;

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
            for(UserEntity user : getUserEntities()){
                if(user.getUsername().equals(u.getUsername())){
                    if (user.getPassword().equals(u.getPassword())){
                        try {
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(Main.class.getResource("view/ItemForm.fxml"));
                            VBox root = loader.load();

                            Stage stage = new Stage();
                            stage.setResizable(false);
                            stage.setTitle("Item Form");
                            stage.setScene(new Scene(root));
                            stage.show();

//                            Stage primaryStage = (Stage) login.getScene().getWindow();
//                            primaryStage.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                        //Pindah ke form Item
                    }
                    else {
                        alert.setTitle("ERROR");
                        alert.setContentText("Username/Password yang dimasukkan salah");
                        alert.showAndWait();
                        break;
                    }
                }
                else  {
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
