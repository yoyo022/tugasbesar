package com.tubes.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item", schema = "vendingmachine", catalog = "")
public class ItemEntity {
    private int id;
    private String nama;
    private double harga;
    private Integer quantity;
    private String foto;
    private CategoryEntity categoryByCategoryId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nama", nullable = false, length = 100)
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Basic
    @Column(name = "harga", nullable = false, precision = 0)
    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    @Basic
    @Column(name = "quantity", nullable = true)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "foto", nullable = false, length = 100)
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemEntity that = (ItemEntity) o;
        return id == that.id &&
                Double.compare(that.harga, harga) == 0 &&
                Objects.equals(nama, that.nama) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(foto, that.foto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nama, harga, quantity, foto);
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    public CategoryEntity getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(CategoryEntity categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
    }
}
