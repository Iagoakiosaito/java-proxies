package com.gubee.arch.dao;

import com.gubee.arch.domain.Customer;

public interface Dao {
    boolean validate(Customer customer);

    Customer save(Customer customer);
}
