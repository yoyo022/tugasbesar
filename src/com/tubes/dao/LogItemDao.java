package com.tubes.dao;

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
//        Connection connection = Konektor.connection();
//        String query = "insert into log_item(tgl_Masuk,item_id,user_id) values(?,?,?)";
//        PreparedStatement ps = connection.prepareStatement(query);
//        ps.setTimestamp(1,object.getTglMasuk());
//        ps.setObject(2,object.getItemByItemId());
//        ps.setObject(2,object.getUserByUserId());
//        if(ps.executeUpdate() != 0){
//            connection.commit();
//            result = 1;
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
