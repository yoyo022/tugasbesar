package com.tubes.controller;

import com.tubes.Main;
import com.tubes.dao.UserDaoImpl;
import com.tubes.entity.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPassword;
    private ObservableList<UserEntity> userEntities;
    private UserDaoImpl userDao;
    private UserEntity userEntity;

    private Alert alert;
    @FXML
    private Button login;
    private MainFormController  mainFormController;
    private Parent scene;
    @FXML
    private VBox loginVbox;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

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
        alert = new Alert(Alert.AlertType.ERROR);
        UserDaoImpl userDao = new UserDaoImpl();
        UserEntity user = userDao.masuk(txtUser.getText(), txtPassword.getText());
        if (user == null){
            alert.setTitle("ERROR");
            alert.setContentText("Please fill username/password");
            alert.showAndWait();
        }else{
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("view/ItemForm.fxml"));
                VBox root = loader.load();
                ItemFormController controller = loader.getController();
                controller.setLoginController(this);
                controller.setUser(user);
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setTitle("Item Form");
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
                txtUser.clear();
                txtPassword.clear();
                Stage loginStage = (Stage) loginVbox.getScene().getWindow();
                loginStage.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            //Pindah ke form Item
        }
    }

    @FXML
    private void btnCancel(ActionEvent actionEvent) {
        Stage loginStage = (Stage) loginVbox.getScene().getWindow();
        loginStage.close();
    }

    public void setMainFormController(MainFormController mainFormController) {
        this.mainFormController = mainFormController;
    }

    @FXML
    private void userFieldAct(ActionEvent actionEvent) {
        alert = new Alert(Alert.AlertType.ERROR);
        UserDaoImpl userDao = new UserDaoImpl();
        UserEntity user = userDao.masuk(txtUser.getText(), txtPassword.getText());
        txtUser.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    if (user == null){
                        alert.setTitle("ERROR");
                        alert.setContentText("Please fill username/password");
                        alert.showAndWait();
                    }else{
                        try {
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(Main.class.getResource("view/ItemForm.fxml"));
                            VBox root = loader.load();
                            ItemFormController controller = loader.getController();
                            controller.setUser(user);
                            Stage stage = new Stage();
                            stage.setResizable(false);
                            stage.setTitle("Item Form");
                            stage.setScene(new Scene(root));
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.show();
                            txtUser.clear();
                            txtPassword.clear();
                            Stage loginStage = (Stage) loginVbox.getScene().getWindow();
                            loginStage.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //Pindah ke form Item
                    }
                }else{

                }
            }
        });
    }

    @FXML
    private void passFieldAct(ActionEvent actionEvent) {
        alert = new Alert(Alert.AlertType.ERROR);
        UserDaoImpl userDao = new UserDaoImpl();
        UserEntity user = userDao.masuk(txtUser.getText(), txtPassword.getText());
        txtPassword.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    if (user == null){
                        alert.setTitle("ERROR");
                        alert.setContentText("Please fill username/password");
                        alert.showAndWait();
                    }else{
                        try {
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(Main.class.getResource("view/ItemForm.fxml"));
                            VBox root = loader.load();
                            ItemFormController item =   loader.getController();
//                            item.setLoginController(this);
//                            login.setUserEntity(this);
                            ItemFormController controller = loader.getController();
                            controller.setUser(user);
                            Stage stage = new Stage();
                            stage.setResizable(false);
                            stage.setTitle("Item Form");
                            stage.setScene(new Scene(root));
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.show();
                            txtUser.clear();
                            txtPassword.clear();
                            Stage loginStage = (Stage) loginVbox.getScene().getWindow();
                            loginStage.close();
                            setUserEntity(user);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //Pindah ke form Item
                    }
                }else{

                }
            }
        });
    }
}
