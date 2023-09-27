import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Main {
    public static void main(String[] args) {
        ScheduledExecutorService controller = Executors.newSingleThreadScheduledExecutor();
        var notificationUseCase = new PoolingUseCaseNotification();
        UseCaseNotification.PresenterNotification emailPresenter = (message) -> System.out.printf("email %s", message);
        UseCaseNotification.PresenterNotification whatsAppPresenter = (message) -> System.out.printf("whatApp %s", message);
        UseCaseNotification.PresenterNotification smsPresenter = (message) -> System.out.printf("sms %s", message);
        UseCaseNotification.PresenterNotification[] notifications = {emailPresenter, whatsAppPresenter, smsPresenter};

        var notificationProcess = new ProxyNotification();
        notificationProcess.notifyEveryHour(UUID.randomUUID().toString(), notifications, controller, notificationUseCase);

    }
}