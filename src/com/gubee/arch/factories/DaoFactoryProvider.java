package com.gubee.arch.factories;

import com.gubee.arch.utils.DaoFactoryType;

public class DaoFactoryProvider {
    public static DaoFactory getDaoFactory(DaoFactoryType type) {
        return switch (type) {
            case DYNAMIC -> new DynamicDaoFactory();
            case DEFAULT -> new DefaultDaoFactory();
        };
    }
}