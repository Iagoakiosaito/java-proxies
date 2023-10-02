package com.gubee.arch.proxy;

import com.gubee.arch.annotations.Transaction;
import com.gubee.arch.dao.Dao;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyDaoInterceptor implements InvocationHandler {
    private final Dao dao;

    public DynamicProxyDaoInterceptor(Dao dao2) {
        this.dao = dao2;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Transaction.class)) {
            System.out.println("Iniciando transação");
            Object result = method.invoke(dao, args);
            System.out.println("Finalizando transação com sucesso");
            return result;
        } else {
            return method.invoke(dao, args);
        }
    }
}