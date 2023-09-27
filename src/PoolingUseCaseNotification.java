import java.util.Random;

class PoolingUseCaseNotification implements UseCaseNotification {
    @Override
    @Transaction
    public void notifyEveryHour(String customerId, PresenterNotification presenter) {
        System.out.println("processando regra de negocio");
        presenter.notification(String.format("mensagem a ser enviada para %s: %s", customerId, new Random().nextInt()));
    }
}