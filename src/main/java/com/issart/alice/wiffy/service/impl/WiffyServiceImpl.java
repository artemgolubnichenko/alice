package com.issart.alice.wiffy.service.impl;

import com.google.inject.Inject;
import com.issart.alice.wiffy.db.IWiffyDao;
import com.issart.alice.wiffy.service.IWiffyService;

public class WiffyServiceImpl implements IWiffyService {

    @Inject
    private IWiffyDao wiffyDao;

    @Override
    public void addOrUpdatePassword(String userId, String device, String password) {
        wiffyDao.addPassword(userId, device, password);
    }

    @Override
    public String getPassword(String userId, String device) {
        return wiffyDao.getPassword(userId, device);
    }
}
