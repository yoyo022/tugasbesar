package com.tubes.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "transaction", schema = "vendingmachine", catalog = "")
public class TransactionEntity {
    private int id;
    private Timestamp tanggal;
    private String transactioncol;
    private ItemEntity itemByItemId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "tanggal", nullable = false)
    public Timestamp getTanggal() {
        return tanggal;
    }

    public void setTanggal(Timestamp tanggal) {
        this.tanggal = tanggal;
    }

//    @Basic
//    @Column(name = "transactioncol", nullable = false, length = 45)
//    public String getTransactioncol() {
//        return transactioncol;
//    }
//
//    public void setTransactioncol(String transactioncol) {
//        this.transactioncol = transactioncol;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionEntity that = (TransactionEntity) o;
        return id == that.id &&
                Objects.equals(tanggal, that.tanggal) &&
                Objects.equals(transactioncol, that.transactioncol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tanggal, transactioncol);
    }

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id", nullable = false)
    public ItemEntity getItemByItemId() {
        return itemByItemId;
    }

    public void setItemByItemId(ItemEntity itemByItemId) {
        this.itemByItemId = itemByItemId;
    }
}
