package com.tubes.dao;

import com.tubes.entity.CategoryEntity;
import com.tubes.entity.LogItemEntity;
import com.tubes.util.DaoService;
import com.tubes.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class LogItemDao implements DaoService<LogItemEntity> {
    @Override
    public List<LogItemEntity> showAll() {
        List<LogItemEntity> logItems = new ArrayList<>();
        Session session = HibernateUtil.getSession();
        Criteria criteria = session.createCriteria(LogItemEntity.class);
        logItems.addAll(criteria.list());
        return logItems;
    }

    @Override
    public int addData(LogItemEntity object) {
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
    public int updateData(LogItemEntity object) {
        return 0;
    }

    @Override
    public int deleteData(LogItemEntity object) {
        return 0;
    }
}
