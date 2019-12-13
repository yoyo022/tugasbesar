package com.tubes.controller;

import com.tubes.Main;
import com.tubes.dao.CategoriesDaoImpl;
import com.tubes.dao.ItemsDaoImpl;
import com.tubes.entity.CategoryEntity;
import com.tubes.entity.ItemEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.*;

public class ItemFormController implements Initializable {
    @FXML
    private TextField txtNamaItem;
    @FXML
    private TextField txtHarga;
    @FXML
    private ComboBox<CategoryEntity> comboCat;
    @FXML
    private Button btnOpenFile;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    private MainFormController mainFormController;
    private List<ItemEntity> itemEntities;
    private ItemsDaoImpl itemsDao;
    private ObservableList<CategoryEntity> categoryEntities;
    private CategoriesDaoImpl categoriesDao;
    private ItemEntity itemSelected;
    @FXML
    private TextField txtQuantity;
    @FXML
    private TilePane tilePane;
    @FXML
    private VBox itemVbox;
    private File fileImg;
    @FXML
    private Label fotoLabel;
    private Alert alert;


    public ItemEntity getItemSelected() {
        return itemSelected;
    }

    public void setItemSelected(ItemEntity itemSelected) {
        this.itemSelected = itemSelected;
    }

    //Mengambil item dari database
    public ItemsDaoImpl getItemsDao() {
        if(itemsDao == null){
            itemsDao = new ItemsDaoImpl();
        }
        return itemsDao;
    }
    //Mengambil item
    public List<ItemEntity> getItemEntities() {
        if(itemEntities == null){
            itemEntities = new ArrayList<>();
            itemEntities.addAll(getItemsDao().showAll());
        }
        return itemEntities;
    }

    public ObservableList<CategoryEntity> getCategoryEntities() {
        if (categoryEntities==null){
            categoryEntities = FXCollections.observableArrayList();
            categoryEntities.addAll(getCategoriesDao().showAll());
        }
        return categoryEntities;
    }

    public CategoriesDaoImpl getCategoriesDao() {
        if (categoriesDao == null){
            categoriesDao = new CategoriesDaoImpl();
        }
        return categoriesDao;
    }

    //Membuat image untuk item
    private ImageView buatImage(String photo){
        Image image = new Image("File:" + photo);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        return imageView;
    }
    //Membuat label quantity
    private Label buatQuantityLabel(int quantity){
        Label label = new Label();
        label.setText(String.valueOf(quantity));
        label.setAlignment(Pos.CENTER);
        return label;
    }
    //membuat label harga
    private Label buatHargaLabel(double harga){
        Label label = new Label();
        label.setText(Double.toString(harga));
        label.setAlignment(Pos.CENTER);
        return label;
    }
    //membuat label nama
    private Label buatNamaLabel(String nama){
        Label label = new Label();
        label.setText(nama);
        label.setAlignment(Pos.CENTER);
        return label;
    }


