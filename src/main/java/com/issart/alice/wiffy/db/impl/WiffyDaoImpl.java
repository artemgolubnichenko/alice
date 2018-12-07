package com.issart.alice.wiffy.db.impl;

import com.google.inject.Inject;
import com.issart.alice.wiffy.db.IWiffyDao;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class WiffyDaoImpl implements IWiffyDao {

    @Inject
    private Sql2o sql2o;

    @Override
    public void addPassword(String userId, String device, String password) {
        try(Connection connection = sql2o.open()) {
            connection.createQuery("INSERT INTO wiffy(user_id, device, password) " +
                "VALUES (:user_id, :device, :password) ON CONFLICT (user_id, device) " +
                "DO UPDATE SET password = :password, updated_at = now()")
                .addParameter("user_id", userId)
                .addParameter("device", device)
                .addParameter("password", password)
                .executeUpdate();
        }
    }

    @Override
    public String getPassword(String userId, String device) {
        try(Connection connection = sql2o.open()) {
            return connection.createQuery("SELECT password FROM wiffy " +
                "WHERE user_id = :user_id AND device = :device")
                .addParameter("user_id", userId)
                .addParameter("device", device)
                .executeAndFetchFirst(String.class);
        }
    }
}
