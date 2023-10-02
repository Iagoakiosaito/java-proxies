package com.gubee.arch.ports;

import com.gubee.arch.annotations.Transaction;
import com.gubee.arch.presenter.NotificationPresenter;

public interface NotificationUseCase {
    @Transaction
    void notifyEveryHour(String customerId, NotificationPresenter presenter);
}
