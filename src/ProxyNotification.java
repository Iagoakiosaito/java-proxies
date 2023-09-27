import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ProxyNotification {

    public void notifyEveryHour(String userId, UseCaseNotification.PresenterNotification[] notifications, ScheduledExecutorService controller, PoolingUseCaseNotification notificationUseCase) {
        controller.scheduleAtFixedRate(() -> {
            var nextPos = Math.abs(new Random().nextInt()) % 3;
            try {
                var method = notificationUseCase.getClass().getMethod("notifyEveryHour", String.class, UseCaseNotification.PresenterNotification.class);
                if (method.isAnnotationPresent(Transaction.class)) {
                    System.out.printf("Iniciando execução do método %s.%s\n", notificationUseCase.getClass().getSimpleName(), "notifyEveryHour");
                    notificationUseCase.notifyEveryHour(userId, notifications[nextPos]);
                    System.out.printf("Finalizando execução do método %s.%s com sucesso\n", notificationUseCase.getClass().getSimpleName(), "notifyEveryHour");
                } else {
                    System.out.printf("Finalizando execução do método %s.%s com erro\n", notificationUseCase.getClass().getSimpleName(), "notifyEveryHour");
                }
            } catch (NoSuchMethodException e) {
                System.out.printf("Finalizando execução do método %s.%s com erro\n", notificationUseCase.getClass().getSimpleName(), "notifyEveryHour");
            }

            System.out.println();
        }, 1, 1, TimeUnit.SECONDS);
    }
}
