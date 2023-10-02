package com.gubee.arch.factories;


import com.gubee.arch.dao.Dao;
import com.gubee.arch.dao.DaoImpl;
import com.gubee.arch.proxy.ProxyDao;

class DefaultDaoFactory implements DaoFactory {
    @Override
    public Dao getDao() {
        return new ProxyDao(new DaoImpl());
    }
}
