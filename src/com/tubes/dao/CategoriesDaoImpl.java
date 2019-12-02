package com.tubes.dao;

import com.tubes.entity.CategoryEntity;
import com.tubes.util.DaoService;
import com.tubes.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class CategoriesDaoImpl implements DaoService<CategoryEntity> {
    @Override
    public List<CategoryEntity> showAll() {
        List<CategoryEntity> categories = new ArrayList<>();
        Session session = HibernateUtil.getSession();
        Criteria criteria = session.createCriteria(CategoryEntity.class);
        categories.addAll(criteria.list());
        return categories;
    }

    @Override
    public int addData(CategoryEntity object) {
        return 0;
    }

    @Override
    public int updateData(CategoryEntity object) {
        return 0;
    }

    @Override
    public int deleteData(CategoryEntity object) {
        return 0;
    }
}
