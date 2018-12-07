package com.issart.alice.wiffy.service;

public interface IWiffyService {

    void addOrUpdatePassword(String userId, String device, String password);
    String getPassword(String userId, String device);
}
