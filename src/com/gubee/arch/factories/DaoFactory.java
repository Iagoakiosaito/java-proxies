package com.gubee.arch.factories;


import com.gubee.arch.dao.Dao;

public interface DaoFactory {
    Dao getDao();
}
