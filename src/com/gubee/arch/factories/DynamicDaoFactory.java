package com.gubee.arch.factories;

import com.gubee.arch.dao.Dao;
import com.gubee.arch.dao.DaoImpl;
import com.gubee.arch.proxy.DynamicProxyDaoInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

class DynamicDaoFactory implements DaoFactory {
    @Override
    public Dao getDao() {
        Dao dao = new DaoImpl();
        InvocationHandler handler = new DynamicProxyDaoInterceptor(dao);
        return (Dao) Proxy.newProxyInstance(Dao.class.getClassLoader(), new Class[]{Dao.class}, handler);
    }
}