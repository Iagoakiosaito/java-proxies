package com.gubee.arch.service;

import com.gubee.arch.annotations.Transaction;
import com.gubee.arch.dao.Dao;
import com.gubee.arch.domain.Customer;
import com.gubee.arch.ports.NotificationUseCase;
import com.gubee.arch.presenter.NotificationPresenter;

import java.util.Random;

public class PoolingNotificationService implements NotificationUseCase {
    private final Dao dao;

    public PoolingNotificationService(Dao dao) {
        this.dao = dao;
    }

    @Override
    @Transaction
    public void notifyEveryHour(String customerId, NotificationPresenter presenter) {
        Customer customer = new Customer(customerId, "Test");
        System.out.println("Iniciando execução do método notifyEveryHour da classe PoolingUseCaseNotification");
        System.out.println("processando regra de negocio");
        presenter.notification(String.format("mensagem a ser enviada para %s: %s", customerId, new Random().nextInt()));
        if (dao.validate(customer)) {// Exemplo de chamada ao Dao com transação
            dao.save(customer);
        }
           // Exemplo de chamada ao Dao com transação
        System.out.println("Finalizando execução do método notifyEveryHour da classe PoolingUseCaseNotification com sucesso");
    }
}