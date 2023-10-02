package com.gubee.arch;

import com.gubee.arch.factories.DaoFactory;
import com.gubee.arch.factories.DaoFactoryProvider;
import com.gubee.arch.dao.Dao;
import com.gubee.arch.presenter.NotificationPresenter;
import com.gubee.arch.service.PoolingNotificationService;
import com.gubee.arch.utils.DaoFactoryType;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ScheduledExecutorService controller = Executors.newSingleThreadScheduledExecutor();

        DaoFactory daoFactory = DaoFactoryProvider.getDaoFactory(DaoFactoryType.DYNAMIC);

        Dao dao = daoFactory.getDao();

        var notificationUseCase = new PoolingNotificationService(dao);

        NotificationPresenter emailPresenter = (message) -> System.out.printf("email %s", message);
        NotificationPresenter whatsAppPresenter = (message) -> System.out.printf("whatApp %s", message);
        NotificationPresenter smsPresenter = (message) -> System.out.printf("sms %s", message);

        NotificationPresenter[] notifications = {emailPresenter, whatsAppPresenter, smsPresenter};

        controller.scheduleAtFixedRate(() -> {
            var nextPos = Math.abs(new Random().nextInt()) % 3;
            notificationUseCase.notifyEveryHour(UUID.randomUUID().toString(), notifications[nextPos]);
            System.out.println();
        }, 1, 1, TimeUnit.SECONDS);
    }
}