package com.tubes.dao;

import com.tubes.entity.UserEntity;
import com.tubes.util.DaoService;
import com.tubes.util.HibernateUtil;
import com.tubes.util.Konektor;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements DaoService<UserEntity> {
    public UserEntity masuk(String username, String password){
        UserEntity user = null;
        try {
            Connection connection = Konektor.connection();
            String query = "SELECT * FROM user WHERE username = ? AND password = (?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                user = new UserEntity();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        } catch ( ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
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
