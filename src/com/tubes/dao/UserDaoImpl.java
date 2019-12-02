package com.tubes.dao;

import com.tubes.entity.UserEntity;
import com.tubes.util.DaoService;
import com.tubes.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements DaoService<UserEntity> {
    @Override
    public List<UserEntity> showAll() {
        List<UserEntity> users = new ArrayList<>();
        Session session = HibernateUtil.getSession();
        Criteria criteria = session.createCriteria(UserEntity.class);
        users.addAll(criteria.list());
        return users;
    }

    @Override
    public int addData(UserEntity object) {
        return 0;
    }

    @Override
    public int updateData(UserEntity object) {
        return 0;
    }

    @Override
    public int deleteData(UserEntity object) {
        return 0;
    }
}
