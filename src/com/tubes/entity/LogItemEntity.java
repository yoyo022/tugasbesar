package com.tubes.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "log_item", schema = "vendingmachine", catalog = "")
public class LogItemEntity {
    private int idlog;
    private Timestamp tglMasuk;
    private ItemEntity itemByItemId;
    private UserEntity userByUserId;

    @Id
    @Column(name = "idlog", nullable = false)
    public int getIdlog() {
        return idlog;
    }

    public void setIdlog(int idlog) {
        this.idlog = idlog;
    }

    @Basic
    @Column(name = "tgl_Masuk", nullable = false)
    public Timestamp getTglMasuk() {
        return tglMasuk;
    }

    public void setTglMasuk(Timestamp tglMasuk) {
        this.tglMasuk = tglMasuk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogItemEntity that = (LogItemEntity) o;
        return idlog == that.idlog &&
                Objects.equals(tglMasuk, that.tglMasuk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idlog, tglMasuk);
    }

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    public ItemEntity getItemByItemId() {
        return itemByItemId;
    }

    public void setItemByItemId(ItemEntity itemByItemId) {
        this.itemByItemId = itemByItemId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }
}
