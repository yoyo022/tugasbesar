package com.tubes.controller;

import com.tubes.dao.ItemsDaoImpl;
import com.tubes.entity.ItemEntity;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
    @FXML
    private TilePane tilePane;
    private ItemsDaoImpl itemsDao;
    private List<ItemEntity> itemEntities;
    private List<String> money = new ArrayList<>();
    private ItemEntity itemSelected;
    private Alert alert;
    @FXML
    private VBox vBoxx;
    @FXML
    private TilePane tilePaneMoney;
    private int moneyId = 1;
    private VBox vBox3 = new VBox();
    @FXML
    private ImageView imgViewMoney2;
    @FXML
    private TilePane tilePaneTest;
    @FXML
    private Button cancel;
    @FXML
    private Button buy;

    //Isi list gambar uang
    public List<String> getMoney() {
        money.add("../src/com/tubes/img/duit/seratusribu.png");
        money.add("../src/com/tubes/img/duit/limapuluhrb.png");
        money.add("../src/com/tubes/img/duit/duapuluhrb.png");
        money.add("../src/com/tubes/img/duit/sepuluhrb.png");
        money.add("../src/com/tubes/img/duit/limarb.png");
        money.add("../src/com/tubes/img/duit/duarb.png");
        money.add("../src/com/tubes/img/duit/seribu.png");
        return money;
    }
    //Pembungkus Uangnya
    public VBox buatVBoxMoney(ImageView imgView){
        VBox vBox = new VBox();
        vBox.setStyle("-fx-border-width: 1px");
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(0,imgView);
        return vBox;
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

    //Membuat imageview uang
    public ImageView buatMoney(String url){
        Image img = new Image("File:src/" + url);
        ImageView imgView = new ImageView(img);
        imgView.setId(String.valueOf(moneyId));
        imgView.setFitWidth(120);
        imgView.setFitHeight(50);
        imgView.setOnDragDetected(event -> {
            Dragboard dragboard = imgView.startDragAndDrop(TransferMode.ANY);
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putImage(imgView.getImage());
            dragboard.setContent(clipboardContent);
            event.consume();
        });
        moneyId+=1;
        return imgView;
    }
    //Membuat vbox getAllItem (All Item)
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
            boolean check = oneItemSelected();
            if(check){
                if(itemSelected.getQuantity() <= 1) {
                    vBox.setDisable(true);
                    hargaLabel.setText("- HABIS -");
                }
            }
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
    //Membuat vbox getOneItem
    private VBox makingVBox2(ItemEntity item, Label namaLabel, Label hargaLabel, ImageView imgView){
        String simpenId = "item"+ item.toString();
        vBox3.getChildren().clear();
        vBox3.getChildren().clear();
        vBox3.getChildren().clear();
        vBox3.setId(simpenId);
        vBox3.setAlignment(Pos.CENTER);
        vBox3.setStyle("-fx-border-width: 1px; -fx-background-color: white;");
        vBox3.getChildren().add(0,imgView);
        vBox3.getChildren().add(1,namaLabel);
        vBox3.getChildren().add(2,hargaLabel);

        return vBox3;
    }

    //Proses Memilih item (getOneItem)
    private boolean oneItemSelected(){
        boolean selected = false;
        if(getItemSelected() == null){
        }
        else{
                selected = true;
                Label namaLabel = buatNamaLabel(getItemSelected().getNama());
                ImageView imgView = buatImage(getItemSelected().getFoto());
                Label hrgLabel = buatHargaLabel(getItemSelected().getHarga());
                VBox vBox = makingVBox2(getItemSelected(), namaLabel, hrgLabel,imgView);
                vBoxx.getChildren().add(vBox);
            }
//        }
        return selected;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imgViewMoney2.setStyle("-fx-border-width: 1px; -fx-border-color: black;");
        tilePaneTest.setVisible(false);
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
        money = getMoney();
        for(String link : money){
            ImageView imageView =  buatMoney(link);
            VBox vBox =  buatVBoxMoney(imageView);
            tilePaneMoney.getChildren().add(vBox);
        }
        tilePaneMoney.setStyle("-fx-alignment: center;-fx-background-color: white;");
    }

    public ItemEntity getItemSelected() {
        return itemSelected;
    }

    public void setItemSelected(ItemEntity itemSelected) {
        this.itemSelected = itemSelected;
    }
    //Drop image money
    @FXML
    private void dropMoney(DragEvent event) {
//      masih ada yg salah
//      Masih ada yang salah
//        masih ada yang salah
        ImageView imgView = (ImageView) event.getGestureSource();
        double nilaiMoney = 0;
        String idMoney = imgView.getId();
        switch (idMoney){
            case "1" :{nilaiMoney=100000; imgViewMoney2.setImage(imgView.getImage()); break;}
            case "2" :{nilaiMoney=50000; imgViewMoney2.setImage(imgView.getImage()); break;}
            case "3" :{nilaiMoney=20000; imgViewMoney2.setImage(imgView.getImage()); break;}
            case "4" :{nilaiMoney=10000; imgViewMoney2.setImage(imgView.getImage()); break;}
            case "5" :{nilaiMoney=5000; imgViewMoney2.setImage(imgView.getImage()); break;}
            case "6" :{nilaiMoney=2000; imgViewMoney2.setImage(imgView.getImage()); break;}
            case "7" :{nilaiMoney=1000; imgViewMoney2.setImage(imgView.getImage()); break;}
        }
        if(nilaiMoney < getItemSelected().getHarga()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gagal Membeli");
            alert.setContentText("Uang yang dimasukkan tidak cukup untuk melakukan transaksi");
            alert.showAndWait();
        }else{
            pembayaran(nilaiMoney);
        }
    }
    //Proses Pembelian
    private void pembayaran(double uang){
        ItemsDaoImpl itemsDao = new ItemsDaoImpl();
        getItemSelected().setQuantity(getItemSelected().getQuantity()-1);
        itemsDao.updateData(getItemSelected());
        if(uang > getItemSelected().getHarga()){
            double kembalian =  uang - getItemSelected().getHarga();
            buatKembalian(kembalian);
        }
        else{
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Terimakasih sudah membeli " + getItemSelected().getNama());
            alert.show();
        }
    }
    //Menentukan jumlah lembar uang kembalian
    private void loopingKembalian(String url, int duit){
        for(int i=0;i<duit; i++){
            Image image = new Image(url);
            ImageView imgView = new ImageView(image);
            imgView.setFitHeight(60);
            imgView.setFitWidth(140);
            tilePane.getChildren().add(imgView);
        }
    }

    private void buatKembalian(double uang){
        TilePane tilePane = new TilePane();
        tilePane.setAlignment(Pos.CENTER);
        tilePane.setPadding(new Insets(7));
        tilePane.setHgap(15);
        tilePane.setVgap(15);
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        double kembalian = uang;

        int duit100rb = (int) uang/100000;
        uang = uang%100000;
        int duit50rb = (int) uang/500000;
        uang = uang%50000;
        int duit20rb = (int) uang/20000;
        uang = uang%20000;
        int duit10rb = (int) uang/10000;
        uang = uang%10000;
        int duit5rb = (int) uang/5000;
        uang = uang%5000;
        int duit2rb = (int) uang/2000;
        uang = uang%2000;
        int duit1rb = (int) uang/1000;
        uang = uang%1000;

        loopingKembalian("../src/com/tubes/img/duit/seratusribu.png",duit100rb);
        loopingKembalian("../src/com/tubes/img/duit/limapuluhrb.png",duit50rb);
        loopingKembalian("../src/com/tubes/img/duit/duapuluhrb.png",duit20rb);
        loopingKembalian("../src/com/tubes/img/duit/sepuluhrb.png",duit10rb);
        loopingKembalian("../src/com/tubes/img/duit/limarb.png",duit5rb);
        loopingKembalian("../src/com/tubes/img/duit/duarb.png",duit2rb);
        loopingKembalian("../src/com/tubes/img/duit/seribu.png",duit1rb);

        vbox.getChildren().add(tilePane);
        Button button = new Button("Silahkan ambil kembalian : Rp." + Double.toString(uang) +", Terima Kasih");
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Stage stage = (Stage) vbox.getScene().getWindow();
            stage.close();
        });
        vbox.getChildren().add(button);
        Stage stage = new Stage();
        stage.setScene(new Scene(vbox));
        stage.show();
    }

    @FXML
    private void handleDragOver(DragEvent event) {
        if (event.getDragboard().hasImage()){
        event.acceptTransferModes(TransferMode.ANY);}
    }

    @FXML
    private void handleDragOver2(DragEvent event) {
        if (event.getDragboard().hasImage()){
            event.acceptTransferModes(TransferMode.ANY);}
    }

    @FXML
    private void btnCancel(ActionEvent actionEvent) {
    }

    @FXML
    private void btnBuy(ActionEvent actionEvent) {
        if (getItemSelected() == null) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Kesalahan dalam pembelian");
            alert.setContentText("Silahkan pilih item yang ingin dibeli");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Konfirmasi Pembelian");
            alert.setContentText("Anda akan membeli " + getItemSelected().getNama() + ". \nTekan OK jika setuju");
            Optional<ButtonType> buttonResult = alert.showAndWait();
            if (buttonResult.get() == ButtonType.OK) {
                tilePane.setDisable(true);
                tilePaneTest.setDisable(false);
                tilePaneTest.setVisible(true);
                buy.setDisable(true);
                cancel.setDisable(true);
//                pembayaran(getItemSelected().getHarga());
            }
        }
    }
}
