package com.tubes.controller;

import com.tubes.dao.ItemsDaoImpl;
import com.tubes.entity.ItemEntity;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

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

    public ImageView buatMoney(String url){
        Image img = new Image("File:src/" + url);
        ImageView imgView = new ImageView(img);
        imgView.setId(String.valueOf(moneyId));
        imgView.setFitWidth(120);
        imgView.setFitHeight(40);
        imgView.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard dragboard = imgView.startDragAndDrop(TransferMode.ANY);
                ClipboardContent clipboardContent = new ClipboardContent();
                clipboardContent.putImage(imgView.getImage());
                dragboard.setContent(clipboardContent);
                event.consume();
            }
        });
        moneyId+=1;
        return imgView;
    }

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

    public VBox buatVBoxMoney(ImageView imgView){
        VBox vBox = new VBox();
        vBox.setStyle("-fx-border-width: 1px");
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(0,imgView);
        return vBox;
    }


    public ItemsDaoImpl getItemsDao() {
        if(itemsDao == null){
            itemsDao = new ItemsDaoImpl();
        }
        return itemsDao;
    }

    public List<ItemEntity> getItemEntities() {
        if(itemEntities == null){
            itemEntities = new ArrayList<>();
            itemEntities.addAll(getItemsDao().showAll());
        }
        return itemEntities;
    }

    private ImageView buatImage(String photo){
        Image image = new Image("File:" + photo);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        return imageView;
    }

    private Label buatQuantityLabel(int quantity){
        Label label = new Label();
        label.setText(String.valueOf(quantity));
        label.setAlignment(Pos.CENTER);
        return label;
    }
    private Label buatHargaLabel(double harga){
        Label label = new Label();
        label.setText(Double.toString(harga));
        label.setAlignment(Pos.CENTER);
        return label;
    }

    private Label buatNamaLabel(String nama){
        Label label = new Label();
        label.setText(nama);
        label.setAlignment(Pos.CENTER);
        return label;
    }

    private TilePane tilePane2(){
        TilePane tilePanes = new TilePane();
        return tilePanes;
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
            boolean check = beliItem();
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

    private VBox makingVBox2(ItemEntity item, Label namaLabel, Label hargaLabel, ImageView imgView){
        VBox vBox = new VBox();
        String simpenId = "item"+ item.toString();
        vBox.setId(simpenId);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-border-width: 1px; -fx-background-color: white;");
        vBox.getChildren().add(0,imgView);
        vBox.getChildren().add(1,namaLabel);
        vBox.getChildren().add(2,hargaLabel);

        return vBox;
    }

    private boolean beliItem(){
        boolean submitPembelian = false;
        if(getItemSelected() == null){
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Kesalahan dalam pembelian");
            alert.setContentText("Silahkan pilih item yang ingin dibeli");
            alert.showAndWait();
        }
        else{
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Konfirmasi Pembelian");
            alert.setContentText("Anda akan membeli "+ getItemSelected().getNama() + ". \nTekan OK jika setuju");
            Optional<ButtonType> buttonResult= alert.showAndWait();
            if(buttonResult.get() == ButtonType.OK){
                submitPembelian = true;
                Label namaLabel = buatNamaLabel(getItemSelected().getNama());
                ImageView imgView = buatImage(getItemSelected().getFoto());
                Label hrgLabel = buatHargaLabel(getItemSelected().getHarga());
                Label quantityLabel = buatQuantityLabel(getItemSelected().getQuantity());
                VBox vBox = makingVBox2(getItemSelected(), namaLabel, hrgLabel,imgView);
                vBoxx.getChildren().add(vBox);
            }
        }
        return submitPembelian;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

    @FXML
    private void dropMoney(DragEvent event) {
        ImageView imgView = (ImageView) event.getGestureSource();
        double nilaiMoney = 0;
        String idMoney = imgView.getId();
        switch (idMoney){
            case "1" :{nilaiMoney=100000; break;}
            case "2" :{nilaiMoney=50000; break;}
            case "3" :{nilaiMoney=20000; break;}
            case "4" :{nilaiMoney=10000; break;}
            case "5" :{nilaiMoney=5000; break;}
            case "6" :{nilaiMoney=2000; break;}
            case "7" :{nilaiMoney=1000; break;}
        }
        if(nilaiMoney < getItemSelected().getHarga()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gagal Membeli");
            alert.setContentText("Uang yang dimasukkan tidak cukup untuk melakukan transaksi");
            alert.showAndWait();
        }else{

        }
    }

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

    private void buatKembalian(double uang){
        TilePane tilePane = new TilePane();
        tilePane.setAlignment(Pos.CENTER);
        tilePane.setPadding(new Insets(7));
        tilePane.setHgap(15);
        tilePane.setVgap(15);

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

        for(int i=0;i<duit100rb; i++){
            Image image = new Image("../src/com/tubes/img/duit/seratusribu.png");
            ImageView imgView = new ImageView(image);
            imgView.setFitHeight(60);
            imgView.setFitWidth(140);
            tilePane.getChildren().add(imgView);
        }
    }
}