    private VBox makingVBox(ItemEntity item, Label namaLabel, Label hargaLabel, ImageView imgView, Label quantity){
        VBox vBox = new VBox();
        String simpenId = "item"+ item.toString();
        vBox.setId(simpenId);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-border-width: 1px; -fx-background-color: white;");
        vBox.getChildren().add(0,imgView);
        vBox.getChildren().add(1,namaLabel);
        vBox.getChildren().add(2,hargaLabel);
        vBox.getChildren().add(3,quantity);
        vBox.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            setItemSelected(item);
            txtNamaItem.setText(getItemSelected().getNama());
            txtHarga.setText(Double.toString(getItemSelected().getHarga()));
            txtQuantity.setText(Integer.toString(getItemSelected().getQuantity()));
            comboCat.setValue(getItemSelected().getCategoryByCategoryId());
            fotoLabel.setText(getItemSelected().getFoto());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        });
        vBox.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            hargaLabel.setStyle("-fx-background-color: salmon;");
            vBox.setStyle("-fx-background-color: red");
        });

        vBox.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            hargaLabel.setStyle("-fx-background-color: white");
            vBox.setStyle("-fx-background-color: white");
        });
        return vBox;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        comboCat.setItems(getCategoryEntities());
        itemEntities = getItemEntities();
        for(ItemEntity item : itemEntities){
            ImageView imgView = buatImage(item.getFoto());
            Label hrgLabel = buatHargaLabel(item.getHarga());
            Label namaLabel = buatNamaLabel(item.getNama());
            Label quantityLabel = buatQuantityLabel(item.getQuantity());

            VBox vBox = makingVBox(item, namaLabel, hrgLabel, imgView, quantityLabel);
            if(item.getQuantity()<=0){
                imgView.setOpacity(0.5);
                vBox.setDisable(true);
            }
            tilePane.getChildren().add(vBox);
        }
    }

    @FXML
    private void openFileAct(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        fileImg = chooser.showOpenDialog(itemVbox.getScene().getWindow());

            if(fileImg!=null){
                fotoLabel.setText(fileImg.getName());
                try{
                    Path sblm = Paths.get(fileImg.toURI().toString());
                    Path dirImg = Paths.get("com/tubes/img/"+fileImg.getName());
                    CopyOption[] options = new CopyOption[]{
                            StandardCopyOption.REPLACE_EXISTING,
                            StandardCopyOption.COPY_ATTRIBUTES
                    };
                    Files.copy(sblm, dirImg, options);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //            Image image = new Image(chooser.toString()); && Files.exists(fileImg.toPath(), LinkOption.NOFOLLOW_LINKS) && Files.isReadable(fileImg.toPath())
            }
    }

    @FXML
    private void saveAct(ActionEvent actionEvent) {
        alert = new Alert(Alert.AlertType.ERROR);
        if(!comboCat.getValue().equals(null) && fotoLabel.getText().equals("Choose Picture")  && !txtNamaItem.getText().trim().isEmpty() && !txtQuantity.getText().trim().isEmpty() && !txtHarga.getText().trim().isEmpty()){
            ItemEntity item = new ItemEntity();
            item.setNama(txtNamaItem.getText());
            item.setQuantity(Integer.parseInt(txtQuantity.getText()));
            item.setHarga(Double.parseDouble(txtHarga.getText()));
            item.setCategoryByCategoryId(comboCat.getValue());
            item.setFoto("com/tubes/img/"+fileImg.getName());
            boolean notFound = getItemEntities().stream().filter(d -> d.getId() == item.getId()).count() == 0;
            if(notFound){
                getItemsDao().addData(item);
                refresh();
                clearField();
            }else{
                alert.setTitle("Duplicate Item");
                alert.setContentText("Item sudah ada!");
                alert.showAndWait();
            }
        }else{
            alert.setTitle("INSERT ERROR");
            alert.setContentText("Tolong isi semua field yang tersedia!");
            alert.showAndWait();
        }
    }

    @FXML
    private void resetAct(ActionEvent actionEvent) {
        clearField();
    }

    @FXML
    private void updateAct(ActionEvent actionEvent) {
        if(!comboCat.getValue().equals(null) && fotoLabel.getText().equals("Choose Picture")  && !txtNamaItem.getText().trim().isEmpty() && !txtQuantity.getText().trim().isEmpty() && !txtHarga.getText().trim().isEmpty()){
            itemSelected.setNama(txtNamaItem.getText());
            itemSelected.setQuantity(Integer.parseInt(txtQuantity.getText()));
            itemSelected.setHarga(Double.parseDouble(txtHarga.getText()));
            itemSelected.setCategoryByCategoryId(comboCat.getValue());
            itemSelected.setFoto("com/tubes/img/"+fileImg.getName());
            getItemsDao().updateData(itemSelected);
            refresh();
            clearField();
        }else{
            alert.setTitle("UPDATE ERROR");
            alert.setContentText("Tolong isi semua field yang tersedia!");
            alert.showAndWait();
        }
    }

    @FXML
    private void deleteAct(ActionEvent actionEvent) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("DELETE CONFIRMATION");
        alert.setContentText("Yakin hapus item "+itemSelected.getNama()+"?");
        Optional<ButtonType> ok = alert.showAndWait();
        if(ok.get()==ButtonType.OK){
            getItemsDao().deleteData(itemSelected);
        }
        refresh();
        clearField();
    }

    @FXML
    private void closeAct(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/LoginForm.fxml"));
            VBox root = loader.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Login Form");
            stage.setScene(new Scene(root));
            stage.show();

            Stage itemStage = (Stage) itemVbox.getScene().getWindow();
            itemStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void refresh(){
        tilePane.getChildren().clear();
        itemEntities = getItemEntities();
        for(ItemEntity item : itemEntities){
            ImageView imgView = buatImage(item.getFoto());
            Label hrgLabel = buatHargaLabel(item.getHarga());
            Label namaLabel = buatNamaLabel(item.getNama());
            Label quantityLabel = buatQuantityLabel(item.getQuantity());

            VBox vBox = makingVBox(item, namaLabel, hrgLabel, imgView, quantityLabel);
            if(item.getQuantity()<=0){
                imgView.setOpacity(0.5);
                vBox.setDisable(true);
            }
            tilePane.getChildren().add(vBox);
        }
    }
    private void clearField(){
        txtHarga.clear();
        txtNamaItem.clear();
        txtQuantity.clear();
        comboCat.setValue(null);
        fotoLabel.setText("Choose Picture");
        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }
}
