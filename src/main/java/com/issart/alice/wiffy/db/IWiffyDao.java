package com.issart.alice.wiffy.db;

public interface IWiffyDao {

    void addPassword(String userId, String device, String password);
    String getPassword(String userId, String device);
}
