package com.gubee.arch.proxy;

import com.gubee.arch.annotations.Transaction;
import com.gubee.arch.dao.Dao;
import com.gubee.arch.domain.Customer;

import java.lang.reflect.Method;

public class ProxyDao implements Dao {
    private final Dao dao;

    public ProxyDao(Dao dao) {
        this.dao = dao;
    }

    @Override
    public boolean validate(Customer customer) {
        if (methodHasTransactionAnnotation("validate")) {
            System.out.println("Iniciando transação");
            boolean result = dao.validate(customer);
            System.out.println("Finalizando transação com sucesso");
            return result;
        } else {
            return dao.validate(customer);
        }
    }

    @Override
    public Customer save(Customer customer) {
        if (methodHasTransactionAnnotation("save")) {
            System.out.println("Iniciando transação");
            Customer result = dao.save(customer);
            System.out.println("Finalizando transação com sucesso");
            return result;
        } else {
            return dao.save(customer);
        }
    }

    private boolean methodHasTransactionAnnotation(String methodName) {
        try {
            Method method = Dao.class.getMethod(methodName, Customer.class);
            return method.isAnnotationPresent(Transaction.class);
        } catch (NoSuchMethodException e) {
            return false;
        }
    }
}