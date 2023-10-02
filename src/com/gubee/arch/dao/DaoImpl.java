package com.gubee.arch.dao;

import com.gubee.arch.annotations.Transaction;
import com.gubee.arch.domain.Customer;

public class DaoImpl implements Dao {
    @Override
    @Transaction
    public boolean validate(Customer customer) {
        System.out.println("Validating entity");
        return true;
    }

    @Override
    @Transaction
    public Customer save(Customer customer) {
        System.out.println("Saving entity");
        return customer;
    }
}