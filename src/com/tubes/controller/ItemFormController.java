package com.tubes.controller;

import com.tubes.dao.CategoriesDaoImpl;
import com.tubes.dao.ItemsDaoImpl;
import com.tubes.entity.CategoryEntity;
import com.tubes.entity.ItemEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

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
    private List<ItemEntity> itemEntities;
    private ItemsDaoImpl itemsDao;
    private ObservableList<CategoryEntity> categoryEntities;
    private CategoriesDaoImpl categoriesDao;
    private ItemEntity itemSelected;
    @FXML
    private TextField txtQuantity;
    @FXML
    private TilePane tilePane;


    public ItemEntity getItemSelected() {
        return itemSelected;
    }

    public void setItemSelected(ItemEntity itemSelected) {
        this.itemSelected = itemSelected;
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

//    private boolean oneItemSelected(){
//        boolean selected = false;
//        if(getItemSelected() == null){
//        }
//        else{
//            selected = true;
//            Label namaLabel = buatNamaLabel(getItemSelected().getNama());
//            ImageView imgView = buatImage(getItemSelected().getFoto());
//            Label hrgLabel = buatHargaLabel(getItemSelected().getHarga());
//            VBox vBox = makingVBox2(getItemSelected(), namaLabel, hrgLabel,imgView);
//            vBoxx.getChildren().add(vBox);
//        }
////        }
//        return selected;
//    }

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
//            boolean check = oneItemSelected();
//            if(check){
//                if(itemSelected.getQuantity() <= 1) {
//                    vBox.setDisable(true);
//                    hargaLabel.setText("- HABIS -");
//                }
//            }
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
        itemEntities = getItemEntities();
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
