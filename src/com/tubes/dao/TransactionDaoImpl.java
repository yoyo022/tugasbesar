package com.tubes.dao;

import com.tubes.entity.TransactionEntity;
import com.tubes.util.DaoService;
import com.tubes.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl implements DaoService<TransactionEntity> {
    @Override
    public List<TransactionEntity> showAll() {
        List<TransactionEntity> transactions = new ArrayList<>();
        Session session = HibernateUtil.getSession();
        Criteria criteria = session.createCriteria(TransactionEntity.class);
        transactions.addAll(criteria.list());
        return transactions;
    }

    @Override
    public int addData(TransactionEntity object) {
        return 0;
    }

    @Override
    public int updateData(TransactionEntity object) {
        return 0;
    }

    @Override
    public int deleteData(TransactionEntity object) {
        return 0;
    }
}
