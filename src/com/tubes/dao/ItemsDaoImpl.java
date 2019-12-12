package com.tubes.dao;

import com.tubes.entity.ItemEntity;
import com.tubes.util.DaoService;
import com.tubes.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ItemsDaoImpl implements DaoService<ItemEntity> {
    @Override
    public List<ItemEntity> showAll() {
        List<ItemEntity> items = new ArrayList<>();
        Session session = HibernateUtil.getSession();
        Criteria criteria = session.createCriteria(ItemEntity.class);
        items.addAll(criteria.list());
        return items;
    }

    @Override
    public int addData(ItemEntity object) {
        int result = 0;
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
                session.save(object);
                transaction.commit();
                result=1;
        }catch (HibernateException e){
                transaction.rollback();
        }
        return result;
    }

    @Override
    public int updateData(ItemEntity object) {
        int result = 0;
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(object);
            transaction.commit();
            result = 1;
        }catch (HibernateException e){
            transaction.rollback();
        }
        session.close();
        return result;
    }

    @Override
    public int deleteData(ItemEntity object) {
        int result = 0;
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(object);
            transaction.commit();
            result=1;
        }catch (HibernateException e){
            transaction.rollback();
        }
        return result;
    }
}
